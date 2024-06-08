package net.tintankgames.marvel.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import net.tintankgames.marvel.world.item.MarvelItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {
    @Shadow protected abstract void renderPlayerArm(PoseStack p_109347_, MultiBufferSource p_109348_, int p_109349_, float p_109350_, float p_109351_, HumanoidArm p_109352_);

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z", ordinal = 0, shift = At.Shift.BEFORE), method = "renderArmWithItem", cancellable = true)
    private void renderHandInPlace(AbstractClientPlayer p_109372_, float p_109373_, float p_109374_, InteractionHand p_109375_, float p_109376_, ItemStack p_109377_, float p_109378_, PoseStack p_109379_, MultiBufferSource p_109380_, int p_109381_, CallbackInfo ci) {
        if (p_109377_.is(MarvelItems.Tags.RENDER_HAND)) {
            boolean flag = p_109375_ == InteractionHand.MAIN_HAND;
            HumanoidArm humanoidarm = flag ? p_109372_.getMainArm() : p_109372_.getMainArm().getOpposite();
            if (flag && !p_109372_.isInvisible()) {
                this.renderPlayerArm(p_109379_, p_109380_, p_109381_, p_109378_, p_109376_, humanoidarm);
            }
            p_109379_.popPose();
            ci.cancel();
        }
    }
}
