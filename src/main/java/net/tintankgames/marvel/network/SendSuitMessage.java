package net.tintankgames.marvel.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.timers.TimerQueue;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.attachment.VeronicaData;
import net.tintankgames.marvel.world.entity.VeronicaSentry;
import net.tintankgames.marvel.world.item.SentryIronManSuitItem;
import net.tintankgames.marvel.world.level.timers.SendSuitCallback;

public record SendSuitMessage(VeronicaData.Suit suit, int delay) implements CustomPacketPayload {
    public static final StreamCodec<RegistryFriendlyByteBuf, SendSuitMessage> CODEC = StreamCodec.composite(VeronicaData.Suit.STREAM_CODEC, SendSuitMessage::suit, ByteBufCodecs.INT, SendSuitMessage::delay, SendSuitMessage::new);

    public static void handle(SendSuitMessage message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.flow().isServerbound() && context.player() instanceof ServerPlayer player) {
                if (message.delay <= 0) {
                    VeronicaSentry sentry = ((SentryIronManSuitItem) message.suit.armor().get(2).getItem()).type().spawn(player.serverLevel(), player.blockPosition().offset(player.getRandom().nextInt(-3, 3), 128, player.getRandom().nextInt(-3, 3)), MobSpawnType.TRIGGERED);
                    if (sentry != null) {
                        sentry.setItemSlot(EquipmentSlot.FEET, message.suit.armor().get(0));
                        sentry.setItemSlot(EquipmentSlot.LEGS, message.suit.armor().get(1));
                        sentry.setItemSlot(EquipmentSlot.CHEST, message.suit.armor().get(2));
                        sentry.setItemSlot(EquipmentSlot.HEAD, message.suit.armor().get(3));
                        sentry.setOwnerUUID(player.getUUID());
                        sentry.setFromVeronica(true);
                        player.getData(MarvelAttachmentTypes.VERONICA).removeSuit(message.suit.id());
                    }
                } else {
                    TimerQueue<MinecraftServer> timerqueue = player.server.getWorldData().overworldData().getScheduledEvents();
                    long i = player.serverLevel().getGameTime();
                    timerqueue.schedule(player.getStringUUID() + "_send_suit_" + message.suit.id(), i + message.delay, new SendSuitCallback(player.getUUID(), message.suit));
                }
            }
        });
    }

    @Override
    public Type<SendSuitMessage> type() {
        return MarvelNetworking.SEND_SUIT;
    }
}
