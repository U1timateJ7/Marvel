package com.ulto.marvel.procedures;

import com.ulto.marvel.network.MarvelModVariables;
import net.minecraft.world.entity.Entity;

public class Mark16AvailableProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return MarvelModVariables.getPlayerVariables(entity).mark16Ready;
	}
}
