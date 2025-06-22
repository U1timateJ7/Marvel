package net.tintankgames.marvel.core.registries;

import net.minecraft.core.Registry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.attachment.VeronicaSuitPreset;

public class MarvelBuiltInRegistries {
    private static final DeferredRegister<VeronicaSuitPreset> DEFERRED_VERONICA_SUIT_REGISTER = DeferredRegister.create(MarvelRegistries.VERONICA_SUIT_PRESET, MarvelSuperheroes.MOD_ID);

    public static final Registry<VeronicaSuitPreset> VERONICA_SUIT_PRESET = DEFERRED_VERONICA_SUIT_REGISTER.makeRegistry(builder -> {});

    public static void register(IEventBus bus) {
        DEFERRED_VERONICA_SUIT_REGISTER.register(bus);
    }
}
