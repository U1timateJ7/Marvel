
package com.ulto.marvel.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

import com.ulto.marvel.init.MarvelModTabs;

public class WebFluidItem extends Item {
	public WebFluidItem() {
		super(new Item.Properties().tab(MarvelModTabs.TAB_MARVEL_ITEMS).stacksTo(64).rarity(Rarity.COMMON));
		setRegistryName("web_fluid");
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 0;
	}
}
