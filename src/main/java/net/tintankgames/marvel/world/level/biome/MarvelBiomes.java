package net.tintankgames.marvel.world.level.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.tintankgames.marvel.MarvelSuperheroes;

public class MarvelBiomes {
    public static class Tags {
        public static final TagKey<Biome> HAS_HYDRA_BASE_CLASSIC = create("has_structure/hydra_base_classic");
        public static final TagKey<Biome> HAS_HYDRA_BASE_WINTER = create("has_structure/hydra_base_winter");
        public static final TagKey<Biome> HAS_HYDRA_BASE_MOUNTAIN = create("has_structure/hydra_base_mountain");
        public static final TagKey<Biome> HAS_HYDRA_OUTPOST_CLASSIC = create("has_structure/hydra_outpost_classic");
        public static final TagKey<Biome> HAS_HYDRA_OUTPOST_WINTER = create("has_structure/hydra_outpost_winter");
        public static final TagKey<Biome> HAS_HYDRA_OUTPOST_MOUNTAIN = create("has_structure/hydra_outpost_mountain");
        public static final TagKey<Biome> SPAWNS_CLASSIC_HYDRA_AGENTS = create("spawns_classic_hydra_agents");
        public static final TagKey<Biome> SPAWNS_WINTER_HYDRA_AGENTS = create("spawns_winter_hydra_agents");
        public static final TagKey<Biome> SPAWNS_MOUNTAIN_HYDRA_AGENTS = create("spawns_mountain_hydra_agents");

        private static TagKey<Biome> create(String id) {
            return TagKey.create(Registries.BIOME, MarvelSuperheroes.id(id));
        }
    }
}
