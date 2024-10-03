package net.tintankgames.marvel.client.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.NoRenderParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.ColorParticleOption;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tintankgames.marvel.core.particles.MarvelParticleTypes;

@OnlyIn(Dist.CLIENT)
public class KineticBlastEmitterParticle extends NoRenderParticle {
    private final ColorParticleOption option;

    KineticBlastEmitterParticle(ColorParticleOption option, ClientLevel p_106947_, double p_106948_, double p_106949_, double p_106950_) {
        super(p_106947_, p_106948_, p_106949_, p_106950_, 0.0, 0.0, 0.0);
        this.lifetime = 8;
        this.option = option;
    }

    @Override
    public void tick() {
        for (int i = 0; i < 6; i++) {
            double d0 = this.x + (this.random.nextDouble() - this.random.nextDouble()) * 4.0;
            double d1 = this.y + (this.random.nextDouble() - this.random.nextDouble()) * 4.0;
            double d2 = this.z + (this.random.nextDouble() - this.random.nextDouble()) * 4.0;
            this.level.addParticle(ColorParticleOption.create(MarvelParticleTypes.KINETIC_BLAST.get(), option.getRed(), option.getGreen(), option.getBlue()), d0, d1, d2, (float)this.age / (float)this.lifetime, 0.0, 0.0);
        }

        this.age++;
        if (this.age == this.lifetime) {
            this.remove();
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<ColorParticleOption> {
        public Particle createParticle(
                ColorParticleOption p_106969_,
                ClientLevel p_106970_,
                double p_106971_,
                double p_106972_,
                double p_106973_,
                double p_106974_,
                double p_106975_,
                double p_106976_
        ) {
            return new KineticBlastEmitterParticle(p_106969_, p_106970_, p_106971_, p_106972_, p_106973_);
        }
    }
}
