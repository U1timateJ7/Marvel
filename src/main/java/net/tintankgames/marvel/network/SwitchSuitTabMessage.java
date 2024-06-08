package net.tintankgames.marvel.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tintankgames.marvel.world.inventory.SuitUpgradingMenu;
import net.tintankgames.marvel.world.inventory.SuitVariantMenu;
import net.tintankgames.marvel.world.level.block.entity.MarvelBlockEntityTypes;

public record SwitchSuitTabMessage(boolean variant) implements CustomPacketPayload {
    public static final StreamCodec<RegistryFriendlyByteBuf, SwitchSuitTabMessage> CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, SwitchSuitTabMessage::variant,
            SwitchSuitTabMessage::new
    );

    public static void handle(SwitchSuitTabMessage message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.flow().isServerbound() && context.player() instanceof ServerPlayer player) {
                BlockPos pos = message.variant ? ((SuitUpgradingMenu)player.containerMenu).getBlockPos() : ((SuitVariantMenu)player.containerMenu).getBlockPos();
                player.serverLevel().getBlockEntity(pos, MarvelBlockEntityTypes.SUIT_TABLE.get()).ifPresent(suitTable -> {
                    suitTable.variant = message.variant;
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
