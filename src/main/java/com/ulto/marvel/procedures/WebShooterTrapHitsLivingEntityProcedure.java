package com.ulto.marvel.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import com.ulto.marvel.world.level.block.MarvelModBlocks;

public class WebShooterTrapHitsLivingEntityProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if ((world.getBlockState(new BlockPos((int) (entity.getX()), (int) (entity.getY()), (int) (entity.getZ())))).getBlock() == Blocks.AIR
				|| (world.getBlockState(new BlockPos((int) (entity.getX()), (int) (entity.getY()), (int) (entity.getZ()))))
						.getBlock() == Blocks.VOID_AIR
				|| (world.getBlockState(new BlockPos((int) (entity.getX()), (int) (entity.getY()), (int) (entity.getZ()))))
						.getBlock() == Blocks.CAVE_AIR
				|| (world.getBlockState(new BlockPos((int) (entity.getX()), (int) (entity.getY()), (int) (entity.getZ()))))
						.getBlock() == Blocks.SNOW) {
			world.setBlock(new BlockPos((int) (entity.getX()), (int) (entity.getY()), (int) (entity.getZ())),
					MarvelModBlocks.TEMPORARY_WEB.get().defaultBlockState(), 3);
		}
	}
}
