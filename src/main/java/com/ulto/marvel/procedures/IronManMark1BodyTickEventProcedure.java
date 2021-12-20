package com.ulto.marvel.procedures;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import java.util.concurrent.atomic.AtomicReference;

import com.ulto.marvel.init.MarvelModItems;

public class IronManMark1BodyTickEventProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double count = 0;
		boolean hasFlamethrower = false;
		boolean bl = false;
		hasFlamethrower = false;
		{
			AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _iitemhandlerref.set(capability));
			if (_iitemhandlerref.get() != null) {
				for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
					ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
					if (hasFlamethrower == false) {
						if (itemstackiterator.getItem() == MarvelModItems.FLAMETHROWER) {
							bl = true;
							count = count + 1;
						}
					}
				}
			}
		}
		if (bl == true) {
			hasFlamethrower = true;
		}
		if (hasFlamethrower == false) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.FLAMETHROWER);
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (hasFlamethrower == true) {
			if (count > 1) {
				if (entity instanceof Player _player) {
					ItemStack _stktoremove = new ItemStack(MarvelModItems.FLAMETHROWER);
					_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) (count - 1),
							_player.inventoryMenu.getCraftSlots());
				}
			}
		}
	}
}
