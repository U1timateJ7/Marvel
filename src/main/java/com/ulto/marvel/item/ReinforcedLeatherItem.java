
package com.ulto.marvel.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;

public class ReinforcedLeatherItem extends Item {
	public ReinforcedLeatherItem() {
		super(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(64).rarity(Rarity.COMMON));
		setRegistryName("reinforced_leather");
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 0;
	}
}
