package net.tintankgames.marvel.mixin;

import com.google.common.collect.Streams;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.tintankgames.marvel.world.item.MarvelItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {
    @Inject(at = @At("HEAD"), method = "getItemEnchantmentLevel", cancellable = true)
    private static void cantRemoveThor(Enchantment enchantment, ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if ((stack.is(MarvelItems.Tags.THOR_ARMOR) || stack.is(MarvelItems.Tags.IRON_MAN_MARK_38_ARMOR)) && enchantment == Enchantments.BINDING_CURSE) cir.setReturnValue(1);
    }

    @Inject(at = @At("HEAD"), method = "hasAquaAffinity", cancellable = true)
    private static void mark37Affinity(LivingEntity living, CallbackInfoReturnable<Boolean> cir) {
        if (Streams.stream(living.getArmorSlots()).allMatch(piece -> piece.is(MarvelItems.Tags.IRON_MAN_MARK_37_ARMOR))) cir.setReturnValue(true);
    }
}
