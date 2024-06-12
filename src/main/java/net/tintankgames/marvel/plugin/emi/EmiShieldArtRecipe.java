package net.tintankgames.marvel.plugin.emi;

import dev.emi.emi.api.recipe.EmiPatternCraftingRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.SlotWidget;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.item.component.ShieldArt;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmiShieldArtRecipe extends EmiPatternCraftingRecipe {
    private static final List<DyeItem> DYES = Stream.of(DyeColor.values()).map(DyeItem::byColor).toList();
    private final Item shield;
    private final ShieldArt art;

    public EmiShieldArtRecipe(Item shield, ShieldArt art, ResourceLocation id) {
        super(List.of(
                EmiIngredient.of(DYES.stream().map(i -> (EmiIngredient) EmiStack.of(i)).collect(Collectors.toList())),
                EmiStack.of(shield)), EmiStack.of(shield), id);
        this.shield = shield;
        this.art = art;
    }

    @Override
    public SlotWidget getInputWidget(int slot, int x, int y) {
        if (slot == 0) {
            return new SlotWidget(EmiStack.of(shield), x, y);
        } else if (slot == 1) {
            return new SlotWidget(EmiStack.of(Items.PAPER), x, y);
        } else {
            final int s = slot - 2;
            return new SlotWidget(getDye(s), x, y);
        }
    }

    @Override
    public SlotWidget getOutputWidget(int x, int y) {
        return new SlotWidget(getShield(), x, y);
    }

    private List<DyeItem> getDyes() {
        return art.dyes();
    }

    private EmiStack getShield() {
        ItemStack stack = new ItemStack(shield);
        stack.set(MarvelDataComponents.SHIELD_ART, art);
        return EmiStack.of(stack);
    }

    private EmiStack getDye(int slot) {
        List<DyeItem> dyes = getDyes();
        if (slot < dyes.size()) {
            return EmiStack.of(dyes.get(slot));
        }
        return EmiStack.EMPTY;
    }
}
