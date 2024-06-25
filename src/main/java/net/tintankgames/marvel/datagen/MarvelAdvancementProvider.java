package net.tintankgames.marvel.datagen;

import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.damagesources.MarvelDamageTypes;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;

import java.util.Optional;
import java.util.function.Consumer;

public class MarvelAdvancementProvider implements AdvancementProvider.AdvancementGenerator {
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
        AdvancementHolder root = Advancement.Builder.advancement()
                .display(MarvelBlocks.GOLD_TITANIUM_BLOCK,
                        Component.translatable("advancements.marvel.root.title"),
                        Component.translatable("advancements.marvel.root.description"),
                        MarvelSuperheroes.id("textures/block/gold_titanium_block.png"),
                        AdvancementType.TASK,
                        false,
                        false,
                        false)
                .addCriterion("tick", PlayerTrigger.TriggerInstance.tick())
                .save(saver, MarvelSuperheroes.id("root"), existingFileHelper);
        AdvancementHolder suitVariant = Advancement.Builder.advancement().parent(root)
                .display(MarvelItems.CAPTAIN_CARTER_CHESTPLATE,
                        Component.translatable("advancements.marvel.suit_variant.title"),
                        Component.translatable("advancements.marvel.suit_variant.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("has_suit_variant", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.SUIT_VARIANTS)))
                .save(saver, MarvelSuperheroes.id("suit_variant"), existingFileHelper);
        AdvancementHolder uru = Advancement.Builder.advancement().parent(root)
                .display(MarvelItems.URU_INGOT,
                        Component.translatable("advancements.marvel.uru.title"),
                        Component.translatable("advancements.marvel.uru.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("has_uru_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(MarvelItems.URU_INGOT))
                .save(saver, MarvelSuperheroes.id("uru"), existingFileHelper);
        AdvancementHolder mjolnir = Advancement.Builder.advancement().parent(uru)
                .display(MarvelItems.MJOLNIR,
                        Component.translatable("advancements.marvel.mjolnir.title"),
                        Component.translatable("advancements.marvel.mjolnir.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("has_mjolnir", InventoryChangeTrigger.TriggerInstance.hasItems(MarvelItems.MJOLNIR))
                .save(saver, MarvelSuperheroes.id("mjolnir"), existingFileHelper);
        AdvancementHolder stormbreaker = Advancement.Builder.advancement().parent(uru)
                .display(MarvelItems.STORMBREAKER,
                        Component.translatable("advancements.marvel.stormbreaker.title"),
                        Component.translatable("advancements.marvel.stormbreaker.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("has_stormbreaker", InventoryChangeTrigger.TriggerInstance.hasItems(MarvelItems.STORMBREAKER))
                .save(saver, MarvelSuperheroes.id("stormbreaker"), existingFileHelper);
        AdvancementHolder vibranium = Advancement.Builder.advancement().parent(root)
                .display(MarvelItems.VIBRANIUM,
                        Component.translatable("advancements.marvel.vibranium.title"),
                        Component.translatable("advancements.marvel.vibranium.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("has_vibranium", InventoryChangeTrigger.TriggerInstance.hasItems(MarvelItems.VIBRANIUM))
                .save(saver, MarvelSuperheroes.id("vibranium"), existingFileHelper);
        AdvancementHolder coverMeInVibranium = Advancement.Builder.advancement().parent(vibranium)
                .display(MarvelItems.VIBRANIUM_CHESTPLATE,
                        Component.translatable("advancements.marvel.cover_me_in_vibranium.title"),
                        Component.translatable("advancements.marvel.cover_me_in_vibranium.description"),
                        null,
                        AdvancementType.GOAL,
                        true,
                        true,
                        false)
                .addCriterion("has_vibranium_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(MarvelItems.VIBRANIUM_HELMET))
                .addCriterion("has_vibranium_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(MarvelItems.VIBRANIUM_CHESTPLATE))
                .addCriterion("has_vibranium_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(MarvelItems.VIBRANIUM_LEGGINGS))
                .addCriterion("has_vibranium_boots", InventoryChangeTrigger.TriggerInstance.hasItems(MarvelItems.VIBRANIUM_BOOTS))
                .save(saver, MarvelSuperheroes.id("cover_me_in_vibranium"), existingFileHelper);
        AdvancementHolder black_panther = Advancement.Builder.advancement().parent(vibranium)
                .display(MarvelItems.BLACK_PANTHER_HELMET,
                        Component.translatable("advancements.marvel.black_panther.title"),
                        Component.translatable("advancements.marvel.black_panther.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("has_black_panther_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.BLACK_PANTHER_HELMET)))
                .addCriterion("has_black_panther_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.BLACK_PANTHER_CHESTPLATE)))
                .addCriterion("has_black_panther_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.BLACK_PANTHER_LEGGINGS)))
                .addCriterion("has_black_panther_boots", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.BLACK_PANTHER_BOOTS)))
                .save(saver, MarvelSuperheroes.id("black_panther"), existingFileHelper);
        AdvancementHolder vibraniumShield = Advancement.Builder.advancement().parent(vibranium)
                .display(MarvelItems.PROTO_ADAMANTIUM_SHIELD,
                        Component.translatable("advancements.marvel.vibranium_shield.title"),
                        Component.translatable("advancements.marvel.vibranium_shield.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("has_vibranium_shield", InventoryChangeTrigger.TriggerInstance.hasItems(MarvelItems.VIBRANIUM_SHIELD))
                .addCriterion("has_proto_adamantium_shield", InventoryChangeTrigger.TriggerInstance.hasItems(MarvelItems.PROTO_ADAMANTIUM_SHIELD))
                .requirements(AdvancementRequirements.Strategy.OR)
                .save(saver, MarvelSuperheroes.id("vibranium_shield"), existingFileHelper);
        AdvancementHolder reinforcedLeather = Advancement.Builder.advancement().parent(root)
                .display(MarvelItems.REINFORCED_LEATHER,
                        Component.translatable("advancements.marvel.reinforced_leather.title"),
                        Component.translatable("advancements.marvel.reinforced_leather.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("has_reinforced_leather", InventoryChangeTrigger.TriggerInstance.hasItems(MarvelItems.REINFORCED_LEATHER))
                .save(saver, MarvelSuperheroes.id("reinforced_leather"), existingFileHelper);
        AdvancementHolder captainAmerica = Advancement.Builder.advancement().parent(reinforcedLeather)
                .display(MarvelItems.CAPTAIN_AMERICA_CHESTPLATE,
                        Component.translatable("advancements.marvel.captain_america.title"),
                        Component.translatable("advancements.marvel.captain_america.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("has_captain_america_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.CAPTAIN_AMERICA_HELMET)))
                .addCriterion("has_captain_america_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.CAPTAIN_AMERICA_CHESTPLATE)))
                .addCriterion("has_captain_america_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.CAPTAIN_AMERICA_LEGGINGS)))
                .addCriterion("has_captain_america_boots", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.CAPTAIN_AMERICA_BOOTS)))
                .save(saver, MarvelSuperheroes.id("captain_america"), existingFileHelper);
        AdvancementHolder spiderMan = Advancement.Builder.advancement().parent(reinforcedLeather)
                .display(MarvelItems.SPIDER_MAN_HELMET,
                        Component.translatable("advancements.marvel.spider_man.title"),
                        Component.translatable("advancements.marvel.spider_man.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("has_spider_man_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.SPIDER_MAN_HELMET)))
                .addCriterion("has_spider_man_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.SPIDER_MAN_CHESTPLATE)))
                .addCriterion("has_spider_man_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.SPIDER_MAN_LEGGINGS)))
                .addCriterion("has_spider_man_boots", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.SPIDER_MAN_BOOTS)))
                .save(saver, MarvelSuperheroes.id("spider_man"), existingFileHelper);
        AdvancementHolder cyclops = Advancement.Builder.advancement().parent(reinforcedLeather)
                .display(MarvelItems.CYCLOPS_HELMET,
                        Component.translatable("advancements.marvel.cyclops.title"),
                        Component.translatable("advancements.marvel.cyclops.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("has_cyclops_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.CYCLOPS_HELMET)))
                .addCriterion("has_cyclops_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.CYCLOPS_CHESTPLATE)))
                .addCriterion("has_cyclops_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.CYCLOPS_LEGGINGS)))
                .addCriterion("has_cyclops_boots", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.CYCLOPS_BOOTS)))
                .save(saver, MarvelSuperheroes.id("cyclops"), existingFileHelper);
        AdvancementHolder cyclopsTacticalFishing = Advancement.Builder.advancement().parent(cyclops)
                .display(Items.SALMON,
                        Component.translatable("advancements.marvel.cyclops_tactical_fishing.title"),
                        Component.translatable("advancements.marvel.cyclops_tactical_fishing.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("kill_cod_with_optic_blast", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityType.COD), DamageSourcePredicate.Builder.damageType().tag(TagPredicate.is(MarvelDamageTypes.Tags.OPTIC_BLAST))))
                .addCriterion("kill_salmon_with_optic_blast", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityType.SALMON), DamageSourcePredicate.Builder.damageType().tag(TagPredicate.is(MarvelDamageTypes.Tags.OPTIC_BLAST))))
                .addCriterion("kill_pufferfish_with_optic_blast", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityType.PUFFERFISH), DamageSourcePredicate.Builder.damageType().tag(TagPredicate.is(MarvelDamageTypes.Tags.OPTIC_BLAST))))
                .addCriterion("kill_tropical_fish_with_optic_blast", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityType.TROPICAL_FISH), DamageSourcePredicate.Builder.damageType().tag(TagPredicate.is(MarvelDamageTypes.Tags.OPTIC_BLAST))))
                .requirements(AdvancementRequirements.Strategy.OR)
                .save(saver, MarvelSuperheroes.id("cyclops_tactical_fishing"), existingFileHelper);
        AdvancementHolder wolverine = Advancement.Builder.advancement().parent(reinforcedLeather)
                .display(MarvelItems.WOLVERINE_HELMET,
                        Component.translatable("advancements.marvel.wolverine.title"),
                        Component.translatable("advancements.marvel.wolverine.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("has_wolverine_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.WOLVERINE_HELMET)))
                .addCriterion("has_wolverine_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.WOLVERINE_CHESTPLATE)))
                .addCriterion("has_wolverine_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.WOLVERINE_LEGGINGS)))
                .addCriterion("has_wolverine_boots", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.WOLVERINE_BOOTS)))
                .save(saver, MarvelSuperheroes.id("wolverine"), existingFileHelper);
        AdvancementHolder canadianHealthcare = Advancement.Builder.advancement().parent(wolverine)
                .display(MarvelItems.WOLVERINE_CHESTPLATE,
                        Component.translatable("advancements.marvel.canadian_healthcare.title"),
                        Component.translatable("advancements.marvel.canadian_healthcare.description"),
                        null,
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false)
                .addCriterion("heal_from_one", CriteriaTriggers.IMPOSSIBLE.createCriterion(new ImpossibleTrigger.TriggerInstance()))
                .rewards(AdvancementRewards.Builder.experience(500))
                .save(saver, MarvelSuperheroes.id("canadian_healthcare"), existingFileHelper);
        AdvancementHolder antMan = Advancement.Builder.advancement().parent(reinforcedLeather)
                .display(MarvelItems.ANT_MAN_HELMET,
                        Component.translatable("advancements.marvel.ant_man.title"),
                        Component.translatable("advancements.marvel.ant_man.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("has_ant_man_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.ANT_MAN_HELMET)))
                .addCriterion("has_ant_man_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.ANT_MAN_CHESTPLATE)))
                .addCriterion("has_ant_man_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.ANT_MAN_LEGGINGS)))
                .addCriterion("has_ant_man_boots", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.ANT_MAN_BOOTS)))
                .save(saver, MarvelSuperheroes.id("ant_man"), existingFileHelper);
        AdvancementHolder largerThanLife = Advancement.Builder.advancement().parent(antMan)
                .display(MarvelItems.ANT_MAN_UPGRADED_HELMET,
                        Component.translatable("advancements.marvel.larger_than_life.title"),
                        Component.translatable("advancements.marvel.larger_than_life.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("use_grow", CriteriaTriggers.USING_ITEM.createCriterion(new UsingItemTrigger.TriggerInstance(Optional.empty(), Optional.of(ItemPredicate.Builder.item().of(MarvelItems.GROW).build()))))
                .save(saver, MarvelSuperheroes.id("larger_than_life"), existingFileHelper);
        AdvancementHolder wasp = Advancement.Builder.advancement().parent(antMan)
                .display(MarvelItems.WASP_HELMET,
                        Component.translatable("advancements.marvel.wasp.title"),
                        Component.translatable("advancements.marvel.wasp.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("has_wasp_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.WASP_HELMET)))
                .addCriterion("has_wasp_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.WASP_CHESTPLATE)))
                .addCriterion("has_wasp_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.WASP_LEGGINGS)))
                .addCriterion("has_wasp_boots", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(MarvelItems.Tags.WASP_BOOTS)))
                .save(saver, MarvelSuperheroes.id("wasp"), existingFileHelper);
    }
}
