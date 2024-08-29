package net.tintankgames.marvel.mixin;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.world.damagesources.MarvelDamageTypes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DamageSources.class)
public abstract class DamageSourcesMixin {
    @Shadow @Final public Registry<DamageType> damageTypes;

    @Inject(at = @At("HEAD"), method = "source(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/world/damagesource/DamageSource;", cancellable = true)
    private void makeTesseractArrow(ResourceKey<DamageType> oldType, Entity directSource, Entity source, CallbackInfoReturnable<DamageSource> cir) {
        if (oldType == DamageTypes.ARROW && directSource instanceof AbstractArrow arrow && arrow.getExistingData(MarvelAttachmentTypes.TESSERACT_CHARGED).orElse(false)) {
            cir.setReturnValue(new DamageSource(damageTypes.getHolderOrThrow(MarvelDamageTypes.TESSERACT_ARROW), directSource, source));
        } else if (oldType == DamageTypes.FIREWORKS && directSource instanceof FireworkRocketEntity fireworkRocket && fireworkRocket.getExistingData(MarvelAttachmentTypes.TESSERACT_CHARGED).orElse(false)) {
            cir.setReturnValue(new DamageSource(damageTypes.getHolderOrThrow(MarvelDamageTypes.TESSERACT_FIREWORKS), directSource, source));
        }
    }
}
