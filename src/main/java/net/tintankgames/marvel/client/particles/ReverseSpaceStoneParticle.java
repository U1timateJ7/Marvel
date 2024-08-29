package net.tintankgames.marvel.client.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.util.FastColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tintankgames.marvel.core.particles.SpeedParticleOptions;

public class ReverseSpaceStoneParticle extends SimpleAnimatedParticle {
    private final double xStart;
    private final double yStart;
    private final double zStart;
    private float fadeR;
    private float fadeG;
    private float fadeB;

    protected ReverseSpaceStoneParticle(SpeedParticleOptions options, ClientLevel p_172094_, double p_172095_, double p_172096_, double p_172097_, double p_172098_, double p_172099_, double p_172100_, SpriteSet p_172102_) {
        super(p_172094_, p_172095_, p_172096_, p_172097_, p_172102_, 0.0F);
        this.xd = p_172098_;
        this.yd = p_172099_;
        this.zd = p_172100_;
        this.x = p_172095_;
        this.y = p_172096_;
        this.z = p_172097_;
        this.xStart = this.x;
        this.yStart = this.y;
        this.zStart = this.z;
        this.quadSize = 0.2F * (this.random.nextFloat() * 0.2F + 0.5F);
        this.lifetime = (int) ((Math.random() * 5.0 + 20) / options.speed());
        float f = this.random.nextFloat() * 0.4F + 0.6F;
        int color = FastColor.ARGB32.colorFromFloat(1.0F, this.randomizeColor(FastColor.ARGB32.red(0x595959) / 255F, f), this.randomizeColor(FastColor.ARGB32.green(0x595959) / 255F, f), this.randomizeColor(FastColor.ARGB32.blue(0x595959) / 255F, f));
        setColor(color);
        int fadeColor = FastColor.ARGB32.colorFromFloat(1.0F, this.randomizeColor(FastColor.ARGB32.red(0x0992DB) / 255F, f), this.randomizeColor(FastColor.ARGB32.green(0x0992DB) / 255F, f), this.randomizeColor(FastColor.ARGB32.blue(0x0992DB) / 255F, f));
        setFadeColor(fadeColor);
        this.setSpriteFromAge(p_172102_);
    }

    public void setFadeColor(int p_107660_) {
        this.fadeR = (float)((p_107660_ & 0xFF0000) >> 16) / 255.0F;
        this.fadeG = (float)((p_107660_ & 0xFF00) >> 8) / 255.0F;
        this.fadeB = (float)((p_107660_ & 0xFF)) / 255.0F;
    }

    @Override
    public void move(double p_107560_, double p_107561_, double p_107562_) {
        this.setBoundingBox(this.getBoundingBox().move(p_107560_, p_107561_, p_107562_));
        this.setLocationFromBoundingbox();
    }

    @Override
    public float getQuadSize(float p_107567_) {
        int inverseAge = -(this.age - this.lifetime);
        float f = ((float)inverseAge + p_107567_) / (float)this.lifetime;
        f = 1.0F - f;
        f *= f;
        f = 1.0F - f;
        return this.quadSize * f;
    }

    @Override
    public void tick() {
        int inverseAge = -(this.age - this.lifetime);
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            float f = (float)inverseAge / (float)this.lifetime;
            float f1 = -f + f * f * 2.0F;
            float f2 = 1.0F - f1;
            this.x = this.xStart + this.xd * (double)f2;
            this.y = this.yStart + this.yd * (double)f2;
            this.z = this.zStart + this.zd * (double)f2;
            this.setPos(this.x, this.y, this.z);
        }
        this.setSpriteFromAge(this.sprites);
        if (inverseAge > this.lifetime / 2) {
            this.setAlpha(1.0F - ((float)inverseAge - (float)(this.lifetime / 2)) / (float)this.lifetime);
        }
        if (this.age > this.lifetime / 2) {
            this.rCol = this.rCol + (this.fadeR - this.rCol) * 0.4F;
            this.gCol = this.gCol + (this.fadeG - this.gCol) * 0.4F;
            this.bCol = this.bCol + (this.fadeB - this.bCol) * 0.4F;
        }
    }

    protected float randomizeColor(float p_172105_, float p_172106_) {
        return p_172105_ * p_172106_;
    }

    @Override
    public int getLightColor(float p_106821_) {
        return 240;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SpeedParticleOptions> {
        private final SpriteSet sprites;

        public Provider(SpriteSet p_106441_) {
            this.sprites = p_106441_;
        }

        public Particle createParticle(SpeedParticleOptions p_106443_, ClientLevel p_106444_, double p_106445_, double p_106446_, double p_106447_, double p_106448_, double p_106449_, double p_106450_) {
            return new ReverseSpaceStoneParticle(p_106443_, p_106444_, p_106445_, p_106446_, p_106447_, p_106448_, p_106449_, p_106450_, this.sprites);
        }
    }
}
