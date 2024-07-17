package net.tintankgames.marvel.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.client.ClientHooks;
import net.tintankgames.marvel.client.model.SuitModel;
import net.tintankgames.marvel.client.renderer.MarvelRenderTypes;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.SuitItem;
import net.tintankgames.marvel.world.level.block.SuitChargerBlock;
import net.tintankgames.marvel.world.level.block.entity.SuitChargerBlockEntity;
import org.spongepowered.asm.mixin.Unique;

public class SuitChargerRenderer implements BlockEntityRenderer<SuitChargerBlockEntity> {
    private static final AABB VISUAL_SHAPE = new AABB(0, 0, 0, 1, 2, 1);

    public SuitChargerRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(SuitChargerBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        BlockState blockstate = blockEntity.getBlockState();
        poseStack.pushPose();
        float f = blockstate.getValue(SuitChargerBlock.FACING).toYRot();
        poseStack.mulPose(Axis.YP.rotationDegrees(-f));
        poseStack.mulPose(Axis.XP.rotationDegrees(180));
        switch (blockstate.getValue(SuitChargerBlock.FACING)) {
            case NORTH -> poseStack.translate(-0.5, -1.7, 0.5);
            case SOUTH -> poseStack.translate(0.5, -1.7, -0.5);
            case WEST -> poseStack.translate(0.5, -1.7, 0.5);
            case EAST -> poseStack.translate(-0.5, -1.7, -0.5);
        }
        this.renderSuitPiece(poseStack, bufferSource, blockEntity, EquipmentSlot.CHEST, combinedLight, new HumanoidModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)));
        this.renderSuitPiece(poseStack, bufferSource, blockEntity, EquipmentSlot.LEGS, combinedLight, new HumanoidModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)));
        this.renderSuitPiece(poseStack, bufferSource, blockEntity, EquipmentSlot.FEET, combinedLight, new HumanoidModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)));
        this.renderSuitPiece(poseStack, bufferSource, blockEntity, EquipmentSlot.HEAD, combinedLight, new HumanoidModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)));
        poseStack.popPose();
    }

    @Unique
    private void renderSuitPiece(PoseStack poseStack, MultiBufferSource multiBufferSource, SuitChargerBlockEntity blockEntity, EquipmentSlot equipmentSlot, int light, HumanoidModel<AbstractClientPlayer> originalModel) {
        originalModel.young = false;
        ItemStack itemstack = blockEntity.getItem(equipmentSlot);
        if (itemstack.getItem() instanceof SuitItem suitItem) {
            if (suitItem.getEquipmentSlot() == equipmentSlot) {
                Model model = ClientHooks.getArmorModel(Minecraft.getInstance().player, itemstack, equipmentSlot, originalModel);
                ArmorMaterial armormaterial = suitItem.getMaterial().value();
                int i = itemstack.is(ItemTags.DYEABLE) ? FastColor.ARGB32.opaque(DyedItemColor.getOrDefault(itemstack, -6265536)) : -1;

                for (ArmorMaterial.Layer armormaterial$layer : armormaterial.layers()) {
                    int j = armormaterial$layer.dyeable() ? i : -1;

                    ResourceLocation texture = ClientHooks.getArmorTexture(Minecraft.getInstance().player, itemstack, armormaterial$layer, false, equipmentSlot);
                    VertexConsumer vertexconsumer = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(texture));
                    model.renderToBuffer(poseStack, vertexconsumer, light, OverlayTexture.pack(OverlayTexture.u(0), OverlayTexture.v(false)), j);
                    if (itemstack.is(MarvelItems.Tags.IRON_MAN_MARK_1_CHESTPLATE)) {
                        VertexConsumer glowConsumer = multiBufferSource.getBuffer(MarvelRenderTypes.entityEmissive(texture.withPath(id -> id.replace(".png", "_glow.png"))));
                        model.renderToBuffer(poseStack, glowConsumer, light, OverlayTexture.pack(OverlayTexture.u(0), OverlayTexture.v(false)), j);
                    }
                    if (itemstack.is(MarvelItems.Tags.IRON_MAN_ARMOR) && itemstack.getOrDefault(MarvelDataComponents.ENERGY, 0.0F) > 0.0F) {
                        VertexConsumer glowConsumer = multiBufferSource.getBuffer(MarvelRenderTypes.entityEmissive(texture.withPath(id -> id.replace(".png", "_glow.png"))));
                        model.renderToBuffer(poseStack, glowConsumer, light, OverlayTexture.pack(OverlayTexture.u(0), OverlayTexture.v(false)), j);
                    }
                }
            }
        }
    }

    @Override
    public boolean shouldRenderOffScreen(SuitChargerBlockEntity p_112306_) {
        return true;
    }

    @Override
    public AABB getRenderBoundingBox(SuitChargerBlockEntity blockEntity) {
        return VISUAL_SHAPE.move(blockEntity.getBlockPos());
    }
}
