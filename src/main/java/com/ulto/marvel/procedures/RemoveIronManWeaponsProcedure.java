package com.ulto.marvel.procedures;

import com.ulto.marvel.world.item.MarvelModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class RemoveIronManWeaponsProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Entity entity = event.player;
			execute(event, entity);
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (!((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_1_CHESTPLATE.get())) {
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = new ItemStack(MarvelModItems.FLAMETHROWER.get());
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
						_player.inventoryMenu.getCraftSlots());
			}
		}
		if (!((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_3_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_5_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_6_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_7_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_16_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_17_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_19_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_20_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_21_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.WAR_MACHINE_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_22_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_23_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_PATRIOT_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_25_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_30_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_33_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_37_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_39_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_42_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_43_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_46_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_47_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.WAR_MACHINE_MARK_2_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_49_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_50_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_85_CHESTPLATE.get())) {
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = new ItemStack(MarvelModItems.REPULSOR.get());
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
						_player.inventoryMenu.getCraftSlots());
			}
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = new ItemStack(MarvelModItems.UNIBEAM.get());
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
						_player.inventoryMenu.getCraftSlots());
			}
		}
		if (!((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.WAR_MACHINE_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_PATRIOT_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.WAR_MACHINE_MARK_2_CHESTPLATE.get())) {
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = new ItemStack(MarvelModItems.WAR_MACHINE_GUN.get());
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
						_player.inventoryMenu.getCraftSlots());
			}
		}
		if (!((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_25_CHESTPLATE.get())) {
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = new ItemStack(MarvelModItems.MARK_25_DRILL.get());
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
						_player.inventoryMenu.getCraftSlots());
			}
		}
		if (!((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_30_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_33_CHESTPLATE.get())) {
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = new ItemStack(MarvelModItems.CENTURION_BLADE.get());
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
						_player.inventoryMenu.getCraftSlots());
			}
		}
		if (!((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_49_CHESTPLATE.get())) {
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = new ItemStack(MarvelModItems.MARK_49_NANO_TOOL.get());
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
						_player.inventoryMenu.getCraftSlots());
			}
		}
		if (!((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_50_CHESTPLATE.get())) {
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = new ItemStack(MarvelModItems.MARK_50_NANO_TOOL.get());
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
						_player.inventoryMenu.getCraftSlots());
			}
		}
		if (!((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_85_CHESTPLATE.get())) {
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = new ItemStack(MarvelModItems.MARK_85_NANO_TOOL.get());
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
						_player.inventoryMenu.getCraftSlots());
			}
		}
		if (!((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_17_CHESTPLATE.get())) {
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = new ItemStack(MarvelModItems.MARK_17_UNIBEAM.get());
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
						_player.inventoryMenu.getCraftSlots());
			}
		}
	}
}
