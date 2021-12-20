package com.ulto.marvel.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;

import java.util.Random;
import java.util.Comparator;

import com.ulto.marvel.network.MarvelModVariables;
import com.ulto.marvel.init.MarvelModItems;
import com.ulto.marvel.init.MarvelModEntities;
import com.ulto.marvel.entity.SentryModeEntity;

public class Mark33SummonProcedure {
	public static void execute(LevelAccessor world, double x, double z, Entity entity) {
		if (entity == null)
			return;
		double x2 = 0;
		double z2 = 0;
		x2 = x + new Random().nextInt(6 + 1) - 3;
		z2 = z + new Random().nextInt(6 + 1) - 3;
		if (world instanceof ServerLevel _level) {
			Entity entityToSpawn = new SentryModeEntity(MarvelModEntities.SENTRY_MODE, _level);
			entityToSpawn.moveTo(x2, 255, z2, world.getRandom().nextFloat() * 360F, 0);
			if (entityToSpawn instanceof Mob _mobToSpawn)
				_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
			world.addFreshEntity(entityToSpawn);
		}
		if (((Entity) world.getEntitiesOfClass(SentryModeEntity.class, AABB.ofSize(new Vec3(x2, 255, z2), 1, 1, 1), e -> true).stream()
				.sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x2, 255, z2)).findFirst().orElse(null))instanceof LivingEntity _entity) {
			if (_entity instanceof Player _player)
				_player.getInventory().armor.set(3, new ItemStack(MarvelModItems.IRON_MAN_MARK_33_HELMET));
			else
				_entity.setItemSlot(EquipmentSlot.HEAD, new ItemStack(MarvelModItems.IRON_MAN_MARK_33_HELMET));
			if (_entity instanceof ServerPlayer _serverPlayer)
				_serverPlayer.getInventory().setChanged();
		}
		if (((Entity) world.getEntitiesOfClass(SentryModeEntity.class, AABB.ofSize(new Vec3(x2, 255, z2), 1, 1, 1), e -> true).stream()
				.sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x2, 255, z2)).findFirst().orElse(null))instanceof LivingEntity _entity) {
			if (_entity instanceof Player _player)
				_player.getInventory().armor.set(2, new ItemStack(MarvelModItems.IRON_MAN_MARK_33_CHESTPLATE));
			else
				_entity.setItemSlot(EquipmentSlot.CHEST, new ItemStack(MarvelModItems.IRON_MAN_MARK_33_CHESTPLATE));
			if (_entity instanceof ServerPlayer _serverPlayer)
				_serverPlayer.getInventory().setChanged();
		}
		if (((Entity) world.getEntitiesOfClass(SentryModeEntity.class, AABB.ofSize(new Vec3(x2, 255, z2), 1, 1, 1), e -> true).stream()
				.sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x2, 255, z2)).findFirst().orElse(null))instanceof LivingEntity _entity) {
			if (_entity instanceof Player _player)
				_player.getInventory().armor.set(1, new ItemStack(MarvelModItems.IRON_MAN_MARK_33_LEGGINGS));
			else
				_entity.setItemSlot(EquipmentSlot.LEGS, new ItemStack(MarvelModItems.IRON_MAN_MARK_33_LEGGINGS));
			if (_entity instanceof ServerPlayer _serverPlayer)
				_serverPlayer.getInventory().setChanged();
		}
		if (((Entity) world.getEntitiesOfClass(SentryModeEntity.class, AABB.ofSize(new Vec3(x2, 255, z2), 1, 1, 1), e -> true).stream()
				.sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x2, 255, z2)).findFirst().orElse(null))instanceof LivingEntity _entity) {
			if (_entity instanceof Player _player)
				_player.getInventory().armor.set(0, new ItemStack(MarvelModItems.IRON_MAN_MARK_33_BOOTS));
			else
				_entity.setItemSlot(EquipmentSlot.FEET, new ItemStack(MarvelModItems.IRON_MAN_MARK_33_BOOTS));
			if (_entity instanceof ServerPlayer _serverPlayer)
				_serverPlayer.getInventory().setChanged();
		}
		if (((Entity) world.getEntitiesOfClass(SentryModeEntity.class, AABB.ofSize(new Vec3(x2, 255, z2), 1, 1, 1), e -> true).stream()
				.sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x2, 255, z2)).findFirst().orElse(null))instanceof TamableAnimal _toTame && entity instanceof Player _owner)
			_toTame.tame(_owner);
		{
			boolean _setval = false;
			entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.mark33Ready = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
