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

import com.ulto.marvel.item.IronManFlamethrowerItem;
import com.ulto.marvel.MarvelModElements;
import com.ulto.marvel.MarvelMod;

@MarvelModElements.ModElement.Tag
public class IronManMark1BodyTickEventProcedure extends MarvelModElements.ModElement {
	public IronManMark1BodyTickEventProcedure(MarvelModElements instance) {
		super(instance, 161);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MarvelMod.LOGGER.warn("Failed to load dependency entity for procedure IronManMark1BodyTickEvent!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MarvelMod.LOGGER.warn("Failed to load dependency world for procedure IronManMark1BodyTickEvent!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		double count = 0;
		boolean hasFlamethrower = false;
		boolean bl = false;
		hasFlamethrower = (boolean) (false);
		{
			AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _iitemhandlerref.set(capability));
			if (_iitemhandlerref.get() != null) {
				for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
					ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
					if (((hasFlamethrower) == (false))) {
						if (((itemstackiterator).getItem() == new ItemStack(IronManFlamethrowerItem.block, (int) (1)).getItem())) {
							bl = (boolean) (true);
							count = (double) ((count) + 1);
						}
					}
				}
			}
		}
		if (((bl) == (true))) {
			hasFlamethrower = (boolean) (true);
		}
		if (((hasFlamethrower) == (false))) {
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(IronManFlamethrowerItem.block, (int) (1));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
		}
		if (((hasFlamethrower) == (true))) {
			if (((count) > 1)) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(IronManFlamethrowerItem.block, (int) (1));
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) ((count) - 1),
							((PlayerEntity) entity).container.func_234641_j_());
				}
			}
		}
	}
}
