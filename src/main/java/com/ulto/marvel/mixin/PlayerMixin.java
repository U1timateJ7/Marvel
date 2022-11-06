package com.ulto.marvel.mixin;

import com.ulto.marvel.world.item.CapeArmorItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity implements net.minecraftforge.common.extensions.IForgePlayer {
    protected PlayerMixin(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
    }

    @Inject(at = @At("RETURN"), method = "isModelPartShown", cancellable = true)
    private void showCapes(PlayerModelPart part, CallbackInfoReturnable<Boolean> cir) {
        if (part == PlayerModelPart.CAPE) {
            if (hasItemInSlot(EquipmentSlot.CHEST)) {
                ItemStack chestStack = getItemBySlot(EquipmentSlot.CHEST);
                if (chestStack.getItem() instanceof CapeArmorItem) {
                    cir.setReturnValue(true);
                }
            }
        }
    }
}
