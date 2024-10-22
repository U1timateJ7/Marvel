package net.tintankgames.marvel.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.component.Size;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Shadow @Final Minecraft minecraft;

    @ModifyExpressionValue(method = "getProjectionMatrix(D)Lorg/joml/Matrix4f;", at = @At(value = "CONSTANT", args = "floatValue=0.05F"))
    private float sizeFix(float value) {
        if (minecraft.getCameraEntity() instanceof Player player && player.getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.SIZE, Size.NORMAL) == Size.SMALL) return value * 0.0625F;
        else return value;
    }

    @Inject(method = "getNightVisionScale", at = @At("HEAD"), cancellable = true)
    private static void getNightVisionScale(LivingEntity p_109109_, float p_109110_, CallbackInfoReturnable<Float> cir) {
        if (marvel$hasArmor(p_109109_, MarvelItems.Tags.IRON_MAN_MARK_25_ARMOR)) {
            cir.setReturnValue(1.0F);
        }
    }

    @Unique
    private static boolean marvel$hasArmor(LivingEntity living, TagKey<Item> tagKey) {
        boolean head = living.getItemBySlot(EquipmentSlot.HEAD).is(tagKey);
        boolean chest = living.getItemBySlot(EquipmentSlot.CHEST).is(tagKey);
        boolean legs = living.getItemBySlot(EquipmentSlot.LEGS).is(tagKey);
        boolean feet = living.getItemBySlot(EquipmentSlot.FEET).is(tagKey);
        return head && chest && legs && feet;
    }
}
