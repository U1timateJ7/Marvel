package com.ulto.marvel.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import java.util.Map;
import java.util.HashMap;

import com.ulto.marvel.block.HeartShapedHerbStage7Block;
import com.ulto.marvel.block.HeartShapedHerbStage6Block;
import com.ulto.marvel.block.HeartShapedHerbStage5Block;
import com.ulto.marvel.block.HeartShapedHerbStage4Block;
import com.ulto.marvel.block.HeartShapedHerbStage3Block;
import com.ulto.marvel.block.HeartShapedHerbStage2Block;
import com.ulto.marvel.block.HeartShapedHerbStage1Block;
import com.ulto.marvel.MarvelModElements;
import com.ulto.marvel.MarvelMod;

@MarvelModElements.ModElement.Tag
public class HeartShapedHerbStage1UpdateTickProcedure extends MarvelModElements.ModElement {
	public HeartShapedHerbStage1UpdateTickProcedure(MarvelModElements instance) {
		super(instance, 97);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MarvelMod.LOGGER.warn("Failed to load dependency x for procedure HeartShapedHerbStage1UpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MarvelMod.LOGGER.warn("Failed to load dependency y for procedure HeartShapedHerbStage1UpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MarvelMod.LOGGER.warn("Failed to load dependency z for procedure HeartShapedHerbStage1UpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MarvelMod.LOGGER.warn("Failed to load dependency world for procedure HeartShapedHerbStage1UpdateTick!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double randomGrowth = 0;
		if ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.FARMLAND.getDefaultState().getBlock()))) {
			if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == HeartShapedHerbStage1Block.block.getDefaultState()
					.getBlock())
					|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == HeartShapedHerbStage2Block.block
							.getDefaultState().getBlock())
							|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == HeartShapedHerbStage3Block.block
									.getDefaultState().getBlock())
									|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == HeartShapedHerbStage4Block.block
											.getDefaultState().getBlock())
											|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
													.getBlock() == HeartShapedHerbStage5Block.block.getDefaultState().getBlock())
													|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
															.getBlock() == HeartShapedHerbStage6Block.block.getDefaultState().getBlock()))))))) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", x);
					$_dependencies.put("y", y);
					$_dependencies.put("z", z);
					HeartShapedHerbStage1BlockDestroyedByExplosionProcedure.executeProcedure($_dependencies);
				}
			} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == HeartShapedHerbStage7Block.block
					.getDefaultState().getBlock())) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", x);
					$_dependencies.put("y", y);
					$_dependencies.put("z", z);
					HeartShapedHerbStage7BlockDestroyedByExplosionProcedure.executeProcedure($_dependencies);
				}
			}
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
		} else if (((((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.SAND.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.RED_SAND.getDefaultState().getBlock()))
				|| (((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.GRAVEL.getDefaultState().getBlock())
						|| ((((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.ANVIL.getDefaultState()
								.getBlock())
								|| (((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.CHIPPED_ANVIL
										.getDefaultState().getBlock())
										|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.DAMAGED_ANVIL
												.getDefaultState().getBlock())))
								|| (((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.WHITE_CONCRETE_POWDER
										.getDefaultState().getBlock())
										|| (((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)))
												.getBlock() == Blocks.ORANGE_CONCRETE_POWDER.getDefaultState().getBlock())
												|| (((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)))
														.getBlock() == Blocks.MAGENTA_CONCRETE_POWDER.getDefaultState().getBlock())
														|| (((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)))
																.getBlock() == Blocks.LIGHT_BLUE_CONCRETE_POWDER.getDefaultState().getBlock())
																|| (((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)))
																		.getBlock() == Blocks.YELLOW_CONCRETE_POWDER.getDefaultState().getBlock())
																		|| (((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)))
																				.getBlock() == Blocks.LIME_CONCRETE_POWDER.getDefaultState()
																						.getBlock())
																				|| (((world
																						.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)))
																								.getBlock() == Blocks.PINK_CONCRETE_POWDER
																										.getDefaultState().getBlock())
																						|| (((world.getBlockState(
																								new BlockPos((int) x, (int) (y + 1), (int) z)))
																										.getBlock() == Blocks.GRAY_CONCRETE_POWDER
																												.getDefaultState().getBlock())
																								|| (((world.getBlockState(new BlockPos((int) x,
																										(int) (y + 1), (int) z)))
																												.getBlock() == Blocks.LIGHT_GRAY_CONCRETE_POWDER
																														.getDefaultState().getBlock())
																										|| (((world.getBlockState(new BlockPos(
																												(int) x, (int) (y + 1), (int) z)))
																														.getBlock() == Blocks.CYAN_CONCRETE_POWDER
																																.getDefaultState()
																																.getBlock())
																												|| (((world.getBlockState(
																														new BlockPos((int) x,
																																(int) (y + 1),
																																(int) z)))
																																		.getBlock() == Blocks.PURPLE_CONCRETE_POWDER
																																				.getDefaultState()
																																				.getBlock())
																														|| (((world.getBlockState(
																																new BlockPos((int) x,
																																		(int) (y + 1),
																																		(int) z)))
																																				.getBlock() == Blocks.BLUE_CONCRETE_POWDER
																																						.getDefaultState()
																																						.getBlock())
																																|| (((world
																																		.getBlockState(
																																				new BlockPos(
																																						(int) x,
																																						(int) (y + 1),
																																						(int) z)))
																																								.getBlock() == Blocks.BROWN_CONCRETE_POWDER
																																										.getDefaultState()
																																										.getBlock())
																																		|| (((world
																																				.getBlockState(
																																						new BlockPos(
																																								(int) x,
																																								(int) (y + 1),
																																								(int) z)))
																																										.getBlock() == Blocks.GREEN_CONCRETE_POWDER
																																												.getDefaultState()
																																												.getBlock())
																																				|| (((world
																																						.getBlockState(
																																								new BlockPos(
																																										(int) x,
																																										(int) (y + 1),
																																										(int) z)))
																																												.getBlock() == Blocks.RED_CONCRETE_POWDER
																																														.getDefaultState()
																																														.getBlock())
																																						|| ((world
																																								.getBlockState(
																																										new BlockPos(
																																												(int) x,
																																												(int) (y + 1),
																																												(int) z)))
																																														.getBlock() == Blocks.BLACK_CONCRETE_POWDER
																																																.getDefaultState()
																																																.getBlock())))))))))))))))))))) {
			if (world instanceof World) {
				Block.spawnDrops(world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)), (World) world,
						new BlockPos((int) x, (int) y, (int) z));
				world.destroyBlock(new BlockPos((int) x, (int) (y + 1), (int) z), false);
			}
		} else if (((!(world.isRemote())) && ((world.getLight(new BlockPos((int) x, (int) (y + 1), (int) z))) > 7))) {
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putDouble("growthTimer", ((new Object() {
						public double getValue(IWorld world, BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "growthTimer")) + 0.05));
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			if (((new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "growthTimer")) >= 40)) {
				randomGrowth = (double) Math.random();
				if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == HeartShapedHerbStage1Block.block.getDefaultState()
						.getBlock())) {
					if (((randomGrowth) >= 0.66)) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), HeartShapedHerbStage3Block.block.getDefaultState(), 3);
					} else if (((randomGrowth) >= 0.33)) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), HeartShapedHerbStage2Block.block.getDefaultState(), 3);
					}
					if (!world.isRemote()) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putDouble("growthTimer", 0);
						if (world instanceof World)
							((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
					}
				} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == HeartShapedHerbStage2Block.block
						.getDefaultState().getBlock())) {
					if (((randomGrowth) >= 0.66)) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), HeartShapedHerbStage4Block.block.getDefaultState(), 3);
					} else if (((randomGrowth) >= 0.33)) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), HeartShapedHerbStage3Block.block.getDefaultState(), 3);
					}
					if (!world.isRemote()) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putDouble("growthTimer", 0);
						if (world instanceof World)
							((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
					}
				} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == HeartShapedHerbStage3Block.block
						.getDefaultState().getBlock())) {
					if (((randomGrowth) >= 0.66)) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), HeartShapedHerbStage5Block.block.getDefaultState(), 3);
					} else if (((randomGrowth) >= 0.33)) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), HeartShapedHerbStage4Block.block.getDefaultState(), 3);
					}
					if (!world.isRemote()) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putDouble("growthTimer", 0);
						if (world instanceof World)
							((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
					}
				} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == HeartShapedHerbStage4Block.block
						.getDefaultState().getBlock())) {
					if (((randomGrowth) >= 0.66)) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), HeartShapedHerbStage6Block.block.getDefaultState(), 3);
					} else if (((randomGrowth) >= 0.33)) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), HeartShapedHerbStage5Block.block.getDefaultState(), 3);
					}
					if (!world.isRemote()) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putDouble("growthTimer", 0);
						if (world instanceof World)
							((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
					}
				} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == HeartShapedHerbStage5Block.block
						.getDefaultState().getBlock())) {
					if (((randomGrowth) >= 0.66)) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), HeartShapedHerbStage7Block.block.getDefaultState(), 3);
					} else if (((randomGrowth) >= 0.33)) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), HeartShapedHerbStage6Block.block.getDefaultState(), 3);
					}
					if (!world.isRemote()) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putDouble("growthTimer", 0);
						if (world instanceof World)
							((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
					}
				} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == HeartShapedHerbStage6Block.block
						.getDefaultState().getBlock())) {
					if (((randomGrowth) >= 0.5)) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), HeartShapedHerbStage7Block.block.getDefaultState(), 3);
					}
					if (!world.isRemote()) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putDouble("growthTimer", 0);
						if (world instanceof World)
							((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
					}
				}
			}
		}
	}
}
