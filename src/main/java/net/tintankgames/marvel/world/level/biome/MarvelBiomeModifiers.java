package net.tintankgames.marvel.world.level.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.data.worldgen.placements.MarvelPlacements;

public class MarvelBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ORES_OVERWORLD = create("ores_overworld");
    public static final ResourceKey<BiomeModifier> ORES_EXTRA_SAVANNA = create("ores_extra_savanna");

    private static ResourceKey<BiomeModifier> create(String id) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, MarvelSuperheroes.id(id));
    }

    public static void bootstrap(BootstrapContext<BiomeModifier> bootstrapContext) {
        HolderGetter<Biome> biome = bootstrapContext.lookup(Registries.BIOME);
        HolderGetter<PlacedFeature> placedFeature = bootstrapContext.lookup(Registries.PLACED_FEATURE);
        bootstrapContext.register(ORES_OVERWORLD, new BiomeModifiers.AddFeaturesBiomeModifier(biome.getOrThrow(BiomeTags.IS_OVERWORLD), HolderSet.direct(placedFeature.getOrThrow(MarvelPlacements.ORE_VIBRANIUM), placedFeature.getOrThrow(MarvelPlacements.ORE_VIBRANIUM_BURIED), placedFeature.getOrThrow(MarvelPlacements.ORE_TITANIUM), placedFeature.getOrThrow(MarvelPlacements.ORE_PALLADIUM)), GenerationStep.Decoration.UNDERGROUND_ORES));
        bootstrapContext.register(ORES_EXTRA_SAVANNA, new BiomeModifiers.AddFeaturesBiomeModifier(biome.getOrThrow(BiomeTags.IS_SAVANNA), HolderSet.direct(placedFeature.getOrThrow(MarvelPlacements.ORE_VIBRANIUM_EXTRA)), GenerationStep.Decoration.UNDERGROUND_ORES));
    }
}
