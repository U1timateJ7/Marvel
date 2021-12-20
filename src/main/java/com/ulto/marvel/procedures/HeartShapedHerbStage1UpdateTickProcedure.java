package com.ulto.marvel.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;

import com.ulto.marvel.init.MarvelModBlocks;

public class HeartShapedHerbStage1UpdateTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double randomGrowth = 0;
		if (!((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.FARMLAND)) {
			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == MarvelModBlocks.HEART_SHAPED_HERB_STAGE_1
					|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == MarvelModBlocks.HEART_SHAPED_HERB_STAGE_2
					|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == MarvelModBlocks.HEART_SHAPED_HERB_STAGE_3
					|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == MarvelModBlocks.HEART_SHAPED_HERB_STAGE_4
					|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == MarvelModBlocks.HEART_SHAPED_HERB_STAGE_5
					|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == MarvelModBlocks.HEART_SHAPED_HERB_STAGE_6) {
				HeartShapedHerbStage1BlockDestroyedByExplosionProcedure.execute(world, x, y, z);
			} else if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == MarvelModBlocks.HEART_SHAPED_HERB_STAGE_7) {
				HeartShapedHerbStage7BlockDestroyedByExplosionProcedure.execute(world, x, y, z);
			}
			world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
		} else if ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.SAND
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.RED_SAND
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.GRAVEL
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.ANVIL
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.CHIPPED_ANVIL
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.DAMAGED_ANVIL
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.WHITE_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.ORANGE_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.MAGENTA_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.LIGHT_BLUE_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.YELLOW_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.LIME_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.PINK_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.GRAY_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.LIGHT_GRAY_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.CYAN_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.PURPLE_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.BLUE_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.BROWN_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.GREEN_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.RED_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.BLACK_CONCRETE_POWDER) {
			if (world instanceof Level) {
				Block.dropResources(world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)), (Level) world,
						new BlockPos((int) x, (int) y, (int) z));
				world.destroyBlock(new BlockPos((int) x, (int) (y + 1), (int) z), false);
			}
		} else if (!world.isClientSide() && world.getMaxLocalRawBrightness(new BlockPos((int) x, (int) (y + 1), (int) z)) > 7) {
			if (!world.isClientSide()) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null)
					_blockEntity.getTileData().putDouble("growthTimer", (new Object() {
						public double getValue(LevelAccessor world, BlockPos pos, String tag) {
							BlockEntity blockEntity = world.getBlockEntity(pos);
							if (blockEntity != null)
								return blockEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "growthTimer") + 0.05));
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			if (new Object() {
				public double getValue(LevelAccessor world, BlockPos pos, String tag) {
					BlockEntity blockEntity = world.getBlockEntity(pos);
					if (blockEntity != null)
						return blockEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "growthTimer") >= 40) {
				randomGrowth = Math.random();
				if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == MarvelModBlocks.HEART_SHAPED_HERB_STAGE_1) {
					if (randomGrowth >= 0.66) {
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), MarvelModBlocks.HEART_SHAPED_HERB_STAGE_3.defaultBlockState(), 3);
					} else if (randomGrowth >= 0.33) {
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), MarvelModBlocks.HEART_SHAPED_HERB_STAGE_2.defaultBlockState(), 3);
					}
					if (!world.isClientSide()) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getTileData().putDouble("growthTimer", 0);
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				} else if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == MarvelModBlocks.HEART_SHAPED_HERB_STAGE_2) {
					if (randomGrowth >= 0.66) {
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), MarvelModBlocks.HEART_SHAPED_HERB_STAGE_4.defaultBlockState(), 3);
					} else if (randomGrowth >= 0.33) {
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), MarvelModBlocks.HEART_SHAPED_HERB_STAGE_3.defaultBlockState(), 3);
					}
					if (!world.isClientSide()) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getTileData().putDouble("growthTimer", 0);
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				} else if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == MarvelModBlocks.HEART_SHAPED_HERB_STAGE_3) {
					if (randomGrowth >= 0.66) {
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), MarvelModBlocks.HEART_SHAPED_HERB_STAGE_5.defaultBlockState(), 3);
					} else if (randomGrowth >= 0.33) {
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), MarvelModBlocks.HEART_SHAPED_HERB_STAGE_4.defaultBlockState(), 3);
					}
					if (!world.isClientSide()) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getTileData().putDouble("growthTimer", 0);
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				} else if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == MarvelModBlocks.HEART_SHAPED_HERB_STAGE_4) {
					if (randomGrowth >= 0.66) {
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), MarvelModBlocks.HEART_SHAPED_HERB_STAGE_6.defaultBlockState(), 3);
					} else if (randomGrowth >= 0.33) {
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), MarvelModBlocks.HEART_SHAPED_HERB_STAGE_5.defaultBlockState(), 3);
					}
					if (!world.isClientSide()) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getTileData().putDouble("growthTimer", 0);
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				} else if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == MarvelModBlocks.HEART_SHAPED_HERB_STAGE_5) {
					if (randomGrowth >= 0.66) {
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), MarvelModBlocks.HEART_SHAPED_HERB_STAGE_7.defaultBlockState(), 3);
					} else if (randomGrowth >= 0.33) {
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), MarvelModBlocks.HEART_SHAPED_HERB_STAGE_6.defaultBlockState(), 3);
					}
					if (!world.isClientSide()) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getTileData().putDouble("growthTimer", 0);
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				} else if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == MarvelModBlocks.HEART_SHAPED_HERB_STAGE_6) {
					if (randomGrowth >= 0.5) {
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), MarvelModBlocks.HEART_SHAPED_HERB_STAGE_7.defaultBlockState(), 3);
					}
					if (!world.isClientSide()) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getTileData().putDouble("growthTimer", 0);
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			}
		}
	}
}
