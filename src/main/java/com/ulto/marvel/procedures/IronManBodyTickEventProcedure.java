package com.ulto.marvel.procedures;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.IWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.concurrent.atomic.AtomicReference;
import java.util.Map;

import com.ulto.marvel.item.UnibeamItem;
import com.ulto.marvel.item.RepulsorItem;
import com.ulto.marvel.MarvelModElements;
import com.ulto.marvel.MarvelMod;

@MarvelModElements.ModElement.Tag
public class IronManBodyTickEventProcedure extends MarvelModElements.ModElement {
	public IronManBodyTickEventProcedure(MarvelModElements instance) {
		super(instance, 168);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MarvelMod.LOGGER.warn("Failed to load dependency entity for procedure IronManBodyTickEvent!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MarvelMod.LOGGER.warn("Failed to load dependency world for procedure IronManBodyTickEvent!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		boolean bl = false;
		boolean hasRepulsor = false;
		boolean bl2 = false;
		boolean hasUnibeam = false;
		double count = 0;
		double count2 = 0;
		hasRepulsor = (boolean) (false);
		{
			AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _iitemhandlerref.set(capability));
			if (_iitemhandlerref.get() != null) {
				for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
					ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
					if (((hasRepulsor) == (false))) {
						if (((itemstackiterator).getItem() == new ItemStack(RepulsorItem.block, (int) (1)).getItem())) {
							bl = (boolean) (true);
							count = (double) ((count) + 1);
						}
					}
				}
			}
		}
		if (((bl) == (true))) {
			hasRepulsor = (boolean) (true);
		}
		if (((hasRepulsor) == (false))) {
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(RepulsorItem.block, (int) (1));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
		}
		if (((hasRepulsor) == (true))) {
			if (((count) > 1)) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(RepulsorItem.block, (int) (1));
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) ((count) - 1),
							((PlayerEntity) entity).container.func_234641_j_());
				}
			}
		}
		hasUnibeam = (boolean) (false);
		{
			AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _iitemhandlerref.set(capability));
			if (_iitemhandlerref.get() != null) {
				for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
					ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
					if (((hasUnibeam) == (false))) {
						if (((itemstackiterator).getItem() == new ItemStack(UnibeamItem.block, (int) (1)).getItem())) {
							bl2 = (boolean) (true);
							count2 = (double) ((count2) + 1);
						}
					}
				}
			}
		}
		if (((bl) == (true))) {
			hasRepulsor = (boolean) (true);
		}
		if (((hasRepulsor) == (false))) {
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(UnibeamItem.block, (int) (1));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
		}
		if (((hasRepulsor) == (true))) {
			if (((count2) > 1)) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(UnibeamItem.block, (int) (1));
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) ((count2) - 1),
							((PlayerEntity) entity).container.func_234641_j_());
				}
			}
		}
	}
}
