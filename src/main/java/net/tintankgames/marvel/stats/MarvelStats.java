package net.tintankgames.marvel.stats;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tintankgames.marvel.MarvelSuperheroes;

@EventBusSubscriber(modid = MarvelSuperheroes.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class MarvelStats {
    public static final DeferredRegister<ResourceLocation> REGISTER = DeferredRegister.create(Registries.CUSTOM_STAT, MarvelSuperheroes.MOD_ID);

    public static final DeferredHolder<ResourceLocation, ResourceLocation> INTERACT_WITH_SUIT_TABLE = register("interact_with_suit_table");

    private static DeferredHolder<ResourceLocation, ResourceLocation> register(String id) {
        return REGISTER.register(id, () -> MarvelSuperheroes.id(id));
    }

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }

    @SubscribeEvent
    public static void setup(FMLCommonSetupEvent event) {
        Stats.CUSTOM.get(INTERACT_WITH_SUIT_TABLE.get(), StatFormatter.DEFAULT);
    }
}
