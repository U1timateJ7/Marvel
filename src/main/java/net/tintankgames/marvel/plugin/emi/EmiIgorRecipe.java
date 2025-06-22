package net.tintankgames.marvel.plugin.emi;

import dev.emi.emi.api.recipe.EmiPatternCraftingRecipe;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.SlotWidget;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.List;

public class EmiIgorRecipe extends EmiPatternCraftingRecipe {
    private final Item igor;

    public EmiIgorRecipe(Item igor, Item helmet, Item chestplate, Item leggings, Item boots, ResourceLocation id) {
        super(List.of(EmiStack.of(boots), EmiStack.of(leggings), EmiStack.of(chestplate), EmiStack.of(helmet)), EmiStack.of(igor), id);
        this.igor = igor;
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
        return new SlotWidget(EmiStack.of(igor), x, y);
    }
}
