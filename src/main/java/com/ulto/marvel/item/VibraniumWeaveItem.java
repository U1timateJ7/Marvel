
package com.ulto.marvel.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import com.ulto.marvel.itemgroup.MarvelItemsItemGroup;
import com.ulto.marvel.MarvelModElements;

@MarvelModElements.ModElement.Tag
public class VibraniumWeaveItem extends MarvelModElements.ModElement {
	@ObjectHolder("marvel:vibranium_weave")
	public static final Item block = null;
	public VibraniumWeaveItem(MarvelModElements instance) {
		super(instance, 13);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(MarvelItemsItemGroup.tab).maxStackSize(64).isImmuneToFire().rarity(Rarity.COMMON));
			setRegistryName("vibranium_weave");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
