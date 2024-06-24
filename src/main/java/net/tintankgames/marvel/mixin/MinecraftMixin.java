package net.tintankgames.marvel.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Enemy;
import net.tintankgames.marvel.MarvelConfig;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import org.jetbrains.annotations.Nullable;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Shadow @Nullable public LocalPlayer player;

    @Inject(at = @At("HEAD"), method = "shouldEntityAppearGlowing", cancellable = true)
    private void spiderSense(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (player.getItemBySlot(EquipmentSlot.CHEST).has(MarvelDataComponents.SPIDER_SENSE)) {
            cir.setReturnValue(entity != player && entity instanceof Enemy && entity.distanceTo(player) <= MarvelConfig.spiderSenseRange);
        }
    }
}
