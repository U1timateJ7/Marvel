
package com.ulto.marvel.world.item;

import com.ulto.marvel.procedures.HeartShapedHerbEatenProcedure;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HeartShapedHerbItem extends Item {
	public HeartShapedHerbItem(Item.Properties properties) {
		super(properties);
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
