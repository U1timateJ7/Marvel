package net.tintankgames.marvel;

import net.minecraft.world.item.ItemDisplayContext;

public class MarvelEnumExtensions {
    public static final ItemDisplayContext FLYING_RIGHT_HAND_CONTEXT = ItemDisplayContext.create("FLYING_RIGHT_HAND", MarvelSuperheroes.id("flying_righthand"), ItemDisplayContext.THIRD_PERSON_RIGHT_HAND);
    public static final ItemDisplayContext FLYING_LEFT_HAND_CONTEXT = ItemDisplayContext.create("FLYING_RIGHT_HAND", MarvelSuperheroes.id("flying_lefthand"), ItemDisplayContext.THIRD_PERSON_LEFT_HAND);
}
