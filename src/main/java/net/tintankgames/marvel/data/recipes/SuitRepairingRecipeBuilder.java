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
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.tintankgames.marvel.world.item.crafting.SuitRepairingRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class SuitRepairingRecipeBuilder implements RecipeBuilder {
    private final Ingredient suit;
    private final NonNullList<Ingredient> ingredients = NonNullList.create();
    private final float repairPercent;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    public SuitRepairingRecipeBuilder(Ingredient suit, float repairPercent) {
        this.suit = suit;
        this.repairPercent = repairPercent;
    }

    public static SuitRepairingRecipeBuilder repair(Ingredient suit) {
        return new SuitRepairingRecipeBuilder(suit, 2.0F/3.0F);
    }

    public static SuitRepairingRecipeBuilder repair(Ingredient suit, float repairPercent) {
        return new SuitRepairingRecipeBuilder(suit, repairPercent);
    }

    public SuitRepairingRecipeBuilder requires(TagKey<Item> tagKey) {
        return this.requires(Ingredient.of(tagKey));
    }

    @SafeVarargs
    public final SuitRepairingRecipeBuilder requires(TagKey<Item>... tagKeys) {
        return this.requires(Ingredient.fromValues(Arrays.stream(tagKeys).map(Ingredient.TagValue::new)));
    }

    public SuitRepairingRecipeBuilder requires(TagKey<Item> tagKey, int count) {
        return this.requires(Ingredient.of(tagKey), count);
    }

    public SuitRepairingRecipeBuilder requires(ItemLike item) {
        return this.requires(item, 1);
    }

    public SuitRepairingRecipeBuilder requires(ItemLike item, int count) {
        for (int i = 0; i < count; i++) {
            this.requires(Ingredient.of(item));
        }

        return this;
    }

    public SuitRepairingRecipeBuilder requires(Ingredient ingredient) {
        return this.requires(ingredient, 1);
    }

    public SuitRepairingRecipeBuilder requires(Ingredient ingredient, int count) {
        for (int i = 0; i < count; i++) {
            this.ingredients.add(ingredient);
        }

        return this;
    }

    public SuitRepairingRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public SuitRepairingRecipeBuilder group(@Nullable String group) {
        return this;
    }

    @Override
    public Item getResult() {
        return suit.getItems()[0].getItem();
    }

    @Override
    public void save(RecipeOutput p_301244_) {
        this.save(p_301244_, suit.getValues()[0] instanceof Ingredient.TagValue tagValue ? tagValue.tag().location().withSuffix("_repairing") : RecipeBuilder.getDefaultRecipeId(this.getResult()).withSuffix("_repairing"));
    }

    @Override
    public void save(RecipeOutput p_301186_, String p_176502_) {
        this.save(p_301186_, suit.getValues()[0] instanceof Ingredient.TagValue tagValue ? tagValue.tag().location().withSuffix("_repairing") : RecipeBuilder.getDefaultRecipeId(this.getResult()).withSuffix("_repairing"));
    }

    @Override
    public void save(RecipeOutput recipeOutput, ResourceLocation id) {
        this.ensureValid(id);
        Advancement.Builder advancement$builder = recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement$builder::addCriterion);
        SuitRepairingRecipe suitRepairingRecipe = new SuitRepairingRecipe(this.suit, this.ingredients, this.repairPercent);
        recipeOutput.accept(id, suitRepairingRecipe, advancement$builder.build(id.withPrefix("recipes/suit_repairing/")));
    }

    private void ensureValid(ResourceLocation id) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
    }
}
