package com.ulto.marvel.mixin;

import com.mojang.authlib.GameProfile;
import com.ulto.marvel.world.item.CapeArmorItem;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerMixin extends Player {
    public AbstractClientPlayerMixin(Level p_36114_, BlockPos p_36115_, float p_36116_, GameProfile p_36117_) {
        super(p_36114_, p_36115_, p_36116_, p_36117_);
    }

    @Inject(at = @At("RETURN"), method = "getCloakTextureLocation", cancellable = true)
    private void customCapes(CallbackInfoReturnable<ResourceLocation> cir) {
        if (hasItemInSlot(EquipmentSlot.CHEST)) {
            ItemStack chestStack = getItemBySlot(EquipmentSlot.CHEST);
            if (chestStack.getItem() instanceof CapeArmorItem capeArmorItem) {
                cir.setReturnValue(capeArmorItem.getCapeTexture(this, chestStack));
            }
        }
    }
}
