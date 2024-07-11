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
        event.registerSpecial(MarvelParticleTypes.KINETIC_BLAST_EMITTER.get(), new KineticBlastEmitterParticle.Provider());
        event.registerSpriteSet(MarvelParticleTypes.KINETIC_BLAST.get(), KineticBlastParticle.Provider::new);
        event.registerSpriteSet(MarvelParticleTypes.SPIDER_SENSE.get(), SpiderSenseParticle.Provider::new);
        event.registerSpriteSet(MarvelParticleTypes.EMISSIVE_FLAME.get(), EmissiveFlameParticle.Provider::new);
        event.registerSpriteSet(MarvelParticleTypes.IRON_MAN_FLAME.get(), IronManFlameParticle.Provider::new);
    }
}
