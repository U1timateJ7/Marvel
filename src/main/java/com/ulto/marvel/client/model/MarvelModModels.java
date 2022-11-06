
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.ulto.marvel.client.model;

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
		event.registerLayerDefinition(ModelHelmet.LAYER_LOCATION, ModelHelmet::createHead32Layer);
		event.registerLayerDefinition(ModelIronSpider.LAYER_LOCATION, ModelIronSpider::createBodyLayer);
		event.registerLayerDefinition(ModelCostume.LAYER_LOCATION, ModelCostume::createBodyLayer);
		event.registerLayerDefinition(ModelTaskmasterHelmet.LAYER_LOCATION, ModelTaskmasterHelmet::createBodyLayer);
		event.registerLayerDefinition(ModelWarMachine.LAYER_LOCATION, ModelWarMachine::createBodyLayer);
		event.registerLayerDefinition(ModelCenturion.LAYER_LOCATION, ModelCenturion::createBodyLayer);
		event.registerLayerDefinition(IronManCharger.LAYER_LOCATION, IronManCharger::createBodyLayer);
		event.registerLayerDefinition(IronManCharger.Battery25.LAYER_LOCATION, IronManCharger.Battery25::createBodyLayer);
		event.registerLayerDefinition(IronManCharger.Battery50.LAYER_LOCATION, IronManCharger.Battery50::createBodyLayer);
		event.registerLayerDefinition(IronManCharger.Battery75.LAYER_LOCATION, IronManCharger.Battery75::createBodyLayer);
		event.registerLayerDefinition(IronManCharger.Battery100.LAYER_LOCATION, IronManCharger.Battery100::createBodyLayer);
	}
}
