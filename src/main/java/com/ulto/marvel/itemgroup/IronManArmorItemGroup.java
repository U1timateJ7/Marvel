
package com.ulto.marvel.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import com.ulto.marvel.item.IronManMark3Item;
import com.ulto.marvel.MarvelModElements;

@MarvelModElements.ModElement.Tag
public class IronManArmorItemGroup extends MarvelModElements.ModElement {
	public IronManArmorItemGroup(MarvelModElements instance) {
		super(instance, 120);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabiron_man_armor") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(IronManMark3Item.helmet, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundImageName("item_search.png");
	}
	public static ItemGroup tab;
}
