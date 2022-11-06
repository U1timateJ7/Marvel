package com.ulto.marvel.world.effect;

import com.ulto.marvel.common.MarvelMod;
import com.ulto.marvel.world.effect.GrowingMobEffect;
import com.ulto.marvel.world.effect.IceingMobEffect;
import com.ulto.marvel.world.effect.ShrinkingMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MarvelModMobEffects {
	private static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MarvelMod.MOD_ID);

	static {
		REGISTRY.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	public static final RegistryObject<MobEffect> ICEING = REGISTRY.register("iceing", IceingMobEffect::new);
	public static final RegistryObject<MobEffect> SHRINKING = REGISTRY.register("shrinking", ShrinkingMobEffect::new);
	public static final RegistryObject<MobEffect> GROWING = REGISTRY.register("growing", GrowingMobEffect::new);
}
