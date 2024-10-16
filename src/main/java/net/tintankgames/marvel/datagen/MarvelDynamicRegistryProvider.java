package net.tintankgames.marvel.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.core.registries.MarvelRegistries;
import net.tintankgames.marvel.data.MarvelStructureSets;
import net.tintankgames.marvel.data.worldgen.MarvelPools;
import net.tintankgames.marvel.data.worldgen.features.MarvelConfiguredFeatures;
import net.tintankgames.marvel.data.worldgen.placements.MarvelPlacements;
import net.tintankgames.marvel.world.damagesources.MarvelDamageTypes;
import net.tintankgames.marvel.world.entity.HydraAgentSkins;
import net.tintankgames.marvel.world.entity.HydraAgentVariants;
import net.tintankgames.marvel.world.item.armortrim.MarvelTrimMaterials;
import net.tintankgames.marvel.world.level.biome.MarvelBiomeModifiers;
import net.tintankgames.marvel.world.level.block.entity.MarvelBannerPatterns;
import net.tintankgames.marvel.world.level.levelgen.structure.MarvelStructures;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class MarvelDynamicRegistryProvider extends DatapackBuiltinEntriesProvider {
    public MarvelDynamicRegistryProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture, addMultiverseData(new RegistrySetBuilder()),
                Set.of(ResourceLocation.DEFAULT_NAMESPACE, MarvelSuperheroes.MOD_ID));
    }

    public static RegistrySetBuilder addMultiverseData(RegistrySetBuilder builder) {
        return builder.add(Registries.CONFIGURED_FEATURE, MarvelConfiguredFeatures::bootstrap)
                .add(Registries.PLACED_FEATURE, MarvelPlacements::bootstrap)
                .add(Registries.STRUCTURE, MarvelStructures::bootstrap)
                .add(Registries.STRUCTURE_SET, MarvelStructureSets::bootstrap)
                .add(Registries.TEMPLATE_POOL, MarvelPools::bootstrap)
                .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, MarvelBiomeModifiers::bootstrap)
                .add(Registries.TRIM_MATERIAL, MarvelTrimMaterials::bootstrap)
                .add(Registries.DAMAGE_TYPE, MarvelDamageTypes::bootstrap)
                .add(Registries.BANNER_PATTERN, MarvelBannerPatterns::bootstrap)
                .add(MarvelRegistries.HYDRA_AGENT_VARIANT, HydraAgentVariants::bootstrap)
                .add(MarvelRegistries.HYDRA_AGENT_SKIN, HydraAgentSkins::bootstrap);
    }
}
