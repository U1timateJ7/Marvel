package net.tintankgames.marvel.attachment;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.core.registries.MarvelRegistries;
import net.tintankgames.marvel.world.item.MarvelItems;

import java.util.function.Supplier;

public class VeronicaSuitPresets {
    private static final DeferredRegister<VeronicaSuitPreset> REGISTER = DeferredRegister.create(MarvelRegistries.VERONICA_SUIT_PRESET, MarvelSuperheroes.MOD_ID);

    public static final DeferredHolder<VeronicaSuitPreset, VeronicaSuitPreset> IRON_MAN_MARK_11 = register("iron_man_mark_11", () -> new VeronicaSuitPreset(MarvelItems.IRON_MAN_MARK_11_HELMET.toStack(), MarvelItems.IRON_MAN_MARK_11_CHESTPLATE.toStack(), MarvelItems.IRON_MAN_MARK_11_LEGGINGS.toStack(), MarvelItems.IRON_MAN_MARK_11_BOOTS.toStack()));
    public static final DeferredHolder<VeronicaSuitPreset, VeronicaSuitPreset> IRON_MAN_MARK_15 = register("iron_man_mark_15", () -> new VeronicaSuitPreset(MarvelItems.IRON_MAN_MARK_15_HELMET.toStack(), MarvelItems.IRON_MAN_MARK_15_CHESTPLATE.toStack(), MarvelItems.IRON_MAN_MARK_15_LEGGINGS.toStack(), MarvelItems.IRON_MAN_MARK_15_BOOTS.toStack()));
    public static final DeferredHolder<VeronicaSuitPreset, VeronicaSuitPreset> IRON_MAN_MARK_17 = register("iron_man_mark_17", () -> new VeronicaSuitPreset(MarvelItems.IRON_MAN_MARK_17_HELMET.toStack(), MarvelItems.IRON_MAN_MARK_17_CHESTPLATE.toStack(), MarvelItems.IRON_MAN_MARK_17_LEGGINGS.toStack(), MarvelItems.IRON_MAN_MARK_17_BOOTS.toStack()));
    public static final DeferredHolder<VeronicaSuitPreset, VeronicaSuitPreset> IRON_MAN_MARK_19 = register("iron_man_mark_19", () -> new VeronicaSuitPreset(MarvelItems.IRON_MAN_MARK_19_HELMET.toStack(), MarvelItems.IRON_MAN_MARK_19_CHESTPLATE.toStack(), MarvelItems.IRON_MAN_MARK_19_LEGGINGS.toStack(), MarvelItems.IRON_MAN_MARK_19_BOOTS.toStack()));
    public static final DeferredHolder<VeronicaSuitPreset, VeronicaSuitPreset> IRON_MAN_MARK_20 = register("iron_man_mark_20", () -> new VeronicaSuitPreset(MarvelItems.IRON_MAN_MARK_20_HELMET.toStack(), MarvelItems.IRON_MAN_MARK_20_CHESTPLATE.toStack(), MarvelItems.IRON_MAN_MARK_20_LEGGINGS.toStack(), MarvelItems.IRON_MAN_MARK_20_BOOTS.toStack()));
    public static final DeferredHolder<VeronicaSuitPreset, VeronicaSuitPreset> IRON_MAN_MARK_21 = register("iron_man_mark_21", () -> new VeronicaSuitPreset(MarvelItems.IRON_MAN_MARK_21_HELMET.toStack(), MarvelItems.IRON_MAN_MARK_21_CHESTPLATE.toStack(), MarvelItems.IRON_MAN_MARK_21_LEGGINGS.toStack(), MarvelItems.IRON_MAN_MARK_21_BOOTS.toStack()));
    public static final DeferredHolder<VeronicaSuitPreset, VeronicaSuitPreset> IRON_MAN_MARK_22 = register("iron_man_mark_22", () -> new VeronicaSuitPreset(MarvelItems.IRON_MAN_MARK_22_HELMET.toStack(), MarvelItems.IRON_MAN_MARK_22_CHESTPLATE.toStack(), MarvelItems.IRON_MAN_MARK_22_LEGGINGS.toStack(), MarvelItems.IRON_MAN_MARK_22_BOOTS.toStack()));
    public static final DeferredHolder<VeronicaSuitPreset, VeronicaSuitPreset> IRON_MAN_MARK_23 = register("iron_man_mark_23", () -> new VeronicaSuitPreset(MarvelItems.IRON_MAN_MARK_23_HELMET.toStack(), MarvelItems.IRON_MAN_MARK_23_CHESTPLATE.toStack(), MarvelItems.IRON_MAN_MARK_23_LEGGINGS.toStack(), MarvelItems.IRON_MAN_MARK_23_BOOTS.toStack()));
    public static final DeferredHolder<VeronicaSuitPreset, VeronicaSuitPreset> IRON_MAN_MARK_24 = register("iron_man_mark_24", () -> new VeronicaSuitPreset(MarvelItems.IRON_MAN_MARK_24_HELMET.toStack(), MarvelItems.IRON_MAN_MARK_24_CHESTPLATE.toStack(), MarvelItems.IRON_MAN_MARK_24_LEGGINGS.toStack(), MarvelItems.IRON_MAN_MARK_24_BOOTS.toStack()));
    public static final DeferredHolder<VeronicaSuitPreset, VeronicaSuitPreset> IRON_MAN_MARK_25 = register("iron_man_mark_25", () -> new VeronicaSuitPreset(MarvelItems.IRON_MAN_MARK_25_HELMET.toStack(), MarvelItems.IRON_MAN_MARK_25_CHESTPLATE.toStack(), MarvelItems.IRON_MAN_MARK_25_LEGGINGS.toStack(), MarvelItems.IRON_MAN_MARK_25_BOOTS.toStack()));
    public static final DeferredHolder<VeronicaSuitPreset, VeronicaSuitPreset> IRON_MAN_MARK_28 = register("iron_man_mark_28", () -> new VeronicaSuitPreset(MarvelItems.IRON_MAN_MARK_28_HELMET.toStack(), MarvelItems.IRON_MAN_MARK_28_CHESTPLATE.toStack(), MarvelItems.IRON_MAN_MARK_28_LEGGINGS.toStack(), MarvelItems.IRON_MAN_MARK_28_BOOTS.toStack()));
    public static final DeferredHolder<VeronicaSuitPreset, VeronicaSuitPreset> IRON_MAN_MARK_30 = register("iron_man_mark_30", () -> new VeronicaSuitPreset(MarvelItems.IRON_MAN_MARK_30_HELMET.toStack(), MarvelItems.IRON_MAN_MARK_30_CHESTPLATE.toStack(), MarvelItems.IRON_MAN_MARK_30_LEGGINGS.toStack(), MarvelItems.IRON_MAN_MARK_30_BOOTS.toStack()));
    public static final DeferredHolder<VeronicaSuitPreset, VeronicaSuitPreset> IRON_MAN_MARK_33 = register("iron_man_mark_33", () -> new VeronicaSuitPreset(MarvelItems.IRON_MAN_MARK_33_HELMET.toStack(), MarvelItems.IRON_MAN_MARK_33_CHESTPLATE.toStack(), MarvelItems.IRON_MAN_MARK_33_LEGGINGS.toStack(), MarvelItems.IRON_MAN_MARK_33_BOOTS.toStack()));
    public static final DeferredHolder<VeronicaSuitPreset, VeronicaSuitPreset> IRON_MAN_MARK_35 = register("iron_man_mark_35", () -> new VeronicaSuitPreset(MarvelItems.IRON_MAN_MARK_35_HELMET.toStack(), MarvelItems.IRON_MAN_MARK_35_CHESTPLATE.toStack(), MarvelItems.IRON_MAN_MARK_35_LEGGINGS.toStack(), MarvelItems.IRON_MAN_MARK_35_BOOTS.toStack()));
    public static final DeferredHolder<VeronicaSuitPreset, VeronicaSuitPreset> IRON_MAN_MARK_37 = register("iron_man_mark_37", () -> new VeronicaSuitPreset(MarvelItems.IRON_MAN_MARK_37_HELMET.toStack(), MarvelItems.IRON_MAN_MARK_37_CHESTPLATE.toStack(), MarvelItems.IRON_MAN_MARK_37_LEGGINGS.toStack(), MarvelItems.IRON_MAN_MARK_37_BOOTS.toStack()));
    public static final DeferredHolder<VeronicaSuitPreset, VeronicaSuitPreset> IRON_MAN_MARK_38 = register("iron_man_mark_38", () -> new VeronicaSuitPreset(MarvelItems.IRON_MAN_MARK_38_HELMET.toStack(), MarvelItems.IRON_MAN_MARK_38_CHESTPLATE.toStack(), MarvelItems.IRON_MAN_MARK_38_LEGGINGS.toStack(), MarvelItems.IRON_MAN_MARK_38_BOOTS.toStack()));
    public static final DeferredHolder<VeronicaSuitPreset, VeronicaSuitPreset> IRON_MAN_MARK_39 = register("iron_man_mark_39", () -> new VeronicaSuitPreset(MarvelItems.IRON_MAN_MARK_39_HELMET.toStack(), MarvelItems.IRON_MAN_MARK_39_CHESTPLATE.toStack(), MarvelItems.IRON_MAN_MARK_39_LEGGINGS.toStack(), MarvelItems.IRON_MAN_MARK_39_BOOTS.toStack()));
    public static final DeferredHolder<VeronicaSuitPreset, VeronicaSuitPreset> IRON_MAN_MARK_42 = register("iron_man_mark_42", () -> new VeronicaSuitPreset(MarvelItems.IRON_MAN_MARK_42_HELMET.toStack(), MarvelItems.IRON_MAN_MARK_42_CHESTPLATE.toStack(), MarvelItems.IRON_MAN_MARK_42_LEGGINGS.toStack(), MarvelItems.IRON_MAN_MARK_42_BOOTS.toStack()));
    public static final DeferredHolder<VeronicaSuitPreset, VeronicaSuitPreset> IRON_MAN_MARK_43 = register("iron_man_mark_43", () -> new VeronicaSuitPreset(MarvelItems.IRON_MAN_MARK_43_HELMET.toStack(), MarvelItems.IRON_MAN_MARK_43_CHESTPLATE.toStack(), MarvelItems.IRON_MAN_MARK_43_LEGGINGS.toStack(), MarvelItems.IRON_MAN_MARK_43_BOOTS.toStack()));
    public static final DeferredHolder<VeronicaSuitPreset, VeronicaSuitPreset> WAR_MACHINE_MARK_2 = register("war_machine_mark_2", () -> new VeronicaSuitPreset(MarvelItems.WAR_MACHINE_MARK_2_HELMET.toStack(), MarvelItems.WAR_MACHINE_MARK_2_CHESTPLATE.toStack(), MarvelItems.WAR_MACHINE_MARK_2_LEGGINGS.toStack(), MarvelItems.WAR_MACHINE_MARK_2_BOOTS.toStack()));
    public static final DeferredHolder<VeronicaSuitPreset, VeronicaSuitPreset> IRON_PATRIOT = register("iron_patriot", () -> new VeronicaSuitPreset(MarvelItems.IRON_PATRIOT_HELMET.toStack(), MarvelItems.IRON_PATRIOT_CHESTPLATE.toStack(), MarvelItems.IRON_PATRIOT_LEGGINGS.toStack(), MarvelItems.IRON_PATRIOT_BOOTS.toStack()));

    private static DeferredHolder<VeronicaSuitPreset, VeronicaSuitPreset> register(String id, Supplier<VeronicaSuitPreset> supplier) {
        return REGISTER.register(id, supplier);
    }

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }
}
