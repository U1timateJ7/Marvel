package net.tintankgames.marvel.client.particles;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.core.particles.MarvelParticleTypes;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = MarvelSuperheroes.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MarvelParticles {
    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(MarvelParticleTypes.EMISSIVE_DUST.get(), EmissiveDustParticle.Provider::new);
    }
}
