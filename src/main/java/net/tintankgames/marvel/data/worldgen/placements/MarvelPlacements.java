package net.tintankgames.marvel.data.worldgen.placements;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.data.worldgen.features.MarvelConfiguredFeatures;

import java.util.Arrays;

public class MarvelPlacements {
    public static final ResourceKey<PlacedFeature> ORE_VIBRANIUM = create("ore_vibranium");
    public static final ResourceKey<PlacedFeature> ORE_VIBRANIUM_BURIED = create("ore_vibranium_buried");
    public static final ResourceKey<PlacedFeature> ORE_VIBRANIUM_EXTRA = create("ore_vibranium_extra");
    public static final ResourceKey<PlacedFeature> ORE_TITANIUM = create("ore_titanium");
    public static final ResourceKey<PlacedFeature> ORE_PALLADIUM = create("ore_palladium");

    private static ResourceKey<PlacedFeature> create(String id) {
        return ResourceKey.create(Registries.PLACED_FEATURE, MarvelSuperheroes.id(id));
    }

    private static Holder.Reference<PlacedFeature> register(BootstrapContext<PlacedFeature> bootstrapContext, ResourceKey<PlacedFeature> key, ResourceKey<ConfiguredFeature<?, ?>> configured, PlacementModifier... placementModifiers) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredRegistry = bootstrapContext.lookup(Registries.CONFIGURED_FEATURE);
        return bootstrapContext.register(key, new PlacedFeature(configuredRegistry.getOrThrow(configured), Arrays.asList(placementModifiers)));
    }

    public static void bootstrap(BootstrapContext<PlacedFeature> bootstrapContext) {
        register(bootstrapContext, ORE_VIBRANIUM, MarvelConfiguredFeatures.ORE_VIBRANIUM, CountPlacement.of(5), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)), BiomeFilter.biome());
        register(bootstrapContext, ORE_VIBRANIUM_BURIED, MarvelConfiguredFeatures.ORE_VIBRANIUM_BURIED, CountPlacement.of(3), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)), BiomeFilter.biome());
        register(bootstrapContext, ORE_VIBRANIUM_EXTRA, MarvelConfiguredFeatures.ORE_VIBRANIUM_EXTRA, CountPlacement.of(3), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(48)), BiomeFilter.biome());
        register(bootstrapContext, ORE_TITANIUM, MarvelConfiguredFeatures.ORE_TITANIUM, CountPlacement.of(8), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56)), BiomeFilter.biome());
        register(bootstrapContext, ORE_PALLADIUM, MarvelConfiguredFeatures.ORE_PALLADIUM, CountPlacement.of(8), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(96)), BiomeFilter.biome());
    }
}
