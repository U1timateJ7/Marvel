
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.ulto.marvel.init;

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
		TAB_MARVEL_ITEMS = new CreativeModeTab("tabmarvel_items") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(MarvelModItems.VIBRANIUM);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
		TAB_MARVEL_WEAPONS = new CreativeModeTab("tabmarvel_weapons") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(MarvelModItems.CAPTAIN_AMERICAS_SHIELD_RED);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
		TAB_MARVEL_COSTUMES = new CreativeModeTab("tabmarvel_costumes") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(MarvelModItems.CAPTAIN_AMERICA_SUIT_CHESTPLATE);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundSuffix("item_search.png");
		TAB_IRON_MAN_ARMOR = new CreativeModeTab("tabiron_man_armor") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(MarvelModItems.IRON_MAN_MARK_3_HELMET);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundSuffix("item_search.png");
	}
}
