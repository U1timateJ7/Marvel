package net.tintankgames.marvel.core.particles;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tintankgames.marvel.MarvelSuperheroes;

import java.util.function.Supplier;

public class MarvelParticleTypes {
    private static final DeferredRegister<ParticleType<?>> REGISTER = DeferredRegister.create(Registries.PARTICLE_TYPE, MarvelSuperheroes.MOD_ID);

    public static final DeferredHolder<ParticleType<?>, ParticleType<EmissiveDustParticleOptions>> EMISSIVE_DUST = register("emissive_dust", () -> new ParticleType<>(false) {
        @Override
        public MapCodec<EmissiveDustParticleOptions> codec() {
            return EmissiveDustParticleOptions.CODEC;
        }

        @Override
        public StreamCodec<? super RegistryFriendlyByteBuf, EmissiveDustParticleOptions> streamCodec() {
            return EmissiveDustParticleOptions.STREAM_CODEC;
        }
    });
    public static final DeferredHolder<ParticleType<?>, ParticleType<ColorParticleOption>> KINETIC_BLAST_EMITTER = register("kinetic_blast_emitter", () -> new ParticleType<>(true) {
        @Override
        public MapCodec<ColorParticleOption> codec() {
            return ColorParticleOption.codec(KINETIC_BLAST_EMITTER.get());
        }

        @Override
        public StreamCodec<? super RegistryFriendlyByteBuf, ColorParticleOption> streamCodec() {
            return ColorParticleOption.streamCodec(KINETIC_BLAST_EMITTER.get());
        }
    });
    public static final DeferredHolder<ParticleType<?>, ParticleType<ColorParticleOption>> KINETIC_BLAST = register("kinetic_blast", () -> new ParticleType<>(true) {
        @Override
        public MapCodec<ColorParticleOption> codec() {
            return ColorParticleOption.codec(KINETIC_BLAST.get());
        }

        @Override
        public StreamCodec<? super RegistryFriendlyByteBuf, ColorParticleOption> streamCodec() {
            return ColorParticleOption.streamCodec(KINETIC_BLAST.get());
        }
    });

    private static <T extends ParticleOptions> DeferredHolder<ParticleType<?>, ParticleType<T>> register(String id, Supplier<ParticleType<T>> supplier) {
        return REGISTER.register(id, supplier);
    }

    private static DeferredHolder<ParticleType<?>, SimpleParticleType> register(String id, boolean overrideLimiter) {
        return REGISTER.register(id, () -> new SimpleParticleType(overrideLimiter));
    }

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }
}
