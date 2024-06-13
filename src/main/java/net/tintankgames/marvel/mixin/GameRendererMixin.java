package net.tintankgames.marvel.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.item.component.Size;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Shadow @Final Minecraft minecraft;

    @ModifyExpressionValue(method = "getProjectionMatrix(D)Lorg/joml/Matrix4f;", at = @At(value = "CONSTANT", args = "floatValue=0.05F"))
    private float sizeFix(float value)
    {
        if (minecraft.getCameraEntity() instanceof Player player && player.getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.SIZE, Size.NORMAL) == Size.SMALL) return value * 0.0625F;
        else return value;
    }
}
