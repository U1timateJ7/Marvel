package net.tintankgames.marvel.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.attachment.VeronicaData;
import net.tintankgames.marvel.world.entity.IronManSentry;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;

public record SendSuitMessage(VeronicaData.Suit suit) implements CustomPacketPayload {
    public static final StreamCodec<RegistryFriendlyByteBuf, SendSuitMessage> CODEC = StreamCodec.composite(VeronicaData.Suit.STREAM_CODEC, SendSuitMessage::suit, SendSuitMessage::new);

    public static void handle(SendSuitMessage message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.flow().isServerbound() && context.player() instanceof ServerPlayer player) {
                IronManSentry sentry = MarvelEntityTypes.IRON_MAN_SENTRY.get().spawn(player.serverLevel(), player.blockPosition().offset(player.getRandom().nextInt(-3, 3), 128, player.getRandom().nextInt(-3, 3)), MobSpawnType.TRIGGERED);
                if (sentry != null) {
                    sentry.setItemSlot(EquipmentSlot.FEET, message.suit.armor().get(0));
                    sentry.setItemSlot(EquipmentSlot.LEGS, message.suit.armor().get(1));
                    sentry.setItemSlot(EquipmentSlot.CHEST, message.suit.armor().get(2));
                    sentry.setItemSlot(EquipmentSlot.HEAD, message.suit.armor().get(3));
                    sentry.setOwnerUUID(player.getUUID());
                    sentry.setFromVeronica(true);
                    player.getData(MarvelAttachmentTypes.VERONICA).removeSuit(message.suit.id());
                }
            }
        });
    }

    @Override
    public Type<SendSuitMessage> type() {
        return MarvelNetworking.SEND_SUIT;
    }
}
