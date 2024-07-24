package net.tintankgames.marvel.world.level;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class KineticExplosionDamageCalculator extends ExplosionDamageCalculator {
    private final float absorbed;
    @Nullable
    private final Entity source;

    public KineticExplosionDamageCalculator(float absorbed, @Nullable Entity source) {
        this.absorbed = absorbed;
        this.source = source;
    }

    @Override
    public boolean shouldBlockExplode(Explosion p_46094_, BlockGetter p_46095_, BlockPos p_46096_, BlockState p_46097_, float p_46098_) {
        return false;
    }

    @Override
    public boolean shouldDamageEntity(Explosion p_314652_, Entity p_314454_) {
        return p_314652_.getIndirectSourceEntity() != p_314454_ && super.shouldDamageEntity(p_314652_, p_314454_);
    }

    @Override
    public float getKnockbackMultiplier(Entity p_340973_) {
        int armor = 0;
        if (p_340973_ instanceof LivingEntity living) for (ItemStack stack : living.getArmorSlots()) if (stack.has(MarvelDataComponents.ABSORBED_DAMAGE)) armor++;
        return armor >= 4 || (source != null && p_340973_.getStringUUID().equals(source.getStringUUID())) ? 0 : absorbed < 0 ? 1.22F : super.getKnockbackMultiplier(p_340973_);
    }

    @Override
    public Optional<Float> getBlockExplosionResistance(Explosion p_46099_, BlockGetter p_46100_, BlockPos p_46101_, BlockState p_46102_, FluidState p_46103_) {
        return Optional.empty();
    }

    @Override
    public float getEntityDamageAmount(Explosion p_311793_, Entity p_311929_) {
        return absorbed < 0 ? super.getEntityDamageAmount(p_311793_, p_311929_) / 8 : absorbed;
    }
}
