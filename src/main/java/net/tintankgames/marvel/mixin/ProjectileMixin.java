package net.tintankgames.marvel.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.core.particles.EmissiveDustParticleOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Projectile.class)
public abstract class ProjectileMixin extends Entity {
    public ProjectileMixin(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    @Inject(at = @At("RETURN"), method = "tick")
    private void particlesForCharged(CallbackInfo ci) {
        if (level() instanceof ServerLevel serverLevel && getExistingData(MarvelAttachmentTypes.TESSERACT_CHARGED).orElse(false)) {
            serverLevel.sendParticles(new EmissiveDustParticleOptions(0x6DE6FC, 0.5F), getX(), getY(), getZ(), 1, 0, 0, 0, 0);
        }
    }
}
