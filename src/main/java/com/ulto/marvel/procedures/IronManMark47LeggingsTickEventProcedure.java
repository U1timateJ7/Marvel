package com.ulto.marvel.procedures;

import com.ulto.marvel.network.MarvelModVariables;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class IronManMark47LeggingsTickEventProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (MarvelModVariables.getPlayerVariables(entity).controllingMark47) {
			if (entity instanceof LivingEntity _entity)
				_entity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 10, 0, (false), (false)));
			if (entity instanceof LivingEntity _entity)
				_entity.addEffect(new MobEffectInstance(MobEffects.SATURATION, 1, 0, (false), (false)));
		}
	}
}
