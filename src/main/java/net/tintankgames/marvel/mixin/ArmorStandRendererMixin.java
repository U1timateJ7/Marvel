package net.tintankgames.marvel.mixin;

import net.minecraft.client.model.ArmorStandArmorModel;
import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.tintankgames.marvel.client.renderer.entity.layers.ItemOnBackLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorStandRenderer.class)
public abstract class ArmorStandRendererMixin extends LivingEntityRenderer<ArmorStand, ArmorStandArmorModel> {
    public ArmorStandRendererMixin(EntityRendererProvider.Context p_174289_, ArmorStandArmorModel p_174290_, float p_174291_) {
        super(p_174289_, p_174290_, p_174291_);
    }

    @Inject(at = @At("RETURN"), method = "<init>")
    private void addMarvelLayers(EntityRendererProvider.Context context, CallbackInfo ci) {
        this.addLayer(new ItemOnBackLayer<>(this, context.getItemRenderer()));
    }
}
