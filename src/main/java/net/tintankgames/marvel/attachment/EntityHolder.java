package net.tintankgames.marvel.attachment;

import net.minecraft.world.entity.Entity;

public class EntityHolder<T extends Entity> {
    public T entity;

    public EntityHolder(T entity) {
        this.entity = entity;
    }
}
