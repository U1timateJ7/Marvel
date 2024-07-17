package net.tintankgames.marvel;

import com.google.common.base.Suppliers;
import net.minecraft.world.inventory.RecipeBookType;

import java.util.function.Supplier;

public class MarvelEnumExtensions {
    public static final Supplier<RecipeBookType> SUIT_UPGRADING_TYPE = Suppliers.memoize(() -> RecipeBookType.create("SUIT_UPGRADING"));
}
