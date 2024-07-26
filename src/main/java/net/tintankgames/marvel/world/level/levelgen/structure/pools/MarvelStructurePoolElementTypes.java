package net.tintankgames.marvel.world.level.levelgen.structure.pools;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tintankgames.marvel.MarvelSuperheroes;

public class MarvelStructurePoolElementTypes {
    private static final DeferredRegister<StructurePoolElementType<?>> REGISTER = DeferredRegister.create(Registries.STRUCTURE_POOL_ELEMENT, MarvelSuperheroes.MOD_ID);

    public static final DeferredHolder<StructurePoolElementType<?>, StructurePoolElementType<UndergroundPoolElement>> UNDERGROUND = register("underground_pool_element", UndergroundPoolElement.CODEC);

    private static <T extends StructurePoolElement> DeferredHolder<StructurePoolElementType<?>, StructurePoolElementType<T>> register(String id, MapCodec<T> codec) {
        return REGISTER.register(id, () -> () -> codec);
    }

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }
}
