package com.ulto.marvel.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.ulto.marvel.world.level.block.IronManSuitChargerBlock;
import com.ulto.marvel.world.level.block.entity.IronManNanotechSuitChargerBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IronManNanotechSuitChargerRenderer implements BlockEntityRenderer<IronManNanotechSuitChargerBlockEntity> {
    private final ItemRenderer itemRenderer;

    public IronManNanotechSuitChargerRenderer(BlockEntityRendererProvider.Context context) {
        itemRenderer = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public void render(IronManNanotechSuitChargerBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        BlockState blockstate = blockEntity.getBlockState();
        poseStack.pushPose();
        float f = blockstate.getValue(IronManSuitChargerBlock.FACING).toYRot();
        poseStack.translate(0.5, 0.5, 0.5);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(-f));
        poseStack.mulPose(Vector3f.XP.rotationDegrees(180));
        poseStack.translate(0, -1, 0);
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(180));
        poseStack.scale(0.4f, 0.4f, 0.4f);
        poseStack.translate(0, -2.75f, -0.55f);
        itemRenderer.renderStatic(blockEntity.getItemBySlot(EquipmentSlot.HEAD), ItemTransforms.TransformType.FIXED, combinedLight, combinedOverlay, poseStack, bufferSource, 0);
        poseStack.translate(0.3f, -0.2f, 0.25f);
        itemRenderer.renderStatic(blockEntity.getItemBySlot(EquipmentSlot.CHEST), ItemTransforms.TransformType.FIXED, combinedLight, combinedOverlay, poseStack, bufferSource, 0);
        poseStack.translate(-0.5f, 0.1f, 0.25f);
        itemRenderer.renderStatic(blockEntity.getItemBySlot(EquipmentSlot.LEGS), ItemTransforms.TransformType.FIXED, combinedLight, combinedOverlay, poseStack, bufferSource, 0);
        poseStack.translate(0.3f, 0.2, 0.25f);
        itemRenderer.renderStatic(blockEntity.getItemBySlot(EquipmentSlot.FEET), ItemTransforms.TransformType.FIXED, combinedLight, combinedOverlay, poseStack, bufferSource, 0);
        poseStack.popPose();
    }
}
