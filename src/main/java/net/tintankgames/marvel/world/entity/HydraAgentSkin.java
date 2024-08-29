package net.tintankgames.marvel.world.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.tintankgames.marvel.core.registries.MarvelRegistries;

import java.util.Objects;
import java.util.function.IntFunction;

public record HydraAgentSkin(String name, Model model) {
    public static final Codec<HydraAgentSkin> DIRECT_CODEC = RecordCodecBuilder.create(instance -> instance.group(Codec.STRING.fieldOf("name").forGetter(HydraAgentSkin::name), Model.CODEC.fieldOf("model").forGetter(HydraAgentSkin::model)).apply(instance, HydraAgentSkin::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, HydraAgentSkin> DIRECT_STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.STRING_UTF8, HydraAgentSkin::name, Model.STREAM_CODEC, HydraAgentSkin::model, HydraAgentSkin::new);
    public static final Codec<Holder<HydraAgentSkin>> CODEC = RegistryFileCodec.create(MarvelRegistries.HYDRA_AGENT_SKIN, DIRECT_CODEC);
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<HydraAgentSkin>> STREAM_CODEC = ByteBufCodecs.holder(MarvelRegistries.HYDRA_AGENT_SKIN, DIRECT_STREAM_CODEC);

    @Override
    public boolean equals(Object p_332811_) {
        if (p_332811_ == this) {
            return true;
        } else {
            return p_332811_ instanceof HydraAgentSkin hydraAgentSkin
                    && Objects.equals(this.name, hydraAgentSkin.name)
                    && Objects.equals(this.model, hydraAgentSkin.model);
        }
    }

    public enum Model implements StringRepresentable {
        WIDE("wide"),
        SLIM("slim");

        private static final IntFunction<Model> BY_ID = ByIdMap.continuous(Model::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
        public static final Codec<Model> CODEC = StringRepresentable.fromEnum(Model::values);
        public static final StreamCodec<ByteBuf, Model> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, Model::ordinal);

        private final String name;

        Model(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }
}
