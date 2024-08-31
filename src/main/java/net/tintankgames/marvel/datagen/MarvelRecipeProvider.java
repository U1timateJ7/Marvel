package net.tintankgames.marvel.datagen;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.data.MarvelBlockFamilies;
import net.tintankgames.marvel.data.recipes.SuitUpgradingRecipeBuilder;
import net.tintankgames.marvel.data.recipes.SuitVariantRecipeBuilder;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.crafting.*;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

public class MarvelRecipeProvider extends RecipeProvider {
    private static final Map<BlockFamily.Variant, BiFunction<ItemLike, ItemLike, RecipeBuilder>> SHAPE_BUILDERS = ImmutableMap.<BlockFamily.Variant, BiFunction<ItemLike, ItemLike, RecipeBuilder>>builder().put(BlockFamily.Variant.BUTTON, (itemLike, itemLike2) -> buttonBuilder(itemLike, Ingredient.of(itemLike2))).put(BlockFamily.Variant.CHISELED, (itemLike, itemLike2) -> chiseledBuilder(RecipeCategory.BUILDING_BLOCKS, itemLike, Ingredient.of(itemLike2))).put(BlockFamily.Variant.CUT, (itemLike, itemLike2) -> cutBuilder(RecipeCategory.BUILDING_BLOCKS, itemLike, Ingredient.of(itemLike2))).put(BlockFamily.Variant.DOOR, (itemLike, itemLike2) -> doorBuilder(itemLike, Ingredient.of(itemLike2))).put(BlockFamily.Variant.CUSTOM_FENCE, (itemLike, itemLike2) -> fenceBuilder(itemLike, Ingredient.of(itemLike2))).put(BlockFamily.Variant.FENCE, (itemLike, itemLike2) -> fenceBuilder(itemLike, Ingredient.of(itemLike2))).put(BlockFamily.Variant.CUSTOM_FENCE_GATE, (itemLike, itemLike2) -> fenceGateBuilder(itemLike, Ingredient.of(itemLike2))).put(BlockFamily.Variant.FENCE_GATE, (itemLike, itemLike2) -> fenceGateBuilder(itemLike, Ingredient.of(itemLike2))).put(BlockFamily.Variant.SIGN, (itemLike, itemLike2) -> signBuilder(itemLike, Ingredient.of(itemLike2))).put(BlockFamily.Variant.SLAB, (itemLike, itemLike2) -> slabBuilder(RecipeCategory.BUILDING_BLOCKS, itemLike, Ingredient.of(itemLike2))).put(BlockFamily.Variant.STAIRS, (itemLike, itemLike2) -> stairBuilder(itemLike, Ingredient.of(itemLike2))).put(BlockFamily.Variant.PRESSURE_PLATE, (itemLike, itemLike2) -> pressurePlateBuilder(RecipeCategory.REDSTONE, itemLike, Ingredient.of(itemLike2))).put(BlockFamily.Variant.POLISHED, (itemLike, itemLike2) -> polishedBuilder(RecipeCategory.BUILDING_BLOCKS, itemLike, Ingredient.of(itemLike2))).put(BlockFamily.Variant.TRAPDOOR, (itemLike, itemLike2) -> trapdoorBuilder(itemLike, Ingredient.of(itemLike2))).put(BlockFamily.Variant.WALL, (itemLike, itemLike2) -> wallBuilder(RecipeCategory.DECORATIONS, itemLike, Ingredient.of(itemLike2))).build();

    public MarvelRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        mvgenerateRecipes(output, MarvelBlockFamilies.GREEN_HYDRA_BRICKS);
        mvstonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, MarvelBlocks.GREEN_HYDRA_BRICK_STAIRS, MarvelBlocks.GREEN_HYDRA_BRICKS);
        mvstonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, MarvelBlocks.GREEN_HYDRA_BRICK_SLAB, MarvelBlocks.GREEN_HYDRA_BRICKS, 2);
        mvstonecutterResultFromBase(output, RecipeCategory.DECORATIONS, MarvelBlocks.GREEN_HYDRA_BRICK_WALL, MarvelBlocks.GREEN_HYDRA_BRICKS);

        mvgenerateRecipes(output, MarvelBlockFamilies.YELLOW_HYDRA_BRICKS);
        mvstonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, MarvelBlocks.YELLOW_HYDRA_BRICK_STAIRS, MarvelBlocks.YELLOW_HYDRA_BRICKS);
        mvstonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, MarvelBlocks.YELLOW_HYDRA_BRICK_SLAB, MarvelBlocks.YELLOW_HYDRA_BRICKS, 2);
        mvstonecutterResultFromBase(output, RecipeCategory.DECORATIONS, MarvelBlocks.YELLOW_HYDRA_BRICK_WALL, MarvelBlocks.YELLOW_HYDRA_BRICKS);

        mvgenerateRecipes(output, MarvelBlockFamilies.LIGHT_GRAY_HYDRA_BRICKS);
        mvstonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, MarvelBlocks.LIGHT_GRAY_HYDRA_BRICK_STAIRS, MarvelBlocks.LIGHT_GRAY_HYDRA_BRICKS);
        mvstonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, MarvelBlocks.LIGHT_GRAY_HYDRA_BRICK_SLAB, MarvelBlocks.LIGHT_GRAY_HYDRA_BRICKS, 2);
        mvstonecutterResultFromBase(output, RecipeCategory.DECORATIONS, MarvelBlocks.LIGHT_GRAY_HYDRA_BRICK_WALL, MarvelBlocks.LIGHT_GRAY_HYDRA_BRICKS);

        mvgenerateRecipes(output, MarvelBlockFamilies.GRAY_HYDRA_BRICKS);
        mvstonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, MarvelBlocks.GRAY_HYDRA_BRICK_STAIRS, MarvelBlocks.GRAY_HYDRA_BRICKS);
        mvstonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, MarvelBlocks.GRAY_HYDRA_BRICK_SLAB, MarvelBlocks.GRAY_HYDRA_BRICKS, 2);
        mvstonecutterResultFromBase(output, RecipeCategory.DECORATIONS, MarvelBlocks.GRAY_HYDRA_BRICK_WALL, MarvelBlocks.GRAY_HYDRA_BRICKS);

        mvcopperBulb(output, MarvelBlocks.GREEN_HYDRA_BRICK_LAMP.get(), MarvelBlocks.GREEN_HYDRA_BRICKS.get());
        mvcopperBulb(output, MarvelBlocks.YELLOW_HYDRA_BRICK_LAMP.get(), MarvelBlocks.YELLOW_HYDRA_BRICKS.get());
        mvcopperBulb(output, MarvelBlocks.LIGHT_GRAY_HYDRA_BRICK_LAMP.get(), MarvelBlocks.LIGHT_GRAY_HYDRA_BRICKS.get());
        mvcopperBulb(output, MarvelBlocks.GRAY_HYDRA_BRICK_LAMP.get(), MarvelBlocks.GRAY_HYDRA_BRICKS.get());
        mvcopperBulb(output, MarvelBlocks.STONE_BRICK_LAMP.get(), Blocks.STONE_BRICKS);
        mvcopperBulb(output, MarvelBlocks.DEEPSLATE_BRICK_LAMP.get(), Blocks.DEEPSLATE_BRICKS);

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
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MarvelItems.GOLD_TITANIUM_INGOT, 2).requires(MarvelItems.TITANIUM_INGOT).requires(Items.GOLD_INGOT).unlockedBy("has_titanium_ingot", has(MarvelItems.TITANIUM_INGOT)).save(output);
        mvnineBlockStorageRecipesRecipesWithCustomUnpacking(output, RecipeCategory.MISC, MarvelItems.GOLD_TITANIUM_INGOT, RecipeCategory.BUILDING_BLOCKS, MarvelBlocks.GOLD_TITANIUM_BLOCK, "gold_titanium_ingot_from_gold_titanium_block", "gold_titanium_ingot");
        mvnineBlockStorageRecipesWithCustomPacking(output, RecipeCategory.MISC, MarvelItems.GOLD_TITANIUM_NUGGET, RecipeCategory.MISC, MarvelItems.GOLD_TITANIUM_INGOT, "gold_titanium_ingot_from_nuggets", "gold_titanium_ingot");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MarvelItems.ADAMANTIUM_INGOT).requires(Tags.Items.INGOTS_IRON).requires(Tags.Items.INGOTS_NETHERITE).requires(Items.MAGMA_CREAM).unlockedBy("has_netherite_ingot", has(Tags.Items.INGOTS_NETHERITE)).save(output);
        mvnineBlockStorageRecipesRecipesWithCustomUnpacking(output, RecipeCategory.MISC, MarvelItems.ADAMANTIUM_INGOT, RecipeCategory.BUILDING_BLOCKS, MarvelBlocks.ADAMANTIUM_BLOCK, "adamantium_ingot_from_adamantium_block", "adamantium_ingot");
        mvnineBlockStorageRecipesWithCustomPacking(output, RecipeCategory.MISC, MarvelItems.ADAMANTIUM_NUGGET, RecipeCategory.MISC, MarvelItems.ADAMANTIUM_INGOT, "adamantium_ingot_from_nuggets", "adamantium_ingot");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MarvelItems.PROTO_ADAMANTIUM_INGOT).requires(Tags.Items.INGOTS_IRON).requires(Tags.Items.INGOTS_NETHERITE).requires(MarvelItems.VIBRANIUM_INGOT).unlockedBy("has_vibranium", has(MarvelItems.VIBRANIUM)).save(output);
        mvnineBlockStorageRecipesRecipesWithCustomUnpacking(output, RecipeCategory.MISC, MarvelItems.PROTO_ADAMANTIUM_INGOT, RecipeCategory.BUILDING_BLOCKS, MarvelBlocks.PROTO_ADAMANTIUM_BLOCK, "proto_adamantium_ingot_from_proto_adamantium_block", "proto_adamantium_ingot");
        mvnineBlockStorageRecipesWithCustomPacking(output, RecipeCategory.MISC, MarvelItems.PROTO_ADAMANTIUM_NUGGET, RecipeCategory.MISC, MarvelItems.PROTO_ADAMANTIUM_INGOT, "proto_adamantium_ingot_from_nuggets", "proto_adamantium_ingot");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MarvelItems.URU_INGOT, 8).define('#', Tags.Items.INGOTS_GOLD).define('X', Items.NETHER_STAR).pattern("###").pattern("#X#").pattern("###").unlockedBy("has_gold_ingot", has(Tags.Items.INGOTS_GOLD)).save(output);
        mvnineBlockStorageRecipesRecipesWithCustomUnpacking(output, RecipeCategory.MISC, MarvelItems.URU_INGOT, RecipeCategory.BUILDING_BLOCKS, MarvelBlocks.URU_BLOCK, "uru_ingot_from_uru_block", "uru_ingot");
        mvnineBlockStorageRecipesWithCustomPacking(output, RecipeCategory.MISC, MarvelItems.URU_NUGGET, RecipeCategory.MISC, MarvelItems.URU_INGOT, "uru_ingot_from_nuggets", "uru_ingot");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MarvelItems.REINFORCED_LEATHER).define('#', Tags.Items.LEATHERS).define('X', Tags.Items.NUGGETS_IRON).define('I', Tags.Items.INGOTS_IRON).pattern("XIX").pattern("X#X").pattern("XIX").unlockedBy("has_leather", has(Tags.Items.LEATHERS)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MarvelItems.VIBRANIUM_WEAVE, 2).define('#', MarvelItems.VIBRANIUM).define('X', Tags.Items.STRINGS).pattern("X#").pattern("#X").unlockedBy("has_vibranium", has(MarvelItems.VIBRANIUM)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MarvelItems.VIBRANIUM_NANITES, 4).define('#', Tags.Items.DUSTS_REDSTONE).define('X', MarvelItems.VIBRANIUM_NUGGET).pattern("XXX").pattern("X#X").pattern("XXX").unlockedBy("has_vibranium", has(MarvelItems.VIBRANIUM)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, MarvelBlocks.SUIT_TABLE).define('#', Blocks.ANVIL).define('X', MarvelItems.TITANIUM_INGOT).pattern("XXX").pattern("X#X").pattern("XXX").unlockedBy("has_titanium_ingot", has(MarvelItems.TITANIUM_INGOT)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, MarvelBlocks.SUIT_CHARGER).define('#', Items.ARMOR_STAND).define('X', Tags.Items.INGOTS_IRON).define('B', Tags.Items.STORAGE_BLOCKS_REDSTONE).define('R', Blocks.REPEATER).pattern("X#X").pattern("XBX").pattern("XRX").unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.VIBRANIUM_SHIELD).define('X', MarvelItems.VIBRANIUM_INGOT).define('#', Tags.Items.LEATHERS).pattern("XXX").pattern("X#X").unlockedBy("has_vibranium", has(MarvelItems.VIBRANIUM)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.PROTO_ADAMANTIUM_SHIELD).define('X', MarvelItems.PROTO_ADAMANTIUM_INGOT).define('#', Tags.Items.LEATHERS).pattern("XXX").pattern("X#X").unlockedBy("has_proto_adamantium_ingot", has(MarvelItems.PROTO_ADAMANTIUM_INGOT)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MarvelItems.WEB_FLUID, 4).define('#', Tags.Items.SLIMEBALLS).define('X', Tags.Items.STRINGS).pattern("X#").pattern("#X").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MarvelItems.UNSTABLE_PYM_PARTICLE).requires(Items.GLASS_BOTTLE).requires(Items.NETHER_WART).requires(Items.BEETROOT).requires(Items.REDSTONE).requires(Items.SUGAR).unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MarvelItems.SYRINGE).define('#', Items.GLASS_BOTTLE).define('X', Tags.Items.INGOTS_IRON).pattern("X").pattern("#").pattern("X").unlockedBy("has_glass", has(Tags.Items.GLASS_BLOCKS_COLORLESS)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.MJOLNIR).define('X', MarvelItems.URU_INGOT).define('#', Tags.Items.RODS_WOODEN).define('L', Items.LEAD).pattern("X#X").pattern("X#X").pattern(" L ").unlockedBy("has_uru_ingot", has(MarvelItems.URU_INGOT)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.STORMBREAKER).define('X', MarvelItems.URU_INGOT).define('#', Tags.Items.RODS_WOODEN).pattern("XXX").pattern("X# ").pattern(" # ").unlockedBy("has_uru_ingot", has(MarvelItems.URU_INGOT)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MarvelItems.PALLADIUM_ARC_REACTOR).define('X', MarvelBlocks.PALLADIUM_BLOCK).define('#', Tags.Items.INGOTS_COPPER).define('G', Tags.Items.GLASS_PANES_COLORLESS).pattern("#G#").pattern("GXG").pattern("#G#").unlockedBy("has_raw_palladium", has(MarvelItems.RAW_PALLADIUM)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MarvelItems.DIAMOND_ARC_REACTOR).define('X', Tags.Items.GEMS_QUARTZ).define('#', Tags.Items.GEMS_DIAMOND).define('G', Tags.Items.GLASS_PANES_COLORLESS).pattern("#G#").pattern("GXG").pattern("#G#").unlockedBy("has_palladium_arc_reactor", has(MarvelItems.PALLADIUM_ARC_REACTOR)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MarvelItems.TESSERACT).define('X', MarvelItems.SPACE_STONE).define('#', Tags.Items.GLASS_BLOCKS_COLORLESS).pattern("###").pattern("#X#").pattern("###").unlockedBy("has_tesseract", has(MarvelItems.TESSERACT)).save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MarvelItems.SPACE_STONE).requires(MarvelItems.TESSERACT).unlockedBy("has_tesseract", has(MarvelItems.TESSERACT)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MarvelItems.KATANA_UPGRADE_SMITHING_TEMPLATE).define('#', Items.DIAMOND_SWORD).define('D', Tags.Items.GEMS_DIAMOND).define('I', MarvelItems.GOLD_TITANIUM_INGOT).pattern("DID").pattern("I#I").pattern("DID").unlockedBy("has_diamond", has(Tags.Items.GEMS_DIAMOND)).save(output);
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(MarvelItems.KATANA_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(Items.DIAMOND_SWORD), Ingredient.of(MarvelItems.TITANIUM_INGOT), RecipeCategory.COMBAT, MarvelItems.KATANAS.get()).unlocks("has_titanium_ingot", has(MarvelItems.TITANIUM_INGOT)).save(output, MarvelSuperheroes.id("katanas_smithing"));

        SpecialRecipeBuilder.special(ShieldArtRecipe::new).save(output, MarvelSuperheroes.id("shield_art"));
        SpecialRecipeBuilder.special(ShieldCleanRecipe::new).save(output, MarvelSuperheroes.id("shield_clean"));
        SpecialRecipeBuilder.special(KineticBlackPantherNecklaceRecipe::new).save(output, MarvelSuperheroes.id("kinetic_black_panther_necklace"));
        SpecialRecipeBuilder.special(KillmongerNecklaceRecipe::new).save(output, MarvelSuperheroes.id("killmonger_necklace"));
        SpecialRecipeBuilder.special(BlackPantherShuriNecklaceRecipe::new).save(output, MarvelSuperheroes.id("black_panther_shuri_necklace"));
        SpecialRecipeBuilder.special(IronManMark5SuitcaseRecipe::new).save(output, MarvelSuperheroes.id("iron_man_mark_5_suitcase"));

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
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CAPTAIN_AMERICA_HELMET), MarvelItems.CAPTAIN_CARTER_HELMET).unlockedBy("has_captain_america_helmet", has(MarvelItems.Tags.CAPTAIN_AMERICA_HELMET)).save(output);
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
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.KINETIC_BLACK_PANTHER_HELMET), MarvelItems.BLACK_PANTHER_SHURI_HELMET).unlockedBy("has_kinetic_black_panther_helmet", has(MarvelItems.Tags.KINETIC_BLACK_PANTHER_HELMET)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.KINETIC_BLACK_PANTHER_CHESTPLATE), MarvelItems.BLACK_PANTHER_SHURI_CHESTPLATE).unlockedBy("has_kinetic_black_panther_chestplate", has(MarvelItems.Tags.KINETIC_BLACK_PANTHER_CHESTPLATE)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.KINETIC_BLACK_PANTHER_LEGGINGS), MarvelItems.BLACK_PANTHER_SHURI_LEGGINGS).unlockedBy("has_kinetic_black_panther_leggings", has(MarvelItems.Tags.KINETIC_BLACK_PANTHER_LEGGINGS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.KINETIC_BLACK_PANTHER_BOOTS), MarvelItems.BLACK_PANTHER_SHURI_BOOTS).unlockedBy("has_kinetic_black_panther_boots", has(MarvelItems.Tags.KINETIC_BLACK_PANTHER_BOOTS)).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.WOLVERINE_HELMET).define('#', Tags.Items.DYES_YELLOW).define('B', Tags.Items.DYES_BLACK).define('X', MarvelItems.REINFORCED_LEATHER).pattern("B B").pattern("#X#").pattern("X X").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.WOLVERINE_CHESTPLATE).define('#', Tags.Items.DYES_YELLOW).define('X', MarvelItems.REINFORCED_LEATHER).define('A', MarvelItems.ADAMANTIUM_INGOT).define('G', MarvelItems.X_GENES).pattern("X X").pattern("AGA").pattern("X#X").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
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
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.WOLVERINE_HELMET), MarvelItems.WOLVERINE_X_FORCE_HELMET).unlockedBy("has_wolverine_helmet", has(MarvelItems.Tags.WOLVERINE_HELMET)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.WOLVERINE_CHESTPLATE), MarvelItems.WOLVERINE_X_FORCE_CHESTPLATE).unlockedBy("has_wolverine_chestplate", has(MarvelItems.Tags.WOLVERINE_CHESTPLATE)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.WOLVERINE_CHESTPLATE), MarvelItems.WOLVERINE_X_FORCE_SLEEVELESS_CHESTPLATE).unlockedBy("has_wolverine_chestplate", has(MarvelItems.Tags.WOLVERINE_CHESTPLATE)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.WOLVERINE_LEGGINGS), MarvelItems.WOLVERINE_X_FORCE_LEGGINGS).unlockedBy("has_wolverine_leggings", has(MarvelItems.Tags.WOLVERINE_LEGGINGS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.WOLVERINE_BOOTS), MarvelItems.WOLVERINE_X_FORCE_BOOTS).unlockedBy("has_wolverine_boots", has(MarvelItems.Tags.WOLVERINE_BOOTS)).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.CYCLOPS_HELMET).define('#', Tags.Items.DYES_BLUE).define('G', Tags.Items.INGOTS_GOLD).define('X', MarvelItems.REINFORCED_LEATHER).define('R', Tags.Items.GEMS_QUARTZ).pattern("X#X").pattern("GRG").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.CYCLOPS_CHESTPLATE).define('#', Tags.Items.DYES_YELLOW).define('B', Tags.Items.DYES_BLUE).define('X', MarvelItems.REINFORCED_LEATHER).define('G', MarvelItems.X_GENES).pattern("X X").pattern("BG#").pattern("X#X").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.CYCLOPS_LEGGINGS).define('#', Tags.Items.DYES_YELLOW).define('B', Tags.Items.DYES_BLUE).define('X', MarvelItems.REINFORCED_LEATHER).pattern("X#X").pattern("B B").pattern("X X").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.CYCLOPS_BOOTS).define('#', Tags.Items.DYES_YELLOW).define('X', MarvelItems.REINFORCED_LEATHER).pattern("X X").pattern("# #").pattern("X X").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CYCLOPS_HELMET), MarvelItems.CYCLOPS_HELMET).unlockedBy("has_cyclops_helmet", has(MarvelItems.Tags.CYCLOPS_HELMET)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CYCLOPS_CHESTPLATE), MarvelItems.CYCLOPS_CHESTPLATE).unlockedBy("has_cyclops_chestplate", has(MarvelItems.Tags.CYCLOPS_CHESTPLATE)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CYCLOPS_LEGGINGS), MarvelItems.CYCLOPS_LEGGINGS).unlockedBy("has_cyclops_leggings", has(MarvelItems.Tags.CYCLOPS_LEGGINGS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CYCLOPS_BOOTS), MarvelItems.CYCLOPS_BOOTS).unlockedBy("has_cyclops_boots", has(MarvelItems.Tags.CYCLOPS_BOOTS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CYCLOPS_HELMET), MarvelItems.CYCLOPS_ASTONISHING_HELMET).unlockedBy("has_cyclops_helmet", has(MarvelItems.Tags.CYCLOPS_HELMET)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CYCLOPS_CHESTPLATE), MarvelItems.CYCLOPS_ASTONISHING_CHESTPLATE).unlockedBy("has_cyclops_chestplate", has(MarvelItems.Tags.CYCLOPS_CHESTPLATE)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CYCLOPS_LEGGINGS), MarvelItems.CYCLOPS_ASTONISHING_LEGGINGS).unlockedBy("has_cyclops_leggings", has(MarvelItems.Tags.CYCLOPS_LEGGINGS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.CYCLOPS_BOOTS), MarvelItems.CYCLOPS_ASTONISHING_BOOTS).unlockedBy("has_cyclops_boots", has(MarvelItems.Tags.CYCLOPS_BOOTS)).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.SPIDER_MAN_HELMET).define('#', Tags.Items.DYES_RED).define('W', Tags.Items.DYES_WHITE).define('X', MarvelItems.REINFORCED_LEATHER).pattern("#X#").pattern("XWX").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.SPIDER_MAN_CHESTPLATE).define('#', Tags.Items.DYES_RED).define('B', Tags.Items.DYES_BLUE).define('T', MarvelItems.TITANIUM_INGOT).define('X', MarvelItems.REINFORCED_LEATHER).pattern("X X").pattern("#T#").pattern("XBX").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.SPIDER_MAN_LEGGINGS).define('#', Tags.Items.DYES_BLUE).define('X', MarvelItems.REINFORCED_LEATHER).pattern("X#X").pattern("# #").pattern("X X").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.SPIDER_MAN_BOOTS).define('#', Tags.Items.DYES_RED).define('X', MarvelItems.REINFORCED_LEATHER).pattern("X X").pattern("# #").pattern("X X").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.SPIDER_MAN_HELMET), MarvelItems.SPIDER_MAN_HELMET).unlockedBy("has_spider_man_helmet", has(MarvelItems.Tags.SPIDER_MAN_HELMET)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.SPIDER_MAN_CHESTPLATE), MarvelItems.SPIDER_MAN_CHESTPLATE).unlockedBy("has_spider_man_chestplate", has(MarvelItems.Tags.SPIDER_MAN_CHESTPLATE)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.SPIDER_MAN_LEGGINGS), MarvelItems.SPIDER_MAN_LEGGINGS).unlockedBy("has_spider_man_leggings", has(MarvelItems.Tags.SPIDER_MAN_LEGGINGS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.SPIDER_MAN_BOOTS), MarvelItems.SPIDER_MAN_BOOTS).unlockedBy("has_spider_man_boots", has(MarvelItems.Tags.SPIDER_MAN_BOOTS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.SPIDER_MAN_HELMET), MarvelItems.SPIDER_MAN_MCU_HELMET).unlockedBy("has_spider_man_helmet", has(MarvelItems.Tags.SPIDER_MAN_HELMET)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.SPIDER_MAN_CHESTPLATE), MarvelItems.SPIDER_MAN_MCU_CHESTPLATE).unlockedBy("has_spider_man_chestplate", has(MarvelItems.Tags.SPIDER_MAN_CHESTPLATE)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.SPIDER_MAN_LEGGINGS), MarvelItems.SPIDER_MAN_MCU_LEGGINGS).unlockedBy("has_spider_man_leggings", has(MarvelItems.Tags.SPIDER_MAN_LEGGINGS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.SPIDER_MAN_BOOTS), MarvelItems.SPIDER_MAN_MCU_BOOTS).unlockedBy("has_spider_man_boots", has(MarvelItems.Tags.SPIDER_MAN_BOOTS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.SPIDER_MAN_HELMET), MarvelItems.MILES_MORALES_HELMET).unlockedBy("has_spider_man_helmet", has(MarvelItems.Tags.SPIDER_MAN_HELMET)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.SPIDER_MAN_CHESTPLATE), MarvelItems.MILES_MORALES_CHESTPLATE).unlockedBy("has_spider_man_chestplate", has(MarvelItems.Tags.SPIDER_MAN_CHESTPLATE)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.SPIDER_MAN_LEGGINGS), MarvelItems.MILES_MORALES_LEGGINGS).unlockedBy("has_spider_man_leggings", has(MarvelItems.Tags.SPIDER_MAN_LEGGINGS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.SPIDER_MAN_BOOTS), MarvelItems.MILES_MORALES_BOOTS).unlockedBy("has_spider_man_boots", has(MarvelItems.Tags.SPIDER_MAN_BOOTS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.SPIDER_MAN_HELMET), MarvelItems.SPIDER_GWEN_HELMET).unlockedBy("has_spider_man_helmet", has(MarvelItems.Tags.SPIDER_MAN_HELMET)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.SPIDER_MAN_CHESTPLATE), MarvelItems.SPIDER_GWEN_CHESTPLATE).unlockedBy("has_spider_man_chestplate", has(MarvelItems.Tags.SPIDER_MAN_CHESTPLATE)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.SPIDER_MAN_LEGGINGS), MarvelItems.SPIDER_GWEN_LEGGINGS).unlockedBy("has_spider_man_leggings", has(MarvelItems.Tags.SPIDER_MAN_LEGGINGS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.SPIDER_MAN_BOOTS), MarvelItems.SPIDER_GWEN_BOOTS).unlockedBy("has_spider_man_boots", has(MarvelItems.Tags.SPIDER_MAN_BOOTS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.SPIDER_MAN_HELMET), MarvelItems.SPIDER_MAN_INSOMNIAC_HELMET).unlockedBy("has_spider_man_helmet", has(MarvelItems.Tags.SPIDER_MAN_HELMET)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.SPIDER_MAN_CHESTPLATE), MarvelItems.SPIDER_MAN_INSOMNIAC_CHESTPLATE).unlockedBy("has_spider_man_chestplate", has(MarvelItems.Tags.SPIDER_MAN_CHESTPLATE)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.SPIDER_MAN_LEGGINGS), MarvelItems.SPIDER_MAN_INSOMNIAC_LEGGINGS).unlockedBy("has_spider_man_leggings", has(MarvelItems.Tags.SPIDER_MAN_LEGGINGS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.SPIDER_MAN_BOOTS), MarvelItems.SPIDER_MAN_INSOMNIAC_BOOTS).unlockedBy("has_spider_man_boots", has(MarvelItems.Tags.SPIDER_MAN_BOOTS)).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.ANT_MAN_HELMET).define('#', Tags.Items.INGOTS_IRON).define('R', Blocks.RED_STAINED_GLASS_PANE).define('X', Blocks.REPEATER).pattern("#X#").pattern("#R#").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.ANT_MAN_CHESTPLATE).define('#', Tags.Items.DYES_RED).define('B', Tags.Items.DYES_BLACK).define('S', Blocks.STONE_BUTTON).define('C', Blocks.COMPARATOR).define('X', MarvelItems.REINFORCED_LEATHER).pattern("X X").pattern("#BS").pattern("XCX").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.ANT_MAN_LEGGINGS).define('#', Tags.Items.DYES_RED).define('B', Tags.Items.DYES_BLACK).define('X', MarvelItems.REINFORCED_LEATHER).pattern("XBX").pattern("# #").pattern("X X").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.ANT_MAN_BOOTS).define('#', Tags.Items.DYES_GRAY).define('X', MarvelItems.REINFORCED_LEATHER).pattern("X X").pattern("# #").pattern("X X").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.ANT_MAN_HELMET), MarvelItems.ANT_MAN_UPGRADED_HELMET).requires(Tags.Items.INGOTS_IRON, 4).requires(Tags.Items.DUSTS_REDSTONE, 2).requires(Blocks.RED_STAINED_GLASS_PANE).unlockedBy("has_ant_man_armor", has(MarvelItems.Tags.ANT_MAN_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.ANT_MAN_CHESTPLATE), MarvelItems.ANT_MAN_UPGRADED_CHESTPLATE).requires(MarvelItems.REINFORCED_LEATHER, 4).requires(Tags.Items.INGOTS_IRON, 2).requires(Tags.Items.DYES_RED).requires(Tags.Items.DYES_BLACK).requires(Tags.Items.DUSTS_REDSTONE).unlockedBy("has_ant_man_armor", has(MarvelItems.Tags.ANT_MAN_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.ANT_MAN_LEGGINGS), MarvelItems.ANT_MAN_UPGRADED_LEGGINGS).requires(MarvelItems.REINFORCED_LEATHER, 4).requires(Tags.Items.INGOTS_IRON, 2).requires(Tags.Items.DYES_RED).requires(Tags.Items.DYES_BLACK).requires(Tags.Items.DUSTS_REDSTONE).unlockedBy("has_ant_man_armor", has(MarvelItems.Tags.ANT_MAN_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.ANT_MAN_BOOTS), MarvelItems.ANT_MAN_UPGRADED_BOOTS).requires(MarvelItems.REINFORCED_LEATHER, 4).requires(Tags.Items.INGOTS_IRON, 2).requires(Tags.Items.DYES_RED).requires(Tags.Items.DYES_BLACK).requires(Tags.Items.DUSTS_REDSTONE).unlockedBy("has_ant_man_armor", has(MarvelItems.Tags.ANT_MAN_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.ANT_MAN_HELMET), MarvelItems.WASP_HELMET).requires(Tags.Items.INGOTS_IRON, 4).requires(Tags.Items.DUSTS_REDSTONE, 2).requires(Blocks.YELLOW_STAINED_GLASS_PANE).unlockedBy("has_ant_man_armor", has(MarvelItems.Tags.ANT_MAN_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.ANT_MAN_CHESTPLATE), MarvelItems.WASP_CHESTPLATE).requires(MarvelItems.REINFORCED_LEATHER, 3).requires(Tags.Items.INGOTS_IRON, 2).requires(Items.HONEYCOMB).requires(Tags.Items.DYES_BLACK).requires(Items.CROSSBOW).requires(Tags.Items.DUSTS_REDSTONE).unlockedBy("has_ant_man_armor", has(MarvelItems.Tags.ANT_MAN_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.ANT_MAN_LEGGINGS), MarvelItems.WASP_LEGGINGS).requires(MarvelItems.REINFORCED_LEATHER, 4).requires(Tags.Items.INGOTS_IRON, 2).requires(Items.HONEYCOMB).requires(Tags.Items.DYES_BLACK).requires(Tags.Items.DUSTS_REDSTONE).unlockedBy("has_ant_man_armor", has(MarvelItems.Tags.ANT_MAN_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.ANT_MAN_BOOTS), MarvelItems.WASP_BOOTS).requires(MarvelItems.REINFORCED_LEATHER, 4).requires(Tags.Items.INGOTS_IRON, 2).requires(Items.HONEYCOMB).requires(Tags.Items.DYES_BLACK).requires(Tags.Items.DUSTS_REDSTONE).unlockedBy("has_ant_man_armor", has(MarvelItems.Tags.ANT_MAN_ARMOR)).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.IRON_MAN_MARK_1_HELMET).define('#', Tags.Items.INGOTS_IRON).define('X', Blocks.IRON_BARS).pattern("###").pattern("#X#").unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.IRON_MAN_MARK_1_CHESTPLATE).define('#', Tags.Items.INGOTS_IRON).define('X', Tags.Items.LEATHERS).define('A', MarvelItems.PALLADIUM_ARC_REACTOR).define('F', Blocks.FURNACE).pattern("# #").pattern("XAX").pattern("#F#").unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.IRON_MAN_MARK_1_LEGGINGS).define('#', Tags.Items.INGOTS_IRON).define('X', Tags.Items.LEATHERS).pattern("###").pattern("X X").pattern("# #").unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.IRON_MAN_MARK_1_BOOTS).define('#', Tags.Items.INGOTS_IRON).define('X', Tags.Items.LEATHERS).pattern("X X").pattern("# #").pattern("# #").unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.IRON_MAN_MARK_1_HELMET), MarvelItems.IRON_MAN_MARK_2_HELMET).requires(MarvelItems.TITANIUM_INGOT, 3).requires(Tags.Items.INGOTS_IRON, 3).requires(Tags.Items.DUSTS_REDSTONE).requires(Tags.Items.GLASS_PANES_COLORLESS).unlockedBy("has_iron_man_mark_1_armor", has(MarvelItems.Tags.IRON_MAN_MARK_1_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.IRON_MAN_MARK_1_CHESTPLATE), MarvelItems.IRON_MAN_MARK_2_CHESTPLATE).requires(MarvelItems.TITANIUM_INGOT, 3).requires(Tags.Items.INGOTS_IRON, 3).requires(Tags.Items.DUSTS_REDSTONE).requires(MarvelItems.PALLADIUM_ARC_REACTOR).unlockedBy("has_iron_man_mark_1_armor", has(MarvelItems.Tags.IRON_MAN_MARK_1_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.IRON_MAN_MARK_1_LEGGINGS), MarvelItems.IRON_MAN_MARK_2_LEGGINGS).requires(MarvelItems.TITANIUM_INGOT, 3).requires(Tags.Items.INGOTS_IRON, 3).requires(Tags.Items.DUSTS_REDSTONE).unlockedBy("has_iron_man_mark_1_armor", has(MarvelItems.Tags.IRON_MAN_MARK_1_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.IRON_MAN_MARK_1_BOOTS), MarvelItems.IRON_MAN_MARK_2_BOOTS).requires(MarvelItems.TITANIUM_INGOT, 3).requires(Tags.Items.INGOTS_IRON, 3).requires(Blocks.REPEATER).unlockedBy("has_iron_man_mark_1_armor", has(MarvelItems.Tags.IRON_MAN_MARK_1_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.IRON_MAN_MARK_2_HELMET), MarvelItems.IRON_MAN_MARK_3_HELMET).requires(MarvelItems.GOLD_TITANIUM_INGOT, 4).requires(Tags.Items.INGOTS_GOLD, 2).requires(Tags.Items.DUSTS_REDSTONE).requires(Tags.Items.GLASS_PANES_COLORLESS).unlockedBy("has_iron_man_mark_2_armor", has(MarvelItems.Tags.IRON_MAN_MARK_2_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.IRON_MAN_MARK_2_CHESTPLATE), MarvelItems.IRON_MAN_MARK_3_CHESTPLATE).requires(MarvelItems.GOLD_TITANIUM_INGOT, 4).requires(Tags.Items.INGOTS_GOLD, 2).requires(Tags.Items.DUSTS_REDSTONE).requires(MarvelItems.PALLADIUM_ARC_REACTOR).unlockedBy("has_iron_man_mark_2_armor", has(MarvelItems.Tags.IRON_MAN_MARK_2_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.IRON_MAN_MARK_2_LEGGINGS), MarvelItems.IRON_MAN_MARK_3_LEGGINGS).requires(MarvelItems.GOLD_TITANIUM_INGOT, 4).requires(Tags.Items.INGOTS_GOLD, 2).requires(Tags.Items.DUSTS_REDSTONE).unlockedBy("has_iron_man_mark_2_armor", has(MarvelItems.Tags.IRON_MAN_MARK_2_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.IRON_MAN_MARK_2_BOOTS), MarvelItems.IRON_MAN_MARK_3_BOOTS).requires(MarvelItems.GOLD_TITANIUM_INGOT, 4).requires(Tags.Items.INGOTS_GOLD, 2).requires(Blocks.REPEATER).unlockedBy("has_iron_man_mark_2_armor", has(MarvelItems.Tags.IRON_MAN_MARK_2_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.IRON_MAN_MARK_3_HELMET), MarvelItems.IRON_MAN_MARK_5_HELMET).requires(MarvelItems.GOLD_TITANIUM_INGOT, 3).requires(Tags.Items.INGOTS_IRON, 3).requires(Blocks.TRIPWIRE_HOOK).requires(Tags.Items.GLASS_PANES_COLORLESS).unlockedBy("has_iron_man_mark_3_armor", has(MarvelItems.Tags.IRON_MAN_MARK_3_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.IRON_MAN_MARK_3_CHESTPLATE), MarvelItems.IRON_MAN_MARK_5_CHESTPLATE).requires(MarvelItems.GOLD_TITANIUM_INGOT, 3).requires(Tags.Items.INGOTS_IRON, 3).requires(Blocks.TRIPWIRE_HOOK).requires(MarvelItems.PALLADIUM_ARC_REACTOR).unlockedBy("has_iron_man_mark_3_armor", has(MarvelItems.Tags.IRON_MAN_MARK_3_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.IRON_MAN_MARK_3_LEGGINGS), MarvelItems.IRON_MAN_MARK_5_LEGGINGS).requires(MarvelItems.GOLD_TITANIUM_INGOT, 3).requires(Tags.Items.INGOTS_IRON, 3).requires(Blocks.TRIPWIRE_HOOK).unlockedBy("has_iron_man_mark_3_armor", has(MarvelItems.Tags.IRON_MAN_MARK_3_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.IRON_MAN_MARK_3_BOOTS), MarvelItems.IRON_MAN_MARK_5_BOOTS).requires(MarvelItems.GOLD_TITANIUM_INGOT, 3).requires(Tags.Items.INGOTS_IRON, 3).requires(Blocks.REPEATER).requires(Blocks.TRIPWIRE_HOOK).unlockedBy("has_iron_man_mark_3_armor", has(MarvelItems.Tags.IRON_MAN_MARK_3_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.IRON_MAN_MARK_5_HELMET), MarvelItems.IRON_MAN_MARK_6_HELMET).requires(MarvelItems.GOLD_TITANIUM_INGOT, 3).requires(Tags.Items.INGOTS_GOLD, 2).requires(Tags.Items.INGOTS_IRON, 1).requires(Tags.Items.DUSTS_REDSTONE).requires(Tags.Items.GLASS_PANES_COLORLESS).unlockedBy("has_iron_man_mark_5_armor", has(MarvelItems.Tags.IRON_MAN_MARK_5_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.IRON_MAN_MARK_5_CHESTPLATE), MarvelItems.IRON_MAN_MARK_6_CHESTPLATE).requires(MarvelItems.GOLD_TITANIUM_INGOT, 3).requires(Tags.Items.INGOTS_GOLD, 2).requires(Tags.Items.INGOTS_IRON, 1).requires(Tags.Items.DUSTS_REDSTONE).requires(MarvelItems.DIAMOND_ARC_REACTOR).unlockedBy("has_iron_man_mark_5_armor", has(MarvelItems.Tags.IRON_MAN_MARK_5_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.IRON_MAN_MARK_5_LEGGINGS), MarvelItems.IRON_MAN_MARK_6_LEGGINGS).requires(MarvelItems.GOLD_TITANIUM_INGOT, 3).requires(Tags.Items.INGOTS_GOLD, 2).requires(Tags.Items.INGOTS_IRON, 1).requires(Tags.Items.DUSTS_REDSTONE).unlockedBy("has_iron_man_mark_5_armor", has(MarvelItems.Tags.IRON_MAN_MARK_5_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.IRON_MAN_MARK_5_BOOTS), MarvelItems.IRON_MAN_MARK_6_BOOTS).requires(MarvelItems.GOLD_TITANIUM_INGOT, 3).requires(Tags.Items.INGOTS_GOLD, 2).requires(Tags.Items.INGOTS_IRON, 1).requires(Blocks.REPEATER).unlockedBy("has_iron_man_mark_5_armor", has(MarvelItems.Tags.IRON_MAN_MARK_5_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.IRON_MAN_MARK_6_HELMET), MarvelItems.IRON_MAN_MARK_7_HELMET).requires(MarvelItems.GOLD_TITANIUM_INGOT, 3).requires(Tags.Items.INGOTS_GOLD, 2).requires(Tags.Items.INGOTS_IRON, 1).requires(Tags.Items.DUSTS_REDSTONE).requires(Tags.Items.GLASS_PANES_COLORLESS).unlockedBy("has_iron_man_mark_6_armor", has(MarvelItems.Tags.IRON_MAN_MARK_6_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.IRON_MAN_MARK_6_CHESTPLATE), MarvelItems.IRON_MAN_MARK_7_CHESTPLATE).requires(MarvelItems.GOLD_TITANIUM_INGOT, 3).requires(Tags.Items.INGOTS_GOLD, 2).requires(Tags.Items.INGOTS_IRON, 1).requires(Tags.Items.DUSTS_REDSTONE).requires(MarvelItems.DIAMOND_ARC_REACTOR).unlockedBy("has_iron_man_mark_6_armor", has(MarvelItems.Tags.IRON_MAN_MARK_6_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.IRON_MAN_MARK_6_LEGGINGS), MarvelItems.IRON_MAN_MARK_7_LEGGINGS).requires(MarvelItems.GOLD_TITANIUM_INGOT, 3).requires(Tags.Items.INGOTS_GOLD, 2).requires(Tags.Items.INGOTS_IRON, 1).requires(Tags.Items.DUSTS_REDSTONE).unlockedBy("has_iron_man_mark_6_armor", has(MarvelItems.Tags.IRON_MAN_MARK_6_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.IRON_MAN_MARK_6_BOOTS), MarvelItems.IRON_MAN_MARK_7_BOOTS).requires(MarvelItems.GOLD_TITANIUM_INGOT, 3).requires(Tags.Items.INGOTS_GOLD, 2).requires(Tags.Items.INGOTS_IRON, 1).requires(Blocks.REPEATER).unlockedBy("has_iron_man_mark_6_armor", has(MarvelItems.Tags.IRON_MAN_MARK_6_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.IRON_MAN_MARK_2_HELMET), MarvelItems.WAR_MACHINE_MARK_1_HELMET, true).requires(MarvelItems.TITANIUM_INGOT, 4).requires(Tags.Items.INGOTS_IRON, 2).unlockedBy("has_iron_man_mark_2_armor", has(MarvelItems.Tags.IRON_MAN_MARK_2_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.IRON_MAN_MARK_2_CHESTPLATE), MarvelItems.WAR_MACHINE_MARK_1_CHESTPLATE, true).requires(MarvelItems.TITANIUM_INGOT, 4).requires(Tags.Items.INGOTS_IRON, 2).requires(Items.CROSSBOW).unlockedBy("has_iron_man_mark_2_armor", has(MarvelItems.Tags.IRON_MAN_MARK_2_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.IRON_MAN_MARK_2_LEGGINGS), MarvelItems.WAR_MACHINE_MARK_1_LEGGINGS, true).requires(MarvelItems.TITANIUM_INGOT, 4).requires(Tags.Items.INGOTS_IRON, 2).unlockedBy("has_iron_man_mark_2_armor", has(MarvelItems.Tags.IRON_MAN_MARK_2_ARMOR)).save(output);
        SuitUpgradingRecipeBuilder.upgrade(Ingredient.of(MarvelItems.Tags.IRON_MAN_MARK_2_BOOTS), MarvelItems.WAR_MACHINE_MARK_1_BOOTS, true).requires(MarvelItems.TITANIUM_INGOT, 4).requires(Tags.Items.INGOTS_IRON, 2).unlockedBy("has_iron_man_mark_2_armor", has(MarvelItems.Tags.IRON_MAN_MARK_2_ARMOR)).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.QUICKSILVER_CHESTPLATE).define('#', Tags.Items.DYES_LIGHT_BLUE).define('X', MarvelItems.REINFORCED_LEATHER).define('G', MarvelItems.X_GENES).define('W', Tags.Items.DYES_WHITE).pattern("X X").pattern("#G#").pattern("XWX").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.QUICKSILVER_LEGGINGS).define('#', Tags.Items.DYES_WHITE).define('X', MarvelItems.REINFORCED_LEATHER).define('C', Tags.Items.DYES_CYAN).pattern("X#X").pattern("C C").pattern("X X").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.QUICKSILVER_BOOTS).define('#', Tags.Items.DYES_WHITE).define('X', MarvelItems.REINFORCED_LEATHER).pattern("X X").pattern("# #").pattern("X X").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.DEADPOOL_HELMET).define('#', Tags.Items.DYES_RED).define('X', MarvelItems.REINFORCED_LEATHER).define('B', Tags.Items.DYES_BLACK).pattern("#X#").pattern("XBX").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.DEADPOOL_CHESTPLATE).define('#', Tags.Items.DYES_RED).define('X', MarvelItems.REINFORCED_LEATHER).define('G', MarvelItems.X_GENES).define('B', Tags.Items.DYES_BLACK).pattern("X X").pattern("#G#").pattern("XBX").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.DEADPOOL_LEGGINGS).define('#', Tags.Items.DYES_RED).define('X', MarvelItems.REINFORCED_LEATHER).define('B', Tags.Items.DYES_BLACK).pattern("X#X").pattern("B B").pattern("X X").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MarvelItems.DEADPOOL_BOOTS).define('#', Tags.Items.DYES_BLACK).define('X', MarvelItems.REINFORCED_LEATHER).pattern("X X").pattern("# #").pattern("X X").unlockedBy("has_reinforced_leather", has(MarvelItems.REINFORCED_LEATHER)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.DEADPOOL_HELMET), MarvelItems.DEADPOOL_HELMET).unlockedBy("has_deadpool_helmet", has(MarvelItems.Tags.DEADPOOL_HELMET)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.DEADPOOL_CHESTPLATE), MarvelItems.DEADPOOL_CHESTPLATE).unlockedBy("has_deadpool_chestplate", has(MarvelItems.Tags.DEADPOOL_CHESTPLATE)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.DEADPOOL_LEGGINGS), MarvelItems.DEADPOOL_LEGGINGS).unlockedBy("has_deadpool_leggings", has(MarvelItems.Tags.DEADPOOL_LEGGINGS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.DEADPOOL_BOOTS), MarvelItems.DEADPOOL_BOOTS).unlockedBy("has_deadpool_boots", has(MarvelItems.Tags.DEADPOOL_BOOTS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.DEADPOOL_HELMET), MarvelItems.DEADPOOL_X_FORCE_HELMET).unlockedBy("has_deadpool_helmet", has(MarvelItems.Tags.DEADPOOL_HELMET)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.DEADPOOL_CHESTPLATE), MarvelItems.DEADPOOL_X_FORCE_CHESTPLATE).unlockedBy("has_deadpool_chestplate", has(MarvelItems.Tags.DEADPOOL_CHESTPLATE)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.DEADPOOL_LEGGINGS), MarvelItems.DEADPOOL_X_FORCE_LEGGINGS).unlockedBy("has_deadpool_leggings", has(MarvelItems.Tags.DEADPOOL_LEGGINGS)).save(output);
        SuitVariantRecipeBuilder.variant(Ingredient.of(MarvelItems.Tags.DEADPOOL_BOOTS), MarvelItems.DEADPOOL_X_FORCE_BOOTS).unlockedBy("has_deadpool_boots", has(MarvelItems.Tags.DEADPOOL_BOOTS)).save(output);
    }

    public static void mvstonecutterResultFromBase(RecipeOutput consumer, RecipeCategory recipeCategory, ItemLike result, ItemLike item) {
        mvstonecutterResultFromBase(consumer, recipeCategory, result, item, 1);
    }

    public static void mvstonecutterResultFromBase(RecipeOutput consumer, RecipeCategory recipeCategory, ItemLike result, ItemLike item, int count) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(item), recipeCategory, result, count).unlockedBy(getHasName(item), has(item)).save(consumer, MarvelSuperheroes.id(getConversionRecipeName(result, item) + "_stonecutting"));
    }

    public static void mvgenerateRecipes(RecipeOutput consumer, BlockFamily blockFamily) {
        blockFamily.getVariants().forEach((variant, block) -> {
            BiFunction<ItemLike, ItemLike, RecipeBuilder> biFunction = SHAPE_BUILDERS.get(variant);
            Block itemLike = getBaseBlock(blockFamily, variant);
            if (biFunction != null) {
                RecipeBuilder recipeBuilder = biFunction.apply(block, itemLike);
                blockFamily.getRecipeGroupPrefix().ifPresent(string -> recipeBuilder.group(string + (variant == BlockFamily.Variant.CUT ? "" : "_" + variant.getRecipeGroup())));
                recipeBuilder.unlockedBy(blockFamily.getRecipeUnlockedBy().orElseGet(() -> getHasName(itemLike)), has(itemLike));
                recipeBuilder.save(consumer, MarvelSuperheroes.id(getItemName(recipeBuilder.getResult())));
            }
            if (variant == BlockFamily.Variant.CRACKED) {
                mvsmeltingResultFromBase(consumer, block, itemLike);
            }
        });
    }

    public static void mvsmeltingResultFromBase(RecipeOutput consumer, ItemLike itemLike, ItemLike itemLike2) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(itemLike2), RecipeCategory.BUILDING_BLOCKS, itemLike, 0.1f, 200).unlockedBy(RecipeProvider.getHasName(itemLike2), RecipeProvider.has(itemLike2)).save(consumer, MarvelSuperheroes.id(getItemName(itemLike)));
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

    protected static void mvcopperBulb(RecipeOutput p_308971_, Block bulb, Block base) {
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, bulb, 4).define('C', base).define('R', Items.REDSTONE).define('B', Items.BLAZE_ROD).pattern(" C ").pattern("CBC").pattern(" R ").unlockedBy(getHasName(base), has(base)).save(p_308971_, MarvelSuperheroes.id(getItemName(bulb)));
    }
}
