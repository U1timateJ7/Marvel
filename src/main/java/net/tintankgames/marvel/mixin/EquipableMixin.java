package net.tintankgames.marvel.mixin;

import com.google.common.collect.Streams;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.entity.IronManSuitPart;
import net.tintankgames.marvel.world.item.component.SuitParts;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Equipable.class)
public interface EquipableMixin {
    @Inject(at = @At("HEAD"), method = "swapWithEquipmentSlot", cancellable = true)
    private void stopSwapWhileSuitPiecesOut(Item p_270453_, Level p_270395_, Player p_270300_, InteractionHand p_270262_, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        ItemStack itemstack = p_270300_.getItemInHand(p_270262_);
        EquipmentSlot equipmentslot = p_270300_.getEquipmentSlotForItem(itemstack);
        if (!p_270300_.canUseSlot(equipmentslot)) {
            cir.setReturnValue(InteractionResultHolder.pass(itemstack));
        } else {
            ItemStack itemstack1 = p_270300_.getItemBySlot(equipmentslot);
            if (!itemstack1.getOrDefault(MarvelDataComponents.SUIT_PARTS, SuitParts.defaultParts(equipmentslot, true)).hasAllParts() && Streams.stream(p_270395_.getEntities().getAll()).anyMatch(entity -> entity instanceof IronManSuitPart part && part.getOwner() == p_270300_)) {
                cir.setReturnValue(InteractionResultHolder.fail(itemstack));
            } else {
                if ((!EnchantmentHelper.has(itemstack1, EnchantmentEffectComponents.PREVENT_ARMOR_CHANGE) || p_270300_.isCreative()) && !ItemStack.matches(itemstack, itemstack1)) {
                    if (!p_270395_.isClientSide()) {
                        p_270300_.awardStat(Stats.ITEM_USED.get(p_270453_));
                    }

                    ItemStack itemstack2 = itemstack1.isEmpty() ? itemstack : itemstack1.copyAndClear();
                    ItemStack itemstack3 = p_270300_.isCreative() ? itemstack.copy() : itemstack.copyAndClear();
                    p_270300_.setItemSlot(equipmentslot, itemstack3);
                    cir.setReturnValue(InteractionResultHolder.sidedSuccess(itemstack2, p_270395_.isClientSide()));
                } else {
                    cir.setReturnValue(InteractionResultHolder.fail(itemstack));
                }
            }
        }
    }
}
