package net.tintankgames.marvel.client.gui.screens.recipebook;

import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.item.crafting.SuitUpgradingRecipe;

import java.util.List;

public class SuitUpgradingRecipeBookComponent extends RecipeBookComponent {
    private static final Component FILTER_NAME = Component.translatable("gui.recipebook.toggleRecipes.upgradable");
    private static final WidgetSprites FILTER_SPRITES = new WidgetSprites(
            MarvelSuperheroes.id("recipe_book/suit_table_filter_enabled"),
            MarvelSuperheroes.id("recipe_book/suit_table_filter_disabled"),
            MarvelSuperheroes.id("recipe_book/suit_table_filter_enabled_highlighted"),
            MarvelSuperheroes.id("recipe_book/suit_table_filter_disabled_highlighted")
    );

    @Override
    protected void initFilterButtonTextures() {
        this.filterButton.initTextureValues(FILTER_SPRITES);
    }

    @Override
    protected Component getRecipeFilterName() {
        return FILTER_NAME;
    }

    public void setupGhostRecipe(RecipeHolder<?> p_301197_, List<Slot> p_100317_) {
        ItemStack itemstack = p_301197_.value().getResultItem(this.minecraft.level.registryAccess());
        this.ghostRecipe.setRecipe(p_301197_);
        this.ghostRecipe.addIngredient(Ingredient.of(itemstack), p_100317_.get(0).x, p_100317_.get(0).y);
        if (p_301197_.value() instanceof SuitUpgradingRecipe suitUpgradingRecipe) this.ghostRecipe.addIngredient(suitUpgradingRecipe.getSuit(), p_100317_.get(1).x, p_100317_.get(1).y);
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int i = 2 + x + (3 * y);
                if (i - 1 < p_301197_.value().getIngredients().size()) this.ghostRecipe.addIngredient(p_301197_.value().getIngredients().get(i - 1), p_100317_.get(i).x, p_100317_.get(i).y);
            }
        }
    }
}
