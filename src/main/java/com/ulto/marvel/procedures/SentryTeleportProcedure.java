package com.ulto.marvel.procedures;

import net.minecraft.world.entity.Entity;

public class SentryTeleportProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity.isOnGround();
	}
}
