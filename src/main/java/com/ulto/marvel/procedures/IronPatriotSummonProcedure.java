package com.ulto.marvel.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Entity;

import java.util.function.Function;
import java.util.Random;
import java.util.Map;
import java.util.Comparator;

import com.ulto.marvel.item.IronPatriotItem;
import com.ulto.marvel.entity.SentryModeEntity;
import com.ulto.marvel.MarvelModVariables;
import com.ulto.marvel.MarvelModElements;
import com.ulto.marvel.MarvelMod;

@MarvelModElements.ModElement.Tag
public class IronPatriotSummonProcedure extends MarvelModElements.ModElement {
	public IronPatriotSummonProcedure(MarvelModElements instance) {
		super(instance, 357);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MarvelMod.LOGGER.warn("Failed to load dependency entity for procedure IronPatriotSummon!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MarvelMod.LOGGER.warn("Failed to load dependency x for procedure IronPatriotSummon!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MarvelMod.LOGGER.warn("Failed to load dependency z for procedure IronPatriotSummon!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MarvelMod.LOGGER.warn("Failed to load dependency world for procedure IronPatriotSummon!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double x2 = 0;
		double z2 = 0;
		x2 = (double) (x + (((new Random()).nextInt((int) 6 + 1)) - 3));
		z2 = (double) (z + (((new Random()).nextInt((int) 6 + 1)) - 3));
		if (world instanceof ServerWorld) {
			Entity entityToSpawn = new SentryModeEntity.CustomEntity(SentryModeEntity.entity, (World) world);
			entityToSpawn.setLocationAndAngles((x2), 255, (z2), world.getRandom().nextFloat() * 360F, 0);
			if (entityToSpawn instanceof MobEntity)
				((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world, world.getDifficultyForLocation(entityToSpawn.getPosition()),
						SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
			world.addEntity(entityToSpawn);
		}
		if (((Entity) world
				.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class,
						new AxisAlignedBB((x2) - (1 / 2d), 255 - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), 255 + (1 / 2d), (z2) + (1 / 2d)), null)
				.stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
					}
				}.compareDistOf((x2), 255, (z2))).findFirst().orElse(null)) instanceof LivingEntity) {
			if (((Entity) world.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class,
					new AxisAlignedBB((x2) - (1 / 2d), 255 - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), 255 + (1 / 2d), (z2) + (1 / 2d)), null)
					.stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf((x2), 255, (z2))).findFirst().orElse(null)) instanceof PlayerEntity)
				((PlayerEntity) ((Entity) world.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class,
						new AxisAlignedBB((x2) - (1 / 2d), 255 - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), 255 + (1 / 2d), (z2) + (1 / 2d)), null)
						.stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf((x2), 255, (z2))).findFirst().orElse(null))).inventory.armorInventory.set((int) 3,
								new ItemStack(IronPatriotItem.helmet, (int) (1)));
			else
				((LivingEntity) ((Entity) world.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class,
						new AxisAlignedBB((x2) - (1 / 2d), 255 - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), 255 + (1 / 2d), (z2) + (1 / 2d)), null)
						.stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf((x2), 255, (z2))).findFirst().orElse(null))).setItemStackToSlot(
								EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 3),
								new ItemStack(IronPatriotItem.helmet, (int) (1)));
			if (((Entity) world.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class,
					new AxisAlignedBB((x2) - (1 / 2d), 255 - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), 255 + (1 / 2d), (z2) + (1 / 2d)), null)
					.stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf((x2), 255, (z2))).findFirst().orElse(null)) instanceof ServerPlayerEntity)
				((ServerPlayerEntity) ((Entity) world.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class,
						new AxisAlignedBB((x2) - (1 / 2d), 255 - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), 255 + (1 / 2d), (z2) + (1 / 2d)), null)
						.stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf((x2), 255, (z2))).findFirst().orElse(null))).inventory.markDirty();
		}
		if (((Entity) world
				.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class,
						new AxisAlignedBB((x2) - (1 / 2d), 255 - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), 255 + (1 / 2d), (z2) + (1 / 2d)), null)
				.stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
					}
				}.compareDistOf((x2), 255, (z2))).findFirst().orElse(null)) instanceof LivingEntity) {
			if (((Entity) world.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class,
					new AxisAlignedBB((x2) - (1 / 2d), 255 - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), 255 + (1 / 2d), (z2) + (1 / 2d)), null)
					.stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf((x2), 255, (z2))).findFirst().orElse(null)) instanceof PlayerEntity)
				((PlayerEntity) ((Entity) world.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class,
						new AxisAlignedBB((x2) - (1 / 2d), 255 - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), 255 + (1 / 2d), (z2) + (1 / 2d)), null)
						.stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf((x2), 255, (z2))).findFirst().orElse(null))).inventory.armorInventory.set((int) 2,
								new ItemStack(IronPatriotItem.body, (int) (1)));
			else
				((LivingEntity) ((Entity) world.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class,
						new AxisAlignedBB((x2) - (1 / 2d), 255 - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), 255 + (1 / 2d), (z2) + (1 / 2d)), null)
						.stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf((x2), 255, (z2))).findFirst().orElse(null))).setItemStackToSlot(
								EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2),
								new ItemStack(IronPatriotItem.body, (int) (1)));
			if (((Entity) world.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class,
					new AxisAlignedBB((x2) - (1 / 2d), 255 - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), 255 + (1 / 2d), (z2) + (1 / 2d)), null)
					.stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf((x2), 255, (z2))).findFirst().orElse(null)) instanceof ServerPlayerEntity)
				((ServerPlayerEntity) ((Entity) world.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class,
						new AxisAlignedBB((x2) - (1 / 2d), 255 - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), 255 + (1 / 2d), (z2) + (1 / 2d)), null)
						.stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf((x2), 255, (z2))).findFirst().orElse(null))).inventory.markDirty();
		}
		if (((Entity) world
				.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class,
						new AxisAlignedBB((x2) - (1 / 2d), 255 - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), 255 + (1 / 2d), (z2) + (1 / 2d)), null)
				.stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
					}
				}.compareDistOf((x2), 255, (z2))).findFirst().orElse(null)) instanceof LivingEntity) {
			if (((Entity) world.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class,
					new AxisAlignedBB((x2) - (1 / 2d), 255 - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), 255 + (1 / 2d), (z2) + (1 / 2d)), null)
					.stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf((x2), 255, (z2))).findFirst().orElse(null)) instanceof PlayerEntity)
				((PlayerEntity) ((Entity) world.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class,
						new AxisAlignedBB((x2) - (1 / 2d), 255 - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), 255 + (1 / 2d), (z2) + (1 / 2d)), null)
						.stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf((x2), 255, (z2))).findFirst().orElse(null))).inventory.armorInventory.set((int) 1,
								new ItemStack(IronPatriotItem.legs, (int) (1)));
			else
				((LivingEntity) ((Entity) world.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class,
						new AxisAlignedBB((x2) - (1 / 2d), 255 - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), 255 + (1 / 2d), (z2) + (1 / 2d)), null)
						.stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf((x2), 255, (z2))).findFirst().orElse(null))).setItemStackToSlot(
								EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 1),
								new ItemStack(IronPatriotItem.legs, (int) (1)));
			if (((Entity) world.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class,
					new AxisAlignedBB((x2) - (1 / 2d), 255 - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), 255 + (1 / 2d), (z2) + (1 / 2d)), null)
					.stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf((x2), 255, (z2))).findFirst().orElse(null)) instanceof ServerPlayerEntity)
				((ServerPlayerEntity) ((Entity) world.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class,
						new AxisAlignedBB((x2) - (1 / 2d), 255 - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), 255 + (1 / 2d), (z2) + (1 / 2d)), null)
						.stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf((x2), 255, (z2))).findFirst().orElse(null))).inventory.markDirty();
		}
		if (((Entity) world
				.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class,
						new AxisAlignedBB((x2) - (1 / 2d), 255 - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), 255 + (1 / 2d), (z2) + (1 / 2d)), null)
				.stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
					}
				}.compareDistOf((x2), 255, (z2))).findFirst().orElse(null)) instanceof LivingEntity) {
			if (((Entity) world.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class,
					new AxisAlignedBB((x2) - (1 / 2d), 255 - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), 255 + (1 / 2d), (z2) + (1 / 2d)), null)
					.stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf((x2), 255, (z2))).findFirst().orElse(null)) instanceof PlayerEntity)
				((PlayerEntity) ((Entity) world.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class,
						new AxisAlignedBB((x2) - (1 / 2d), 255 - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), 255 + (1 / 2d), (z2) + (1 / 2d)), null)
						.stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf((x2), 255, (z2))).findFirst().orElse(null))).inventory.armorInventory.set((int) 0,
								new ItemStack(IronPatriotItem.boots, (int) (1)));
			else
				((LivingEntity) ((Entity) world.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class,
						new AxisAlignedBB((x2) - (1 / 2d), 255 - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), 255 + (1 / 2d), (z2) + (1 / 2d)), null)
						.stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf((x2), 255, (z2))).findFirst().orElse(null))).setItemStackToSlot(
								EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0),
								new ItemStack(IronPatriotItem.boots, (int) (1)));
			if (((Entity) world.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class,
					new AxisAlignedBB((x2) - (1 / 2d), 255 - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), 255 + (1 / 2d), (z2) + (1 / 2d)), null)
					.stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf((x2), 255, (z2))).findFirst().orElse(null)) instanceof ServerPlayerEntity)
				((ServerPlayerEntity) ((Entity) world.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class,
						new AxisAlignedBB((x2) - (1 / 2d), 255 - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), 255 + (1 / 2d), (z2) + (1 / 2d)), null)
						.stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf((x2), 255, (z2))).findFirst().orElse(null))).inventory.markDirty();
		}
		if ((((Entity) world
				.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class,
						new AxisAlignedBB((x2) - (1 / 2d), 255 - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), 255 + (1 / 2d), (z2) + (1 / 2d)), null)
				.stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
					}
				}.compareDistOf((x2), 255, (z2))).findFirst().orElse(null)) instanceof TameableEntity) && (entity instanceof PlayerEntity)) {
			((TameableEntity) ((Entity) world.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class,
					new AxisAlignedBB((x2) - (1 / 2d), 255 - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), 255 + (1 / 2d), (z2) + (1 / 2d)), null)
					.stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf((x2), 255, (z2))).findFirst().orElse(null))).setTamed(true);
			((TameableEntity) ((Entity) world.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class,
					new AxisAlignedBB((x2) - (1 / 2d), 255 - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), 255 + (1 / 2d), (z2) + (1 / 2d)), null)
					.stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf((x2), 255, (z2))).findFirst().orElse(null))).setTamedBy((PlayerEntity) entity);
		}
		{
			boolean _setval = (boolean) (false);
			entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.ironPatriotReady = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
