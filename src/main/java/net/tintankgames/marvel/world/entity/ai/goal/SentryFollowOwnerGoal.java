package net.tintankgames.marvel.world.entity.ai.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.pathfinder.PathType;
import net.tintankgames.marvel.world.entity.VeronicaSentry;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class SentryFollowOwnerGoal extends Goal {
    private final VeronicaSentry sentry;
    @Nullable
    private LivingEntity owner;
    private final double speedModifier;
    private int timeToRecalcPath;
    private final float stopDistance;
    private final float startDistance;
    private float oldWaterCost;

    public SentryFollowOwnerGoal(VeronicaSentry sentry, double speedModifier, float startDistance, float stopDistance) {
        this.sentry = sentry;
        this.speedModifier = speedModifier;
        this.startDistance = startDistance;
        this.stopDistance = stopDistance;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity livingentity = this.sentry.getOwner();
        if (livingentity == null) {
            return false;
        } else if (this.sentry.unableToMoveToOwner()) {
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
            return !this.sentry.unableToMoveToOwner() && !(this.sentry.distanceToSqr(this.owner) <= (double) (this.stopDistance * this.stopDistance));
        }
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
        boolean flag = this.sentry.shouldTryTeleportToOwner();
        if (!flag) {
            this.sentry.getLookControl().setLookAt(this.owner, 10.0F, (float)this.sentry.getMaxHeadXRot());
        }

        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10);
            if (flag) {
                this.sentry.tryToTeleportToOwner();
            } else {
                this.sentry.getNavigation().moveTo(this.owner, this.speedModifier);
            }
        }
    }
}
