package com.ulto.marvel.procedures;

import net.minecraft.world.entity.Entity;

import com.ulto.marvel.network.MarvelModVariables;

public class SuperSoldierSerumTakenProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MarvelModVariables.PlayerVariables())).hasEatenHeartShapedHerb
				&& !(entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).radioactive) {
			{
				boolean _setval = true;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.superSoldier = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
