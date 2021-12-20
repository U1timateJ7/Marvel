package com.ulto.marvel.procedures;

import net.minecraft.world.entity.Entity;

public class IronManFlamethrowerBulletHitsLivingEntityProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.setSecondsOnFire(15);
	}
}
