
package com.ulto.marvel.item;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.InteractionResult;

import com.ulto.marvel.procedures.HeartShapedHerbSeedsRightClickedOnBlockProcedure;
import com.ulto.marvel.init.MarvelModTabs;

public class HeartShapedHerbSeedsItem extends Item {
	public HeartShapedHerbSeedsItem() {
		super(new Item.Properties().tab(MarvelModTabs.TAB_MARVEL_ITEMS).stacksTo(64).rarity(Rarity.COMMON));
		setRegistryName("heart_shaped_herb_seeds");
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 0;
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		InteractionResult retval = super.useOn(context);
		HeartShapedHerbSeedsRightClickedOnBlockProcedure.execute(context.getLevel(), context.getClickedPos().getX(), context.getClickedPos().getY(),
				context.getClickedPos().getZ(), context.getPlayer());
		return retval;
	}
}
