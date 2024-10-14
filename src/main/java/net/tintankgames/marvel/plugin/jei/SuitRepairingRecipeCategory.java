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
import net.tintankgames.marvel.world.item.crafting.SuitRepairingRecipe;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;

public class SuitRepairingRecipeCategory implements IRecipeCategory<RecipeHolder<SuitRepairingRecipe>> {
    public static final int width = 145;
    public static final int height = 54;

    private final IDrawable background;
    private final IDrawable icon;
    private final Component localizedName;

    public SuitRepairingRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(MarvelSuperheroes.id("textures/jei/gui/gui_marvel.png"), 0, 88, width, height);
        icon = guiHelper.createDrawableItemStack(new ItemStack(MarvelBlocks.SUIT_TABLE));
        localizedName = Component.translatable("emi.category.marvel.suit_repairing");
    }

    @Override
    public RecipeType<RecipeHolder<SuitRepairingRecipe>> getRecipeType() {
        return MarvelJeiRecipeTypes.SUIT_REPAIRING;
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
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<SuitRepairingRecipe> recipeHolder, IFocusGroup focuses) {
        SuitRepairingRecipe recipe = recipeHolder.value();
        builder.setShapeless();
        builder.addSlot(RecipeIngredientRole.OUTPUT, 124, 19).addItemStack(MarvelSuperheroesJeiPlugin.getResultItem(recipe));
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 19).addIngredients(recipe.getSuit());
        builder.addSlot(RecipeIngredientRole.INPUT, 49, 10).addIngredients(getIngredient(recipe, 1));
        builder.addSlot(RecipeIngredientRole.INPUT, 67, 10).addIngredients(getIngredient(recipe, 2));
        builder.addSlot(RecipeIngredientRole.INPUT, 49, 28).addIngredients(getIngredient(recipe, 3));
        builder.addSlot(RecipeIngredientRole.INPUT, 67, 28).addIngredients(getIngredient(recipe, 4));
    }

    private Ingredient getIngredient(SuitRepairingRecipe recipe, int slot) {
        return recipe.getIngredients().size() > slot ? recipe.getIngredients().get(slot) : Ingredient.EMPTY;
    }

    @Override
    public boolean isHandled(RecipeHolder<SuitRepairingRecipe> recipeHolder) {
        return !recipeHolder.value().isSpecial();
    }
}
