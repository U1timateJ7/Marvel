
package com.ulto.marvel.potion;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class IceingMobEffect extends MobEffect {
	public IceingMobEffect() {
		super(MobEffectCategory.HARMFUL, -6697729);
		setRegistryName("iceing");
	}

	@Override
	public String getDescriptionId() {
		return "effect.marvel.iceing";
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
