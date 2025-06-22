package net.tintankgames.marvel.plugin.jei;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.component.ShieldArt;

import java.util.ArrayList;
import java.util.List;

public class ShieldCleanRecipeMaker {
    public static List<RecipeHolder<CraftingRecipe>> createRecipes() {
        List<Item> shields = List.of(MarvelItems.VIBRANIUM_SHIELD.get(), MarvelItems.PROTO_ADAMANTIUM_SHIELD.get());

        List<RecipeHolder<CraftingRecipe>> list = new ArrayList<>();
        for (Item shield : shields) {
            list.add(createRecipe(shield));
        }
        return list;
    }

    private static RecipeHolder<CraftingRecipe> createRecipe(Item shield) {
        NonNullList<Ingredient> inputs = NonNullList.create();
        inputs.add(Ingredient.of(createCapShield(shield)));
        inputs.add(Ingredient.of(Items.WATER_BUCKET));

        ItemStack output = shield.getDefaultInstance();

        ResourceLocation id = new ResourceLocation(output.getItemHolder().getRegisteredName()).withSuffix("/blank");
        CraftingRecipe recipe = new ShapelessRecipe("marvel.vibranium_shield.clean", CraftingBookCategory.EQUIPMENT, output, inputs);
        return new RecipeHolder<>(id, recipe);
    }

    private static ItemStack createCapShield(Item shield) {
        ItemStack stack = new ItemStack(shield);
        stack.set(MarvelDataComponents.SHIELD_ART, ShieldArt.CAPTAIN_AMERICA);
        return stack;
    }

    private ShieldCleanRecipeMaker() {
    }
}
