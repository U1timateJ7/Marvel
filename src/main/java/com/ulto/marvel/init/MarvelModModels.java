
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.ulto.marvel.init;

import com.ulto.marvel.client.model.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class MarvelModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModelWolverineHelmet.LAYER_LOCATION, ModelWolverineHelmet::createBodyLayer);
		event.registerLayerDefinition(ModelBodyCape.LAYER_LOCATION, ModelBodyCape::createBodyLayer);
		event.registerLayerDefinition(ModelHelmet.LAYER_LOCATION, ModelHelmet::createBodyLayer);
		event.registerLayerDefinition(ModelIronSpider.LAYER_LOCATION, ModelIronSpider::createBodyLayer);
		event.registerLayerDefinition(ModelCostume.LAYER_LOCATION, ModelCostume::createBodyLayer);
		event.registerLayerDefinition(ModelTaskmasterHelmet.LAYER_LOCATION, ModelTaskmasterHelmet::createBodyLayer);
		event.registerLayerDefinition(ModelWarMachine.LAYER_LOCATION, ModelWarMachine::createBodyLayer);
		event.registerLayerDefinition(ModelCenturion.LAYER_LOCATION, ModelCenturion::createBodyLayer);
	}
}
