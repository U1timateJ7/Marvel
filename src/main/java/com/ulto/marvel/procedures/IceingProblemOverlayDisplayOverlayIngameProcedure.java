package com.ulto.marvel.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import com.ulto.marvel.init.MarvelModMobEffects;

public class IceingProblemOverlayDisplayOverlayIngameProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if (entity instanceof LivingEntity _livEnt ? _livEnt.hasEffect(MarvelModMobEffects.ICEING) : false) {
			return true;
		}
		return false;
	}
}
