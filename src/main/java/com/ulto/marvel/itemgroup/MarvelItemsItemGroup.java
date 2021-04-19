
package com.ulto.marvel.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import com.ulto.marvel.item.VibraniumItem;
import com.ulto.marvel.MarvelModElements;

@MarvelModElements.ModElement.Tag
public class MarvelItemsItemGroup extends MarvelModElements.ModElement {
	public MarvelItemsItemGroup(MarvelModElements instance) {
		super(instance, 92);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabmarvel_items") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(VibraniumItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
