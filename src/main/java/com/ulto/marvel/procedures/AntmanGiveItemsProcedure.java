package com.ulto.marvel.procedures;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;

import java.util.concurrent.atomic.AtomicReference;

import com.ulto.marvel.init.MarvelModItems;

public class AntmanGiveItemsProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double count = 0;
		double count2 = 0;
		boolean bl = false;
		boolean hasShrink = false;
		boolean bl2 = false;
		boolean hasGrow = false;
		hasShrink = false;
		{
			AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _iitemhandlerref.set(capability));
			if (_iitemhandlerref.get() != null) {
				for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
					ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
					if (hasShrink == false) {
						if (itemstackiterator.getItem() == MarvelModItems.SHRINK_ITEM) {
							bl = true;
							count = count + 1;
						}
					}
				}
			}
		}
		if (bl == true) {
			hasShrink = true;
		}
		if (hasShrink == false) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.SHRINK_ITEM);
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (hasShrink == true) {
			if (count > 1) {
				if (entity instanceof Player _player) {
					ItemStack _stktoremove = new ItemStack(MarvelModItems.SHRINK_ITEM);
					_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) (count - 1),
							_player.inventoryMenu.getCraftSlots());
				}
			}
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.ANTMAN_V_2_SUIT_CHESTPLATE) {
			hasGrow = false;
			{
				AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _iitemhandlerref.set(capability));
				if (_iitemhandlerref.get() != null) {
					for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
						ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
						if (hasGrow == false) {
							if (itemstackiterator.getItem() == MarvelModItems.GROW_ITEM) {
								bl2 = true;
								count2 = count2 + 1;
							}
						}
					}
				}
			}
			if (bl2 == true) {
				hasGrow = true;
			}
			if (hasGrow == false) {
				if (entity instanceof Player _player) {
					ItemStack _setstack = new ItemStack(MarvelModItems.GROW_ITEM);
					_setstack.setCount(1);
					ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
				}
			}
			if (hasGrow == true) {
				if (count2 > 1) {
					if (entity instanceof Player _player) {
						ItemStack _stktoremove = new ItemStack(MarvelModItems.GROW_ITEM);
						_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) (count2 - 1),
								_player.inventoryMenu.getCraftSlots());
					}
				}
			}
		}
	}
}
