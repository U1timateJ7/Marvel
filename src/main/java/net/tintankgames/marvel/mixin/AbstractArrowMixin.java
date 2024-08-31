package net.tintankgames.marvel.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin extends Projectile {
    protected AbstractArrowMixin(EntityType<? extends Projectile> p_37248_, Level p_37249_) {
        super(p_37248_, p_37249_);
    }

    @Inject(at = @At("HEAD"), method = "isCritArrow", cancellable = true)
    private void removeCritParticles(CallbackInfoReturnable<Boolean> cir) {
        if (getData(MarvelAttachmentTypes.TESSERACT_CHARGED)) {
            cir.setReturnValue(false);
        }
    }
}
