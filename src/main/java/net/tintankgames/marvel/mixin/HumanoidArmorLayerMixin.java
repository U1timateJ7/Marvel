package net.tintankgames.marvel.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.neoforge.client.ClientHooks;
import net.tintankgames.marvel.client.model.SuitModel;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.SuitItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidArmorLayer.class)
public abstract class HumanoidArmorLayerMixin<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {
    @Shadow protected abstract void renderModel(PoseStack p_289664_, MultiBufferSource p_289689_, int p_289681_, Model p_289658_, float p_289678_, float p_289674_, float p_289693_, ResourceLocation p_324344_);
    @Shadow protected abstract Model getArmorModelHook(T entity, ItemStack itemStack, EquipmentSlot slot, A model);
    @Shadow protected abstract void setPartVisibility(A p_117126_, EquipmentSlot p_117127_);

    public HumanoidArmorLayerMixin(RenderLayerParent<T, M> p_117346_) {
        super(p_117346_);
    }

    @Inject(at = @At("HEAD"), method = "renderModel(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/model/Model;FFFLnet/minecraft/resources/ResourceLocation;)V", cancellable = true)
    private void changeTightRenderType(PoseStack poseStack, MultiBufferSource multiBufferSource, int light, Model model, float red, float green, float blue, ResourceLocation texture, CallbackInfo ci) {
        if (model instanceof SuitModel<?>) {
            VertexConsumer vertexconsumer = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(texture));
            model.renderToBuffer(poseStack, vertexconsumer, light, OverlayTexture.NO_OVERLAY, red, green, blue, 1.0F);
            ci.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "renderArmorPiece", cancellable = true)
    private void pantherSuit(PoseStack poseStack, MultiBufferSource multiBufferSource, T livingEntity, EquipmentSlot equipmentSlot, int light, A originalModel, CallbackInfo ci) {
        ItemStack itemstack = livingEntity.getItemBySlot(equipmentSlot);
        if (itemstack.getItem() instanceof SuitItem suitItem) {
            if (suitItem.getEquipmentSlot() == equipmentSlot) {
                this.getParentModel().copyPropertiesTo(originalModel);
                this.setPartVisibility(originalModel, equipmentSlot);
                Model model = getArmorModelHook(livingEntity, itemstack, equipmentSlot, originalModel);
                ArmorMaterial armormaterial = suitItem.getMaterial().value();
                int i = itemstack.is(ItemTags.DYEABLE) ? DyedItemColor.getOrDefault(itemstack, -6265536) : -1;

                for (ArmorMaterial.Layer armormaterial$layer : armormaterial.layers()) {
                    float red;
                    float green;
                    float blue;
                    if (armormaterial$layer.dyeable() && i != -1) {
                        red = (float) FastColor.ARGB32.red(i) / 255.0F;
                        green = (float)FastColor.ARGB32.green(i) / 255.0F;
                        blue = (float)FastColor.ARGB32.blue(i) / 255.0F;
                    } else {
                        red = 1.0F;
                        green = 1.0F;
                        blue = 1.0F;
                    }

                    if (model instanceof SuitModel<?> suitModel && suitModel.rightArm.hasChild("right_claws") && suitModel.leftArm.hasChild("left_claws")) {
                        suitModel.rightArm.getChild("right_claws").visible = livingEntity.getMainHandItem().has(MarvelDataComponents.CLAWS_OUT);
                        suitModel.leftArm.getChild("left_claws").visible = livingEntity.getMainHandItem().has(MarvelDataComponents.CLAWS_OUT);
                    }

                    ResourceLocation texture = ClientHooks.getArmorTexture(livingEntity, itemstack, armormaterial$layer, false, equipmentSlot);
                    this.renderModel(poseStack, multiBufferSource, light, model, red, green, blue, texture);
                    if (itemstack.has(MarvelDataComponents.ABSORBED_DAMAGE) && !itemstack.getOrDefault(MarvelDataComponents.HELMET_OPEN, false)) {
                        VertexConsumer vertexconsumer = multiBufferSource.getBuffer(RenderType.entityTranslucentEmissive(texture.withPath(id -> id.replace(".png", "_glow.png"))));
                        float percent = itemstack.getOrDefault(MarvelDataComponents.ABSORBED_DAMAGE, 0.0F) / 25.0F;
                        model.renderToBuffer(poseStack, vertexconsumer, light, OverlayTexture.NO_OVERLAY, red, green, blue, percent);
                    }
                    if (itemstack.is(MarvelItems.Tags.CYCLOPS_HELMET)) {
                        VertexConsumer vertexconsumer = multiBufferSource.getBuffer(RenderType.entityTranslucentEmissive(texture.withPath(id -> id.replace(".png", "_glow.png"))));
                        model.renderToBuffer(poseStack, vertexconsumer, light, OverlayTexture.NO_OVERLAY, red, green, blue, 1.0F);
                    }
                }
            }
            ci.cancel();
        }
    }
}
