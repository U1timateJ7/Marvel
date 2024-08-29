package net.tintankgames.marvel.network.syncher;

import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.entity.HydraAgentSkin;
import net.tintankgames.marvel.world.entity.HydraAgentVariant;

public class MarvelEntityDataSerializers {
    private static final DeferredRegister<EntityDataSerializer<?>> REGISTER = DeferredRegister.create(NeoForgeRegistries.ENTITY_DATA_SERIALIZERS, MarvelSuperheroes.MOD_ID);

    public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<Holder<HydraAgentVariant>>> HYDRA_AGENT_VARIANT = REGISTER.register("hydra_agent_variant", () -> EntityDataSerializer.forValueType(HydraAgentVariant.STREAM_CODEC));
    public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<Holder<HydraAgentSkin>>> HYDRA_AGENT_SKIN = REGISTER.register("hydra_agent_skin", () -> EntityDataSerializer.forValueType(HydraAgentSkin.STREAM_CODEC));

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }
}
