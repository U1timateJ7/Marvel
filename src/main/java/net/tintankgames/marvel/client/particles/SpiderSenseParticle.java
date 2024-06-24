package net.tintankgames.marvel.client.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class SpiderSenseParticle extends TextureSheetParticle {
    protected SpiderSenseParticle(ClientLevel p_106905_, double p_106906_, double p_106907_, double p_106908_, double p_106909_, SpriteSet p_106910_) {
        super(p_106905_, p_106906_, p_106907_, p_106908_, 0.0, 0.0, 0.0);
        this.lifetime = 20;
        this.alpha = 0.6F;
        this.quadSize = 0.75F;
        this.setSpriteFromAge(p_106910_);
    }

    @Override
    public int getLightColor(float p_106921_) {
        return 15728880;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet p_106925_) {
            this.sprites = p_106925_;
        }

        public Particle createParticle(
                SimpleParticleType p_106936_,
                ClientLevel p_106937_,
                double p_106938_,
                double p_106939_,
                double p_106940_,
                double p_106941_,
                double p_106942_,
                double p_106943_
        ) {
            return new SpiderSenseParticle(p_106937_, p_106938_, p_106939_, p_106940_, p_106941_, this.sprites);
        }
    }
}
