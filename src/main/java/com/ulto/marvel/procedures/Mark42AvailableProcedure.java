package com.ulto.marvel.procedures;

import net.minecraft.world.entity.Entity;

import com.ulto.marvel.network.MarvelModVariables;

public class Mark42AvailableProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return (entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MarvelModVariables.PlayerVariables())).mark42Ready;
	}
}
