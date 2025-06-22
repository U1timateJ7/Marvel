package net.tintankgames.marvel.plugin.jei;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

import java.util.List;

public class IgorRecipeMaker {
    public static List<RecipeHolder<CraftingRecipe>> createRecipes(Item igor, Item helmet, Item chestplate, Item leggings, Item boots) {
        List<Item> pieces = List.of(helmet, chestplate, leggings, boots);
        return List.of(createRecipe(igor, pieces));
    }

    private static RecipeHolder<CraftingRecipe> createRecipe(Item igor, List<Item> pieces) {
        NonNullList<Ingredient> inputs = NonNullList.copyOf(pieces.stream().map(Ingredient::of).toList());

        ItemStack output = igor.getDefaultInstance();

        ResourceLocation id = ResourceLocation.parse(output.getItemHolder().getRegisteredName());
        CraftingRecipe recipe = new ShapelessRecipe(id.getNamespace(), CraftingBookCategory.EQUIPMENT, output, inputs);
        return new RecipeHolder<>(id, recipe);
    }

    private IgorRecipeMaker() {
    }
}
