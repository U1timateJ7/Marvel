package net.tintankgames.marvel.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.timers.TimerQueue;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.world.level.timers.ResetCallMjolnirCallback;

public class CallMjolnirMessage implements CustomPacketPayload {
    public static final CallMjolnirMessage INSTANCE = new CallMjolnirMessage();
    public static final StreamCodec<RegistryFriendlyByteBuf, CallMjolnirMessage> CODEC = StreamCodec.unit(INSTANCE);

    private CallMjolnirMessage() {

    }

    public static void handle(CallMjolnirMessage message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.flow().isServerbound() && context.player() instanceof ServerPlayer player) {
                if (player.isCrouching()) {
                    player.setData(MarvelAttachmentTypes.CALLING_MJOLNIR, true);
                    TimerQueue<MinecraftServer> timerqueue = player.getServer().getWorldData().overworldData().getScheduledEvents();
                    long i = player.serverLevel().getGameTime();
                    timerqueue.schedule(player.getStringUUID() + "_stop_calling_mjolnir", i + 2, new ResetCallMjolnirCallback(player.getUUID()));
                }
            }
        });
    }

    @Override
    public Type<CallMjolnirMessage> type() {
        return MarvelNetworking.CALL_MJOLNIR;
    }
}
