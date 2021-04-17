package com.ulto.marvel.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import com.ulto.marvel.item.IronManMark1Item;
import com.ulto.marvel.MarvelModElements;
import com.ulto.marvel.MarvelMod;

@MarvelModElements.ModElement.Tag
public class Mark1BlurDisplayProcedure extends MarvelModElements.ModElement {
	public Mark1BlurDisplayProcedure(MarvelModElements instance) {
		super(instance, 121);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MarvelMod.LOGGER.warn("Failed to load dependency entity for procedure Mark1BlurDisplay!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		return (((entity instanceof LivingEntity)
				? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.HEAD)
				: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark1Item.helmet, (int) (1)).getItem());
	}
}
