package net.tintankgames.marvel.world.entity.ai.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.tintankgames.marvel.world.entity.IronManSentry;

import java.util.EnumSet;

public class SentryFollowOwnerGoal extends Goal {
    private final IronManSentry sentry;
    private LivingEntity owner;
    private final LevelReader level;
    private final double speedModifier;
    private int timeToRecalcPath;
    private final float stopDistance;
    private final float startDistance;
    private float oldWaterCost;
    private final boolean canFly;

    public SentryFollowOwnerGoal(IronManSentry p_25294_, double p_25295_, float p_25296_, float p_25297_, boolean p_25298_) {
        this.sentry = p_25294_;
        this.level = p_25294_.level();
        this.speedModifier = p_25295_;
        this.startDistance = p_25296_;
        this.stopDistance = p_25297_;
        this.canFly = p_25298_;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity livingentity = this.sentry.getOwner();
        if (livingentity == null) {
            return false;
        } else if (livingentity.isSpectator()) {
            return false;
        } else if (this.unableToMove()) {
            return false;
        } else if (this.sentry.distanceToSqr(livingentity) < (double)(this.startDistance * this.startDistance)) {
            return false;
        } else {
            this.owner = livingentity;
            return true;
        }
    }

    @Override
    public boolean canContinueToUse() {
        if (this.sentry.getNavigation().isDone()) {
            return false;
        } else {
            return this.unableToMove() ? false : !(this.sentry.distanceToSqr(this.owner) <= (double)(this.stopDistance * this.stopDistance));
        }
    }

    private boolean unableToMove() {
        return this.sentry.isOrderedToSit() || this.sentry.isPassenger() || this.sentry.mayBeLeashed();
    }

    @Override
    public void start() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.sentry.getPathfindingMalus(PathType.WATER);
        this.sentry.setPathfindingMalus(PathType.WATER, 0.0F);
    }

    @Override
    public void stop() {
        this.owner = null;
        this.sentry.getNavigation().stop();
        this.sentry.setPathfindingMalus(PathType.WATER, this.oldWaterCost);
    }

    @Override
    public void tick() {
        this.sentry.getLookControl().setLookAt(this.owner, 10.0F, (float)this.sentry.getMaxHeadXRot());
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10);
            if (this.sentry.distanceToSqr(this.owner) >= (this.sentry.getIsFlying() ? 768.0 : 144.0)) {
                if (this.sentry.getIsFlying()) this.teleportToOwnerFlying();
                else this.teleportToOwner();
            } else {
                this.sentry.getNavigation().moveTo(this.owner, this.speedModifier);
            }
        }
    }

    private void teleportToOwner() {
        BlockPos blockpos = this.owner.blockPosition();

        for (int i = 0; i < 10; i++) {
            int j = this.randomIntInclusive(-3, 3);
            int k = this.randomIntInclusive(-1, 1);
            int l = this.randomIntInclusive(-3, 3);
            boolean flag = this.maybeTeleportTo(blockpos.getX() + j, blockpos.getY() + k, blockpos.getZ() + l);
            if (flag) {
                return;
            }
        }
    }

    private boolean maybeTeleportTo(int p_25304_, int p_25305_, int p_25306_) {
        if (Math.abs((double)p_25304_ - this.owner.getX()) < 2.0 && Math.abs((double)p_25306_ - this.owner.getZ()) < 2.0) {
            return false;
        } else if (!this.canTeleportTo(new BlockPos(p_25304_, p_25305_, p_25306_))) {
            return false;
        } else {
            this.sentry.moveTo((double)p_25304_ + 0.5, (double)p_25305_, (double)p_25306_ + 0.5, this.sentry.getYRot(), this.sentry.getXRot());
            this.sentry.getNavigation().stop();
            return true;
        }
    }

    private boolean canTeleportTo(BlockPos p_25308_) {
        PathType pathtype = WalkNodeEvaluator.getPathTypeStatic(this.sentry, p_25308_);
        if (pathtype != PathType.WALKABLE) {
            return false;
        } else {
            BlockState blockstate = this.level.getBlockState(p_25308_.below());
            if (!this.canFly && blockstate.getBlock() instanceof LeavesBlock) {
                return false;
            } else {
                BlockPos blockpos = p_25308_.subtract(this.sentry.blockPosition());
                return this.level.noCollision(this.sentry, this.sentry.getBoundingBox().move(blockpos));
            }
        }
    }

    private void teleportToOwnerFlying() {
        BlockPos blockpos = this.owner.blockPosition();

        for (int i = 0; i < 10; i++) {
            int j = this.randomIntInclusive(-3, 3);
            int k = this.randomIntInclusive(-1, 1);
            int l = this.randomIntInclusive(-3, 3);
            boolean flag = this.maybeTeleportToFlying(blockpos.getX() + j, blockpos.getY() + k, blockpos.getZ() + l);
            if (flag) {
                return;
            }
        }
    }

    private boolean maybeTeleportToFlying(int p_350930_, int p_350303_, int p_350410_) {
        if (!this.canTeleportToFlying(new BlockPos(p_350930_, p_350303_, p_350410_))) {
            return false;
        } else {
            this.sentry.moveTo((double)p_350930_ + 0.5, p_350303_, (double)p_350410_ + 0.5, this.sentry.getYRot(), this.sentry.getXRot());
            this.sentry.getNavigation().stop();
            return true;
        }
    }

    private boolean canTeleportToFlying(BlockPos p_350767_) {
        BlockPos blockpos = p_350767_.subtract(this.sentry.blockPosition());
        return this.sentry.level().noCollision(this.sentry, this.sentry.getBoundingBox().move(blockpos));
    }

    private int randomIntInclusive(int p_25301_, int p_25302_) {
        return this.sentry.getRandom().nextInt(p_25302_ - p_25301_ + 1) + p_25301_;
    }
}
