package net.tintankgames.marvel.core.particles;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public class SpeedParticleOptions implements ParticleOptions {
    private final ParticleType<SpeedParticleOptions> type;
    private final float speed;

    public static MapCodec<SpeedParticleOptions> codec(ParticleType<SpeedParticleOptions> type) {
        return Codec.FLOAT.xmap(speed -> new SpeedParticleOptions(type, speed), SpeedParticleOptions::speed).fieldOf("speed");
    }

    public static StreamCodec<? super RegistryFriendlyByteBuf, SpeedParticleOptions> streamCodec(ParticleType<SpeedParticleOptions> type) {
        return ByteBufCodecs.FLOAT.map(speed -> new SpeedParticleOptions(type, speed), SpeedParticleOptions::speed);
    }

    public SpeedParticleOptions(ParticleType<SpeedParticleOptions> type, float speed) {
        this.type = type;
        this.speed = speed;
    }

    @Override
    public ParticleType<SpeedParticleOptions> getType() {
        return type;
    }

    public float speed() {
        return speed;
    }
}
