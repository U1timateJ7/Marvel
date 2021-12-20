package com.ulto.marvel.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class IronManFlamethrowerBulletHitsBlockProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.AIR) {
			world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.FIRE.defaultBlockState(), 3);
		}
		if ((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == Blocks.AIR) {
			world.setBlock(new BlockPos((int) (x + 1), (int) y, (int) z), Blocks.FIRE.defaultBlockState(), 3);
		}
		if ((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == Blocks.AIR) {
			world.setBlock(new BlockPos((int) (x - 1), (int) y, (int) z), Blocks.FIRE.defaultBlockState(), 3);
		}
		if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == Blocks.AIR) {
			world.setBlock(new BlockPos((int) x, (int) y, (int) (z + 1)), Blocks.FIRE.defaultBlockState(), 3);
		}
		if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == Blocks.AIR) {
			world.setBlock(new BlockPos((int) x, (int) y, (int) (z - 1)), Blocks.FIRE.defaultBlockState(), 3);
		}
		if ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.AIR) {
			world.setBlock(new BlockPos((int) x, (int) (y + 1), (int) z), Blocks.FIRE.defaultBlockState(), 3);
		}
		if ((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.AIR) {
			world.setBlock(new BlockPos((int) x, (int) (y - 1), (int) z), Blocks.FIRE.defaultBlockState(), 3);
		}
	}
}
