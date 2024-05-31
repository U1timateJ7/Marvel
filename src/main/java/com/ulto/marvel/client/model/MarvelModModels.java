package com.ulto.marvel.client.model;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class MarvelModModels {
	public static final ModelLayerLocation WOLVERINE_HELMET = new ModelLayerLocation(new ResourceLocation("marvel", "wolverine_helmet"), "main");
	public static final ModelLayerLocation BODY_WITH_CAPE = new ModelLayerLocation(new ResourceLocation("marvel", "body_with_cape"), "main");
	public static final ModelLayerLocation HELMET = new ModelLayerLocation(new ResourceLocation("marvel", "helmet"), "main");
	public static final ModelLayerLocation IRON_SPIDER = new ModelLayerLocation(new ResourceLocation("marvel", "iron_spider"), "main");
	public static final ModelLayerLocation COSTUME = new ModelLayerLocation(new ResourceLocation("marvel", "costume"), "main");
	public static final ModelLayerLocation TASKMASTER_HELMET = new ModelLayerLocation(new ResourceLocation("marvel", "taskmaster_helmet"), "main");
	public static final ModelLayerLocation WAR_MACHINE = new ModelLayerLocation(new ResourceLocation("marvel", "war_machine"), "main");
	public static final ModelLayerLocation CENTURION = new ModelLayerLocation(new ResourceLocation("marvel", "centurion"), "main");

	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(WOLVERINE_HELMET, WolverineHelmetModel::createBodyLayer);
		event.registerLayerDefinition(BODY_WITH_CAPE, BodyWithCapeModel::createBodyLayer);
		event.registerLayerDefinition(HELMET, HelmetModel::createHead32Layer);
		event.registerLayerDefinition(IRON_SPIDER, IronSpiderModel::createBodyLayer);
		event.registerLayerDefinition(COSTUME, CostumeModel::createBodyLayer);
		event.registerLayerDefinition(TASKMASTER_HELMET, TaskmasterHelmetModel::createBodyLayer);
		event.registerLayerDefinition(WAR_MACHINE, WarMachineModel::createBodyLayer);
		event.registerLayerDefinition(CENTURION, CenturionModel::createBodyLayer);
	}
}
