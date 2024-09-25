package net.tintankgames.marvel.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.entity.projectile.TesseractCharge;

@OnlyIn(Dist.CLIENT)
public class TesseractChargeRenderer extends EntityRenderer<TesseractCharge> {
    private static final ResourceLocation TEXTURE_LOCATION = MarvelSuperheroes.id("textures/entity/tesseract_charge.png");

    public TesseractChargeRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    protected int getBlockLightLevel(TesseractCharge tesseractCharge, BlockPos pos) {
        return 15;
    }

    public void render(TesseractCharge tesseractCharge, float yRot, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int light) {
        poseStack.pushPose();
        poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        PoseStack.Pose posestack$pose = poseStack.last();
        VertexConsumer vertexconsumer = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(tesseractCharge)));
        vertex(vertexconsumer, posestack$pose, light, 0.0F, 0, 0, 1);
        vertex(vertexconsumer, posestack$pose, light, 1.0F, 0, 1, 1);
        vertex(vertexconsumer, posestack$pose, light, 1.0F, 1, 1, 0);
        vertex(vertexconsumer, posestack$pose, light, 0.0F, 1, 0, 0);
        poseStack.popPose();
        super.render(tesseractCharge, yRot, partialTick, poseStack, multiBufferSource, light);
    }

    private static void vertex(VertexConsumer vertexConsumer, PoseStack.Pose pose, int light, float x, int y, int u, int v) {
        vertexConsumer.vertex(pose, x - 0.5F, (float)y - 0.25F, 0.0F).color(-1).uv((float)u, (float)v).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(pose, 0.0F, 1.0F, 0.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(TesseractCharge p_114482_) {
        return TEXTURE_LOCATION;
    }
}
