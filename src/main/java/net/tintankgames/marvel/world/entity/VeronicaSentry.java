package net.tintankgames.marvel.world.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;

public abstract class VeronicaSentry extends TamableAnimal {
    protected VeronicaSentry(EntityType<? extends VeronicaSentry> type, Level level) {
        super(type, level);
    }

    public abstract void setFromVeronica(boolean fromVeronica);

    public abstract boolean getIsFlying();

    public abstract boolean isCharging();

    public abstract void setCharging(boolean charging);
}
