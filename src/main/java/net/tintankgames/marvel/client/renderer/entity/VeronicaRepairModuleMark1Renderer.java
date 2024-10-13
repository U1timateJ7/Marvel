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
import net.tintankgames.marvel.client.model.VeronicaModuleModel;
import net.tintankgames.marvel.client.renderer.MarvelRenderTypes;
import net.tintankgames.marvel.world.entity.VeronicaRepairModuleMark1;

@OnlyIn(Dist.CLIENT)
public class VeronicaRepairModuleMark1Renderer extends EntityRenderer<VeronicaRepairModuleMark1> {
    private static final ResourceLocation MODULE_LOCATION = MarvelSuperheroes.id("textures/entity/veronica/repair_module_mark_1.png");
    private static final ResourceLocation MODULE_GLOW_LOCATION = MarvelSuperheroes.id("textures/entity/veronica/repair_module_glow.png");
    private final VeronicaModuleModel<VeronicaRepairModuleMark1> model;

    public VeronicaRepairModuleMark1Renderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new VeronicaModuleModel<>(context.bakeLayer(MarvelModels.VERONICA_MODULE));
    }

    public void render(VeronicaRepairModuleMark1 repairModuleMark1, float yRot, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int light) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTick, repairModuleMark1.yRotO, repairModuleMark1.getYRot())));
        poseStack.mulPose(Axis.XP.rotationDegrees(-Mth.lerp(partialTick, repairModuleMark1.xRotO, repairModuleMark1.getXRot()) - 180.0F));
        VertexConsumer vertexconsumer = multiBufferSource.getBuffer(model.renderType(getTextureLocation(repairModuleMark1)));
        model.renderToBuffer(poseStack, vertexconsumer, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        VertexConsumer vertexconsumer2 = multiBufferSource.getBuffer(MarvelRenderTypes.entityEmissive(MODULE_GLOW_LOCATION));
        model.renderToBuffer(poseStack, vertexconsumer2, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
        super.render(repairModuleMark1, yRot, partialTick, poseStack, multiBufferSource, light);
    }

    @Override
    public ResourceLocation getTextureLocation(VeronicaRepairModuleMark1 repairModuleMark1) {
        return MODULE_LOCATION;
    }
}
