package com.ulto.marvel.procedures;

import com.ulto.marvel.world.item.IronManSuitItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class RepulsorRangedItemUsedProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (entity instanceof Player _player) {
			_player.getCooldowns().addCooldown(itemstack.getItem(), 20);
			if (!_player.isCreative()) {
				if (_player.getInventory().armor.get(3).getItem() instanceof IronManSuitItem)
					IronManSuitItem.removeBattery(_player.getInventory().armor.get(3), 0.5f);
				if (_player.getInventory().armor.get(2).getItem() instanceof IronManSuitItem)
					IronManSuitItem.removeBattery(_player.getInventory().armor.get(2), 0.5f);
				if (_player.getInventory().armor.get(1).getItem() instanceof IronManSuitItem)
					IronManSuitItem.removeBattery(_player.getInventory().armor.get(1), 0.5f);
				if (_player.getInventory().armor.get(0).getItem() instanceof IronManSuitItem)
					IronManSuitItem.removeBattery(_player.getInventory().armor.get(0), 0.5f);
				_player.getInventory().setChanged();
			}
		}
	}
}
