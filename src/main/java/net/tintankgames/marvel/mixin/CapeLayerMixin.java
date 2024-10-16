package net.tintankgames.marvel.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CapeLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.SuitItem;
import net.tintankgames.marvel.world.item.component.Size;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.CuriosApi;

@Mixin(CapeLayer.class)
public abstract class CapeLayerMixin extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    public CapeLayerMixin(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> p_117346_) {
        super(p_117346_);
    }

    @Inject(at = @At("HEAD"), method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/player/AbstractClientPlayer;FFFFFF)V", cancellable = true)
    private void suitCapes(PoseStack p_116615_, MultiBufferSource p_116616_, int p_116617_, AbstractClientPlayer p_116618_, float p_116619_, float p_116620_, float p_116621_, float p_116622_, float p_116623_, float p_116624_, CallbackInfo ci) {
        boolean coolFlying = (p_116618_.getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.FLYING, false) || p_116618_.getItemBySlot(EquipmentSlot.MAINHAND).getOrDefault(MarvelDataComponents.FLYING, false) || p_116618_.getItemBySlot(EquipmentSlot.OFFHAND).getOrDefault(MarvelDataComponents.FLYING, false)) && (hasArmor(p_116618_, MarvelItems.Tags.IRON_MAN_ARMOR, true) || (hasArmor(p_116618_, MarvelItems.Tags.WASP_ARMOR, true) && p_116618_.getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.SIZE, Size.NORMAL) == Size.SMALL) || p_116618_.getMainHandItem().is(MarvelItems.MJOLNIR) || p_116618_.getMainHandItem().is(MarvelItems.STORMBREAKER) || p_116618_.getOffhandItem().is(MarvelItems.MJOLNIR) || p_116618_.getOffhandItem().is(MarvelItems.STORMBREAKER));
        ItemStack itemstack = p_116618_.getItemBySlot(EquipmentSlot.CHEST);
        if (itemstack.is(MarvelItems.Tags.HAS_CAPE) && itemstack.getItem() instanceof SuitItem suitItem && !(CuriosApi.getCuriosInventory(p_116618_).get().isEquipped(Items.ELYTRA) && CuriosApi.getCuriosInventory(p_116618_).get().findFirstCurio(Items.ELYTRA).get().slotContext().visible())) {
            p_116615_.pushPose();
            p_116615_.translate(0.0F, 0.0F, 0.125F);
            double d0 = Mth.lerp(p_116621_, p_116618_.xCloakO, p_116618_.xCloak) - Mth.lerp(p_116621_, p_116618_.xo, p_116618_.getX());
            double d1 = Mth.lerp(p_116621_, p_116618_.yCloakO, p_116618_.yCloak) - Mth.lerp(p_116621_, p_116618_.yo, p_116618_.getY());
            double d2 = Mth.lerp(p_116621_, p_116618_.zCloakO, p_116618_.zCloak) - Mth.lerp(p_116621_, p_116618_.zo, p_116618_.getZ());
            float f = Mth.rotLerp(p_116621_, p_116618_.yBodyRotO, p_116618_.yBodyRot);
            double d3 = Mth.sin(f * (float) (Math.PI / 180.0));
            double d4 = -Mth.cos(f * (float) (Math.PI / 180.0));
            float f1 = (float)d1 * 10.0F;
            f1 = Mth.clamp(f1, -6.0F, 32.0F);
            float f2 = (float)(d0 * d3 + d2 * d4) * 100.0F;
            f2 = Mth.clamp(f2, 0.0F, 150.0F);
            float f3 = (float)(d0 * d4 - d2 * d3) * 100.0F;
            f3 = Mth.clamp(f3, -20.0F, 20.0F);
            if (f2 < 0.0F) {
                f2 = 0.0F;
            }

            float f4 = Mth.lerp(p_116621_, p_116618_.oBob, p_116618_.bob);
            f1 += Mth.sin((coolFlying ? 0 : Mth.lerp(p_116621_, p_116618_.walkDistO, p_116618_.walkDist)) * 6.0F) * 32.0F * f4;
            if (p_116618_.isCrouching()) {
                f1 += 25.0F;
            }

            p_116615_.mulPose(Axis.XP.rotationDegrees(6.0F + f2 / 2.0F + f1));
            p_116615_.mulPose(Axis.ZP.rotationDegrees(f3 / 2.0F));
            p_116615_.mulPose(Axis.YP.rotationDegrees(180.0F - f3 / 2.0F));
            VertexConsumer vertexconsumer = p_116616_.getBuffer(RenderType.entitySolid(BuiltInRegistries.ITEM.getKey(suitItem).withPath(id -> "textures/models/cape/" + id.replace("_" + suitItem.getType().getName(), "") + ".png")));
            this.getParentModel().renderCloak(p_116615_, vertexconsumer, p_116617_, OverlayTexture.NO_OVERLAY);
            p_116615_.popPose();
            ci.cancel();
        }
        if (!ci.isCancelled() && !p_116618_.isInvisible() && p_116618_.isModelPartShown(PlayerModelPart.CAPE)) {
            PlayerSkin playerskin = p_116618_.getSkin();
            if (playerskin.capeTexture() != null) {
                ItemStack chest = p_116618_.getItemBySlot(EquipmentSlot.CHEST);
                if (!chest.is(Items.ELYTRA)) {
                    p_116615_.pushPose();
                    p_116615_.translate(0.0F, 0.0F, 0.125F);
                    double d0 = Mth.lerp(p_116621_, p_116618_.xCloakO, p_116618_.xCloak) - Mth.lerp(p_116621_, p_116618_.xo, p_116618_.getX());
                    double d1 = Mth.lerp(p_116621_, p_116618_.yCloakO, p_116618_.yCloak) - Mth.lerp(p_116621_, p_116618_.yo, p_116618_.getY());
                    double d2 = Mth.lerp(p_116621_, p_116618_.zCloakO, p_116618_.zCloak) - Mth.lerp(p_116621_, p_116618_.zo, p_116618_.getZ());
                    float f = Mth.rotLerp(p_116621_, p_116618_.yBodyRotO, p_116618_.yBodyRot);
                    double d3 = Mth.sin(f * (float) (Math.PI / 180.0));
                    double d4 = -Mth.cos(f * (float) (Math.PI / 180.0));
                    float f1 = (float)d1 * 10.0F;
                    f1 = Mth.clamp(f1, -6.0F, 32.0F);
                    float f2 = (float)(d0 * d3 + d2 * d4) * 100.0F;
                    f2 = Mth.clamp(f2, 0.0F, 150.0F);
                    float f3 = (float)(d0 * d4 - d2 * d3) * 100.0F;
                    f3 = Mth.clamp(f3, -20.0F, 20.0F);
                    if (f2 < 0.0F) {
                        f2 = 0.0F;
                    }

                    float f4 = Mth.lerp(p_116621_, p_116618_.oBob, p_116618_.bob);
                    f1 += Mth.sin((coolFlying ? 0 : Mth.lerp(p_116621_, p_116618_.walkDistO, p_116618_.walkDist)) * 6.0F) * 32.0F * f4;
                    if (p_116618_.isCrouching()) {
                        f1 += 25.0F;
                    }

                    p_116615_.mulPose(Axis.XP.rotationDegrees(6.0F + f2 / 2.0F + f1));
                    p_116615_.mulPose(Axis.ZP.rotationDegrees(f3 / 2.0F));
                    p_116615_.mulPose(Axis.YP.rotationDegrees(180.0F - f3 / 2.0F));
                    VertexConsumer vertexconsumer = p_116616_.getBuffer(RenderType.entitySolid(playerskin.capeTexture()));
                    this.getParentModel().renderCloak(p_116615_, vertexconsumer, p_116617_, OverlayTexture.NO_OVERLAY);
                    p_116615_.popPose();
                }
            }
        }
    }

    @Unique
    private boolean hasArmor(LivingEntity living, TagKey<Item> tagKey, boolean needsHead) {
        boolean head = living.getItemBySlot(EquipmentSlot.HEAD).is(tagKey) || !needsHead;
        boolean chest = living.getItemBySlot(EquipmentSlot.CHEST).is(tagKey);
        boolean legs = living.getItemBySlot(EquipmentSlot.LEGS).is(tagKey);
        boolean feet = living.getItemBySlot(EquipmentSlot.FEET).is(tagKey);
        return head && chest && legs && feet;
    }
}
