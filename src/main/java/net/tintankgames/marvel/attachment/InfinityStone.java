package net.tintankgames.marvel.attachment;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;

import java.util.function.IntFunction;

public enum InfinityStone implements StringRepresentable {
    SPACE("space");

    private static final IntFunction<InfinityStone> BY_ID = ByIdMap.continuous(InfinityStone::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
    public static final Codec<InfinityStone> CODEC = StringRepresentable.fromEnum(InfinityStone::values);
    public static final StreamCodec<ByteBuf, InfinityStone> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, InfinityStone::ordinal);

    private final String name;

    InfinityStone(String name) {
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
