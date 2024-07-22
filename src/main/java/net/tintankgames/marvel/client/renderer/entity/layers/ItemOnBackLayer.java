package net.tintankgames.marvel.client.renderer.entity.layers;

import com.google.common.collect.Streams;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.SuitItem;

public class ItemOnBackLayer<T extends LivingEntity, M extends HumanoidModel<T>> extends RenderLayer<T, M> {
    private final ItemRenderer itemRenderer;

    public ItemOnBackLayer(RenderLayerParent<T, M> renderLayerParent, ItemRenderer itemRenderer) {
        super(renderLayerParent);
        this.itemRenderer = itemRenderer;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int light, T living, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (living.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.DEADPOOL_ARMOR)) {
            poseStack.pushPose();
            getParentModel().body.translateAndRotate(poseStack);
            renderItem(poseStack, multiBufferSource, light, living);
            poseStack.popPose();
        }
    }

    private void renderItem(PoseStack poseStack, MultiBufferSource multiBufferSource, int light, T living) {
        poseStack.translate(0F, 0.25F, 0.15F);
        poseStack.pushPose();
        poseStack.scale(0.9F, 0.9F, 0.9F);
        if (getParentModel().young) {
            poseStack.translate(0.0F, 0.75F, 0.0F);
            poseStack.scale(0.5F, 0.5F, 0.5F);
        }
        ItemStack stack = MarvelItems.KATANAS.toStack();
        ItemStack inventoryStack = MarvelItems.KATANAS.toStack();
        if (living instanceof Player player && player.getInventory().items.stream().anyMatch(stack1 -> stack1.is(MarvelItems.KATANAS))) inventoryStack = player.getInventory().getItem(SuitItem.findSlotMatchingItem(player.getInventory().items, MarvelItems.KATANAS.get())).copy();
        boolean holdingKatanas = living.isHolding(MarvelItems.KATANAS.get());
        boolean hasKatanasInInventory = living instanceof Player player ? player.getInventory().contains(stack1 -> stack1.is(MarvelItems.KATANAS)) : Streams.stream(living.getAllSlots()).anyMatch(stack1 -> stack1.is(MarvelItems.KATANAS));
        itemRenderer.renderStatic(living, holdingKatanas ? stack : inventoryStack, ItemDisplayContext.FIXED, false, poseStack, multiBufferSource, living.level(), light, OverlayTexture.NO_OVERLAY, holdingKatanas || !hasKatanasInInventory ? -2018 : -2016);
        poseStack.popPose();
    }
}
