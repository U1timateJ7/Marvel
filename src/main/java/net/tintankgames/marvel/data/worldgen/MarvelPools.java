package net.tintankgames.marvel.data.worldgen;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.tintankgames.marvel.MarvelSuperheroes;

import java.util.List;

public class MarvelPools {
    public static final ResourceKey<StructureTemplatePool> HYDRA_BASE_CLASSIC_ENTRANCES = create("hydra_base/classic/entrances");
    public static final ResourceKey<StructureTemplatePool> HYDRA_BASE_CLASSIC_STAIRCASES = create("hydra_base/classic/staircases");
    public static final ResourceKey<StructureTemplatePool> HYDRA_BASE_CLASSIC_HALLWAYS = create("hydra_base/classic/hallways");
    public static final ResourceKey<StructureTemplatePool> HYDRA_BASE_CLASSIC_HALLWAY_ENTRANCES = create("hydra_base/classic/hallway_entrances");
    public static final ResourceKey<StructureTemplatePool> HYDRA_BASE_CLASSIC_HALL_TERMINATORS = create("hydra_base/classic/hall_terminators");
    public static final ResourceKey<StructureTemplatePool> HYDRA_BASE_CLASSIC_ROOMS = create("hydra_base/classic/rooms");
    public static final ResourceKey<StructureTemplatePool> HYDRA_BASE_CLASSIC_ROOM_TERMINATORS = create("hydra_base/classic/room_terminators");
    public static final ResourceKey<StructureTemplatePool> HYDRA_BASE_CLASSIC_BOSS_HALLWAYS = create("hydra_base/classic/boss_hallways");
    public static final ResourceKey<StructureTemplatePool> HYDRA_BASE_CLASSIC_BOSS_ROOMS = create("hydra_base/classic/boss_rooms");
    public static final ResourceKey<StructureTemplatePool> HYDRA_BASE_CLASSIC_AGENTS = create("hydra_base/classic/agents");
    public static final ResourceKey<StructureTemplatePool> HYDRA_BASE_CLASSIC_PRISONERS = create("hydra_base/classic/prisoners");
    public static final ResourceKey<StructureTemplatePool> HYDRA_BASE_CLASSIC_PRISONER_TERMINATORS = create("hydra_base/classic/prisoner_terminators");

    private static ResourceKey<StructureTemplatePool> create(String id) {
        return ResourceKey.create(Registries.TEMPLATE_POOL, MarvelSuperheroes.id(id));
    }

    public static void bootstrap(BootstrapContext<StructureTemplatePool> bootstrapContext) {
        HolderGetter<StructureTemplatePool> templatePool = bootstrapContext.lookup(Registries.TEMPLATE_POOL);
        bootstrapContext.register(HYDRA_BASE_CLASSIC_ENTRANCES, new StructureTemplatePool(templatePool.getOrThrow(Pools.EMPTY), List.of(Pair.of(StructurePoolElement.single(MarvelSuperheroes.id("hydra_base/classic/entrance/entrance_01").toString()).apply(StructureTemplatePool.Projection.RIGID), 1))));
        bootstrapContext.register(HYDRA_BASE_CLASSIC_STAIRCASES, new StructureTemplatePool(templatePool.getOrThrow(Pools.EMPTY), List.of(Pair.of(StructurePoolElement.single(MarvelSuperheroes.id("hydra_base/classic/staircase/staircase_01").toString()).apply(StructureTemplatePool.Projection.RIGID), 1))));
        Holder<StructureTemplatePool> classicHallTerminators = bootstrapContext.register(HYDRA_BASE_CLASSIC_HALL_TERMINATORS, new StructureTemplatePool(templatePool.getOrThrow(Pools.EMPTY), List.of(Pair.of(StructurePoolElement.single(MarvelSuperheroes.id("hydra_base/classic/terminator/hall_01").toString()).apply(StructureTemplatePool.Projection.RIGID), 1))));
        bootstrapContext.register(HYDRA_BASE_CLASSIC_HALLWAYS, new StructureTemplatePool(classicHallTerminators, List.of(Pair.of(StructurePoolElement.single(MarvelSuperheroes.id("hydra_base/classic/hallway/straight_01").toString()).apply(StructureTemplatePool.Projection.RIGID), 2), Pair.of(StructurePoolElement.single(MarvelSuperheroes.id("hydra_base/classic/hallway/straight_02").toString()).apply(StructureTemplatePool.Projection.RIGID), 1), Pair.of(StructurePoolElement.single(MarvelSuperheroes.id("hydra_base/classic/hallway/straight_03").toString()).apply(StructureTemplatePool.Projection.RIGID), 1), Pair.of(StructurePoolElement.single(MarvelSuperheroes.id("hydra_base/classic/hallway/corner_01").toString()).apply(StructureTemplatePool.Projection.RIGID), 1), Pair.of(StructurePoolElement.single(MarvelSuperheroes.id("hydra_base/classic/hallway/t_01").toString()).apply(StructureTemplatePool.Projection.RIGID), 1))));
        bootstrapContext.register(HYDRA_BASE_CLASSIC_HALLWAY_ENTRANCES, new StructureTemplatePool(classicHallTerminators, List.of(Pair.of(StructurePoolElement.single(MarvelSuperheroes.id("hydra_base/classic/hallway/entrance_01").toString()).apply(StructureTemplatePool.Projection.RIGID), 1))));
        Holder<StructureTemplatePool> classicRoomTerminators = bootstrapContext.register(HYDRA_BASE_CLASSIC_ROOM_TERMINATORS, new StructureTemplatePool(templatePool.getOrThrow(Pools.EMPTY), List.of(Pair.of(StructurePoolElement.single(MarvelSuperheroes.id("hydra_base/classic/terminator/room_01").toString()).apply(StructureTemplatePool.Projection.RIGID), 1))));
        bootstrapContext.register(HYDRA_BASE_CLASSIC_ROOMS, new StructureTemplatePool(classicRoomTerminators, List.of(Pair.of(StructurePoolElement.single(MarvelSuperheroes.id("hydra_base/classic/room/room_01").toString()).apply(StructureTemplatePool.Projection.RIGID), 1), Pair.of(StructurePoolElement.single(MarvelSuperheroes.id("hydra_base/classic/room/library_01").toString()).apply(StructureTemplatePool.Projection.RIGID), 1), Pair.of(StructurePoolElement.single(MarvelSuperheroes.id("hydra_base/classic/room/prison_01").toString()).apply(StructureTemplatePool.Projection.RIGID), 1), Pair.of(StructurePoolElement.single(MarvelSuperheroes.id("hydra_base/classic/room/range_01").toString()).apply(StructureTemplatePool.Projection.RIGID), 1), Pair.of(StructurePoolElement.single(MarvelSuperheroes.id("hydra_base/classic/room/experiment_01").toString()).apply(StructureTemplatePool.Projection.RIGID), 1), Pair.of(StructurePoolElement.single(MarvelSuperheroes.id("hydra_base/classic/room/map_01").toString()).apply(StructureTemplatePool.Projection.RIGID), 1))));
        Holder<StructureTemplatePool> classicBossRooms = bootstrapContext.register(HYDRA_BASE_CLASSIC_BOSS_ROOMS, new StructureTemplatePool(templatePool.getOrThrow(Pools.EMPTY), List.of(Pair.of(StructurePoolElement.single(MarvelSuperheroes.id("hydra_base/classic/room/zemo_01").toString()).apply(StructureTemplatePool.Projection.RIGID), 1))));
        bootstrapContext.register(HYDRA_BASE_CLASSIC_BOSS_HALLWAYS, new StructureTemplatePool(classicBossRooms, List.of(Pair.of(StructurePoolElement.single(MarvelSuperheroes.id("hydra_base/classic/boss_hallway/straight_01").toString()).apply(StructureTemplatePool.Projection.RIGID), 2), Pair.of(StructurePoolElement.single(MarvelSuperheroes.id("hydra_base/classic/boss_hallway/straight_02").toString()).apply(StructureTemplatePool.Projection.RIGID), 1), Pair.of(StructurePoolElement.single(MarvelSuperheroes.id("hydra_base/classic/boss_hallway/straight_03").toString()).apply(StructureTemplatePool.Projection.RIGID), 1), Pair.of(StructurePoolElement.single(MarvelSuperheroes.id("hydra_base/classic/boss_hallway/corner_01").toString()).apply(StructureTemplatePool.Projection.RIGID), 1))));
        bootstrapContext.register(HYDRA_BASE_CLASSIC_AGENTS, new StructureTemplatePool(templatePool.getOrThrow(Pools.EMPTY), List.of(Pair.of(StructurePoolElement.single(MarvelSuperheroes.id("hydra_base/classic/agent/agent_01").toString()).apply(StructureTemplatePool.Projection.RIGID), 1))));
        Holder<StructureTemplatePool> classicPrisonerTerminators = bootstrapContext.register(HYDRA_BASE_CLASSIC_PRISONERS, new StructureTemplatePool(templatePool.getOrThrow(Pools.EMPTY), List.of(Pair.of(StructurePoolElement.single(MarvelSuperheroes.id("hydra_base/classic/prisoner/prisoner_01").toString()).apply(StructureTemplatePool.Projection.RIGID), 1))));
        bootstrapContext.register(HYDRA_BASE_CLASSIC_PRISONER_TERMINATORS, new StructureTemplatePool(classicPrisonerTerminators, List.of(Pair.of(StructurePoolElement.single(MarvelSuperheroes.id("hydra_base/classic/prisoner/prisoner_01").toString()).apply(StructureTemplatePool.Projection.RIGID), 1))));
    }
}
