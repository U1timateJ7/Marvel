package net.tintankgames.marvel.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;

@EventBusSubscriber(Dist.CLIENT)
public record SyncSprintMessage(boolean sprinting) implements CustomPacketPayload {
    public static final StreamCodec<RegistryFriendlyByteBuf, SyncSprintMessage> CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, SyncSprintMessage::sprinting,
            SyncSprintMessage::new
    );

    public static void handle(SyncSprintMessage message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.flow().isServerbound() && context.player() instanceof ServerPlayer player) {
                player.setData(MarvelAttachmentTypes.MOVING.get(), message.sprinting);
            }
        });
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void syncSprint(ClientTickEvent.Post event) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().getConnection().getConnection().isConnected()) {
            boolean sprint = Minecraft.getInstance().player.input.hasForwardImpulse();
            Minecraft.getInstance().player.setData(MarvelAttachmentTypes.MOVING.get(), sprint);
            PacketDistributor.sendToServer(new SyncSprintMessage(sprint));
        }
    }

    @Override
    public Type<SyncSprintMessage> type() {
        return MarvelNetworking.SYNC_SPRINT;
    }
}
