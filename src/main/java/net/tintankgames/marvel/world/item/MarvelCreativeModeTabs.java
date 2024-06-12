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
        output.accept(MarvelBlocks.SUIT_TABLE);
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
        output.accept(MarvelItems.REINFORCED_LEATHER);
        output.accept(MarvelItems.VIBRANIUM_WEAVE);
        output.accept(MarvelItems.VIBRANIUM_NANITES);
        output.accept(MarvelItems.KINETIC_BLACK_PANTHER_NECKLACE);
        output.accept(MarvelItems.KILLMONGER_NECKLACE);
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
        output.accept(MarvelItems.CYCLOPS_HELMET);
        output.accept(MarvelItems.CYCLOPS_CHESTPLATE);
        output.accept(MarvelItems.CYCLOPS_LEGGINGS);
        output.accept(MarvelItems.CYCLOPS_BOOTS);
        output.accept(MarvelItems.CYCLOPS_DARK_HELMET);
        output.accept(MarvelItems.CYCLOPS_DARK_CHESTPLATE);
        output.accept(MarvelItems.CYCLOPS_DARK_LEGGINGS);
        output.accept(MarvelItems.CYCLOPS_DARK_BOOTS);
        output.accept(MarvelItems.SPIDER_MAN_HELMET);
        output.accept(MarvelItems.SPIDER_MAN_CHESTPLATE);
        output.accept(MarvelItems.SPIDER_MAN_LEGGINGS);
        output.accept(MarvelItems.SPIDER_MAN_BOOTS);
    }));

    private static DeferredHolder<CreativeModeTab, CreativeModeTab> register(String id, Function<CreativeModeTab.Builder, CreativeModeTab.Builder> builderConsumer) {
        return REGISTER.register(id, () -> builderConsumer.apply(CreativeModeTab.builder().title(Component.translatable(MarvelSuperheroes.id(id).toLanguageKey("itemGroup")))).build());
    }

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }
}
