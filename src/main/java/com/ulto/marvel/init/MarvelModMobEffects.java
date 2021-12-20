
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package com.ulto.marvel.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.effect.MobEffect;

import java.util.List;
import java.util.ArrayList;

import com.ulto.marvel.potion.ShrinkingMobEffect;
import com.ulto.marvel.potion.IceingMobEffect;
import com.ulto.marvel.potion.GrowingMobEffect;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MarvelModMobEffects {
	private static final List<MobEffect> REGISTRY = new ArrayList<>();
	public static final MobEffect ICEING = register(new IceingMobEffect());
	public static final MobEffect SHRINKING = register(new ShrinkingMobEffect());
	public static final MobEffect GROWING = register(new GrowingMobEffect());

	private static MobEffect register(MobEffect effect) {
		REGISTRY.add(effect);
		return effect;
	}

	@SubscribeEvent
	public static void registerMobEffects(RegistryEvent.Register<MobEffect> event) {
		event.getRegistry().registerAll(REGISTRY.toArray(new MobEffect[0]));
	}
}
