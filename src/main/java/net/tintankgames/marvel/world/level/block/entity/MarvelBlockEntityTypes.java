package net.tintankgames.marvel.world.level.block.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;

import java.util.function.Supplier;

public class MarvelBlockEntityTypes {
    private static final DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MarvelSuperheroes.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SuitTableBlockEntity>> SUIT_TABLE = register("suit_table", () -> BlockEntityType.Builder.of(SuitTableBlockEntity::new, MarvelBlocks.SUIT_TABLE.get()).build(null));

    private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> register(String id, Supplier<BlockEntityType<T>> supplier) {
        return REGISTER.register(id, supplier);
    }

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }
}
