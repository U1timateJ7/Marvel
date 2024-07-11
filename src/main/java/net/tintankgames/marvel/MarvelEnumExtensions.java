package net.tintankgames.marvel;

import net.minecraft.world.inventory.RecipeBookType;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;

public class MarvelEnumExtensions {
    public static final EnumProxy<RecipeBookType> SUIT_UPGRADING_TYPE = new EnumProxy<>(RecipeBookType.class);
}
