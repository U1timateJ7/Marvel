package net.tintankgames.marvel.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tintankgames.marvel.world.level.block.entity.Mark38ChargerMultiBlockEntity;
import net.tintankgames.marvel.world.level.block.entity.MarvelBlockEntityTypes;
import net.tintankgames.marvel.world.level.block.state.properties.ChargerPart;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class Mark38ChargerMultiBlock extends Block implements EntityBlock {
    public static final MapCodec<Mark38ChargerMultiBlock> CODEC = simpleCodec(Mark38ChargerMultiBlock::new);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<ChargerPart> PART = EnumProperty.create("part", ChargerPart.class);
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final Map<ChargerPart, VoxelShape> NORTH_SHAPES = Map.of(ChargerPart.BOTTOM_LEFT, Shapes.or(Shapes.box(0.25, 0.0625, 0, 1, 0.1875, 0.75), Shapes.box(0.1875, 0, 0, 1, 0.0625, 0.8125)), ChargerPart.BOTTOM_RIGHT, Shapes.or(Shapes.box(0, 0.0625, 0, 0.75, 0.1875, 0.75), Shapes.box(0, 0, 0, 0.8125, 0.0625, 0.8125)), ChargerPart.TOP_LEFT, Shapes.or(Shapes.box(0.25, 0.0625, 0.25, 1, 0.1875, 1), Shapes.or(Shapes.box(0.75, 0.0625, 0.1875, 1, 0.1875, 0.25), Shapes.box(0.1875, 0, 0.1875, 1, 0.0625, 1))), ChargerPart.TOP_RIGHT, Shapes.or(Shapes.box(0, 0.0625, 0.25, 0.75, 0.1875, 1), Shapes.or(Shapes.box(0, 0, 0.1875, 0.8125, 0.0625, 1), Shapes.box(0, 0.0625, 0.1875, 0.25, 0.1875, 0.25))));
    public static final Map<ChargerPart, VoxelShape> SOUTH_SHAPES = Map.of(ChargerPart.BOTTOM_LEFT, Shapes.or(Shapes.box(0, 0.0625, 0.25, 0.75, 0.1875, 1), Shapes.box(0, 0, 0.1875, 0.8125, 0.0625, 1)), ChargerPart.BOTTOM_RIGHT, Shapes.or(Shapes.box(0.25, 0.0625, 0.25, 1, 0.1875, 1), Shapes.box(0.1875, 0, 0.1875, 1, 0.0625, 1)), ChargerPart.TOP_LEFT, Shapes.or(Shapes.box(0, 0.0625, 0, 0.75, 0.1875, 0.75), Shapes.or(Shapes.box(0, 0.0625, 0.75, 0.25, 0.1875, 0.8125), Shapes.box(0, 0, 0, 0.8125, 0.0625, 0.8125))), ChargerPart.TOP_RIGHT, Shapes.or(Shapes.box(0.25, 0.0625, 0, 1, 0.1875, 0.75), Shapes.or(Shapes.box(0.1875, 0, 0, 1, 0.0625, 0.8125), Shapes.box(0.75, 0.0625, 0.75, 1, 0.1875, 0.8125))));
    public static final Map<ChargerPart, VoxelShape> EAST_SHAPES = Map.of(ChargerPart.BOTTOM_LEFT, Shapes.or(Shapes.box(0.25, 0.0625, 0.25, 1, 0.1875, 1), Shapes.box(0.1875, 0, 0.1875, 1, 0.0625, 1)), ChargerPart.BOTTOM_RIGHT, Shapes.or(Shapes.box(0.25, 0.0625, 0, 1, 0.1875, 0.75), Shapes.box(0.1875, 0, 0, 1, 0.0625, 0.8125)), ChargerPart.TOP_LEFT, Shapes.or(Shapes.box(0, 0.0625, 0.25, 0.75, 0.1875, 1), Shapes.or(Shapes.box(0.75, 0.0625, 0.75, 0.8125, 0.1875, 1), Shapes.box(0, 0, 0.1875, 0.8125, 0.0625, 1))), ChargerPart.TOP_RIGHT, Shapes.or(Shapes.box(0, 0.0625, 0, 0.75, 0.1875, 0.75), Shapes.or(Shapes.box(0, 0, 0, 0.8125, 0.0625, 0.8125), Shapes.box(0.75, 0.0625, 0, 0.8125, 0.1875, 0.25))));
    public static final Map<ChargerPart, VoxelShape> WEST_SHAPES = Map.of(ChargerPart.BOTTOM_LEFT, Shapes.or(Shapes.box(0, 0.0625, 0, 0.75, 0.1875, 0.75), Shapes.box(0, 0, 0, 0.8125, 0.0625, 0.8125)), ChargerPart.BOTTOM_RIGHT, Shapes.or(Shapes.box(0, 0.0625, 0.25, 0.75, 0.1875, 1), Shapes.box(0, 0, 0.1875, 0.8125, 0.0625, 1)), ChargerPart.TOP_LEFT, Shapes.or(Shapes.box(0.25, 0.0625, 0, 1, 0.1875, 0.75), Shapes.or(Shapes.box(0.1875, 0.0625, 0, 0.25, 0.1875, 0.25), Shapes.box(0.1875, 0, 0, 1, 0.0625, 0.8125))), ChargerPart.TOP_RIGHT, Shapes.or(Shapes.box(0.25, 0.0625, 0.25, 1, 0.1875, 1), Shapes.or(Shapes.box(0.1875, 0, 0.1875, 1, 0.0625, 1), Shapes.box(0.1875, 0.0625, 0.75, 0.25, 0.1875, 1))));
    public static final Map<Direction, Map<ChargerPart, VoxelShape>> SHAPES = Map.of(Direction.NORTH, NORTH_SHAPES, Direction.SOUTH, SOUTH_SHAPES, Direction.EAST, EAST_SHAPES, Direction.WEST, WEST_SHAPES);

    public Mark38ChargerMultiBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(PART, ChargerPart.TOP_RIGHT).setValue(POWERED, false));
    }

    @Override
    public MapCodec<Mark38ChargerMultiBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter p_152918_, BlockPos p_152919_, CollisionContext p_152920_) {
        return SHAPES.get(state.getValue(FACING)).get(state.getValue(PART));
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor levelAccessor, BlockPos pos, BlockPos pos1) {
        return !state.canSurvive(levelAccessor, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, state1, levelAccessor, pos, pos1);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos) {
        return !levelReader.isEmptyBlock(pos.below());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new Mark38ChargerMultiBlockEntity(blockPos, blockState);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos blockPos, BlockState blockState, @Nullable BlockEntity blockEntity, ItemStack itemStack) {
        super.playerDestroy(level, player, blockPos, blockState, blockEntity, itemStack);
        if (level.getBlockEntity(blockPos, MarvelBlockEntityTypes.MARK_38_CHARGER_PART.get()).isPresent()) {
            if (player.isCreative()) level.removeBlock(level.getBlockEntity(blockPos, MarvelBlockEntityTypes.MARK_38_CHARGER_PART.get()).get().mainBlock, true);
            else level.destroyBlock(level.getBlockEntity(blockPos, MarvelBlockEntityTypes.MARK_38_CHARGER_PART.get()).get().mainBlock, true, player);
            level.getBlockEntity(level.getBlockEntity(blockPos, MarvelBlockEntityTypes.MARK_38_CHARGER_PART.get()).get().mainBlock, MarvelBlockEntityTypes.MARK_38_CHARGER.get()).get().getExtraBlocks().forEach((part, pos) -> {
                level.destroyBlock(pos, !player.isCreative(), player);
            });
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, PART, POWERED);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos pos1, boolean p_300890_) {
        if (!level.isClientSide && level.getBlockEntity(pos, MarvelBlockEntityTypes.MARK_38_CHARGER_PART.get()).isPresent()) {
            Mark38ChargerMultiBlockEntity blockEntity = level.getBlockEntity(pos, MarvelBlockEntityTypes.MARK_38_CHARGER_PART.get()).get();
            boolean flag = hasNeighborSignals(level, Mark38ChargerBlock.getAllPlacementPositions(blockEntity.mainBlock, state.getValue(FACING)));
            if (level.getBlockState(blockEntity.mainBlock).hasProperty(POWERED) && flag != level.getBlockState(blockEntity.mainBlock).getValue(POWERED)) {
                level.setBlockAndUpdate(blockEntity.mainBlock, level.getBlockState(blockEntity.mainBlock).setValue(POWERED, flag));
                level.setBlockAndUpdate(Mark38ChargerBlock.getExtraPlacementPositions(blockEntity.mainBlock, state.getValue(FACING))[0], level.getBlockState(Mark38ChargerBlock.getExtraPlacementPositions(blockEntity.mainBlock, state.getValue(FACING))[0]).setValue(POWERED, flag));
                level.setBlockAndUpdate(Mark38ChargerBlock.getExtraPlacementPositions(blockEntity.mainBlock, state.getValue(FACING))[1], level.getBlockState(Mark38ChargerBlock.getExtraPlacementPositions(blockEntity.mainBlock, state.getValue(FACING))[1]).setValue(POWERED, flag));
                level.setBlockAndUpdate(Mark38ChargerBlock.getExtraPlacementPositions(blockEntity.mainBlock, state.getValue(FACING))[2], level.getBlockState(Mark38ChargerBlock.getExtraPlacementPositions(blockEntity.mainBlock, state.getValue(FACING))[2]).setValue(POWERED, flag));
            }
        }
    }

    private boolean hasNeighborSignals(SignalGetter signalGetter, List<BlockPos> positions) {
        boolean hasPower = false;
        for (BlockPos pos : positions) {
            for (Direction direction : Direction.values()) {
                if (signalGetter.hasSignal(pos.relative(direction), direction)) {
                    hasPower = true;
                }
            }

            if (signalGetter.hasSignal(pos, Direction.DOWN)) {
                hasPower = true;
            } else {
                BlockPos blockpos = pos.above();

                for (Direction direction1 : Direction.values()) {
                    if (signalGetter.hasSignal(blockpos.relative(direction1), direction1)) {
                        hasPower = true;
                    }
                }

            }
        }
        return hasPower;
    }

    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> blockEntityType, BlockEntityType<E> blockEntityType2, BlockEntityTicker<? super E> blockEntityTicker) {
        return blockEntityType2 == blockEntityType ? (BlockEntityTicker<A>) blockEntityTicker : null;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, MarvelBlockEntityTypes.MARK_38_CHARGER_PART.get(), Mark38ChargerMultiBlockEntity::tick);
    }
}
