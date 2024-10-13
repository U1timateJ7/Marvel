package net.tintankgames.marvel.client.renderer.entity;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.client.renderer.blockentity.SuitChargerRenderer;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;
import net.tintankgames.marvel.world.level.block.entity.MarvelBlockEntityTypes;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = MarvelSuperheroes.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MarvelEntityRenderers {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(MarvelEntityTypes.HYDRA_AGENT.get(), HydraAgentRenderer::new);
        event.registerEntityRenderer(MarvelEntityTypes.BARON_ZEMO.get(), BaronZemoRenderer::new);
        event.registerEntityRenderer(MarvelEntityTypes.WINTER_SOLDIER.get(), WinterSoldierRenderer::new);
        event.registerEntityRenderer(MarvelEntityTypes.RED_SKULL.get(), RedSkullRenderer::new);
        event.registerEntityRenderer(MarvelEntityTypes.IRON_MAN_SENTRY.get(), IronManSentryRenderer::new);

        event.registerEntityRenderer(MarvelEntityTypes.VIBRANIUM_SHIELD.get(), ThrownVibraniumShieldRenderer::new);
        event.registerEntityRenderer(MarvelEntityTypes.WEB_SHOT.get(), WebShotRenderer::new);
        event.registerEntityRenderer(MarvelEntityTypes.WASP_STING.get(), WaspStingRenderer::new);
        event.registerEntityRenderer(MarvelEntityTypes.MJOLNIR.get(), ThrownMjolnirRenderer::new);
        event.registerEntityRenderer(MarvelEntityTypes.STORMBREAKER.get(), ThrownStormbreakerRenderer::new);
        event.registerEntityRenderer(MarvelEntityTypes.FLAME.get(), FlameRenderer::new);
        event.registerEntityRenderer(MarvelEntityTypes.REPULSOR.get(), RepulsorRenderer::new);
        event.registerEntityRenderer(MarvelEntityTypes.TESSERACT_CHARGE.get(), TesseractChargeRenderer::new);
        event.registerEntityRenderer(MarvelEntityTypes.VERONICA_SATELLITE.get(), VeronicaSatelliteRenderer::new);
        event.registerEntityRenderer(MarvelEntityTypes.VERONICA_REPAIR_MODULE_MARK_1.get(), VeronicaRepairModuleMark1Renderer::new);
        event.registerEntityRenderer(MarvelEntityTypes.VERONICA_REPAIR_MODULE_MARK_2.get(), VeronicaRepairModuleMark2Renderer::new);

        event.registerBlockEntityRenderer(MarvelBlockEntityTypes.SUIT_CHARGER.get(), SuitChargerRenderer::new);
    }
}
