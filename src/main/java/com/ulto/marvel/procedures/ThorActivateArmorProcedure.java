package com.ulto.marvel.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import com.ulto.marvel.world.item.MarvelModItems;

public class ThorActivateArmorProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == MarvelModItems.STORMBREAKER.get()
				|| (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == MarvelModItems.MJOLNIR.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.THOR_ARMOR_CHESTPLATE.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.THOR_ARMOR_LEGGINGS.get())
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.THOR_ARMOR_BOOTS.get())) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = (entity instanceof LivingEntity _entGetArmor
						? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD)
						: ItemStack.EMPTY);
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			if (entity instanceof Player _player) {
				ItemStack _setstack = (entity instanceof LivingEntity _entGetArmor
						? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST)
						: ItemStack.EMPTY);
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			if (entity instanceof Player _player) {
				ItemStack _setstack = (entity instanceof LivingEntity _entGetArmor
						? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS)
						: ItemStack.EMPTY);
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			if (entity instanceof Player _player) {
				ItemStack _setstack = (entity instanceof LivingEntity _entGetArmor
						? _entGetArmor.getItemBySlot(EquipmentSlot.FEET)
						: ItemStack.EMPTY);
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			if (entity instanceof LivingEntity _entity) {
				if (_entity instanceof Player _player)
					_player.getInventory().armor.set(2, new ItemStack(MarvelModItems.THOR_ARMOR_CHESTPLATE.get()));
				else
					_entity.setItemSlot(EquipmentSlot.CHEST, new ItemStack(MarvelModItems.THOR_ARMOR_CHESTPLATE.get()));
				if (_entity instanceof ServerPlayer _serverPlayer)
					_serverPlayer.getInventory().setChanged();
			}
			if (entity instanceof LivingEntity _entity) {
				if (_entity instanceof Player _player)
					_player.getInventory().armor.set(1, new ItemStack(MarvelModItems.THOR_ARMOR_LEGGINGS.get()));
				else
					_entity.setItemSlot(EquipmentSlot.LEGS, new ItemStack(MarvelModItems.THOR_ARMOR_LEGGINGS.get()));
				if (_entity instanceof ServerPlayer _serverPlayer)
					_serverPlayer.getInventory().setChanged();
			}
			if (entity instanceof LivingEntity _entity) {
				if (_entity instanceof Player _player)
					_player.getInventory().armor.set(0, new ItemStack(MarvelModItems.THOR_ARMOR_BOOTS.get()));
				else
					_entity.setItemSlot(EquipmentSlot.FEET, new ItemStack(MarvelModItems.THOR_ARMOR_BOOTS.get()));
				if (_entity instanceof ServerPlayer _serverPlayer)
					_serverPlayer.getInventory().setChanged();
			}
			if (world instanceof ServerLevel _level) {
				LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
				entityToSpawn.moveTo(Vec3.atBottomCenterOf(new BlockPos((int) x, (int) y, (int) z)));
				entityToSpawn.setVisualOnly(true);
				_level.addFreshEntity(entityToSpawn);
			}
		}
	}
}
