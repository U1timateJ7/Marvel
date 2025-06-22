package net.tintankgames.marvel.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.FlyStraightTowardsParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.AABB;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class QuicksilverParticle extends FlyStraightTowardsParticle {
    protected QuicksilverParticle(ClientLevel p_338359_, double p_338512_, double p_338787_, double p_338665_, double p_338833_, double p_338537_, double p_338840_, int p_338764_, int p_338316_) {
        super(p_338359_, p_338512_, p_338787_, p_338665_, p_338833_, p_338537_, p_338840_, p_338764_, p_338316_);
    }

    @Override
    public AABB getRenderBoundingBox(float partialTicks) {
        return AABB.INFINITE;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet p_338292_) {
            this.sprite = p_338292_;
        }

        public Particle createParticle(
                SimpleParticleType p_338365_,
                ClientLevel p_338448_,
                double p_338829_,
                double p_338561_,
                double p_338765_,
                double p_338694_,
                double p_338802_,
                double p_338768_
        ) {
            QuicksilverParticle quicksilverParticle = new QuicksilverParticle(p_338448_, p_338829_, p_338561_, p_338765_, p_338694_, p_338802_, p_338768_, -12210434, -1);
            quicksilverParticle.scale(Mth.randomBetween(p_338448_.getRandom(), 3.0F, 5.0F));
            quicksilverParticle.pickSprite(this.sprite);
            return quicksilverParticle;
        }
    }
}
