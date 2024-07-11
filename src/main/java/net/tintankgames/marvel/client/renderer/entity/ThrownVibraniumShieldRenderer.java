package net.tintankgames.marvel.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.client.model.MarvelModels;
import net.tintankgames.marvel.client.model.VibraniumShieldModel;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.entity.projectile.ThrownVibraniumShield;
import net.tintankgames.marvel.world.item.component.ShieldArt;

@OnlyIn(Dist.CLIENT)
public class ThrownVibraniumShieldRenderer extends EntityRenderer<ThrownVibraniumShield> {
    private final VibraniumShieldModel model;
    private final VibraniumShieldModel overlayModel;

    public ThrownVibraniumShieldRenderer(EntityRendererProvider.Context p_174420_) {
        super(p_174420_);
        this.model = new VibraniumShieldModel(p_174420_.bakeLayer(MarvelModels.VIBRANIUM_SHIELD));
        this.overlayModel = new VibraniumShieldModel(RenderType::entityCutoutNoCull, p_174420_.bakeLayer(MarvelModels.VIBRANIUM_SHIELD));
    }

    public void render(ThrownVibraniumShield shield, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int light) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(g, shield.yRotO, shield.getYRot())));
        poseStack.mulPose(Axis.XP.rotationDegrees((shield.returningToOwner() ? 1 : -1) * Mth.lerp(g, shield.xRotO, shield.getXRot()) + 180));
        ItemStack stack = shield.getItem();
        VertexConsumer shieldBaseConsumer = ItemRenderer.getFoilBufferDirect(multiBufferSource, this.model.renderType(BuiltInRegistries.ITEM.getKey(stack.getItem()).withPath(name -> "textures/entity/shield/" + name.replace("_shield", "") + ".png")), false, shield.isFoil() && !stack.has(DataComponents.DYED_COLOR) && stack.getOrDefault(MarvelDataComponents.SHIELD_ART, ShieldArt.BLANK) == ShieldArt.BLANK);
        this.model.renderToBuffer(poseStack, shieldBaseConsumer, light, OverlayTexture.NO_OVERLAY);
        if (!stack.has(DataComponents.DYED_COLOR) && stack.getOrDefault(MarvelDataComponents.SHIELD_ART, ShieldArt.BLANK) != ShieldArt.BLANK) {
            VertexConsumer shieldArtConsumer = ItemRenderer.getFoilBufferDirect(multiBufferSource, this.overlayModel.renderType(MarvelSuperheroes.id("textures/entity/shield/" + stack.getOrDefault(MarvelDataComponents.SHIELD_ART, ShieldArt.BLANK).getName() + ".png")), false, shield.isFoil());
            this.overlayModel.renderToBuffer(poseStack, shieldArtConsumer, light, OverlayTexture.NO_OVERLAY);
        } else if (stack.has(DataComponents.DYED_COLOR) && stack.getOrDefault(MarvelDataComponents.SHIELD_ART, ShieldArt.BLANK) == ShieldArt.BLANK) {
            int color = FastColor.ARGB32.opaque(stack.get(DataComponents.DYED_COLOR).rgb());
            VertexConsumer shieldArtConsumer = ItemRenderer.getFoilBufferDirect(multiBufferSource, this.overlayModel.renderType(MarvelSuperheroes.id("textures/entity/shield/dyed.png")), false, shield.isFoil());
            this.overlayModel.renderToBuffer(poseStack, shieldArtConsumer, light, OverlayTexture.NO_OVERLAY, color);
        }
        poseStack.popPose();
        super.render(shield, f, g, poseStack, multiBufferSource, light);
    }

    @Override
    public ResourceLocation getTextureLocation(ThrownVibraniumShield p_114482_) {
        return MarvelSuperheroes.id("textures/entity/shield/vibranium.png");
    }
}
