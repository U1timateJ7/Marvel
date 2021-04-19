
package com.ulto.marvel.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import com.ulto.marvel.item.CapsShieldRedItem;
import com.ulto.marvel.MarvelModElements;

@MarvelModElements.ModElement.Tag
public class MarvelWeaponsItemGroup extends MarvelModElements.ModElement {
	public MarvelWeaponsItemGroup(MarvelModElements instance) {
		super(instance, 93);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabmarvel_weapons") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(CapsShieldRedItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
