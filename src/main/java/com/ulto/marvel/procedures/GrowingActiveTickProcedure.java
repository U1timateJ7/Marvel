package com.ulto.marvel.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import virtuoel.pehkui.api.ScaleTypes;

public class GrowingActiveTickProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		ScaleTypes.HEIGHT.getScaleData(entity).setTargetScale(10);
		ScaleTypes.WIDTH.getScaleData(entity).setTargetScale(10);
		ScaleTypes.REACH.getScaleData(entity).setTargetScale(10);
		ScaleTypes.DROPS.getScaleData(entity).setTargetScale(10);
		ScaleTypes.PROJECTILES.getScaleData(entity).setTargetScale(10);
		ScaleTypes.MOTION.getScaleData(entity).setTargetScale(4);
		ScaleTypes.EXPLOSIONS.getScaleData(entity).setTargetScale(10);
	}
}
