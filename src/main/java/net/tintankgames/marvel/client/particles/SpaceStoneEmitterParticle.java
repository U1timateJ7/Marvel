package net.tintankgames.marvel.client.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.NoRenderParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tintankgames.marvel.core.particles.MarvelParticleTypes;
import net.tintankgames.marvel.core.particles.SpaceStoneParticleOptions;
import net.tintankgames.marvel.core.particles.SpeedParticleOptions;

@OnlyIn(Dist.CLIENT)
public class SpaceStoneEmitterParticle extends NoRenderParticle {
    private final SpaceStoneParticleOptions options;

    SpaceStoneEmitterParticle(SpaceStoneParticleOptions options, ClientLevel p_106947_, double p_106948_, double p_106949_, double p_106950_) {
        super(p_106947_, p_106948_, p_106949_, p_106950_);
        this.options = options;
        this.xd = 0;
        this.yd = 0;
        this.zd = 0;
        this.lifetime = (int) (30 / options.speed());
    }

    @Override
    public void tick() {
        for (int j = 0; j < 128; j++) {
            double d0 = (double) j / 127.0;
            double d1 = x;
            double d2 = y;
            double d3 = z;
            Vec3 radius = new Vec3(Math.sin(Math.PI * d0 * 2), Math.cos(Math.PI * d0 * 2), 0);
            radius = radius.yRot(options.rotation() * -Mth.DEG_TO_RAD);
            this.level.addAlwaysVisibleParticle(new SpeedParticleOptions(MarvelParticleTypes.SPACE_STONE.get(), options.speed()), d1, d2, d3, radius.x(), radius.y(), radius.z());
        }

        this.age++;
        if (this.age == this.lifetime) {
            this.remove();
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SpaceStoneParticleOptions> {
        public Particle createParticle(SpaceStoneParticleOptions p_106969_, ClientLevel p_106970_, double p_106971_, double p_106972_, double p_106973_, double p_106974_, double p_106975_, double p_106976_) {
            return new SpaceStoneEmitterParticle(p_106969_, p_106970_, p_106971_, p_106972_, p_106973_);
        }
    }
}
