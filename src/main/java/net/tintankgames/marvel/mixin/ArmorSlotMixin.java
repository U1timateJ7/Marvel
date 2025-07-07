package net.tintankgames.marvel.mixin;

import com.google.common.collect.Streams;
import net.minecraft.world.Container;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ArmorSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.entity.IronManSuitPart;
import net.tintankgames.marvel.world.item.component.SuitParts;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorSlot.class)
public abstract class ArmorSlotMixin extends Slot {
    @Shadow @Final private EquipmentSlot slot;

    public ArmorSlotMixin(Container p_40223_, int p_40224_, int p_40225_, int p_40226_) {
        super(p_40223_, p_40224_, p_40225_, p_40226_);
    }

    @Inject(at = @At("HEAD"), method = "mayPickup", cancellable = true)
    private void stopSwapWhileSuitPiecesOut(Player p_345575_, CallbackInfoReturnable<Boolean> cir) {
        ItemStack itemstack = this.getItem();
        cir.setReturnValue((itemstack.isEmpty() || p_345575_.isCreative() || !EnchantmentHelper.has(itemstack, EnchantmentEffectComponents.PREVENT_ARMOR_CHANGE)) && !(!getItem().getOrDefault(MarvelDataComponents.SUIT_PARTS, SuitParts.defaultParts(this.slot, true)).hasAllParts() && Streams.stream(p_345575_.level().getEntities().getAll()).anyMatch(entity -> entity instanceof IronManSuitPart part && part.getOwner() == p_345575_)) && super.mayPickup(p_345575_));
    }
}
