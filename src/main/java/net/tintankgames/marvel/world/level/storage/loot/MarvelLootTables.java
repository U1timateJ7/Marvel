package net.tintankgames.marvel.world.level.storage.loot;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import net.tintankgames.marvel.MarvelSuperheroes;

public class MarvelLootTables {
    public static final ResourceKey<LootTable> HYDRA_BASE = create("chests/hydra_base");
    public static final ResourceKey<LootTable> HYDRA_BASE_MAP = create("chests/hydra_base_map");
    public static final ResourceKey<LootTable> HYDRA_OUTPOST = create("chests/hydra_outpost");

    private static ResourceKey<LootTable> create(String id) {
        return ResourceKey.create(Registries.LOOT_TABLE, MarvelSuperheroes.id(id));
    }
}
