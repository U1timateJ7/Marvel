package com.ulto.marvel.procedures;

import net.minecraft.entity.Entity;

import java.util.Map;

import com.ulto.marvel.MarvelModVariables;
import com.ulto.marvel.MarvelModElements;
import com.ulto.marvel.MarvelMod;

@MarvelModElements.ModElement.Tag
public class SuperSoldierSerumTakenProcedure extends MarvelModElements.ModElement {
	public SuperSoldierSerumTakenProcedure(MarvelModElements instance) {
		super(instance, 65);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MarvelMod.LOGGER.warn("Failed to load dependency entity for procedure SuperSoldierSerumTaken!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			boolean _setval = (boolean) (true);
			entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.superSoldier = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
