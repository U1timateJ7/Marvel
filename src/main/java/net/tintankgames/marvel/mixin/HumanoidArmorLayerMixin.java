package net.tintankgames.marvel.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.neoforge.client.ClientHooks;
import net.tintankgames.marvel.client.model.SuitModel;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.SuitItem;
import net.tintankgames.marvel.world.item.component.Size;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidArmorLayer.class)
public abstract class HumanoidArmorLayerMixin<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {
    @Shadow protected abstract Model getArmorModelHook(T entity, ItemStack itemStack, EquipmentSlot slot, A model);
    @Shadow protected abstract void setPartVisibility(A p_117126_, EquipmentSlot p_117127_);
    @Shadow protected abstract A getArmorModel(EquipmentSlot p_117079_);

    public HumanoidArmorLayerMixin(RenderLayerParent<T, M> p_117346_) {
        super(p_117346_);
    }

    @Inject(at = @At("RETURN"), method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V", cancellable = true)
    private void doSuitRender(PoseStack p_117096_, MultiBufferSource p_117097_, int p_117098_, T p_117099_, float p_117100_, float p_117101_, float p_117102_, float p_117103_, float p_117104_, float p_117105_, CallbackInfo ci) {
        this.marvel$renderSuitPiece(p_117096_, p_117097_, p_117099_, EquipmentSlot.CHEST, p_117098_, p_117103_, this.getArmorModel(EquipmentSlot.CHEST));
        this.marvel$renderSuitPiece(p_117096_, p_117097_, p_117099_, EquipmentSlot.LEGS, p_117098_, p_117103_, this.getArmorModel(EquipmentSlot.LEGS));
        this.marvel$renderSuitPiece(p_117096_, p_117097_, p_117099_, EquipmentSlot.FEET, p_117098_, p_117103_, this.getArmorModel(EquipmentSlot.FEET));
        this.marvel$renderSuitPiece(p_117096_, p_117097_, p_117099_, EquipmentSlot.HEAD, p_117098_, p_117103_, this.getArmorModel(EquipmentSlot.HEAD));
    }

    @Inject(at = @At("HEAD"), method = "renderArmorPiece", cancellable = true)
    private void doSuitRender(PoseStack p_117119_, MultiBufferSource p_117120_, T livingEntity, EquipmentSlot equipmentSlot, int p_117123_, A p_117124_, CallbackInfo ci) {
        ItemStack itemstack = livingEntity.getItemBySlot(equipmentSlot);
        if (itemstack.getItem() instanceof SuitItem) {
            ci.cancel();
        }
    }

    @Unique
    private void marvel$renderSuitPiece(PoseStack poseStack, MultiBufferSource multiBufferSource, T livingEntity, EquipmentSlot equipmentSlot, int light, float animationProgress, A originalModel) {
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

                    if (model instanceof SuitModel<?> suitModel && suitModel.root.hasChild("left_wing") && suitModel.root.hasChild("right_wing") && livingEntity instanceof Player player && equipmentSlot == EquipmentSlot.CHEST) {
                        ModelPart leftWing = suitModel.root.getChild("left_wing");
                        ModelPart rightWing = suitModel.root.getChild("right_wing");
                        float f = animationProgress * 120.32113F * (float) (Math.PI / 180.0);
                        rightWing.xRot = 0.0F;
                        rightWing.yRot = Mth.cos(f) * (float) Math.PI * 0.15F + (15 * Mth.DEG_TO_RAD);
                        rightWing.zRot = 0.0F;
                        leftWing.xRot = rightWing.xRot;
                        leftWing.yRot = -rightWing.yRot;
                        leftWing.zRot = rightWing.zRot;
                        leftWing.visible = itemstack.getOrDefault(MarvelDataComponents.FLYING, false) && itemstack.getOrDefault(MarvelDataComponents.SIZE, Size.NORMAL) == Size.SMALL;
                        rightWing.visible = itemstack.getOrDefault(MarvelDataComponents.FLYING, false) && itemstack.getOrDefault(MarvelDataComponents.SIZE, Size.NORMAL) == Size.SMALL;
                    }

                    ResourceLocation texture = ClientHooks.getArmorTexture(livingEntity, itemstack, armormaterial$layer, false, equipmentSlot);
                    VertexConsumer vertexconsumer = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(texture));
                    model.renderToBuffer(poseStack, vertexconsumer, light, LivingEntityRenderer.getOverlayCoords(livingEntity, 0), red, green, blue, 1.0F);
                    if (itemstack.has(MarvelDataComponents.ABSORBED_DAMAGE) && !itemstack.getOrDefault(MarvelDataComponents.HELMET_OPEN, false)) {
                        VertexConsumer glowConsumer = multiBufferSource.getBuffer(RenderType.entityTranslucentEmissive(texture.withPath(id -> id.replace(".png", "_glow.png"))));
                        float percent = itemstack.getOrDefault(MarvelDataComponents.ABSORBED_DAMAGE, 0.0F) / 25.0F;
                        model.renderToBuffer(poseStack, glowConsumer, light, LivingEntityRenderer.getOverlayCoords(livingEntity, 0), red, green, blue, percent);
                    }
                    if (itemstack.is(MarvelItems.Tags.CYCLOPS_HELMET)) {
                        VertexConsumer glowConsumer = multiBufferSource.getBuffer(RenderType.entityTranslucentEmissive(texture.withPath(id -> id.replace(".png", "_glow.png"))));
                        model.renderToBuffer(poseStack, glowConsumer, light, LivingEntityRenderer.getOverlayCoords(livingEntity, 0), red, green, blue, 1.0F);
                    }
                }
            }
        }
    }
}
