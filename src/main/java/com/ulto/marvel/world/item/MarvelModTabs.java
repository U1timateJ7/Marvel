package com.ulto.marvel.world.item;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;

public class MarvelModTabs {
	public static CreativeModeTab TAB_MARVEL_ITEMS;
	public static CreativeModeTab TAB_MARVEL_WEAPONS;
	public static CreativeModeTab TAB_MARVEL_COSTUMES;
	public static CreativeModeTab TAB_IRON_MAN_ARMOR;

	public static void load() {
		TAB_MARVEL_ITEMS = new CreativeModeTab("marvel.marvel_items") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(MarvelModItems.VIBRANIUM.get());
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
		TAB_MARVEL_WEAPONS = new CreativeModeTab("marvel.marvel_weapons") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(MarvelModItems.CAPTAIN_AMERICAS_SHIELD_RED.get());
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
		TAB_MARVEL_COSTUMES = new CreativeModeTab("marvel.marvel_costumes") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(MarvelModItems.CAPTAIN_AMERICA_SUIT_CHESTPLATE.get());
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundSuffix("item_search.png");
		TAB_IRON_MAN_ARMOR = new CreativeModeTab("marvel.iron_man_armor") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(MarvelModItems.IRON_MAN_MARK_3_HELMET.get());
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundSuffix("item_search.png");
	}
}
