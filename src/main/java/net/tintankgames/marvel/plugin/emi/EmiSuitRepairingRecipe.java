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
import net.tintankgames.marvel.world.item.crafting.SuitRepairingRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmiSuitRepairingRecipe implements EmiRecipe {
    protected final ResourceLocation id;
    protected final EmiIngredient suit;
    protected final List<EmiIngredient> input;
    protected final EmiStack output;

    public EmiSuitRepairingRecipe(RecipeHolder<SuitRepairingRecipe> recipe) {
        this.suit = EmiIngredient.of(recipe.value().getSuit());
        this.input = recipe.value().getIngredients().stream().map(EmiIngredient::of).collect(Collectors.toList());
        this.input.removeFirst();
        this.output = EmiStack.of(recipe.value().getResultItem(Minecraft.getInstance().level.registryAccess()));
        this.id = recipe.id();
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return MarvelSuperheroesEmiPlugin.SUIT_REPAIRING;
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
    public boolean supportsRecipeTree() {
        return false;
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
        widgets.addTexture(EmiTexture.EMPTY_ARROW, 76, 18);
        widgets.addTexture(EmiTexture.SHAPELESS, 113, 0);
        widgets.addSlot(suit, 0, 18);
        int sOff = 0;
        for (int i = 0; i < 4; i++) {
            int s = i + sOff;
            if (s < input.size()) {
                widgets.addSlot(input.get(s), i % 2 * 18 + 31, i / 2 * 18 + 9);
            } else {
                widgets.addSlot(EmiStack.of(ItemStack.EMPTY), i % 2 * 18 + 31, i / 2 * 18 + 9);
            }
        }
        widgets.addSlot(output, 108, 14).large(true).recipeContext(this);
    }
}
