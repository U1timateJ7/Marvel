package net.tintankgames.marvel.datagen;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;

import java.util.Collections;
import java.util.stream.Collectors;

public class MarvelLootTableProvider {
    public static class BlockLoot extends BlockLootSubProvider {
        protected BlockLoot() {
            super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected void generate() {
            dropSelf(MarvelBlocks.VIBRANIUM_ORE.get());
            dropSelf(MarvelBlocks.DEEPSLATE_VIBRANIUM_ORE.get());
            dropSelf(MarvelBlocks.VIBRANIUM_BLOCK.get());
            add(MarvelBlocks.TITANIUM_ORE.get(), block -> createOreDrop(block, MarvelItems.RAW_TITANIUM.get()));
            add(MarvelBlocks.DEEPSLATE_TITANIUM_ORE.get(), block -> createOreDrop(block, MarvelItems.RAW_TITANIUM.get()));
            dropSelf(MarvelBlocks.TITANIUM_BLOCK.get());
            dropSelf(MarvelBlocks.RAW_TITANIUM_BLOCK.get());
            add(MarvelBlocks.PALLADIUM_ORE.get(), block -> createOreDrop(block, MarvelItems.RAW_PALLADIUM.get()));
            add(MarvelBlocks.DEEPSLATE_PALLADIUM_ORE.get(), block -> createOreDrop(block, MarvelItems.RAW_PALLADIUM.get()));
            dropSelf(MarvelBlocks.PALLADIUM_BLOCK.get());
            dropSelf(MarvelBlocks.RAW_PALLADIUM_BLOCK.get());
            dropSelf(MarvelBlocks.GOLD_TITANIUM_BLOCK.get());
            dropSelf(MarvelBlocks.ADAMANTIUM_BLOCK.get());
            dropSelf(MarvelBlocks.URU_BLOCK.get());
            dropSelf(MarvelBlocks.SUIT_TABLE.get());
            add(MarvelBlocks.SPIDER_WEB.get(), noDrop());
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return MarvelBlocks.REGISTER.getEntries().stream().map(DeferredHolder::get).collect(Collectors.toSet());
        }
    }
}
