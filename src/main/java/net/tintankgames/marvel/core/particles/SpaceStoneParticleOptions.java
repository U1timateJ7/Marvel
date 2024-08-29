package net.tintankgames.marvel.core.particles;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public class SpaceStoneParticleOptions implements ParticleOptions {
    private final ParticleType<SpaceStoneParticleOptions> type;
    private final float speed;
    private final float rotation;

    public static MapCodec<SpaceStoneParticleOptions> codec(ParticleType<SpaceStoneParticleOptions> type) {
        return RecordCodecBuilder.mapCodec(instance -> instance.group(Codec.FLOAT.fieldOf("speed").forGetter(SpaceStoneParticleOptions::speed), Codec.FLOAT.fieldOf("rotation").forGetter(SpaceStoneParticleOptions::rotation)).apply(instance, (speed, rotation) -> new SpaceStoneParticleOptions(type, speed, rotation)));
    }

    public static StreamCodec<? super RegistryFriendlyByteBuf, SpaceStoneParticleOptions> streamCodec(ParticleType<SpaceStoneParticleOptions> type) {
        return StreamCodec.composite(ByteBufCodecs.FLOAT, SpaceStoneParticleOptions::speed, ByteBufCodecs.FLOAT, SpaceStoneParticleOptions::rotation, (speed, rotation) -> new SpaceStoneParticleOptions(type, speed, rotation));
    }

    public SpaceStoneParticleOptions(ParticleType<SpaceStoneParticleOptions> type, float speed, float rotation) {
        this.type = type;
        this.speed = speed;
        this.rotation = rotation;
    }

    @Override
    public ParticleType<SpaceStoneParticleOptions> getType() {
        return type;
    }

    public float speed() {
        return speed;
    }

    public float rotation() {
        return rotation;
    }
}
