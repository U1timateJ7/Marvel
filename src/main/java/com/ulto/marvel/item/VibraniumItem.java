
package com.ulto.marvel.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

import com.ulto.marvel.init.MarvelModTabs;

public class VibraniumItem extends Item {
	public VibraniumItem() {
		super(new Item.Properties().tab(MarvelModTabs.TAB_MARVEL_ITEMS).stacksTo(64).fireResistant().rarity(Rarity.UNCOMMON));
		setRegistryName("vibranium");
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 0;
	}
}
