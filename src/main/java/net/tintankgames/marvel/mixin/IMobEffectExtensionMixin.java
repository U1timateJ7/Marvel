package net.tintankgames.marvel.mixin;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.neoforged.neoforge.common.EffectCure;
import net.neoforged.neoforge.common.extensions.IMobEffectExtension;
import net.tintankgames.marvel.world.item.DeadpoolSuitItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

@Mixin(IMobEffectExtension.class)
public interface IMobEffectExtensionMixin {
    @Shadow
    private MobEffect self() {
        return (MobEffect) this;
    }

    @Inject(at = @At("RETURN"), method = "fillEffectCures")
    private void fillDeadpoolCures(Set<EffectCure> cures, MobEffectInstance effectInstance, CallbackInfo ci) {
        if (self().getCategory() == MobEffectCategory.HARMFUL) cures.add(DeadpoolSuitItem.DEADPOOL_CURE);
    }
}
