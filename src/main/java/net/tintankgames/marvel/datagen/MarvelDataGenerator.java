package net.tintankgames.marvel.datagen;

import net.minecraft.data.DataProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.tintankgames.marvel.MarvelSuperheroes;

import java.util.Collections;
import java.util.List;

@EventBusSubscriber(modid = MarvelSuperheroes.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class MarvelDataGenerator {
    @SubscribeEvent
    public static void dataGen(GatherDataEvent event) {
        event.getGenerator().addProvider(event.includeServer(), (DataProvider.Factory<MarvelDynamicRegistryProvider>) output -> new MarvelDynamicRegistryProvider(output, event.getLookupProvider()));
        event.getGenerator().addProvider(event.includeServer(), (DataProvider.Factory<MarvelRecipeProvider>) output -> new MarvelRecipeProvider(output, event.getLookupProvider()));
        event.getGenerator().addProvider(event.includeServer(), (DataProvider.Factory<AdvancementProvider>) output -> new AdvancementProvider(output, event.getLookupProvider(), event.getExistingFileHelper(), List.of(new MarvelAdvancementProvider())));
        event.getGenerator().addProvider(event.includeServer(), (DataProvider.Factory<LootTableProvider>) output -> new LootTableProvider(output, Collections.emptySet(), List.of(
                new LootTableProvider.SubProviderEntry(MarvelLootTableProvider.BlockLoot::new, LootContextParamSets.BLOCK)), event.getLookupProvider()));
        MarvelTagProvider.addProviders(event);
    }
}
