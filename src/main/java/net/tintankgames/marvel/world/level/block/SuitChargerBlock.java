package net.tintankgames.marvel.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SignalGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
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
    private static final VoxelShape NORTH_SHAPE = Shapes.or(Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.0625, 0.9375), Shapes.box(0.125, 0.0625, 0.125, 0.875, 0.1875, 0.875), Shapes.box(0.3125, 0.0625, 0.0625, 0.6875, 0.1875, 0.125));
    private static final VoxelShape SOUTH_SHAPE = Shapes.or(Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.0625, 0.9375), Shapes.box(0.125, 0.0625, 0.125, 0.875, 0.1875, 0.875), Shapes.box(0.3125, 0.0625, 0.875, 0.6875, 0.1875, 0.9375));
    private static final VoxelShape EAST_SHAPE = Shapes.or(Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.0625, 0.9375), Shapes.box(0.125, 0.0625, 0.125, 0.875, 0.1875, 0.875), Shapes.box(0.875, 0.0625, 0.3125, 0.9375, 0.1875, 0.6875));
    private static final VoxelShape WEST_SHAPE = Shapes.or(Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.0625, 0.9375), Shapes.box(0.125, 0.0625, 0.125, 0.875, 0.1875, 0.875), Shapes.box(0.0625, 0.0625, 0.3125, 0.125, 0.1875, 0.6875));

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
                    if (player.getInventory().contains(stack -> stack.is(MarvelItems.FLAMETHROWER))) player.getInventory().removeItem(player.getInventory().getItem(SuitItem.findSlotMatchingItem(player.getInventory().items, MarvelItems.FLAMETHROWER.get())));
                    if (player.getInventory().contains(stack -> stack.is(MarvelItems.REPULSOR))) player.getInventory().removeItem(player.getInventory().getItem(SuitItem.findSlotMatchingItem(player.getInventory().items, MarvelItems.REPULSOR.get())));
                    if (player.getInventory().contains(stack -> stack.is(MarvelItems.UNIBEAM))) player.getInventory().removeItem(player.getInventory().getItem(SuitItem.findSlotMatchingItem(player.getInventory().items, MarvelItems.UNIBEAM.get())));
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
                level.setBlock(pos, state.setValue(POWERED, flag), 2);
            }
        }
    }

    private boolean hasNeighborSignal(SignalGetter p_277378_, BlockPos p_60179_) {
        for (Direction direction : Direction.values()) {
            if (p_277378_.hasSignal(p_60179_.relative(direction), direction)) {
                return true;
            }
        }

        if (p_277378_.hasSignal(p_60179_, Direction.DOWN)) {
            return true;
        } else {
            BlockPos blockpos = p_60179_.above();

            for (Direction direction1 : Direction.values()) {
                if (p_277378_.hasSignal(blockpos.relative(direction1), direction1)) {
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
