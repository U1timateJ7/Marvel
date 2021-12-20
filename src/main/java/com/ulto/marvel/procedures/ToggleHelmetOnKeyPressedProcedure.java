package com.ulto.marvel.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

public class ToggleHelmetOnKeyPressedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		ToggleHelmetOnKeyProcedure.execute(world, x, y, z, entity);
		ToggleHelmetPart2Procedure.execute(world, x, y, z, entity);
		ToggleHelmetPart3Procedure.execute(world, x, y, z, entity);
		ToggleHelmetPart4Procedure.execute(world, x, y, z, entity);
	}
}
