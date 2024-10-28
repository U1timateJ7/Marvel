package net.tintankgames.marvel.world.entity.ai.control;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.tintankgames.marvel.world.entity.IronManSentry;

public class SentryMoveControl extends MoveControl {
    private final IronManSentry sentry;
    private final int maxTurn;
    private final boolean hoversInPlace;

    public SentryMoveControl(IronManSentry sentry, int p_24894_, boolean p_24895_) {
        super(sentry);
        this.sentry = sentry;
        this.maxTurn = p_24894_;
        this.hoversInPlace = p_24895_;
    }

    @Override
    public void tick() {
        if (sentry.getIsFlying()) {
            if (this.operation == Operation.MOVE_TO) {
                this.operation = Operation.WAIT;
                this.mob.setNoGravity(true);
                double d0 = this.wantedX - this.mob.getX();
                double d1 = this.wantedY - this.mob.getY();
                double d2 = this.wantedZ - this.mob.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if (d3 < 2.5000003E-7F) {
                    this.mob.setYya(0.0F);
                    this.mob.setZza(0.0F);
                    return;
                }

                float f = (float)(Mth.atan2(d2, d0) * 180.0F / (float)Math.PI) - 90.0F;
                this.mob.setYRot(this.rotlerp(this.mob.getYRot(), f, 90.0F));
                float f1;
                if (this.mob.onGround()) {
                    f1 = (float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
                } else {
                    f1 = (float)(this.speedModifier * this.mob.getAttributeValue(Attributes.FLYING_SPEED));
                }

                this.mob.setSpeed(f1);
                double d4 = Math.sqrt(d0 * d0 + d2 * d2);
                if (Math.abs(d1) > 1.0E-5F || Math.abs(d4) > 1.0E-5F) {
                    float f2 = (float)(-(Mth.atan2(d1, d4) * 180.0F / (float)Math.PI));
                    this.mob.setXRot(this.rotlerp(this.mob.getXRot(), f2, (float)this.maxTurn));
                    if (Math.abs(d1) > 0.5F) {
                        this.mob.setYya(d1 > 0.0 ? f1 : -f1);
                    } else {
                        this.mob.setYya(0.0F);
                    }
                }
            } else {
                if (!this.hoversInPlace) {
                    this.mob.setNoGravity(false);
                }

                this.mob.setYya(0.0F);
                this.mob.setZza(0.0F);
            }
        } else {
            super.tick();
        }
    }
}
