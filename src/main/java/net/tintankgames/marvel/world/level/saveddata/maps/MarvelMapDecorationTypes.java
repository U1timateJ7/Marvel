package net.tintankgames.marvel.world.level.saveddata.maps;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.saveddata.maps.MapDecorationType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tintankgames.marvel.MarvelSuperheroes;

import java.util.function.Supplier;

public class MarvelMapDecorationTypes {
    private static final DeferredRegister<MapDecorationType> REGISTER = DeferredRegister.create(Registries.MAP_DECORATION_TYPE, MarvelSuperheroes.MOD_ID);

    public static final DeferredHolder<MapDecorationType, MapDecorationType> HYDRA = register("hydra", () -> new MapDecorationType(MarvelSuperheroes.id("hydra"), true, 0x3A9D24, true, false));
    public static final DeferredHolder<MapDecorationType, MapDecorationType> HYDRA_HEADQUARTERS = register("hydra_headquarters", () -> new MapDecorationType(MarvelSuperheroes.id("hydra_headquarters"), true, 0xCE2323, true, false));

    private static DeferredHolder<MapDecorationType, MapDecorationType> register(String id, Supplier<MapDecorationType> supplier) {
        return REGISTER.register(id, supplier);
    }

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }
}
