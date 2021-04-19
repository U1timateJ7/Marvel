
package com.ulto.marvel.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Rarity;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;

import com.ulto.marvel.MarvelModElements;

@MarvelModElements.ModElement.Tag
public class WakandaItem extends MarvelModElements.ModElement {
	@ObjectHolder("marvel:wakanda")
	public static final Item block = null;
	public WakandaItem(MarvelModElements instance) {
		super(instance, 28);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new MusicDiscItemCustom());
	}
	public static class MusicDiscItemCustom extends MusicDiscItem {
		public MusicDiscItemCustom() {
			super(0, MarvelModElements.sounds.get(new ResourceLocation("marvel:music.marvel.wakanda")),
					new Item.Properties().group(ItemGroup.MISC).maxStackSize(1).rarity(Rarity.RARE));
			setRegistryName("wakanda");
		}
	}
}
