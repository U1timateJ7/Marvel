package net.tintankgames.marvel.data;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.level.levelgen.structure.MarvelStructures;

public class MarvelStructureSets {
    public static final ResourceKey<StructureSet> HYDRA_BASES = create("hydra_bases");

    private static ResourceKey<StructureSet> create(String id) {
        return ResourceKey.create(Registries.STRUCTURE_SET, MarvelSuperheroes.id(id));
    }

    public static void bootstrap(BootstrapContext<StructureSet> bootstrapContext) {
        HolderGetter<Structure> structure = bootstrapContext.lookup(Registries.STRUCTURE);
        bootstrapContext.register(HYDRA_BASES, new StructureSet(structure.getOrThrow(MarvelStructures.HYDRA_BASE_CLASSIC), new RandomSpreadStructurePlacement(24, 11, RandomSpreadType.LINEAR, 8240938)));
    }
}
