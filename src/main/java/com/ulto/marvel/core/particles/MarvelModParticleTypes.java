package com.ulto.marvel.core.particles;

import com.ulto.marvel.common.MarvelMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MarvelModParticleTypes {
    public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MarvelMod.MOD_ID);

    static {
        REGISTRY.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<ParticleType<?>> IRON_MAN_FLIGHT = REGISTRY.register("iron_man_flight", () -> new SimpleParticleType(true));
}
