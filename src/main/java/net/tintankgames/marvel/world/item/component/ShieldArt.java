package net.tintankgames.marvel.world.item.component;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Stream;

public enum ShieldArt implements StringRepresentable {
    BLANK("blank"),
    CAPTAIN_AMERICA("captain_america", DyeColor.RED, DyeColor.WHITE, DyeColor.BLUE),
    CAPTAIN_AMERICA_STEALTH("captain_america_stealth", DyeColor.LIGHT_BLUE, DyeColor.WHITE, DyeColor.BLUE),
    CAPTAIN_CARTER("captain_carter", DyeColor.RED, DyeColor.BLUE),
    RED_GUARDIAN("red_guardian", DyeColor.RED);

    private static final IntFunction<ShieldArt> SHIELD_ART_BY_ID = ByIdMap.continuous(ShieldArt::ordinal, ShieldArt.values(), ByIdMap.OutOfBoundsStrategy.ZERO);
    public static final Codec<ShieldArt> CODEC = StringRepresentable.fromEnum(ShieldArt::values);
    public static final StreamCodec<ByteBuf, ShieldArt> STREAM_CODEC = ByteBufCodecs.idMapper(SHIELD_ART_BY_ID, ShieldArt::ordinal);
    private final String name;
    private final List<DyeColor> colors;

    ShieldArt(String name, DyeColor... colors) {
        this.name = name;
        this.colors = Arrays.asList(colors);
    }

    public static ShieldArt getFromColors(List<DyeColor> colors) {
        for (ShieldArt art : values()) {
            if (colors.size() == art.colors.size() && new HashSet<>(colors).containsAll(art.colors)) {
                return art;
            }
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

    public List<DyeColor> colors() {
        return List.copyOf(colors);
    }

    public List<DyeItem> dyes() {
        List<DyeItem> dyes = new ArrayList<>();
        List<DyeColor> colors = new ArrayList<>(this.colors);
        for (DyeItem dyeItem : Stream.of(DyeColor.values()).map(DyeItem::byColor).toList()) {
            if (colors.contains(dyeItem.getDyeColor())) {
                dyes.add(dyeItem);
                colors.remove(dyeItem.getDyeColor());
                if (colors.isEmpty()) break;
            }
        }
        return dyes;
    }
}
