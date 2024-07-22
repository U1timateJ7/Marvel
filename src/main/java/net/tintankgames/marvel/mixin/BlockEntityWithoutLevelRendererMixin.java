package net.tintankgames.marvel.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.client.model.MarvelModels;
import net.tintankgames.marvel.client.model.MjolnirModel;
import net.tintankgames.marvel.client.model.StormbreakerModel;
import net.tintankgames.marvel.client.model.VibraniumShieldModel;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.item.MjolnirItem;
import net.tintankgames.marvel.world.item.StormbreakerItem;
import net.tintankgames.marvel.world.item.VibraniumShieldItem;
import net.tintankgames.marvel.world.item.component.ShieldArt;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockEntityWithoutLevelRenderer.class)
public abstract class BlockEntityWithoutLevelRendererMixin {
    @Shadow @Final private EntityModelSet entityModelSet;
    @Unique private VibraniumShieldModel marvel$vibraniumShieldModel;
    @Unique private VibraniumShieldModel marvel$vibraniumShieldOverlayModel;
    @Unique private MjolnirModel marvel$mjolnirModel;
    @Unique private StormbreakerModel marvel$stormbreakerModel;

    @Inject(at = @At("RETURN"), method = "onResourceManagerReload")
    private void reloadModels(ResourceManager p_172555_, CallbackInfo ci) {
        this.marvel$vibraniumShieldModel = new VibraniumShieldModel(this.entityModelSet.bakeLayer(MarvelModels.VIBRANIUM_SHIELD));
        this.marvel$vibraniumShieldOverlayModel = new VibraniumShieldModel(RenderType::entityCutoutNoCull, this.entityModelSet.bakeLayer(MarvelModels.VIBRANIUM_SHIELD));
        this.marvel$mjolnirModel = new MjolnirModel(this.entityModelSet.bakeLayer(MarvelModels.MJOLNIR));
        this.marvel$stormbreakerModel = new StormbreakerModel(this.entityModelSet.bakeLayer(MarvelModels.STORMBREAKER));
    }

    @Inject(at = @At("RETURN"), method = "renderByItem")
    private void reloadModels(ItemStack stack, ItemDisplayContext itemDisplayContext, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, int overlay, CallbackInfo ci) {
        if (stack.getItem() instanceof VibraniumShieldItem) {
            poseStack.pushPose();
            poseStack.scale(1.0F, -1.0F, -1.0F);
            VertexConsumer shieldBaseConsumer = ItemRenderer.getFoilBufferDirect(multiBufferSource, this.marvel$vibraniumShieldModel.renderType(BuiltInRegistries.ITEM.getKey(stack.getItem()).withPath(name -> "textures/entity/shield/" + name.replace("_shield", "") + ".png")), false, stack.hasFoil() && !stack.has(DataComponents.DYED_COLOR) && stack.getOrDefault(MarvelDataComponents.SHIELD_ART, ShieldArt.BLANK) == ShieldArt.BLANK);
            this.marvel$vibraniumShieldModel.renderToBuffer(poseStack, shieldBaseConsumer, light, overlay);
            if (!stack.has(DataComponents.DYED_COLOR) && stack.getOrDefault(MarvelDataComponents.SHIELD_ART, ShieldArt.BLANK) != ShieldArt.BLANK) {
                VertexConsumer shieldArtConsumer = ItemRenderer.getFoilBufferDirect(multiBufferSource, this.marvel$vibraniumShieldOverlayModel.renderType(MarvelSuperheroes.id("textures/entity/shield/" + stack.getOrDefault(MarvelDataComponents.SHIELD_ART, ShieldArt.BLANK).getName() + ".png")), false, stack.hasFoil());
                this.marvel$vibraniumShieldOverlayModel.renderToBuffer(poseStack, shieldArtConsumer, light, overlay);
            } else if (stack.has(DataComponents.DYED_COLOR) && stack.getOrDefault(MarvelDataComponents.SHIELD_ART, ShieldArt.BLANK) == ShieldArt.BLANK) {
                int color = FastColor.ARGB32.opaque(stack.get(DataComponents.DYED_COLOR).rgb());
                VertexConsumer shieldArtConsumer = ItemRenderer.getFoilBufferDirect(multiBufferSource, this.marvel$vibraniumShieldOverlayModel.renderType(MarvelSuperheroes.id("textures/entity/shield/dyed.png")), false, stack.hasFoil());
                this.marvel$vibraniumShieldOverlayModel.renderToBuffer(poseStack, shieldArtConsumer, light, overlay, color);
            }
            poseStack.popPose();
        } else if (stack.getItem() instanceof MjolnirItem) {
            poseStack.pushPose();
            poseStack.scale(1.0F, -1.0F, -1.0F);
            VertexConsumer shieldBaseConsumer = ItemRenderer.getFoilBufferDirect(multiBufferSource, this.marvel$mjolnirModel.renderType(MarvelSuperheroes.id("textures/entity/mjolnir.png")), false, stack.hasFoil());
            this.marvel$mjolnirModel.renderToBuffer(poseStack, shieldBaseConsumer, light, overlay);
            poseStack.popPose();
        } else if (stack.getItem() instanceof StormbreakerItem) {
            poseStack.pushPose();
            poseStack.scale(1.0F, -1.0F, -1.0F);
            VertexConsumer shieldBaseConsumer = ItemRenderer.getFoilBufferDirect(multiBufferSource, this.marvel$stormbreakerModel.renderType(MarvelSuperheroes.id("textures/entity/stormbreaker.png")), false, stack.hasFoil());
            this.marvel$stormbreakerModel.renderToBuffer(poseStack, shieldBaseConsumer, light, overlay);
            poseStack.popPose();
        }
    }
}
