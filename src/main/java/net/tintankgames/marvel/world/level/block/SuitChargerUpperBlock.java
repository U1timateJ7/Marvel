package net.tintankgames.marvel.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SuitChargerUpperBlock extends HorizontalDirectionalBlock {
    public static final MapCodec<SuitChargerUpperBlock> CODEC = simpleCodec(SuitChargerUpperBlock::new);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    private static final VoxelShape Z_SHAPE = Shapes.or(Shapes.box(0.25, 0, 0.4375, 0.75, 0.0625, 0.5625), Shapes.box(0.3125, 0.0625, 0.4375, 0.6875, 0.5, 0.5625), Shapes.box(0.125, 0.5, 0.40625, 0.875, 0.6875, 0.59375), Shapes.box(0.4375, 0.6875, 0.4375, 0.5625, 1.125, 0.5625));
    private static final VoxelShape X_SHAPE = Shapes.or(Shapes.box(0.4375, 0, 0.25, 0.5625, 0.0625, 0.75), Shapes.box(0.4375, 0.0625, 0.3125, 0.5625, 0.5, 0.6875), Shapes.box(0.40625, 0.5, 0.125, 0.59375, 0.6875, 0.875), Shapes.box(0.4375, 0.6875, 0.4375, 0.5625, 1.125, 0.5625));

    public SuitChargerUpperBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(POWERED, false));
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        return super.getCloneItemStack(state, target, level, pos, player);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos blockPos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case EAST, WEST -> X_SHAPE;
            default -> Z_SHAPE;
        };
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide && (player.isCreative() || !player.hasCorrectToolForDrops(state, level, pos))) {
            preventDropFromBottomPart(level, pos, player);
        }

        return super.playerWillDestroy(level, pos, state, player);
    }

    protected static void preventDropFromBottomPart(Level level, BlockPos pos, Player player) {
        BlockPos blockpos = pos.below();
        BlockState blockstate = level.getBlockState(blockpos);
        if (blockstate.is(MarvelBlocks.SUIT_CHARGER)) {
            BlockState blockstate1 = blockstate.getFluidState().is(Fluids.WATER) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
            level.setBlock(blockpos, blockstate1, 35);
            level.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        return level.getBlockState(pos.below()).useWithoutItem(level, player, hitResult.withPosition(hitResult.getBlockPos().below()));
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        return level.getBlockState(pos.below()).useItemOn(stack, level, player, hand, hitResult.withPosition(hitResult.getBlockPos().below()));
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor levelAccessor, BlockPos pos, BlockPos pos1) {
        if (direction == Direction.DOWN && !state1.is(MarvelBlocks.SUIT_CHARGER)) return Blocks.AIR.defaultBlockState();
        else return super.updateShape(state, direction, state1, levelAccessor, pos, pos1);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(POWERED, hasNeighborSignal(context.getLevel(), context.getClickedPos()));
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos pos1, boolean p_300890_) {
        if (!level.isClientSide) {
            boolean flag = hasNeighborSignal(level, pos.below());
            if (flag != state.getValue(POWERED)) {
                level.setBlock(pos, state.setValue(POWERED, flag), 2);
            }
        }
    }

    private boolean hasNeighborSignal(SignalGetter signalGetter, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            if (signalGetter.hasSignal(pos.relative(direction), direction)) {
                return true;
            }
        }

        if (signalGetter.hasSignal(pos, Direction.DOWN)) {
            return true;
        } else {
            BlockPos blockpos = pos.above();

            for (Direction direction1 : Direction.values()) {
                if (signalGetter.hasSignal(blockpos.relative(direction1), direction1)) {
                    return true;
                }
            }

            return false;
        }
    }

    @Override
    public void destroy(LevelAccessor levelAccessor, BlockPos pos, BlockState state) {
        if (levelAccessor.getBlockState(pos.below()).is(MarvelBlocks.SUIT_CHARGER)) levelAccessor.removeBlock(pos.below(), true);
        super.destroy(levelAccessor, pos, state);
    }

    @Override
    protected MapCodec<SuitChargerUpperBlock> codec() {
        return CODEC;
    }
}
