package net.tintankgames.marvel.world.item.component;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;

import java.util.ArrayList;
import java.util.List;

public record SuitParts(List<Boolean> parts) {
    public static final Codec<SuitParts> CODEC = Codec.BOOL.listOf(1, 6).xmap(SuitParts::new, SuitParts::parts);
    public static final StreamCodec<ByteBuf, SuitParts> STREAM_CODEC = ByteBufCodecs.BOOL.apply(ByteBufCodecs.list(6)).map(SuitParts::new, SuitParts::parts);

    public SuitParts(List<Boolean> parts) {
        this.parts = new ArrayList<>(parts);
    }

    public static SuitParts onePart(int part, int size) {
        List<Boolean> parts = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            parts.add(i, i == part);
        }
        return new SuitParts(parts);
    }

    public static SuitParts defaultParts(ArmorItem.Type type, boolean value) {
        return new SuitParts(type == ArmorItem.Type.CHESTPLATE ? List.of(value, value, value, value, value, value) : List.of(value, value));
    }

    public static SuitParts defaultParts(EquipmentSlot slot, boolean value) {
        return new SuitParts(slot == EquipmentSlot.CHEST ? List.of(value, value, value, value, value, value) : List.of(value, value));
    }

    public boolean hasAllParts() {
        boolean bl = true;
        for (boolean bool : parts()) {
            bl = bool && bl;
        }
        return bl;
    }
}
