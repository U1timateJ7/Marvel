package com.ulto.marvel.procedures;

import com.ulto.marvel.world.item.MarvelModItems;
import com.ulto.marvel.network.MarvelModVariables;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class IronManWaterBreathingProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_6_HELMET.get()
				|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_39_HELMET.get()) {
			if (entity instanceof LivingEntity _entity)
				_entity.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 10, 0, (false), (false)));
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_25_HELMET.get()) {
			if (entity instanceof LivingEntity _entity)
				_entity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 110, 0, (false), (false)));
		}
		if (MarvelModVariables.getPlayerVariables(entity).ironManMkNum == 37
				&& !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
				.getOrCreateTag().getBoolean("Open"))) {
			if (entity instanceof LivingEntity _entity)
				_entity.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 10, 0, (false), (false)));
			if (entity instanceof LivingEntity _entity)
				_entity.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 10, 0, (false), (false)));
		}
		if (MarvelModVariables.getPlayerVariables(entity).ironManMkNum == -16) {
			if (entity instanceof LivingEntity _entity)
				_entity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 10, 0, (false), (false)));
		}
	}
}
