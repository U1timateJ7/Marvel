package net.tintankgames.marvel.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WebBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.tintankgames.marvel.world.item.MarvelItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WebBlock.class)
public abstract class WebBlockMixin {
    @Inject(at = @At("HEAD"), method = "entityInside", cancellable = true)
    private void spideyNoStuck(BlockState p_58180_, Level p_58181_, BlockPos p_58182_, Entity p_58183_, CallbackInfo ci) {
        if (p_58183_ instanceof LivingEntity living && living.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.SPIDER_MAN_ARMOR) && living.getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.SPIDER_MAN_ARMOR) && living.getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.SPIDER_MAN_ARMOR)) {
            ci.cancel();
        }
    }
}
