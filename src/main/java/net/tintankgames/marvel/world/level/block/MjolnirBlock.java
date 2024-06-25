package net.tintankgames.marvel.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tintankgames.marvel.sounds.MarvelSoundEvents;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.level.block.entity.MarvelBlockEntityTypes;
import net.tintankgames.marvel.world.level.block.entity.MjolnirBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MjolnirBlock extends FallingBlock implements EntityBlock, BucketPickup, LiquidBlockContainer {
    public static final MapCodec<MjolnirBlock> CODEC = simpleCodec(MjolnirBlock::new);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty LAVALOGGED = BooleanProperty.create("lavalogged");
    private static final VoxelShape SHAPE = Shapes.or(Shapes.box(0.46875, 0.328125, 0.46875, 0.53125, 0.828125, 0.5312), Shapes.box(0.459375, 0.7875, 0.459375, 0.540625, 0.86875, 0.540625), Shapes.box(0.40625, 0, 0.40625, 0.59375, 0.0625, 0.59375), Shapes.box(0.34375, 0.015625, 0.28125, 0.65625, 0.328125, 0.71875), Shapes.box(0.375, 0.046875, 0.6875, 0.625, 0.296875, 0.75), Shapes.box(0.375, 0.046875, 0.25, 0.625, 0.296875, 0.3125), Shapes.box(0.40625, 0.078125, 0.234375, 0.59375, 0.265625, 0.296875), Shapes.box(0.40625, 0.078125, 0.703125, 0.59375, 0.265625, 0.765625));

    public MjolnirBlock(Properties p_53205_) {
        super(p_53205_);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(LAVALOGGED, false));
    }

    @Override
    public boolean canPlaceLiquid(@Nullable Player p_294196_, BlockGetter p_56301_, BlockPos p_56302_, BlockState p_56303_, Fluid p_56304_) {
        return p_56304_ == Fluids.WATER || p_56304_ == Fluids.LAVA;
    }

    @Override
    public boolean placeLiquid(LevelAccessor p_56306_, BlockPos p_56307_, BlockState p_56308_, FluidState p_56309_) {
        if (!p_56308_.getValue(WATERLOGGED) && p_56309_.getType() == Fluids.WATER) {
            if (!p_56306_.isClientSide()) {
                p_56306_.setBlock(p_56307_, p_56308_.setValue(WATERLOGGED, true), 3);
                p_56306_.scheduleTick(p_56307_, p_56309_.getType(), p_56309_.getType().getTickDelay(p_56306_));
            }

            return true;
        } else if (!p_56308_.getValue(LAVALOGGED) && p_56309_.getType() == Fluids.LAVA) {
            if (!p_56306_.isClientSide()) {
                p_56306_.setBlock(p_56307_, p_56308_.setValue(LAVALOGGED, true), 3);
                p_56306_.scheduleTick(p_56307_, p_56309_.getType(), p_56309_.getType().getTickDelay(p_56306_));
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public ItemStack pickupBlock(@Nullable Player p_294786_, LevelAccessor p_154560_, BlockPos p_154561_, BlockState p_154562_) {
        if (p_154562_.getValue(WATERLOGGED)) {
            p_154560_.setBlock(p_154561_, p_154562_.setValue(WATERLOGGED, false), 3);
            if (!p_154562_.canSurvive(p_154560_, p_154561_)) {
                p_154560_.destroyBlock(p_154561_, true);
            }

            return new ItemStack(Items.WATER_BUCKET);
        } else if (p_154562_.getValue(LAVALOGGED)) {
            p_154560_.setBlock(p_154561_, p_154562_.setValue(LAVALOGGED, false), 3);
            if (!p_154562_.canSurvive(p_154560_, p_154561_)) {
                p_154560_.destroyBlock(p_154561_, true);
            }

            return new ItemStack(Items.LAVA_BUCKET);
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public Optional<SoundEvent> getPickupSound(BlockState state) {
        return state.getValue(LAVALOGGED) ? Fluids.LAVA.getPickupSound() : Fluids.WATER.getPickupSound();
    }

    @Override
    public Optional<SoundEvent> getPickupSound() {
        return Fluids.WATER.getPickupSound();
    }

    @Override
    protected BlockState updateShape(BlockState p_54363_, Direction p_54364_, BlockState p_54365_, LevelAccessor p_54366_, BlockPos p_54367_, BlockPos p_54368_) {
        if (p_54363_.getValue(WATERLOGGED)) {
            p_54366_.scheduleTick(p_54367_, Fluids.WATER, Fluids.WATER.getTickDelay(p_54366_));
        } else if (p_54363_.getValue(LAVALOGGED)) {
            p_54366_.scheduleTick(p_54367_, Fluids.LAVA, Fluids.LAVA.getTickDelay(p_54366_));
        }
        return super.updateShape(p_54363_, p_54364_, p_54365_, p_54366_, p_54367_, p_54368_);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_338691_) {
        FluidState fluidstate = p_338691_.getLevel().getFluidState(p_338691_.getClickedPos());
        return this.defaultBlockState().setValue(WATERLOGGED, fluidstate.is(Fluids.WATER)).setValue(LAVALOGGED, fluidstate.is(Fluids.LAVA));
    }

    @Override
    protected FluidState getFluidState(BlockState p_335518_) {
        return p_335518_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : p_335518_.getValue(LAVALOGGED) ? Fluids.LAVA.getSource(false) : super.getFluidState(p_335518_);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_333925_) {
        p_333925_.add(WATERLOGGED, LAVALOGGED);
    }

    @Override
    protected MapCodec<MjolnirBlock> codec() {
        return CODEC;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState p_60503_, Level p_60504_, BlockPos p_60505_, Player p_60506_, BlockHitResult p_60508_) {
        p_60504_.getBlockEntity(p_60505_, MarvelBlockEntityTypes.MJOLNIR.get()).ifPresent(blockEntity -> {
            if ((Objects.equals(blockEntity.getOwner().toString(), p_60506_.getUUID().toString()) || blockEntity.getOwner() == null) && p_60506_.getMainHandItem().isEmpty()) {
                p_60506_.setItemInHand(InteractionHand.MAIN_HAND, blockEntity.getStack().copy());
                p_60504_.setBlockAndUpdate(p_60505_, p_60504_.getFluidState(p_60505_).createLegacyBlock());
                p_60504_.playSound(null, p_60505_, MarvelSoundEvents.MJOLNIR_CALL.get(), SoundSource.BLOCKS);
            }
        });
        return InteractionResult.sidedSuccess(p_60504_.isClientSide);
    }

    @Override
    protected List<ItemStack> getDrops(BlockState p_287732_, LootParams.Builder p_287596_) {
        ItemStack stack = new ItemStack(MarvelItems.MJOLNIR.get());
        if (p_287596_.getParameter(LootContextParams.BLOCK_ENTITY) instanceof MjolnirBlockEntity blockEntity) {
            stack = blockEntity.getStack().copy();
        }
        return List.of(stack);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new MjolnirBlockEntity(p_153215_, p_153216_);
    }

    @Override
    protected void tick(BlockState p_221124_, ServerLevel p_221125_, BlockPos p_221126_, RandomSource p_221127_) {
        if (isFree(p_221125_.getBlockState(p_221126_.below())) && p_221126_.getY() >= p_221125_.getMinBuildHeight()) {
            CompoundTag blockData = null;
            if (p_221125_.getBlockEntity(p_221126_, MarvelBlockEntityTypes.MJOLNIR.get()).isPresent()) {
                blockData = p_221125_.getBlockEntity(p_221126_, MarvelBlockEntityTypes.MJOLNIR.get()).get().saveCustomOnly(p_221125_.registryAccess());
            }
            FallingBlockEntity fallingblockentity = FallingBlockEntity.fall(p_221125_, p_221126_, p_221124_.hasProperty(LAVALOGGED) ? p_221124_.setValue(LAVALOGGED, false) : p_221124_);
            fallingblockentity.blockData = blockData;
            this.falling(fallingblockentity);
        }
    }

    @Override
    protected VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return SHAPE;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> p_153214_) {
        return createTickerHelper(p_153214_, MarvelBlockEntityTypes.MJOLNIR.get(), MjolnirBlockEntity::tick);
    }

    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> p_152133_, BlockEntityType<E> p_152134_, BlockEntityTicker<? super E> p_152135_) {
        return p_152134_ == p_152133_ ? (BlockEntityTicker<A>)p_152135_ : null;
    }
}
