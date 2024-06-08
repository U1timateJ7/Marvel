package net.tintankgames.marvel.client.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.DustParticleBase;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tintankgames.marvel.core.particles.EmissiveDustParticleOptions;

public class EmissiveDustParticle extends DustParticleBase<EmissiveDustParticleOptions> {
    protected EmissiveDustParticle(
            ClientLevel p_106415_,
            double p_106416_,
            double p_106417_,
            double p_106418_,
            double p_106419_,
            double p_106420_,
            double p_106421_,
            EmissiveDustParticleOptions p_106422_,
            SpriteSet p_106423_
    ) {
        super(p_106415_, p_106416_, p_106417_, p_106418_, p_106419_, p_106420_, p_106421_, p_106422_, p_106423_);
        float f = this.random.nextFloat() * 0.4F + 0.6F;
        this.rCol = this.randomizeColor(p_106422_.getColor().x(), f);
        this.gCol = this.randomizeColor(p_106422_.getColor().y(), f);
        this.bCol = this.randomizeColor(p_106422_.getColor().z(), f);
        this.xd = 0F;
        this.yd = 0F;
        this.zd = 0F;
    }

    @Override
    public int getLightColor(float p_106821_) {
        return 0xFFFFFF;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<EmissiveDustParticleOptions> {
        private final SpriteSet sprites;

        public Provider(SpriteSet p_106441_) {
            this.sprites = p_106441_;
        }

        public Particle createParticle(
                EmissiveDustParticleOptions p_106443_,
                ClientLevel p_106444_,
                double p_106445_,
                double p_106446_,
                double p_106447_,
                double p_106448_,
                double p_106449_,
                double p_106450_
        ) {
            return new EmissiveDustParticle(p_106444_, p_106445_, p_106446_, p_106447_, p_106448_, p_106449_, p_106450_, p_106443_, this.sprites);
        }
    }
}
