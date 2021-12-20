
package com.ulto.marvel.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class FlameItem extends Item {
	public FlameItem() {
		super(new Item.Properties().tab(null).stacksTo(64).rarity(Rarity.COMMON));
		setRegistryName("flame");
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 0;
	}
}
