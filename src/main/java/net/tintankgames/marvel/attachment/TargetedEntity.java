package net.tintankgames.marvel.attachment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.UUIDUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.event.entity.living.LivingHurtEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tintankgames.marvel.MarvelSuperheroes;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;

import java.util.Optional;
import java.util.UUID;

@EventBusSubscriber
public class TargetedEntity implements INBTSerializable<CompoundTag> {
    public static final Codec<TargetedEntity> CODEC = RecordCodecBuilder.create(instance -> instance.group(UUIDUtil.CODEC.optionalFieldOf("uuid").forGetter(target -> target.uuid), Codec.intRange(-1, Integer.MAX_VALUE).fieldOf("time_left").forGetter(target -> target.timeLeft)).apply(instance, TargetedEntity::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, TargetedEntity> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.optional(UUIDUtil.STREAM_CODEC), target -> target.uuid, ByteBufCodecs.INT, target -> target.timeLeft, TargetedEntity::new);

    public Optional<UUID> uuid;
    public int timeLeft = 100;

    public TargetedEntity(Optional<UUID> uuid, int timeLeft) {
        this.uuid = uuid;
        this.timeLeft = timeLeft;
    }

    public TargetedEntity(Entity entity) {
        this(entity != null ? entity.getUUID() : null);
    }

    public TargetedEntity(UUID uuid) {
        this.uuid = Optional.of(uuid);
    }

    public TargetedEntity() {
        this.uuid = Optional.empty();
        this.timeLeft = -1;
    }

    @SubscribeEvent
    public static void tick(PlayerTickEvent.Post event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            TargetedEntity targetedEntity = player.getData(MarvelAttachmentTypes.TARGETED_ENTITY);
            if (targetedEntity.timeLeft >= 0) {
                targetedEntity.timeLeft--;
                if (targetedEntity.timeLeft <= -1) {
                    targetedEntity.uuid = Optional.empty();
                }
                PacketDistributor.sendToPlayer(player, new SyncMessage(targetedEntity));
            }
        }
    }

    @SubscribeEvent
    public static void attack(LivingHurtEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer && event.getSource().getEntity() instanceof LivingEntity living) {
            serverPlayer.setData(MarvelAttachmentTypes.TARGETED_ENTITY, new TargetedEntity(living));
        }
        if (event.getSource().getEntity() instanceof ServerPlayer serverPlayer && event.getEntity() instanceof LivingEntity living) {
            serverPlayer.setData(MarvelAttachmentTypes.TARGETED_ENTITY, new TargetedEntity(living));
        }
    }

    @Nullable
    public Entity getEntity(ServerLevel serverLevel) {
        return uuid.map(serverLevel::getEntity).orElse(null);
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        uuid.ifPresent(value -> tag.putUUID("UUID", value));
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        uuid = nbt.hasUUID("UUID") ? Optional.of(nbt.getUUID("UUID")) : Optional.empty();
    }

    public record SyncMessage(TargetedEntity targetedEntity) implements CustomPacketPayload {
        public static final Type<SyncMessage> TYPE = new Type<>(MarvelSuperheroes.id("targeted_entity_sync"));
        public static final StreamCodec<RegistryFriendlyByteBuf, SyncMessage> CODEC = StreamCodec.composite(TargetedEntity.STREAM_CODEC, SyncMessage::targetedEntity, SyncMessage::new);

        public static void handle(SyncMessage message, IPayloadContext context) {
            context.enqueueWork(() -> {
                if (context.flow().isClientbound() && context.player() instanceof LocalPlayer player) {
                    player.setData(MarvelAttachmentTypes.TARGETED_ENTITY, message.targetedEntity());
                }
            });
        }

        @Override
        public Type<SyncMessage> type() {
            return TYPE;
        }
    }
}
