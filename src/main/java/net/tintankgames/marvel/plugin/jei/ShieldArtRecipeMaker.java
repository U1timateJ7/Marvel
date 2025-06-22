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
import java.util.Arrays;
import java.util.List;

public class ShieldArtRecipeMaker {
    public static List<RecipeHolder<CraftingRecipe>> createRecipes() {
        List<Item> shields = List.of(MarvelItems.VIBRANIUM_SHIELD.get(), MarvelItems.PROTO_ADAMANTIUM_SHIELD.get());

        List<RecipeHolder<CraftingRecipe>> list = new ArrayList<>();
        for (Item shield : shields) {
            for (ShieldArt art : Arrays.stream(ShieldArt.values()).filter(shieldArt -> shieldArt != ShieldArt.BLANK).toList()) {
                list.add(createRecipe(shield, art));
            }
        }
        return list;
    }

    private static RecipeHolder<CraftingRecipe> createRecipe(Item shield, ShieldArt art) {
        NonNullList<Ingredient> inputs = NonNullList.create();
        inputs.add(Ingredient.of(shield));
        inputs.add(Ingredient.of(Items.PAPER));
        inputs.addAll(art.dyes().stream().map(Ingredient::of).toList());

        ItemStack output = createOutput(shield, art);

        ResourceLocation id = new ResourceLocation(output.getItemHolder().getRegisteredName()).withSuffix("/" + art.getName());
        CraftingRecipe recipe = new ShapelessRecipe("marvel.vibranium_shield.art", CraftingBookCategory.EQUIPMENT, output, inputs);
        return new RecipeHolder<>(id, recipe);
    }

    private static ItemStack createOutput(Item shield, ShieldArt art) {
        ItemStack output = new ItemStack(shield);
        output.set(MarvelDataComponents.SHIELD_ART, art);
        return output;
    }

    private ShieldArtRecipeMaker() {
    }
}
