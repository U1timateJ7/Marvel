
package com.ulto.marvel.world.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class IceingMobEffect extends MobEffect {
	public IceingMobEffect() {
		super(MobEffectCategory.HARMFUL, -6697729);
	}

	@Override
	public String getDescriptionId() {
		return "effect.marvel.iceing";
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public void applyEffectTick(LivingEntity p_19467_, int p_19468_) {
		p_19467_.setIsInPowderSnow(true);
	}

	@Override
	public void removeAttributeModifiers(LivingEntity p_19469_, AttributeMap p_19470_, int p_19471_) {
		super.removeAttributeModifiers(p_19469_, p_19470_, p_19471_);
		p_19469_.setIsInPowderSnow(false);
	}
}
