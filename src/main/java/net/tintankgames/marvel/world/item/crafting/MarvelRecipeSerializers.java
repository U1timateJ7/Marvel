package net.tintankgames.marvel.world.item.crafting;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tintankgames.marvel.MarvelSuperheroes;

import java.util.function.Supplier;

public class MarvelRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> REGISTER = DeferredRegister.create(Registries.RECIPE_SERIALIZER, MarvelSuperheroes.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<SuitUpgradingRecipe>> SUIT_UPGRADING = register("suit_upgrading", SuitUpgradingRecipe.Serializer::new);
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<SuitVariantRecipe>> SUIT_VARIANT = register("suit_variant", SuitVariantRecipe.Serializer::new);
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ShieldArtRecipe>> SHIELD_ART = register("crafting_special_shieldart", () -> new SimpleCraftingRecipeSerializer<>(ShieldArtRecipe::new));
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ShieldCleanRecipe>> SHIELD_CLEAN = register("crafting_special_shieldclean", () -> new SimpleCraftingRecipeSerializer<>(ShieldCleanRecipe::new));
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<KineticBlackPantherNecklaceRecipe>> KINETIC_BLACK_PANTHER_NECKLACE = register("crafting_special_kineticblackpanthernecklace", () -> new SimpleCraftingRecipeSerializer<>(KineticBlackPantherNecklaceRecipe::new));
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<KillmongerNecklaceRecipe>> KILLMONGER_NECKLACE = register("crafting_special_killmongernecklace", () -> new SimpleCraftingRecipeSerializer<>(KillmongerNecklaceRecipe::new));

    private static <T extends Recipe<?>> DeferredHolder<RecipeSerializer<?>, RecipeSerializer<T>> register(String id, Supplier<RecipeSerializer<T>> supplier) {
        return REGISTER.register(id, supplier);
    }

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }
}
