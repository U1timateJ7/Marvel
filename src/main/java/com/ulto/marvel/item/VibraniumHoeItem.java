
package com.ulto.marvel.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.item.HoeItem;

import com.ulto.marvel.itemgroup.MarvelWeaponsItemGroup;
import com.ulto.marvel.MarvelModElements;

@MarvelModElements.ModElement.Tag
public class VibraniumHoeItem extends MarvelModElements.ModElement {
	@ObjectHolder("marvel:vibranium_hoe")
	public static final Item block = null;
	public VibraniumHoeItem(MarvelModElements instance) {
		super(instance, 15);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new HoeItem(new IItemTier() {
			public int getMaxUses() {
				return 0;
			}

			public float getEfficiency() {
				return 12f;
			}

			public float getAttackDamage() {
				return -1f;
			}

			public int getHarvestLevel() {
				return 4;
			}

			public int getEnchantability() {
				return 0;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.EMPTY;
			}
		}, 0, 0f, new Item.Properties().group(MarvelWeaponsItemGroup.tab).isImmuneToFire()) {
		}.setRegistryName("vibranium_hoe"));
	}
}
