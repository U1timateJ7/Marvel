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

    public SuitUpgradingRecipeBuilder requires(TagKey<Item> p_206420_) {
        return this.requires(Ingredient.of(p_206420_));
    }

    public SuitUpgradingRecipeBuilder requires(TagKey<Item> p_206420_, int count) {
        return this.requires(Ingredient.of(p_206420_), count);
    }

    public SuitUpgradingRecipeBuilder requires(ItemLike p_126210_) {
        return this.requires(p_126210_, 1);
    }

    public SuitUpgradingRecipeBuilder requires(ItemLike p_126212_, int p_126213_) {
        for (int i = 0; i < p_126213_; i++) {
            this.requires(Ingredient.of(p_126212_));
        }

        return this;
    }

    public SuitUpgradingRecipeBuilder requires(Ingredient p_126185_) {
        return this.requires(p_126185_, 1);
    }

    public SuitUpgradingRecipeBuilder requires(Ingredient p_126187_, int p_126188_) {
        for (int i = 0; i < p_126188_; i++) {
            this.ingredients.add(p_126187_);
        }

        return this;
    }

    public SuitUpgradingRecipeBuilder unlockedBy(String p_176781_, Criterion<?> p_300897_) {
        this.criteria.put(p_176781_, p_300897_);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String p_176495_) {
        return this;
    }

    @Override
    public Item getResult() {
        return this.result.getItem();
    }

    @Override
    public void save(RecipeOutput p_301215_, ResourceLocation p_126206_) {
        this.ensureValid(p_126206_);
        Advancement.Builder advancement$builder = p_301215_.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(p_126206_))
                .rewards(AdvancementRewards.Builder.recipe(p_126206_))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement$builder::addCriterion);
        SuitUpgradingRecipe suitUpgradingRecipe = new SuitUpgradingRecipe(
                this.suit,
                this.result,
                this.ingredients,
                this.consumesSuit
        );
        p_301215_.accept(p_126206_, suitUpgradingRecipe, advancement$builder.build(p_126206_.withPrefix("recipes/suit_upgrading/")));
    }

    private void ensureValid(ResourceLocation p_126208_) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + p_126208_);
        }
    }
}
