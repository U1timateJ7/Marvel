package net.tintankgames.marvel.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {
    protected PlayerMixin(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
    }

    @Inject(at = @At("TAIL"), method = "aiStep")
    private void grapple(CallbackInfo ci) {
        if (getData(MarvelAttachmentTypes.GRAPPLING.get()).entity != null && getData(MarvelAttachmentTypes.GRAPPLING.get()).entity.inBlock()) {
            this.resetFallDistance();
            if (this.isControlledByLocalInstance()) {
                Vec3 vec3 = getData(MarvelAttachmentTypes.GRAPPLING.get()).entity.position().subtract(this.getEyePosition());
                float g = getData(MarvelAttachmentTypes.GRAPPLING.get()).entity.length();
                double d = vec3.length();
                if (d > (double)g) {
                    double e = d / (double)g * 0.1;
                    this.addDeltaMovement(vec3.scale(1.0 / d).multiply(e, e * 1.1, e));
                }
            }
        }
    }
}
