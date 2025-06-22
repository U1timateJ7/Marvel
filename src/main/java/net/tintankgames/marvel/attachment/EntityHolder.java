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
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
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
            PacketDistributor.sendToPlayer(player, new SyncMessage(player.getData(MarvelAttachmentTypes.HELD_ENTITY).entity != null ? Optional.of(player.getData(MarvelAttachmentTypes.HELD_ENTITY).entity.getUUID()) : Optional.empty(), player.getData(MarvelAttachmentTypes.ENTITY_SUIT)));
        }
    }

    @SubscribeEvent
    public static void changedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            Entity held = player.getData(MarvelAttachmentTypes.HELD_ENTITY).entity;
            if (held != null) {
                Entity entity = held.getType().create(player.level());
                if (entity != null) {
                    entity.restoreFrom(held);
                    entity.moveTo(player.getX(), player.getY(), player.getZ(), held.getYRot(), held.getXRot());
                    player.serverLevel().addDuringTeleport(entity);
                    player.getData(MarvelAttachmentTypes.HELD_ENTITY).entity = entity;
                }
                held.setRemoved(Entity.RemovalReason.CHANGED_DIMENSION);
            }
            PacketDistributor.sendToPlayer(player, new SyncMessage(player.getData(MarvelAttachmentTypes.HELD_ENTITY).entity != null ? Optional.of(player.getData(MarvelAttachmentTypes.HELD_ENTITY).entity.getUUID()) : Optional.empty(), player.getData(MarvelAttachmentTypes.ENTITY_SUIT)));
        }
    }

    @SubscribeEvent
    public static void clone(PlayerEvent.Clone event) {
        if (event.getEntity() instanceof ServerPlayer player && !event.isWasDeath()) {
            player.setData(MarvelAttachmentTypes.HELD_ENTITY, event.getOriginal().getData(MarvelAttachmentTypes.HELD_ENTITY));
            player.setData(MarvelAttachmentTypes.ENTITY_SUIT, event.getOriginal().getData(MarvelAttachmentTypes.ENTITY_SUIT));
        }
    }

    public record SyncMessage(Optional<UUID> held, EntitySuit suit) implements CustomPacketPayload {
        public static final Type<SyncMessage> TYPE = new Type<>(MarvelSuperheroes.id("held_entity_sync"));
        public static final StreamCodec<RegistryFriendlyByteBuf, SyncMessage> CODEC = StreamCodec.composite(UUIDUtil.STREAM_CODEC.apply(ByteBufCodecs::optional), SyncMessage::held, EntitySuit.STREAM_CODEC, SyncMessage::suit, SyncMessage::new);

        public static void handle(SyncMessage message, IPayloadContext context) {
            context.enqueueWork(() -> {
                if (context.flow().isClientbound() && context.player() instanceof LocalPlayer player) {
                    player.getData(MarvelAttachmentTypes.HELD_ENTITY).entity = message.held().isPresent() ? player.clientLevel.getEntities().get(message.held().get()) : null;
                    player.setData(MarvelAttachmentTypes.ENTITY_SUIT, message.suit());
                }
            });
        }

        @Override
        public Type<SyncMessage> type() {
            return TYPE;
        }
    }
}
