package net.tintankgames.marvel.client.renderer.entity;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = MarvelSuperheroes.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MarvelEntityRenderers {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(MarvelEntityTypes.VIBRANIUM_SHIELD.get(), ThrownVibraniumShieldRenderer::new);
        event.registerEntityRenderer(MarvelEntityTypes.WEB_SHOT.get(), WebShotRenderer::new);
        event.registerEntityRenderer(MarvelEntityTypes.WASP_STING.get(), WaspStingRenderer::new);
        event.registerEntityRenderer(MarvelEntityTypes.MJOLNIR.get(), ThrownMjolnirRenderer::new);
        event.registerEntityRenderer(MarvelEntityTypes.STORMBREAKER.get(), ThrownStormbreakerRenderer::new);
    }
}
