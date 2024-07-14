package net.tintankgames.marvel.plugin.jei;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.item.crafting.SuitUpgradingRecipe;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;

public class SuitUpgradingRecipeCategory implements IRecipeCategory<RecipeHolder<SuitUpgradingRecipe>> {
    public static final int width = 145;
    public static final int height = 54;

    private final IDrawable background;
    private final IDrawable icon;
    private final Component localizedName;

    public SuitUpgradingRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(MarvelSuperheroes.id("textures/jei/gui/gui_marvel.png"), 0, 0, width, height);
        icon = guiHelper.createDrawableItemStack(new ItemStack(MarvelBlocks.SUIT_TABLE));
        localizedName = Component.translatable("emi.category.marvel.suit_upgrading");
    }

    @Override
    public RecipeType<RecipeHolder<SuitUpgradingRecipe>> getRecipeType() {
        return MarvelJeiRecipeTypes.SUIT_UPGRADING;
    }

    @Override
    public Component getTitle() {
        return localizedName;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<SuitUpgradingRecipe> recipeHolder, IFocusGroup focuses) {
        SuitUpgradingRecipe recipe = recipeHolder.value();
        builder.setShapeless();
        builder.addSlot(RecipeIngredientRole.OUTPUT, 124, 19).addItemStack(MarvelSuperheroesJeiPlugin.getResultItem(recipe));
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 19).addIngredients(recipe.getSuit());
        builder.addSlot(RecipeIngredientRole.INPUT, 40, 1).addIngredients(getIngredient(recipe, 1));
        builder.addSlot(RecipeIngredientRole.INPUT, 58, 1).addIngredients(getIngredient(recipe, 2));
        builder.addSlot(RecipeIngredientRole.INPUT, 76, 1).addIngredients(getIngredient(recipe, 3));
        builder.addSlot(RecipeIngredientRole.INPUT, 40, 19).addIngredients(getIngredient(recipe, 4));
        builder.addSlot(RecipeIngredientRole.INPUT, 58, 19).addIngredients(getIngredient(recipe, 5));
        builder.addSlot(RecipeIngredientRole.INPUT, 76, 19).addIngredients(getIngredient(recipe, 6));
        builder.addSlot(RecipeIngredientRole.INPUT, 40, 37).addIngredients(getIngredient(recipe, 7));
        builder.addSlot(RecipeIngredientRole.INPUT, 58, 37).addIngredients(getIngredient(recipe, 8));
        builder.addSlot(RecipeIngredientRole.INPUT, 76, 37).addIngredients(getIngredient(recipe, 9));
    }

    private Ingredient getIngredient(SuitUpgradingRecipe recipe, int slot) {
        return recipe.getIngredients().size() > slot ? recipe.getIngredients().get(slot) : Ingredient.EMPTY;
    }

    @Override
    public boolean isHandled(RecipeHolder<SuitUpgradingRecipe> recipeHolder) {
        return !recipeHolder.value().isSpecial();
    }
}
