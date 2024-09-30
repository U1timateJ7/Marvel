package net.tintankgames.marvel.world.entity.projectile;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.tintankgames.marvel.world.entity.IronManSentry;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;

public class Repulsor extends Projectile {
    public Repulsor(EntityType<? extends Repulsor> entityType, Level level) {
        super(entityType, level);
        this.noCulling = true;
    }

    public Repulsor(Level p_338686_, double p_338771_, double p_338674_, double p_338477_) {
        super(MarvelEntityTypes.REPULSOR.get(), p_338686_);
        this.setPos(p_338771_, p_338674_, p_338477_);
    }

    public Repulsor(Level level, LivingEntity living) {
        this(MarvelEntityTypes.REPULSOR.get(), level);
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
            }

            this.setPos(hitResult.getLocation());
            this.checkInsideBlocks();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        if (entityHitResult.getEntity() instanceof IronManSentry target && getOwner() instanceof IronManSentry owner && target.getOwnerUUID() != null && owner.getOwnerUUID() != null) {
            if (!target.getOwnerUUID().toString().equals(owner.getOwnerUUID().toString())) entityHitResult.getEntity().hurt(damageSources().source(DamageTypes.ARROW, this, getOwner() == null ? this : getOwner()), 8.0F);
        } else {
            if ((getOwner() instanceof IronManSentry owner && owner.getOwner() != entityHitResult.getEntity()) || !(getOwner() instanceof IronManSentry)) entityHitResult.getEntity().hurt(damageSources().source(DamageTypes.ARROW, this, getOwner() == null ? this : getOwner()), 8.0F);
        }
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
