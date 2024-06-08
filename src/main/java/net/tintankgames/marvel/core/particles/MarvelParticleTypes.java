package net.tintankgames.marvel.core.particles;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
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

    private static <T extends ParticleOptions> DeferredHolder<ParticleType<?>, ParticleType<T>> register(String id, Supplier<ParticleType<T>> supplier) {
        return REGISTER.register(id, supplier);
    }

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }
}
