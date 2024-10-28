package net.tintankgames.marvel.mixin.integration.stellaris;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.item.MarvelItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(targets = "com.st0x0ef.stellaris.common.oxygen.DimensionOxygenManager", remap = false)
public class DimensionOxygenManagerMixin {
    @Inject(at = @At("HEAD"), method = "canBreath", cancellable = true)
    private void spaceSuitWorks(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        if (hasArmor(entity, MarvelItems.Tags.IRON_MAN_MARK_39_ARMOR)) cir.setReturnValue(true);
    }

    @Unique
    private boolean hasArmor(LivingEntity living, TagKey<Item> tagKey) {
        boolean head = living.getItemBySlot(EquipmentSlot.HEAD).is(tagKey) && !living.getItemBySlot(EquipmentSlot.HEAD).getOrDefault(MarvelDataComponents.HELMET_OPEN, false);
        boolean chest = living.getItemBySlot(EquipmentSlot.CHEST).is(tagKey);
        boolean legs = living.getItemBySlot(EquipmentSlot.LEGS).is(tagKey);
        boolean feet = living.getItemBySlot(EquipmentSlot.FEET).is(tagKey);
        return head && chest && legs && feet;
    }
}
