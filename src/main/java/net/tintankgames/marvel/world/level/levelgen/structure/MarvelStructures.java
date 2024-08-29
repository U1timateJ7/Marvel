package net.tintankgames.marvel.world.level.levelgen.structure;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.DimensionPadding;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.data.worldgen.MarvelPools;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;
import net.tintankgames.marvel.world.level.biome.MarvelBiomes;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MarvelStructures {
    public static final ResourceKey<Structure> HYDRA_BASE_CLASSIC = create("hydra_base_classic");
    public static final ResourceKey<Structure> HYDRA_BASE_WINTER = create("hydra_base_winter");

    private static ResourceKey<Structure> create(String id) {
        return ResourceKey.create(Registries.STRUCTURE, MarvelSuperheroes.id(id));
    }

    public static void bootstrap(BootstrapContext<Structure> bootstrapContext) {
        HolderGetter<Biome> biome = bootstrapContext.lookup(Registries.BIOME);
        HolderGetter<StructureTemplatePool> templatePool = bootstrapContext.lookup(Registries.TEMPLATE_POOL);
        bootstrapContext.register(HYDRA_BASE_CLASSIC, new JigsawStructure(new Structure.StructureSettings(biome.getOrThrow(MarvelBiomes.Tags.HAS_HYDRA_BASE_CLASSIC), Map.of(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, WeightedRandomList.create(new MobSpawnSettings.SpawnerData(MarvelEntityTypes.HYDRA_AGENT.get(), 100, 1, 1)))), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_THIN), templatePool.getOrThrow(MarvelPools.HYDRA_BASE_CLASSIC_ENTRANCES), Optional.empty(), 12, ConstantHeight.of(VerticalAnchor.absolute(0)), true, Optional.of(Heightmap.Types.WORLD_SURFACE_WG), 116, List.of(), DimensionPadding.ZERO, LiquidSettings.IGNORE_WATERLOGGING));
        bootstrapContext.register(HYDRA_BASE_WINTER, new JigsawStructure(new Structure.StructureSettings(biome.getOrThrow(MarvelBiomes.Tags.HAS_HYDRA_BASE_WINTER), Map.of(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, WeightedRandomList.create(new MobSpawnSettings.SpawnerData(MarvelEntityTypes.HYDRA_AGENT.get(), 100, 1, 1)))), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_THIN), templatePool.getOrThrow(MarvelPools.HYDRA_BASE_WINTER_ENTRANCES), Optional.empty(), 12, ConstantHeight.of(VerticalAnchor.absolute(0)), true, Optional.of(Heightmap.Types.WORLD_SURFACE_WG), 116, List.of(), DimensionPadding.ZERO, LiquidSettings.IGNORE_WATERLOGGING));
    }

    public static class Tags {
        public static final TagKey<Structure> HYDRA_BASE = create("hydra_base");

        private static TagKey<Structure> create(String id) {
            return TagKey.create(Registries.STRUCTURE, MarvelSuperheroes.id(id));
        }
    }
}
