package net.tintankgames.marvel.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.data.recipes.SuitUpgradingRecipeBuilder;
import net.tintankgames.marvel.data.recipes.SuitVariantRecipeBuilder;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.crafting.KillmongerNecklaceRecipe;
import net.tintankgames.marvel.world.item.crafting.KineticBlackPantherNecklaceRecipe;
import net.tintankgames.marvel.world.item.crafting.ShieldArtRecipe;
import net.tintankgames.marvel.world.item.crafting.ShieldCleanRecipe;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MarvelRecipeProvider extends RecipeProvider {
    public MarvelRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        mvoreSmelting(output, List.of(MarvelBlocks.TITANIUM_ORE, MarvelBlocks.DEEPSLATE_TITANIUM_ORE, MarvelItems.RAW_TITANIUM), RecipeCategory.MISC, MarvelItems.TITANIUM_INGOT, 1.0f, 200, "titanium_ingot");
        mvoreSmelting(output, List.of(MarvelBlocks.PALLADIUM_ORE, MarvelBlocks.DEEPSLATE_PALLADIUM_ORE, MarvelItems.RAW_PALLADIUM), RecipeCategory.MISC, MarvelItems.PALLADIUM_INGOT, 1.0f, 200, "palladium_ingot");
        mvoreBlasting(output, List.of(MarvelBlocks.VIBRANIUM_ORE, MarvelBlocks.DEEPSLATE_VIBRANIUM_ORE), RecipeCategory.MISC, MarvelItems.VIBRANIUM, 1.0f, 200, "vibranium");
        mvoreBlasting(output, List.of(MarvelItems.VIBRANIUM), RecipeCategory.MISC, MarvelItems.VIBRANIUM_INGOT, 1.0f, 200, "vibranium_ingot");
        mvoreBlasting(output, List.of(MarvelBlocks.TITANIUM_ORE, MarvelBlocks.DEEPSLATE_TITANIUM_ORE, MarvelItems.RAW_TITANIUM), RecipeCategory.MISC, MarvelItems.TITANIUM_INGOT, 1.0f, 100, "titanium_ingot");
        mvoreBlasting(output, List.of(MarvelBlocks.PALLADIUM_ORE, MarvelBlocks.DEEPSLATE_PALLADIUM_ORE, MarvelItems.RAW_PALLADIUM), RecipeCategory.MISC, MarvelItems.PALLADIUM_INGOT, 1.0f, 100, "palladium_ingot");
        mvnineBlockStorageRecipesRecipesWithCustomUnpacking(output, RecipeCategory.MISC, MarvelItems.VIBRANIUM_INGOT, RecipeCategory.BUILDING_BLOCKS, MarvelBlocks.VIBRANIUM_BLOCK, "vibranium_ingot_from_vibranium_block", "vibranium_ingot");
        mvnineBlockStorageRecipesWithCustomPacking(output, RecipeCategory.MISC, MarvelItems.VIBRANIUM_NUGGET, RecipeCategory.MISC, MarvelItems.VIBRANIUM_INGOT, "vibranium_ingot_from_nuggets", "vibranium_ingot");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(MarvelItems.VIBRANIUM_PICKAXE, MarvelItems.VIBRANIUM_SHOVEL, MarvelItems.VIBRANIUM_AXE, MarvelItems.VIBRANIUM_HOE, MarvelItems.VIBRANIUM_SWORD, MarvelItems.VIBRANIUM_HELMET, MarvelItems.VIBRANIUM_CHESTPLATE, MarvelItems.VIBRANIUM_LEGGINGS, MarvelItems.VIBRANIUM_BOOTS), RecipeCategory.MISC, MarvelItems.VIBRANIUM_NUGGET, 0.1F, 200).unlockedBy("has_vibranium_pickaxe", has(MarvelItems.VIBRANIUM_PICKAXE)).unlockedBy("has_vibranium_shovel", has(MarvelItems.VIBRANIUM_SHOVEL)).unlockedBy("has_vibranium_axe", has(MarvelItems.VIBRANIUM_AXE)).unlockedBy("has_vibranium_hoe", has(MarvelItems.VIBRANIUM_HOE)).unlockedBy("has_vibranium_sword", has(MarvelItems.VIBRANIUM_SWORD)).unlockedBy("has_vibranium_helmet", has(MarvelItems.VIBRANIUM_HELMET)).unlockedBy("has_vibranium_chestplate", has(MarvelItems.VIBRANIUM_CHESTPLATE)).unlockedBy("has_vibranium_leggings", has(MarvelItems.VIBRANIUM_LEGGINGS)).unlockedBy("has_vibranium_boots", has(MarvelItems.VIBRANIUM_BOOTS)).save(output, MarvelSuperheroes.id(getSmeltingRecipeName(MarvelItems.VIBRANIUM_NUGGET)));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.VIBRANIUM_SWORD).define('#', Tags.Items.RODS_WOODEN).define('X', MarvelItems.VIBRANIUM_INGOT).pattern("X").pattern("X").pattern("#").unlockedBy("has_vibranium", has(MarvelItems.VIBRANIUM)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, MarvelItems.VIBRANIUM_PICKAXE).define('#', Tags.Items.RODS_WOODEN).define('X', MarvelItems.VIBRANIUM_INGOT).pattern("XXX").pattern(" # ").pattern(" # ").unlockedBy("has_vibranium", has(MarvelItems.VIBRANIUM)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, MarvelItems.VIBRANIUM_SHOVEL).define('#', Tags.Items.RODS_WOODEN).define('X', MarvelItems.VIBRANIUM_INGOT).pattern("X").pattern("#").pattern("#").unlockedBy("has_vibranium", has(MarvelItems.VIBRANIUM)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, MarvelItems.VIBRANIUM_AXE).define('#', Tags.Items.RODS_WOODEN).define('X', MarvelItems.VIBRANIUM_INGOT).pattern("XX").pattern("X#").pattern(" #").unlockedBy("has_vibranium", has(MarvelItems.VIBRANIUM)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, MarvelItems.VIBRANIUM_HOE).define('#', Tags.Items.RODS_WOODEN).define('X', MarvelItems.VIBRANIUM_INGOT).pattern("XX").pattern(" #").pattern(" #").unlockedBy("has_vibranium", has(MarvelItems.VIBRANIUM)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.VIBRANIUM_HELMET).define('X', MarvelItems.VIBRANIUM_INGOT).pattern("XXX").pattern("X X").unlockedBy("has_vibranium", has(MarvelItems.VIBRANIUM)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.VIBRANIUM_CHESTPLATE).define('X', MarvelItems.VIBRANIUM_INGOT).pattern("X X").pattern("XXX").pattern("XXX").unlockedBy("has_vibranium", has(MarvelItems.VIBRANIUM)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.VIBRANIUM_LEGGINGS).define('X', MarvelItems.VIBRANIUM_INGOT).pattern("XXX").pattern("X X").pattern("X X").unlockedBy("has_vibranium", has(MarvelItems.VIBRANIUM)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.VIBRANIUM_BOOTS).define('X', MarvelItems.VIBRANIUM_INGOT).pattern("X X").pattern("X X").unlockedBy("has_vibranium", has(MarvelItems.VIBRANIUM)).save(output);
        mvnineBlockStorageRecipes(output, RecipeCategory.MISC, MarvelItems.RAW_TITANIUM, RecipeCategory.BUILDING_BLOCKS, MarvelBlocks.RAW_TITANIUM_BLOCK);
        mvnineBlockStorageRecipesRecipesWithCustomUnpacking(output, RecipeCategory.MISC, MarvelItems.TITANIUM_INGOT, RecipeCategory.BUILDING_BLOCKS, MarvelBlocks.TITANIUM_BLOCK, "titanium_ingot_from_titanium_block", "titanium_ingot");
        mvnineBlockStorageRecipesWithCustomPacking(output, RecipeCategory.MISC, MarvelItems.TITANIUM_NUGGET, RecipeCategory.MISC, MarvelItems.TITANIUM_INGOT, "titanium_ingot_from_nuggets", "titanium_ingot");
        mvnineBlockStorageRecipes(output, RecipeCategory.MISC, MarvelItems.RAW_PALLADIUM, RecipeCategory.BUILDING_BLOCKS, MarvelBlocks.RAW_PALLADIUM_BLOCK);
        mvnineBlockStorageRecipesRecipesWithCustomUnpacking(output, RecipeCategory.MISC, MarvelItems.PALLADIUM_INGOT, RecipeCategory.BUILDING_BLOCKS, MarvelBlocks.PALLADIUM_BLOCK, "palladium_ingot_from_palladium_block", "palladium_ingot");
        mvnineBlockStorageRecipesWithCustomPacking(output, RecipeCategory.MISC, MarvelItems.PALLADIUM_NUGGET, RecipeCategory.MISC, MarvelItems.PALLADIUM_INGOT, "palladium_ingot_from_nuggets", "palladium_ingot");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MarvelItems.GOLD_TITANIUM_INGOT).requires(MarvelItems.TITANIUM_INGOT).requires(Items.GOLD_INGOT).unlockedBy("has_titanium_ingot", has(MarvelItems.TITANIUM_INGOT)).save(output);
        mvnineBlockStorageRecipesRecipesWithCustomUnpacking(output, RecipeCategory.MISC, MarvelItems.GOLD_TITANIUM_INGOT, RecipeCategory.BUILDING_BLOCKS, MarvelBlocks.GOLD_TITANIUM_BLOCK, "gold_titanium_ingot_from_gold_titanium_block", "gold_titanium_ingot");
        mvnineBlockStorageRecipesWithCustomPacking(output, RecipeCategory.MISC, MarvelItems.GOLD_TITANIUM_NUGGET, RecipeCategory.MISC, MarvelItems.GOLD_TITANIUM_INGOT, "gold_titanium_ingot_from_nuggets", "gold_titanium_ingot");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MarvelItems.ADAMANTIUM_INGOT).requires(Tags.Items.INGOTS_IRON).requires(Tags.Items.INGOTS_NETHERITE).requires(Items.MAGMA_CREAM).unlockedBy("has_netherite_ingot", has(Tags.Items.INGOTS_NETHERITE)).save(output);
        mvnineBlockStorageRecipesRecipesWithCustomUnpacking(output, RecipeCategory.MISC, MarvelItems.ADAMANTIUM_INGOT, RecipeCategory.BUILDING_BLOCKS, MarvelBlocks.ADAMANTIUM_BLOCK, "adamantium_ingot_from_adamantium_block", "adamantium_ingot");
        mvnineBlockStorageRecipesWithCustomPacking(output, RecipeCategory.MISC, MarvelItems.ADAMANTIUM_NUGGET, RecipeCategory.MISC, MarvelItems.ADAMANTIUM_INGOT, "adamantium_ingot_from_nuggets", "adamantium_ingot");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MarvelItems.PROTO_ADAMANTIUM_INGOT).requires(Tags.Items.INGOTS_IRON).requires(Tags.Items.INGOTS_NETHERITE).requires(MarvelItems.VIBRANIUM_INGOT).unlockedBy("has_vibranium", has(MarvelItems.VIBRANIUM)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MarvelItems.REINFORCED_LEATHER).define('#', Tags.Items.LEATHERS).define('X', Tags.Items.NUGGETS_IRON).define('I', Tags.Items.INGOTS_IRON).pattern("XIX").pattern("X#X").pattern("XIX").unlockedBy("has_leather", has(Tags.Items.LEATHERS)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MarvelItems.VIBRANIUM_WEAVE, 2).define('#', MarvelItems.VIBRANIUM).define('X', Tags.Items.STRINGS).pattern("X#").pattern("#X").unlockedBy("has_vibranium", has(MarvelItems.VIBRANIUM)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MarvelItems.VIBRANIUM_NANITES, 4).define('#', Tags.Items.DUSTS_REDSTONE).define('X', MarvelItems.VIBRANIUM_NUGGET).pattern("XXX").pattern("X#X").pattern("XXX").unlockedBy("has_vibranium", has(MarvelItems.VIBRANIUM)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, MarvelBlocks.SUIT_TABLE).define('#', Blocks.ANVIL).define('X', MarvelItems.TITANIUM_INGOT).pattern("XXX").pattern("X#X").pattern("XXX").unlockedBy("has_titanium", has(MarvelItems.TITANIUM_INGOT)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.VIBRANIUM_SHIELD).define('X', MarvelItems.VIBRANIUM_INGOT).define('#', Tags.Items.LEATHERS).pattern("XXX").pattern("X#X").unlockedBy("has_vibranium", has(MarvelItems.VIBRANIUM)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.PROTO_ADAMANTIUM_SHIELD).define('X', MarvelItems.PROTO_ADAMANTIUM_INGOT).define('#', Tags.Items.LEATHERS).pattern("XXX").pattern("X#X").unlockedBy("has_proto_adamantium_ingot", has(MarvelItems.PROTO_ADAMANTIUM_INGOT)).save(output);

        SpecialRecipeBuilder.special(ShieldArtRecipe::new).save(output, MarvelSuperheroes.id("shield_art"));
        SpecialRecipeBuilder.special(ShieldCleanRecipe::new).save(output, MarvelSuperheroes.id("shield_clean"));
        SpecialRecipeBuilder.special(KineticBlackPantherNecklaceRecipe::new).save(output, MarvelSuperheroes.id("kinetic_black_panther_necklace"));
        SpecialRecipeBuilder.special(KillmongerNecklaceRecipe::new).save(output, MarvelSuperheroes.id("killmonger_necklace"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.CAPTAIN_AMERICA_HELMET).define('#', Tags.Items.DYES_BLUE).define('X', MarvelItems.REINFORCED_LEATHER).define('L', Items.LEAD).pattern("#X#").pattern("XLX").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.CAPTAIN_AMERICA_CHESTPLATE).define('#', Tags.Items.DYES_BLUE).define('X', MarvelItems.REINFORCED_LEATHER).define('W', Tags.Items.DYES_WHITE).define('I', Tags.Items.INGOTS_IRON).pattern("X X").pattern("#I#").pattern("XWX").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.CAPTAIN_AMERICA_LEGGINGS).define('#', Tags.Items.DYES_BLUE).define('X', MarvelItems.REINFORCED_LEATHER).define('R', Tags.Items.DYES_RED).pattern("XRX").pattern("# #").pattern("X X").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.CAPTAIN_AMERICA_BOOTS).define('#', Tags.Items.DYES_BROWN).define('X', MarvelItems.REINFORCED_LEATHER).pattern("X X").pattern("# #").pattern("X X").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CAPTAIN_AMERICA_HELMET), MarvelItems.CAPTAIN_AMERICA_HELMET).unlockedBy("has_captain_america_helmet", has(MarvelItems.Tags.CAPTAIN_AMERICA_HELMET)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CAPTAIN_AMERICA_CHESTPLATE), MarvelItems.CAPTAIN_AMERICA_CHESTPLATE).unlockedBy("has_captain_america_chestplate", has(MarvelItems.Tags.CAPTAIN_AMERICA_CHESTPLATE)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CAPTAIN_AMERICA_LEGGINGS), MarvelItems.CAPTAIN_AMERICA_LEGGINGS).unlockedBy("has_captain_america_leggings", has(MarvelItems.Tags.CAPTAIN_AMERICA_LEGGINGS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CAPTAIN_AMERICA_BOOTS), MarvelItems.CAPTAIN_AMERICA_BOOTS).unlockedBy("has_captain_america_boots", has(MarvelItems.Tags.CAPTAIN_AMERICA_BOOTS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CAPTAIN_AMERICA_HELMET), MarvelItems.CAPTAIN_AMERICA_STEALTH_HELMET).unlockedBy("has_captain_america_helmet", has(MarvelItems.Tags.CAPTAIN_AMERICA_HELMET)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CAPTAIN_AMERICA_CHESTPLATE), MarvelItems.CAPTAIN_AMERICA_STEALTH_CHESTPLATE).unlockedBy("has_captain_america_chestplate", has(MarvelItems.Tags.CAPTAIN_AMERICA_CHESTPLATE)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CAPTAIN_AMERICA_LEGGINGS), MarvelItems.CAPTAIN_AMERICA_STEALTH_LEGGINGS).unlockedBy("has_captain_america_leggings", has(MarvelItems.Tags.CAPTAIN_AMERICA_LEGGINGS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CAPTAIN_AMERICA_BOOTS), MarvelItems.CAPTAIN_AMERICA_STEALTH_BOOTS).unlockedBy("has_captain_america_boots", has(MarvelItems.Tags.CAPTAIN_AMERICA_BOOTS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CAPTAIN_AMERICA_CHESTPLATE), MarvelItems.CAPTAIN_CARTER_CHESTPLATE).unlockedBy("has_captain_america_chestplate", has(MarvelItems.Tags.CAPTAIN_AMERICA_CHESTPLATE)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CAPTAIN_AMERICA_LEGGINGS), MarvelItems.CAPTAIN_CARTER_LEGGINGS).unlockedBy("has_captain_america_leggings", has(MarvelItems.Tags.CAPTAIN_AMERICA_LEGGINGS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CAPTAIN_AMERICA_BOOTS), MarvelItems.CAPTAIN_CARTER_BOOTS).unlockedBy("has_captain_america_boots", has(MarvelItems.Tags.CAPTAIN_AMERICA_BOOTS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CAPTAIN_AMERICA_HELMET), MarvelItems.RED_GUARDIAN_HELMET).unlockedBy("has_captain_america_helmet", has(MarvelItems.Tags.CAPTAIN_AMERICA_HELMET)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CAPTAIN_AMERICA_CHESTPLATE), MarvelItems.RED_GUARDIAN_CHESTPLATE).unlockedBy("has_captain_america_chestplate", has(MarvelItems.Tags.CAPTAIN_AMERICA_CHESTPLATE)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CAPTAIN_AMERICA_LEGGINGS), MarvelItems.RED_GUARDIAN_LEGGINGS).unlockedBy("has_captain_america_leggings", has(MarvelItems.Tags.CAPTAIN_AMERICA_LEGGINGS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CAPTAIN_AMERICA_BOOTS), MarvelItems.RED_GUARDIAN_BOOTS).unlockedBy("has_captain_america_boots", has(MarvelItems.Tags.CAPTAIN_AMERICA_BOOTS)).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.BLACK_PANTHER_HELMET).define('X', MarvelItems.VIBRANIUM_WEAVE).pattern("XXX").pattern("X X").unlockedBy("has_vibranium", has(MarvelItems.VIBRANIUM)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.BLACK_PANTHER_CHESTPLATE).define('X', MarvelItems.VIBRANIUM_WEAVE).pattern("X X").pattern("XXX").pattern("XXX").unlockedBy("has_vibranium", has(MarvelItems.VIBRANIUM)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.BLACK_PANTHER_LEGGINGS).define('X', MarvelItems.VIBRANIUM_WEAVE).pattern("XXX").pattern("X X").pattern("X X").unlockedBy("has_vibranium", has(MarvelItems.VIBRANIUM)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.BLACK_PANTHER_BOOTS).define('X', MarvelItems.VIBRANIUM_WEAVE).pattern("X X").pattern("X X").unlockedBy("has_vibranium", has(MarvelItems.VIBRANIUM)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.BLACK_PANTHER_HELMET), MarvelItems.KINETIC_BLACK_PANTHER_HELMET).requires(MarvelItems.VIBRANIUM_NANITES, 8).requires(Tags.Items.GEMS_AMETHYST).unlockedBy("has_black_panther_armor", has(MarvelItems.Tags.BLACK_PANTHER_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.BLACK_PANTHER_CHESTPLATE), MarvelItems.KINETIC_BLACK_PANTHER_CHESTPLATE).requires(MarvelItems.VIBRANIUM_NANITES, 8).requires(Tags.Items.GEMS_AMETHYST).unlockedBy("has_black_panther_armor", has(MarvelItems.Tags.BLACK_PANTHER_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.BLACK_PANTHER_LEGGINGS), MarvelItems.KINETIC_BLACK_PANTHER_LEGGINGS).requires(MarvelItems.VIBRANIUM_NANITES, 8).requires(Tags.Items.GEMS_AMETHYST).unlockedBy("has_black_panther_armor", has(MarvelItems.Tags.BLACK_PANTHER_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.BLACK_PANTHER_BOOTS), MarvelItems.KINETIC_BLACK_PANTHER_BOOTS).requires(MarvelItems.VIBRANIUM_NANITES, 8).requires(ItemTags.WOOL).unlockedBy("has_black_panther_armor", has(MarvelItems.Tags.BLACK_PANTHER_ARMOR)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.KINETIC_BLACK_PANTHER_HELMET), MarvelItems.KINETIC_BLACK_PANTHER_HELMET).unlockedBy("has_kinetic_black_panther_helmet", has(MarvelItems.Tags.KINETIC_BLACK_PANTHER_HELMET)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.KINETIC_BLACK_PANTHER_CHESTPLATE), MarvelItems.KINETIC_BLACK_PANTHER_CHESTPLATE).unlockedBy("has_kinetic_black_panther_chestplate", has(MarvelItems.Tags.KINETIC_BLACK_PANTHER_CHESTPLATE)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.KINETIC_BLACK_PANTHER_LEGGINGS), MarvelItems.KINETIC_BLACK_PANTHER_LEGGINGS).unlockedBy("has_kinetic_black_panther_leggings", has(MarvelItems.Tags.KINETIC_BLACK_PANTHER_LEGGINGS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.KINETIC_BLACK_PANTHER_BOOTS), MarvelItems.KINETIC_BLACK_PANTHER_BOOTS).unlockedBy("has_kinetic_black_panther_boots", has(MarvelItems.Tags.KINETIC_BLACK_PANTHER_BOOTS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.KINETIC_BLACK_PANTHER_HELMET), MarvelItems.KILLMONGER_HELMET).unlockedBy("has_kinetic_black_panther_helmet", has(MarvelItems.Tags.KINETIC_BLACK_PANTHER_HELMET)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.KINETIC_BLACK_PANTHER_CHESTPLATE), MarvelItems.KILLMONGER_CHESTPLATE).unlockedBy("has_kinetic_black_panther_chestplate", has(MarvelItems.Tags.KINETIC_BLACK_PANTHER_CHESTPLATE)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.KINETIC_BLACK_PANTHER_LEGGINGS), MarvelItems.KILLMONGER_LEGGINGS).unlockedBy("has_kinetic_black_panther_leggings", has(MarvelItems.Tags.KINETIC_BLACK_PANTHER_LEGGINGS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.KINETIC_BLACK_PANTHER_BOOTS), MarvelItems.KILLMONGER_BOOTS).unlockedBy("has_kinetic_black_panther_boots", has(MarvelItems.Tags.KINETIC_BLACK_PANTHER_BOOTS)).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.WOLVERINE_HELMET).define('#', Tags.Items.DYES_YELLOW).define('B', Tags.Items.DYES_BLACK).define('X', MarvelItems.REINFORCED_LEATHER).pattern("B B").pattern("#X#").pattern("X X").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.WOLVERINE_CHESTPLATE).define('#', Tags.Items.DYES_YELLOW).define('B', Tags.Items.DYES_BLUE).define('X', MarvelItems.REINFORCED_LEATHER).define('A', MarvelItems.ADAMANTIUM_INGOT).pattern("X X").pattern("A#A").pattern("XBX").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.WOLVERINE_LEGGINGS).define('#', Tags.Items.DYES_YELLOW).define('B', Tags.Items.DYES_BLUE).define('X', MarvelItems.REINFORCED_LEATHER).pattern("XBX").pattern("# #").pattern("X X").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.WOLVERINE_BOOTS).define('#', Tags.Items.DYES_BLUE).define('X', MarvelItems.REINFORCED_LEATHER).pattern("X X").pattern("# #").pattern("X X").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.WOLVERINE_HELMET), MarvelItems.WOLVERINE_HELMET).unlockedBy("has_wolverine_helmet", has(MarvelItems.Tags.WOLVERINE_HELMET)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.WOLVERINE_CHESTPLATE), MarvelItems.WOLVERINE_CHESTPLATE).unlockedBy("has_wolverine_chestplate", has(MarvelItems.Tags.WOLVERINE_CHESTPLATE)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.WOLVERINE_CHESTPLATE), MarvelItems.WOLVERINE_SLEEVELESS_CHESTPLATE).unlockedBy("has_wolverine_chestplate", has(MarvelItems.Tags.WOLVERINE_CHESTPLATE)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.WOLVERINE_LEGGINGS), MarvelItems.WOLVERINE_LEGGINGS).unlockedBy("has_wolverine_leggings", has(MarvelItems.Tags.WOLVERINE_LEGGINGS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.WOLVERINE_BOOTS), MarvelItems.WOLVERINE_BOOTS).unlockedBy("has_wolverine_boots", has(MarvelItems.Tags.WOLVERINE_BOOTS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.WOLVERINE_HELMET), MarvelItems.WOLVERINE_BROWN_HELMET).unlockedBy("has_wolverine_helmet", has(MarvelItems.Tags.WOLVERINE_HELMET)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.WOLVERINE_CHESTPLATE), MarvelItems.WOLVERINE_BROWN_CHESTPLATE).unlockedBy("has_wolverine_chestplate", has(MarvelItems.Tags.WOLVERINE_CHESTPLATE)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.WOLVERINE_CHESTPLATE), MarvelItems.WOLVERINE_BROWN_SLEEVELESS_CHESTPLATE).unlockedBy("has_wolverine_chestplate", has(MarvelItems.Tags.WOLVERINE_CHESTPLATE)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.WOLVERINE_LEGGINGS), MarvelItems.WOLVERINE_BROWN_LEGGINGS).unlockedBy("has_wolverine_leggings", has(MarvelItems.Tags.WOLVERINE_LEGGINGS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.WOLVERINE_BOOTS), MarvelItems.WOLVERINE_BROWN_BOOTS).unlockedBy("has_wolverine_boots", has(MarvelItems.Tags.WOLVERINE_BOOTS)).save(output);

        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CYCLOPS_HELMET), MarvelItems.CYCLOPS_HELMET).unlockedBy("has_cyclops_helmet", has(MarvelItems.Tags.CYCLOPS_HELMET)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CYCLOPS_CHESTPLATE), MarvelItems.CYCLOPS_CHESTPLATE).unlockedBy("has_cyclops_chestplate", has(MarvelItems.Tags.CYCLOPS_CHESTPLATE)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CYCLOPS_LEGGINGS), MarvelItems.CYCLOPS_LEGGINGS).unlockedBy("has_cyclops_leggings", has(MarvelItems.Tags.CYCLOPS_LEGGINGS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CYCLOPS_BOOTS), MarvelItems.CYCLOPS_BOOTS).unlockedBy("has_cyclops_boots", has(MarvelItems.Tags.CYCLOPS_BOOTS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CYCLOPS_HELMET), MarvelItems.CYCLOPS_DARK_HELMET).unlockedBy("has_cyclops_helmet", has(MarvelItems.Tags.CYCLOPS_HELMET)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CYCLOPS_CHESTPLATE), MarvelItems.CYCLOPS_DARK_CHESTPLATE).unlockedBy("has_cyclops_chestplate", has(MarvelItems.Tags.CYCLOPS_CHESTPLATE)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CYCLOPS_LEGGINGS), MarvelItems.CYCLOPS_DARK_LEGGINGS).unlockedBy("has_cyclops_leggings", has(MarvelItems.Tags.CYCLOPS_LEGGINGS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CYCLOPS_BOOTS), MarvelItems.CYCLOPS_DARK_BOOTS).unlockedBy("has_cyclops_boots", has(MarvelItems.Tags.CYCLOPS_BOOTS)).save(output);
    }

    public static void mvnineBlockStorageRecipesRecipesWithCustomUnpacking(RecipeOutput consumer, RecipeCategory recipeCategory, ItemLike item, RecipeCategory recipeCategory2, ItemLike item2, String name, String group) {
        mvnineBlockStorageRecipes(consumer, recipeCategory, item, recipeCategory2, item2, getSimpleRecipeName(item2), null, name, group);
    }

    public static void mvnineBlockStorageRecipesWithCustomPacking(RecipeOutput recipeOutput, RecipeCategory recipeCategory, ItemLike itemLike, RecipeCategory recipeCategory2, ItemLike itemLike2, String string, String string2) {
        mvnineBlockStorageRecipes(recipeOutput, recipeCategory, itemLike, recipeCategory2, itemLike2, string, string2, getSimpleRecipeName(itemLike), null);
    }

    public static void mvnineBlockStorageRecipes(RecipeOutput consumer, RecipeCategory recipeCategory, ItemLike item, RecipeCategory recipeCategory2, ItemLike item2) {
        mvnineBlockStorageRecipes(consumer, recipeCategory, item, recipeCategory2, item2, getSimpleRecipeName(item2), null, getSimpleRecipeName(item), null);
    }

    public static void mvnineBlockStorageRecipes(RecipeOutput consumer, RecipeCategory recipeCategory, ItemLike itemLike, RecipeCategory recipeCategory2, ItemLike itemLike2, String string, @Nullable String string2, String string3, @Nullable String string4) {
        ShapelessRecipeBuilder.shapeless(recipeCategory, itemLike, 9).requires(itemLike2).group(string4).unlockedBy(getHasName(itemLike2), has(itemLike2)).save(consumer, MarvelSuperheroes.id(string3));
        ShapedRecipeBuilder.shaped(recipeCategory2, itemLike2).define('#', itemLike).pattern("###").pattern("###").pattern("###").group(string2).unlockedBy(getHasName(itemLike), has(itemLike)).save(consumer, MarvelSuperheroes.id(string));
    }

    public static void mvoreBlasting(RecipeOutput consumer, List<ItemLike> smeltables, RecipeCategory recipeCategory, ItemLike result, float experience, int time, String groupName) {
        mvoreCooking(consumer, RecipeSerializer.BLASTING_RECIPE, smeltables, recipeCategory, result, experience, time, groupName, "_from_blasting", BlastingRecipe::new);
    }

    public static void mvoreSmelting(RecipeOutput consumer, List<ItemLike> smeltables, RecipeCategory recipeCategory, ItemLike result, float experience, int time, String groupName) {
        mvoreCooking(consumer, RecipeSerializer.SMELTING_RECIPE, smeltables, recipeCategory, result, experience, time, groupName, "_from_smelting", SmeltingRecipe::new);
    }

    public static <T extends AbstractCookingRecipe> void mvoreCooking(RecipeOutput consumer, RecipeSerializer<T> recipeSerializer, List<ItemLike> list, RecipeCategory recipeCategory, ItemLike itemLike, float f, int i, String string, String string2, AbstractCookingRecipe.Factory<T> factory) {
        for (ItemLike itemLike2 : list) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemLike2), recipeCategory, itemLike, f, i, recipeSerializer, factory).group(string).unlockedBy(getHasName(itemLike2), has(itemLike2)).save(consumer, MarvelSuperheroes.id(getItemName(itemLike) + string2 + "_" + getItemName(itemLike2)));
        }
    }
}
