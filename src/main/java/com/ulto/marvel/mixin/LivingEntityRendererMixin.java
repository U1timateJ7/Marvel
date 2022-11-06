package com.ulto.marvel.mixin;

import com.ulto.marvel.client.renderer.layer.GlowingHumanoidArmorLayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> implements RenderLayerParent<T, M> {
    @Shadow public abstract boolean addLayer(RenderLayer<T, M> p_115327_);

    protected LivingEntityRendererMixin(EntityRendererProvider.Context p_174008_) {
        super(p_174008_);
    }

    @Inject(at = @At("RETURN"), method = "addLayer")
    private void addGlowLayer(RenderLayer<T, M> p_115327_, CallbackInfoReturnable<Boolean> cir) {
        if (p_115327_ instanceof HumanoidArmorLayer<?,?,?>) addLayer(new GlowingHumanoidArmorLayer<>((LivingEntityRenderer)(Object)this, new HumanoidModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR))));
    }
}
