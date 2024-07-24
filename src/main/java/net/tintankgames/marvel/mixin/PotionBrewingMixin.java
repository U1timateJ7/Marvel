package net.tintankgames.marvel.mixin;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import net.tintankgames.marvel.world.item.MarvelItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PotionBrewing.class)
public class PotionBrewingMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z"), method = "mix", cancellable = true)
    private void percentChance(ItemStack ingredient, ItemStack input, CallbackInfoReturnable<ItemStack> cir) {
        if (input.is(MarvelItems.VILLAGER_BLOOD_SAMPLE) && ServerLifecycleHooks.getCurrentServer() != null && ServerLifecycleHooks.getCurrentServer().overworld().getRandom().nextInt(10) >= 4) {
            cir.setReturnValue(MarvelItems.SYRINGE.toStack());
        }
    }
}
