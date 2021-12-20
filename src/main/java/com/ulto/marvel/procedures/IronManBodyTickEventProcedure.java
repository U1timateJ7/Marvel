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

import com.ulto.marvel.network.MarvelModVariables;
import com.ulto.marvel.init.MarvelModItems;

public class IronManBodyTickEventProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double count = 0;
		double count2 = 0;
		double count3 = 0;
		double count4 = 0;
		double count5 = 0;
		double count6 = 0;
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
		boolean bl6 = false;
		boolean hasNanotool = false;
		if (entity.isAlive()) {
			hasRepulsor = false;
			{
				AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _iitemhandlerref.set(capability));
				if (_iitemhandlerref.get() != null) {
					for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
						ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
						if (hasRepulsor == false) {
							if (itemstackiterator.getItem() == MarvelModItems.REPULSOR) {
								bl = true;
								count = count + 1;
							}
						}
					}
				}
			}
			if (bl == true) {
				hasRepulsor = true;
			}
			if (hasRepulsor == false) {
				if (entity instanceof Player _player) {
					ItemStack _setstack = new ItemStack(MarvelModItems.REPULSOR);
					_setstack.setCount(1);
					ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
				}
			}
			if (hasRepulsor == true) {
				if (count > 1) {
					if (entity instanceof Player _player) {
						ItemStack _stktoremove = new ItemStack(MarvelModItems.REPULSOR);
						_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) (count - 1),
								_player.inventoryMenu.getCraftSlots());
					}
				}
			}
			if (!((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
					.getItem() == MarvelModItems.IRON_MAN_MARK_17_CHESTPLATE)) {
				hasUnibeam = false;
				{
					AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
					entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
							.ifPresent(capability -> _iitemhandlerref.set(capability));
					if (_iitemhandlerref.get() != null) {
						for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
							ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
							if (hasUnibeam == false) {
								if (itemstackiterator.getItem() == MarvelModItems.UNIBEAM) {
									bl2 = true;
									count2 = count2 + 1;
								}
							}
						}
					}
				}
				if (bl2 == true) {
					hasUnibeam = true;
				}
				if (hasUnibeam == false) {
					if (entity instanceof Player _player) {
						ItemStack _setstack = new ItemStack(MarvelModItems.UNIBEAM);
						_setstack.setCount(1);
						ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
					}
				}
				if (hasUnibeam == true) {
					if (count2 > 1) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = new ItemStack(MarvelModItems.UNIBEAM);
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) (count2 - 1),
									_player.inventoryMenu.getCraftSlots());
						}
					}
				}
			}
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
					.getItem() == MarvelModItems.IRON_MAN_MARK_17_CHESTPLATE) {
				hasUnibeam = false;
				{
					AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
					entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
							.ifPresent(capability -> _iitemhandlerref.set(capability));
					if (_iitemhandlerref.get() != null) {
						for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
							ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
							if (hasUnibeam == false) {
								if (itemstackiterator.getItem() == MarvelModItems.MARK_17_UNIBEAM) {
									bl2 = true;
									count2 = count2 + 1;
								}
							}
						}
					}
				}
				if (bl2 == true) {
					hasUnibeam = true;
				}
				if (hasUnibeam == false) {
					if (entity instanceof Player _player) {
						ItemStack _setstack = new ItemStack(MarvelModItems.MARK_17_UNIBEAM);
						_setstack.setCount(1);
						ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
					}
				}
				if (hasUnibeam == true) {
					if (count2 > 1) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = new ItemStack(MarvelModItems.MARK_17_UNIBEAM);
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) (count2 - 1),
									_player.inventoryMenu.getCraftSlots());
						}
					}
				}
			}
			if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new MarvelModVariables.PlayerVariables())).ironManMkNum == -1
					|| (entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new MarvelModVariables.PlayerVariables())).ironManMkNum == -2
					|| (entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new MarvelModVariables.PlayerVariables())).ironManMkNum == -3) {
				hasMinigun = false;
				{
					AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
					entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
							.ifPresent(capability -> _iitemhandlerref.set(capability));
					if (_iitemhandlerref.get() != null) {
						for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
							ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
							if (hasMinigun == false) {
								if (itemstackiterator.getItem() == MarvelModItems.WAR_MACHINE_GUN) {
									bl3 = true;
									count3 = count3 + 1;
								}
							}
						}
					}
				}
				if (bl3 == true) {
					hasMinigun = true;
				}
				if (hasMinigun == false) {
					if (entity instanceof Player _player) {
						ItemStack _setstack = new ItemStack(MarvelModItems.WAR_MACHINE_GUN);
						_setstack.setCount(1);
						ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
					}
				}
				if (hasMinigun == true) {
					if (count3 > 1) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = new ItemStack(MarvelModItems.WAR_MACHINE_GUN);
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) (count3 - 1),
									_player.inventoryMenu.getCraftSlots());
						}
					}
				}
			}
			if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new MarvelModVariables.PlayerVariables())).ironManMkNum == 25) {
				hasDrill = false;
				{
					AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
					entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
							.ifPresent(capability -> _iitemhandlerref.set(capability));
					if (_iitemhandlerref.get() != null) {
						for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
							ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
							if (hasDrill == false) {
								if (itemstackiterator.getItem() == MarvelModItems.MARK_25_DRILL) {
									bl4 = true;
									count4 = count4 + 1;
								}
							}
						}
					}
				}
				if (bl4 == true) {
					hasDrill = true;
				}
				if (hasDrill == false) {
					if (entity instanceof Player _player) {
						ItemStack _setstack = new ItemStack(MarvelModItems.MARK_25_DRILL);
						_setstack.setCount(1);
						ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
					}
				}
				if (hasDrill == true) {
					if (count4 > 1) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = new ItemStack(MarvelModItems.MARK_25_DRILL);
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) (count4 - 1),
									_player.inventoryMenu.getCraftSlots());
						}
					}
				}
			}
			if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new MarvelModVariables.PlayerVariables())).ironManMkNum == 30
					|| (entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new MarvelModVariables.PlayerVariables())).ironManMkNum == 33) {
				hasBlade = false;
				{
					AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
					entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
							.ifPresent(capability -> _iitemhandlerref.set(capability));
					if (_iitemhandlerref.get() != null) {
						for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
							ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
							if (hasBlade == false) {
								if (itemstackiterator.getItem() == MarvelModItems.CENTURION_BLADE) {
									bl5 = true;
									count5 = count5 + 1;
								}
							}
						}
					}
				}
				if (bl5 == true) {
					hasBlade = true;
				}
				if (hasBlade == false) {
					if (entity instanceof Player _player) {
						ItemStack _setstack = new ItemStack(MarvelModItems.CENTURION_BLADE);
						_setstack.setCount(1);
						ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
					}
				}
				if (hasBlade == true) {
					if (count5 > 1) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = new ItemStack(MarvelModItems.CENTURION_BLADE);
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) (count5 - 1),
									_player.inventoryMenu.getCraftSlots());
						}
					}
				}
			}
			if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new MarvelModVariables.PlayerVariables())).ironManMkNum == 49) {
				hasNanotool = false;
				{
					AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
					entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
							.ifPresent(capability -> _iitemhandlerref.set(capability));
					if (_iitemhandlerref.get() != null) {
						for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
							ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
							if (hasNanotool == false) {
								if (itemstackiterator.getItem() == MarvelModItems.MARK_49_NANO_TOOL) {
									bl6 = true;
									count6 = count6 + 1;
								}
							}
						}
					}
				}
				if (bl6 == true) {
					hasNanotool = true;
				}
				if (hasNanotool == false) {
					if (entity instanceof Player _player) {
						ItemStack _setstack = new ItemStack(MarvelModItems.MARK_49_NANO_TOOL);
						_setstack.setCount(1);
						ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
					}
				}
				if (hasNanotool == true) {
					if (count6 > 1) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = new ItemStack(MarvelModItems.MARK_49_NANO_TOOL);
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) (count6 - 1),
									_player.inventoryMenu.getCraftSlots());
						}
					}
				}
			}
			if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new MarvelModVariables.PlayerVariables())).ironManMkNum == 50) {
				hasNanotool = false;
				{
					AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
					entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
							.ifPresent(capability -> _iitemhandlerref.set(capability));
					if (_iitemhandlerref.get() != null) {
						for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
							ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
							if (hasNanotool == false) {
								if (itemstackiterator.getItem() == MarvelModItems.MARK_50_NANO_TOOL) {
									bl6 = true;
									count6 = count6 + 1;
								}
							}
						}
					}
				}
				if (bl6 == true) {
					hasNanotool = true;
				}
				if (hasNanotool == false) {
					if (entity instanceof Player _player) {
						ItemStack _setstack = new ItemStack(MarvelModItems.MARK_50_NANO_TOOL);
						_setstack.setCount(1);
						ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
					}
				}
				if (hasNanotool == true) {
					if (count6 > 1) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = new ItemStack(MarvelModItems.MARK_50_NANO_TOOL);
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) (count6 - 1),
									_player.inventoryMenu.getCraftSlots());
						}
					}
				}
			}
			if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new MarvelModVariables.PlayerVariables())).ironManMkNum == 85) {
				hasNanotool = false;
				{
					AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
					entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
							.ifPresent(capability -> _iitemhandlerref.set(capability));
					if (_iitemhandlerref.get() != null) {
						for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
							ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
							if (hasNanotool == false) {
								if (itemstackiterator.getItem() == MarvelModItems.MARK_85_NANO_TOOL) {
									bl6 = true;
									count6 = count6 + 1;
								}
							}
						}
					}
				}
				if (bl6 == true) {
					hasNanotool = true;
				}
				if (hasNanotool == false) {
					if (entity instanceof Player _player) {
						ItemStack _setstack = new ItemStack(MarvelModItems.MARK_85_NANO_TOOL);
						_setstack.setCount(1);
						ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
					}
				}
				if (hasNanotool == true) {
					if (count6 > 1) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = new ItemStack(MarvelModItems.MARK_85_NANO_TOOL);
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) (count6 - 1),
									_player.inventoryMenu.getCraftSlots());
						}
					}
				}
			}
		}
	}
}
