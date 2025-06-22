package net.tintankgames.marvel.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tintankgames.marvel.world.level.block.entity.Mark38ChargerBlockEntity;
import net.tintankgames.marvel.world.level.block.entity.MarvelBlockEntityTypes;
import net.tintankgames.marvel.world.level.block.state.properties.ChargerPart;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Mark38ChargerBlock extends CarpetBlock implements EntityBlock {
    public static final MapCodec<Mark38ChargerBlock> CODEC = simpleCodec(Mark38ChargerBlock::new);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public Mark38ChargerBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(POWERED, false));
    }

    @Override
    public MapCodec<Mark38ChargerBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter p_152918_, BlockPos p_152919_, CollisionContext p_152920_) {
        return Mark38ChargerMultiBlock.SHAPES.get(state.getValue(FACING)).get(ChargerPart.TOP_LEFT);
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
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        BlockPos blockPos = blockPlaceContext.getClickedPos();
        Level level = blockPlaceContext.getLevel();
        return canPlace(blockPlaceContext.getHorizontalDirection().getOpposite(), blockPos, level, blockPlaceContext) ? this.defaultBlockState().setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite()).setValue(POWERED, hasNeighborSignals(level, getAllPlacementPositions(blockPos, blockPlaceContext.getHorizontalDirection().getOpposite()))) : null;
    }

    public boolean canPlace(Direction direction, BlockPos pos, Level level, BlockPlaceContext blockPlaceContext) {
        boolean bl = true;
        for (BlockPos blockPos : getExtraPlacementPositions(pos, direction)) {
            bl = bl && level.getBlockState(blockPos).canBeReplaced(blockPlaceContext);
        }
        return bl;
    }

    public static BlockPos[] getExtraPlacementPositions(BlockPos pos, BlockState state) {
        return getExtraPlacementPositions(pos, state.getValue(FACING));
    }

    public static BlockPos[] getExtraPlacementPositions(BlockPos pos, Direction direction) {
        return getExtraPlacementPositions(pos, direction, ChargerPart.TOP_LEFT);
    }

    public static List<BlockPos> getAllPlacementPositions(BlockPos pos, Direction direction) {
        List<BlockPos> list = new ArrayList<>(Arrays.asList(getExtraPlacementPositions(pos, direction, ChargerPart.TOP_LEFT)));
        list.add(pos);
        return list;
    }

    public static BlockPos[] getExtraPlacementPositions(BlockPos pos, Direction direction, ChargerPart part) {
        return switch (part) {
            case TOP_LEFT -> switch (direction) {
                case NORTH, UP, DOWN -> new BlockPos[] {
                        pos.south(),
                        pos.south().east(),
                        pos.east()
                };
                case SOUTH -> new BlockPos[] {
                        pos.north(),
                        pos.north().west(),
                        pos.west()
                };
                case WEST -> new BlockPos[] {
                        pos.east(),
                        pos.east().north(),
                        pos.north()
                };
                case EAST -> new BlockPos[] {
                        pos.west(),
                        pos.west().south(),
                        pos.south()
                };
            };
            case TOP_RIGHT -> switch (direction) {
                case NORTH, UP, DOWN -> new BlockPos[] {
                        pos.south().west(),
                        pos.south(),
                        pos.west()
                };
                case SOUTH -> new BlockPos[] {
                        pos.north().east(),
                        pos.north(),
                        pos.east()
                };
                case WEST -> new BlockPos[] {
                        pos.east().south(),
                        pos.east(),
                        pos.south()
                };
                case EAST -> new BlockPos[] {
                        pos.west().north(),
                        pos.west(),
                        pos.north()
                };
            };
            case BOTTOM_LEFT -> switch (direction) {
                case NORTH, UP, DOWN -> new BlockPos[] {
                        pos.east(),
                        pos.north(),
                        pos.north().east()
                };
                case SOUTH -> new BlockPos[] {
                        pos.west(),
                        pos.south(),
                        pos.south().west()
                };
                case WEST -> new BlockPos[] {
                        pos.north(),
                        pos.west(),
                        pos.west().north()
                };
                case EAST -> new BlockPos[] {
                        pos.south(),
                        pos.east(),
                        pos.east().south()
                };
            };
            case BOTTOM_RIGHT -> switch (direction) {
                case NORTH, UP, DOWN -> new BlockPos[] {
                        pos.west(),
                        pos.north().west(),
                        pos.north()
                };
                case SOUTH -> new BlockPos[] {
                        pos.east(),
                        pos.south().east(),
                        pos.south()
                };
                case WEST -> new BlockPos[] {
                        pos.south(),
                        pos.west().south(),
                        pos.west()
                };
                case EAST -> new BlockPos[] {
                        pos.north(),
                        pos.east().north(),
                        pos.east()
                };
            };
        };
    }

    @Override
    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, LivingEntity livingEntity, ItemStack itemStack) {
        BlockPos[] posList = getExtraPlacementPositions(blockPos, blockState);
        level.getBlockEntity(blockPos, MarvelBlockEntityTypes.MARK_38_CHARGER.get()).get().setExtraBlocks(Map.of(ChargerPart.BOTTOM_LEFT, posList[0], ChargerPart.BOTTOM_RIGHT, posList[1], ChargerPart.TOP_RIGHT, posList[2]));
        BlockState state = MarvelBlocks.MARK_38_CHARGER_PART.get().defaultBlockState().setValue(Mark38ChargerMultiBlock.FACING, blockState.getValue(FACING));
        level.setBlock(posList[0], state.setValue(Mark38ChargerMultiBlock.PART, ChargerPart.BOTTOM_LEFT).setValue(POWERED, blockState.getValue(POWERED)), 3);
        level.setBlock(posList[1], state.setValue(Mark38ChargerMultiBlock.PART, ChargerPart.BOTTOM_RIGHT).setValue(POWERED, blockState.getValue(POWERED)), 3);
        level.setBlock(posList[2], state.setValue(Mark38ChargerMultiBlock.PART, ChargerPart.TOP_RIGHT).setValue(POWERED, blockState.getValue(POWERED)), 3);
    }

    @Override
    public void destroy(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState) {
        super.destroy(levelAccessor, blockPos, blockState);
        if (levelAccessor.getBlockEntity(blockPos, MarvelBlockEntityTypes.MARK_38_CHARGER.get()).isPresent()) {
            levelAccessor.getBlockEntity(blockPos, MarvelBlockEntityTypes.MARK_38_CHARGER.get()).get().getExtraBlocks().forEach((part, pos) -> {
                levelAccessor.destroyBlock(pos, true);
            });
        }
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos blockPos, BlockState blockState, @Nullable BlockEntity blockEntity, ItemStack itemStack) {
        super.playerDestroy(level, player, blockPos, blockState, blockEntity, itemStack);
        if (level.getBlockEntity(blockPos, MarvelBlockEntityTypes.MARK_38_CHARGER.get()).isPresent()) {
            level.getBlockEntity(blockPos, MarvelBlockEntityTypes.MARK_38_CHARGER.get()).get().getExtraBlocks().forEach((part, pos) -> {
                level.destroyBlock(pos, !player.isCreative());
            });
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos pos1, boolean p_300890_) {
        if (!level.isClientSide) {
            boolean flag = hasNeighborSignals(level, getAllPlacementPositions(pos, state.getValue(FACING)));
            if (flag != state.getValue(POWERED)) {
                level.setBlockAndUpdate(pos, state.setValue(POWERED, flag));
                level.setBlockAndUpdate(getExtraPlacementPositions(pos, state.getValue(FACING))[0], level.getBlockState(getExtraPlacementPositions(pos, state.getValue(FACING))[0]).setValue(POWERED, flag));
                level.setBlockAndUpdate(getExtraPlacementPositions(pos, state.getValue(FACING))[1], level.getBlockState(getExtraPlacementPositions(pos, state.getValue(FACING))[1]).setValue(POWERED, flag));
                level.setBlockAndUpdate(getExtraPlacementPositions(pos, state.getValue(FACING))[2], level.getBlockState(getExtraPlacementPositions(pos, state.getValue(FACING))[2]).setValue(POWERED, flag));
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
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new Mark38ChargerBlockEntity(blockPos, blockState);
    }

    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> blockEntityType, BlockEntityType<E> blockEntityType2, BlockEntityTicker<? super E> blockEntityTicker) {
        return blockEntityType2 == blockEntityType ? (BlockEntityTicker<A>) blockEntityTicker : null;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, MarvelBlockEntityTypes.MARK_38_CHARGER.get(), Mark38ChargerBlockEntity::tick);
    }
}
