package net.tintankgames.marvel;

import net.minecraft.world.item.ItemDisplayContext;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;

public class MarvelEnumExtensions {
    public static final EnumProxy<ItemDisplayContext> FLYING_RIGHT_HAND_CONTEXT = new EnumProxy<>(ItemDisplayContext.class, -1, MarvelSuperheroes.id("flying_righthand").toString(), "THIRD_PERSON_RIGHT_HAND");
    public static final EnumProxy<ItemDisplayContext> FLYING_LEFT_HAND_CONTEXT = new EnumProxy<>(ItemDisplayContext.class, -1, MarvelSuperheroes.id("flying_lefthand").toString(), "THIRD_PERSON_LEFT_HAND");
}
