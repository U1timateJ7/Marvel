package net.tintankgames.marvel.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.network.ToggleHelmetMessage;
import net.tintankgames.marvel.sounds.MarvelSoundEvents;
import net.tintankgames.marvel.world.item.component.Size;

public class SizeItem extends SuitPowerItem {
    private final Size size;

    public SizeItem(Size size, Properties properties) {
        super(properties);
        this.size = size;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack handItem = player.getItemInHand(hand);
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
        if (player instanceof ServerPlayer serverPlayer) {
            if (serverPlayer.getInventory().contains(stack -> stack.is(MarvelItems.PYM_PARTICLE)) || serverPlayer.isCreative() || chestplate.getOrDefault(MarvelDataComponents.SIZE, Size.NORMAL) == size) {
                if (chestplate.getOrDefault(MarvelDataComponents.SIZE, Size.NORMAL) != size && !serverPlayer.isCreative()) {
                    serverPlayer.getInventory().removeItem(SuitItem.findSlotMatchingItem(serverPlayer.getInventory().items, MarvelItems.PYM_PARTICLE.get()), 1);
                }
                if (helmet.getOrDefault(MarvelDataComponents.HELMET_OPEN, false)) {
                    ToggleHelmetMessage.toggleHelmet(serverPlayer);
                }
                chestplate.update(MarvelDataComponents.SIZE, Size.NORMAL, size -> {
                    if ((this.size == Size.SMALL && (size == Size.NORMAL || size == Size.BIG)) || (this.size == Size.BIG && size == Size.BIG)) {
                        serverPlayer.serverLevel().playSound(null, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), MarvelSoundEvents.ANT_MAN_SHRINK.get(), SoundSource.PLAYERS);
                    } else if ((this.size == Size.BIG && (size == Size.NORMAL || size == Size.SMALL)) || (this.size == Size.SMALL && size == Size.SMALL)) {
                        serverPlayer.serverLevel().playSound(null, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), MarvelSoundEvents.ANT_MAN_GROW.get(), SoundSource.PLAYERS);
                    }
                    return size == this.size ? Size.NORMAL : this.size;
                });
            } else {
                player.displayClientMessage(Component.translatable(BuiltInRegistries.ITEM.getKey(this).toLanguageKey("item", "fail")).withStyle(ChatFormatting.RED), true);
            }
        }
        return InteractionResultHolder.sidedSuccess(handItem, level.isClientSide);
    }
}
