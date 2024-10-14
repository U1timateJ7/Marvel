package net.tintankgames.marvel.world.item.crafting;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tintankgames.marvel.MarvelSuperheroes;

public class MarvelRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> REGISTER = DeferredRegister.create(Registries.RECIPE_TYPE, MarvelSuperheroes.MOD_ID);

    public static final DeferredHolder<RecipeType<?>, RecipeType<SuitUpgradingRecipe>> SUIT_UPGRADING = register("suit_upgrading");
    public static final DeferredHolder<RecipeType<?>, RecipeType<SuitVariantRecipe>> SUIT_VARIANT = register("suit_variant");
    public static final DeferredHolder<RecipeType<?>, RecipeType<SuitRepairingRecipe>> SUIT_REPAIRING = register("suit_repairing");

    private static <T extends Recipe<?>> DeferredHolder<RecipeType<?>, RecipeType<T>> register(String id) {
        return REGISTER.register(id, () -> new RecipeType<>() {
            @Override
            public String toString() {
                return MarvelSuperheroes.id(id).toString();
            }
        });
    }

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }
}
