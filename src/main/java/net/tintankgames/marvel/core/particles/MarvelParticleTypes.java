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
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPIDER_SENSE = register("spider_sense", true);
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> EMISSIVE_FLAME = register("emissive_flame", true);
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> IRON_MAN_FLAME = register("iron_man_flame", true);
    public static final DeferredHolder<ParticleType<?>, ParticleType<SpaceStoneParticleOptions>> SPACE_STONE_EMITTER = register("space_stone_emitter", () -> new ParticleType<>(true) {
        @Override
        public MapCodec<SpaceStoneParticleOptions> codec() {
            return SpaceStoneParticleOptions.codec(SPACE_STONE_EMITTER.get());
        }

        @Override
        public StreamCodec<? super RegistryFriendlyByteBuf, SpaceStoneParticleOptions> streamCodec() {
            return SpaceStoneParticleOptions.streamCodec(SPACE_STONE_EMITTER.get());
        }
    });
    public static final DeferredHolder<ParticleType<?>, ParticleType<SpeedParticleOptions>> SPACE_STONE = register("space_stone", () -> new ParticleType<>(true) {
        @Override
        public MapCodec<SpeedParticleOptions> codec() {
            return SpeedParticleOptions.codec(SPACE_STONE.get());
        }

        @Override
        public StreamCodec<? super RegistryFriendlyByteBuf, SpeedParticleOptions> streamCodec() {
            return SpeedParticleOptions.streamCodec(SPACE_STONE.get());
        }
    });
    public static final DeferredHolder<ParticleType<?>, ParticleType<SpaceStoneParticleOptions>> REVERSE_SPACE_STONE_EMITTER = register("reverse_space_stone_emitter", () -> new ParticleType<>(true) {
        @Override
        public MapCodec<SpaceStoneParticleOptions> codec() {
            return SpaceStoneParticleOptions.codec(REVERSE_SPACE_STONE_EMITTER.get());
        }

        @Override
        public StreamCodec<? super RegistryFriendlyByteBuf, SpaceStoneParticleOptions> streamCodec() {
            return SpaceStoneParticleOptions.streamCodec(REVERSE_SPACE_STONE_EMITTER.get());
        }
    });
    public static final DeferredHolder<ParticleType<?>, ParticleType<SpeedParticleOptions>> REVERSE_SPACE_STONE = register("reverse_space_stone", () -> new ParticleType<>(true) {
        @Override
        public MapCodec<SpeedParticleOptions> codec() {
            return SpeedParticleOptions.codec(REVERSE_SPACE_STONE.get());
        }

        @Override
        public StreamCodec<? super RegistryFriendlyByteBuf, SpeedParticleOptions> streamCodec() {
            return SpeedParticleOptions.streamCodec(REVERSE_SPACE_STONE.get());
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
