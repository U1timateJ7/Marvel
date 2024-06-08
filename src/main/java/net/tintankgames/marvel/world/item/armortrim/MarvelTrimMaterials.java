package net.tintankgames.marvel.world.item.armortrim;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.item.MarvelArmorMaterials;
import net.tintankgames.marvel.world.item.MarvelItems;

import java.util.Map;

public class MarvelTrimMaterials {
    public static final ResourceKey<TrimMaterial> VIBRANIUM = create("vibranium");
    public static final ResourceKey<TrimMaterial> TITANIUM = create("titanium");
    public static final ResourceKey<TrimMaterial> PALLADIUM = create("palladium");

    private static ResourceKey<TrimMaterial> create(String id) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, MarvelSuperheroes.id(id));
    }

    public static void bootstrap(BootstrapContext<TrimMaterial> bootstrapContext) {
        bootstrapContext.register(VIBRANIUM, TrimMaterial.create("vibranium", MarvelItems.VIBRANIUM_INGOT.get(), 1.0f, Component.translatable(VIBRANIUM.location().toLanguageKey("trim_material")).withColor(0x8E75F0), Map.of(MarvelArmorMaterials.VIBRANIUM, "vibranium_darker")));
        bootstrapContext.register(TITANIUM, TrimMaterial.create("titanium", MarvelItems.TITANIUM_INGOT.get(), 0.9f, Component.translatable(TITANIUM.location().toLanguageKey("trim_material")).withColor(0x4B4B65), Map.of()));
        bootstrapContext.register(PALLADIUM, TrimMaterial.create("palladium", MarvelItems.PALLADIUM_INGOT.get(), 0.3f, Component.translatable(PALLADIUM.location().toLanguageKey("trim_material")).withColor(0x4B4B4B), Map.of()));
    }
}
