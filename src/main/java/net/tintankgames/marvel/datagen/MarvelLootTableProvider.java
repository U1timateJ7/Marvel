package net.tintankgames.marvel.datagen;

import net.minecraft.advancements.critereon.EntityEquipmentPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;
import net.tintankgames.marvel.world.level.levelgen.structure.MarvelStructures;
import net.tintankgames.marvel.world.level.saveddata.maps.MarvelMapDecorationTypes;
import net.tintankgames.marvel.world.level.storage.loot.MarvelLootTables;

import java.util.Collections;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        protected EntityLoot() {
            super(FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        public void generate() {
            add(MarvelEntityTypes.HYDRA_AGENT.get(), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(MarvelItems.TESSERACT_SHARD).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))).when(LootItemKilledByPlayerCondition.killedByPlayer()).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().equipment(EntityEquipmentPredicate.Builder.equipment().mainhand(ItemPredicate.Builder.item().of(MarvelItems.TESSERACT_CROSSBOW)))))
                    )
            );
            add(MarvelEntityTypes.BARON_ZEMO.get(), LootTable.lootTable());
            add(MarvelEntityTypes.WINTER_SOLDIER.get(), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(MarvelItems.WINTER_SOLDIER_KNIFE)).when(LootItemKilledByPlayerCondition.killedByPlayer()))
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(MarvelItems.WINTER_SOLDIER_CHESTPLATE)).when(LootItemKilledByPlayerCondition.killedByPlayer()))
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(MarvelItems.WINTER_SOLDIER_LEGGINGS)).when(LootItemKilledByPlayerCondition.killedByPlayer()))
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(MarvelItems.WINTER_SOLDIER_BOOTS)).when(LootItemKilledByPlayerCondition.killedByPlayer()))
            );
            add(MarvelEntityTypes.RED_SKULL.get(), LootTable.lootTable());
        }

        @Override
        protected Stream<EntityType<?>> getKnownEntityTypes() {
            return MarvelEntityTypes.REGISTER.getEntries().stream().map(DeferredHolder::get);
        }
    }

    public static class ChestLoot implements LootTableSubProvider {
        @Override
        public void generate(HolderLookup.Provider registries, BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
            consumer.accept(MarvelLootTables.HYDRA_BASE, LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 4.0F))
                            .add(LootItem.lootTableItem(Items.ARROW).setWeight(20).setQuality(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F))))
                            .add(LootItem.lootTableItem(Items.LEATHER).setWeight(20).setQuality(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                            .add(LootItem.lootTableItem(Items.COAL).setWeight(10).setQuality(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))
                            .add(LootItem.lootTableItem(Items.REDSTONE).setWeight(10).setQuality(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 6.0F))))
                            .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(5).setQuality(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))
                            .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(5).setQuality(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                    ).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(Items.EMERALD).setWeight(30).setQuality(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))
                            .add(LootItem.lootTableItem(MarvelItems.REINFORCED_LEATHER).setWeight(20).setQuality(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                            .add(LootItem.lootTableItem(Items.CROSSBOW).setWeight(10).setQuality(3).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15))))
                            .add(LootItem.lootTableItem(Items.IRON_SWORD).setWeight(10).setQuality(3).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15))))
                            .add(LootItem.lootTableItem(MarvelItems.HYDRA_BANNER_PATTERN).setWeight(5).setQuality(4))
                            .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(5).setQuality(4).apply(SetItemCountFunction.setCount(new UniformGenerator(ConstantValue.exactly(1.0F), UniformGenerator.between(1.0F, 2.0F)))))
                    )
            );
            consumer.accept(MarvelLootTables.HYDRA_BASE_MAP, LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                            .add(NestedLootTable.lootTableReference(MarvelLootTables.HYDRA_BASE))
                    ).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(Items.MAP).apply(ExplorationMapFunction.makeExplorationMap().setDestination(MarvelStructures.Tags.ON_HYDRA_HEADQUARTERS_MAPS).setMapDecoration(MarvelMapDecorationTypes.HYDRA_HEADQUARTERS).setSearchRadius(100)).apply(SetNameFunction.setName(Component.translatable("filled_map.hydra_headquarters"), SetNameFunction.Target.ITEM_NAME)))
                    )
            );
            consumer.accept(MarvelLootTables.HYDRA_OUTPOST, LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 4.0F))
                            .add(LootItem.lootTableItem(Items.ARROW).setWeight(20).setQuality(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F))))
                            .add(LootItem.lootTableItem(Items.LEATHER).setWeight(20).setQuality(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                            .add(LootItem.lootTableItem(Items.COAL).setWeight(10).setQuality(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))
                            .add(LootItem.lootTableItem(Items.REDSTONE).setWeight(10).setQuality(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 6.0F))))
                            .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(5).setQuality(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))
                            .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(5).setQuality(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                    ).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(Items.MAP).apply(ExplorationMapFunction.makeExplorationMap().setDestination(MarvelStructures.Tags.HYDRA_BASE).setMapDecoration(MarvelMapDecorationTypes.HYDRA).setSearchRadius(100)).apply(SetNameFunction.setName(Component.translatable("filled_map.hydra"), SetNameFunction.Target.ITEM_NAME)))
                    )
            );
        }
    }
}
