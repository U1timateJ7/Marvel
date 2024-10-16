package net.tintankgames.marvel.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.damagesources.MarvelDamageTypes;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.level.biome.MarvelBiomes;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;
import net.tintankgames.marvel.world.level.block.entity.MarvelBannerPatterns;
import net.tintankgames.marvel.world.level.levelgen.structure.MarvelStructures;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class MarvelTagProvider {
    public static void addProviders(GatherDataEvent event) {
        BlockProvider blockTagProvider = event.getGenerator().addProvider(event.includeServer(), (DataProvider.Factory<BlockProvider>) output -> new BlockProvider(output, event.getLookupProvider(), event.getExistingFileHelper()));
        event.getGenerator().addProvider(event.includeServer(), (DataProvider.Factory<ItemProvider>) output -> new ItemProvider(output, event.getLookupProvider(), blockTagProvider.contentsGetter(), event.getExistingFileHelper()));
        event.getGenerator().addProvider(event.includeServer(), (DataProvider.Factory<EntityTypeProvider>) output -> new EntityTypeProvider(output, event.getLookupProvider(), event.getExistingFileHelper()));
        event.getGenerator().addProvider(event.includeServer(), (DataProvider.Factory<StructureProvider>) output -> new StructureProvider(output, event.getLookupProvider(), event.getExistingFileHelper()));
        event.getGenerator().addProvider(event.includeServer(), (DataProvider.Factory<BiomeProvider>) output -> new BiomeProvider(output, event.getLookupProvider(), event.getExistingFileHelper()));
        event.getGenerator().addProvider(event.includeServer(), (DataProvider.Factory<DamageTypeProvider>) output -> new DamageTypeProvider(output, event.getLookupProvider(), event.getExistingFileHelper()));
        event.getGenerator().addProvider(event.includeServer(), (DataProvider.Factory<BannerPatternProvider>) output -> new BannerPatternProvider(output, event.getLookupProvider(), event.getExistingFileHelper()));
    }

    public static class BlockProvider extends BlockTagsProvider {
        public BlockProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, MarvelSuperheroes.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MarvelBlocks.VIBRANIUM_ORE.get(), MarvelBlocks.DEEPSLATE_VIBRANIUM_ORE.get(), MarvelBlocks.VIBRANIUM_BLOCK.get(), MarvelBlocks.TITANIUM_ORE.get(), MarvelBlocks.DEEPSLATE_TITANIUM_ORE.get(), MarvelBlocks.TITANIUM_BLOCK.get(), MarvelBlocks.RAW_TITANIUM_BLOCK.get(), MarvelBlocks.PALLADIUM_ORE.get(), MarvelBlocks.DEEPSLATE_PALLADIUM_ORE.get(), MarvelBlocks.PALLADIUM_BLOCK.get(), MarvelBlocks.RAW_PALLADIUM_BLOCK.get(), MarvelBlocks.GOLD_TITANIUM_BLOCK.get(), MarvelBlocks.ADAMANTIUM_BLOCK.get(), MarvelBlocks.PROTO_ADAMANTIUM_BLOCK.get(), MarvelBlocks.URU_BLOCK.get(), MarvelBlocks.SUIT_TABLE.get(), MarvelBlocks.SUIT_CHARGER.get(), MarvelBlocks.SUIT_CHARGER_UPPER.get(), MarvelBlocks.GREEN_HYDRA_BRICKS.get(), MarvelBlocks.GREEN_HYDRA_BRICK_SLAB.get(), MarvelBlocks.GREEN_HYDRA_BRICK_STAIRS.get(), MarvelBlocks.GREEN_HYDRA_BRICK_WALL.get(), MarvelBlocks.GREEN_HYDRA_BRICK_LAMP.get(), MarvelBlocks.YELLOW_HYDRA_BRICKS.get(), MarvelBlocks.YELLOW_HYDRA_BRICK_SLAB.get(), MarvelBlocks.YELLOW_HYDRA_BRICK_STAIRS.get(), MarvelBlocks.YELLOW_HYDRA_BRICK_WALL.get(), MarvelBlocks.YELLOW_HYDRA_BRICK_LAMP.get(), MarvelBlocks.LIGHT_GRAY_HYDRA_BRICKS.get(), MarvelBlocks.LIGHT_GRAY_HYDRA_BRICK_SLAB.get(), MarvelBlocks.LIGHT_GRAY_HYDRA_BRICK_STAIRS.get(), MarvelBlocks.LIGHT_GRAY_HYDRA_BRICK_WALL.get(), MarvelBlocks.LIGHT_GRAY_HYDRA_BRICK_LAMP.get(), MarvelBlocks.GRAY_HYDRA_BRICKS.get(), MarvelBlocks.GRAY_HYDRA_BRICK_SLAB.get(), MarvelBlocks.GRAY_HYDRA_BRICK_STAIRS.get(), MarvelBlocks.GRAY_HYDRA_BRICK_WALL.get(), MarvelBlocks.GRAY_HYDRA_BRICK_LAMP.get(), MarvelBlocks.STONE_BRICK_LAMP.get(), MarvelBlocks.DEEPSLATE_BRICK_LAMP.get());
            tag(BlockTags.BEACON_BASE_BLOCKS).add(MarvelBlocks.VIBRANIUM_BLOCK.get(), MarvelBlocks.TITANIUM_BLOCK.get(), MarvelBlocks.ADAMANTIUM_BLOCK.get(), MarvelBlocks.PROTO_ADAMANTIUM_BLOCK.get(), MarvelBlocks.URU_BLOCK.get());
            tag(BlockTags.DRAGON_IMMUNE).add(MarvelBlocks.TESSERACT.get(), MarvelBlocks.MJOLNIR.get());
            tag(BlockTags.NEEDS_DIAMOND_TOOL).add(MarvelBlocks.VIBRANIUM_ORE.get(), MarvelBlocks.DEEPSLATE_VIBRANIUM_ORE.get(), MarvelBlocks.VIBRANIUM_BLOCK.get(), MarvelBlocks.ADAMANTIUM_BLOCK.get(), MarvelBlocks.PROTO_ADAMANTIUM_BLOCK.get(), MarvelBlocks.URU_BLOCK.get());
            tag(BlockTags.NEEDS_IRON_TOOL).add(MarvelBlocks.TITANIUM_ORE.get(), MarvelBlocks.DEEPSLATE_TITANIUM_ORE.get(), MarvelBlocks.TITANIUM_BLOCK.get(), MarvelBlocks.RAW_TITANIUM_BLOCK.get(), MarvelBlocks.GOLD_TITANIUM_BLOCK.get());
            tag(BlockTags.NEEDS_STONE_TOOL).add(MarvelBlocks.PALLADIUM_ORE.get(), MarvelBlocks.DEEPSLATE_PALLADIUM_ORE.get(), MarvelBlocks.PALLADIUM_BLOCK.get(), MarvelBlocks.RAW_PALLADIUM_BLOCK.get(), MarvelBlocks.SUIT_TABLE.get(), MarvelBlocks.SUIT_CHARGER.get(), MarvelBlocks.SUIT_CHARGER_UPPER.get());
            tag(BlockTags.SLABS).add(MarvelBlocks.GREEN_HYDRA_BRICK_SLAB.get(), MarvelBlocks.YELLOW_HYDRA_BRICK_SLAB.get(), MarvelBlocks.LIGHT_GRAY_HYDRA_BRICK_SLAB.get(), MarvelBlocks.GRAY_HYDRA_BRICK_SLAB.get());
            tag(BlockTags.STAIRS).add(MarvelBlocks.GREEN_HYDRA_BRICK_STAIRS.get(), MarvelBlocks.YELLOW_HYDRA_BRICK_STAIRS.get(), MarvelBlocks.LIGHT_GRAY_HYDRA_BRICK_STAIRS.get(), MarvelBlocks.GRAY_HYDRA_BRICK_STAIRS.get());
            tag(BlockTags.WALLS).add(MarvelBlocks.GREEN_HYDRA_BRICK_WALL.get(), MarvelBlocks.YELLOW_HYDRA_BRICK_WALL.get(), MarvelBlocks.LIGHT_GRAY_HYDRA_BRICK_WALL.get(), MarvelBlocks.GRAY_HYDRA_BRICK_WALL.get());
            tag(BlockTags.WITHER_IMMUNE).add(MarvelBlocks.TESSERACT.get(), MarvelBlocks.MJOLNIR.get());

            tag(MarvelBlocks.Tags.VIBRANIUM_ORES).add(MarvelBlocks.VIBRANIUM_ORE.get(), MarvelBlocks.DEEPSLATE_VIBRANIUM_ORE.get());
            tag(MarvelBlocks.Tags.TITANIUM_ORES).add(MarvelBlocks.TITANIUM_ORE.get(), MarvelBlocks.DEEPSLATE_TITANIUM_ORE.get());
            tag(MarvelBlocks.Tags.PALLADIUM_ORES).add(MarvelBlocks.PALLADIUM_ORE.get(), MarvelBlocks.DEEPSLATE_PALLADIUM_ORE.get());
            tag(MarvelBlocks.Tags.HYDRA_AGENT_SPAWNABLE_ON).add(MarvelBlocks.GREEN_HYDRA_BRICKS.get(), MarvelBlocks.YELLOW_HYDRA_BRICKS.get(), Blocks.STONE_BRICKS, MarvelBlocks.LIGHT_GRAY_HYDRA_BRICKS.get(), Blocks.DEEPSLATE_BRICKS, MarvelBlocks.GRAY_HYDRA_BRICKS.get());
        }
    }

    public static class ItemProvider extends ItemTagsProvider {
        public ItemProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, blockTags, MarvelSuperheroes.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            tag(ItemTags.AXES).add(MarvelItems.VIBRANIUM_AXE.get());
            tag(ItemTags.BEACON_PAYMENT_ITEMS).add(MarvelItems.VIBRANIUM_INGOT.get(), MarvelItems.TITANIUM_INGOT.get(), MarvelItems.ADAMANTIUM_INGOT.get(), MarvelItems.PROTO_ADAMANTIUM_INGOT.get(), MarvelItems.URU_INGOT.get());
            tag(ItemTags.CHEST_ARMOR).add(MarvelItems.VIBRANIUM_CHESTPLATE.get());
            tag(ItemTags.CROSSBOW_ENCHANTABLE).add(MarvelItems.TESSERACT_CROSSBOW.get());
            tag(ItemTags.DYEABLE).add(MarvelItems.VIBRANIUM_SHIELD.get(), MarvelItems.PROTO_ADAMANTIUM_SHIELD.get(), MarvelItems.CAPTAIN_MARVEL_HELMET.get());
            tag(ItemTags.DURABILITY_ENCHANTABLE).add(MarvelItems.TESSERACT_CROSSBOW.get());
            tag(ItemTags.FOOT_ARMOR).add(MarvelItems.VIBRANIUM_BOOTS.get());
            tag(ItemTags.HEAD_ARMOR).add(MarvelItems.VIBRANIUM_HELMET.get());
            tag(ItemTags.HOES).add(MarvelItems.VIBRANIUM_HOE.get());
            tag(ItemTags.LEG_ARMOR).add(MarvelItems.VIBRANIUM_LEGGINGS.get());
            tag(ItemTags.PICKAXES).add(MarvelItems.VIBRANIUM_PICKAXE.get());
            tag(ItemTags.SHOVELS).add(MarvelItems.VIBRANIUM_SHOVEL.get());
            copy(BlockTags.SLABS, ItemTags.SLABS);
            copy(BlockTags.STAIRS, ItemTags.STAIRS);
            tag(ItemTags.SWORDS).add(MarvelItems.VIBRANIUM_SWORD.get(), MarvelItems.WINTER_SOLDIER_KNIFE.get(), MarvelItems.KATANAS.get());
            tag(ItemTags.TRIM_MATERIALS).add(MarvelItems.VIBRANIUM_INGOT.get(), MarvelItems.TITANIUM_INGOT.get(), MarvelItems.PALLADIUM_INGOT.get());
            tag(ItemTags.TRIMMABLE_ARMOR).add(MarvelItems.VIBRANIUM_HELMET.get(), MarvelItems.VIBRANIUM_CHESTPLATE.get(), MarvelItems.VIBRANIUM_LEGGINGS.get(), MarvelItems.VIBRANIUM_BOOTS.get());
            copy(BlockTags.WALLS, ItemTags.WALLS);

            copy(MarvelBlocks.Tags.VIBRANIUM_ORES, MarvelItems.Tags.VIBRANIUM_ORES);
            copy(MarvelBlocks.Tags.TITANIUM_ORES, MarvelItems.Tags.TITANIUM_ORES);
            copy(MarvelBlocks.Tags.PALLADIUM_ORES, MarvelItems.Tags.PALLADIUM_ORES);
            tag(MarvelItems.Tags.SUIT_VARIANTS).add(MarvelItems.CAPTAIN_AMERICA_STEALTH_HELMET.get(), MarvelItems.CAPTAIN_AMERICA_STEALTH_CHESTPLATE.get(), MarvelItems.CAPTAIN_AMERICA_STEALTH_LEGGINGS.get(), MarvelItems.CAPTAIN_AMERICA_STEALTH_BOOTS.get(), MarvelItems.CAPTAIN_CARTER_HELMET.get(), MarvelItems.CAPTAIN_CARTER_CHESTPLATE.get(), MarvelItems.CAPTAIN_CARTER_LEGGINGS.get(), MarvelItems.CAPTAIN_CARTER_BOOTS.get(), MarvelItems.RED_GUARDIAN_HELMET.get(), MarvelItems.RED_GUARDIAN_CHESTPLATE.get(), MarvelItems.RED_GUARDIAN_LEGGINGS.get(), MarvelItems.RED_GUARDIAN_BOOTS.get(), MarvelItems.KILLMONGER_HELMET.get(), MarvelItems.KILLMONGER_CHESTPLATE.get(), MarvelItems.KILLMONGER_LEGGINGS.get(), MarvelItems.KILLMONGER_BOOTS.get(), MarvelItems.BLACK_PANTHER_SHURI_HELMET.get(), MarvelItems.BLACK_PANTHER_SHURI_CHESTPLATE.get(), MarvelItems.BLACK_PANTHER_SHURI_LEGGINGS.get(), MarvelItems.BLACK_PANTHER_SHURI_BOOTS.get(), MarvelItems.WOLVERINE_SLEEVELESS_CHESTPLATE.get(), MarvelItems.WOLVERINE_BROWN_HELMET.get(), MarvelItems.WOLVERINE_BROWN_CHESTPLATE.get(), MarvelItems.WOLVERINE_BROWN_SLEEVELESS_CHESTPLATE.get(), MarvelItems.WOLVERINE_BROWN_LEGGINGS.get(), MarvelItems.WOLVERINE_BROWN_BOOTS.get(), MarvelItems.WOLVERINE_X_FORCE_HELMET.get(), MarvelItems.WOLVERINE_X_FORCE_CHESTPLATE.get(), MarvelItems.WOLVERINE_X_FORCE_SLEEVELESS_CHESTPLATE.get(), MarvelItems.WOLVERINE_X_FORCE_LEGGINGS.get(), MarvelItems.WOLVERINE_X_FORCE_BOOTS.get(), MarvelItems.CYCLOPS_ASTONISHING_HELMET.get(), MarvelItems.CYCLOPS_ASTONISHING_CHESTPLATE.get(), MarvelItems.CYCLOPS_ASTONISHING_LEGGINGS.get(), MarvelItems.CYCLOPS_ASTONISHING_BOOTS.get(), MarvelItems.SPIDER_MAN_MCU_HELMET.get(), MarvelItems.SPIDER_MAN_MCU_CHESTPLATE.get(), MarvelItems.SPIDER_MAN_MCU_LEGGINGS.get(), MarvelItems.SPIDER_MAN_MCU_BOOTS.get(), MarvelItems.MILES_MORALES_HELMET.get(), MarvelItems.MILES_MORALES_CHESTPLATE.get(), MarvelItems.MILES_MORALES_LEGGINGS.get(), MarvelItems.MILES_MORALES_BOOTS.get(), MarvelItems.SPIDER_GWEN_HELMET.get(), MarvelItems.SPIDER_GWEN_CHESTPLATE.get(), MarvelItems.SPIDER_GWEN_LEGGINGS.get(), MarvelItems.SPIDER_GWEN_BOOTS.get(), MarvelItems.SPIDER_MAN_INSOMNIAC_HELMET.get(), MarvelItems.SPIDER_MAN_INSOMNIAC_CHESTPLATE.get(), MarvelItems.SPIDER_MAN_INSOMNIAC_LEGGINGS.get(), MarvelItems.SPIDER_MAN_INSOMNIAC_BOOTS.get(), MarvelItems.IRON_PATRIOT_HELMET.get(), MarvelItems.IRON_PATRIOT_CHESTPLATE.get(), MarvelItems.IRON_PATRIOT_LEGGINGS.get(), MarvelItems.IRON_PATRIOT_BOOTS.get(), MarvelItems.QUICKSILVER_MCU_CHESTPLATE.get(), MarvelItems.QUICKSILVER_MCU_LEGGINGS.get(), MarvelItems.QUICKSILVER_MCU_BOOTS.get(), MarvelItems.DEADPOOL_X_FORCE_HELMET.get(), MarvelItems.DEADPOOL_X_FORCE_CHESTPLATE.get(), MarvelItems.DEADPOOL_X_FORCE_LEGGINGS.get(), MarvelItems.DEADPOOL_X_FORCE_BOOTS.get(), MarvelItems.CAPTAIN_MARVEL_838_HELMET.get(), MarvelItems.CAPTAIN_MARVEL_838_CHESTPLATE.get(), MarvelItems.CAPTAIN_MARVEL_838_LEGGINGS.get(), MarvelItems.CAPTAIN_MARVEL_838_BOOTS.get());
            tag(MarvelItems.Tags.RENDER_HAND).add(MarvelItems.ADAMANTIUM_CLAWS.get(), MarvelItems.OPTIC_BLAST.get(), MarvelItems.WEB_SHOOTER.get(), MarvelItems.SHRINK.get(), MarvelItems.GROW.get(), MarvelItems.STING.get(), MarvelItems.FLAMETHROWER.get(), MarvelItems.REPULSOR.get(), MarvelItems.UNIBEAM.get(), MarvelItems.SHOULDER_TURRET.get(), MarvelItems.SONIC_BOOM.get(), MarvelItems.PHOTON_BLAST.get());
            tag(MarvelItems.Tags.INVISIBLE_WHEN_OPEN).add(MarvelItems.CAPTAIN_CARTER_HELMET.get(), MarvelItems.KINETIC_BLACK_PANTHER_HELMET.get(), MarvelItems.KILLMONGER_HELMET.get(), MarvelItems.BLACK_PANTHER_SHURI_HELMET.get(), MarvelItems.ANT_MAN_UPGRADED_HELMET.get(), MarvelItems.WASP_HELMET.get(), MarvelItems.THOR_HELMET.get(), MarvelItems.CAPTAIN_MARVEL_HELMET.get(), MarvelItems.CAPTAIN_MARVEL_838_HELMET.get());
            tag(MarvelItems.Tags.HAS_CAPE).add(MarvelItems.THOR_CHESTPLATE.get());
            tag(MarvelItems.Tags.HIDES_OUTER_LAYER).add(MarvelItems.CAPTAIN_AMERICA_HELMET.get(), MarvelItems.CAPTAIN_AMERICA_CHESTPLATE.get(), MarvelItems.CAPTAIN_AMERICA_LEGGINGS.get(), MarvelItems.CAPTAIN_AMERICA_BOOTS.get(), MarvelItems.CAPTAIN_AMERICA_STEALTH_HELMET.get(), MarvelItems.CAPTAIN_AMERICA_STEALTH_CHESTPLATE.get(), MarvelItems.CAPTAIN_AMERICA_STEALTH_LEGGINGS.get(), MarvelItems.CAPTAIN_AMERICA_STEALTH_BOOTS.get(), MarvelItems.CAPTAIN_CARTER_HELMET.get(), MarvelItems.CAPTAIN_CARTER_CHESTPLATE.get(), MarvelItems.CAPTAIN_CARTER_LEGGINGS.get(), MarvelItems.CAPTAIN_CARTER_BOOTS.get(), MarvelItems.RED_GUARDIAN_HELMET.get(), MarvelItems.RED_GUARDIAN_CHESTPLATE.get(), MarvelItems.RED_GUARDIAN_LEGGINGS.get(), MarvelItems.RED_GUARDIAN_BOOTS.get(), MarvelItems.BLACK_PANTHER_HELMET.get(), MarvelItems.BLACK_PANTHER_CHESTPLATE.get(), MarvelItems.BLACK_PANTHER_LEGGINGS.get(), MarvelItems.BLACK_PANTHER_BOOTS.get(), MarvelItems.KINETIC_BLACK_PANTHER_HELMET.get(), MarvelItems.KINETIC_BLACK_PANTHER_CHESTPLATE.get(), MarvelItems.KINETIC_BLACK_PANTHER_LEGGINGS.get(), MarvelItems.KINETIC_BLACK_PANTHER_BOOTS.get(), MarvelItems.KILLMONGER_HELMET.get(), MarvelItems.KILLMONGER_CHESTPLATE.get(), MarvelItems.KILLMONGER_LEGGINGS.get(), MarvelItems.KILLMONGER_BOOTS.get(), MarvelItems.BLACK_PANTHER_SHURI_HELMET.get(), MarvelItems.BLACK_PANTHER_SHURI_CHESTPLATE.get(), MarvelItems.BLACK_PANTHER_SHURI_LEGGINGS.get(), MarvelItems.BLACK_PANTHER_SHURI_BOOTS.get(), MarvelItems.WOLVERINE_HELMET.get(), MarvelItems.WOLVERINE_CHESTPLATE.get(), MarvelItems.WOLVERINE_SLEEVELESS_CHESTPLATE.get(), MarvelItems.WOLVERINE_LEGGINGS.get(), MarvelItems.WOLVERINE_BOOTS.get(), MarvelItems.WOLVERINE_BROWN_HELMET.get(), MarvelItems.WOLVERINE_BROWN_CHESTPLATE.get(), MarvelItems.WOLVERINE_BROWN_SLEEVELESS_CHESTPLATE.get(), MarvelItems.WOLVERINE_BROWN_LEGGINGS.get(), MarvelItems.WOLVERINE_BROWN_BOOTS.get(), MarvelItems.WOLVERINE_X_FORCE_HELMET.get(), MarvelItems.WOLVERINE_X_FORCE_CHESTPLATE.get(), MarvelItems.WOLVERINE_X_FORCE_SLEEVELESS_CHESTPLATE.get(), MarvelItems.WOLVERINE_X_FORCE_LEGGINGS.get(), MarvelItems.WOLVERINE_X_FORCE_BOOTS.get(), MarvelItems.CYCLOPS_CHESTPLATE.get(), MarvelItems.CYCLOPS_LEGGINGS.get(), MarvelItems.CYCLOPS_BOOTS.get(), MarvelItems.CYCLOPS_ASTONISHING_HELMET.get(), MarvelItems.CYCLOPS_ASTONISHING_CHESTPLATE.get(), MarvelItems.CYCLOPS_ASTONISHING_LEGGINGS.get(), MarvelItems.CYCLOPS_ASTONISHING_BOOTS.get(), MarvelItems.SPIDER_MAN_HELMET.get(), MarvelItems.SPIDER_MAN_CHESTPLATE.get(), MarvelItems.SPIDER_MAN_LEGGINGS.get(), MarvelItems.SPIDER_MAN_BOOTS.get(), MarvelItems.SPIDER_MAN_MCU_HELMET.get(), MarvelItems.SPIDER_MAN_MCU_CHESTPLATE.get(), MarvelItems.SPIDER_MAN_MCU_LEGGINGS.get(), MarvelItems.SPIDER_MAN_MCU_BOOTS.get(), MarvelItems.MILES_MORALES_HELMET.get(), MarvelItems.MILES_MORALES_CHESTPLATE.get(), MarvelItems.MILES_MORALES_LEGGINGS.get(), MarvelItems.MILES_MORALES_BOOTS.get(), MarvelItems.SPIDER_GWEN_HELMET.get(), MarvelItems.SPIDER_GWEN_CHESTPLATE.get(), MarvelItems.SPIDER_GWEN_LEGGINGS.get(), MarvelItems.SPIDER_GWEN_BOOTS.get(), MarvelItems.SPIDER_MAN_INSOMNIAC_HELMET.get(), MarvelItems.SPIDER_MAN_INSOMNIAC_CHESTPLATE.get(), MarvelItems.SPIDER_MAN_INSOMNIAC_LEGGINGS.get(), MarvelItems.SPIDER_MAN_INSOMNIAC_BOOTS.get(), MarvelItems.ANT_MAN_HELMET.get(), MarvelItems.ANT_MAN_CHESTPLATE.get(), MarvelItems.ANT_MAN_LEGGINGS.get(), MarvelItems.ANT_MAN_BOOTS.get(), MarvelItems.ANT_MAN_UPGRADED_HELMET.get(), MarvelItems.ANT_MAN_UPGRADED_CHESTPLATE.get(), MarvelItems.ANT_MAN_UPGRADED_LEGGINGS.get(), MarvelItems.ANT_MAN_UPGRADED_BOOTS.get(), MarvelItems.WASP_HELMET.get(), MarvelItems.WASP_CHESTPLATE.get(), MarvelItems.WASP_LEGGINGS.get(), MarvelItems.WASP_BOOTS.get(), MarvelItems.THOR_HELMET.get(), MarvelItems.THOR_CHESTPLATE.get(), MarvelItems.THOR_LEGGINGS.get(), MarvelItems.THOR_BOOTS.get(), MarvelItems.IRON_MAN_MARK_1_HELMET.get(), MarvelItems.IRON_MAN_MARK_1_CHESTPLATE.get(), MarvelItems.IRON_MAN_MARK_1_LEGGINGS.get(), MarvelItems.IRON_MAN_MARK_1_BOOTS.get(), MarvelItems.IRON_MAN_MARK_2_HELMET.get(), MarvelItems.IRON_MAN_MARK_2_CHESTPLATE.get(), MarvelItems.IRON_MAN_MARK_2_LEGGINGS.get(), MarvelItems.IRON_MAN_MARK_2_BOOTS.get(), MarvelItems.IRON_MAN_MARK_3_HELMET.get(), MarvelItems.IRON_MAN_MARK_3_CHESTPLATE.get(), MarvelItems.IRON_MAN_MARK_3_LEGGINGS.get(), MarvelItems.IRON_MAN_MARK_3_BOOTS.get(), MarvelItems.IRON_MAN_MARK_5_HELMET.get(), MarvelItems.IRON_MAN_MARK_5_CHESTPLATE.get(), MarvelItems.IRON_MAN_MARK_5_LEGGINGS.get(), MarvelItems.IRON_MAN_MARK_5_BOOTS.get(), MarvelItems.IRON_MAN_MARK_6_HELMET.get(), MarvelItems.IRON_MAN_MARK_6_CHESTPLATE.get(), MarvelItems.IRON_MAN_MARK_6_LEGGINGS.get(), MarvelItems.IRON_MAN_MARK_6_BOOTS.get(), MarvelItems.IRON_MAN_MARK_7_HELMET.get(), MarvelItems.IRON_MAN_MARK_7_CHESTPLATE.get(), MarvelItems.IRON_MAN_MARK_7_LEGGINGS.get(), MarvelItems.IRON_MAN_MARK_7_BOOTS.get(), MarvelItems.IRON_MAN_MARK_11_HELMET.get(), MarvelItems.IRON_MAN_MARK_11_CHESTPLATE.get(), MarvelItems.IRON_MAN_MARK_11_LEGGINGS.get(), MarvelItems.IRON_MAN_MARK_11_BOOTS.get(), MarvelItems.IRON_MAN_MARK_15_HELMET.get(), MarvelItems.IRON_MAN_MARK_15_CHESTPLATE.get(), MarvelItems.IRON_MAN_MARK_15_LEGGINGS.get(), MarvelItems.IRON_MAN_MARK_15_BOOTS.get(), MarvelItems.IRON_MAN_MARK_17_HELMET.get(), MarvelItems.IRON_MAN_MARK_17_CHESTPLATE.get(), MarvelItems.IRON_MAN_MARK_17_LEGGINGS.get(), MarvelItems.IRON_MAN_MARK_17_BOOTS.get(), MarvelItems.IRON_MAN_MARK_19_HELMET.get(), MarvelItems.IRON_MAN_MARK_19_CHESTPLATE.get(), MarvelItems.IRON_MAN_MARK_19_LEGGINGS.get(), MarvelItems.IRON_MAN_MARK_19_BOOTS.get(), MarvelItems.IRON_MAN_MARK_20_HELMET.get(), MarvelItems.IRON_MAN_MARK_20_CHESTPLATE.get(), MarvelItems.IRON_MAN_MARK_20_LEGGINGS.get(), MarvelItems.IRON_MAN_MARK_20_BOOTS.get(), MarvelItems.IRON_MAN_MARK_21_HELMET.get(), MarvelItems.IRON_MAN_MARK_21_CHESTPLATE.get(), MarvelItems.IRON_MAN_MARK_21_LEGGINGS.get(), MarvelItems.IRON_MAN_MARK_21_BOOTS.get(), MarvelItems.IRON_MAN_MARK_22_HELMET.get(), MarvelItems.IRON_MAN_MARK_22_CHESTPLATE.get(), MarvelItems.IRON_MAN_MARK_22_LEGGINGS.get(), MarvelItems.IRON_MAN_MARK_22_BOOTS.get(), MarvelItems.WAR_MACHINE_MARK_1_HELMET.get(), MarvelItems.WAR_MACHINE_MARK_1_CHESTPLATE.get(), MarvelItems.WAR_MACHINE_MARK_1_LEGGINGS.get(), MarvelItems.WAR_MACHINE_MARK_1_BOOTS.get(), MarvelItems.WAR_MACHINE_MARK_2_HELMET.get(), MarvelItems.WAR_MACHINE_MARK_2_CHESTPLATE.get(), MarvelItems.WAR_MACHINE_MARK_2_LEGGINGS.get(), MarvelItems.WAR_MACHINE_MARK_2_BOOTS.get(), MarvelItems.IRON_PATRIOT_HELMET.get(), MarvelItems.IRON_PATRIOT_CHESTPLATE.get(), MarvelItems.IRON_PATRIOT_LEGGINGS.get(), MarvelItems.IRON_PATRIOT_BOOTS.get(), MarvelItems.QUICKSILVER_CHESTPLATE.get(), MarvelItems.QUICKSILVER_LEGGINGS.get(), MarvelItems.QUICKSILVER_BOOTS.get(), MarvelItems.QUICKSILVER_MCU_CHESTPLATE.get(), MarvelItems.QUICKSILVER_MCU_LEGGINGS.get(), MarvelItems.QUICKSILVER_MCU_BOOTS.get(), MarvelItems.DEADPOOL_HELMET.get(), MarvelItems.DEADPOOL_CHESTPLATE.get(), MarvelItems.DEADPOOL_LEGGINGS.get(), MarvelItems.DEADPOOL_BOOTS.get(), MarvelItems.DEADPOOL_X_FORCE_HELMET.get(), MarvelItems.DEADPOOL_X_FORCE_CHESTPLATE.get(), MarvelItems.DEADPOOL_X_FORCE_LEGGINGS.get(), MarvelItems.DEADPOOL_X_FORCE_BOOTS.get(), MarvelItems.CAPTAIN_MARVEL_HELMET.get(), MarvelItems.CAPTAIN_MARVEL_CHESTPLATE.get(), MarvelItems.CAPTAIN_MARVEL_LEGGINGS.get(), MarvelItems.CAPTAIN_MARVEL_BOOTS.get(), MarvelItems.CAPTAIN_MARVEL_838_CHESTPLATE.get(), MarvelItems.CAPTAIN_MARVEL_838_LEGGINGS.get(), MarvelItems.CAPTAIN_MARVEL_838_BOOTS.get(), MarvelItems.WINTER_SOLDIER_CHESTPLATE.get(), MarvelItems.WINTER_SOLDIER_LEGGINGS.get(), MarvelItems.WINTER_SOLDIER_BOOTS.get());
            tag(MarvelItems.Tags.CAPTAIN_AMERICA_HELMET).add(MarvelItems.CAPTAIN_AMERICA_HELMET.get(), MarvelItems.CAPTAIN_AMERICA_STEALTH_HELMET.get(), MarvelItems.CAPTAIN_CARTER_HELMET.get(), MarvelItems.RED_GUARDIAN_HELMET.get());
            tag(MarvelItems.Tags.CAPTAIN_AMERICA_CHESTPLATE).add(MarvelItems.CAPTAIN_AMERICA_CHESTPLATE.get(), MarvelItems.CAPTAIN_AMERICA_STEALTH_CHESTPLATE.get(), MarvelItems.CAPTAIN_CARTER_CHESTPLATE.get(), MarvelItems.RED_GUARDIAN_CHESTPLATE.get());
            tag(MarvelItems.Tags.CAPTAIN_AMERICA_LEGGINGS).add(MarvelItems.CAPTAIN_AMERICA_LEGGINGS.get(), MarvelItems.CAPTAIN_AMERICA_STEALTH_LEGGINGS.get(), MarvelItems.CAPTAIN_CARTER_LEGGINGS.get(), MarvelItems.RED_GUARDIAN_LEGGINGS.get());
            tag(MarvelItems.Tags.CAPTAIN_AMERICA_BOOTS).add(MarvelItems.CAPTAIN_AMERICA_BOOTS.get(), MarvelItems.CAPTAIN_AMERICA_STEALTH_BOOTS.get(), MarvelItems.CAPTAIN_CARTER_BOOTS.get(), MarvelItems.RED_GUARDIAN_BOOTS.get());
            tag(MarvelItems.Tags.BLACK_PANTHER_HELMET).add(MarvelItems.BLACK_PANTHER_HELMET.get());
            tag(MarvelItems.Tags.BLACK_PANTHER_CHESTPLATE).add(MarvelItems.BLACK_PANTHER_CHESTPLATE.get());
            tag(MarvelItems.Tags.BLACK_PANTHER_LEGGINGS).add(MarvelItems.BLACK_PANTHER_LEGGINGS.get());
            tag(MarvelItems.Tags.BLACK_PANTHER_BOOTS).add(MarvelItems.BLACK_PANTHER_BOOTS.get());
            tag(MarvelItems.Tags.KINETIC_BLACK_PANTHER_HELMET).add(MarvelItems.KINETIC_BLACK_PANTHER_HELMET.get(), MarvelItems.KILLMONGER_HELMET.get(), MarvelItems.BLACK_PANTHER_SHURI_HELMET.get());
            tag(MarvelItems.Tags.KINETIC_BLACK_PANTHER_CHESTPLATE).add(MarvelItems.KINETIC_BLACK_PANTHER_CHESTPLATE.get(), MarvelItems.KILLMONGER_CHESTPLATE.get(), MarvelItems.BLACK_PANTHER_SHURI_CHESTPLATE.get());
            tag(MarvelItems.Tags.KINETIC_BLACK_PANTHER_LEGGINGS).add(MarvelItems.KINETIC_BLACK_PANTHER_LEGGINGS.get(), MarvelItems.KILLMONGER_LEGGINGS.get(), MarvelItems.BLACK_PANTHER_SHURI_LEGGINGS.get());
            tag(MarvelItems.Tags.KINETIC_BLACK_PANTHER_BOOTS).add(MarvelItems.KINETIC_BLACK_PANTHER_BOOTS.get(), MarvelItems.KILLMONGER_BOOTS.get(), MarvelItems.BLACK_PANTHER_SHURI_BOOTS.get());
            tag(MarvelItems.Tags.WOLVERINE_HELMET).add(MarvelItems.WOLVERINE_HELMET.get(), MarvelItems.WOLVERINE_BROWN_HELMET.get(), MarvelItems.WOLVERINE_X_FORCE_HELMET.get());
            tag(MarvelItems.Tags.WOLVERINE_CHESTPLATE).add(MarvelItems.WOLVERINE_CHESTPLATE.get(), MarvelItems.WOLVERINE_SLEEVELESS_CHESTPLATE.get(), MarvelItems.WOLVERINE_BROWN_CHESTPLATE.get(), MarvelItems.WOLVERINE_BROWN_SLEEVELESS_CHESTPLATE.get(), MarvelItems.WOLVERINE_X_FORCE_CHESTPLATE.get(), MarvelItems.WOLVERINE_X_FORCE_SLEEVELESS_CHESTPLATE.get());
            tag(MarvelItems.Tags.WOLVERINE_LEGGINGS).add(MarvelItems.WOLVERINE_LEGGINGS.get(), MarvelItems.WOLVERINE_BROWN_LEGGINGS.get(), MarvelItems.WOLVERINE_X_FORCE_LEGGINGS.get());
            tag(MarvelItems.Tags.WOLVERINE_BOOTS).add(MarvelItems.WOLVERINE_BOOTS.get(), MarvelItems.WOLVERINE_BROWN_BOOTS.get(), MarvelItems.WOLVERINE_X_FORCE_BOOTS.get());
            tag(MarvelItems.Tags.CYCLOPS_HELMET).add(MarvelItems.CYCLOPS_HELMET.get(), MarvelItems.CYCLOPS_ASTONISHING_HELMET.get());
            tag(MarvelItems.Tags.CYCLOPS_CHESTPLATE).add(MarvelItems.CYCLOPS_CHESTPLATE.get(), MarvelItems.CYCLOPS_ASTONISHING_CHESTPLATE.get());
            tag(MarvelItems.Tags.CYCLOPS_LEGGINGS).add(MarvelItems.CYCLOPS_LEGGINGS.get(), MarvelItems.CYCLOPS_ASTONISHING_LEGGINGS.get());
            tag(MarvelItems.Tags.CYCLOPS_BOOTS).add(MarvelItems.CYCLOPS_BOOTS.get(), MarvelItems.CYCLOPS_ASTONISHING_BOOTS.get());
            tag(MarvelItems.Tags.SPIDER_MAN_HELMET).add(MarvelItems.SPIDER_MAN_HELMET.get(), MarvelItems.SPIDER_MAN_MCU_HELMET.get(), MarvelItems.MILES_MORALES_HELMET.get(), MarvelItems.SPIDER_GWEN_HELMET.get(), MarvelItems.SPIDER_MAN_INSOMNIAC_HELMET.get());
            tag(MarvelItems.Tags.SPIDER_MAN_CHESTPLATE).add(MarvelItems.SPIDER_MAN_CHESTPLATE.get(), MarvelItems.SPIDER_MAN_MCU_CHESTPLATE.get(), MarvelItems.MILES_MORALES_CHESTPLATE.get(), MarvelItems.SPIDER_GWEN_CHESTPLATE.get(), MarvelItems.SPIDER_MAN_INSOMNIAC_CHESTPLATE.get());
            tag(MarvelItems.Tags.SPIDER_MAN_LEGGINGS).add(MarvelItems.SPIDER_MAN_LEGGINGS.get(), MarvelItems.SPIDER_MAN_MCU_LEGGINGS.get(), MarvelItems.MILES_MORALES_LEGGINGS.get(), MarvelItems.SPIDER_GWEN_LEGGINGS.get(), MarvelItems.SPIDER_MAN_INSOMNIAC_LEGGINGS.get());
            tag(MarvelItems.Tags.SPIDER_MAN_BOOTS).add(MarvelItems.SPIDER_MAN_BOOTS.get(), MarvelItems.SPIDER_MAN_MCU_BOOTS.get(), MarvelItems.MILES_MORALES_BOOTS.get(), MarvelItems.SPIDER_GWEN_BOOTS.get(), MarvelItems.SPIDER_MAN_INSOMNIAC_BOOTS.get());
            tag(MarvelItems.Tags.ANT_MAN_HELMET).add(MarvelItems.ANT_MAN_HELMET.get());
            tag(MarvelItems.Tags.ANT_MAN_CHESTPLATE).add(MarvelItems.ANT_MAN_CHESTPLATE.get());
            tag(MarvelItems.Tags.ANT_MAN_LEGGINGS).add(MarvelItems.ANT_MAN_LEGGINGS.get());
            tag(MarvelItems.Tags.ANT_MAN_BOOTS).add(MarvelItems.ANT_MAN_BOOTS.get());
            tag(MarvelItems.Tags.ANT_MAN_UPGRADED_HELMET).add(MarvelItems.ANT_MAN_UPGRADED_HELMET.get());
            tag(MarvelItems.Tags.ANT_MAN_UPGRADED_CHESTPLATE).add(MarvelItems.ANT_MAN_UPGRADED_CHESTPLATE.get());
            tag(MarvelItems.Tags.ANT_MAN_UPGRADED_LEGGINGS).add(MarvelItems.ANT_MAN_UPGRADED_LEGGINGS.get());
            tag(MarvelItems.Tags.ANT_MAN_UPGRADED_BOOTS).add(MarvelItems.ANT_MAN_UPGRADED_BOOTS.get());
            tag(MarvelItems.Tags.WASP_HELMET).add(MarvelItems.WASP_HELMET.get());
            tag(MarvelItems.Tags.WASP_CHESTPLATE).add(MarvelItems.WASP_CHESTPLATE.get());
            tag(MarvelItems.Tags.WASP_LEGGINGS).add(MarvelItems.WASP_LEGGINGS.get());
            tag(MarvelItems.Tags.WASP_BOOTS).add(MarvelItems.WASP_BOOTS.get());
            tag(MarvelItems.Tags.THOR_HELMET).add(MarvelItems.THOR_HELMET.get());
            tag(MarvelItems.Tags.THOR_CHESTPLATE).add(MarvelItems.THOR_CHESTPLATE.get());
            tag(MarvelItems.Tags.THOR_LEGGINGS).add(MarvelItems.THOR_LEGGINGS.get());
            tag(MarvelItems.Tags.THOR_BOOTS).add(MarvelItems.THOR_BOOTS.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_1_HELMET).add(MarvelItems.IRON_MAN_MARK_1_HELMET.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_1_CHESTPLATE).add(MarvelItems.IRON_MAN_MARK_1_CHESTPLATE.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_1_LEGGINGS).add(MarvelItems.IRON_MAN_MARK_1_LEGGINGS.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_1_BOOTS).add(MarvelItems.IRON_MAN_MARK_1_BOOTS.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_2_HELMET).add(MarvelItems.IRON_MAN_MARK_2_HELMET.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_2_CHESTPLATE).add(MarvelItems.IRON_MAN_MARK_2_CHESTPLATE.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_2_LEGGINGS).add(MarvelItems.IRON_MAN_MARK_2_LEGGINGS.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_2_BOOTS).add(MarvelItems.IRON_MAN_MARK_2_BOOTS.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_3_HELMET).add(MarvelItems.IRON_MAN_MARK_3_HELMET.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_3_CHESTPLATE).add(MarvelItems.IRON_MAN_MARK_3_CHESTPLATE.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_3_LEGGINGS).add(MarvelItems.IRON_MAN_MARK_3_LEGGINGS.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_3_BOOTS).add(MarvelItems.IRON_MAN_MARK_3_BOOTS.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_5_HELMET).add(MarvelItems.IRON_MAN_MARK_5_HELMET.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_5_CHESTPLATE).add(MarvelItems.IRON_MAN_MARK_5_CHESTPLATE.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_5_LEGGINGS).add(MarvelItems.IRON_MAN_MARK_5_LEGGINGS.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_5_BOOTS).add(MarvelItems.IRON_MAN_MARK_5_BOOTS.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_6_HELMET).add(MarvelItems.IRON_MAN_MARK_6_HELMET.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_6_CHESTPLATE).add(MarvelItems.IRON_MAN_MARK_6_CHESTPLATE.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_6_LEGGINGS).add(MarvelItems.IRON_MAN_MARK_6_LEGGINGS.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_6_BOOTS).add(MarvelItems.IRON_MAN_MARK_6_BOOTS.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_7_HELMET).add(MarvelItems.IRON_MAN_MARK_7_HELMET.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_7_CHESTPLATE).add(MarvelItems.IRON_MAN_MARK_7_CHESTPLATE.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_7_LEGGINGS).add(MarvelItems.IRON_MAN_MARK_7_LEGGINGS.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_7_BOOTS).add(MarvelItems.IRON_MAN_MARK_7_BOOTS.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_11_HELMET).add(MarvelItems.IRON_MAN_MARK_11_HELMET.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_11_CHESTPLATE).add(MarvelItems.IRON_MAN_MARK_11_CHESTPLATE.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_11_LEGGINGS).add(MarvelItems.IRON_MAN_MARK_11_LEGGINGS.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_11_BOOTS).add(MarvelItems.IRON_MAN_MARK_11_BOOTS.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_15_HELMET).add(MarvelItems.IRON_MAN_MARK_15_HELMET.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_15_CHESTPLATE).add(MarvelItems.IRON_MAN_MARK_15_CHESTPLATE.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_15_LEGGINGS).add(MarvelItems.IRON_MAN_MARK_15_LEGGINGS.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_15_BOOTS).add(MarvelItems.IRON_MAN_MARK_15_BOOTS.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_17_HELMET).add(MarvelItems.IRON_MAN_MARK_17_HELMET.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_17_CHESTPLATE).add(MarvelItems.IRON_MAN_MARK_17_CHESTPLATE.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_17_LEGGINGS).add(MarvelItems.IRON_MAN_MARK_17_LEGGINGS.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_17_BOOTS).add(MarvelItems.IRON_MAN_MARK_17_BOOTS.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_19_HELMET).add(MarvelItems.IRON_MAN_MARK_19_HELMET.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_19_CHESTPLATE).add(MarvelItems.IRON_MAN_MARK_19_CHESTPLATE.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_19_LEGGINGS).add(MarvelItems.IRON_MAN_MARK_19_LEGGINGS.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_19_BOOTS).add(MarvelItems.IRON_MAN_MARK_19_BOOTS.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_20_HELMET).add(MarvelItems.IRON_MAN_MARK_20_HELMET.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_20_CHESTPLATE).add(MarvelItems.IRON_MAN_MARK_20_CHESTPLATE.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_20_LEGGINGS).add(MarvelItems.IRON_MAN_MARK_20_LEGGINGS.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_20_BOOTS).add(MarvelItems.IRON_MAN_MARK_20_BOOTS.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_21_HELMET).add(MarvelItems.IRON_MAN_MARK_21_HELMET.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_21_CHESTPLATE).add(MarvelItems.IRON_MAN_MARK_21_CHESTPLATE.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_21_LEGGINGS).add(MarvelItems.IRON_MAN_MARK_21_LEGGINGS.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_21_BOOTS).add(MarvelItems.IRON_MAN_MARK_21_BOOTS.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_22_HELMET).add(MarvelItems.IRON_MAN_MARK_22_HELMET.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_22_CHESTPLATE).add(MarvelItems.IRON_MAN_MARK_22_CHESTPLATE.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_22_LEGGINGS).add(MarvelItems.IRON_MAN_MARK_22_LEGGINGS.get());
            tag(MarvelItems.Tags.IRON_MAN_MARK_22_BOOTS).add(MarvelItems.IRON_MAN_MARK_22_BOOTS.get());
            tag(MarvelItems.Tags.WAR_MACHINE_MARK_1_HELMET).add(MarvelItems.WAR_MACHINE_MARK_1_HELMET.get());
            tag(MarvelItems.Tags.WAR_MACHINE_MARK_1_CHESTPLATE).add(MarvelItems.WAR_MACHINE_MARK_1_CHESTPLATE.get());
            tag(MarvelItems.Tags.WAR_MACHINE_MARK_1_LEGGINGS).add(MarvelItems.WAR_MACHINE_MARK_1_LEGGINGS.get());
            tag(MarvelItems.Tags.WAR_MACHINE_MARK_1_BOOTS).add(MarvelItems.WAR_MACHINE_MARK_1_BOOTS.get());
            tag(MarvelItems.Tags.WAR_MACHINE_MARK_2_HELMET).add(MarvelItems.WAR_MACHINE_MARK_2_HELMET.get(), MarvelItems.IRON_PATRIOT_HELMET.get());
            tag(MarvelItems.Tags.WAR_MACHINE_MARK_2_CHESTPLATE).add(MarvelItems.WAR_MACHINE_MARK_2_CHESTPLATE.get(), MarvelItems.IRON_PATRIOT_CHESTPLATE.get());
            tag(MarvelItems.Tags.WAR_MACHINE_MARK_2_LEGGINGS).add(MarvelItems.WAR_MACHINE_MARK_2_LEGGINGS.get(), MarvelItems.IRON_PATRIOT_LEGGINGS.get());
            tag(MarvelItems.Tags.WAR_MACHINE_MARK_2_BOOTS).add(MarvelItems.WAR_MACHINE_MARK_2_BOOTS.get(), MarvelItems.IRON_PATRIOT_BOOTS.get());
            tag(MarvelItems.Tags.IRON_MAN_HELMET).addTag(MarvelItems.Tags.IRON_MAN_MARK_2_HELMET).addTag(MarvelItems.Tags.IRON_MAN_MARK_3_HELMET).addTag(MarvelItems.Tags.IRON_MAN_MARK_5_HELMET).addTag(MarvelItems.Tags.IRON_MAN_MARK_6_HELMET).addTag(MarvelItems.Tags.IRON_MAN_MARK_7_HELMET).addTag(MarvelItems.Tags.IRON_MAN_MARK_11_HELMET).addTag(MarvelItems.Tags.IRON_MAN_MARK_15_HELMET).addTag(MarvelItems.Tags.IRON_MAN_MARK_17_HELMET).addTag(MarvelItems.Tags.IRON_MAN_MARK_19_HELMET).addTag(MarvelItems.Tags.IRON_MAN_MARK_20_HELMET).addTag(MarvelItems.Tags.IRON_MAN_MARK_21_HELMET).addTag(MarvelItems.Tags.IRON_MAN_MARK_22_HELMET).addTag(MarvelItems.Tags.WAR_MACHINE_MARK_1_HELMET).addTag(MarvelItems.Tags.WAR_MACHINE_MARK_2_HELMET);
            tag(MarvelItems.Tags.IRON_MAN_CHESTPLATE).addTag(MarvelItems.Tags.IRON_MAN_MARK_2_CHESTPLATE).addTag(MarvelItems.Tags.IRON_MAN_MARK_3_CHESTPLATE).addTag(MarvelItems.Tags.IRON_MAN_MARK_5_CHESTPLATE).addTag(MarvelItems.Tags.IRON_MAN_MARK_6_CHESTPLATE).addTag(MarvelItems.Tags.IRON_MAN_MARK_7_CHESTPLATE).addTag(MarvelItems.Tags.IRON_MAN_MARK_11_CHESTPLATE).addTag(MarvelItems.Tags.IRON_MAN_MARK_15_CHESTPLATE).addTag(MarvelItems.Tags.IRON_MAN_MARK_17_CHESTPLATE).addTag(MarvelItems.Tags.IRON_MAN_MARK_19_CHESTPLATE).addTag(MarvelItems.Tags.IRON_MAN_MARK_20_CHESTPLATE).addTag(MarvelItems.Tags.IRON_MAN_MARK_21_CHESTPLATE).addTag(MarvelItems.Tags.IRON_MAN_MARK_22_CHESTPLATE).addTag(MarvelItems.Tags.WAR_MACHINE_MARK_1_CHESTPLATE).addTag(MarvelItems.Tags.WAR_MACHINE_MARK_2_CHESTPLATE);
            tag(MarvelItems.Tags.IRON_MAN_LEGGINGS).addTag(MarvelItems.Tags.IRON_MAN_MARK_2_LEGGINGS).addTag(MarvelItems.Tags.IRON_MAN_MARK_3_LEGGINGS).addTag(MarvelItems.Tags.IRON_MAN_MARK_5_LEGGINGS).addTag(MarvelItems.Tags.IRON_MAN_MARK_6_LEGGINGS).addTag(MarvelItems.Tags.IRON_MAN_MARK_7_LEGGINGS).addTag(MarvelItems.Tags.IRON_MAN_MARK_11_LEGGINGS).addTag(MarvelItems.Tags.IRON_MAN_MARK_15_LEGGINGS).addTag(MarvelItems.Tags.IRON_MAN_MARK_17_LEGGINGS).addTag(MarvelItems.Tags.IRON_MAN_MARK_19_LEGGINGS).addTag(MarvelItems.Tags.IRON_MAN_MARK_20_LEGGINGS).addTag(MarvelItems.Tags.IRON_MAN_MARK_21_LEGGINGS).addTag(MarvelItems.Tags.IRON_MAN_MARK_22_LEGGINGS).addTag(MarvelItems.Tags.WAR_MACHINE_MARK_1_LEGGINGS).addTag(MarvelItems.Tags.WAR_MACHINE_MARK_2_LEGGINGS);
            tag(MarvelItems.Tags.IRON_MAN_BOOTS).addTag(MarvelItems.Tags.IRON_MAN_MARK_2_BOOTS).addTag(MarvelItems.Tags.IRON_MAN_MARK_3_BOOTS).addTag(MarvelItems.Tags.IRON_MAN_MARK_5_BOOTS).addTag(MarvelItems.Tags.IRON_MAN_MARK_6_BOOTS).addTag(MarvelItems.Tags.IRON_MAN_MARK_7_BOOTS).addTag(MarvelItems.Tags.IRON_MAN_MARK_11_BOOTS).addTag(MarvelItems.Tags.IRON_MAN_MARK_15_BOOTS).addTag(MarvelItems.Tags.IRON_MAN_MARK_17_BOOTS).addTag(MarvelItems.Tags.IRON_MAN_MARK_19_BOOTS).addTag(MarvelItems.Tags.IRON_MAN_MARK_20_BOOTS).addTag(MarvelItems.Tags.IRON_MAN_MARK_21_BOOTS).addTag(MarvelItems.Tags.IRON_MAN_MARK_22_BOOTS).addTag(MarvelItems.Tags.WAR_MACHINE_MARK_1_BOOTS).addTag(MarvelItems.Tags.WAR_MACHINE_MARK_2_BOOTS);
            tag(MarvelItems.Tags.WAR_MACHINE_HELMET).addTag(MarvelItems.Tags.WAR_MACHINE_MARK_1_HELMET).addTag(MarvelItems.Tags.WAR_MACHINE_MARK_2_HELMET);
            tag(MarvelItems.Tags.WAR_MACHINE_CHESTPLATE).addTag(MarvelItems.Tags.WAR_MACHINE_MARK_1_CHESTPLATE).addTag(MarvelItems.Tags.WAR_MACHINE_MARK_2_CHESTPLATE);
            tag(MarvelItems.Tags.WAR_MACHINE_LEGGINGS).addTag(MarvelItems.Tags.WAR_MACHINE_MARK_1_LEGGINGS).addTag(MarvelItems.Tags.WAR_MACHINE_MARK_2_LEGGINGS);
            tag(MarvelItems.Tags.WAR_MACHINE_BOOTS).addTag(MarvelItems.Tags.WAR_MACHINE_MARK_1_BOOTS).addTag(MarvelItems.Tags.WAR_MACHINE_MARK_2_BOOTS);
            tag(MarvelItems.Tags.QUICKSILVER_CHESTPLATE).add(MarvelItems.QUICKSILVER_CHESTPLATE.get(), MarvelItems.QUICKSILVER_MCU_CHESTPLATE.get());
            tag(MarvelItems.Tags.QUICKSILVER_LEGGINGS).add(MarvelItems.QUICKSILVER_LEGGINGS.get(), MarvelItems.QUICKSILVER_MCU_LEGGINGS.get());
            tag(MarvelItems.Tags.QUICKSILVER_BOOTS).add(MarvelItems.QUICKSILVER_BOOTS.get(), MarvelItems.QUICKSILVER_MCU_BOOTS.get());
            tag(MarvelItems.Tags.DEADPOOL_HELMET).add(MarvelItems.DEADPOOL_HELMET.get(), MarvelItems.DEADPOOL_X_FORCE_HELMET.get());
            tag(MarvelItems.Tags.DEADPOOL_CHESTPLATE).add(MarvelItems.DEADPOOL_CHESTPLATE.get(), MarvelItems.DEADPOOL_X_FORCE_CHESTPLATE.get());
            tag(MarvelItems.Tags.DEADPOOL_LEGGINGS).add(MarvelItems.DEADPOOL_LEGGINGS.get(), MarvelItems.DEADPOOL_X_FORCE_LEGGINGS.get());
            tag(MarvelItems.Tags.DEADPOOL_BOOTS).add(MarvelItems.DEADPOOL_BOOTS.get(), MarvelItems.DEADPOOL_X_FORCE_BOOTS.get());
            tag(MarvelItems.Tags.CAPTAIN_MARVEL_HELMET).add(MarvelItems.CAPTAIN_MARVEL_HELMET.get(), MarvelItems.CAPTAIN_MARVEL_838_HELMET.get());
            tag(MarvelItems.Tags.CAPTAIN_MARVEL_CHESTPLATE).add(MarvelItems.CAPTAIN_MARVEL_CHESTPLATE.get(), MarvelItems.CAPTAIN_MARVEL_838_CHESTPLATE.get());
            tag(MarvelItems.Tags.CAPTAIN_MARVEL_LEGGINGS).add(MarvelItems.CAPTAIN_MARVEL_LEGGINGS.get(), MarvelItems.CAPTAIN_MARVEL_838_LEGGINGS.get());
            tag(MarvelItems.Tags.CAPTAIN_MARVEL_BOOTS).add(MarvelItems.CAPTAIN_MARVEL_BOOTS.get(), MarvelItems.CAPTAIN_MARVEL_838_BOOTS.get());
            tag(MarvelItems.Tags.WINTER_SOLDIER_CHESTPLATE).add(MarvelItems.WINTER_SOLDIER_CHESTPLATE.get());
            tag(MarvelItems.Tags.WINTER_SOLDIER_LEGGINGS).add(MarvelItems.WINTER_SOLDIER_LEGGINGS.get());
            tag(MarvelItems.Tags.WINTER_SOLDIER_BOOTS).add(MarvelItems.WINTER_SOLDIER_BOOTS.get());
            tag(MarvelItems.Tags.CAPTAIN_AMERICA_ARMOR).addTag(MarvelItems.Tags.CAPTAIN_AMERICA_HELMET).addTag(MarvelItems.Tags.CAPTAIN_AMERICA_CHESTPLATE).addTag(MarvelItems.Tags.CAPTAIN_AMERICA_LEGGINGS).addTag(MarvelItems.Tags.CAPTAIN_AMERICA_BOOTS);
            tag(MarvelItems.Tags.BLACK_PANTHER_ARMOR).addTag(MarvelItems.Tags.BLACK_PANTHER_HELMET).addTag(MarvelItems.Tags.BLACK_PANTHER_CHESTPLATE).addTag(MarvelItems.Tags.BLACK_PANTHER_LEGGINGS).addTag(MarvelItems.Tags.BLACK_PANTHER_BOOTS);
            tag(MarvelItems.Tags.KINETIC_BLACK_PANTHER_ARMOR).addTag(MarvelItems.Tags.KINETIC_BLACK_PANTHER_HELMET).addTag(MarvelItems.Tags.KINETIC_BLACK_PANTHER_CHESTPLATE).addTag(MarvelItems.Tags.KINETIC_BLACK_PANTHER_LEGGINGS).addTag(MarvelItems.Tags.KINETIC_BLACK_PANTHER_BOOTS);
            tag(MarvelItems.Tags.WOLVERINE_ARMOR).addTag(MarvelItems.Tags.WOLVERINE_HELMET).addTag(MarvelItems.Tags.WOLVERINE_CHESTPLATE).addTag(MarvelItems.Tags.WOLVERINE_LEGGINGS).addTag(MarvelItems.Tags.WOLVERINE_BOOTS);
            tag(MarvelItems.Tags.CYCLOPS_ARMOR).addTag(MarvelItems.Tags.CYCLOPS_HELMET).addTag(MarvelItems.Tags.CYCLOPS_CHESTPLATE).addTag(MarvelItems.Tags.CYCLOPS_LEGGINGS).addTag(MarvelItems.Tags.CYCLOPS_BOOTS);
            tag(MarvelItems.Tags.SPIDER_MAN_ARMOR).addTag(MarvelItems.Tags.SPIDER_MAN_HELMET).addTag(MarvelItems.Tags.SPIDER_MAN_CHESTPLATE).addTag(MarvelItems.Tags.SPIDER_MAN_LEGGINGS).addTag(MarvelItems.Tags.SPIDER_MAN_BOOTS);
            tag(MarvelItems.Tags.ANT_MAN_ARMOR).addTag(MarvelItems.Tags.ANT_MAN_HELMET).addTag(MarvelItems.Tags.ANT_MAN_CHESTPLATE).addTag(MarvelItems.Tags.ANT_MAN_LEGGINGS).addTag(MarvelItems.Tags.ANT_MAN_BOOTS);
            tag(MarvelItems.Tags.ANT_MAN_UPGRADED_ARMOR).addTag(MarvelItems.Tags.ANT_MAN_UPGRADED_HELMET).addTag(MarvelItems.Tags.ANT_MAN_UPGRADED_CHESTPLATE).addTag(MarvelItems.Tags.ANT_MAN_UPGRADED_LEGGINGS).addTag(MarvelItems.Tags.ANT_MAN_UPGRADED_BOOTS);
            tag(MarvelItems.Tags.WASP_ARMOR).addTag(MarvelItems.Tags.WASP_HELMET).addTag(MarvelItems.Tags.WASP_CHESTPLATE).addTag(MarvelItems.Tags.WASP_LEGGINGS).addTag(MarvelItems.Tags.WASP_BOOTS);
            tag(MarvelItems.Tags.THOR_ARMOR).addTag(MarvelItems.Tags.THOR_HELMET).addTag(MarvelItems.Tags.THOR_CHESTPLATE).addTag(MarvelItems.Tags.THOR_LEGGINGS).addTag(MarvelItems.Tags.THOR_BOOTS);
            tag(MarvelItems.Tags.IRON_MAN_MARK_1_ARMOR).addTag(MarvelItems.Tags.IRON_MAN_MARK_1_HELMET).addTag(MarvelItems.Tags.IRON_MAN_MARK_1_CHESTPLATE).addTag(MarvelItems.Tags.IRON_MAN_MARK_1_LEGGINGS).addTag(MarvelItems.Tags.IRON_MAN_MARK_1_BOOTS);
            tag(MarvelItems.Tags.IRON_MAN_MARK_2_ARMOR).addTag(MarvelItems.Tags.IRON_MAN_MARK_2_HELMET).addTag(MarvelItems.Tags.IRON_MAN_MARK_2_CHESTPLATE).addTag(MarvelItems.Tags.IRON_MAN_MARK_2_LEGGINGS).addTag(MarvelItems.Tags.IRON_MAN_MARK_2_BOOTS);
            tag(MarvelItems.Tags.IRON_MAN_MARK_3_ARMOR).addTag(MarvelItems.Tags.IRON_MAN_MARK_3_HELMET).addTag(MarvelItems.Tags.IRON_MAN_MARK_3_CHESTPLATE).addTag(MarvelItems.Tags.IRON_MAN_MARK_3_LEGGINGS).addTag(MarvelItems.Tags.IRON_MAN_MARK_3_BOOTS);
            tag(MarvelItems.Tags.IRON_MAN_MARK_5_ARMOR).addTag(MarvelItems.Tags.IRON_MAN_MARK_5_HELMET).addTag(MarvelItems.Tags.IRON_MAN_MARK_5_CHESTPLATE).addTag(MarvelItems.Tags.IRON_MAN_MARK_5_LEGGINGS).addTag(MarvelItems.Tags.IRON_MAN_MARK_5_BOOTS);
            tag(MarvelItems.Tags.IRON_MAN_MARK_6_ARMOR).addTag(MarvelItems.Tags.IRON_MAN_MARK_6_HELMET).addTag(MarvelItems.Tags.IRON_MAN_MARK_6_CHESTPLATE).addTag(MarvelItems.Tags.IRON_MAN_MARK_6_LEGGINGS).addTag(MarvelItems.Tags.IRON_MAN_MARK_6_BOOTS);
            tag(MarvelItems.Tags.IRON_MAN_MARK_7_ARMOR).addTag(MarvelItems.Tags.IRON_MAN_MARK_7_HELMET).addTag(MarvelItems.Tags.IRON_MAN_MARK_7_CHESTPLATE).addTag(MarvelItems.Tags.IRON_MAN_MARK_7_LEGGINGS).addTag(MarvelItems.Tags.IRON_MAN_MARK_7_BOOTS);
            tag(MarvelItems.Tags.IRON_MAN_MARK_11_ARMOR).addTag(MarvelItems.Tags.IRON_MAN_MARK_11_HELMET).addTag(MarvelItems.Tags.IRON_MAN_MARK_11_CHESTPLATE).addTag(MarvelItems.Tags.IRON_MAN_MARK_11_LEGGINGS).addTag(MarvelItems.Tags.IRON_MAN_MARK_11_BOOTS);
            tag(MarvelItems.Tags.IRON_MAN_MARK_15_ARMOR).addTag(MarvelItems.Tags.IRON_MAN_MARK_15_HELMET).addTag(MarvelItems.Tags.IRON_MAN_MARK_15_CHESTPLATE).addTag(MarvelItems.Tags.IRON_MAN_MARK_15_LEGGINGS).addTag(MarvelItems.Tags.IRON_MAN_MARK_15_BOOTS);
            tag(MarvelItems.Tags.IRON_MAN_MARK_17_ARMOR).addTag(MarvelItems.Tags.IRON_MAN_MARK_17_HELMET).addTag(MarvelItems.Tags.IRON_MAN_MARK_17_CHESTPLATE).addTag(MarvelItems.Tags.IRON_MAN_MARK_17_LEGGINGS).addTag(MarvelItems.Tags.IRON_MAN_MARK_17_BOOTS);
            tag(MarvelItems.Tags.IRON_MAN_MARK_19_ARMOR).addTag(MarvelItems.Tags.IRON_MAN_MARK_19_HELMET).addTag(MarvelItems.Tags.IRON_MAN_MARK_19_CHESTPLATE).addTag(MarvelItems.Tags.IRON_MAN_MARK_19_LEGGINGS).addTag(MarvelItems.Tags.IRON_MAN_MARK_19_BOOTS);
            tag(MarvelItems.Tags.IRON_MAN_MARK_20_ARMOR).addTag(MarvelItems.Tags.IRON_MAN_MARK_20_HELMET).addTag(MarvelItems.Tags.IRON_MAN_MARK_20_CHESTPLATE).addTag(MarvelItems.Tags.IRON_MAN_MARK_20_LEGGINGS).addTag(MarvelItems.Tags.IRON_MAN_MARK_20_BOOTS);
            tag(MarvelItems.Tags.IRON_MAN_MARK_21_ARMOR).addTag(MarvelItems.Tags.IRON_MAN_MARK_21_HELMET).addTag(MarvelItems.Tags.IRON_MAN_MARK_21_CHESTPLATE).addTag(MarvelItems.Tags.IRON_MAN_MARK_21_LEGGINGS).addTag(MarvelItems.Tags.IRON_MAN_MARK_21_BOOTS);
            tag(MarvelItems.Tags.IRON_MAN_MARK_22_ARMOR).addTag(MarvelItems.Tags.IRON_MAN_MARK_22_HELMET).addTag(MarvelItems.Tags.IRON_MAN_MARK_22_CHESTPLATE).addTag(MarvelItems.Tags.IRON_MAN_MARK_22_LEGGINGS).addTag(MarvelItems.Tags.IRON_MAN_MARK_22_BOOTS);
            tag(MarvelItems.Tags.WAR_MACHINE_MARK_1_ARMOR).addTag(MarvelItems.Tags.WAR_MACHINE_MARK_1_HELMET).addTag(MarvelItems.Tags.WAR_MACHINE_MARK_1_CHESTPLATE).addTag(MarvelItems.Tags.WAR_MACHINE_MARK_1_LEGGINGS).addTag(MarvelItems.Tags.WAR_MACHINE_MARK_1_BOOTS);
            tag(MarvelItems.Tags.WAR_MACHINE_MARK_2_ARMOR).addTag(MarvelItems.Tags.WAR_MACHINE_MARK_2_HELMET).addTag(MarvelItems.Tags.WAR_MACHINE_MARK_2_CHESTPLATE).addTag(MarvelItems.Tags.WAR_MACHINE_MARK_2_LEGGINGS).addTag(MarvelItems.Tags.WAR_MACHINE_MARK_2_BOOTS);
            tag(MarvelItems.Tags.IRON_MAN_ARMOR).addTag(MarvelItems.Tags.IRON_MAN_HELMET).addTag(MarvelItems.Tags.IRON_MAN_CHESTPLATE).addTag(MarvelItems.Tags.IRON_MAN_LEGGINGS).addTag(MarvelItems.Tags.IRON_MAN_BOOTS);
            tag(MarvelItems.Tags.WAR_MACHINE_ARMOR).addTag(MarvelItems.Tags.WAR_MACHINE_HELMET).addTag(MarvelItems.Tags.WAR_MACHINE_CHESTPLATE).addTag(MarvelItems.Tags.WAR_MACHINE_LEGGINGS).addTag(MarvelItems.Tags.WAR_MACHINE_BOOTS);
            tag(MarvelItems.Tags.QUICKSILVER_ARMOR).addTag(MarvelItems.Tags.QUICKSILVER_CHESTPLATE).addTag(MarvelItems.Tags.QUICKSILVER_LEGGINGS).addTag(MarvelItems.Tags.QUICKSILVER_BOOTS);
            tag(MarvelItems.Tags.DEADPOOL_ARMOR).addTag(MarvelItems.Tags.DEADPOOL_HELMET).addTag(MarvelItems.Tags.DEADPOOL_CHESTPLATE).addTag(MarvelItems.Tags.DEADPOOL_LEGGINGS).addTag(MarvelItems.Tags.DEADPOOL_BOOTS);
            tag(MarvelItems.Tags.CAPTAIN_MARVEL_ARMOR).addTag(MarvelItems.Tags.CAPTAIN_MARVEL_HELMET).addTag(MarvelItems.Tags.CAPTAIN_MARVEL_CHESTPLATE).addTag(MarvelItems.Tags.CAPTAIN_MARVEL_LEGGINGS).addTag(MarvelItems.Tags.CAPTAIN_MARVEL_BOOTS);
            tag(MarvelItems.Tags.WINTER_SOLDIER_ARMOR).addTag(MarvelItems.Tags.WINTER_SOLDIER_CHESTPLATE).addTag(MarvelItems.Tags.WINTER_SOLDIER_LEGGINGS).addTag(MarvelItems.Tags.WINTER_SOLDIER_BOOTS);
            tag(MarvelItems.Tags.SOUND_DAMPENING_BOOTS).addTag(MarvelItems.Tags.KINETIC_BLACK_PANTHER_BOOTS);
            tag(MarvelItems.Tags.FLYING_ARMOR).addTag(MarvelItems.Tags.IRON_MAN_ARMOR).addTag(MarvelItems.Tags.WASP_ARMOR).addTag(MarvelItems.Tags.CAPTAIN_MARVEL_ARMOR);
        }
    }

    public static class EntityTypeProvider extends EntityTypeTagsProvider {
        public EntityTypeProvider(PackOutput p_256095_, CompletableFuture<HolderLookup.Provider> p_256572_, @Nullable ExistingFileHelper existingFileHelper) {
            super(p_256095_, p_256572_, MarvelSuperheroes.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider arg) {
            tag(EntityTypeTags.IMPACT_PROJECTILES).add(MarvelEntityTypes.VIBRANIUM_SHIELD.get());

            tag(MarvelEntityTypes.Tags.BLOCKED_BY_VIBRANIUM_SHIELD).addTag(EntityTypeTags.ARROWS).add(EntityType.SMALL_FIREBALL);
        }
    }

    public static class StructureProvider extends TagsProvider<Structure> {
        public StructureProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, Registries.STRUCTURE, completableFuture, MarvelSuperheroes.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider arg) {
            tag(MarvelStructures.Tags.HYDRA_BASE).add(MarvelStructures.HYDRA_BASE_CLASSIC, MarvelStructures.HYDRA_BASE_WINTER, MarvelStructures.HYDRA_BASE_MOUNTAIN);
            tag(MarvelStructures.Tags.HYDRA_OUTPOST).add(MarvelStructures.HYDRA_OUTPOST_CLASSIC, MarvelStructures.HYDRA_OUTPOST_WINTER, MarvelStructures.HYDRA_OUTPOST_MOUNTAIN);
            tag(MarvelStructures.Tags.ON_HYDRA_HEADQUARTERS_MAPS).add(MarvelStructures.HYDRA_BASE_MOUNTAIN);
        }
    }

    public static class BiomeProvider extends TagsProvider<Biome> {
        public BiomeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, Registries.BIOME, completableFuture, MarvelSuperheroes.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider arg) {
            tag(MarvelBiomes.Tags.HAS_HYDRA_BASE_CLASSIC).addTag(Tags.Biomes.IS_JUNGLE);
            tag(MarvelBiomes.Tags.HAS_HYDRA_BASE_WINTER).add(Biomes.SNOWY_PLAINS, Biomes.ICE_SPIKES, Biomes.SNOWY_TAIGA);
            tag(MarvelBiomes.Tags.HAS_HYDRA_BASE_MOUNTAIN).addTag(Tags.Biomes.IS_MOUNTAIN).add(Biomes.GROVE);
            tag(MarvelBiomes.Tags.HAS_HYDRA_OUTPOST_CLASSIC).add(Biomes.PLAINS, Biomes.SUNFLOWER_PLAINS, Biomes.DESERT, Biomes.SWAMP, Biomes.MANGROVE_SWAMP, Biomes.FOREST, Biomes.FLOWER_FOREST, Biomes.BIRCH_FOREST, Biomes.DARK_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST, Biomes.OLD_GROWTH_PINE_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA, Biomes.TAIGA, Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_FOREST, Biomes.WINDSWEPT_SAVANNA, Biomes.JUNGLE, Biomes.SPARSE_JUNGLE, Biomes.BAMBOO_JUNGLE, Biomes.BADLANDS, Biomes.ERODED_BADLANDS, Biomes.WOODED_BADLANDS);
            tag(MarvelBiomes.Tags.HAS_HYDRA_OUTPOST_WINTER).add(Biomes.SNOWY_PLAINS, Biomes.ICE_SPIKES, Biomes.SNOWY_TAIGA);
            tag(MarvelBiomes.Tags.HAS_HYDRA_OUTPOST_MOUNTAIN).addTag(Tags.Biomes.IS_MOUNTAIN).add(Biomes.GROVE);
            tag(MarvelBiomes.Tags.SPAWNS_CLASSIC_HYDRA_AGENTS).addTag(Tags.Biomes.IS_JUNGLE);
            tag(MarvelBiomes.Tags.SPAWNS_WINTER_HYDRA_AGENTS).add(Biomes.SNOWY_PLAINS, Biomes.ICE_SPIKES, Biomes.SNOWY_TAIGA, Biomes.SNOWY_BEACH, Biomes.FROZEN_RIVER, Biomes.COLD_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.FROZEN_OCEAN, Biomes.DEEP_FROZEN_OCEAN);
            tag(MarvelBiomes.Tags.SPAWNS_MOUNTAIN_HYDRA_AGENTS).addTag(Tags.Biomes.IS_MOUNTAIN).add(Biomes.GROVE);
        }
    }

    public static class DamageTypeProvider extends TagsProvider<DamageType> {
        public DamageTypeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, Registries.DAMAGE_TYPE, completableFuture, MarvelSuperheroes.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider arg) {
            tag(DamageTypeTags.ALWAYS_HURTS_ENDER_DRAGONS).add(MarvelDamageTypes.KINETIC_BLAST, MarvelDamageTypes.VIBRANIUM_SHIELD, MarvelDamageTypes.VIBRANIUM_SHIELD_DISPENSER, MarvelDamageTypes.OPTIC_BLAST, MarvelDamageTypes.OPTIC_BLAST_GOOFY, MarvelDamageTypes.GIANT_MAN, MarvelDamageTypes.MJOLNIR, MarvelDamageTypes.MJOLNIR_DISPENSER, MarvelDamageTypes.STORMBREAKER, MarvelDamageTypes.STORMBREAKER_DISPENSER, MarvelDamageTypes.PHOTON_BLAST);
            tag(DamageTypeTags.AVOIDS_GUARDIAN_THORNS).add(MarvelDamageTypes.KINETIC_BLAST, MarvelDamageTypes.OPTIC_BLAST, MarvelDamageTypes.OPTIC_BLAST_GOOFY, MarvelDamageTypes.PHOTON_BLAST);
            tag(DamageTypeTags.IS_EXPLOSION).add(MarvelDamageTypes.KINETIC_BLAST, MarvelDamageTypes.TESSERACT_FIREWORKS);
            tag(DamageTypeTags.IS_PLAYER_ATTACK).add(MarvelDamageTypes.KINETIC_BLAST, MarvelDamageTypes.OPTIC_BLAST, MarvelDamageTypes.OPTIC_BLAST_GOOFY, MarvelDamageTypes.WEB_SHOT, MarvelDamageTypes.GIANT_MAN, MarvelDamageTypes.QUICKSILVER_ATTACK, MarvelDamageTypes.PHOTON_BLAST);
            tag(DamageTypeTags.IS_PROJECTILE).add(MarvelDamageTypes.VIBRANIUM_SHIELD, MarvelDamageTypes.VIBRANIUM_SHIELD_DISPENSER, MarvelDamageTypes.WEB_SHOT, MarvelDamageTypes.MJOLNIR, MarvelDamageTypes.MJOLNIR_DISPENSER, MarvelDamageTypes.STORMBREAKER, MarvelDamageTypes.STORMBREAKER_DISPENSER, MarvelDamageTypes.FLAMETHROWER, MarvelDamageTypes.OPTIC_BLAST, MarvelDamageTypes.PHOTON_BLAST, MarvelDamageTypes.TESSERACT_ARROW);
            tag(DamageTypeTags.ALWAYS_KILLS_ARMOR_STANDS).add(MarvelDamageTypes.VIBRANIUM_SHIELD, MarvelDamageTypes.VIBRANIUM_SHIELD_DISPENSER, MarvelDamageTypes.MJOLNIR, MarvelDamageTypes.MJOLNIR_DISPENSER, MarvelDamageTypes.STORMBREAKER, MarvelDamageTypes.STORMBREAKER_DISPENSER, MarvelDamageTypes.OPTIC_BLAST, MarvelDamageTypes.OPTIC_BLAST_GOOFY, MarvelDamageTypes.TESSERACT_ARROW);
            tag(DamageTypeTags.NO_KNOCKBACK).add(MarvelDamageTypes.OPTIC_BLAST, MarvelDamageTypes.OPTIC_BLAST_GOOFY, MarvelDamageTypes.WEB_SHOT, MarvelDamageTypes.FLAMETHROWER, MarvelDamageTypes.PHOTON_BLAST);
            tag(DamageTypeTags.PANIC_CAUSES).add(MarvelDamageTypes.TESSERACT_ARROW, MarvelDamageTypes.TESSERACT_FIREWORKS);
            tag(DamageTypeTags.BYPASSES_ARMOR).add(MarvelDamageTypes.OPTIC_BLAST_GOOFY, MarvelDamageTypes.TESSERACT_ARROW, MarvelDamageTypes.TESSERACT_FIREWORKS);
            tag(DamageTypeTags.BYPASSES_COOLDOWN).add(MarvelDamageTypes.OPTIC_BLAST_GOOFY, MarvelDamageTypes.QUICKSILVER_ATTACK);
            tag(DamageTypeTags.BYPASSES_EFFECTS).add(MarvelDamageTypes.OPTIC_BLAST_GOOFY);
            tag(DamageTypeTags.BYPASSES_ENCHANTMENTS).add(MarvelDamageTypes.OPTIC_BLAST_GOOFY);
            tag(DamageTypeTags.BYPASSES_INVULNERABILITY).add(MarvelDamageTypes.OPTIC_BLAST_GOOFY);
            tag(DamageTypeTags.BYPASSES_RESISTANCE).add(MarvelDamageTypes.OPTIC_BLAST_GOOFY);
            tag(DamageTypeTags.BYPASSES_SHIELD).add(MarvelDamageTypes.OPTIC_BLAST_GOOFY, MarvelDamageTypes.GIANT_MAN, MarvelDamageTypes.STORMBREAKER, MarvelDamageTypes.STORMBREAKER_DISPENSER);
            tag(DamageTypeTags.BYPASSES_WOLF_ARMOR).add(MarvelDamageTypes.OPTIC_BLAST_GOOFY);

            tag(MarvelDamageTypes.Tags.OPTIC_BLAST).add(MarvelDamageTypes.OPTIC_BLAST, MarvelDamageTypes.OPTIC_BLAST_GOOFY);
        }
    }

    public static class BannerPatternProvider extends TagsProvider<BannerPattern> {
        public BannerPatternProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, Registries.BANNER_PATTERN, completableFuture, MarvelSuperheroes.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider arg) {
            tag(MarvelBannerPatterns.Tags.PATTERN_ITEM_HYDRA).add(MarvelBannerPatterns.HYDRA);
        }
    }
}
