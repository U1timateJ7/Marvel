package net.tintankgames.marvel.world.level;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

import java.util.Optional;

public class OpticBlastExplosionDamageCalculator extends ExplosionDamageCalculator {
    @Override
    public Optional<Float> getBlockExplosionResistance(Explosion p_46099_, BlockGetter p_46100_, BlockPos p_46101_, BlockState p_46102_, FluidState p_46103_) {
        Optional<Float> resistance = super.getBlockExplosionResistance(p_46099_, p_46100_, p_46101_, p_46102_, p_46103_);
        if (resistance.isPresent() && resistance.get() < 100) {
            resistance = Optional.of(Blocks.DIRT.getExplosionResistance(p_46102_, p_46100_, p_46101_, p_46099_));
        }
        return resistance;
    }

    @Override
    public boolean shouldDamageEntity(Explosion p_314652_, Entity p_314454_) {
        return p_314652_.radius() > 0;
    }
}
