package net.tintankgames.marvel.world.entity.projectile;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.tintankgames.marvel.core.particles.EmissiveDustParticleOptions;
import net.tintankgames.marvel.sounds.MarvelSoundEvents;
import net.tintankgames.marvel.world.damagesources.MarvelDamageTypes;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;

public class TesseractCharge extends Projectile {
    public TesseractCharge(EntityType<? extends TesseractCharge> entityType, Level level) {
        super(entityType, level);
        this.noCulling = true;
    }

    public TesseractCharge(Level p_338686_, double p_338771_, double p_338674_, double p_338477_) {
        super(MarvelEntityTypes.TESSERACT_CHARGE.get(), p_338686_);
        this.setPos(p_338771_, p_338674_, p_338477_);
    }

    public TesseractCharge(Level level, LivingEntity living) {
        this(MarvelEntityTypes.TESSERACT_CHARGE.get(), level);
        this.setOwner(living);
        this.setPos(living.getX(), living.getEyeY() - 0.1, living.getZ());
        this.setDeltaMovement(living.getViewVector(1.0F).scale(3.0));
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
    }

    public boolean shouldRenderAtSqrDistance(double d) {
        return true;
    }

    public void tick() {
        super.tick();
        if (getOwner() != null) {
            HitResult hitResult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
            if (hitResult.getType() != HitResult.Type.MISS) {
                this.onHit(hitResult);
                this.playSound(MarvelSoundEvents.RED_SKULL_HIT.get());
            }

            this.setPos(hitResult.getLocation());
            this.checkInsideBlocks();
        }
        if (level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(new EmissiveDustParticleOptions(0x6DE6FC, 1.0F), getX(), getY(), getZ(), 1, 0, 0, 0, 0);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        entityHitResult.getEntity().hurt(damageSources().source(MarvelDamageTypes.TESSERACT_ARROW, this, getOwner() == null ? this : getOwner()), 4.0F);
        discard();
    }

    protected void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        discard();
    }

    protected MovementEmission getMovementEmission() {
        return MovementEmission.NONE;
    }
}
