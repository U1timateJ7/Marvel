
package com.ulto.marvel.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import com.ulto.marvel.item.CaptainAmericaSuitItem;
import com.ulto.marvel.MarvelModElements;

@MarvelModElements.ModElement.Tag
public class MarvelCostumesItemGroup extends MarvelModElements.ModElement {
	public MarvelCostumesItemGroup(MarvelModElements instance) {
		super(instance, 94);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabmarvel_costumes") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(CaptainAmericaSuitItem.body, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
