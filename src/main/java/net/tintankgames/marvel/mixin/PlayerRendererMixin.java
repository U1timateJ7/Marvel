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
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.neoforge.client.ClientHooks;
import net.tintankgames.marvel.client.model.MarvelModels;
import net.tintankgames.marvel.client.model.SuitModel;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.item.SuitItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin extends LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    public PlayerRendererMixin(EntityRendererProvider.Context p_174289_, PlayerModel<AbstractClientPlayer> p_174290_, float p_174291_) {
        super(p_174289_, p_174290_, p_174291_);
    }

    @Inject(at = @At("RETURN"), method = "renderHand")
    private void renderSuitFirstPerson(PoseStack poseStack, MultiBufferSource multiBufferSource, int light, AbstractClientPlayer player, ModelPart arm, ModelPart sleeve, CallbackInfo ci) {
        ItemStack stack = player.getItemBySlot(EquipmentSlot.CHEST);
        if (stack.getItem() instanceof SuitItem suitItem) {
            HumanoidModel<?> originalModel = new SuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MarvelModels.suit(ArmorItem.Type.CHESTPLATE)));
            boolean rightArm = arm == getModel().rightArm;
            originalModel.rightArm.copyFrom(arm);
            originalModel.leftArm.copyFrom(arm);
            SuitModel<?> model = (SuitModel<?>) ClientHooks.getArmorModel(player, stack, EquipmentSlot.CHEST, originalModel);
            ArmorMaterial armormaterial = suitItem.getMaterial().value();
            int i = stack.is(ItemTags.DYEABLE) ? FastColor.ARGB32.opaque(DyedItemColor.getOrDefault(stack, -6265536)) : -1;

            for (ArmorMaterial.Layer armormaterial$layer : armormaterial.layers()) {
                int j = armormaterial$layer.dyeable() ? i : -1;

                if (model.rightArm.hasChild("right_claws") && model.leftArm.hasChild("left_claws")) {
                    model.rightArm.getChild("right_claws").visible = player.getMainHandItem().has(MarvelDataComponents.CLAWS_OUT);
                    model.leftArm.getChild("left_claws").visible = player.getMainHandItem().has(MarvelDataComponents.CLAWS_OUT);
                }

                ResourceLocation texture = ClientHooks.getArmorTexture(player, stack, armormaterial$layer, false, EquipmentSlot.CHEST);
                VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityTranslucent(texture));
                (rightArm ? model.rightArm : model.leftArm).render(poseStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, j);
                if (stack.has(MarvelDataComponents.ABSORBED_DAMAGE)) {
                    VertexConsumer vertexConsumer1 = multiBufferSource.getBuffer(RenderType.entityTranslucentEmissive(texture.withPath(id -> id.replace(".png", "_glow.png"))));
                    float percent = stack.getOrDefault(MarvelDataComponents.ABSORBED_DAMAGE, 0.0F) / 25.0F;
                    int k = FastColor.ARGB32.color(j, (int) (percent * 255));
                    (rightArm ? model.rightArm : model.leftArm).render(poseStack, vertexConsumer1, light, OverlayTexture.NO_OVERLAY, k);
                }
            }
        }
    }
}
