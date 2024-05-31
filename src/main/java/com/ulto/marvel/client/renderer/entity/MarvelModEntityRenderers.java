package com.ulto.marvel.client.renderer.entity;

import com.ulto.marvel.client.renderer.blockentity.IronManNanotechSuitChargerRenderer;
import com.ulto.marvel.client.renderer.blockentity.IronManSuitChargerRenderer;
import com.ulto.marvel.world.entity.MarvelModEntityTypes;
import com.ulto.marvel.world.level.block.entity.MarvelModBlockEntityTypes;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MarvelModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(MarvelModEntityTypes.MJOLNIR.get(), MjolnirRenderer::new);
		event.registerEntityRenderer(MarvelModEntityTypes.WEB_SHOOTER_SWING.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntityTypes.STORMBREAKER.get(), StormbreakerRenderer::new);
		event.registerEntityRenderer(MarvelModEntityTypes.VIBRANIUM_SHIELD.get(), VibraniumShieldRenderer::new);
		event.registerEntityRenderer(MarvelModEntityTypes.SHRINKING_DISK.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntityTypes.GROWING_DISK.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntityTypes.SENTRY_MODE.get(), SentryModeRenderer::new);
		event.registerEntityRenderer(MarvelModEntityTypes.FLAMETHROWER.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntityTypes.REPULSOR.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntityTypes.UNIBEAM.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntityTypes.WAR_MACHINE_GUN.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntityTypes.WEB_SHOOTER_TRAP.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(MarvelModEntityTypes.MARK_17_UNIBEAM.get(), ThrownItemRenderer::new);
		event.registerBlockEntityRenderer(MarvelModBlockEntityTypes.IRON_MAN_SUIT_CHARGER.get(), IronManSuitChargerRenderer::new);
		event.registerBlockEntityRenderer(MarvelModBlockEntityTypes.IRON_MAN_NANOTECH_SUIT_CHARGER.get(), IronManNanotechSuitChargerRenderer::new);
	}
}
