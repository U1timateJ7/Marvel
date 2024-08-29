package net.tintankgames.marvel.datagen;

import net.minecraft.advancements.critereon.EntityEquipmentPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MarvelLootTableProvider {
    public static class BlockLoot extends BlockLootSubProvider {
        protected BlockLoot(HolderLookup.Provider provider) {
            super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags(), provider);
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
            dropSelf(MarvelBlocks.PROTO_ADAMANTIUM_BLOCK.get());
            dropSelf(MarvelBlocks.URU_BLOCK.get());
            dropSelf(MarvelBlocks.SUIT_TABLE.get());
            dropSelf(MarvelBlocks.SUIT_CHARGER.get());
            dropSelf(MarvelBlocks.GREEN_HYDRA_BRICKS.get());
            add(MarvelBlocks.GREEN_HYDRA_BRICK_SLAB.get(), this::createSlabItemTable);
            dropSelf(MarvelBlocks.GREEN_HYDRA_BRICK_STAIRS.get());
            dropSelf(MarvelBlocks.GREEN_HYDRA_BRICK_WALL.get());
            dropSelf(MarvelBlocks.GREEN_HYDRA_BRICK_LAMP.get());
            dropSelf(MarvelBlocks.YELLOW_HYDRA_BRICKS.get());
            add(MarvelBlocks.YELLOW_HYDRA_BRICK_SLAB.get(), this::createSlabItemTable);
            dropSelf(MarvelBlocks.YELLOW_HYDRA_BRICK_STAIRS.get());
            dropSelf(MarvelBlocks.YELLOW_HYDRA_BRICK_WALL.get());
            dropSelf(MarvelBlocks.YELLOW_HYDRA_BRICK_LAMP.get());
            dropSelf(MarvelBlocks.LIGHT_GRAY_HYDRA_BRICKS.get());
            add(MarvelBlocks.LIGHT_GRAY_HYDRA_BRICK_SLAB.get(), this::createSlabItemTable);
            dropSelf(MarvelBlocks.LIGHT_GRAY_HYDRA_BRICK_STAIRS.get());
            dropSelf(MarvelBlocks.LIGHT_GRAY_HYDRA_BRICK_WALL.get());
            dropSelf(MarvelBlocks.LIGHT_GRAY_HYDRA_BRICK_LAMP.get());
            dropSelf(MarvelBlocks.GRAY_HYDRA_BRICKS.get());
            add(MarvelBlocks.GRAY_HYDRA_BRICK_SLAB.get(), this::createSlabItemTable);
            dropSelf(MarvelBlocks.GRAY_HYDRA_BRICK_STAIRS.get());
            dropSelf(MarvelBlocks.GRAY_HYDRA_BRICK_WALL.get());
            dropSelf(MarvelBlocks.GRAY_HYDRA_BRICK_LAMP.get());
            dropSelf(MarvelBlocks.STONE_BRICK_LAMP.get());
            dropSelf(MarvelBlocks.DEEPSLATE_BRICK_LAMP.get());
            dropSelf(MarvelBlocks.TESSERACT.get());
            add(MarvelBlocks.SPIDER_WEB.get(), noDrop());
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return MarvelBlocks.REGISTER.getEntries().stream().map(DeferredHolder::get).collect(Collectors.toSet());
        }
    }

    public static class EntityLoot extends EntityLootSubProvider {
        protected EntityLoot(HolderLookup.Provider provider) {
            super(FeatureFlags.REGISTRY.allFlags(), provider);
        }

        @Override
        public void generate() {
            add(MarvelEntityTypes.HYDRA_AGENT.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(MarvelItems.TESSERACT_SHARD).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))).when(LootItemKilledByPlayerCondition.killedByPlayer()).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().equipment(EntityEquipmentPredicate.Builder.equipment().mainhand(ItemPredicate.Builder.item().of(MarvelItems.TESSERACT_CROSSBOW)))))));
            add(MarvelEntityTypes.BARON_ZEMO.get(), LootTable.lootTable());
            add(MarvelEntityTypes.WINTER_SOLDIER.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(MarvelItems.WINTER_SOLDIER_KNIFE)).when(LootItemKilledByPlayerCondition.killedByPlayer())).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(MarvelItems.WINTER_SOLDIER_CHESTPLATE)).when(LootItemKilledByPlayerCondition.killedByPlayer())).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(MarvelItems.WINTER_SOLDIER_LEGGINGS)).when(LootItemKilledByPlayerCondition.killedByPlayer())).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(MarvelItems.WINTER_SOLDIER_BOOTS)).when(LootItemKilledByPlayerCondition.killedByPlayer())));
            add(MarvelEntityTypes.RED_SKULL.get(), LootTable.lootTable());
        }

        @Override
        protected Stream<EntityType<?>> getKnownEntityTypes() {
            return MarvelEntityTypes.REGISTER.getEntries().stream().map(DeferredHolder::get);
        }
    }
}
