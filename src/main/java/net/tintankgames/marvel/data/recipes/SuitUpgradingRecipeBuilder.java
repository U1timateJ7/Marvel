package net.tintankgames.marvel.data.recipes;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.tintankgames.marvel.world.item.crafting.SuitUpgradingRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

public class SuitUpgradingRecipeBuilder implements RecipeBuilder {
    private final boolean consumesSuit;
    private final Ingredient suit;
    private final ItemStack result;
    private final NonNullList<Ingredient> ingredients = NonNullList.create();
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    public SuitUpgradingRecipeBuilder(Ingredient suit, ItemStack result) {
        this(suit, result, false);
    }

    public SuitUpgradingRecipeBuilder(Ingredient suit, ItemStack result, boolean consumesSuit) {
        this.consumesSuit = consumesSuit;
        this.suit = suit;
        this.result = result;
    }

    public static SuitUpgradingRecipeBuilder upgrade(Ingredient suit, ItemLike result) {
        return new SuitUpgradingRecipeBuilder(suit, new ItemStack(result), false);
    }

    public static SuitUpgradingRecipeBuilder upgrade(Ingredient suit, ItemLike result, boolean consumesSuit) {
        return new SuitUpgradingRecipeBuilder(suit, new ItemStack(result), consumesSuit);
    }

    public static SuitUpgradingRecipeBuilder upgrade(Ingredient suit, ItemStack result) {
        return new SuitUpgradingRecipeBuilder(suit, result);
    }

    public SuitUpgradingRecipeBuilder requires(TagKey<Item> tagKey) {
        return this.requires(Ingredient.of(tagKey));
    }

    public SuitUpgradingRecipeBuilder requires(TagKey<Item> tagKey, int count) {
        return this.requires(Ingredient.of(tagKey), count);
    }

    public SuitUpgradingRecipeBuilder requires(ItemLike item) {
        return this.requires(item, 1);
    }

    public SuitUpgradingRecipeBuilder requires(ItemLike item, int count) {
        for (int i = 0; i < count; i++) {
            this.requires(Ingredient.of(item));
        }

        return this;
    }

    public SuitUpgradingRecipeBuilder requires(Ingredient ingredient) {
        return this.requires(ingredient, 1);
    }

    public SuitUpgradingRecipeBuilder requires(Ingredient ingredient, int count) {
        for (int i = 0; i < count; i++) {
            this.ingredients.add(ingredient);
        }

        return this;
    }

    public SuitUpgradingRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public SuitUpgradingRecipeBuilder group(@Nullable String group) {
        return this;
    }

    @Override
    public Item getResult() {
        return this.result.getItem();
    }

    @Override
    public void save(RecipeOutput recipeOutput, ResourceLocation id) {
        this.ensureValid(id);
        Advancement.Builder advancement$builder = recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement$builder::addCriterion);
        SuitUpgradingRecipe suitUpgradingRecipe = new SuitUpgradingRecipe(this.suit, this.result, this.ingredients, this.consumesSuit);
        recipeOutput.accept(id, suitUpgradingRecipe, advancement$builder.build(id.withPrefix("recipes/suit_upgrading/")));
    }

    private void ensureValid(ResourceLocation id) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
    }
}
