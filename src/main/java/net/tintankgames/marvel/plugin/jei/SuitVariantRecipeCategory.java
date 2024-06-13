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
import net.minecraft.world.item.crafting.RecipeHolder;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.item.crafting.SuitVariantRecipe;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;

public class SuitVariantRecipeCategory implements IRecipeCategory<RecipeHolder<SuitVariantRecipe>> {
    public static final int width = 82;
    public static final int height = 34;

    private final IDrawable background;
    private final IDrawable icon;
    private final Component localizedName;

    public SuitVariantRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(MarvelSuperheroes.id("textures/jei/gui/gui_marvel.png"), 0, 54, width, height);
        icon = guiHelper.createDrawableItemStack(new ItemStack(MarvelBlocks.SUIT_TABLE));
        localizedName = Component.translatable("emi.category.marvel.suit_variant");
    }

    @Override
    public RecipeType<RecipeHolder<SuitVariantRecipe>> getRecipeType() {
        return MarvelJeiRecipeTypes.SUIT_VARIANT;
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
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<SuitVariantRecipe> recipeHolder, IFocusGroup focuses) {
        SuitVariantRecipe recipe = recipeHolder.value();
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 9).addIngredients(recipe.getSuit());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 61, 9).addItemStack(MarvelSuperheroesJeiPlugin.getResultItem(recipe));
    }

    @Override
    public boolean isHandled(RecipeHolder<SuitVariantRecipe> recipeHolder) {
        SuitVariantRecipe recipe = recipeHolder.value();
        return !recipe.isSpecial();
    }
}
