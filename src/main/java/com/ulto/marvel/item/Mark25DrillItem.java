
package com.ulto.marvel.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

import com.ulto.marvel.MarvelModElements;

@MarvelModElements.ModElement.Tag
public class Mark25DrillItem extends MarvelModElements.ModElement {
	@ObjectHolder("marvel:mark_25_drill")
	public static final Item block = null;
	public Mark25DrillItem(MarvelModElements instance) {
		super(instance, 283);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new PickaxeItem(new IItemTier() {
			public int getMaxUses() {
				return 0;
			}

			public float getEfficiency() {
				return 12f;
			}

			public float getAttackDamage() {
				return 3f;
			}

			public int getHarvestLevel() {
				return 2;
			}

			public int getEnchantability() {
				return 2;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.EMPTY;
			}
		}, 1, -2.7f, new Item.Properties().group(null)) {
		}.setRegistryName("mark_25_drill"));
	}
}
