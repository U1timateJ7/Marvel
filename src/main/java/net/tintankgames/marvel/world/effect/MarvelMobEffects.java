package net.tintankgames.marvel.world.effect;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tintankgames.marvel.MarvelSuperheroes;

import java.util.function.Supplier;

public class MarvelMobEffects {
    private static final DeferredRegister<MobEffect> REGISTER = DeferredRegister.create(Registries.MOB_EFFECT, MarvelSuperheroes.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> ICING = register("icing", IcingMobEffect::new);

    private static DeferredHolder<MobEffect, MobEffect> register(String id, Supplier<MobEffect> supplier) {
        return REGISTER.register(id, supplier);
    }

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }
}
