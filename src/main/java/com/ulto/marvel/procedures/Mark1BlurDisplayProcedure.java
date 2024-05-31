package com.ulto.marvel.procedures;

import com.ulto.marvel.world.item.IronManSuitItem;
import com.ulto.marvel.world.item.MarvelModItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

public class Mark1BlurDisplayProcedure {
	public static boolean execute(LivingEntity entity) {
		if (entity == null)
			return false;
		return entity.getItemBySlot(EquipmentSlot.HEAD).getItem() == MarvelModItems.IRON_MAN_MARK_1_HELMET.get() || (entity.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof IronManSuitItem && IronManSuitItem.getBatteryOfEntity(entity, EquipmentSlot.HEAD) <= 0);
	}
}
