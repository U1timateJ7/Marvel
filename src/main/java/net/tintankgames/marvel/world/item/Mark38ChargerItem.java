package net.tintankgames.marvel.world.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.tintankgames.marvel.world.level.block.Mark38ChargerBlock;

public class Mark38ChargerItem extends BlockItem {
    public Mark38ChargerItem(Block block, Properties properties) {
        super(block, properties);
    }

    protected boolean placeBlock(BlockPlaceContext blockPlaceContext, BlockState blockState) {
        Level level = blockPlaceContext.getLevel();
        for (BlockPos blockPos : Mark38ChargerBlock.getExtraPlacementPositions(blockPlaceContext.getClickedPos(), blockState)) {
            BlockState blockState2 = level.isWaterAt(blockPos) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
            level.setBlock(blockPos, blockState2, 27);
        }
        return super.placeBlock(blockPlaceContext, blockState);
    }
}
