package net.tintankgames.marvel.world.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.item.component.ShieldArt;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;

import java.util.function.Function;

public class MarvelCreativeModeTabs {
    private static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MarvelSuperheroes.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BLOCKS_AND_ITEMS = register("blocks_and_items", builder -> builder.icon(MarvelItems.VIBRANIUM::toStack).displayItems((parameters, output) -> {
        output.accept(MarvelBlocks.VIBRANIUM_ORE);
        output.accept(MarvelBlocks.DEEPSLATE_VIBRANIUM_ORE);
        output.accept(MarvelBlocks.VIBRANIUM_BLOCK);
        output.accept(MarvelBlocks.TITANIUM_ORE);
        output.accept(MarvelBlocks.DEEPSLATE_TITANIUM_ORE);
        output.accept(MarvelBlocks.TITANIUM_BLOCK);
        output.accept(MarvelBlocks.RAW_TITANIUM_BLOCK);
        output.accept(MarvelBlocks.PALLADIUM_ORE);
        output.accept(MarvelBlocks.DEEPSLATE_PALLADIUM_ORE);
        output.accept(MarvelBlocks.PALLADIUM_BLOCK);
        output.accept(MarvelBlocks.RAW_PALLADIUM_BLOCK);
        output.accept(MarvelBlocks.GOLD_TITANIUM_BLOCK);
        output.accept(MarvelBlocks.ADAMANTIUM_BLOCK);
        output.accept(MarvelBlocks.PROTO_ADAMANTIUM_BLOCK);
        output.accept(MarvelBlocks.URU_BLOCK);
        output.accept(MarvelBlocks.SUIT_TABLE);
        output.accept(MarvelBlocks.SUIT_CHARGER);
        output.accept(MarvelBlocks.GREEN_HYDRA_BRICKS);
        output.accept(MarvelBlocks.GREEN_HYDRA_BRICK_SLAB);
        output.accept(MarvelBlocks.GREEN_HYDRA_BRICK_STAIRS);
        output.accept(MarvelBlocks.GREEN_HYDRA_BRICK_WALL);
        output.accept(MarvelBlocks.GREEN_HYDRA_BRICK_LAMP);
        output.accept(MarvelBlocks.YELLOW_HYDRA_BRICKS);
        output.accept(MarvelBlocks.YELLOW_HYDRA_BRICK_SLAB);
        output.accept(MarvelBlocks.YELLOW_HYDRA_BRICK_STAIRS);
        output.accept(MarvelBlocks.YELLOW_HYDRA_BRICK_WALL);
        output.accept(MarvelBlocks.YELLOW_HYDRA_BRICK_LAMP);
        output.accept(MarvelBlocks.LIGHT_GRAY_HYDRA_BRICKS);
        output.accept(MarvelBlocks.LIGHT_GRAY_HYDRA_BRICK_SLAB);
        output.accept(MarvelBlocks.LIGHT_GRAY_HYDRA_BRICK_STAIRS);
        output.accept(MarvelBlocks.LIGHT_GRAY_HYDRA_BRICK_WALL);
        output.accept(MarvelBlocks.LIGHT_GRAY_HYDRA_BRICK_LAMP);
        output.accept(MarvelBlocks.GRAY_HYDRA_BRICKS);
        output.accept(MarvelBlocks.GRAY_HYDRA_BRICK_SLAB);
        output.accept(MarvelBlocks.GRAY_HYDRA_BRICK_STAIRS);
        output.accept(MarvelBlocks.GRAY_HYDRA_BRICK_WALL);
        output.accept(MarvelBlocks.GRAY_HYDRA_BRICK_LAMP);
        output.accept(MarvelBlocks.STONE_BRICK_LAMP);
        output.accept(MarvelBlocks.DEEPSLATE_BRICK_LAMP);
        output.accept(MarvelBlocks.TESSERACT);
        output.accept(MarvelItems.VIBRANIUM);
        output.accept(MarvelItems.VIBRANIUM_INGOT);
        output.accept(MarvelItems.VIBRANIUM_NUGGET);
        output.accept(MarvelItems.VIBRANIUM_SWORD);
        output.accept(MarvelItems.VIBRANIUM_SHOVEL);
        output.accept(MarvelItems.VIBRANIUM_PICKAXE);
        output.accept(MarvelItems.VIBRANIUM_AXE);
        output.accept(MarvelItems.VIBRANIUM_HOE);
        output.accept(MarvelItems.VIBRANIUM_HELMET);
        output.accept(MarvelItems.VIBRANIUM_CHESTPLATE);
        output.accept(MarvelItems.VIBRANIUM_LEGGINGS);
        output.accept(MarvelItems.VIBRANIUM_BOOTS);
        output.accept(MarvelItems.RAW_TITANIUM);
        output.accept(MarvelItems.TITANIUM_INGOT);
        output.accept(MarvelItems.TITANIUM_NUGGET);
        output.accept(MarvelItems.RAW_PALLADIUM);
        output.accept(MarvelItems.PALLADIUM_INGOT);
        output.accept(MarvelItems.PALLADIUM_NUGGET);
        output.accept(MarvelItems.GOLD_TITANIUM_INGOT);
        output.accept(MarvelItems.GOLD_TITANIUM_NUGGET);
        output.accept(MarvelItems.ADAMANTIUM_INGOT);
        output.accept(MarvelItems.ADAMANTIUM_NUGGET);
        output.accept(MarvelItems.PROTO_ADAMANTIUM_INGOT);
        output.accept(MarvelItems.PROTO_ADAMANTIUM_NUGGET);
        output.accept(MarvelItems.URU_INGOT);
        output.accept(MarvelItems.URU_NUGGET);
        output.accept(MarvelItems.REINFORCED_LEATHER);
        output.accept(MarvelItems.VIBRANIUM_WEAVE);
        output.accept(MarvelItems.VIBRANIUM_NANITES);
        output.accept(MarvelItems.KINETIC_BLACK_PANTHER_NECKLACE);
        output.accept(MarvelItems.KILLMONGER_NECKLACE);
        output.accept(MarvelItems.BLACK_PANTHER_SHURI_NECKLACE);
        output.accept(MarvelItems.WEB_FLUID);
        output.accept(MarvelItems.UNSTABLE_PYM_PARTICLE);
        output.accept(MarvelItems.PYM_PARTICLE);
        output.accept(MarvelItems.SYRINGE);
        output.accept(MarvelItems.VILLAGER_BLOOD_SAMPLE);
        output.accept(MarvelItems.X_GENES);
        output.accept(MarvelItems.PALLADIUM_ARC_REACTOR);
        output.accept(MarvelItems.DIAMOND_ARC_REACTOR);
        output.accept(MarvelItems.IRON_MAN_MARK_5_SUITCASE);
        output.accept(MarvelItems.VERONICA_SATELLITE);
        output.accept(MarvelItems.VERONICA_REPAIR_MODULE_MARK_1);
        output.accept(MarvelItems.VERONICA_REPAIR_MODULE_MARK_2);
        output.accept(MarvelItems.VERONICA_REMOTE);
        output.accept(MarvelItems.HYDRA_BANNER_PATTERN);
        output.accept(MarvelItems.TESSERACT_SHARD);
        output.accept(MarvelItems.SPACE_STONE);
        output.accept(MarvelItems.KATANA_UPGRADE_SMITHING_TEMPLATE);
        output.accept(MarvelItems.HYDRA_AGENT_SPAWN_EGG);
        output.accept(MarvelItems.BARON_ZEMO_SPAWN_EGG);
        output.accept(MarvelItems.WINTER_SOLDIER_SPAWN_EGG);
        output.accept(MarvelItems.RED_SKULL_SPAWN_EGG);
    }));
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WEAPONS = register("weapons", builder -> builder.withTabsBefore(BLOCKS_AND_ITEMS.getKey()).icon(() -> {
        ItemStack stack = MarvelItems.PROTO_ADAMANTIUM_SHIELD.toStack();
        stack.set(MarvelDataComponents.SHIELD_ART, ShieldArt.CAPTAIN_AMERICA);
        return stack;
    }).displayItems((parameters, output) -> {
        for (ShieldArt art : ShieldArt.values()) {
            ItemStack stack = MarvelItems.VIBRANIUM_SHIELD.toStack();
            stack.set(MarvelDataComponents.SHIELD_ART, art);
            output.accept(stack);
        }
        for (ShieldArt art : ShieldArt.values()) {
            ItemStack stack = MarvelItems.PROTO_ADAMANTIUM_SHIELD.toStack();
            stack.set(MarvelDataComponents.SHIELD_ART, art);
            output.accept(stack);
        }
        output.accept(MarvelItems.MJOLNIR);
        output.accept(MarvelItems.STORMBREAKER);
        output.accept(MarvelItems.TESSERACT_CROSSBOW);
        output.accept(MarvelItems.WINTER_SOLDIER_KNIFE);
        output.accept(MarvelItems.KATANAS);
    }));
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SUITS = register("suits", builder -> builder.withTabsBefore(WEAPONS.getKey()).icon(MarvelItems.CAPTAIN_AMERICA_CHESTPLATE::toStack).displayItems((parameters, output) -> {
        output.accept(MarvelItems.CAPTAIN_AMERICA_HELMET);
        output.accept(MarvelItems.CAPTAIN_AMERICA_CHESTPLATE);
        output.accept(MarvelItems.CAPTAIN_AMERICA_LEGGINGS);
        output.accept(MarvelItems.CAPTAIN_AMERICA_BOOTS);
        output.accept(MarvelItems.CAPTAIN_AMERICA_STEALTH_HELMET);
        output.accept(MarvelItems.CAPTAIN_AMERICA_STEALTH_CHESTPLATE);
        output.accept(MarvelItems.CAPTAIN_AMERICA_STEALTH_LEGGINGS);
        output.accept(MarvelItems.CAPTAIN_AMERICA_STEALTH_BOOTS);
        output.accept(MarvelItems.CAPTAIN_CARTER_HELMET);
        output.accept(MarvelItems.CAPTAIN_CARTER_CHESTPLATE);
        output.accept(MarvelItems.CAPTAIN_CARTER_LEGGINGS);
        output.accept(MarvelItems.CAPTAIN_CARTER_BOOTS);
        output.accept(MarvelItems.RED_GUARDIAN_HELMET);
        output.accept(MarvelItems.RED_GUARDIAN_CHESTPLATE);
        output.accept(MarvelItems.RED_GUARDIAN_LEGGINGS);
        output.accept(MarvelItems.RED_GUARDIAN_BOOTS);
        output.accept(MarvelItems.BLACK_PANTHER_HELMET);
        output.accept(MarvelItems.BLACK_PANTHER_CHESTPLATE);
        output.accept(MarvelItems.BLACK_PANTHER_LEGGINGS);
        output.accept(MarvelItems.BLACK_PANTHER_BOOTS);
        output.accept(MarvelItems.KINETIC_BLACK_PANTHER_HELMET);
        output.accept(MarvelItems.KINETIC_BLACK_PANTHER_CHESTPLATE);
        output.accept(MarvelItems.KINETIC_BLACK_PANTHER_LEGGINGS);
        output.accept(MarvelItems.KINETIC_BLACK_PANTHER_BOOTS);
        output.accept(MarvelItems.KILLMONGER_HELMET);
        output.accept(MarvelItems.KILLMONGER_CHESTPLATE);
        output.accept(MarvelItems.KILLMONGER_LEGGINGS);
        output.accept(MarvelItems.KILLMONGER_BOOTS);
        output.accept(MarvelItems.BLACK_PANTHER_SHURI_HELMET);
        output.accept(MarvelItems.BLACK_PANTHER_SHURI_CHESTPLATE);
        output.accept(MarvelItems.BLACK_PANTHER_SHURI_LEGGINGS);
        output.accept(MarvelItems.BLACK_PANTHER_SHURI_BOOTS);
        output.accept(MarvelItems.WOLVERINE_HELMET);
        output.accept(MarvelItems.WOLVERINE_CHESTPLATE);
        output.accept(MarvelItems.WOLVERINE_SLEEVELESS_CHESTPLATE);
        output.accept(MarvelItems.WOLVERINE_LEGGINGS);
        output.accept(MarvelItems.WOLVERINE_BOOTS);
        output.accept(MarvelItems.WOLVERINE_BROWN_HELMET);
        output.accept(MarvelItems.WOLVERINE_BROWN_CHESTPLATE);
        output.accept(MarvelItems.WOLVERINE_BROWN_SLEEVELESS_CHESTPLATE);
        output.accept(MarvelItems.WOLVERINE_BROWN_LEGGINGS);
        output.accept(MarvelItems.WOLVERINE_BROWN_BOOTS);
        output.accept(MarvelItems.WOLVERINE_X_FORCE_HELMET);
        output.accept(MarvelItems.WOLVERINE_X_FORCE_CHESTPLATE);
        output.accept(MarvelItems.WOLVERINE_X_FORCE_SLEEVELESS_CHESTPLATE);
        output.accept(MarvelItems.WOLVERINE_X_FORCE_LEGGINGS);
        output.accept(MarvelItems.WOLVERINE_X_FORCE_BOOTS);
        output.accept(MarvelItems.CYCLOPS_HELMET);
        output.accept(MarvelItems.CYCLOPS_CHESTPLATE);
        output.accept(MarvelItems.CYCLOPS_LEGGINGS);
        output.accept(MarvelItems.CYCLOPS_BOOTS);
        output.accept(MarvelItems.CYCLOPS_ASTONISHING_HELMET);
        output.accept(MarvelItems.CYCLOPS_ASTONISHING_CHESTPLATE);
        output.accept(MarvelItems.CYCLOPS_ASTONISHING_LEGGINGS);
        output.accept(MarvelItems.CYCLOPS_ASTONISHING_BOOTS);
        output.accept(MarvelItems.SPIDER_MAN_HELMET);
        output.accept(MarvelItems.SPIDER_MAN_CHESTPLATE);
        output.accept(MarvelItems.SPIDER_MAN_LEGGINGS);
        output.accept(MarvelItems.SPIDER_MAN_BOOTS);
        output.accept(MarvelItems.SPIDER_MAN_MCU_HELMET);
        output.accept(MarvelItems.SPIDER_MAN_MCU_CHESTPLATE);
        output.accept(MarvelItems.SPIDER_MAN_MCU_LEGGINGS);
        output.accept(MarvelItems.SPIDER_MAN_MCU_BOOTS);
        output.accept(MarvelItems.MILES_MORALES_HELMET);
        output.accept(MarvelItems.MILES_MORALES_CHESTPLATE);
        output.accept(MarvelItems.MILES_MORALES_LEGGINGS);
        output.accept(MarvelItems.MILES_MORALES_BOOTS);
        output.accept(MarvelItems.SPIDER_GWEN_HELMET);
        output.accept(MarvelItems.SPIDER_GWEN_CHESTPLATE);
        output.accept(MarvelItems.SPIDER_GWEN_LEGGINGS);
        output.accept(MarvelItems.SPIDER_GWEN_BOOTS);
        output.accept(MarvelItems.SPIDER_MAN_INSOMNIAC_HELMET);
        output.accept(MarvelItems.SPIDER_MAN_INSOMNIAC_CHESTPLATE);
        output.accept(MarvelItems.SPIDER_MAN_INSOMNIAC_LEGGINGS);
        output.accept(MarvelItems.SPIDER_MAN_INSOMNIAC_BOOTS);
        output.accept(MarvelItems.ANT_MAN_HELMET);
        output.accept(MarvelItems.ANT_MAN_CHESTPLATE);
        output.accept(MarvelItems.ANT_MAN_LEGGINGS);
        output.accept(MarvelItems.ANT_MAN_BOOTS);
        output.accept(MarvelItems.ANT_MAN_UPGRADED_HELMET);
        output.accept(MarvelItems.ANT_MAN_UPGRADED_CHESTPLATE);
        output.accept(MarvelItems.ANT_MAN_UPGRADED_LEGGINGS);
        output.accept(MarvelItems.ANT_MAN_UPGRADED_BOOTS);
        output.accept(MarvelItems.WASP_HELMET);
        output.accept(MarvelItems.WASP_CHESTPLATE);
        output.accept(MarvelItems.WASP_LEGGINGS);
        output.accept(MarvelItems.WASP_BOOTS);
        output.accept(MarvelItems.WAR_MACHINE_MARK_1_HELMET);
        output.accept(MarvelItems.WAR_MACHINE_MARK_1_CHESTPLATE);
        output.accept(MarvelItems.WAR_MACHINE_MARK_1_LEGGINGS);
        output.accept(MarvelItems.WAR_MACHINE_MARK_1_BOOTS);
        output.accept(MarvelItems.WAR_MACHINE_MARK_2_HELMET);
        output.accept(MarvelItems.WAR_MACHINE_MARK_2_CHESTPLATE);
        output.accept(MarvelItems.WAR_MACHINE_MARK_2_LEGGINGS);
        output.accept(MarvelItems.WAR_MACHINE_MARK_2_BOOTS);
        output.accept(MarvelItems.IRON_PATRIOT_HELMET);
        output.accept(MarvelItems.IRON_PATRIOT_CHESTPLATE);
        output.accept(MarvelItems.IRON_PATRIOT_LEGGINGS);
        output.accept(MarvelItems.IRON_PATRIOT_BOOTS);
        output.accept(MarvelItems.QUICKSILVER_CHESTPLATE);
        output.accept(MarvelItems.QUICKSILVER_LEGGINGS);
        output.accept(MarvelItems.QUICKSILVER_BOOTS);
        output.accept(MarvelItems.QUICKSILVER_MCU_CHESTPLATE);
        output.accept(MarvelItems.QUICKSILVER_MCU_LEGGINGS);
        output.accept(MarvelItems.QUICKSILVER_MCU_BOOTS);
        output.accept(MarvelItems.DEADPOOL_HELMET);
        output.accept(MarvelItems.DEADPOOL_CHESTPLATE);
        output.accept(MarvelItems.DEADPOOL_LEGGINGS);
        output.accept(MarvelItems.DEADPOOL_BOOTS);
        output.accept(MarvelItems.DEADPOOL_X_FORCE_HELMET);
        output.accept(MarvelItems.DEADPOOL_X_FORCE_CHESTPLATE);
        output.accept(MarvelItems.DEADPOOL_X_FORCE_LEGGINGS);
        output.accept(MarvelItems.DEADPOOL_X_FORCE_BOOTS);
        output.accept(MarvelItems.CAPTAIN_MARVEL_HELMET);
        output.accept(MarvelItems.CAPTAIN_MARVEL_CHESTPLATE);
        output.accept(MarvelItems.CAPTAIN_MARVEL_LEGGINGS);
        output.accept(MarvelItems.CAPTAIN_MARVEL_BOOTS);
        output.accept(MarvelItems.CAPTAIN_MARVEL_838_HELMET);
        output.accept(MarvelItems.CAPTAIN_MARVEL_838_CHESTPLATE);
        output.accept(MarvelItems.CAPTAIN_MARVEL_838_LEGGINGS);
        output.accept(MarvelItems.CAPTAIN_MARVEL_838_BOOTS);
        output.accept(MarvelItems.WINTER_SOLDIER_CHESTPLATE);
        output.accept(MarvelItems.WINTER_SOLDIER_LEGGINGS);
        output.accept(MarvelItems.WINTER_SOLDIER_BOOTS);
    }));
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> IRON_MAN_SUITS = register("iron_man_suits", builder -> builder.withTabsBefore(SUITS.getKey()).icon(MarvelItems.IRON_MAN_MARK_7_CHESTPLATE::toStack).displayItems((parameters, output) -> {
        output.accept(MarvelItems.IRON_MAN_MARK_1_HELMET);
        output.accept(MarvelItems.IRON_MAN_MARK_1_CHESTPLATE);
        output.accept(MarvelItems.IRON_MAN_MARK_1_LEGGINGS);
        output.accept(MarvelItems.IRON_MAN_MARK_1_BOOTS);
        output.accept(MarvelItems.IRON_MAN_MARK_2_HELMET);
        output.accept(MarvelItems.IRON_MAN_MARK_2_CHESTPLATE);
        output.accept(MarvelItems.IRON_MAN_MARK_2_LEGGINGS);
        output.accept(MarvelItems.IRON_MAN_MARK_2_BOOTS);
        output.accept(MarvelItems.IRON_MAN_MARK_3_HELMET);
        output.accept(MarvelItems.IRON_MAN_MARK_3_CHESTPLATE);
        output.accept(MarvelItems.IRON_MAN_MARK_3_LEGGINGS);
        output.accept(MarvelItems.IRON_MAN_MARK_3_BOOTS);
        output.accept(MarvelItems.IRON_MAN_MARK_5_HELMET);
        output.accept(MarvelItems.IRON_MAN_MARK_5_CHESTPLATE);
        output.accept(MarvelItems.IRON_MAN_MARK_5_LEGGINGS);
        output.accept(MarvelItems.IRON_MAN_MARK_5_BOOTS);
        output.accept(MarvelItems.IRON_MAN_MARK_6_HELMET);
        output.accept(MarvelItems.IRON_MAN_MARK_6_CHESTPLATE);
        output.accept(MarvelItems.IRON_MAN_MARK_6_LEGGINGS);
        output.accept(MarvelItems.IRON_MAN_MARK_6_BOOTS);
        output.accept(MarvelItems.IRON_MAN_MARK_7_HELMET);
        output.accept(MarvelItems.IRON_MAN_MARK_7_CHESTPLATE);
        output.accept(MarvelItems.IRON_MAN_MARK_7_LEGGINGS);
        output.accept(MarvelItems.IRON_MAN_MARK_7_BOOTS);
        output.accept(MarvelItems.IRON_MAN_MARK_11_HELMET);
        output.accept(MarvelItems.IRON_MAN_MARK_11_CHESTPLATE);
        output.accept(MarvelItems.IRON_MAN_MARK_11_LEGGINGS);
        output.accept(MarvelItems.IRON_MAN_MARK_11_BOOTS);
        output.accept(MarvelItems.IRON_MAN_MARK_15_HELMET);
        output.accept(MarvelItems.IRON_MAN_MARK_15_CHESTPLATE);
        output.accept(MarvelItems.IRON_MAN_MARK_15_LEGGINGS);
        output.accept(MarvelItems.IRON_MAN_MARK_15_BOOTS);
        output.accept(MarvelItems.IRON_MAN_MARK_17_HELMET);
        output.accept(MarvelItems.IRON_MAN_MARK_17_CHESTPLATE);
        output.accept(MarvelItems.IRON_MAN_MARK_17_LEGGINGS);
        output.accept(MarvelItems.IRON_MAN_MARK_17_BOOTS);
        output.accept(MarvelItems.IRON_MAN_MARK_19_HELMET);
        output.accept(MarvelItems.IRON_MAN_MARK_19_CHESTPLATE);
        output.accept(MarvelItems.IRON_MAN_MARK_19_LEGGINGS);
        output.accept(MarvelItems.IRON_MAN_MARK_19_BOOTS);
        output.accept(MarvelItems.IRON_MAN_MARK_20_HELMET);
        output.accept(MarvelItems.IRON_MAN_MARK_20_CHESTPLATE);
        output.accept(MarvelItems.IRON_MAN_MARK_20_LEGGINGS);
        output.accept(MarvelItems.IRON_MAN_MARK_20_BOOTS);
        output.accept(MarvelItems.IRON_MAN_MARK_21_HELMET);
        output.accept(MarvelItems.IRON_MAN_MARK_21_CHESTPLATE);
        output.accept(MarvelItems.IRON_MAN_MARK_21_LEGGINGS);
        output.accept(MarvelItems.IRON_MAN_MARK_21_BOOTS);
        output.accept(MarvelItems.IRON_MAN_MARK_22_HELMET);
        output.accept(MarvelItems.IRON_MAN_MARK_22_CHESTPLATE);
        output.accept(MarvelItems.IRON_MAN_MARK_22_LEGGINGS);
        output.accept(MarvelItems.IRON_MAN_MARK_22_BOOTS);
    }));

    private static DeferredHolder<CreativeModeTab, CreativeModeTab> register(String id, Function<CreativeModeTab.Builder, CreativeModeTab.Builder> builderConsumer) {
        return REGISTER.register(id, () -> builderConsumer.apply(CreativeModeTab.builder().title(Component.translatable(MarvelSuperheroes.id(id).toLanguageKey("itemGroup")))).build());
    }

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }
}
