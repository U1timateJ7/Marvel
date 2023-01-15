package com.ulto.marvel.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.ulto.marvel.client.renderer.layer.GlowingHumanoidArmorLayer;
import com.ulto.marvel.world.entity.SentryMode;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.ArrowLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class SentryModeRenderer extends HumanoidMobRenderer<SentryMode, PlayerModel<SentryMode>> {
	public SentryModeRenderer(EntityRendererProvider.Context context) {
		super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), false), 0.5f);
		this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
				new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR))));
		this.addLayer(new GlowingHumanoidArmorLayer<>(this, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
				new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR))));
		this.addLayer(new ArrowLayer<>(context, this));
	}

	@Override
	protected void scale(@NotNull SentryMode p_117798_, PoseStack p_117799_, float p_117800_) {
		p_117799_.scale(0.9375F, 0.9375F, 0.9375F);
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull SentryMode entity) {
		return new ResourceLocation("marvel:textures/empty.png");
	}
}
