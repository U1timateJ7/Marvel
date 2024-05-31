package com.ulto.marvel.world.level.block.state.properties;

import net.minecraft.util.StringRepresentable;

public enum PowerStage implements StringRepresentable {
    STAGE_0(0),
    STAGE_25(25),
    STAGE_50(50),
    STAGE_75(75),
    STAGE_100(100);

    public final int number;

    PowerStage(int number) {
        this.number = number;
    }

    public static PowerStage getStage(float minimumBattery) {
        PowerStage stage = PowerStage.STAGE_0;
        if (minimumBattery >= 25)
            stage = STAGE_25;
        if (minimumBattery >= 50)
            stage = STAGE_50;
        if (minimumBattery >= 75)
            stage = STAGE_75;
        if (minimumBattery >= 100)
            stage = STAGE_100;
        return stage;
    }

    @Override
    public String getSerializedName() {
        return String.valueOf(number);
    }
}
