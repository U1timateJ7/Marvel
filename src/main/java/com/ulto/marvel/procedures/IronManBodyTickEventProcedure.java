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

import com.ulto.marvel.item.WarMachineGunItem;
import com.ulto.marvel.item.UnibeamItem;
import com.ulto.marvel.item.RepulsorItem;
import com.ulto.marvel.item.Mark25DrillItem;
import com.ulto.marvel.item.CenturionBladeItem;
import com.ulto.marvel.MarvelModVariables;
import com.ulto.marvel.MarvelModElements;
import com.ulto.marvel.MarvelMod;

@MarvelModElements.ModElement.Tag
public class IronManBodyTickEventProcedure extends MarvelModElements.ModElement {
	public IronManBodyTickEventProcedure(MarvelModElements instance) {
		super(instance, 189);
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
		double count = 0;
		double count2 = 0;
		double count3 = 0;
		double count4 = 0;
		double count5 = 0;
		boolean bl = false;
		boolean hasRepulsor = false;
		boolean bl2 = false;
		boolean hasUnibeam = false;
		boolean hasMinigun = false;
		boolean bl3 = false;
		boolean bl4 = false;
		boolean hasDrill = false;
		boolean bl5 = false;
		boolean hasBlade = false;
		if ((entity.isAlive())) {
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
			if (((bl2) == (true))) {
				hasUnibeam = (boolean) (true);
			}
			if (((hasUnibeam) == (false))) {
				if (entity instanceof PlayerEntity) {
					ItemStack _setstack = new ItemStack(UnibeamItem.block, (int) (1));
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
				}
			}
			if (((hasUnibeam) == (true))) {
				if (((count2) > 1)) {
					if (entity instanceof PlayerEntity) {
						ItemStack _stktoremove = new ItemStack(UnibeamItem.block, (int) (1));
						((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) ((count2) - 1),
								((PlayerEntity) entity).container.func_234641_j_());
					}
				}
			}
			if (((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new MarvelModVariables.PlayerVariables())).ironManMkNum) == (-1))
					|| ((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new MarvelModVariables.PlayerVariables())).ironManMkNum) == (-2))
							|| (((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new MarvelModVariables.PlayerVariables())).ironManMkNum) == (-3))))) {
				hasMinigun = (boolean) (false);
				{
					AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
					entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
							.ifPresent(capability -> _iitemhandlerref.set(capability));
					if (_iitemhandlerref.get() != null) {
						for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
							ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
							if (((hasMinigun) == (false))) {
								if (((itemstackiterator).getItem() == new ItemStack(WarMachineGunItem.block, (int) (1)).getItem())) {
									bl3 = (boolean) (true);
									count3 = (double) ((count3) + 1);
								}
							}
						}
					}
				}
				if (((bl3) == (true))) {
					hasMinigun = (boolean) (true);
				}
				if (((hasMinigun) == (false))) {
					if (entity instanceof PlayerEntity) {
						ItemStack _setstack = new ItemStack(WarMachineGunItem.block, (int) (1));
						_setstack.setCount((int) 1);
						ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
					}
				}
				if (((hasMinigun) == (true))) {
					if (((count3) > 1)) {
						if (entity instanceof PlayerEntity) {
							ItemStack _stktoremove = new ItemStack(WarMachineGunItem.block, (int) (1));
							((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) ((count3) - 1),
									((PlayerEntity) entity).container.func_234641_j_());
						}
					}
				}
			}
			if ((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new MarvelModVariables.PlayerVariables())).ironManMkNum) == 25)) {
				hasDrill = (boolean) (false);
				{
					AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
					entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
							.ifPresent(capability -> _iitemhandlerref.set(capability));
					if (_iitemhandlerref.get() != null) {
						for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
							ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
							if (((hasDrill) == (false))) {
								if (((itemstackiterator).getItem() == new ItemStack(Mark25DrillItem.block, (int) (1)).getItem())) {
									bl4 = (boolean) (true);
									count4 = (double) ((count4) + 1);
								}
							}
						}
					}
				}
				if (((bl4) == (true))) {
					hasDrill = (boolean) (true);
				}
				if (((hasDrill) == (false))) {
					if (entity instanceof PlayerEntity) {
						ItemStack _setstack = new ItemStack(Mark25DrillItem.block, (int) (1));
						_setstack.setCount((int) 1);
						ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
					}
				}
				if (((hasDrill) == (true))) {
					if (((count4) > 1)) {
						if (entity instanceof PlayerEntity) {
							ItemStack _stktoremove = new ItemStack(Mark25DrillItem.block, (int) (1));
							((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) ((count4) - 1),
									((PlayerEntity) entity).container.func_234641_j_());
						}
					}
				}
			}
			if ((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new MarvelModVariables.PlayerVariables())).ironManMkNum) == 33)) {
				hasBlade = (boolean) (false);
				{
					AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
					entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
							.ifPresent(capability -> _iitemhandlerref.set(capability));
					if (_iitemhandlerref.get() != null) {
						for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
							ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
							if (((hasBlade) == (false))) {
								if (((itemstackiterator).getItem() == new ItemStack(CenturionBladeItem.block, (int) (1)).getItem())) {
									bl5 = (boolean) (true);
									count5 = (double) ((count5) + 1);
								}
							}
						}
					}
				}
				if (((bl5) == (true))) {
					hasBlade = (boolean) (true);
				}
				if (((hasBlade) == (false))) {
					if (entity instanceof PlayerEntity) {
						ItemStack _setstack = new ItemStack(CenturionBladeItem.block, (int) (1));
						_setstack.setCount((int) 1);
						ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
					}
				}
				if (((hasBlade) == (true))) {
					if (((count5) > 1)) {
						if (entity instanceof PlayerEntity) {
							ItemStack _stktoremove = new ItemStack(CenturionBladeItem.block, (int) (1));
							((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) ((count5) - 1),
									((PlayerEntity) entity).container.func_234641_j_());
						}
					}
				}
			}
		}
	}
}
