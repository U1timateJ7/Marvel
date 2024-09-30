package net.tintankgames.marvel.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.SignalGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tintankgames.marvel.stats.MarvelStats;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.SuitChargerItem;
import net.tintankgames.marvel.world.item.SuitItem;
import net.tintankgames.marvel.world.level.block.entity.MarvelBlockEntityTypes;
import net.tintankgames.marvel.world.level.block.entity.SuitChargerBlockEntity;
import org.jetbrains.annotations.Nullable;

public class SuitChargerBlock extends HorizontalDirectionalBlock implements EntityBlock {
    public static final MapCodec<SuitChargerBlock> CODEC = simpleCodec(SuitChargerBlock::new);
    public static final Component UPGRADING_TITLE = Component.translatable("container.suit_charger");
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    private static final VoxelShape NORTH_SHAPE = Shapes.or(Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.0625, 0.9375), Shapes.box(0.125, 0.0625, 0.125, 0.875, 0.1875, 0.875), Shapes.box(0.3125, 0.0625, 0.0625, 0.6875, 0.1875, 0.125), Shapes.box(0.3125, 0.1875, 0.4375, 0.6875, 0.875, 0.5625), Shapes.box(0.25, 0.875, 0.4375, 0.75, 1, 0.5625));
    private static final VoxelShape SOUTH_SHAPE = Shapes.or(Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.0625, 0.9375), Shapes.box(0.125, 0.0625, 0.125, 0.875, 0.1875, 0.875), Shapes.box(0.3125, 0.0625, 0.875, 0.6875, 0.1875, 0.9375), Shapes.box(0.3125, 0.1875, 0.4375, 0.6875, 0.875, 0.5625), Shapes.box(0.25, 0.875, 0.4375, 0.75, 1, 0.5625));
    private static final VoxelShape EAST_SHAPE = Shapes.or(Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.0625, 0.9375), Shapes.box(0.125, 0.0625, 0.125, 0.875, 0.1875, 0.875), Shapes.box(0.875, 0.0625, 0.3125, 0.9375, 0.1875, 0.6875), Shapes.box(0.4375, 0.1875, 0.3125, 0.5625, 0.875, 0.6875), Shapes.box(0.4375, 0.875, 0.25, 0.5625, 1, 0.75));
    private static final VoxelShape WEST_SHAPE = Shapes.or(Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.0625, 0.9375), Shapes.box(0.125, 0.0625, 0.125, 0.875, 0.1875, 0.875), Shapes.box(0.0625, 0.0625, 0.3125, 0.125, 0.1875, 0.6875), Shapes.box(0.4375, 0.1875, 0.3125, 0.5625, 0.875, 0.6875), Shapes.box(0.4375, 0.875, 0.25, 0.5625, 1, 0.75));

    @Override
    public MapCodec<SuitChargerBlock> codec() {
        return CODEC;
    }

    public SuitChargerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(POWERED, false));
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos blockPos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case SOUTH -> SOUTH_SHAPE;
            case EAST -> EAST_SHAPE;
            case WEST -> WEST_SHAPE;
            default -> NORTH_SHAPE;
        };
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (player.isSecondaryUseActive() && blockEntity instanceof SuitChargerBlockEntity charger) {
            boolean playerHasNoSuit = true;
            boolean chargerHasNoSuit = true;
            boolean playerHasFullSuit = player.getInventory().armor.get(0).getItem() instanceof SuitChargerItem && player.getInventory().armor.get(1).getItem() instanceof SuitChargerItem && player.getInventory().armor.get(2).getItem() instanceof SuitChargerItem && player.getInventory().armor.get(3).getItem() instanceof SuitChargerItem;
            boolean chargerHasFullSuit = charger.getItem(0).getItem() instanceof SuitChargerItem && charger.getItem(1).getItem() instanceof SuitChargerItem && charger.getItem(2).getItem() instanceof SuitChargerItem && charger.getItem(3).getItem() instanceof SuitChargerItem;
            for (ItemStack armor : player.getInventory().armor) {
                if (!armor.isEmpty()) {
                    playerHasNoSuit = false;
                    break;
                }
            }
            for (ItemStack armor : charger.items()) {
                if (!armor.isEmpty()) {
                    chargerHasNoSuit = false;
                    break;
                }
            }
            if ((playerHasNoSuit || playerHasFullSuit) && (chargerHasNoSuit || chargerHasFullSuit)) {
                if (!level.isClientSide) {
                    NonNullList<ItemStack> chargerContents = charger.items();
                    NonNullList<ItemStack> playerArmor = NonNullList.copyOf(player.getInventory().armor);
                    for (int i = 0; i < player.getInventory().armor.size(); i++) {
                        player.getInventory().armor.set(i, chargerContents.get(i).copy());
                    }
                    for (int i = 0; i < charger.getContainerSize(); i++) {
                        charger.setItem(i, playerArmor.get(i).copy());
                    }
                }
                return InteractionResult.sidedSuccess(level.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        } else {
            MenuProvider provider = state.getMenuProvider(level, pos);
            if (!level.isClientSide && provider != null) {
                player.openMenu(provider);
                player.awardStat(MarvelStats.INTERACT_WITH_SUIT_CHARGER.get());
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (player.isSecondaryUseActive() && blockEntity instanceof SuitChargerBlockEntity charger) {
            boolean playerHasNoSuit = true;
            boolean chargerHasNoSuit = true;
            boolean playerHasFullSuit = player.getInventory().armor.get(0).getItem() instanceof SuitChargerItem && player.getInventory().armor.get(1).getItem() instanceof SuitChargerItem && player.getInventory().armor.get(2).getItem() instanceof SuitChargerItem && player.getInventory().armor.get(3).getItem() instanceof SuitChargerItem;
            boolean chargerHasFullSuit = charger.getItem(0).getItem() instanceof SuitChargerItem && charger.getItem(1).getItem() instanceof SuitChargerItem && charger.getItem(2).getItem() instanceof SuitChargerItem && charger.getItem(3).getItem() instanceof SuitChargerItem;
            for (ItemStack armor : player.getInventory().armor) {
                if (!armor.isEmpty()) {
                    playerHasNoSuit = false;
                    break;
                }
            }
            for (ItemStack armor : charger.items()) {
                if (!armor.isEmpty()) {
                    chargerHasNoSuit = false;
                    break;
                }
            }
            if ((playerHasNoSuit || playerHasFullSuit) && (chargerHasNoSuit || chargerHasFullSuit)) {
                if (!level.isClientSide) {
                    NonNullList<ItemStack> chargerContents = charger.items();
                    NonNullList<ItemStack> playerArmor = NonNullList.copyOf(player.getInventory().armor);
                    for (int i = 0; i < player.getInventory().armor.size(); i++) {
                        player.getInventory().armor.set(i, chargerContents.get(i).copy());
                    }
                    for (int i = 0; i < charger.getContainerSize(); i++) {
                        charger.setItem(i, playerArmor.get(i).copy());
                    }
                }
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
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
            boolean flag = hasNeighborSignal(level, pos);
            if (flag != state.getValue(POWERED)) {
                level.setBlockAndUpdate(pos, state.setValue(POWERED, flag));
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
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState state1, boolean bl) {
        Containers.dropContentsOnDestroy(state, state1, level, pos);
        super.onRemove(state, level, pos, state1, bl);
    }

    @Override
    public void destroy(LevelAccessor levelAccessor, BlockPos pos, BlockState state) {
        if (levelAccessor.getBlockState(pos.above()).is(MarvelBlocks.SUIT_CHARGER_UPPER)) levelAccessor.removeBlock(pos.above(), true);
        super.destroy(levelAccessor, pos, state);
    }

    @Override
    protected boolean isPathfindable(BlockState p_60475_, PathComputationType p_60478_) {
        return false;
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor levelAccessor, BlockPos pos, BlockPos pos1) {
        if (direction == Direction.UP && !state1.is(MarvelBlocks.SUIT_CHARGER_UPPER)) return Blocks.AIR.defaultBlockState();
        else return super.updateShape(state, direction, state1, levelAccessor, pos, pos1);
    }

    @Override
    public void setPlacedBy(Level p_52749_, BlockPos p_52750_, BlockState p_52751_, LivingEntity p_52752_, ItemStack p_52753_) {
        p_52749_.setBlock(p_52750_.above(), MarvelBlocks.SUIT_CHARGER_UPPER.get().defaultBlockState().setValue(SuitChargerUpperBlock.FACING, p_52751_.getValue(FACING)).setValue(SuitChargerUpperBlock.POWERED, p_52751_.getValue(POWERED)), 3);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SuitChargerBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, MarvelBlockEntityTypes.SUIT_CHARGER.get(), SuitChargerBlockEntity::tick);
    }

    @Override
    protected boolean triggerEvent(BlockState state, Level level, BlockPos pos, int id, int data) {
        super.triggerEvent(state, level, pos, id, data);
        BlockEntity blockentity = level.getBlockEntity(pos);
        return blockentity != null && blockentity.triggerEvent(id, data);
    }

    @Nullable
    @Override
    protected MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        BlockEntity blockentity = level.getBlockEntity(pos);
        return blockentity instanceof MenuProvider menuProvider ? menuProvider : null;
    }

    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> type, BlockEntityType<E> type1, BlockEntityTicker<? super E> ticker) {
        return type1 == type ? (BlockEntityTicker<A>)ticker : null;
    }
}
