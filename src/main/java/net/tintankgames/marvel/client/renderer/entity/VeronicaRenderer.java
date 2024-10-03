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
import net.tintankgames.marvel.client.model.VeronicaModel;
import net.tintankgames.marvel.client.renderer.MarvelRenderTypes;
import net.tintankgames.marvel.world.entity.Veronica;

@OnlyIn(Dist.CLIENT)
public class VeronicaRenderer extends EntityRenderer<Veronica> {
    private static final ResourceLocation VERONICA_LOCATION = MarvelSuperheroes.id("textures/entity/veronica/veronica.png");
    private static final ResourceLocation VERONICA_GLOW_LOCATION = MarvelSuperheroes.id("textures/entity/veronica/glow.png");
    private final VeronicaModel model;

    public VeronicaRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new VeronicaModel(context.bakeLayer(MarvelModels.VERONICA));
    }

    public void render(Veronica veronica, float yRot, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int light) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTick, veronica.yRotO, veronica.getYRot())));
        poseStack.mulPose(Axis.XP.rotationDegrees(-Mth.lerp(partialTick, veronica.xRotO, veronica.getXRot()) - 180.0F));
        VertexConsumer vertexconsumer = multiBufferSource.getBuffer(model.renderType(getTextureLocation(veronica)));
        model.renderToBuffer(poseStack, vertexconsumer, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        VertexConsumer vertexconsumer2 = multiBufferSource.getBuffer(MarvelRenderTypes.entityEmissive(VERONICA_GLOW_LOCATION));
        model.renderToBuffer(poseStack, vertexconsumer2, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
        super.render(veronica, yRot, partialTick, poseStack, multiBufferSource, light);
    }

    @Override
    public ResourceLocation getTextureLocation(Veronica veronica) {
        return VERONICA_LOCATION;
    }
}
