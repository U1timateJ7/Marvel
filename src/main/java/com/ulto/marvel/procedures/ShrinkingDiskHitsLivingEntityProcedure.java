package com.ulto.marvel.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import com.ulto.marvel.world.effect.MarvelModMobEffects;

public class ShrinkingDiskHitsLivingEntityProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity)
			_entity.addEffect(new MobEffectInstance(MarvelModMobEffects.SHRINKING.get(), 1200, 0, (false), (false)));
	}
}
