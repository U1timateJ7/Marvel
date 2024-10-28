package net.tintankgames.marvel.attachment;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tintankgames.marvel.MarvelSuperheroes;

import java.util.Optional;
import java.util.UUID;

@EventBusSubscriber
public class EntityHolder<T extends Entity> {
    public T entity;

    public EntityHolder(T entity) {
        this.entity = entity;
    }

    @SubscribeEvent
    public static void tick(PlayerTickEvent.Post event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            PacketDistributor.sendToPlayer(player, new SyncMessage(player.getData(MarvelAttachmentTypes.HELD_ENTITY).entity != null ? Optional.of(player.getData(MarvelAttachmentTypes.HELD_ENTITY).entity.getUUID()) : Optional.empty()));
        }
    }

    public record SyncMessage(Optional<UUID> uuid) implements CustomPacketPayload {
        public static final Type<SyncMessage> TYPE = new Type<>(MarvelSuperheroes.id("held_entity_sync"));
        public static final StreamCodec<RegistryFriendlyByteBuf, SyncMessage> CODEC = StreamCodec.composite(UUIDUtil.STREAM_CODEC.apply(ByteBufCodecs::optional), SyncMessage::uuid, SyncMessage::new);

        public static void handle(SyncMessage message, IPayloadContext context) {
            context.enqueueWork(() -> {
                if (context.flow().isClientbound() && context.player() instanceof LocalPlayer player) {
                    player.getData(MarvelAttachmentTypes.HELD_ENTITY).entity = message.uuid().isPresent() ? player.clientLevel.getEntities().get(message.uuid().get()) : null;
                }
            });
        }

        @Override
        public Type<SyncMessage> type() {
            return TYPE;
        }
    }
}
