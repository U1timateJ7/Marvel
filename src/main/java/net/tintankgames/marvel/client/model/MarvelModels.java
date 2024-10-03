package net.tintankgames.marvel.client.model;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.world.item.ArmorItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.tintankgames.marvel.MarvelSuperheroes;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = MarvelSuperheroes.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MarvelModels {
    public static final ModelLayerLocation SUIT_HELMET = new ModelLayerLocation(MarvelSuperheroes.id("suit_helmet"), "main");
    public static final ModelLayerLocation SUIT_CHESTPLATE = new ModelLayerLocation(MarvelSuperheroes.id("suit_chestplate"), "main");
    public static final ModelLayerLocation SUIT_LEGGINGS = new ModelLayerLocation(MarvelSuperheroes.id("suit_leggings"), "main");
    public static final ModelLayerLocation SUIT_BOOTS = new ModelLayerLocation(MarvelSuperheroes.id("suit_boots"), "main");
    public static final ModelLayerLocation PANTHER_HELMET = new ModelLayerLocation(MarvelSuperheroes.id("panther_helmet"), "main");
    public static final ModelLayerLocation PANTHER_CHESTPLATE = new ModelLayerLocation(MarvelSuperheroes.id("panther_chestplate"), "main");
    public static final ModelLayerLocation PANTHER_LEGGINGS = new ModelLayerLocation(MarvelSuperheroes.id("panther_leggings"), "main");
    public static final ModelLayerLocation PANTHER_BOOTS = new ModelLayerLocation(MarvelSuperheroes.id("panther_boots"), "main");
    public static final ModelLayerLocation WOLVERINE_HELMET = new ModelLayerLocation(MarvelSuperheroes.id("wolverine_helmet"), "main");
    public static final ModelLayerLocation WOLVERINE_CHESTPLATE = new ModelLayerLocation(MarvelSuperheroes.id("wolverine_chestplate"), "main");
    public static final ModelLayerLocation WOLVERINE_LEGGINGS = new ModelLayerLocation(MarvelSuperheroes.id("wolverine_leggings"), "main");
    public static final ModelLayerLocation WOLVERINE_BOOTS = new ModelLayerLocation(MarvelSuperheroes.id("wolverine_boots"), "main");
    public static final ModelLayerLocation CYCLOPS_HELMET = new ModelLayerLocation(MarvelSuperheroes.id("cyclops_helmet"), "main");
    public static final ModelLayerLocation CYCLOPS_CHESTPLATE = new ModelLayerLocation(MarvelSuperheroes.id("cyclops_chestplate"), "main");
    public static final ModelLayerLocation CYCLOPS_LEGGINGS = new ModelLayerLocation(MarvelSuperheroes.id("cyclops_leggings"), "main");
    public static final ModelLayerLocation CYCLOPS_BOOTS = new ModelLayerLocation(MarvelSuperheroes.id("cyclops_boots"), "main");
    public static final ModelLayerLocation SPIDER_MAN_HELMET = new ModelLayerLocation(MarvelSuperheroes.id("spider_man_helmet"), "main");
    public static final ModelLayerLocation SPIDER_MAN_CHESTPLATE = new ModelLayerLocation(MarvelSuperheroes.id("spider_man_chestplate"), "main");
    public static final ModelLayerLocation SPIDER_MAN_LEGGINGS = new ModelLayerLocation(MarvelSuperheroes.id("spider_man_leggings"), "main");
    public static final ModelLayerLocation SPIDER_MAN_BOOTS = new ModelLayerLocation(MarvelSuperheroes.id("spider_man_boots"), "main");
    public static final ModelLayerLocation WASP_HELMET = new ModelLayerLocation(MarvelSuperheroes.id("wasp_helmet"), "main");
    public static final ModelLayerLocation WASP_CHESTPLATE = new ModelLayerLocation(MarvelSuperheroes.id("wasp_chestplate"), "main");
    public static final ModelLayerLocation WASP_LEGGINGS = new ModelLayerLocation(MarvelSuperheroes.id("wasp_leggings"), "main");
    public static final ModelLayerLocation WASP_BOOTS = new ModelLayerLocation(MarvelSuperheroes.id("wasp_boots"), "main");
    public static final ModelLayerLocation THOR_HELMET = new ModelLayerLocation(MarvelSuperheroes.id("thor_helmet"), "main");
    public static final ModelLayerLocation THOR_CHESTPLATE = new ModelLayerLocation(MarvelSuperheroes.id("thor_chestplate"), "main");
    public static final ModelLayerLocation THOR_LEGGINGS = new ModelLayerLocation(MarvelSuperheroes.id("thor_leggings"), "main");
    public static final ModelLayerLocation THOR_BOOTS = new ModelLayerLocation(MarvelSuperheroes.id("thor_boots"), "main");
    public static final ModelLayerLocation IRON_MAN_MARK_1_HELMET = new ModelLayerLocation(MarvelSuperheroes.id("iron_man_mark_1_helmet"), "main");
    public static final ModelLayerLocation IRON_MAN_MARK_1_CHESTPLATE = new ModelLayerLocation(MarvelSuperheroes.id("iron_man_mark_1_chestplate"), "main");
    public static final ModelLayerLocation IRON_MAN_MARK_1_LEGGINGS = new ModelLayerLocation(MarvelSuperheroes.id("iron_man_mark_1_leggings"), "main");
    public static final ModelLayerLocation IRON_MAN_MARK_1_BOOTS = new ModelLayerLocation(MarvelSuperheroes.id("iron_man_mark_1_boots"), "main");
    public static final ModelLayerLocation IRON_MAN_HELMET = new ModelLayerLocation(MarvelSuperheroes.id("iron_man_helmet"), "main");
    public static final ModelLayerLocation IRON_MAN_CHESTPLATE = new ModelLayerLocation(MarvelSuperheroes.id("iron_man_chestplate"), "main");
    public static final ModelLayerLocation IRON_MAN_LEGGINGS = new ModelLayerLocation(MarvelSuperheroes.id("iron_man_leggings"), "main");
    public static final ModelLayerLocation IRON_MAN_BOOTS = new ModelLayerLocation(MarvelSuperheroes.id("iron_man_boots"), "main");
    public static final ModelLayerLocation WAR_MACHINE_HELMET = new ModelLayerLocation(MarvelSuperheroes.id("war_machine_helmet"), "main");
    public static final ModelLayerLocation WAR_MACHINE_CHESTPLATE = new ModelLayerLocation(MarvelSuperheroes.id("war_machine_chestplate"), "main");
    public static final ModelLayerLocation WAR_MACHINE_LEGGINGS = new ModelLayerLocation(MarvelSuperheroes.id("war_machine_leggings"), "main");
    public static final ModelLayerLocation WAR_MACHINE_BOOTS = new ModelLayerLocation(MarvelSuperheroes.id("war_machine_boots"), "main");
    public static final ModelLayerLocation DEADPOOL_HELMET = new ModelLayerLocation(MarvelSuperheroes.id("deadpool_helmet"), "main");
    public static final ModelLayerLocation DEADPOOL_CHESTPLATE = new ModelLayerLocation(MarvelSuperheroes.id("deadpool_chestplate"), "main");
    public static final ModelLayerLocation DEADPOOL_LEGGINGS = new ModelLayerLocation(MarvelSuperheroes.id("deadpool_leggings"), "main");
    public static final ModelLayerLocation DEADPOOL_BOOTS = new ModelLayerLocation(MarvelSuperheroes.id("deadpool_boots"), "main");
    public static final ModelLayerLocation CAPTAIN_MARVEL_HELMET = new ModelLayerLocation(MarvelSuperheroes.id("captain_marvel_helmet"), "main");
    public static final ModelLayerLocation CAPTAIN_MARVEL_CHESTPLATE = new ModelLayerLocation(MarvelSuperheroes.id("captain_marvel_chestplate"), "main");
    public static final ModelLayerLocation CAPTAIN_MARVEL_LEGGINGS = new ModelLayerLocation(MarvelSuperheroes.id("captain_marvel_leggings"), "main");
    public static final ModelLayerLocation CAPTAIN_MARVEL_BOOTS = new ModelLayerLocation(MarvelSuperheroes.id("captain_marvel_boots"), "main");
    public static final ModelLayerLocation WINTER_SOLDIER_HELMET = new ModelLayerLocation(MarvelSuperheroes.id("winter_soldier_helmet"), "main");
    public static final ModelLayerLocation WINTER_SOLDIER_CHESTPLATE = new ModelLayerLocation(MarvelSuperheroes.id("winter_soldier_chestplate"), "main");
    public static final ModelLayerLocation WINTER_SOLDIER_LEGGINGS = new ModelLayerLocation(MarvelSuperheroes.id("winter_soldier_leggings"), "main");
    public static final ModelLayerLocation WINTER_SOLDIER_BOOTS = new ModelLayerLocation(MarvelSuperheroes.id("winter_soldier_boots"), "main");
    public static final ModelLayerLocation VIBRANIUM_SHIELD = new ModelLayerLocation(MarvelSuperheroes.id("vibranium_shield"), "main");
    public static final ModelLayerLocation MJOLNIR = new ModelLayerLocation(MarvelSuperheroes.id("mjolnir"), "main");
    public static final ModelLayerLocation STORMBREAKER = new ModelLayerLocation(MarvelSuperheroes.id("stormbreaker"), "main");
    public static final ModelLayerLocation REPULSOR = new ModelLayerLocation(MarvelSuperheroes.id("repulsor"), "main");
    public static final ModelLayerLocation VERONICA = new ModelLayerLocation(MarvelSuperheroes.id("veronica"), "main");

    @SubscribeEvent
    public static void registerModels(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SUIT_HELMET, () -> SuitModel.createBodyLayer(ArmorItem.Type.HELMET));
        event.registerLayerDefinition(SUIT_CHESTPLATE, () -> SuitModel.createBodyLayer(ArmorItem.Type.CHESTPLATE));
        event.registerLayerDefinition(SUIT_LEGGINGS, () -> SuitModel.createBodyLayer(ArmorItem.Type.LEGGINGS));
        event.registerLayerDefinition(SUIT_BOOTS, () -> SuitModel.createBodyLayer(ArmorItem.Type.BOOTS));
        event.registerLayerDefinition(PANTHER_HELMET, () -> SuitModel.createPantherBodyLayer(ArmorItem.Type.HELMET));
        event.registerLayerDefinition(PANTHER_CHESTPLATE, () -> SuitModel.createPantherBodyLayer(ArmorItem.Type.CHESTPLATE));
        event.registerLayerDefinition(PANTHER_LEGGINGS, () -> SuitModel.createPantherBodyLayer(ArmorItem.Type.LEGGINGS));
        event.registerLayerDefinition(PANTHER_BOOTS, () -> SuitModel.createPantherBodyLayer(ArmorItem.Type.BOOTS));
        event.registerLayerDefinition(WOLVERINE_HELMET, () -> SuitModel.createWolverineBodyLayer(ArmorItem.Type.HELMET));
        event.registerLayerDefinition(WOLVERINE_CHESTPLATE, () -> SuitModel.createWolverineBodyLayer(ArmorItem.Type.CHESTPLATE));
        event.registerLayerDefinition(WOLVERINE_LEGGINGS, () -> SuitModel.createWolverineBodyLayer(ArmorItem.Type.LEGGINGS));
        event.registerLayerDefinition(WOLVERINE_BOOTS, () -> SuitModel.createWolverineBodyLayer(ArmorItem.Type.BOOTS));
        event.registerLayerDefinition(CYCLOPS_HELMET, () -> SuitModel.createCyclopsBodyLayer(ArmorItem.Type.HELMET));
        event.registerLayerDefinition(CYCLOPS_CHESTPLATE, () -> SuitModel.createCyclopsBodyLayer(ArmorItem.Type.CHESTPLATE));
        event.registerLayerDefinition(CYCLOPS_LEGGINGS, () -> SuitModel.createCyclopsBodyLayer(ArmorItem.Type.LEGGINGS));
        event.registerLayerDefinition(CYCLOPS_BOOTS, () -> SuitModel.createCyclopsBodyLayer(ArmorItem.Type.BOOTS));
        event.registerLayerDefinition(SPIDER_MAN_HELMET, () -> SuitModel.createSpiderManBodyLayer(ArmorItem.Type.HELMET));
        event.registerLayerDefinition(SPIDER_MAN_CHESTPLATE, () -> SuitModel.createSpiderManBodyLayer(ArmorItem.Type.CHESTPLATE));
        event.registerLayerDefinition(SPIDER_MAN_LEGGINGS, () -> SuitModel.createSpiderManBodyLayer(ArmorItem.Type.LEGGINGS));
        event.registerLayerDefinition(SPIDER_MAN_BOOTS, () -> SuitModel.createSpiderManBodyLayer(ArmorItem.Type.BOOTS));
        event.registerLayerDefinition(WASP_HELMET, () -> SuitModel.createWaspBodyLayer(ArmorItem.Type.HELMET));
        event.registerLayerDefinition(WASP_CHESTPLATE, () -> SuitModel.createWaspBodyLayer(ArmorItem.Type.CHESTPLATE));
        event.registerLayerDefinition(WASP_LEGGINGS, () -> SuitModel.createWaspBodyLayer(ArmorItem.Type.LEGGINGS));
        event.registerLayerDefinition(WASP_BOOTS, () -> SuitModel.createWaspBodyLayer(ArmorItem.Type.BOOTS));
        event.registerLayerDefinition(THOR_HELMET, () -> SuitModel.createThorBodyLayer(ArmorItem.Type.HELMET));
        event.registerLayerDefinition(THOR_CHESTPLATE, () -> SuitModel.createThorBodyLayer(ArmorItem.Type.CHESTPLATE));
        event.registerLayerDefinition(THOR_LEGGINGS, () -> SuitModel.createThorBodyLayer(ArmorItem.Type.LEGGINGS));
        event.registerLayerDefinition(THOR_BOOTS, () -> SuitModel.createThorBodyLayer(ArmorItem.Type.BOOTS));
        event.registerLayerDefinition(IRON_MAN_MARK_1_HELMET, () -> SuitModel.createIronManMark1BodyLayer(ArmorItem.Type.HELMET));
        event.registerLayerDefinition(IRON_MAN_MARK_1_CHESTPLATE, () -> SuitModel.createIronManMark1BodyLayer(ArmorItem.Type.CHESTPLATE));
        event.registerLayerDefinition(IRON_MAN_MARK_1_LEGGINGS, () -> SuitModel.createIronManMark1BodyLayer(ArmorItem.Type.LEGGINGS));
        event.registerLayerDefinition(IRON_MAN_MARK_1_BOOTS, () -> SuitModel.createIronManMark1BodyLayer(ArmorItem.Type.BOOTS));
        event.registerLayerDefinition(IRON_MAN_HELMET, () -> SuitModel.createIronManBodyLayer(ArmorItem.Type.HELMET));
        event.registerLayerDefinition(IRON_MAN_CHESTPLATE, () -> SuitModel.createIronManBodyLayer(ArmorItem.Type.CHESTPLATE));
        event.registerLayerDefinition(IRON_MAN_LEGGINGS, () -> SuitModel.createIronManBodyLayer(ArmorItem.Type.LEGGINGS));
        event.registerLayerDefinition(IRON_MAN_BOOTS, () -> SuitModel.createIronManBodyLayer(ArmorItem.Type.BOOTS));
        event.registerLayerDefinition(WAR_MACHINE_HELMET, () -> SuitModel.createWarMachineBodyLayer(ArmorItem.Type.HELMET));
        event.registerLayerDefinition(WAR_MACHINE_CHESTPLATE, () -> SuitModel.createWarMachineBodyLayer(ArmorItem.Type.CHESTPLATE));
        event.registerLayerDefinition(WAR_MACHINE_LEGGINGS, () -> SuitModel.createWarMachineBodyLayer(ArmorItem.Type.LEGGINGS));
        event.registerLayerDefinition(WAR_MACHINE_BOOTS, () -> SuitModel.createWarMachineBodyLayer(ArmorItem.Type.BOOTS));
        event.registerLayerDefinition(DEADPOOL_HELMET, () -> SuitModel.createDeadpoolBodyLayer(ArmorItem.Type.HELMET));
        event.registerLayerDefinition(DEADPOOL_CHESTPLATE, () -> SuitModel.createDeadpoolBodyLayer(ArmorItem.Type.CHESTPLATE));
        event.registerLayerDefinition(DEADPOOL_LEGGINGS, () -> SuitModel.createDeadpoolBodyLayer(ArmorItem.Type.LEGGINGS));
        event.registerLayerDefinition(DEADPOOL_BOOTS, () -> SuitModel.createDeadpoolBodyLayer(ArmorItem.Type.BOOTS));
        event.registerLayerDefinition(CAPTAIN_MARVEL_HELMET, () -> SuitModel.createCaptainMarvelBodyLayer(ArmorItem.Type.HELMET));
        event.registerLayerDefinition(CAPTAIN_MARVEL_CHESTPLATE, () -> SuitModel.createCaptainMarvelBodyLayer(ArmorItem.Type.CHESTPLATE));
        event.registerLayerDefinition(CAPTAIN_MARVEL_LEGGINGS, () -> SuitModel.createCaptainMarvelBodyLayer(ArmorItem.Type.LEGGINGS));
        event.registerLayerDefinition(CAPTAIN_MARVEL_BOOTS, () -> SuitModel.createCaptainMarvelBodyLayer(ArmorItem.Type.BOOTS));
        event.registerLayerDefinition(WINTER_SOLDIER_HELMET, () -> SuitModel.createWinterSoldierBodyLayer(ArmorItem.Type.HELMET));
        event.registerLayerDefinition(WINTER_SOLDIER_CHESTPLATE, () -> SuitModel.createWinterSoldierBodyLayer(ArmorItem.Type.CHESTPLATE));
        event.registerLayerDefinition(WINTER_SOLDIER_LEGGINGS, () -> SuitModel.createWinterSoldierBodyLayer(ArmorItem.Type.LEGGINGS));
        event.registerLayerDefinition(WINTER_SOLDIER_BOOTS, () -> SuitModel.createWinterSoldierBodyLayer(ArmorItem.Type.BOOTS));
        event.registerLayerDefinition(VIBRANIUM_SHIELD, VibraniumShieldModel::createBodyLayer);
        event.registerLayerDefinition(MJOLNIR, MjolnirModel::createBodyLayer);
        event.registerLayerDefinition(STORMBREAKER, StormbreakerModel::createBodyLayer);
        event.registerLayerDefinition(REPULSOR, RepulsorModel::createBodyLayer);
        event.registerLayerDefinition(VERONICA, VeronicaModel::createBodyLayer);
    }

    public static ModelLayerLocation suit(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> SUIT_HELMET;
            case CHESTPLATE, BODY -> SUIT_CHESTPLATE;
            case LEGGINGS -> SUIT_LEGGINGS;
            case BOOTS -> SUIT_BOOTS;
        };
    }

    public static ModelLayerLocation pantherSuit(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> PANTHER_HELMET;
            case CHESTPLATE, BODY -> PANTHER_CHESTPLATE;
            case LEGGINGS -> PANTHER_LEGGINGS;
            case BOOTS -> PANTHER_BOOTS;
        };
    }

    public static ModelLayerLocation wolverineSuit(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> WOLVERINE_HELMET;
            case CHESTPLATE, BODY -> WOLVERINE_CHESTPLATE;
            case LEGGINGS -> WOLVERINE_LEGGINGS;
            case BOOTS -> WOLVERINE_BOOTS;
        };
    }

    public static ModelLayerLocation cyclopsSuit(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> CYCLOPS_HELMET;
            case CHESTPLATE, BODY -> CYCLOPS_CHESTPLATE;
            case LEGGINGS -> CYCLOPS_LEGGINGS;
            case BOOTS -> CYCLOPS_BOOTS;
        };
    }

    public static ModelLayerLocation spiderManSuit(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> SPIDER_MAN_HELMET;
            case CHESTPLATE, BODY -> SPIDER_MAN_CHESTPLATE;
            case LEGGINGS -> SPIDER_MAN_LEGGINGS;
            case BOOTS -> SPIDER_MAN_BOOTS;
        };
    }

    public static ModelLayerLocation waspSuit(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> WASP_HELMET;
            case CHESTPLATE, BODY -> WASP_CHESTPLATE;
            case LEGGINGS -> WASP_LEGGINGS;
            case BOOTS -> WASP_BOOTS;
        };
    }

    public static ModelLayerLocation thorSuit(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> THOR_HELMET;
            case CHESTPLATE, BODY -> THOR_CHESTPLATE;
            case LEGGINGS -> THOR_LEGGINGS;
            case BOOTS -> THOR_BOOTS;
        };
    }

    public static ModelLayerLocation ironManMark1Suit(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> IRON_MAN_MARK_1_HELMET;
            case CHESTPLATE, BODY -> IRON_MAN_MARK_1_CHESTPLATE;
            case LEGGINGS -> IRON_MAN_MARK_1_LEGGINGS;
            case BOOTS -> IRON_MAN_MARK_1_BOOTS;
        };
    }

    public static ModelLayerLocation ironManSuit(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> IRON_MAN_HELMET;
            case CHESTPLATE, BODY -> IRON_MAN_CHESTPLATE;
            case LEGGINGS -> IRON_MAN_LEGGINGS;
            case BOOTS -> IRON_MAN_BOOTS;
        };
    }

    public static ModelLayerLocation warMachineSuit(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> WAR_MACHINE_HELMET;
            case CHESTPLATE, BODY -> WAR_MACHINE_CHESTPLATE;
            case LEGGINGS -> WAR_MACHINE_LEGGINGS;
            case BOOTS -> WAR_MACHINE_BOOTS;
        };
    }

    public static ModelLayerLocation deadpoolSuit(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> DEADPOOL_HELMET;
            case CHESTPLATE, BODY -> DEADPOOL_CHESTPLATE;
            case LEGGINGS -> DEADPOOL_LEGGINGS;
            case BOOTS -> DEADPOOL_BOOTS;
        };
    }

    public static ModelLayerLocation captainMarvelSuit(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> CAPTAIN_MARVEL_HELMET;
            case CHESTPLATE, BODY -> CAPTAIN_MARVEL_CHESTPLATE;
            case LEGGINGS -> CAPTAIN_MARVEL_LEGGINGS;
            case BOOTS -> CAPTAIN_MARVEL_BOOTS;
        };
    }

    public static ModelLayerLocation winterSoldierSuit(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> WINTER_SOLDIER_HELMET;
            case CHESTPLATE, BODY -> WINTER_SOLDIER_CHESTPLATE;
            case LEGGINGS -> WINTER_SOLDIER_LEGGINGS;
            case BOOTS -> WINTER_SOLDIER_BOOTS;
        };
    }
}
