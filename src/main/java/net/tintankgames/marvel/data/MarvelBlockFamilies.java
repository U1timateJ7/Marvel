package net.tintankgames.marvel.data;

import net.minecraft.data.BlockFamily;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;

public class MarvelBlockFamilies {
    public static final BlockFamily GREEN_HYDRA_BRICKS = new BlockFamily.Builder(MarvelBlocks.GREEN_HYDRA_BRICKS.get()).wall(MarvelBlocks.GREEN_HYDRA_BRICK_WALL.get()).stairs(MarvelBlocks.GREEN_HYDRA_BRICK_STAIRS.get()).slab(MarvelBlocks.GREEN_HYDRA_BRICK_SLAB.get()).getFamily();
    public static final BlockFamily YELLOW_HYDRA_BRICKS = new BlockFamily.Builder(MarvelBlocks.YELLOW_HYDRA_BRICKS.get()).wall(MarvelBlocks.YELLOW_HYDRA_BRICK_WALL.get()).stairs(MarvelBlocks.YELLOW_HYDRA_BRICK_STAIRS.get()).slab(MarvelBlocks.YELLOW_HYDRA_BRICK_SLAB.get()).getFamily();
}
