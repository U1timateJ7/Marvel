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
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.damagesources.MarvelDamageTypes;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class MarvelTagProvider {
    public static void addProviders(GatherDataEvent event) {
        BlockProvider blockTagProvider = event.getGenerator().addProvider(event.includeServer(), (DataProvider.Factory<BlockProvider>) output -> new BlockProvider(output, event.getLookupProvider(), event.getExistingFileHelper()));
        event.getGenerator().addProvider(event.includeServer(), (DataProvider.Factory<ItemProvider>) output -> new ItemProvider(output, event.getLookupProvider(), blockTagProvider.contentsGetter(), event.getExistingFileHelper()));
        event.getGenerator().addProvider(event.includeServer(), (DataProvider.Factory<EntityTypeProvider>) output -> new EntityTypeProvider(output, event.getLookupProvider(), event.getExistingFileHelper()));
        event.getGenerator().addProvider(event.includeServer(), (DataProvider.Factory<DamageTypeProvider>) output -> new DamageTypeProvider(output, event.getLookupProvider(), event.getExistingFileHelper()));
    }

    public static class BlockProvider extends BlockTagsProvider {
        public BlockProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, MarvelSuperheroes.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MarvelBlocks.VIBRANIUM_ORE.get(), MarvelBlocks.DEEPSLATE_VIBRANIUM_ORE.get(), MarvelBlocks.VIBRANIUM_BLOCK.get(), MarvelBlocks.TITANIUM_ORE.get(), MarvelBlocks.DEEPSLATE_TITANIUM_ORE.get(), MarvelBlocks.TITANIUM_BLOCK.get(), MarvelBlocks.RAW_TITANIUM_BLOCK.get(), MarvelBlocks.PALLADIUM_ORE.get(), MarvelBlocks.DEEPSLATE_PALLADIUM_ORE.get(), MarvelBlocks.PALLADIUM_BLOCK.get(), MarvelBlocks.RAW_PALLADIUM_BLOCK.get(), MarvelBlocks.GOLD_TITANIUM_BLOCK.get(), MarvelBlocks.ADAMANTIUM_BLOCK.get(), MarvelBlocks.SUIT_TABLE.get());
            tag(BlockTags.NEEDS_DIAMOND_TOOL).add(MarvelBlocks.VIBRANIUM_ORE.get(), MarvelBlocks.DEEPSLATE_VIBRANIUM_ORE.get(), MarvelBlocks.VIBRANIUM_BLOCK.get(), MarvelBlocks.ADAMANTIUM_BLOCK.get());
            tag(BlockTags.NEEDS_IRON_TOOL).add(MarvelBlocks.TITANIUM_ORE.get(), MarvelBlocks.DEEPSLATE_TITANIUM_ORE.get(), MarvelBlocks.TITANIUM_BLOCK.get(), MarvelBlocks.RAW_TITANIUM_BLOCK.get(), MarvelBlocks.GOLD_TITANIUM_BLOCK.get());
            tag(BlockTags.NEEDS_STONE_TOOL).add(MarvelBlocks.PALLADIUM_ORE.get(), MarvelBlocks.DEEPSLATE_PALLADIUM_ORE.get(), MarvelBlocks.PALLADIUM_BLOCK.get(), MarvelBlocks.RAW_PALLADIUM_BLOCK.get(), MarvelBlocks.SUIT_TABLE.get());

            tag(MarvelBlocks.Tags.VIBRANIUM_ORES).add(MarvelBlocks.VIBRANIUM_ORE.get(), MarvelBlocks.DEEPSLATE_VIBRANIUM_ORE.get());
            tag(MarvelBlocks.Tags.TITANIUM_ORES).add(MarvelBlocks.TITANIUM_ORE.get(), MarvelBlocks.DEEPSLATE_TITANIUM_ORE.get());
            tag(MarvelBlocks.Tags.PALLADIUM_ORES).add(MarvelBlocks.PALLADIUM_ORE.get(), MarvelBlocks.DEEPSLATE_PALLADIUM_ORE.get());
        }
    }

    public static class ItemProvider extends ItemTagsProvider {
        public ItemProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, blockTags, MarvelSuperheroes.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            tag(ItemTags.AXES).add(MarvelItems.VIBRANIUM_AXE.get());
            tag(ItemTags.CHEST_ARMOR).add(MarvelItems.VIBRANIUM_CHESTPLATE.get());
            tag(ItemTags.DYEABLE).add(MarvelItems.VIBRANIUM_SHIELD.get(), MarvelItems.PROTO_ADAMANTIUM_SHIELD.get());
            tag(ItemTags.FOOT_ARMOR).add(MarvelItems.VIBRANIUM_BOOTS.get());
            tag(ItemTags.HEAD_ARMOR).add(MarvelItems.VIBRANIUM_HELMET.get());
            tag(ItemTags.HOES).add(MarvelItems.VIBRANIUM_HOE.get());
            tag(ItemTags.LEG_ARMOR).add(MarvelItems.VIBRANIUM_LEGGINGS.get());
            tag(ItemTags.PICKAXES).add(MarvelItems.VIBRANIUM_PICKAXE.get());
            tag(ItemTags.SHOVELS).add(MarvelItems.VIBRANIUM_SHOVEL.get());
            tag(ItemTags.SWORDS).add(MarvelItems.VIBRANIUM_SWORD.get());
            tag(ItemTags.TRIM_MATERIALS).add(MarvelItems.VIBRANIUM_INGOT.get(), MarvelItems.TITANIUM_INGOT.get(), MarvelItems.PALLADIUM_INGOT.get());
            tag(ItemTags.TRIMMABLE_ARMOR).add(MarvelItems.VIBRANIUM_HELMET.get(), MarvelItems.VIBRANIUM_CHESTPLATE.get(), MarvelItems.VIBRANIUM_LEGGINGS.get(), MarvelItems.VIBRANIUM_BOOTS.get());

            copy(MarvelBlocks.Tags.VIBRANIUM_ORES, MarvelItems.Tags.VIBRANIUM_ORES);
            copy(MarvelBlocks.Tags.TITANIUM_ORES, MarvelItems.Tags.TITANIUM_ORES);
            copy(MarvelBlocks.Tags.PALLADIUM_ORES, MarvelItems.Tags.PALLADIUM_ORES);
            tag(MarvelItems.Tags.RENDER_HAND).add(MarvelItems.ADAMANTIUM_CLAWS.get(), MarvelItems.OPTIC_BLAST.get());
            tag(MarvelItems.Tags.HIDES_OUTER_LAYER).add(MarvelItems.CAPTAIN_AMERICA_HELMET.get(), MarvelItems.CAPTAIN_AMERICA_CHESTPLATE.get(), MarvelItems.CAPTAIN_AMERICA_LEGGINGS.get(), MarvelItems.CAPTAIN_AMERICA_BOOTS.get(), MarvelItems.CAPTAIN_AMERICA_STEALTH_HELMET.get(), MarvelItems.CAPTAIN_AMERICA_STEALTH_CHESTPLATE.get(), MarvelItems.CAPTAIN_AMERICA_STEALTH_LEGGINGS.get(), MarvelItems.CAPTAIN_AMERICA_STEALTH_BOOTS.get(), MarvelItems.CAPTAIN_CARTER_CHESTPLATE.get(), MarvelItems.CAPTAIN_CARTER_LEGGINGS.get(), MarvelItems.CAPTAIN_CARTER_BOOTS.get(), MarvelItems.RED_GUARDIAN_HELMET.get(), MarvelItems.RED_GUARDIAN_CHESTPLATE.get(), MarvelItems.RED_GUARDIAN_LEGGINGS.get(), MarvelItems.RED_GUARDIAN_BOOTS.get(), MarvelItems.BLACK_PANTHER_HELMET.get(), MarvelItems.BLACK_PANTHER_CHESTPLATE.get(), MarvelItems.BLACK_PANTHER_LEGGINGS.get(), MarvelItems.BLACK_PANTHER_BOOTS.get(), MarvelItems.KINETIC_BLACK_PANTHER_HELMET.get(), MarvelItems.KINETIC_BLACK_PANTHER_CHESTPLATE.get(), MarvelItems.KINETIC_BLACK_PANTHER_LEGGINGS.get(), MarvelItems.KINETIC_BLACK_PANTHER_BOOTS.get(), MarvelItems.KILLMONGER_HELMET.get(), MarvelItems.KILLMONGER_CHESTPLATE.get(), MarvelItems.KILLMONGER_LEGGINGS.get(), MarvelItems.KILLMONGER_BOOTS.get(), MarvelItems.WOLVERINE_HELMET.get(), MarvelItems.WOLVERINE_CHESTPLATE.get(), MarvelItems.WOLVERINE_SLEEVELESS_CHESTPLATE.get(), MarvelItems.WOLVERINE_LEGGINGS.get(), MarvelItems.WOLVERINE_BOOTS.get(), MarvelItems.WOLVERINE_BROWN_HELMET.get(), MarvelItems.WOLVERINE_BROWN_CHESTPLATE.get(), MarvelItems.WOLVERINE_BROWN_SLEEVELESS_CHESTPLATE.get(), MarvelItems.WOLVERINE_BROWN_LEGGINGS.get(), MarvelItems.WOLVERINE_BROWN_BOOTS.get(), MarvelItems.CYCLOPS_CHESTPLATE.get(), MarvelItems.CYCLOPS_LEGGINGS.get(), MarvelItems.CYCLOPS_BOOTS.get(), MarvelItems.CYCLOPS_DARK_HELMET.get(), MarvelItems.CYCLOPS_DARK_CHESTPLATE.get(), MarvelItems.CYCLOPS_DARK_LEGGINGS.get(), MarvelItems.CYCLOPS_DARK_BOOTS.get());
            tag(MarvelItems.Tags.CAPTAIN_AMERICA_HELMET).add(MarvelItems.CAPTAIN_AMERICA_HELMET.get(), MarvelItems.CAPTAIN_AMERICA_STEALTH_HELMET.get(), MarvelItems.RED_GUARDIAN_HELMET.get());
            tag(MarvelItems.Tags.BLACK_PANTHER_HELMET).add(MarvelItems.BLACK_PANTHER_HELMET.get());
            tag(MarvelItems.Tags.KINETIC_BLACK_PANTHER_HELMET).add(MarvelItems.KINETIC_BLACK_PANTHER_HELMET.get(), MarvelItems.KILLMONGER_HELMET.get());
            tag(MarvelItems.Tags.WOLVERINE_HELMET).add(MarvelItems.WOLVERINE_HELMET.get(), MarvelItems.WOLVERINE_BROWN_HELMET.get());
            tag(MarvelItems.Tags.CYCLOPS_HELMET).add(MarvelItems.CYCLOPS_HELMET.get(), MarvelItems.CYCLOPS_DARK_HELMET.get());
            tag(MarvelItems.Tags.CAPTAIN_AMERICA_CHESTPLATE).add(MarvelItems.CAPTAIN_AMERICA_CHESTPLATE.get(), MarvelItems.CAPTAIN_AMERICA_STEALTH_CHESTPLATE.get(), MarvelItems.CAPTAIN_CARTER_CHESTPLATE.get(), MarvelItems.RED_GUARDIAN_CHESTPLATE.get());
            tag(MarvelItems.Tags.BLACK_PANTHER_CHESTPLATE).add(MarvelItems.BLACK_PANTHER_CHESTPLATE.get());
            tag(MarvelItems.Tags.KINETIC_BLACK_PANTHER_CHESTPLATE).add(MarvelItems.KINETIC_BLACK_PANTHER_CHESTPLATE.get(), MarvelItems.KILLMONGER_CHESTPLATE.get());
            tag(MarvelItems.Tags.WOLVERINE_CHESTPLATE).add(MarvelItems.WOLVERINE_CHESTPLATE.get(), MarvelItems.WOLVERINE_SLEEVELESS_CHESTPLATE.get(), MarvelItems.WOLVERINE_BROWN_CHESTPLATE.get(), MarvelItems.WOLVERINE_BROWN_SLEEVELESS_CHESTPLATE.get());
            tag(MarvelItems.Tags.CYCLOPS_CHESTPLATE).add(MarvelItems.CYCLOPS_CHESTPLATE.get(), MarvelItems.CYCLOPS_DARK_CHESTPLATE.get());
            tag(MarvelItems.Tags.CAPTAIN_AMERICA_LEGGINGS).add(MarvelItems.CAPTAIN_AMERICA_LEGGINGS.get(), MarvelItems.CAPTAIN_AMERICA_STEALTH_LEGGINGS.get(), MarvelItems.CAPTAIN_CARTER_LEGGINGS.get(), MarvelItems.RED_GUARDIAN_LEGGINGS.get());
            tag(MarvelItems.Tags.BLACK_PANTHER_LEGGINGS).add(MarvelItems.BLACK_PANTHER_LEGGINGS.get());
            tag(MarvelItems.Tags.KINETIC_BLACK_PANTHER_LEGGINGS).add(MarvelItems.KINETIC_BLACK_PANTHER_LEGGINGS.get(), MarvelItems.KILLMONGER_LEGGINGS.get());
            tag(MarvelItems.Tags.WOLVERINE_LEGGINGS).add(MarvelItems.WOLVERINE_LEGGINGS.get(), MarvelItems.WOLVERINE_BROWN_LEGGINGS.get());
            tag(MarvelItems.Tags.CYCLOPS_LEGGINGS).add(MarvelItems.CYCLOPS_LEGGINGS.get(), MarvelItems.CYCLOPS_DARK_LEGGINGS.get());
            tag(MarvelItems.Tags.CAPTAIN_AMERICA_BOOTS).add(MarvelItems.CAPTAIN_AMERICA_BOOTS.get(), MarvelItems.CAPTAIN_AMERICA_STEALTH_BOOTS.get(), MarvelItems.CAPTAIN_CARTER_BOOTS.get(), MarvelItems.RED_GUARDIAN_BOOTS.get());
            tag(MarvelItems.Tags.BLACK_PANTHER_BOOTS).add(MarvelItems.BLACK_PANTHER_BOOTS.get());
            tag(MarvelItems.Tags.KINETIC_BLACK_PANTHER_BOOTS).add(MarvelItems.KINETIC_BLACK_PANTHER_BOOTS.get(), MarvelItems.KILLMONGER_BOOTS.get());
            tag(MarvelItems.Tags.WOLVERINE_BOOTS).add(MarvelItems.WOLVERINE_BOOTS.get(), MarvelItems.WOLVERINE_BROWN_BOOTS.get());
            tag(MarvelItems.Tags.CYCLOPS_BOOTS).add(MarvelItems.CYCLOPS_BOOTS.get(), MarvelItems.CYCLOPS_DARK_BOOTS.get());
            tag(MarvelItems.Tags.CAPTAIN_AMERICA_ARMOR).addTag(MarvelItems.Tags.CAPTAIN_AMERICA_HELMET).addTag(MarvelItems.Tags.CAPTAIN_AMERICA_CHESTPLATE).addTag(MarvelItems.Tags.CAPTAIN_AMERICA_LEGGINGS).addTag(MarvelItems.Tags.CAPTAIN_AMERICA_BOOTS);
            tag(MarvelItems.Tags.BLACK_PANTHER_ARMOR).addTag(MarvelItems.Tags.BLACK_PANTHER_HELMET).addTag(MarvelItems.Tags.BLACK_PANTHER_CHESTPLATE).addTag(MarvelItems.Tags.BLACK_PANTHER_LEGGINGS).addTag(MarvelItems.Tags.BLACK_PANTHER_BOOTS);
            tag(MarvelItems.Tags.KINETIC_BLACK_PANTHER_ARMOR).addTag(MarvelItems.Tags.KINETIC_BLACK_PANTHER_HELMET).addTag(MarvelItems.Tags.KINETIC_BLACK_PANTHER_CHESTPLATE).addTag(MarvelItems.Tags.KINETIC_BLACK_PANTHER_LEGGINGS).addTag(MarvelItems.Tags.KINETIC_BLACK_PANTHER_BOOTS);
            tag(MarvelItems.Tags.WOLVERINE_ARMOR).addTag(MarvelItems.Tags.WOLVERINE_HELMET).addTag(MarvelItems.Tags.WOLVERINE_CHESTPLATE).addTag(MarvelItems.Tags.WOLVERINE_LEGGINGS).addTag(MarvelItems.Tags.WOLVERINE_BOOTS);
            tag(MarvelItems.Tags.CYCLOPS_ARMOR).addTag(MarvelItems.Tags.CYCLOPS_HELMET).addTag(MarvelItems.Tags.CYCLOPS_CHESTPLATE).addTag(MarvelItems.Tags.CYCLOPS_LEGGINGS).addTag(MarvelItems.Tags.CYCLOPS_BOOTS);
            tag(MarvelItems.Tags.SOUND_DAMPENING_BOOTS).addTag(MarvelItems.Tags.KINETIC_BLACK_PANTHER_BOOTS);
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

    public static class DamageTypeProvider extends TagsProvider<DamageType> {
        public DamageTypeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, Registries.DAMAGE_TYPE, completableFuture, MarvelSuperheroes.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider arg) {
            tag(DamageTypeTags.ALWAYS_HURTS_ENDER_DRAGONS).add(MarvelDamageTypes.KINETIC_BLAST, MarvelDamageTypes.VIBRANIUM_SHIELD, MarvelDamageTypes.OPTIC_BLAST, MarvelDamageTypes.OPTIC_BLAST_GOOFY);
            tag(DamageTypeTags.AVOIDS_GUARDIAN_THORNS).add(MarvelDamageTypes.KINETIC_BLAST, MarvelDamageTypes.OPTIC_BLAST, MarvelDamageTypes.OPTIC_BLAST_GOOFY);
            tag(DamageTypeTags.IS_EXPLOSION).add(MarvelDamageTypes.KINETIC_BLAST);
            tag(DamageTypeTags.IS_PLAYER_ATTACK).add(MarvelDamageTypes.KINETIC_BLAST, MarvelDamageTypes.OPTIC_BLAST, MarvelDamageTypes.OPTIC_BLAST_GOOFY);
            tag(DamageTypeTags.IS_PROJECTILE).add(MarvelDamageTypes.VIBRANIUM_SHIELD);
            tag(DamageTypeTags.ALWAYS_KILLS_ARMOR_STANDS).add(MarvelDamageTypes.VIBRANIUM_SHIELD, MarvelDamageTypes.OPTIC_BLAST, MarvelDamageTypes.OPTIC_BLAST_GOOFY);
            tag(DamageTypeTags.NO_KNOCKBACK).add(MarvelDamageTypes.OPTIC_BLAST, MarvelDamageTypes.OPTIC_BLAST_GOOFY);
            tag(DamageTypeTags.BYPASSES_ARMOR).add(MarvelDamageTypes.OPTIC_BLAST_GOOFY);
            tag(DamageTypeTags.BYPASSES_COOLDOWN).add(MarvelDamageTypes.OPTIC_BLAST_GOOFY);
            tag(DamageTypeTags.BYPASSES_EFFECTS).add(MarvelDamageTypes.OPTIC_BLAST_GOOFY);
            tag(DamageTypeTags.BYPASSES_ENCHANTMENTS).add(MarvelDamageTypes.OPTIC_BLAST_GOOFY);
            tag(DamageTypeTags.BYPASSES_INVULNERABILITY).add(MarvelDamageTypes.OPTIC_BLAST_GOOFY);
            tag(DamageTypeTags.BYPASSES_RESISTANCE).add(MarvelDamageTypes.OPTIC_BLAST_GOOFY);
            tag(DamageTypeTags.BYPASSES_SHIELD).add(MarvelDamageTypes.OPTIC_BLAST_GOOFY);
            tag(DamageTypeTags.BYPASSES_WOLF_ARMOR).add(MarvelDamageTypes.OPTIC_BLAST_GOOFY);
        }
    }
}
