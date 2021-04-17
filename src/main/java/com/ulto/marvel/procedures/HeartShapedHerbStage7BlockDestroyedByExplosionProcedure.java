package com.ulto.marvel.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.item.ItemEntity;

import java.util.Map;

import com.ulto.marvel.item.HeartShapedHerbSeedsItem;
import com.ulto.marvel.item.HeartShapedHerbItemItem;
import com.ulto.marvel.MarvelModElements;
import com.ulto.marvel.MarvelMod;

@MarvelModElements.ModElement.Tag
public class HeartShapedHerbStage7BlockDestroyedByExplosionProcedure extends MarvelModElements.ModElement {
	public HeartShapedHerbStage7BlockDestroyedByExplosionProcedure(MarvelModElements instance) {
		super(instance, 96);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MarvelMod.LOGGER.warn("Failed to load dependency x for procedure HeartShapedHerbStage7BlockDestroyedByExplosion!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MarvelMod.LOGGER.warn("Failed to load dependency y for procedure HeartShapedHerbStage7BlockDestroyedByExplosion!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MarvelMod.LOGGER.warn("Failed to load dependency z for procedure HeartShapedHerbStage7BlockDestroyedByExplosion!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MarvelMod.LOGGER.warn("Failed to load dependency world for procedure HeartShapedHerbStage7BlockDestroyedByExplosion!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double dropChance = 0;
		dropChance = (double) Math.random();
		if (world instanceof World && !world.isRemote()) {
			ItemEntity entityToSpawn = new ItemEntity((World) world, (x + 0.5), (y + 0.5), (z + 0.5),
					new ItemStack(HeartShapedHerbItemItem.block, (int) (1)));
			entityToSpawn.setPickupDelay((int) 10);
			world.addEntity(entityToSpawn);
		}
		if (((dropChance) >= 0.9)) {
			for (int index0 = 0; index0 < (int) (2); index0++) {
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, (x + 0.5), (y + 0.5), (z + 0.5),
							new ItemStack(HeartShapedHerbSeedsItem.block, (int) (1)));
					entityToSpawn.setPickupDelay((int) 10);
					world.addEntity(entityToSpawn);
				}
			}
		} else if (((dropChance) >= 0.1)) {
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, (x + 0.5), (y + 0.5), (z + 0.5),
						new ItemStack(HeartShapedHerbSeedsItem.block, (int) (1)));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		}
	}
}
