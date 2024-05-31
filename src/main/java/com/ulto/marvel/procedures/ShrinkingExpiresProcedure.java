package com.ulto.marvel.procedures;

import com.ulto.marvel.sounds.MarvelModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import virtuoel.pehkui.api.ScaleTypes;

public class ShrinkingExpiresProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		ScaleTypes.HEIGHT.getScaleData(entity).setTargetScale(1);
		ScaleTypes.WIDTH.getScaleData(entity).setTargetScale(1);
		ScaleTypes.REACH.getScaleData(entity).setTargetScale(1);
		ScaleTypes.DROPS.getScaleData(entity).setTargetScale(1);
		ScaleTypes.PROJECTILES.getScaleData(entity).setTargetScale(1);
		ScaleTypes.MOTION.getScaleData(entity).setTargetScale(1);
		ScaleTypes.EXPLOSIONS.getScaleData(entity).setTargetScale(1);
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						MarvelModSounds.get(new ResourceLocation("marvel:antman.grow")), SoundSource.NEUTRAL, 1, 1);
			} else {
				_level.playLocalSound(x, y, z, MarvelModSounds.get(new ResourceLocation("marvel:antman.grow")), SoundSource.NEUTRAL,
						1, 1, false);
			}
		}
	}
}
