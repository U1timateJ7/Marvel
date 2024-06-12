package net.tintankgames.marvel.world.item;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.core.components.MarvelDataComponents;

import java.util.List;
import java.util.function.Supplier;

public class MarvelItems {
    private static final DeferredRegister.Items REGISTER = DeferredRegister.createItems(MarvelSuperheroes.MOD_ID);

    public static final DeferredItem<Item> VIBRANIUM = register("vibranium", () -> new Item(new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> VIBRANIUM_INGOT = register("vibranium_ingot", () -> new Item(new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> VIBRANIUM_NUGGET = register("vibranium_nugget", () -> new Item(new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> VIBRANIUM_SWORD = register("vibranium_sword", () -> new SwordItem(MarvelTiers.VIBRANIUM, new Item.Properties().fireResistant().attributes(SwordItem.createAttributes(MarvelTiers.VIBRANIUM, 3, -2.4F))));
    public static final DeferredItem<Item> VIBRANIUM_SHOVEL = register("vibranium_shovel", () -> new ShovelItem(MarvelTiers.VIBRANIUM, new Item.Properties().fireResistant().attributes(ShovelItem.createAttributes(MarvelTiers.VIBRANIUM, 1.5F, -3.0F))));
    public static final DeferredItem<Item> VIBRANIUM_PICKAXE = register("vibranium_pickaxe", () -> new PickaxeItem(MarvelTiers.VIBRANIUM, new Item.Properties().fireResistant().attributes(PickaxeItem.createAttributes(MarvelTiers.VIBRANIUM, 1.0F, -2.8F))));
    public static final DeferredItem<Item> VIBRANIUM_AXE = register("vibranium_axe", () -> new AxeItem(MarvelTiers.VIBRANIUM, new Item.Properties().fireResistant().attributes(AxeItem.createAttributes(MarvelTiers.VIBRANIUM, 5.0F, -3.0F))));
    public static final DeferredItem<Item> VIBRANIUM_HOE = register("vibranium_hoe", () -> new HoeItem(MarvelTiers.VIBRANIUM, new Item.Properties().fireResistant().attributes(HoeItem.createAttributes(MarvelTiers.VIBRANIUM, -4.0F, 0.0F))));
    public static final DeferredItem<Item> VIBRANIUM_HELMET = register("vibranium_helmet", () -> new ArmorItem(MarvelArmorMaterials.VIBRANIUM, ArmorItem.Type.HELMET, new Item.Properties().fireResistant().durability(ArmorItem.Type.HELMET.getDurability(45))));
    public static final DeferredItem<Item> VIBRANIUM_CHESTPLATE = register("vibranium_chestplate", () -> new ArmorItem(MarvelArmorMaterials.VIBRANIUM, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant().durability(ArmorItem.Type.CHESTPLATE.getDurability(45))));
    public static final DeferredItem<Item> VIBRANIUM_LEGGINGS = register("vibranium_leggings", () -> new ArmorItem(MarvelArmorMaterials.VIBRANIUM, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant().durability(ArmorItem.Type.LEGGINGS.getDurability(45))));
    public static final DeferredItem<Item> VIBRANIUM_BOOTS = register("vibranium_boots", () -> new ArmorItem(MarvelArmorMaterials.VIBRANIUM, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant().durability(ArmorItem.Type.BOOTS.getDurability(45))));
    public static final DeferredItem<Item> RAW_TITANIUM = register("raw_titanium", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TITANIUM_INGOT = register("titanium_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TITANIUM_NUGGET = register("titanium_nugget", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_PALLADIUM = register("raw_palladium", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PALLADIUM_INGOT = register("palladium_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PALLADIUM_NUGGET = register("palladium_nugget", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GOLD_TITANIUM_INGOT = register("gold_titanium_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GOLD_TITANIUM_NUGGET = register("gold_titanium_nugget", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ADAMANTIUM_INGOT = register("adamantium_ingot", () -> new Item(new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> ADAMANTIUM_NUGGET = register("adamantium_nugget", () -> new Item(new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> PROTO_ADAMANTIUM_INGOT = register("proto_adamantium_ingot", () -> new Item(new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> REINFORCED_LEATHER = register("reinforced_leather", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> VIBRANIUM_WEAVE = register("vibranium_weave", () -> new Item(new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> VIBRANIUM_NANITES = register("vibranium_nanites", () -> new Item(new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> CAPTAIN_AMERICA_HELMET = register("captain_america_helmet", () -> new CaptainAmericaSuitItem(ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(33))));
    public static final DeferredItem<Item> CAPTAIN_AMERICA_CHESTPLATE = register("captain_america_chestplate", () -> new CaptainAmericaSuitItem(ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(33))));
    public static final DeferredItem<Item> CAPTAIN_AMERICA_LEGGINGS = register("captain_america_leggings", () -> new CaptainAmericaSuitItem(ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(33))));
    public static final DeferredItem<Item> CAPTAIN_AMERICA_BOOTS = register("captain_america_boots", () -> new CaptainAmericaSuitItem(ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(33))));
    public static final DeferredItem<Item> CAPTAIN_AMERICA_STEALTH_HELMET = register("captain_america_stealth_helmet", () -> new CaptainAmericaSuitItem(ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(33))));
    public static final DeferredItem<Item> CAPTAIN_AMERICA_STEALTH_CHESTPLATE = register("captain_america_stealth_chestplate", () -> new CaptainAmericaSuitItem(ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(33))));
    public static final DeferredItem<Item> CAPTAIN_AMERICA_STEALTH_LEGGINGS = register("captain_america_stealth_leggings", () -> new CaptainAmericaSuitItem(ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(33))));
    public static final DeferredItem<Item> CAPTAIN_AMERICA_STEALTH_BOOTS = register("captain_america_stealth_boots", () -> new CaptainAmericaSuitItem(ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(33))));
    public static final DeferredItem<Item> CAPTAIN_CARTER_CHESTPLATE = register("captain_carter_chestplate", () -> new CaptainAmericaSuitItem(ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(33))));
    public static final DeferredItem<Item> CAPTAIN_CARTER_LEGGINGS = register("captain_carter_leggings", () -> new CaptainAmericaSuitItem(ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(33))));
    public static final DeferredItem<Item> CAPTAIN_CARTER_BOOTS = register("captain_carter_boots", () -> new CaptainAmericaSuitItem(ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(33))));
    public static final DeferredItem<Item> RED_GUARDIAN_HELMET = register("red_guardian_helmet", () -> new CaptainAmericaSuitItem(ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(33))));
    public static final DeferredItem<Item> RED_GUARDIAN_CHESTPLATE = register("red_guardian_chestplate", () -> new CaptainAmericaSuitItem(ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(33))));
    public static final DeferredItem<Item> RED_GUARDIAN_LEGGINGS = register("red_guardian_leggings", () -> new CaptainAmericaSuitItem(ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(33))));
    public static final DeferredItem<Item> RED_GUARDIAN_BOOTS = register("red_guardian_boots", () -> new CaptainAmericaSuitItem(ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(33))));
    public static final DeferredItem<Item> VIBRANIUM_SHIELD = register("vibranium_shield", () -> new VibraniumShieldItem(VIBRANIUM_INGOT, new Item.Properties().durability(4063).attributes(VibraniumShieldItem.vibraniumAttributes()).fireResistant()));
    public static final DeferredItem<Item> PROTO_ADAMANTIUM_SHIELD = register("proto_adamantium_shield", () -> new VibraniumShieldItem(VIBRANIUM_INGOT, new Item.Properties().stacksTo(1).attributes(VibraniumShieldItem.protoAdamantiumAttributes()).fireResistant()));
    public static final DeferredItem<Item> BLACK_PANTHER_HELMET = register("black_panther_helmet", () -> new BlackPantherSuitItem(ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(45))));
    public static final DeferredItem<Item> BLACK_PANTHER_CHESTPLATE = register("black_panther_chestplate", () -> new BlackPantherSuitItem(ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(45))));
    public static final DeferredItem<Item> BLACK_PANTHER_LEGGINGS = register("black_panther_leggings", () -> new BlackPantherSuitItem(ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(45))));
    public static final DeferredItem<Item> BLACK_PANTHER_BOOTS = register("black_panther_boots", () -> new BlackPantherSuitItem(ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(45))));
    public static final DeferredItem<Item> KINETIC_BLACK_PANTHER_HELMET = register("kinetic_black_panther_helmet", () -> new KineticBlackPantherSuitItem(0x8A5DEB, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(45)).component(MarvelDataComponents.HELMET_OPEN, false)));
    public static final DeferredItem<Item> KINETIC_BLACK_PANTHER_CHESTPLATE = register("kinetic_black_panther_chestplate", () -> new KineticBlackPantherSuitItem(0x8A5DEB, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(45))));
    public static final DeferredItem<Item> KINETIC_BLACK_PANTHER_LEGGINGS = register("kinetic_black_panther_leggings", () -> new KineticBlackPantherSuitItem(0x8A5DEB, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(45))));
    public static final DeferredItem<Item> KINETIC_BLACK_PANTHER_BOOTS = register("kinetic_black_panther_boots", () -> new KineticBlackPantherSuitItem(0x8A5DEB, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(45))));
    public static final DeferredItem<Item> KINETIC_BLACK_PANTHER_NECKLACE = register("kinetic_black_panther_necklace", () -> new KineticBlackPantherNecklaceItem(0x8A5DEB, List.of(KINETIC_BLACK_PANTHER_BOOTS, KINETIC_BLACK_PANTHER_LEGGINGS, KINETIC_BLACK_PANTHER_CHESTPLATE, KINETIC_BLACK_PANTHER_HELMET), new Item.Properties()));
    public static final DeferredItem<Item> KILLMONGER_HELMET = register("killmonger_helmet", () -> new KineticBlackPantherSuitItem(0xFFC34C, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(45)).component(MarvelDataComponents.HELMET_OPEN, false)));
    public static final DeferredItem<Item> KILLMONGER_CHESTPLATE = register("killmonger_chestplate", () -> new KineticBlackPantherSuitItem(0xFFC34C, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(45))));
    public static final DeferredItem<Item> KILLMONGER_LEGGINGS = register("killmonger_leggings", () -> new KineticBlackPantherSuitItem(0xFFC34C, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(45))));
    public static final DeferredItem<Item> KILLMONGER_BOOTS = register("killmonger_boots", () -> new KineticBlackPantherSuitItem(0xFFC34C, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(45))));
    public static final DeferredItem<Item> KILLMONGER_NECKLACE = register("killmonger_necklace", () -> new KineticBlackPantherNecklaceItem(0xFFC34C, List.of(KILLMONGER_BOOTS, KILLMONGER_LEGGINGS, KILLMONGER_CHESTPLATE, KILLMONGER_HELMET), new Item.Properties()));
    public static final DeferredItem<Item> ADAMANTIUM_CLAWS = register("adamantium_claws", () -> new AdamantiumClawsItem(new Item.Properties()));
    public static final DeferredItem<Item> WOLVERINE_HELMET = register("wolverine_helmet", () -> new WolverineSuitItem(ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(33))));
    public static final DeferredItem<Item> WOLVERINE_CHESTPLATE = register("wolverine_chestplate", () -> new WolverineSuitItem(ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(33))));
    public static final DeferredItem<Item> WOLVERINE_SLEEVELESS_CHESTPLATE = register("wolverine_sleeveless_chestplate", () -> new WolverineSuitItem(ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(33))));
    public static final DeferredItem<Item> WOLVERINE_LEGGINGS = register("wolverine_leggings", () -> new WolverineSuitItem(ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(33))));
    public static final DeferredItem<Item> WOLVERINE_BOOTS = register("wolverine_boots", () -> new WolverineSuitItem(ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(33))));
    public static final DeferredItem<Item> WOLVERINE_BROWN_HELMET = register("wolverine_brown_helmet", () -> new WolverineSuitItem(ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(33))));
    public static final DeferredItem<Item> WOLVERINE_BROWN_CHESTPLATE = register("wolverine_brown_chestplate", () -> new WolverineSuitItem(ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(33))));
    public static final DeferredItem<Item> WOLVERINE_BROWN_SLEEVELESS_CHESTPLATE = register("wolverine_brown_sleeveless_chestplate", () -> new WolverineSuitItem(ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(33))));
    public static final DeferredItem<Item> WOLVERINE_BROWN_LEGGINGS = register("wolverine_brown_leggings", () -> new WolverineSuitItem(ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(33))));
    public static final DeferredItem<Item> WOLVERINE_BROWN_BOOTS = register("wolverine_brown_boots", () -> new WolverineSuitItem(ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(33))));
    public static final DeferredItem<Item> OPTIC_BLAST = register("optic_blast", () -> new OpticBlastItem(new Item.Properties()));
    public static final DeferredItem<Item> CYCLOPS_HELMET = register("cyclops_helmet", () -> new CyclopsSuitItem(ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(33))));
    public static final DeferredItem<Item> CYCLOPS_CHESTPLATE = register("cyclops_chestplate", () -> new CyclopsSuitItem(ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(33))));
    public static final DeferredItem<Item> CYCLOPS_LEGGINGS = register("cyclops_leggings", () -> new CyclopsSuitItem(ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(33))));
    public static final DeferredItem<Item> CYCLOPS_BOOTS = register("cyclops_boots", () -> new CyclopsSuitItem(ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(33))));
    public static final DeferredItem<Item> CYCLOPS_DARK_HELMET = register("cyclops_dark_helmet", () -> new CyclopsSuitItem(ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(33))));
    public static final DeferredItem<Item> CYCLOPS_DARK_CHESTPLATE = register("cyclops_dark_chestplate", () -> new CyclopsSuitItem(ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(33))));
    public static final DeferredItem<Item> CYCLOPS_DARK_LEGGINGS = register("cyclops_dark_leggings", () -> new CyclopsSuitItem(ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(33))));
    public static final DeferredItem<Item> CYCLOPS_DARK_BOOTS = register("cyclops_dark_boots", () -> new CyclopsSuitItem(ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(33))));
    public static final DeferredItem<Item> WEB_SHOOTER = register("web_shooter", () -> new WebShooterItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> WEB_FLUID = register("web_fluid", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SPIDER_MAN_HELMET = register("spider_man_helmet", () -> new SpiderManSuitItem(ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(33))));
    public static final DeferredItem<Item> SPIDER_MAN_CHESTPLATE = register("spider_man_chestplate", () -> new SpiderManSuitItem(ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(33))));
    public static final DeferredItem<Item> SPIDER_MAN_LEGGINGS = register("spider_man_leggings", () -> new SpiderManSuitItem(ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(33))));
    public static final DeferredItem<Item> SPIDER_MAN_BOOTS = register("spider_man_boots", () -> new SpiderManSuitItem(ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(33))));

    private static <T extends Item> DeferredItem<T> register(String id, Supplier<T> supplier) {
        return REGISTER.register(id, supplier);
    }

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }

    public static class Tags {
        public static final TagKey<Item> VIBRANIUM_ORES = create("vibranium_ores");
        public static final TagKey<Item> TITANIUM_ORES = create("titanium_ores");
        public static final TagKey<Item> PALLADIUM_ORES = create("palladium_ores");
        public static final TagKey<Item> HIDES_OUTER_LAYER = create("hides_outer_layer");
        public static final TagKey<Item> SOUND_DAMPENING_BOOTS = create("sound_dampening_boots");
        public static final TagKey<Item> RENDER_HAND = create("render_hand");
        public static final TagKey<Item> CAPTAIN_AMERICA_ARMOR = create("captain_america_armor");
        public static final TagKey<Item> CAPTAIN_AMERICA_HELMET = create("captain_america_helmet");
        public static final TagKey<Item> CAPTAIN_AMERICA_CHESTPLATE = create("captain_america_chestplate");
        public static final TagKey<Item> CAPTAIN_AMERICA_LEGGINGS = create("captain_america_leggings");
        public static final TagKey<Item> CAPTAIN_AMERICA_BOOTS = create("captain_america_boots");
        public static final TagKey<Item> BLACK_PANTHER_ARMOR = create("black_panther_armor");
        public static final TagKey<Item> BLACK_PANTHER_HELMET = create("black_panther_helmet");
        public static final TagKey<Item> BLACK_PANTHER_CHESTPLATE = create("black_panther_chestplate");
        public static final TagKey<Item> BLACK_PANTHER_LEGGINGS = create("black_panther_leggings");
        public static final TagKey<Item> BLACK_PANTHER_BOOTS = create("black_panther_boots");
        public static final TagKey<Item> KINETIC_BLACK_PANTHER_ARMOR = create("kinetic_black_panther_armor");
        public static final TagKey<Item> KINETIC_BLACK_PANTHER_HELMET = create("kinetic_black_panther_helmet");
        public static final TagKey<Item> KINETIC_BLACK_PANTHER_CHESTPLATE = create("kinetic_black_panther_chestplate");
        public static final TagKey<Item> KINETIC_BLACK_PANTHER_LEGGINGS = create("kinetic_black_panther_leggings");
        public static final TagKey<Item> KINETIC_BLACK_PANTHER_BOOTS = create("kinetic_black_panther_boots");
        public static final TagKey<Item> WOLVERINE_ARMOR = create("wolverine_armor");
        public static final TagKey<Item> WOLVERINE_HELMET = create("wolverine_helmet");
        public static final TagKey<Item> WOLVERINE_CHESTPLATE = create("wolverine_chestplate");
        public static final TagKey<Item> WOLVERINE_LEGGINGS = create("wolverine_leggings");
        public static final TagKey<Item> WOLVERINE_BOOTS = create("wolverine_boots");
        public static final TagKey<Item> CYCLOPS_ARMOR = create("cyclops_armor");
        public static final TagKey<Item> CYCLOPS_HELMET = create("cyclops_helmet");
        public static final TagKey<Item> CYCLOPS_CHESTPLATE = create("cyclops_chestplate");
        public static final TagKey<Item> CYCLOPS_LEGGINGS = create("cyclops_leggings");
        public static final TagKey<Item> CYCLOPS_BOOTS = create("cyclops_boots");
        public static final TagKey<Item> SPIDER_MAN_ARMOR = create("spider_man_armor");
        public static final TagKey<Item> SPIDER_MAN_HELMET = create("spider_man_helmet");
        public static final TagKey<Item> SPIDER_MAN_CHESTPLATE = create("spider_man_chestplate");
        public static final TagKey<Item> SPIDER_MAN_LEGGINGS = create("spider_man_leggings");
        public static final TagKey<Item> SPIDER_MAN_BOOTS = create("spider_man_boots");

        private static TagKey<Item> create(String id) {
            return REGISTER.createTagKey(id);
        }
    }
}
