package net.tintankgames.marvel.plugin.emi;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.tintankgames.marvel.world.item.crafting.SuitVariantRecipe;

import java.util.List;

public class EmiSuitVariantRecipe implements EmiRecipe {
    private final ResourceLocation id;
    private final EmiIngredient input;
    private final EmiStack output;

    public EmiSuitVariantRecipe(RecipeHolder<SuitVariantRecipe> recipe) {
        id = recipe.id();
        input = EmiIngredient.of(recipe.value().getSuit());
        output = EmiStack.of(recipe.value().getResultItem(Minecraft.getInstance().level.registryAccess()));
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return MarvelSuperheroesEmiPlugin.SUIT_VARIANT;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return List.of(input);
    }

    @Override
    public List<EmiStack> getOutputs() {
        return List.of(output);
    }

    @Override
    public int getDisplayWidth() {
        return 76;
    }

    @Override
    public int getDisplayHeight() {
        return 18;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(EmiTexture.EMPTY_ARROW, 26, 1);
        widgets.addSlot(input, 0, 0);
        widgets.addSlot(output, 58, 0).recipeContext(this);
    }
}
