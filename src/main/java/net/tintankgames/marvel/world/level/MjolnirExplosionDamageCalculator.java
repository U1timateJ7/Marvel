package net.tintankgames.marvel.world.level;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;

public class MjolnirExplosionDamageCalculator extends ExplosionDamageCalculator {
    private final Entity target;

    public MjolnirExplosionDamageCalculator(Entity target) {
        this.target = target;
    }

    @Override
    public boolean shouldDamageEntity(Explosion p_314652_, Entity p_314454_) {
        return p_314454_ != target && super.shouldDamageEntity(p_314652_, p_314454_);
    }

    @Override
    public float getKnockbackMultiplier(Entity p_340973_) {
        return p_340973_ == target ? 0 : super.getKnockbackMultiplier(p_340973_);
    }

    @Override
    public float getEntityDamageAmount(Explosion p_311793_, Entity p_311929_) {
        return p_311929_ == target ? 0 : super.getEntityDamageAmount(p_311793_, p_311929_) / 5;
    }
}
