package net.tintankgames.marvel.plugin.jei;

import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

import java.util.List;
import java.util.stream.Collectors;

public class NecklaceRecipeMaker {
    public static List<RecipeHolder<CraftingRecipe>> createRecipes(Item necklace) {
        List<ItemStack> pieces = new ItemStack(necklace).get(DataComponents.CONTAINER).stream().collect(Collectors.toList()).reversed();
        return List.of(createRecipe(necklace, pieces));
    }

    private static RecipeHolder<CraftingRecipe> createRecipe(Item necklace, List<ItemStack> pieces) {
        NonNullList<Ingredient> inputs = NonNullList.copyOf(pieces.stream().map(Ingredient::of).toList());

        ItemStack output = necklace.getDefaultInstance();

        ResourceLocation id = ResourceLocation.parse(output.getItemHolder().getRegisteredName());
        CraftingRecipe recipe = new ShapelessRecipe(id.getNamespace(), CraftingBookCategory.EQUIPMENT, output, inputs);
        return new RecipeHolder<>(id, recipe);
    }

    private NecklaceRecipeMaker() {
    }
}
