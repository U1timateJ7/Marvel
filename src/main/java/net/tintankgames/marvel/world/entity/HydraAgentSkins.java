package net.tintankgames.marvel.world.entity;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.core.registries.MarvelRegistries;

public class HydraAgentSkins {
    public static final ResourceKey<HydraAgentSkin> STEVE = create("steve");
    public static final ResourceKey<HydraAgentSkin> ALEX = create("alex");
    public static final ResourceKey<HydraAgentSkin> SUNNY = create("sunny");
    public static final ResourceKey<HydraAgentSkin> KAI = create("kai");

    private static ResourceKey<HydraAgentSkin> create(String id) {
        return ResourceKey.create(MarvelRegistries.HYDRA_AGENT_SKIN, MarvelSuperheroes.id(id));
    }

    static void register(BootstrapContext<HydraAgentSkin> bootstrapContext, ResourceKey<HydraAgentSkin> key, HydraAgentSkin.Model model) {
        bootstrapContext.register(key, new HydraAgentSkin(key.location().getPath(), model));
    }

    public static void bootstrap(BootstrapContext<HydraAgentSkin> bootstrapContext) {
        register(bootstrapContext, STEVE, HydraAgentSkin.Model.WIDE);
        register(bootstrapContext, ALEX, HydraAgentSkin.Model.SLIM);
        register(bootstrapContext, SUNNY, HydraAgentSkin.Model.WIDE);
        register(bootstrapContext, KAI, HydraAgentSkin.Model.WIDE);
    }
}
