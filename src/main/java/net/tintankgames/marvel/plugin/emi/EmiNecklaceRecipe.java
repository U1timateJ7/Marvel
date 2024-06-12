package net.tintankgames.marvel.plugin.emi;

import dev.emi.emi.api.recipe.EmiPatternCraftingRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.SlotWidget;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.stream.Stream;

public class EmiNecklaceRecipe extends EmiPatternCraftingRecipe {
    private static final List<DyeItem> DYES = Stream.of(DyeColor.values()).map(DyeItem::byColor).toList();
    private final Item necklace;

    public EmiNecklaceRecipe(Item necklace, ResourceLocation id) {
        super(new ItemStack(necklace).get(DataComponents.CONTAINER).stream().map(Ingredient::of).map(EmiIngredient::of).toList(), EmiStack.of(necklace), id);
        this.necklace = necklace;
    }

    @Override
    public SlotWidget getInputWidget(int slot, int x, int y) {
        if (slot == 0) {
            return new SlotWidget(getInputs().get(3), x, y);
        } else if (slot == 1) {
            return new SlotWidget(getInputs().get(2), x, y);
        } else if (slot == 2) {
            return new SlotWidget(getInputs().get(1), x, y);
        } else if (slot == 3) {
            return new SlotWidget(getInputs().get(0), x, y);
        } else return new SlotWidget(EmiStack.EMPTY, x, y);
    }

    @Override
    public SlotWidget getOutputWidget(int x, int y) {
        return new SlotWidget(EmiStack.of(necklace), x, y);
    }
}
