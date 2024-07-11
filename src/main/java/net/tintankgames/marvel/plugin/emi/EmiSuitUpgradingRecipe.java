package net.tintankgames.marvel.plugin.emi;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.tintankgames.marvel.world.item.crafting.SuitUpgradingRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmiSuitUpgradingRecipe implements EmiRecipe {
    protected final ResourceLocation id;
    protected final EmiIngredient suit;
    protected final List<EmiIngredient> input;
    protected final EmiStack output;

    public EmiSuitUpgradingRecipe(RecipeHolder<SuitUpgradingRecipe> recipe) {
        this.suit = EmiIngredient.of(recipe.value().getSuit());
        this.input = recipe.value().getIngredients().stream().map(EmiIngredient::of).collect(Collectors.toList());
        this.input.removeFirst();
        this.output = EmiStack.of(recipe.value().getResultItem(Minecraft.getInstance().level.registryAccess()));
        this.id = recipe.id();
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return MarvelSuperheroesEmiPlugin.SUIT_UPGRADING;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        List<EmiIngredient> list = new ArrayList<>();
        list.add(suit);
        list.addAll(input);
        return list;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return List.of(output);
    }

    @Override
    public int getDisplayWidth() {
        return 134;
    }

    @Override
    public int getDisplayHeight() {
        return 54;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(EmiTexture.EMPTY_ARROW, 80, 18);
        widgets.addTexture(EmiTexture.SHAPELESS, 113, 0);
        widgets.addSlot(suit, 0, 18);
        int sOff = 0;
        for (int i = 0; i < 9; i++) {
            int s = i + sOff;
            if (s < input.size()) {
                widgets.addSlot(input.get(s), i % 3 * 18 + 22, i / 3 * 18);
            } else {
                widgets.addSlot(EmiStack.of(ItemStack.EMPTY), i % 3 * 18 + 22, i / 3 * 18);
            }
        }
        widgets.addSlot(output, 108, 14).large(true).recipeContext(this);
    }
}
