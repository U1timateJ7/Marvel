package net.tintankgames.marvel.mixin;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.renderer.block.model.ItemTransform;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.world.item.ItemDisplayContext;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemTransforms.class)
public abstract class ItemTransformsMixin {
    @Shadow @Final public ImmutableMap<ItemDisplayContext, ItemTransform> moddedTransforms;
    @Shadow @Final public ItemTransform thirdPersonLeftHand;
    @Shadow @Final public ItemTransform thirdPersonRightHand;
    @Shadow @Final public ItemTransform firstPersonLeftHand;
    @Shadow @Final public ItemTransform firstPersonRightHand;
    @Shadow @Final public ItemTransform head;
    @Shadow @Final public ItemTransform gui;
    @Shadow @Final public ItemTransform ground;
    @Shadow @Final public ItemTransform fixed;

    @Inject(at = @At("HEAD"), method = "getTransform", cancellable = true)
    private void getModdedTransforms(ItemDisplayContext p_270619_, CallbackInfoReturnable<ItemTransform> cir) {
        if (p_270619_.isModded()) {
            ItemTransform moddedTransform = moddedTransforms.get(p_270619_);
            if (moddedTransform != null) {
                cir.setReturnValue(moddedTransform);
            }
            ItemDisplayContext moddedFallback = p_270619_.fallback();
            if (moddedFallback == null && !cir.isCancelled()) {
                cir.setReturnValue(ItemTransform.NO_TRANSFORM);
            }
            if (!cir.isCancelled() && moddedFallback != null) {
                cir.setReturnValue(switch (moddedFallback) {
                    case THIRD_PERSON_LEFT_HAND -> this.thirdPersonLeftHand;
                    case THIRD_PERSON_RIGHT_HAND -> this.thirdPersonRightHand;
                    case FIRST_PERSON_LEFT_HAND -> this.firstPersonLeftHand;
                    case FIRST_PERSON_RIGHT_HAND -> this.firstPersonRightHand;
                    case HEAD -> this.head;
                    case GUI -> this.gui;
                    case GROUND -> this.ground;
                    case FIXED -> this.fixed;
                    default -> ItemTransform.NO_TRANSFORM;
                });
            }
        }
    }
}
