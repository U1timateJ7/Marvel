package net.tintankgames.marvel.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.tintankgames.marvel.world.item.MarvelItems;

import org.jetbrains.annotations.Nullable;
import java.util.Optional;

public class SpiderWebBlock extends Block implements BucketPickup, LiquidBlockContainer {
    public static final MapCodec<SpiderWebBlock> CODEC = simpleCodec(SpiderWebBlock::new);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty LAVALOGGED = BooleanProperty.create("lavalogged");

    @Override
    public MapCodec<SpiderWebBlock> codec() {
        return CODEC;
    }

    public SpiderWebBlock(BlockBehaviour.Properties p_58178_) {
        super(p_58178_);
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
    protected void onPlace(BlockState p_60566_, Level p_60567_, BlockPos p_60568_, BlockState p_60569_, boolean p_60570_) {
        super.onPlace(p_60566_, p_60567_, p_60568_, p_60569_, p_60570_);
        p_60567_.scheduleTick(p_60568_, this, 200);
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
    protected void tick(BlockState p_222945_, ServerLevel p_222946_, BlockPos p_222947_, RandomSource p_222948_) {
        super.tick(p_222945_, p_222946_, p_222947_, p_222948_);
        p_222946_.removeBlock(p_222947_, false);
    }

    @Override
    protected void entityInside(BlockState p_58180_, Level p_58181_, BlockPos p_58182_, Entity p_58183_) {
        Vec3 vec3 = new Vec3(0.25, 0.05F, 0.25);
        if (p_58183_ instanceof LivingEntity livingentity && livingentity.hasEffect(MobEffects.WEAVING)) {
            vec3 = new Vec3(0.5, 0.25, 0.5);
        }

        if (!(p_58183_ instanceof LivingEntity) || (p_58183_ instanceof LivingEntity living && !(living.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.SPIDER_MAN_ARMOR) && living.getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.SPIDER_MAN_ARMOR) && living.getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.SPIDER_MAN_ARMOR)))) p_58183_.makeStuckInBlock(p_58180_, vec3);
    }
}
