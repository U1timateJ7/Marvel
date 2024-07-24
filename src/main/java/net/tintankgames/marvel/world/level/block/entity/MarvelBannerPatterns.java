package net.tintankgames.marvel.world.level.block.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BannerPatterns;
import net.tintankgames.marvel.MarvelSuperheroes;

public class MarvelBannerPatterns {
    public static final ResourceKey<BannerPattern> HYDRA = create("hydra");

    private static ResourceKey<BannerPattern> create(String id) {
        return ResourceKey.create(Registries.BANNER_PATTERN, MarvelSuperheroes.id(id));
    }

    public static void bootstrap(BootstrapContext<BannerPattern> bootstrapContext) {
        BannerPatterns.register(bootstrapContext, HYDRA);
    }

    public static class Tags {
        public static final TagKey<BannerPattern> PATTERN_ITEM_HYDRA = create("pattern_item/hydra");

        private static TagKey<BannerPattern> create(String id) {
            return TagKey.create(Registries.BANNER_PATTERN, MarvelSuperheroes.id(id));
        }
    }
}
