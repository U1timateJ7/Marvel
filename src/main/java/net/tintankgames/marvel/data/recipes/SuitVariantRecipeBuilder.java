package net.tintankgames.marvel.data.recipes;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.tintankgames.marvel.world.item.crafting.SuitVariantRecipe;

import org.jetbrains.annotations.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class SuitVariantRecipeBuilder implements RecipeBuilder {
    private final ItemStack result;
    private final Ingredient ingredient;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    public SuitVariantRecipeBuilder(Ingredient p_251221_, ItemStack p_251302_) {
        this.result = p_251302_;
        this.ingredient = p_251221_;
    }

    public static SuitVariantRecipeBuilder variant(Ingredient suit, ItemLike variant) {
        return new SuitVariantRecipeBuilder(suit, new ItemStack(variant));
    }

    public static SuitVariantRecipeBuilder variant(Ingredient suit, ItemStack variant) {
        return new SuitVariantRecipeBuilder(suit, variant);
    }

    public SuitVariantRecipeBuilder unlockedBy(String p_176810_, Criterion<?> p_301267_) {
        this.criteria.put(p_176810_, p_301267_);
        return this;
    }

    public SuitVariantRecipeBuilder group(@Nullable String p_176808_) {
        return this;
    }

    @Override
    public Item getResult() {
        return this.result.getItem();
    }

    @Override
    public void save(RecipeOutput p_301137_, ResourceLocation p_126328_) {
        this.ensureValid(p_126328_);
        Advancement.Builder advancement$builder = p_301137_.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(p_126328_))
                .rewards(AdvancementRewards.Builder.recipe(p_126328_))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement$builder::addCriterion);
        SuitVariantRecipe singleitemrecipe = new SuitVariantRecipe(this.ingredient, this.result);
        p_301137_.accept(p_126328_, singleitemrecipe, advancement$builder.build(p_126328_.withPrefix("recipes/suit_variant/")));
    }

    @Override
    public void save(RecipeOutput p_301244_) {
        this.save(p_301244_, RecipeBuilder.getDefaultRecipeId(getResult()).withPath(id -> id + "_from_variants"));
    }

    private void ensureValid(ResourceLocation p_126330_) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + p_126330_);
        }
    }
}
