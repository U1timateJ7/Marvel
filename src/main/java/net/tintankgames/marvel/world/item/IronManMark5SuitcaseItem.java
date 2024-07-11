package net.tintankgames.marvel.world.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.timers.TimerQueue;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.sounds.MarvelSoundEvents;
import net.tintankgames.marvel.world.level.timers.SetItemInSlotCallback;
import net.tintankgames.marvel.world.level.timers.ToggleHelmetCallback;

import java.util.List;

public class IronManMark5SuitcaseItem extends Item {
    public IronManMark5SuitcaseItem(Properties properties) {
        super(properties.stacksTo(1).component(DataComponents.CONTAINER, ItemContainerContents.fromItems(List.of(MarvelItems.IRON_MAN_MARK_5_BOOTS.toStack(), MarvelItems.IRON_MAN_MARK_5_LEGGINGS.toStack(), MarvelItems.IRON_MAN_MARK_5_CHESTPLATE.toStack(), MarvelItems.IRON_MAN_MARK_5_HELMET.toStack()))));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        boolean noMark5Armor = true, noThorArmor = true;
        for (ItemStack armor : player.getInventory().armor) {
            if (armor.is(MarvelItems.Tags.IRON_MAN_MARK_5_ARMOR)) {
                noMark5Armor = false;
                break;
            }
        }
        for (ItemStack armor : player.getInventory().armor) {
            if (armor.is(MarvelItems.Tags.THOR_ARMOR)) {
                noThorArmor = false;
                break;
            }
        }
        ItemStack stack = player.getItemInHand(hand);
        if (noMark5Armor && noThorArmor) {
            if (player instanceof ServerPlayer serverPlayer) {
                ItemContainerContents suitcaseContents = stack.get(DataComponents.CONTAINER);
                TimerQueue<MinecraftServer> timerqueue = player.getServer().getWorldData().overworldData().getScheduledEvents();
                long i = serverPlayer.serverLevel().getGameTime();
                serverPlayer.serverLevel().playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.IRON_MAN_MARK_5_SUITCASE_USE.get(), SoundSource.PLAYERS);
                ItemStack helmet = suitcaseContents.getStackInSlot(3);
                helmet.set(MarvelDataComponents.HELMET_OPEN, true);
                timerqueue.schedule(serverPlayer.getStringUUID() + "_iron_man_mark_5_chestplate_equip", i + 1, new SetItemInSlotCallback(serverPlayer, EquipmentSlot.CHEST, suitcaseContents.getStackInSlot(2), false, false));
                timerqueue.schedule(serverPlayer.getStringUUID() + "_iron_man_mark_5_leggings_equip", i + 10, new SetItemInSlotCallback(serverPlayer, EquipmentSlot.LEGS, suitcaseContents.getStackInSlot(1), false, false));
                timerqueue.schedule(serverPlayer.getStringUUID() + "_iron_man_mark_5_boots_equip", i + 20, new SetItemInSlotCallback(serverPlayer, EquipmentSlot.FEET, suitcaseContents.getStackInSlot(0), false, false));
                timerqueue.schedule(serverPlayer.getStringUUID() + "_iron_man_mark_5_helmet_equip", i + 30, new SetItemInSlotCallback(serverPlayer, EquipmentSlot.HEAD, helmet, false, false));
                timerqueue.schedule(serverPlayer.getStringUUID() + "_iron_man_mark_5_helmet_toggle", i + 40, new ToggleHelmetCallback(serverPlayer));
                stack.shrink(1);
            }
            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
        }
        return InteractionResultHolder.pass(stack);
    }
}
