
package com.ulto.marvel.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class RepulsorBulletItem extends Item {
	public RepulsorBulletItem() {
		super(new Item.Properties().tab(null).stacksTo(64).rarity(Rarity.COMMON));
		setRegistryName("repulsor_bullet");
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 0;
	}
}
