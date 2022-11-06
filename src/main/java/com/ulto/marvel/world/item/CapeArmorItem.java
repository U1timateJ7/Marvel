package com.ulto.marvel.world.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

public interface CapeArmorItem {
    ResourceLocation getCapeTexture(Entity entity, ItemStack stack);
}
