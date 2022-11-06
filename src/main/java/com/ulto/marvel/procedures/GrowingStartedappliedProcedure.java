package com.ulto.marvel.procedures;

import com.ulto.marvel.sounds.MarvelModSounds;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

public class GrowingStartedappliedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
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
