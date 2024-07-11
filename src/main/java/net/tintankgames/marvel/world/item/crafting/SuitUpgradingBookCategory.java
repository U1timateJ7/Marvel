package net.tintankgames.marvel.world.item.crafting;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;

import java.util.function.IntFunction;

public enum SuitUpgradingBookCategory implements StringRepresentable {
    SUITS("suits", 0),
    IRON_MAN_SUITS("iron_man_suits", 1);

    public static final Codec<SuitUpgradingBookCategory> CODEC = StringRepresentable.fromEnum(SuitUpgradingBookCategory::values);
    public static final IntFunction<SuitUpgradingBookCategory> BY_ID = ByIdMap.continuous(SuitUpgradingBookCategory::id, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
    public static final StreamCodec<ByteBuf, SuitUpgradingBookCategory> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, SuitUpgradingBookCategory::id);
    private final String name;
    private final int id;

    SuitUpgradingBookCategory(String p_249346_, int p_320577_) {
        this.name = p_249346_;
        this.id = p_320577_;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    private int id() {
        return this.id;
    }
}
