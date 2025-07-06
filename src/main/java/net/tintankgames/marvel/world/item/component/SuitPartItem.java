package net.tintankgames.marvel.world.item.component;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;

public record SuitPartItem(ArmorItem.Type piece, int part, ItemStack stack) {
}
