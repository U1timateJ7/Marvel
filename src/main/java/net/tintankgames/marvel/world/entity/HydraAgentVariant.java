package net.tintankgames.marvel.world.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.tintankgames.marvel.core.registries.MarvelRegistries;

import java.util.Objects;

public final class HydraAgentVariant {
    public static final Codec<HydraAgentVariant> DIRECT_CODEC = RecordCodecBuilder.create(instance -> instance.group(ResourceLocation.CODEC.fieldOf("texture").forGetter(hydraAgentVariant -> hydraAgentVariant.texture), RegistryCodecs.homogeneousList(Registries.BIOME).fieldOf("biomes").forGetter(HydraAgentVariant::biomes)).apply(instance, HydraAgentVariant::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, HydraAgentVariant> DIRECT_STREAM_CODEC = StreamCodec.composite(ResourceLocation.STREAM_CODEC, HydraAgentVariant::texture, ByteBufCodecs.holderSet(Registries.BIOME), HydraAgentVariant::biomes, HydraAgentVariant::new);
    public static final Codec<Holder<HydraAgentVariant>> CODEC = RegistryFileCodec.create(MarvelRegistries.HYDRA_AGENT_VARIANT, DIRECT_CODEC);
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<HydraAgentVariant>> STREAM_CODEC = ByteBufCodecs.holder(MarvelRegistries.HYDRA_AGENT_VARIANT, DIRECT_STREAM_CODEC);
    private final ResourceLocation texture;
    private final HolderSet<Biome> biomes;

    public HydraAgentVariant(ResourceLocation texture, HolderSet<Biome> biomes) {
        this.texture = texture;
        this.biomes = biomes;
    }

    public ResourceLocation texture(String name) {
        return texture.withPath(path -> "textures/" + path + "_" + name + ".png");
    }

    public ResourceLocation texture() {
        return this.texture;
    }

    public HolderSet<Biome> biomes() {
        return this.biomes;
    }

    @Override
    public boolean equals(Object p_332811_) {
        if (p_332811_ == this) {
            return true;
        } else {
            return p_332811_ instanceof HydraAgentVariant hydraAgentVariant
                    && Objects.equals(this.texture, hydraAgentVariant.texture)
                    && Objects.equals(this.biomes, hydraAgentVariant.biomes);
        }
    }

    @Override
    public int hashCode() {
        int i = 1;
        i = 31 * i + this.texture.hashCode();
        return 31 * i + this.biomes.hashCode();
    }
}
