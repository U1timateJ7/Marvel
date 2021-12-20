
package com.ulto.marvel.item;

import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.resources.ResourceLocation;

import com.ulto.marvel.init.MarvelModSounds;

public class WakandaItem extends RecordItem {
	public WakandaItem() {
		super(0, MarvelModSounds.REGISTRY.get(new ResourceLocation("marvel:music.marvel.wakanda")),
				new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1).rarity(Rarity.RARE));
		setRegistryName("wakanda");
	}
}
