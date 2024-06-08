package net.tintankgames.marvel.world.item.component;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.DyeColor;

import java.util.List;
import java.util.function.IntFunction;

public enum ShieldArt implements StringRepresentable {
    BLANK("blank"),
    CAPTAIN_AMERICA("captain_america"),
    CAPTAIN_AMERICA_STEALTH("captain_america_stealth"),
    CAPTAIN_CARTER("captain_carter"),
    RED_GUARDIAN("red_guardian");

    private static final IntFunction<ShieldArt> SHIELD_ART_BY_ID = ByIdMap.continuous(ShieldArt::ordinal, ShieldArt.values(), ByIdMap.OutOfBoundsStrategy.ZERO);
    public static final Codec<ShieldArt> CODEC = StringRepresentable.fromEnum(ShieldArt::values);
    public static final StreamCodec<ByteBuf, ShieldArt> STREAM_CODEC = ByteBufCodecs.idMapper(SHIELD_ART_BY_ID, ShieldArt::ordinal);
    private final String name;

    ShieldArt(String name) {
        this.name = name;
    }

    public static ShieldArt getFromColors(List<DyeColor> colors) {
        if (colors.size() == 3 && colors.contains(DyeColor.RED) && colors.contains(DyeColor.WHITE) && colors.contains(DyeColor.BLUE)) {
            return CAPTAIN_AMERICA;
        } else if (colors.size() == 3 && colors.contains(DyeColor.LIGHT_BLUE) && colors.contains(DyeColor.WHITE) && colors.contains(DyeColor.BLUE)) {
            return CAPTAIN_AMERICA_STEALTH;
        } else if (colors.size() == 2 && colors.contains(DyeColor.RED) && colors.contains(DyeColor.BLUE)) {
            return CAPTAIN_CARTER;
        } else if (colors.size() == 1 && colors.contains(DyeColor.RED)) {
            return RED_GUARDIAN;
        }
        return null;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
