package com.ulto.marvel.procedures;

import net.minecraft.world.entity.Entity;

import com.ulto.marvel.network.MarvelModVariables;

public class HeartShapedHerbEatenProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!MarvelModVariables.getPlayerVariables(entity).superSoldier
				&& !MarvelModVariables.getPlayerVariables(entity).hasEatenHeartShapedHerb
				&& !MarvelModVariables.getPlayerVariables(entity).radioactive) {
			{
				boolean _setval = true;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.hasEatenHeartShapedHerb = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if (!MarvelModVariables.getPlayerVariables(entity).superSoldier
				&& MarvelModVariables.getPlayerVariables(entity).hasEatenHeartShapedHerb
				&& !MarvelModVariables.getPlayerVariables(entity).radioactive) {
			{
				boolean _setval = false;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.hasEatenHeartShapedHerb = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
