package net.tintankgames.marvel.plugin.jei;

import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.tintankgames.marvel.world.item.crafting.MarvelRecipeTypes;
import net.tintankgames.marvel.world.item.crafting.SuitUpgradingRecipe;
import net.tintankgames.marvel.world.item.crafting.SuitVariantRecipe;

public class MarvelJeiRecipeTypes {
    public static final RecipeType<RecipeHolder<SuitUpgradingRecipe>> SUIT_UPGRADING = RecipeType.createFromVanilla(MarvelRecipeTypes.SUIT_UPGRADING.get());
    public static final RecipeType<RecipeHolder<SuitVariantRecipe>> SUIT_VARIANT = RecipeType.createFromVanilla(MarvelRecipeTypes.SUIT_VARIANT.get());
}
