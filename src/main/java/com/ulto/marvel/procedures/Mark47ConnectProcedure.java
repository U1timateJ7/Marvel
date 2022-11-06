package com.ulto.marvel.procedures;

import com.ulto.marvel.world.entity.SentryModeEntity;
import com.ulto.marvel.world.item.MarvelModItems;
import com.ulto.marvel.network.MarvelModVariables;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.items.CapabilityItemHandler;

import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicReference;

@SuppressWarnings("RedundantCast")
public class Mark47ConnectProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (MarvelModVariables.getPlayerVariables(entity).mark47Ready) {
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(9, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inventory0 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(10, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inventory1 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(11, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inventory2 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(12, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inventory3 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(13, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inventory4 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(14, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inventory5 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(15, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inventory6 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(16, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inventory7 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(17, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inventory8 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(18, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inventory9 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(19, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inventory10 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(20, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inventory11 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(21, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inventory12 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(22, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inventory13 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(23, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inventory14 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(24, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inventory15 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(25, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inventory16 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(26, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inventory17 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(27, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inventory18 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(28, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inventory19 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(29, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inventory20 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(30, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inventory21 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(31, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inventory22 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(32, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inventory23 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(33, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inventory24 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(34, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inventory25 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(35, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.inventory26 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(0, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.hotbar0 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(1, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.hotbar1 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(2, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.hotbar2 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(3, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.hotbar3 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(4, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.hotbar4 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(5, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.hotbar5 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(6, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.hotbar6 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(7, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.hotbar7 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
						return _retval.get();
					}
				}.getItemStack(8, entity));
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.hotbar8 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY);
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.offhand0 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY);
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.armor0 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY);
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.armor1 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY);
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.armor2 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				ItemStack _setval = (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY);
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.armor3 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = entity.getX();
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.posX = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = entity.getY();
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.posY = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = entity.getZ();
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.posZ = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = entity.getYRot();
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.yaw = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = entity.getXRot();
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.pitch = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.health = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = entity instanceof Player _plr ? _plr.getFoodData().getFoodLevel() : 0;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.hunger = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = entity instanceof Player _plr ? _plr.getFoodData().getSaturationLevel() : 0;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.saturation = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = entity instanceof Player _plr ? _plr.experienceProgress : 0;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.xpProgress = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = entity instanceof Player _plr ? _plr.experienceLevel : 0;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.xpLevels = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (entity instanceof LivingEntity _entity)
				_entity.setHealth(entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1);
			{
				entity.teleportTo(x, 255, z);
				if (entity instanceof ServerPlayer _serverPlayer) {
					_serverPlayer.connection.teleport(x, 255, z, entity.getYRot(), entity.getXRot(), Collections.emptySet());
				}
			}
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4,
						"", new TextComponent(""), _level.getServer(), null).withSuppressedOutput(),
						("clear " + entity.getDisplayName().getString()));
			if (entity instanceof LivingEntity _entity) {
				if (_entity instanceof Player _player)
					_player.getInventory().armor.set(3, new ItemStack(MarvelModItems.IRON_MAN_MARK_47_HELMET.get()));
				else
					_entity.setItemSlot(EquipmentSlot.HEAD, new ItemStack(MarvelModItems.IRON_MAN_MARK_47_HELMET.get()));
				if (_entity instanceof ServerPlayer _serverPlayer)
					_serverPlayer.getInventory().setChanged();
			}
			if (entity instanceof LivingEntity _entity) {
				if (_entity instanceof Player _player)
					_player.getInventory().armor.set(2, new ItemStack(MarvelModItems.IRON_MAN_MARK_47_CHESTPLATE.get()));
				else
					_entity.setItemSlot(EquipmentSlot.CHEST, new ItemStack(MarvelModItems.IRON_MAN_MARK_47_CHESTPLATE.get()));
				if (_entity instanceof ServerPlayer _serverPlayer)
					_serverPlayer.getInventory().setChanged();
			}
			if (entity instanceof LivingEntity _entity) {
				if (_entity instanceof Player _player)
					_player.getInventory().armor.set(1, new ItemStack(MarvelModItems.IRON_MAN_MARK_47_LEGGINGS.get()));
				else
					_entity.setItemSlot(EquipmentSlot.LEGS, new ItemStack(MarvelModItems.IRON_MAN_MARK_47_LEGGINGS.get()));
				if (_entity instanceof ServerPlayer _serverPlayer)
					_serverPlayer.getInventory().setChanged();
			}
			if (entity instanceof LivingEntity _entity) {
				if (_entity instanceof Player _player)
					_player.getInventory().armor.set(0, new ItemStack(MarvelModItems.IRON_MAN_MARK_47_BOOTS.get()));
				else
					_entity.setItemSlot(EquipmentSlot.FEET, new ItemStack(MarvelModItems.IRON_MAN_MARK_47_BOOTS.get()));
				if (_entity instanceof ServerPlayer _serverPlayer)
					_serverPlayer.getInventory().setChanged();
			}
			{
				boolean _setval = false;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.mark47Ready = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				boolean _setval = true;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.controllingMark47 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performCommand(
						new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", new TextComponent(""),
								_level.getServer(), null).withSuppressedOutput(),
						("tellraw " + entity.getDisplayName().getString() + " {\"translate\":\"iron_man.mark_47.remote.success\"}"));
		} else if (!(((Entity) world.getEntitiesOfClass(SentryModeEntity.class, AABB.ofSize(new Vec3(x, y, z), 400, 400, 400), e -> true).stream().min(new Object() {
			Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
				return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
			}
		}.compareDistOf(x, y, z)).orElse(null)) == (null))) {
			if ((((Entity) world.getEntitiesOfClass(SentryModeEntity.class, AABB.ofSize(new Vec3(x, y, z), 400, 400, 400), e -> true).stream().min(new Object() {
				Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
					return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
				}
			}.compareDistOf(x, y, z)).orElse(null))instanceof LivingEntity _entGetArmor
							? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD)
							: ItemStack.EMPTY).getItem() == MarvelModItems.IRON_MAN_MARK_47_HELMET.get()
					&& (((Entity) world.getEntitiesOfClass(SentryModeEntity.class, AABB.ofSize(new Vec3(x, y, z), 400, 400, 400), e -> true).stream().min(new Object() {
				Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
					return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
				}
			}.compareDistOf(x, y, z)).orElse(null))instanceof LivingEntity _entGetArmor
									? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST)
									: ItemStack.EMPTY).getItem() == MarvelModItems.IRON_MAN_MARK_47_CHESTPLATE.get()
					&& (((Entity) world.getEntitiesOfClass(SentryModeEntity.class, AABB.ofSize(new Vec3(x, y, z), 400, 400, 400), e -> true).stream().min(new Object() {
				Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
					return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
				}
			}.compareDistOf(x, y, z)).orElse(null))instanceof LivingEntity _entGetArmor
									? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS)
									: ItemStack.EMPTY).getItem() == MarvelModItems.IRON_MAN_MARK_47_LEGGINGS.get()
					&& (((Entity) world.getEntitiesOfClass(SentryModeEntity.class, AABB.ofSize(new Vec3(x, y, z), 400, 400, 400), e -> true).stream().min(new Object() {
				Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
					return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
				}
			}.compareDistOf(x, y, z)).orElse(null))instanceof LivingEntity _entGetArmor
									? _entGetArmor.getItemBySlot(EquipmentSlot.FEET)
									: ItemStack.EMPTY).getItem() == MarvelModItems.IRON_MAN_MARK_47_BOOTS.get()) {
				if ((((Entity) world.getEntitiesOfClass(SentryModeEntity.class, AABB.ofSize(new Vec3(x, y, z), 400, 400, 400), e -> true).stream().min(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).orElse(null))instanceof TamableAnimal _tamEnt
								? (Entity) _tamEnt.getOwner()
								: null) == entity) {
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(9, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.inventory0 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(10, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.inventory1 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(11, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.inventory2 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(12, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.inventory3 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(13, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.inventory4 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(14, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.inventory5 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(15, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.inventory6 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(16, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.inventory7 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(17, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.inventory8 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(18, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.inventory9 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(19, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.inventory10 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(20, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.inventory11 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(21, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.inventory12 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(22, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.inventory13 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(23, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.inventory14 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(24, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.inventory15 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(25, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.inventory16 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(26, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.inventory17 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(27, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.inventory18 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(28, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.inventory19 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(29, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.inventory20 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(30, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.inventory21 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(31, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.inventory22 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(32, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.inventory23 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(33, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.inventory24 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(34, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.inventory25 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(35, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.inventory26 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(0, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.hotbar0 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(1, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.hotbar1 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(2, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.hotbar2 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(3, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.hotbar3 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(4, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.hotbar4 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(5, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.hotbar5 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(6, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.hotbar6 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(7, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.hotbar7 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(sltid).copy()));
								return _retval.get();
							}
						}.getItemStack(8, entity));
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.hotbar8 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY);
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.offhand0 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (entity instanceof LivingEntity _entGetArmor
								? _entGetArmor.getItemBySlot(EquipmentSlot.FEET)
								: ItemStack.EMPTY);
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.armor0 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (entity instanceof LivingEntity _entGetArmor
								? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS)
								: ItemStack.EMPTY);
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.armor1 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (entity instanceof LivingEntity _entGetArmor
								? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST)
								: ItemStack.EMPTY);
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.armor2 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						ItemStack _setval = (entity instanceof LivingEntity _entGetArmor
								? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD)
								: ItemStack.EMPTY);
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.armor3 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						double _setval = entity.getX();
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.posX = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						double _setval = entity.getY();
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.posY = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						double _setval = entity.getZ();
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.posZ = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						double _setval = entity.getYRot();
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.yaw = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						double _setval = entity.getXRot();
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.pitch = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						double _setval = entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1;
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.health = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						double _setval = entity instanceof Player _plr ? _plr.getFoodData().getFoodLevel() : 0;
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.hunger = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						double _setval = entity instanceof Player _plr ? _plr.getFoodData().getSaturationLevel() : 0;
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.saturation = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						double _setval = entity instanceof Player _plr ? _plr.experienceLevel : 0;
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.xpLevels = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					if (entity instanceof LivingEntity _entity)
						_entity.setHealth(entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1);
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands()
								.performCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "",
										new TextComponent(""), _level.getServer(), null).withSuppressedOutput(),
										("clear " + entity.getDisplayName().getString()));
					if (entity instanceof LivingEntity _entity) {
						if (_entity instanceof Player _player)
							_player.getInventory().armor.set(3, new ItemStack(MarvelModItems.IRON_MAN_MARK_47_HELMET.get()));
						else
							_entity.setItemSlot(EquipmentSlot.HEAD, new ItemStack(MarvelModItems.IRON_MAN_MARK_47_HELMET.get()));
						if (_entity instanceof ServerPlayer _serverPlayer)
							_serverPlayer.getInventory().setChanged();
					}
					if (entity instanceof LivingEntity _entity) {
						if (_entity instanceof Player _player)
							_player.getInventory().armor.set(2, new ItemStack(MarvelModItems.IRON_MAN_MARK_47_CHESTPLATE.get()));
						else
							_entity.setItemSlot(EquipmentSlot.CHEST, new ItemStack(MarvelModItems.IRON_MAN_MARK_47_CHESTPLATE.get()));
						if (_entity instanceof ServerPlayer _serverPlayer)
							_serverPlayer.getInventory().setChanged();
					}
					if (entity instanceof LivingEntity _entity) {
						if (_entity instanceof Player _player)
							_player.getInventory().armor.set(1, new ItemStack(MarvelModItems.IRON_MAN_MARK_47_LEGGINGS.get()));
						else
							_entity.setItemSlot(EquipmentSlot.LEGS, new ItemStack(MarvelModItems.IRON_MAN_MARK_47_LEGGINGS.get()));
						if (_entity instanceof ServerPlayer _serverPlayer)
							_serverPlayer.getInventory().setChanged();
					}
					if (entity instanceof LivingEntity _entity) {
						if (_entity instanceof Player _player)
							_player.getInventory().armor.set(0, new ItemStack(MarvelModItems.IRON_MAN_MARK_47_BOOTS.get()));
						else
							_entity.setItemSlot(EquipmentSlot.FEET, new ItemStack(MarvelModItems.IRON_MAN_MARK_47_BOOTS.get()));
						if (_entity instanceof ServerPlayer _serverPlayer)
							_serverPlayer.getInventory().setChanged();
					}
					{
						entity.teleportTo(
								(((Entity) world.getEntitiesOfClass(SentryModeEntity.class, AABB.ofSize(new Vec3(x, y, z), 400, 400, 400), e -> true)
										.stream().min(new Object() {
											Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
												return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
											}
										}.compareDistOf(x, y, z)).orElse(null)).getX()),
								(((Entity) world.getEntitiesOfClass(SentryModeEntity.class, AABB.ofSize(new Vec3(x, y, z), 400, 400, 400), e -> true)
										.stream().min(new Object() {
											Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
												return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
											}
										}.compareDistOf(x, y, z)).orElse(null)).getY()),
								(((Entity) world.getEntitiesOfClass(SentryModeEntity.class, AABB.ofSize(new Vec3(x, y, z), 400, 400, 400), e -> true)
										.stream().min(new Object() {
											Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
												return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
											}
										}.compareDistOf(x, y, z)).orElse(null)).getZ()));
						if (entity instanceof ServerPlayer _serverPlayer) {
							_serverPlayer.connection.teleport((((Entity) world
											.getEntitiesOfClass(SentryModeEntity.class, AABB.ofSize(new Vec3(x, y, z), 400, 400, 400), e -> true).stream().min(new Object() {
												Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
													return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
												}
											}.compareDistOf(x, y, z)).orElse(null)).getX()),
									(((Entity) world
											.getEntitiesOfClass(SentryModeEntity.class, AABB.ofSize(new Vec3(x, y, z), 400, 400, 400), e -> true)
											.stream().min(new Object() {
												Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
													return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
												}
											}.compareDistOf(x, y, z)).orElse(null)).getY()),
									(((Entity) world
											.getEntitiesOfClass(SentryModeEntity.class, AABB.ofSize(new Vec3(x, y, z), 400, 400, 400), e -> true)
											.stream().min(new Object() {
												Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
													return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
												}
											}.compareDistOf(x, y, z)).orElse(null)).getZ()),
									entity.getYRot(), entity.getXRot(), Collections.emptySet());
						}
					}
					{
						entity.setYRot(
								((Entity) world.getEntitiesOfClass(SentryModeEntity.class, AABB.ofSize(new Vec3(x, y, z), 400, 400, 400), e -> true)
										.stream().min(new Object() {
											Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
												return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
											}
										}.compareDistOf(x, y, z)).orElse(null)).getYRot());
						entity.setXRot(
								((Entity) world.getEntitiesOfClass(SentryModeEntity.class, AABB.ofSize(new Vec3(x, y, z), 400, 400, 400), e -> true)
										.stream().min(new Object() {
											Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
												return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
											}
										}.compareDistOf(x, y, z)).orElse(null)).getXRot());
						entity.setYBodyRot(entity.getYRot());
						entity.setYHeadRot(entity.getYRot());
						entity.yRotO = entity.getYRot();
						entity.xRotO = entity.getXRot();
						if (entity instanceof LivingEntity _entity) {
							_entity.yBodyRotO = _entity.getYRot();
							_entity.yHeadRotO = _entity.getYRot();
						}
					}
					if (!((Entity) world.getEntitiesOfClass(SentryModeEntity.class, AABB.ofSize(new Vec3(x, y, z), 400, 400, 400), e -> true).stream().min(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).orElse(null)).level.isClientSide())
						((Entity) world.getEntitiesOfClass(SentryModeEntity.class, AABB.ofSize(new Vec3(x, y, z), 400, 400, 400), e -> true).stream().min(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf(x, y, z)).orElse(null)).discard();
					{
						boolean _setval = true;
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.controllingMark47 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performCommand(
								new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", new TextComponent(""),
										_level.getServer(), null).withSuppressedOutput(),
								("tellraw " + entity.getDisplayName().getString() + " {\"translate\":\"iron_man.mark_47.remote.success\"}"));
				} else {
					{
						boolean _setval = false;
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.controllingMark47 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performCommand(
								new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", new TextComponent(""),
										_level.getServer(), null).withSuppressedOutput(),
								("tellraw " + entity.getDisplayName().getString() + " {\"translate\":\"iron_man.mark_47.remote.fail\"}"));
				}
			} else {
				{
					boolean _setval = false;
					entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.controllingMark47 = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performCommand(
							new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", new TextComponent(""),
									_level.getServer(), null).withSuppressedOutput(),
							("tellraw " + entity.getDisplayName().getString() + " {\"translate\":\"iron_man.mark_47.remote.fail\"}"));
			}
		} else {
			{
				boolean _setval = false;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.controllingMark47 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performCommand(
						new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", new TextComponent(""),
								_level.getServer(), null).withSuppressedOutput(),
						("tellraw " + entity.getDisplayName().getString() + " {\"translate\":\"iron_man.mark_47.remote.fail\"}"));
		}
	}
}
