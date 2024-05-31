package com.ulto.marvel.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import virtuoel.pehkui.api.ScaleTypes;

public class ShrinkingActiveTickProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		ScaleTypes.HEIGHT.getScaleData(entity).setTargetScale(0.1f);
		ScaleTypes.WIDTH.getScaleData(entity).setTargetScale(0.1f);
		ScaleTypes.REACH.getScaleData(entity).setTargetScale(0.5f);
		ScaleTypes.DROPS.getScaleData(entity).setTargetScale(0.1f);
		ScaleTypes.PROJECTILES.getScaleData(entity).setTargetScale(0.1f);
		ScaleTypes.MOTION.getScaleData(entity).setTargetScale(1);
		ScaleTypes.EXPLOSIONS.getScaleData(entity).setTargetScale(1);
	}
}
