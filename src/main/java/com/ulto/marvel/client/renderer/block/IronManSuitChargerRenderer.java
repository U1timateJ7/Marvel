package com.ulto.marvel.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.ulto.marvel.world.level.block.IronManSuitChargerBlock;
import com.ulto.marvel.world.level.block.entity.IronManSuitChargerBlockEntity;
import com.ulto.marvel.client.model.IronManCharger;
import com.ulto.marvel.mixin.HumanoidArmorLayerAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class IronManSuitChargerRenderer implements BlockEntityRenderer<IronManSuitChargerBlockEntity> {
    private final IronManCharger model;
    private final IronManCharger.Battery25 model25;
    private final IronManCharger.Battery50 model50;
    private final IronManCharger.Battery75 model75;
    private final IronManCharger.Battery100 model100;
    public static final ResourceLocation TEXTURE_LOCATION_OFF2 = new ResourceLocation("marvel:textures/block/iron_man_suit_charger/off.png");
    public static final ResourceLocation TEXTURE_LOCATION_ON2 = new ResourceLocation("marvel:textures/block/iron_man_suit_charger/on.png");

    public IronManSuitChargerRenderer(BlockEntityRendererProvider.Context context) {
        model = new IronManCharger(context.bakeLayer(IronManCharger.LAYER_LOCATION));
        model25 = new IronManCharger.Battery25(context.bakeLayer(IronManCharger.Battery25.LAYER_LOCATION));
        model50 = new IronManCharger.Battery50(context.bakeLayer(IronManCharger.Battery50.LAYER_LOCATION));
        model75 = new IronManCharger.Battery75(context.bakeLayer(IronManCharger.Battery75.LAYER_LOCATION));
        model100 = new IronManCharger.Battery100(context.bakeLayer(IronManCharger.Battery100.LAYER_LOCATION));
    }

    @Override
    public void render(IronManSuitChargerBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        BlockState blockstate = blockEntity.getBlockState();
        poseStack.pushPose();
        float f = blockstate.getValue(IronManSuitChargerBlock.FACING).toYRot();
        poseStack.translate(0.5, 0.5, 0.5);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(-f));
        poseStack.mulPose(Vector3f.XP.rotationDegrees(-53.5f));
        poseStack.translate(0, 0.21, -0.4);
        poseStack.translate(-0.5, -0.5, -0.5);
        VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(blockEntity.active ? TEXTURE_LOCATION_ON2 : TEXTURE_LOCATION_OFF2));
        this.model.renderToBuffer(poseStack, vertexconsumer, combinedLight, combinedOverlay, 1, 1, 1, 1);
        float minimumBattery = Math.min(Math.min(blockEntity.getItem(0).isEmpty() ? 100 : blockEntity.getItem(0).getOrCreateTag().getFloat("Battery"), blockEntity.getItem(1).isEmpty() ? 100 : blockEntity.getItem(1).getOrCreateTag().getFloat("Battery")), Math.min(blockEntity.getItem(2).isEmpty() ? 100 : blockEntity.getItem(2).getOrCreateTag().getFloat("Battery"), blockEntity.getItem(3).isEmpty() ? 100 : blockEntity.getItem(3).getOrCreateTag().getFloat("Battery")));
        if (blockEntity.getItem(0).isEmpty() && blockEntity.getItem(1).isEmpty() && blockEntity.getItem(2).isEmpty() && blockEntity.getItem(3).isEmpty()) minimumBattery = 0;
        if (minimumBattery >= 25)
            this.model25.renderToBuffer(poseStack, vertexconsumer, combinedLight, combinedOverlay, 1, 1, 1, 1);
        if (minimumBattery >= 50)
            this.model50.renderToBuffer(poseStack, vertexconsumer, combinedLight, combinedOverlay, 1, 1, 1, 1);
        if (minimumBattery >= 75)
            this.model75.renderToBuffer(poseStack, vertexconsumer, combinedLight, combinedOverlay, 1, 1, 1, 1);
        if (minimumBattery >= 100)
            this.model100.renderToBuffer(poseStack, vertexconsumer, combinedLight, combinedOverlay, 1, 1, 1, 1);
        poseStack.popPose();
        poseStack.pushPose();
        poseStack.mulPose(Vector3f.YP.rotationDegrees(-f));
        poseStack.mulPose(Vector3f.XP.rotationDegrees(180));
        poseStack.translate(-0.5, -2.75, 0.5);
        poseStack.scale(1.75f, 1.75f, 1.75f);
        this.renderArmorPiece(poseStack, bufferSource, blockEntity, EquipmentSlot.CHEST, combinedLight, new HumanoidModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)));
        this.renderArmorPiece(poseStack, bufferSource, blockEntity, EquipmentSlot.LEGS, combinedLight, new HumanoidModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)));
        this.renderArmorPiece(poseStack, bufferSource, blockEntity, EquipmentSlot.FEET, combinedLight, new HumanoidModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)));
        poseStack.scale(0.7f, 0.7f, 0.7f);
        poseStack.translate(0, 0.3, 0);
        this.renderArmorPiece(poseStack, bufferSource, blockEntity, EquipmentSlot.HEAD, combinedLight, new HumanoidModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)));
        poseStack.popPose();
    }

    private void renderArmorPiece(PoseStack p_117119_, MultiBufferSource p_117120_, IronManSuitChargerBlockEntity blockEntity, EquipmentSlot p_117122_, int p_117123_, HumanoidModel<AbstractClientPlayer> p_117124_) {
        ItemStack itemstack = blockEntity.getItemBySlot(p_117122_);
        if (itemstack.getItem() instanceof ArmorItem armoritem) {
            if (armoritem.getSlot() == p_117122_) {
                this.setPartVisibility(p_117124_, p_117122_);
                net.minecraft.client.model.Model model = getArmorModelHook(itemstack, p_117122_, p_117124_);
                boolean flag1 = itemstack.hasFoil();
                if (armoritem instanceof net.minecraft.world.item.DyeableLeatherItem) {
                    int i = ((net.minecraft.world.item.DyeableLeatherItem)armoritem).getColor(itemstack);
                    float f = (float)(i >> 16 & 255) / 255.0F;
                    float f1 = (float)(i >> 8 & 255) / 255.0F;
                    float f2 = (float)(i & 255) / 255.0F;
                    this.renderModel(p_117119_, p_117120_, p_117123_, flag1, model, f, f1, f2, this.getArmorResource(itemstack, p_117122_, null));
                    this.renderModel(p_117119_, p_117120_, p_117123_, flag1, model, 1.0F, 1.0F, 1.0F, this.getArmorResource(itemstack, p_117122_, "overlay"));
                } else {
                    this.renderModel(p_117119_, p_117120_, p_117123_, flag1, model, 1.0F, 1.0F, 1.0F, this.getArmorResource(itemstack, p_117122_, null));
                }
            }
        }
    }

    protected void setPartVisibility(HumanoidModel<AbstractClientPlayer> p_117126_, EquipmentSlot p_117127_) {
        p_117126_.setAllVisible(false);
        switch (p_117127_) {
            case HEAD -> {
                p_117126_.head.visible = true;
                p_117126_.hat.visible = true;
            }
            case CHEST -> {
                p_117126_.body.visible = true;
                p_117126_.rightArm.visible = true;
                p_117126_.leftArm.visible = true;
            }
            case LEGS -> {
                p_117126_.body.visible = true;
                p_117126_.rightLeg.visible = true;
                p_117126_.leftLeg.visible = true;
            }
            case FEET -> {
                p_117126_.rightLeg.visible = true;
                p_117126_.leftLeg.visible = true;
            }
        }
    }

    protected net.minecraft.client.model.Model getArmorModelHook(ItemStack itemStack, EquipmentSlot slot, HumanoidModel<AbstractClientPlayer> model) {
        return net.minecraftforge.client.ForgeHooksClient.getArmorModel(Minecraft.getInstance().player, itemStack, slot, model);
    }

    private boolean usesInnerModel(EquipmentSlot p_117129_) {
        return p_117129_ == EquipmentSlot.LEGS;
    }

    public ResourceLocation getArmorResource(ItemStack stack, EquipmentSlot slot, @Nullable String type) {
        ArmorItem item = (ArmorItem)stack.getItem();
        String texture = item.getMaterial().getName();
        String domain = "minecraft";
        int idx = texture.indexOf(':');
        if (idx != -1) {
            domain = texture.substring(0, idx);
            texture = texture.substring(idx + 1);
        }
        String s1 = String.format(java.util.Locale.ROOT, "%s:textures/models/armor/%s_layer_%d%s.png", domain, texture, (usesInnerModel(slot) ? 2 : 1), type == null ? "" : String.format(java.util.Locale.ROOT, "_%s", type));

        s1 = net.minecraftforge.client.ForgeHooksClient.getArmorTexture(Minecraft.getInstance().player, stack, s1, slot, type);
        ResourceLocation resourcelocation = HumanoidArmorLayerAccessor.getArmorLocationCache().get(s1);

        if (resourcelocation == null) {
            resourcelocation = new ResourceLocation(s1);
            HumanoidArmorLayerAccessor.getArmorLocationCache().put(s1, resourcelocation);
        }

        return resourcelocation;
    }

    private void renderModel(PoseStack p_117107_, MultiBufferSource p_117108_, int p_117109_, boolean p_117111_, Model p_117112_, float p_117114_, float p_117115_, float p_117116_, ResourceLocation armorResource) {
        VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(p_117108_, RenderType.armorCutoutNoCull(armorResource), false, p_117111_);
        p_117112_.renderToBuffer(p_117107_, vertexconsumer, p_117109_, OverlayTexture.NO_OVERLAY, p_117114_, p_117115_, p_117116_, 1.0F);
    }
}
