package com.ulto.marvel.procedures;

import com.ulto.marvel.world.item.IronManSuitItem;
import com.ulto.marvel.world.item.MarvelModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class Mark1BlurDisplayProcedure {
	public static boolean execute(LivingEntity entity) {
		if (entity == null)
			return false;
		return entity.getItemBySlot(EquipmentSlot.HEAD).getItem() == MarvelModItems.IRON_MAN_MARK_1_HELMET.get() || !IronManSuitItem.hasPower(entity, 0);
	}
}
