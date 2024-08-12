package net.tintankgames.marvel.core.particles;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ScalableParticleOptionsBase;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;

public class EmissiveDustParticleOptions extends ScalableParticleOptionsBase {
    public static final MapCodec<EmissiveDustParticleOptions> CODEC = RecordCodecBuilder.mapCodec(p_341566_ -> p_341566_.group(SimpleWeightedRandomList.wrappedCodec(ExtraCodecs.NON_NEGATIVE_INT).fieldOf("color").forGetter(p_253371_ -> p_253371_.color), SCALE.fieldOf("scale").forGetter(EmissiveDustParticleOptions::getScale), Codec.floatRange(0.01F, Float.MAX_VALUE).optionalFieldOf("lifetime", 1.0F).forGetter(EmissiveDustParticleOptions::getLifetime)).apply(p_341566_, EmissiveDustParticleOptions::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, EmissiveDustParticleOptions> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.fromCodec(SimpleWeightedRandomList.wrappedCodec(ExtraCodecs.NON_NEGATIVE_INT)), p_319429_ -> p_319429_.color, ByteBufCodecs.FLOAT, EmissiveDustParticleOptions::getScale, EmissiveDustParticleOptions::new);
    private final SimpleWeightedRandomList<Integer> color;
    private final float lifetime;

    public EmissiveDustParticleOptions(int p_253868_, float p_254154_) {
        this(SimpleWeightedRandomList.single(p_253868_), p_254154_);
    }

    public EmissiveDustParticleOptions(SimpleWeightedRandomList<Integer> p_253868_, float p_254154_) {
        this(p_253868_, p_254154_, 1);
    }

    public EmissiveDustParticleOptions(SimpleWeightedRandomList<Integer> p_253868_, float p_254154_, float lifetime) {
        super(p_254154_);
        this.color = p_253868_;
        this.lifetime = lifetime;
    }

    @Override
    public ParticleType<EmissiveDustParticleOptions> getType() {
        return MarvelParticleTypes.EMISSIVE_DUST.get();
    }

    public int getColor(RandomSource randomSource) {
        return this.color.getRandomValue(randomSource).orElse(-1);
    }

    public float getLifetime() {
        return lifetime;
    }
}
