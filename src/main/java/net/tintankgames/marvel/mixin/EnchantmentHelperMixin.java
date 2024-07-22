package net.tintankgames.marvel.mixin;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.tintankgames.marvel.world.item.MarvelItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {
    @Inject(at = @At("HEAD"), method = "has", cancellable = true)
    private static void cantRemoveThor(ItemStack stack, DataComponentType<?> type, CallbackInfoReturnable<Boolean> cir) {
        if (stack.is(MarvelItems.Tags.THOR_ARMOR) && type == EnchantmentEffectComponents.PREVENT_ARMOR_CHANGE) cir.setReturnValue(true);
    }
}
