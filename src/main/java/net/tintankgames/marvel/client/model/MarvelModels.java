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
    public static final ModelLayerLocation SUIT_EMPTY = new ModelLayerLocation(MarvelSuperheroes.id("suit_empty"), "main");
    public static final ModelLayerLocation SUIT_HELMET = new ModelLayerLocation(MarvelSuperheroes.id("suit_helmet"), "main");
    public static final ModelLayerLocation SUIT_CHESTPLATE = new ModelLayerLocation(MarvelSuperheroes.id("suit_chestplate"), "main");
    public static final ModelLayerLocation SUIT_LEGGINGS = new ModelLayerLocation(MarvelSuperheroes.id("suit_leggings"), "main");
    public static final ModelLayerLocation SUIT_BOOTS = new ModelLayerLocation(MarvelSuperheroes.id("suit_boots"), "main");
    public static final ModelLayerLocation PANTHER_SUIT_HELMET = new ModelLayerLocation(MarvelSuperheroes.id("panther_suit_helmet"), "main");
    public static final ModelLayerLocation PANTHER_SUIT_CHESTPLATE = new ModelLayerLocation(MarvelSuperheroes.id("panther_suit_chestplate"), "main");
    public static final ModelLayerLocation PANTHER_SUIT_LEGGINGS = new ModelLayerLocation(MarvelSuperheroes.id("panther_suit_leggings"), "main");
    public static final ModelLayerLocation PANTHER_SUIT_BOOTS = new ModelLayerLocation(MarvelSuperheroes.id("panther_suit_boots"), "main");
    public static final ModelLayerLocation WOLVERINE_SUIT_HELMET = new ModelLayerLocation(MarvelSuperheroes.id("wolverine_suit_helmet"), "main");
    public static final ModelLayerLocation WOLVERINE_SUIT_CHESTPLATE = new ModelLayerLocation(MarvelSuperheroes.id("wolverine_suit_chestplate"), "main");
    public static final ModelLayerLocation WOLVERINE_SUIT_LEGGINGS = new ModelLayerLocation(MarvelSuperheroes.id("wolverine_suit_leggings"), "main");
    public static final ModelLayerLocation WOLVERINE_SUIT_BOOTS = new ModelLayerLocation(MarvelSuperheroes.id("wolverine_suit_boots"), "main");
    public static final ModelLayerLocation CYCLOPS_SUIT_HELMET = new ModelLayerLocation(MarvelSuperheroes.id("cyclops_suit_helmet"), "main");
    public static final ModelLayerLocation CYCLOPS_SUIT_CHESTPLATE = new ModelLayerLocation(MarvelSuperheroes.id("cyclops_suit_chestplate"), "main");
    public static final ModelLayerLocation CYCLOPS_SUIT_LEGGINGS = new ModelLayerLocation(MarvelSuperheroes.id("cyclops_suit_leggings"), "main");
    public static final ModelLayerLocation CYCLOPS_SUIT_BOOTS = new ModelLayerLocation(MarvelSuperheroes.id("cyclops_suit_boots"), "main");
    public static final ModelLayerLocation VIBRANIUM_SHIELD = new ModelLayerLocation(MarvelSuperheroes.id("vibranium_shield"), "main");

    @SubscribeEvent
    public static void registerModels(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SUIT_EMPTY, SuitModel::createBodyLayer);
        event.registerLayerDefinition(SUIT_HELMET, () -> SuitModel.createBodyLayer(ArmorItem.Type.HELMET));
        event.registerLayerDefinition(SUIT_CHESTPLATE, () -> SuitModel.createBodyLayer(ArmorItem.Type.CHESTPLATE));
        event.registerLayerDefinition(SUIT_LEGGINGS, () -> SuitModel.createBodyLayer(ArmorItem.Type.LEGGINGS));
        event.registerLayerDefinition(SUIT_BOOTS, () -> SuitModel.createBodyLayer(ArmorItem.Type.BOOTS));
        event.registerLayerDefinition(PANTHER_SUIT_HELMET, () -> SuitModel.createPantherBodyLayer(ArmorItem.Type.HELMET));
        event.registerLayerDefinition(PANTHER_SUIT_CHESTPLATE, () -> SuitModel.createPantherBodyLayer(ArmorItem.Type.CHESTPLATE));
        event.registerLayerDefinition(PANTHER_SUIT_LEGGINGS, () -> SuitModel.createPantherBodyLayer(ArmorItem.Type.LEGGINGS));
        event.registerLayerDefinition(PANTHER_SUIT_BOOTS, () -> SuitModel.createPantherBodyLayer(ArmorItem.Type.BOOTS));
        event.registerLayerDefinition(WOLVERINE_SUIT_HELMET, () -> SuitModel.createWolverineBodyLayer(ArmorItem.Type.HELMET));
        event.registerLayerDefinition(WOLVERINE_SUIT_CHESTPLATE, () -> SuitModel.createWolverineBodyLayer(ArmorItem.Type.CHESTPLATE));
        event.registerLayerDefinition(WOLVERINE_SUIT_LEGGINGS, () -> SuitModel.createWolverineBodyLayer(ArmorItem.Type.LEGGINGS));
        event.registerLayerDefinition(WOLVERINE_SUIT_BOOTS, () -> SuitModel.createWolverineBodyLayer(ArmorItem.Type.BOOTS));
        event.registerLayerDefinition(CYCLOPS_SUIT_HELMET, () -> SuitModel.createCyclopsBodyLayer(ArmorItem.Type.HELMET));
        event.registerLayerDefinition(CYCLOPS_SUIT_CHESTPLATE, () -> SuitModel.createCyclopsBodyLayer(ArmorItem.Type.CHESTPLATE));
        event.registerLayerDefinition(CYCLOPS_SUIT_LEGGINGS, () -> SuitModel.createCyclopsBodyLayer(ArmorItem.Type.LEGGINGS));
        event.registerLayerDefinition(CYCLOPS_SUIT_BOOTS, () -> SuitModel.createCyclopsBodyLayer(ArmorItem.Type.BOOTS));
        event.registerLayerDefinition(VIBRANIUM_SHIELD, VibraniumShieldModel::createBodyLayer);
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
            case HELMET -> PANTHER_SUIT_HELMET;
            case CHESTPLATE, BODY -> PANTHER_SUIT_CHESTPLATE;
            case LEGGINGS -> PANTHER_SUIT_LEGGINGS;
            case BOOTS -> PANTHER_SUIT_BOOTS;
        };
    }

    public static ModelLayerLocation wolverineSuit(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> WOLVERINE_SUIT_HELMET;
            case CHESTPLATE, BODY -> WOLVERINE_SUIT_CHESTPLATE;
            case LEGGINGS -> WOLVERINE_SUIT_LEGGINGS;
            case BOOTS -> WOLVERINE_SUIT_BOOTS;
        };
    }

    public static ModelLayerLocation cyclopsSuit(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> CYCLOPS_SUIT_HELMET;
            case CHESTPLATE, BODY -> CYCLOPS_SUIT_CHESTPLATE;
            case LEGGINGS -> CYCLOPS_SUIT_LEGGINGS;
            case BOOTS -> CYCLOPS_SUIT_BOOTS;
        };
    }
}
