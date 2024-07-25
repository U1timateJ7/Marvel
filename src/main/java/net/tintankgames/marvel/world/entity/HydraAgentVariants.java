package net.tintankgames.marvel.world.entity;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.core.registries.MarvelRegistries;
import net.tintankgames.marvel.world.level.biome.MarvelBiomes;

public class HydraAgentVariants {
    public static final ResourceKey<HydraAgentVariant> CLASSIC = create("classic");
    public static final ResourceKey<HydraAgentVariant> DEFAULT = CLASSIC;

    private static ResourceKey<HydraAgentVariant> create(String id) {
        return ResourceKey.create(MarvelRegistries.HYDRA_AGENT_VARIANT, MarvelSuperheroes.id(id));
    }

    static void register(BootstrapContext<HydraAgentVariant> bootstrapContext, ResourceKey<HydraAgentVariant> key, TagKey<Biome> tagKey) {
        register(bootstrapContext, key, bootstrapContext.lookup(Registries.BIOME).getOrThrow(tagKey));
    }

    static void register(BootstrapContext<HydraAgentVariant> bootstrapContext, ResourceKey<HydraAgentVariant> key, HolderSet<Biome> biomes) {
        ResourceLocation texture = MarvelSuperheroes.id("entity/hydra_agent/" + key.location().getPath());
        bootstrapContext.register(key, new HydraAgentVariant(texture, biomes));
    }

    public static Holder<HydraAgentVariant> getSpawnVariant(RegistryAccess registryAccess, Holder<Biome> biome) {
        Registry<HydraAgentVariant> registry = registryAccess.registryOrThrow(MarvelRegistries.HYDRA_AGENT_VARIANT);
        return registry.holders().filter(holder -> holder.value().biomes().contains(biome)).findFirst().or(() -> registry.getHolder(DEFAULT)).or(registry::getAny).orElseThrow();
    }

    public static void bootstrap(BootstrapContext<HydraAgentVariant> bootstrapContext) {
        register(bootstrapContext, CLASSIC, MarvelBiomes.Tags.HAS_HYDRA_BASE_CLASSIC);
    }
}
