package com.ulto.marvel.procedures;

import net.minecraft.entity.Entity;

import java.util.Map;

import com.ulto.marvel.MarvelModVariables;
import com.ulto.marvel.MarvelModElements;
import com.ulto.marvel.MarvelMod;

@MarvelModElements.ModElement.Tag
public class Mark21UnavailableProcedure extends MarvelModElements.ModElement {
	public Mark21UnavailableProcedure(MarvelModElements instance) {
		super(instance, 312);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MarvelMod.LOGGER.warn("Failed to load dependency entity for procedure Mark21Unavailable!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		return (!((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MarvelModVariables.PlayerVariables())).mark21Ready));
	}
}
