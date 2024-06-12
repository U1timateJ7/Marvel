package net.tintankgames.marvel.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.tintankgames.marvel.world.item.MarvelItems;

public class SpiderWebBlock extends Block implements SimpleWaterloggedBlock {
    public static final MapCodec<SpiderWebBlock> CODEC = simpleCodec(SpiderWebBlock::new);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    @Override
    public MapCodec<SpiderWebBlock> codec() {
        return CODEC;
    }

    public SpiderWebBlock(BlockBehaviour.Properties p_58178_) {
        super(p_58178_);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
    }

    @Override
    protected BlockState updateShape(BlockState p_54363_, Direction p_54364_, BlockState p_54365_, LevelAccessor p_54366_, BlockPos p_54367_, BlockPos p_54368_) {
        if (p_54363_.getValue(WATERLOGGED)) {
            p_54366_.scheduleTick(p_54367_, Fluids.WATER, Fluids.WATER.getTickDelay(p_54366_));
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
        return this.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(fluidstate.is(Fluids.WATER)));
    }

    @Override
    protected FluidState getFluidState(BlockState p_335518_) {
        return p_335518_.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_335518_);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_333925_) {
        p_333925_.add(BlockStateProperties.WATERLOGGED);
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
