package net.tintankgames.marvel.world.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.neoforge.common.EffectCure;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.tintankgames.marvel.MarvelSuperheroes;

import java.util.Set;

public class IcingMobEffect extends MobEffect {
    protected IcingMobEffect() {
        super(MobEffectCategory.HARMFUL, 0xA8F7FF);
        addAttributeModifier(NeoForgeMod.CREATIVE_FLIGHT, MarvelSuperheroes.id("effect.icing"), -1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    @Override
    public void fillEffectCures(Set<EffectCure> cures, MobEffectInstance effectInstance) {
    }
}
