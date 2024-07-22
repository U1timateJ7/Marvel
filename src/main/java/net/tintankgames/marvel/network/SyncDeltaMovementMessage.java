package net.tintankgames.marvel.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;

@EventBusSubscriber(Dist.CLIENT)
public record SyncDeltaMovementMessage(Vec3 deltaMovement) implements CustomPacketPayload {
    public static final StreamCodec<RegistryFriendlyByteBuf, SyncDeltaMovementMessage> CODEC = StreamCodec.composite(ByteBufCodecs.fromCodec(Vec3.CODEC), SyncDeltaMovementMessage::deltaMovement, SyncDeltaMovementMessage::new);

    public static void handle(SyncDeltaMovementMessage message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.flow().isServerbound() && context.player() instanceof ServerPlayer player) {
                player.setData(MarvelAttachmentTypes.DELTA_MOVEMENT, message.deltaMovement());
            }
        });
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void syncSprint(ClientTickEvent.Post event) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().getConnection().getConnection().isConnected()) {
            PacketDistributor.sendToServer(new SyncDeltaMovementMessage(Minecraft.getInstance().player.getDeltaMovement()));
        }
    }

    @Override
    public Type<SyncDeltaMovementMessage> type() {
        return MarvelNetworking.SYNC_DELTA_MOVEMENT;
    }
}
