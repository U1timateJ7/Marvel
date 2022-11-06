package com.ulto.marvel.procedures;

import com.ulto.marvel.world.item.MarvelModItems;
import com.ulto.marvel.network.MarvelModVariables;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Collections;

public class Mark47DisconnectProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (MarvelModVariables.getPlayerVariables(entity).controllingMark47) {
			SentryModeProcedure.execute(world, x, y, z, entity, "iron_man_mark_47");
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).hotbar0);
				final int _sltid = 0;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).hotbar0)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).hotbar1);
				final int _sltid = 1;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).hotbar1)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).hotbar2);
				final int _sltid = 2;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).hotbar2)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).hotbar3);
				final int _sltid = 3;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).hotbar3)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).hotbar4);
				final int _sltid = 4;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).hotbar4)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).hotbar5);
				final int _sltid = 5;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).hotbar5)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).hotbar6);
				final int _sltid = 6;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).hotbar6)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).hotbar7);
				final int _sltid = 7;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).hotbar7)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).hotbar8);
				final int _sltid = 8;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).hotbar8)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).inventory0);
				final int _sltid = 9;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).inventory0)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).inventory1);
				final int _sltid = 10;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).inventory1)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).inventory2);
				final int _sltid = 11;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).inventory2)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).inventory3);
				final int _sltid = 12;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).inventory3)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).inventory4);
				final int _sltid = 13;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).inventory4)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).inventory5);
				final int _sltid = 14;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).inventory5)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).inventory6);
				final int _sltid = 15;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).inventory6)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).inventory7);
				final int _sltid = 16;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).inventory7)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).inventory8);
				final int _sltid = 17;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).inventory8)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).inventory9);
				final int _sltid = 18;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).inventory9)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).inventory10);
				final int _sltid = 19;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).inventory10)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).inventory11);
				final int _sltid = 20;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).inventory11)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).inventory12);
				final int _sltid = 21;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).inventory12)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).inventory13);
				final int _sltid = 22;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).inventory13)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).inventory14);
				final int _sltid = 23;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).inventory14)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).inventory15);
				final int _sltid = 24;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).inventory15)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).inventory16);
				final int _sltid = 25;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).inventory16)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).inventory17);
				final int _sltid = 26;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).inventory17)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).inventory18);
				final int _sltid = 27;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).inventory18)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).inventory19);
				final int _sltid = 28;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).inventory19)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).inventory20);
				final int _sltid = 29;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).inventory20)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).inventory21);
				final int _sltid = 30;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).inventory21)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).inventory22);
				final int _sltid = 31;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).inventory22)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).inventory23);
				final int _sltid = 32;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).inventory23)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).inventory24);
				final int _sltid = 33;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).inventory24)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).inventory25);
				final int _sltid = 34;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).inventory25)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).inventory26);
				final int _sltid = 35;
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).inventory26)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			if (entity instanceof LivingEntity _entity) {
				ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).offhand0);
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).offhand0)).getCount());
				_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
				if (_entity instanceof ServerPlayer _serverPlayer)
					_serverPlayer.getInventory().setChanged();
			}
			if (entity instanceof Player _player) {
				ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).armor0);
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).armor0)).getCount());
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			if (entity instanceof Player _player) {
				ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).armor1);
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).armor1)).getCount());
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			if (entity instanceof Player _player) {
				ItemStack _setstack = (MarvelModVariables.getPlayerVariables(entity).armor2);
				_setstack.setCount(((MarvelModVariables.getPlayerVariables(entity).armor2)).getCount());
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			if (entity instanceof LivingEntity _entity) {
				if (_entity instanceof Player _player)
					_player.getInventory().armor.set(3, new ItemStack(MarvelModItems.MARK_47_GLASSES.get()));
				else
					_entity.setItemSlot(EquipmentSlot.HEAD, new ItemStack(MarvelModItems.MARK_47_GLASSES.get()));
				if (_entity instanceof ServerPlayer _serverPlayer)
					_serverPlayer.getInventory().setChanged();
			}
			if (entity instanceof LivingEntity _entity)
				_entity.setHealth((float) MarvelModVariables.getPlayerVariables(entity).health);
			{
				entity.teleportTo(
						(MarvelModVariables.getPlayerVariables(entity).posX),
						(MarvelModVariables.getPlayerVariables(entity).posY),
						(MarvelModVariables.getPlayerVariables(entity).posZ));
				if (entity instanceof ServerPlayer _serverPlayer) {
					_serverPlayer.connection.teleport(
							(MarvelModVariables.getPlayerVariables(entity).posX),
							(MarvelModVariables.getPlayerVariables(entity).posY),
							(MarvelModVariables.getPlayerVariables(entity).posZ),
							entity.getYRot(), entity.getXRot(), Collections.emptySet());
				}
			}
			{
				entity.setYRot((float) MarvelModVariables.getPlayerVariables(entity).yaw);
				entity.setXRot((float) MarvelModVariables.getPlayerVariables(entity).pitch);
				entity.setYBodyRot(entity.getYRot());
				entity.setYHeadRot(entity.getYRot());
				entity.yRotO = entity.getYRot();
				entity.xRotO = entity.getXRot();
				if (entity instanceof LivingEntity _entity) {
					_entity.yBodyRotO = _entity.getYRot();
					_entity.yHeadRotO = _entity.getYRot();
				}
			}
			if (entity instanceof Player _player) {
				_player.getFoodData().setFoodLevel((int) MarvelModVariables.getPlayerVariables(entity).hunger);
				_player.getFoodData().setSaturation((float) MarvelModVariables.getPlayerVariables(entity).saturation);
				_player.experienceProgress = (int) MarvelModVariables.getPlayerVariables(entity).xpProgress;
				_player.experienceLevel = (int) MarvelModVariables.getPlayerVariables(entity).xpLevels;
			}
			entity.setDeltaMovement(0, 0, 0);
			{
				boolean _setval = false;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.controllingMark47 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
