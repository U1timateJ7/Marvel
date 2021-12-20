package com.ulto.marvel.procedures;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import java.util.Collections;

import com.ulto.marvel.network.MarvelModVariables;
import com.ulto.marvel.init.MarvelModItems;

public class Mark47DisconnectProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MarvelModVariables.PlayerVariables())).controllingMark47 == true) {
			Mark47SentryModeProcedure.execute(world, x, y, z, entity);
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).hotbar0);
				final int _sltid = 0;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).hotbar0)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).hotbar1);
				final int _sltid = 1;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).hotbar1)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).hotbar2);
				final int _sltid = 2;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).hotbar2)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).hotbar3);
				final int _sltid = 3;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).hotbar3)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).hotbar4);
				final int _sltid = 4;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).hotbar4)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).hotbar5);
				final int _sltid = 5;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).hotbar5)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).hotbar6);
				final int _sltid = 6;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).hotbar6)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).hotbar7);
				final int _sltid = 7;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).hotbar7)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).hotbar8);
				final int _sltid = 8;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).hotbar8)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory0);
				final int _sltid = 9;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory0)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory1);
				final int _sltid = 10;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory1)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory2);
				final int _sltid = 11;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory2)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory3);
				final int _sltid = 12;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory3)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory4);
				final int _sltid = 13;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory4)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory5);
				final int _sltid = 14;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory5)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory6);
				final int _sltid = 15;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory6)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory7);
				final int _sltid = 16;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory7)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory8);
				final int _sltid = 17;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory8)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory9);
				final int _sltid = 18;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory9)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory10);
				final int _sltid = 19;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory10)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory11);
				final int _sltid = 20;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory11)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory12);
				final int _sltid = 21;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory12)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory13);
				final int _sltid = 22;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory13)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory14);
				final int _sltid = 23;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory14)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory15);
				final int _sltid = 24;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory15)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory16);
				final int _sltid = 25;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory16)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory17);
				final int _sltid = 26;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory17)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory18);
				final int _sltid = 27;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory18)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory19);
				final int _sltid = 28;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory19)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory20);
				final int _sltid = 29;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory20)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory21);
				final int _sltid = 30;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory21)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory22);
				final int _sltid = 31;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory22)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory23);
				final int _sltid = 32;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory23)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory24);
				final int _sltid = 33;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory24)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory25);
				final int _sltid = 34;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory25)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			{
				final ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory26);
				final int _sltid = 35;
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).inventory26)).getCount());
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable _modHandler)
						_modHandler.setStackInSlot(_sltid, _setstack);
				});
			}
			if (entity instanceof LivingEntity _entity) {
				ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).offhand0);
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).offhand0)).getCount());
				_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
				if (_entity instanceof ServerPlayer _serverPlayer)
					_serverPlayer.getInventory().setChanged();
			}
			if (entity instanceof Player _player) {
				ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).armor0);
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).armor0)).getCount());
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			if (entity instanceof Player _player) {
				ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).armor1);
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).armor1)).getCount());
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			if (entity instanceof Player _player) {
				ItemStack _setstack = ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).armor2);
				_setstack.setCount((((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).armor2)).getCount());
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			if (entity instanceof LivingEntity _entity) {
				if (_entity instanceof Player _player)
					_player.getInventory().armor.set(3, new ItemStack(MarvelModItems.MARK_47_GLASSES_HELMET));
				else
					_entity.setItemSlot(EquipmentSlot.HEAD, new ItemStack(MarvelModItems.MARK_47_GLASSES_HELMET));
				if (_entity instanceof ServerPlayer _serverPlayer)
					_serverPlayer.getInventory().setChanged();
			}
			if (entity instanceof LivingEntity _entity)
				_entity.setHealth((float) (entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).health);
			{
				Entity _ent = entity;
				_ent.teleportTo(
						((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).posX),
						((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).posY),
						((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).posZ));
				if (_ent instanceof ServerPlayer _serverPlayer) {
					_serverPlayer.connection.teleport(
							((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new MarvelModVariables.PlayerVariables())).posX),
							((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new MarvelModVariables.PlayerVariables())).posY),
							((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new MarvelModVariables.PlayerVariables())).posZ),
							_ent.getYRot(), _ent.getXRot(), Collections.emptySet());
				}
			}
			{
				Entity _ent = entity;
				_ent.setYRot((float) (entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).yaw);
				_ent.setXRot((float) (entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).pitch);
				_ent.setYBodyRot(_ent.getYRot());
				_ent.setYHeadRot(_ent.getYRot());
				_ent.yRotO = _ent.getYRot();
				_ent.xRotO = _ent.getXRot();
				if (_ent instanceof LivingEntity _entity) {
					_entity.yBodyRotO = _entity.getYRot();
					_entity.yHeadRotO = _entity.getYRot();
				}
			}
			if (entity instanceof Player _player)
				_player.getFoodData().setFoodLevel((int) (entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).hunger);
			if (entity instanceof Player _player)
				_player.getFoodData().setSaturation((float) (entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).saturation);
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performCommand(
						new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", new TextComponent(""),
								_level.getServer(), null).withSuppressedOutput(),
						("xp set " + entity.getStringUUID() + " " + (entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).xpLevels + " levels"));
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
