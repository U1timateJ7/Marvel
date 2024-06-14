package net.tintankgames.marvel.world.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tintankgames.marvel.MarvelSuperheroes;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class MarvelArmorMaterials {
    private static final DeferredRegister<ArmorMaterial> REGISTER = DeferredRegister.create(Registries.ARMOR_MATERIAL, MarvelSuperheroes.MOD_ID);

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> VIBRANIUM = register("vibranium", () -> new ArmorMaterial(createArmorMap(3, 6, 8, 3, 15), 7, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Ingredient.of(MarvelItems.VIBRANIUM_INGOT), List.of(new ArmorMaterial.Layer(MarvelSuperheroes.id("vibranium"))), 3.0F, 0.25F));
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> REINFORCED_LEATHER = register("reinforced_leather", () -> new ArmorMaterial(createArmorMap(3, 5, 6, 3, 11), 0, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(MarvelItems.REINFORCED_LEATHER), List.of(new ArmorMaterial.Layer(MarvelSuperheroes.id("reinforced_leather"))), 1.0F, 0.0F));
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> UPGRADED_REINFORCED_LEATHER = register("upgraded_reinforced_leather", () -> new ArmorMaterial(createArmorMap(3, 6, 8, 3, 11), 0, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(MarvelItems.REINFORCED_LEATHER), List.of(new ArmorMaterial.Layer(MarvelSuperheroes.id("upgraded_reinforced_leather"))), 2.0F, 0.0F));
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> VIBRANIUM_WEAVE = register("vibranium_weave", () -> new ArmorMaterial(createArmorMap(3, 6, 8, 3, 11), 0, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(MarvelItems.VIBRANIUM_WEAVE), List.of(new ArmorMaterial.Layer(MarvelSuperheroes.id("vibranium_weave"))), 2.0F, 0.1F));
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> VIBRANIUM_NANITE = register("vibranium_nanite", () -> new ArmorMaterial(createArmorMap(3, 6, 8, 3, 15), 0, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(MarvelItems.VIBRANIUM_WEAVE), List.of(new ArmorMaterial.Layer(MarvelSuperheroes.id("nanotech_vibranium_weave"))), 3.0F, 0.25F));

    private static Map<ArmorItem.Type, Integer> createArmorMap(int boots, int leggings, int chestplate, int helmet, int body) {
        return Map.of(ArmorItem.Type.BOOTS, boots, ArmorItem.Type.LEGGINGS, leggings, ArmorItem.Type.CHESTPLATE, chestplate, ArmorItem.Type.HELMET, helmet, ArmorItem.Type.BODY, body);
    }

    private static DeferredHolder<ArmorMaterial, ArmorMaterial> register(String id, Supplier<ArmorMaterial> supplier) {
        return REGISTER.register(id, supplier);
    }

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }
}
