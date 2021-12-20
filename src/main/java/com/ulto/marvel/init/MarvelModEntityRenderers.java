
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.ulto.marvel.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;

import com.ulto.marvel.client.renderer.SentryModeRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MarvelModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(MarvelModEntities.MJOLNIR, ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntities.CAPTAIN_AMERICAS_SHIELD_RED, ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntities.STORMBREAKER, ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntities.WEB_SHOOTER_SWING, ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntities.VIBRANIUM_SHIELD, ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntities.CAPTAIN_AMERICAS_SHIELD_BLUE, ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntities.RED_GUARDIAN_SHIELD, ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntities.TASKMASTER_SHIELD, ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntities.BLOODY_CAPTAIN_AMERICAS_SHIELD_RED, ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntities.BLOODY_VIBRANIUM_SHIELD, ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntities.BLOODY_CAPTAIN_AMERICAS_SHIELD_BLUE, ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntities.BLOODY_RED_GUARDIAN_SHIELD, ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntities.BLOODY_TASKMASTER_SHIELD, ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntities.SHRINKING_DISK, ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntities.GROWING_DISK, ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntities.SENTRY_MODE, SentryModeRenderer::new);
		event.registerEntityRenderer(MarvelModEntities.FLAMETHROWER, ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntities.REPULSOR, ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntities.UNIBEAM, ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntities.WAR_MACHINE_GUN, ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntities.WEB_SHOOTER_TRAP, ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntities.MARK_17_UNIBEAM, ThrownItemRenderer::new);
	}
}
