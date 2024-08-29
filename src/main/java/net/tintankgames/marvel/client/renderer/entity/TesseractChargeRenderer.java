package net.tintankgames.marvel.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.client.model.MarvelModels;
import net.tintankgames.marvel.client.model.RepulsorModel;
import net.tintankgames.marvel.client.renderer.MarvelRenderTypes;
import net.tintankgames.marvel.world.entity.projectile.TesseractCharge;

@OnlyIn(Dist.CLIENT)
public class TesseractChargeRenderer extends EntityRenderer<TesseractCharge> {
    private final RepulsorModel<TesseractCharge> model;

    public TesseractChargeRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new RepulsorModel<>(context.bakeLayer(MarvelModels.REPULSOR));
    }

    public void render(TesseractCharge repulsor, float yRot, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int light) {
        if (repulsor.tickCount >= 2 || !(this.entityRenderDispatcher.camera.getEntity().distanceToSqr(repulsor) < 6.25)) {
            poseStack.pushPose();
            poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTick, repulsor.yRotO, repulsor.getYRot())));
            poseStack.mulPose(Axis.XP.rotationDegrees(-Mth.lerp(partialTick, repulsor.xRotO, repulsor.getXRot()) - 180.0F));
            poseStack.translate(0, -0.125F, 0);
            VertexConsumer vertexconsumer = multiBufferSource.getBuffer(MarvelRenderTypes.entitySolidEmissive(getTextureLocation(repulsor)));
            model.renderToBuffer(poseStack, vertexconsumer, light, OverlayTexture.NO_OVERLAY);
            poseStack.popPose();
            super.render(repulsor, yRot, partialTick, poseStack, multiBufferSource, light);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(TesseractCharge p_114482_) {
        return MarvelSuperheroes.id("textures/entity/tesseract_charge.png");
    }
}
