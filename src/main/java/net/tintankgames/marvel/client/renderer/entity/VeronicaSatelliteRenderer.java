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
import net.tintankgames.marvel.client.model.VeronicaSatelliteModel;
import net.tintankgames.marvel.client.renderer.MarvelRenderTypes;
import net.tintankgames.marvel.world.entity.VeronicaSatellite;

@OnlyIn(Dist.CLIENT)
public class VeronicaSatelliteRenderer extends EntityRenderer<VeronicaSatellite> {
    private static final ResourceLocation VERONICA_LOCATION = MarvelSuperheroes.id("textures/entity/veronica/veronica.png");
    private static final ResourceLocation VERONICA_GLOW_LOCATION = MarvelSuperheroes.id("textures/entity/veronica/glow.png");
    private final VeronicaSatelliteModel model;

    public VeronicaSatelliteRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new VeronicaSatelliteModel(context.bakeLayer(MarvelModels.VERONICA_SATELLITE));
    }

    public void render(VeronicaSatellite veronicaSatellite, float yRot, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int light) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTick, veronicaSatellite.yRotO, veronicaSatellite.getYRot())));
        poseStack.mulPose(Axis.XP.rotationDegrees(-Mth.lerp(partialTick, veronicaSatellite.xRotO, veronicaSatellite.getXRot()) - 180.0F));
        VertexConsumer vertexconsumer = multiBufferSource.getBuffer(model.renderType(getTextureLocation(veronicaSatellite)));
        model.renderToBuffer(poseStack, vertexconsumer, light, OverlayTexture.NO_OVERLAY);
        VertexConsumer vertexconsumer2 = multiBufferSource.getBuffer(MarvelRenderTypes.entityEmissive(VERONICA_GLOW_LOCATION));
        model.renderToBuffer(poseStack, vertexconsumer2, light, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(veronicaSatellite, yRot, partialTick, poseStack, multiBufferSource, light);
    }

    @Override
    public ResourceLocation getTextureLocation(VeronicaSatellite veronicaSatellite) {
        return VERONICA_LOCATION;
    }
}
