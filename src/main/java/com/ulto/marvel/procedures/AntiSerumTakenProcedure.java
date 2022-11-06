package com.ulto.marvel.procedures;

import com.ulto.marvel.network.MarvelModVariables;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class AntiSerumTakenProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			boolean _setval = false;
			entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.superSoldier = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			boolean _setval = false;
			entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.radioactive = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if (entity instanceof LivingEntity _entity)
			_entity.removeAllEffects();
	}
}
