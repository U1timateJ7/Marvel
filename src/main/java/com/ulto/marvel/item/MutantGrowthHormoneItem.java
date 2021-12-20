
package com.ulto.marvel.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;

import com.ulto.marvel.procedures.MutantGrowthHormoneUsedProcedure;
import com.ulto.marvel.init.MarvelModTabs;

public class MutantGrowthHormoneItem extends Item {
	public MutantGrowthHormoneItem() {
		super(new Item.Properties().tab(MarvelModTabs.TAB_MARVEL_ITEMS).stacksTo(64).rarity(Rarity.COMMON));
		setRegistryName("mutant_growth_hormone");
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

		MutantGrowthHormoneUsedProcedure.execute(world, x, y, z, entity);
		return ar;
	}
}
