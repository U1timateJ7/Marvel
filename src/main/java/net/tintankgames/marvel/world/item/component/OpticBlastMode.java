package net.tintankgames.marvel.world.item.component;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;

import java.util.function.IntFunction;

public enum OpticBlastMode implements StringRepresentable {
    NARROW("narrow"),
    WIDE("wide");

    private static final IntFunction<OpticBlastMode> OPTIC_BLAST_BY_ID = ByIdMap.continuous(OpticBlastMode::ordinal, OpticBlastMode.values(), ByIdMap.OutOfBoundsStrategy.ZERO);
    public static final Codec<OpticBlastMode> CODEC = StringRepresentable.fromEnum(OpticBlastMode::values);
    public static final StreamCodec<ByteBuf, OpticBlastMode> STREAM_CODEC = ByteBufCodecs.idMapper(OPTIC_BLAST_BY_ID, OpticBlastMode::ordinal);
    private final String name;

    OpticBlastMode(String name) {
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
