package net.tintankgames.marvel.world.item.component;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;

import java.util.function.IntFunction;

public enum Size implements StringRepresentable {
    NORMAL("normal"),
    SMALL("small"),
    BIG("big");

    private static final IntFunction<Size> SIZE_BY_ID = ByIdMap.continuous(Size::ordinal, Size.values(), ByIdMap.OutOfBoundsStrategy.ZERO);
    public static final Codec<Size> CODEC = StringRepresentable.fromEnum(Size::values);
    public static final StreamCodec<ByteBuf, Size> STREAM_CODEC = ByteBufCodecs.idMapper(SIZE_BY_ID, Size::ordinal);
    private final String name;

    Size(String name) {
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
