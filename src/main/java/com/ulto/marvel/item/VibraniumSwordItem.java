
package com.ulto.marvel.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

import com.ulto.marvel.itemgroup.MarvelWeaponsItemGroup;
import com.ulto.marvel.MarvelModElements;

@MarvelModElements.ModElement.Tag
public class VibraniumSwordItem extends MarvelModElements.ModElement {
	@ObjectHolder("marvel:vibranium_sword")
	public static final Item block = null;
	public VibraniumSwordItem(MarvelModElements instance) {
		super(instance, 11);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 0;
			}

			public float getEfficiency() {
				return 12f;
			}

			public float getAttackDamage() {
				return 6f;
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
		}, 3, -2.4f, new Item.Properties().group(MarvelWeaponsItemGroup.tab).isImmuneToFire()) {
		}.setRegistryName("vibranium_sword"));
	}
}
