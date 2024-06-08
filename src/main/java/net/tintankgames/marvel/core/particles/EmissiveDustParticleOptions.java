package net.tintankgames.marvel.core.particles;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ScalableParticleOptionsBase;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import org.joml.Vector3f;

public class EmissiveDustParticleOptions extends ScalableParticleOptionsBase {
    public static final MapCodec<EmissiveDustParticleOptions> CODEC = RecordCodecBuilder.mapCodec(p_341566_ -> p_341566_.group(ExtraCodecs.VECTOR3F.fieldOf("color").forGetter(p_253371_ -> p_253371_.color), SCALE.fieldOf("scale").forGetter(ScalableParticleOptionsBase::getScale)).apply(p_341566_, EmissiveDustParticleOptions::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, EmissiveDustParticleOptions> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.VECTOR3F, p_319429_ -> p_319429_.color, ByteBufCodecs.FLOAT, ScalableParticleOptionsBase::getScale, EmissiveDustParticleOptions::new);
    private final Vector3f color;

    public EmissiveDustParticleOptions(Vector3f p_253868_, float p_254154_) {
        super(p_254154_);
        this.color = p_253868_;
    }

    @Override
    public ParticleType<EmissiveDustParticleOptions> getType() {
        return MarvelParticleTypes.EMISSIVE_DUST.get();
    }

    public Vector3f getColor() {
        return this.color;
    }
}
