
package com.ulto.marvel.item;

import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.resources.ResourceLocation;

import com.ulto.marvel.init.MarvelModSounds;

public class DrivingWithTheTopDownItem extends RecordItem {
	public DrivingWithTheTopDownItem() {
		super(0, MarvelModSounds.REGISTRY.get(new ResourceLocation("marvel:music.marvel.driving_with_the_top_down")),
				new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1).rarity(Rarity.RARE));
		setRegistryName("driving_with_the_top_down");
	}
}
