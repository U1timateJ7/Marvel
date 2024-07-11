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
import net.tintankgames.marvel.client.model.MjolnirModel;
import net.tintankgames.marvel.world.entity.projectile.ThrownMjolnir;

@OnlyIn(Dist.CLIENT)
public class ThrownMjolnirRenderer extends EntityRenderer<ThrownMjolnir> {
    private final MjolnirModel model;

    public ThrownMjolnirRenderer(EntityRendererProvider.Context p_174420_) {
        super(p_174420_);
        this.model = new MjolnirModel(p_174420_.bakeLayer(MarvelModels.MJOLNIR));
    }

    public void render(ThrownMjolnir mjolnir, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int light) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(g, mjolnir.yRotO, mjolnir.getYRot())));
        poseStack.mulPose(Axis.XP.rotationDegrees((mjolnir.returningToOwner() ? 1 : -1) * Mth.lerp(g, mjolnir.xRotO, mjolnir.getXRot()) - 90));
        VertexConsumer vertexConsumer = ItemRenderer.getFoilBufferDirect(multiBufferSource, this.model.renderType(getTextureLocation(mjolnir)), false, mjolnir.isFoil());
        this.model.renderToBuffer(poseStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(mjolnir, f, g, poseStack, multiBufferSource, light);
    }

    @Override
    public ResourceLocation getTextureLocation(ThrownMjolnir mjolnir) {
        return MarvelSuperheroes.id("textures/entity/mjolnir.png");
    }
}
