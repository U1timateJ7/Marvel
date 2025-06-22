package net.tintankgames.marvel.world.level.block.state.properties;

import net.minecraft.util.StringRepresentable;

public enum ChargerPart implements StringRepresentable {
    TOP_LEFT,
    TOP_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_RIGHT;

    @Override
    public String getSerializedName() {
        return toString().toLowerCase();
    }
}
