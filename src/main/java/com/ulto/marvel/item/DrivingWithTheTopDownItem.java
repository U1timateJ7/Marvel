
package com.ulto.marvel.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Rarity;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;

import com.ulto.marvel.MarvelModElements;

@MarvelModElements.ModElement.Tag
public class DrivingWithTheTopDownItem extends MarvelModElements.ModElement {
	@ObjectHolder("marvel:driving_with_the_top_down")
	public static final Item block = null;
	public DrivingWithTheTopDownItem(MarvelModElements instance) {
		super(instance, 182);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new MusicDiscItemCustom());
	}
	public static class MusicDiscItemCustom extends MusicDiscItem {
		public MusicDiscItemCustom() {
			super(0, MarvelModElements.sounds.get(new ResourceLocation("marvel:music.marvel.driving_with_the_top_down")),
					new Item.Properties().group(ItemGroup.MISC).maxStackSize(1).rarity(Rarity.RARE));
			setRegistryName("driving_with_the_top_down");
		}
	}
}
