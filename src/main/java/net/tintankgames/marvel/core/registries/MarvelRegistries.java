package net.tintankgames.marvel.core.registries;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.entity.HydraAgentSkin;
import net.tintankgames.marvel.world.entity.HydraAgentVariant;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MarvelSuperheroes.MOD_ID)
public class MarvelRegistries {
    public static final ResourceKey<Registry<HydraAgentVariant>> HYDRA_AGENT_VARIANT = ResourceKey.createRegistryKey(MarvelSuperheroes.id("hydra_agent_variant"));
    public static final ResourceKey<Registry<HydraAgentSkin>> HYDRA_AGENT_SKIN = ResourceKey.createRegistryKey(MarvelSuperheroes.id("hydra_agent_skin"));

    @SubscribeEvent
    public static void dataPackRegistries(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(HYDRA_AGENT_VARIANT, HydraAgentVariant.DIRECT_CODEC, HydraAgentVariant.DIRECT_CODEC);
        event.dataPackRegistry(HYDRA_AGENT_SKIN, HydraAgentSkin.DIRECT_CODEC, HydraAgentSkin.DIRECT_CODEC);
    }
}
