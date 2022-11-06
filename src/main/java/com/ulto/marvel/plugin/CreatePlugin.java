package com.ulto.marvel.plugin;

import com.simibubi.create.AllTags;
import com.simibubi.create.Create;
import com.simibubi.create.content.AllSections;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreatePlugin {
    static {
        if (ModList.get().isLoaded("create")) {
            CreateRegistrate REGISTRATE = Create.registrate().creativeModeTab(() -> Create.BASE_CREATIVE_TAB).startSection(AllSections.MATERIALS);

            final ItemEntry<Item> CRUSHED_TITANIUM_ORE = REGISTRATE.item("crushed_titanium_ore", Item::new).tag(AllTags.AllItemTags.CRUSHED_ORES.tag).register();
            final ItemEntry<Item> CRUSHED_PALLADIUM_ORE = REGISTRATE.item("crushed_palladium_ore", Item::new).tag(AllTags.AllItemTags.CRUSHED_ORES.tag).register();
            final ItemEntry<Item> CRUSHED_URU_ORE = REGISTRATE.item("crushed_uru_ore", Item::new).tag(AllTags.AllItemTags.CRUSHED_ORES.tag).register();
            final ItemEntry<Item> CRUSHED_VIBRANIUM_ORE = REGISTRATE.item("crushed_vibranium_ore", Item::new).tag(AllTags.AllItemTags.CRUSHED_ORES.tag).register();
        }
    }
}
