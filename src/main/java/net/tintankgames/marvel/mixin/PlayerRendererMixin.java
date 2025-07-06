package net.tintankgames.marvel.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.FastColor;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.neoforge.client.ClientHooks;
import net.tintankgames.marvel.attachment.EntitySuit;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.client.model.IgorModel;
import net.tintankgames.marvel.client.model.MarvelModels;
import net.tintankgames.marvel.client.model.SuitModel;
import net.tintankgames.marvel.client.renderer.MarvelRenderTypes;
import net.tintankgames.marvel.client.renderer.entity.layers.EntitySuitLayer;
import net.tintankgames.marvel.client.renderer.entity.layers.ItemOnBackLayer;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.SuitItem;
import net.tintankgames.marvel.world.item.component.SuitParts;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin extends LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    public PlayerRendererMixin(EntityRendererProvider.Context p_174289_, PlayerModel<AbstractClientPlayer> p_174290_, float p_174291_) {
        super(p_174289_, p_174290_, p_174291_);
    }

    @Inject(at = @At("RETURN"), method = "<init>")
    private void addMarvelLayers(EntityRendererProvider.Context context, boolean slim, CallbackInfo ci) {
        this.addLayer(new ItemOnBackLayer<>(this, context.getItemRenderer()));
        this.addLayer(new EntitySuitLayer<>(this, new IgorModel<>(context.bakeLayer(MarvelModels.IRON_MAN_MARK_38))));
    }

    @Inject(at = @At("RETURN"), method = "setModelProperties")
    private void partVisibility(AbstractClientPlayer player, CallbackInfo ci) {
        if (player.getData(MarvelAttachmentTypes.ENTITY_SUIT) != EntitySuit.NONE) {
            getModel().head.visible = true;
            getModel().hat.visible = false;
            getModel().body.visible = false;
            getModel().rightArm.visible = false;
            getModel().leftArm.visible = false;
            getModel().rightLeg.visible = false;
            getModel().leftLeg.visible = false;
            getModel().leftSleeve.visible = false;
            getModel().rightSleeve.visible = false;
            getModel().leftPants.visible = false;
            getModel().rightPants.visible = false;
            getModel().jacket.visible = false;
        }
    }

    @Inject(at = @At("HEAD"), method = "renderHand", cancellable = true)
    private void noRenderSometimes(PoseStack poseStack, MultiBufferSource multiBufferSource, int light, AbstractClientPlayer player, ModelPart arm, ModelPart sleeve, CallbackInfo ci) {
        ItemStack stack = player.getItemBySlot(EquipmentSlot.CHEST);
        if (stack.has(MarvelDataComponents.INVISIBLE)) {
            ci.cancel();
        }
    }

    @Inject(at = @At("RETURN"), method = "renderHand")
    private void renderSuitFirstPerson(PoseStack poseStack, MultiBufferSource multiBufferSource, int light, AbstractClientPlayer player, ModelPart arm, ModelPart sleeve, CallbackInfo ci) {
        ItemStack stack = player.getItemBySlot(EquipmentSlot.CHEST);
        if (!stack.has(MarvelDataComponents.INVISIBLE) && !ci.isCancelled()) {
            if (stack.getItem() instanceof SuitItem suitItem) {
                HumanoidModel<?> originalModel = new SuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MarvelModels.suit(ArmorItem.Type.CHESTPLATE)));
                boolean rightArm = arm == getModel().rightArm;
                originalModel.rightArm.copyFrom(arm);
                originalModel.leftArm.copyFrom(arm);
                SuitModel<?> model = (SuitModel<?>) ClientHooks.getArmorModel(player, stack, EquipmentSlot.CHEST, originalModel);
                ArmorMaterial armormaterial = suitItem.getMaterial().value();
                int i = stack.is(ItemTags.DYEABLE) ? FastColor.ARGB32.opaque(DyedItemColor.getOrDefault(stack, -6265536)) : -1;

                for (ArmorMaterial.Layer layer : armormaterial.layers()) {
                    int j = layer.dyeable() ? i : -1;

                    if (model instanceof SuitModel<?> suitModel) {
                        suitModel.animateArmor(player, getBob(player, Minecraft.getInstance().getTimer().getGameTimeDeltaPartialTick(true)));
                    }

                    SuitParts parts = stack.get(MarvelDataComponents.SUIT_PARTS);
                    if (parts != null && !parts.hasAllParts()) {
                        for (int k = 0; k < parts.parts().size(); k++) {
                            if (parts.parts().get(k)) {
                                int finalK = k;
                                ResourceLocation texture = ClientHooks.getArmorTexture(player, stack, layer, false, EquipmentSlot.CHEST).withPath(id -> id.replace(".png", "_" + suitItem.getType().getName() + "_" + finalK + ".png"));
                                VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(texture));
                                (rightArm ? model.rightArm : model.leftArm).render(poseStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, j);
                                if ((suitItem.getType() != ArmorItem.Type.HELMET || k != 1) && stack.is(MarvelItems.Tags.IRON_MAN_ARMOR) && stack.getOrDefault(MarvelDataComponents.ENERGY, 0.0F) > 0.0F) {
                                    VertexConsumer glowConsumer = multiBufferSource.getBuffer(MarvelRenderTypes.entityEmissive(texture.withPath(id -> id.replace(".png", "_glow.png"))));
                                    (rightArm ? model.rightArm : model.leftArm).render(poseStack, glowConsumer, light, OverlayTexture.NO_OVERLAY, j);
                                }
                            }
                        }
                        continue;
                    }

                    ResourceLocation texture = ClientHooks.getArmorTexture(player, stack, layer, false, EquipmentSlot.CHEST);
                    VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityTranslucent(texture));
                    (rightArm ? model.rightArm : model.leftArm).render(poseStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, j);
                    if (stack.has(MarvelDataComponents.ABSORBED_DAMAGE)) {
                        VertexConsumer vertexConsumer1 = multiBufferSource.getBuffer(RenderType.entityTranslucentEmissive(texture.withPath(id -> id.replace(".png", "_glow.png"))));
                        float percent = stack.getOrDefault(MarvelDataComponents.ABSORBED_DAMAGE, 0.0F) / 25.0F;
                        int k = FastColor.ARGB32.color((int) (percent * 255), j);
                        (rightArm ? model.rightArm : model.leftArm).render(poseStack, vertexConsumer1, light, OverlayTexture.NO_OVERLAY, k);
                    }
                }
            }
            if (player.getData(MarvelAttachmentTypes.ENTITY_SUIT) != EntitySuit.NONE) {
                HumanoidModel<AbstractClientPlayer> model = player.getData(MarvelAttachmentTypes.ENTITY_SUIT) == EntitySuit.IRON_MAN_MARK_38 ? new IgorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MarvelModels.IRON_MAN_MARK_38)) : null;
                if (model != null) {
                    boolean rightArm = arm == getModel().rightArm;
                    model.rightArm.copyFrom(arm);
                    model.leftArm.copyFrom(arm);
                    VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(player.getData(MarvelAttachmentTypes.ENTITY_SUIT).texture()));
                    (rightArm ? model.rightArm : model.leftArm).render(poseStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, -1);
                }
            }
        }
    }

    @Inject(at = @At("RETURN"), method = "getArmPose", cancellable = true)
    private static void noHoldingHands(AbstractClientPlayer player, InteractionHand hand, CallbackInfoReturnable<HumanoidModel.ArmPose> cir) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.is(MarvelItems.Tags.RENDER_HAND) && !stack.is(MarvelItems.Tags.POSE_HAND) && cir.getReturnValue() == HumanoidModel.ArmPose.ITEM) cir.setReturnValue(HumanoidModel.ArmPose.EMPTY);
    }
}
