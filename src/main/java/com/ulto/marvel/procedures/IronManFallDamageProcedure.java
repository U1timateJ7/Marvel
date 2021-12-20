package com.ulto.marvel.procedures;

import net.minecraft.world.entity.Entity;

public class IronManFallDamageProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.fallDistance = 0;
	}
}
