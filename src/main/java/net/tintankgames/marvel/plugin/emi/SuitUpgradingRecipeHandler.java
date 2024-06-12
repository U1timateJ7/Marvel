package net.tintankgames.marvel.plugin.emi;

import com.google.common.collect.Lists;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.handler.StandardRecipeHandler;
import net.minecraft.world.inventory.Slot;
import net.tintankgames.marvel.world.inventory.SuitUpgradingMenu;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SuitUpgradingRecipeHandler implements StandardRecipeHandler<SuitUpgradingMenu> {
    @Override
    public List<Slot> getInputSources(SuitUpgradingMenu handler) {
        List<Slot> list = Lists.newArrayList();
        for (int i = 1; i < 11; i++) {
            list.add(handler.getSlot(i));
        }
        int invStart = 11;
        for (int i = invStart; i < invStart + 36; i++) {
            list.add(handler.getSlot(i));
        }
        return list;
    }

    @Override
    public List<Slot> getCraftingSlots(SuitUpgradingMenu handler) {
        List<Slot> list = Lists.newArrayList();
        for (int i = 1; i < 11; i++) {
            list.add(handler.getSlot(i));
        }
        return list;
    }

    @Override
    public @Nullable Slot getOutputSlot(SuitUpgradingMenu handler) {
        return handler.slots.get(0);
    }

    @Override
    public boolean supportsRecipe(EmiRecipe recipe) {
        return recipe.getCategory() == MarvelSuperheroesEmiPlugin.SUIT_UPGRADING && recipe.supportsRecipeTree();
    }
}
