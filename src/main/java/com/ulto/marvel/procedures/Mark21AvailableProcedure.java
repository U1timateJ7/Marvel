package com.ulto.marvel.procedures;

import net.minecraft.world.entity.Entity;

import com.ulto.marvel.network.MarvelModVariables;

public class Mark21AvailableProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return MarvelModVariables.getPlayerVariables(entity).mark21Ready;
	}
}
