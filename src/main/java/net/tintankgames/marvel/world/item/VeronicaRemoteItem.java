package net.tintankgames.marvel.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.network.OpenVeronicaMessage;

public class VeronicaRemoteItem extends Item {
    public VeronicaRemoteItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (player instanceof ServerPlayer serverPlayer) {
            if (serverPlayer.getData(MarvelAttachmentTypes.VERONICA).enabled()) {
                PacketDistributor.sendToPlayer(serverPlayer, OpenVeronicaMessage.INSTANCE);
            } else {
                serverPlayer.sendSystemMessage(Component.translatable("item.marvel.veronica_remote.fail").withStyle(ChatFormatting.RED), true);
            }
        }
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }
}
