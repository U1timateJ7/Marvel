
package com.ulto.marvel.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.LivingEntity;

import com.ulto.marvel.procedures.HeartShapedHerbEatenProcedure;
import com.ulto.marvel.init.MarvelModTabs;

public class HeartShapedHerbItemItem extends Item {
	public HeartShapedHerbItemItem() {
		super(new Item.Properties().tab(MarvelModTabs.TAB_MARVEL_ITEMS).stacksTo(64).rarity(Rarity.COMMON)
				.food((new FoodProperties.Builder()).nutrition(20).saturationMod(2f).alwaysEat()

						.build()));
		setRegistryName("heart_shaped_herb_item");
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		ItemStack retval = super.finishUsingItem(itemstack, world, entity);
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();

		HeartShapedHerbEatenProcedure.execute(entity);
		return retval;
	}
}
