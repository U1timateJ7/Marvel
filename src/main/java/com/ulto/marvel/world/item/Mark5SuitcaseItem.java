
package com.ulto.marvel.world.item;

import com.ulto.marvel.procedures.Mark5SuitUp;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Mark5SuitcaseItem extends Item {
	public Mark5SuitcaseItem(Properties properties) {
		super(properties);
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 0;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		ItemStack itemstack = ar.getObject();
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();

		Mark5SuitUp.execute(world, x, y, z, entity);
		return ar;
	}
}
