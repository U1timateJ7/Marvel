package net.tintankgames.marvel.world.level.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.tintankgames.marvel.MarvelSuperheroes;

public class MarvelBiomes {
    public static class Tags {
        public static final TagKey<Biome> HAS_HYDRA_BASE_CLASSIC = create("has_structure/hydra_base_classic");

        private static TagKey<Biome> create(String id) {
            return TagKey.create(Registries.BIOME, MarvelSuperheroes.id(id));
        }
    }
}
