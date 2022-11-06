package com.ulto.marvel.world.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface GlowingArmor {
    ResourceLocation getGlowTexture(ItemStack stack, @Nullable Entity entity, @Nullable EquipmentSlot slot);
}
