package net.tintankgames.marvel.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tintankgames.marvel.world.inventory.SuitTableMenu;
import net.tintankgames.marvel.world.level.block.entity.MarvelBlockEntityTypes;

public record SwitchSuitTabMessage(int tab) implements CustomPacketPayload {
    public static final StreamCodec<RegistryFriendlyByteBuf, SwitchSuitTabMessage> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, SwitchSuitTabMessage::tab,
            SwitchSuitTabMessage::new
    );

    public static void handle(SwitchSuitTabMessage message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.flow().isServerbound() && context.player() instanceof ServerPlayer player) {
                BlockPos pos = ((SuitTableMenu)player.containerMenu).getBlockPos();
                player.serverLevel().getBlockEntity(pos, MarvelBlockEntityTypes.SUIT_TABLE.get()).ifPresent(suitTable -> {
                    suitTable.tab = message.tab;
                    player.openMenu(suitTable);
                });
            }
        });
    }

    @Override
    public Type<SwitchSuitTabMessage> type() {
        return MarvelNetworking.SWITCH_SUIT_TAB;
    }
}
