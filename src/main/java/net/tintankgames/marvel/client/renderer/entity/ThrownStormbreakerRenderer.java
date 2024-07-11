package net.tintankgames.marvel.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.client.model.MarvelModels;
import net.tintankgames.marvel.client.model.StormbreakerModel;
import net.tintankgames.marvel.world.entity.projectile.ThrownStormbreaker;

@OnlyIn(Dist.CLIENT)
public class ThrownStormbreakerRenderer extends EntityRenderer<ThrownStormbreaker> {
    private final StormbreakerModel model;

    public ThrownStormbreakerRenderer(EntityRendererProvider.Context p_174420_) {
        super(p_174420_);
        this.model = new StormbreakerModel(p_174420_.bakeLayer(MarvelModels.STORMBREAKER));
    }

    public void render(ThrownStormbreaker stormbreaker, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int light) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(g, stormbreaker.yRotO + stormbreaker.getExtraYRotO(), stormbreaker.getYRot() + stormbreaker.getExtraYRot())));
        poseStack.mulPose(Axis.XP.rotationDegrees((stormbreaker.returningToOwner() ? 1 : -1) * Mth.lerp(g, stormbreaker.xRotO, stormbreaker.getXRot()) - 90));
        VertexConsumer vertexConsumer = ItemRenderer.getFoilBufferDirect(multiBufferSource, this.model.renderType(getTextureLocation(stormbreaker)), false, stormbreaker.isFoil());
        this.model.renderToBuffer(poseStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(stormbreaker, f, g, poseStack, multiBufferSource, light);
    }

    @Override
    public ResourceLocation getTextureLocation(ThrownStormbreaker stormbreaker) {
        return MarvelSuperheroes.id("textures/entity/stormbreaker.png");
    }
}
