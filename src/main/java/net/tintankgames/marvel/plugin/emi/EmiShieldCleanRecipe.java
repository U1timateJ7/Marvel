package net.tintankgames.marvel.plugin.emi;

import com.google.common.collect.Lists;
import dev.emi.emi.api.recipe.EmiPatternCraftingRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.GeneratedSlotWidget;
import dev.emi.emi.api.widget.SlotWidget;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.DyedItemColor;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.item.component.ShieldArt;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmiShieldCleanRecipe extends EmiPatternCraftingRecipe {
    private static final List<DyeItem> DYES = Stream.of(DyeColor.values()).map(DyeItem::byColor).toList();
    private final Item shield;

    public EmiShieldCleanRecipe(Item shield, ResourceLocation id) {
        super(List.of(
                EmiIngredient.of(DYES.stream().map(i -> (EmiIngredient) EmiStack.of(i)).collect(Collectors.toList())),
                EmiStack.of(shield)), EmiStack.of(shield), id);
        this.shield = shield;
    }

    @Override
    public SlotWidget getInputWidget(int slot, int x, int y) {
        if (slot == 0) {
            return new GeneratedSlotWidget(this::getShield, unique, x, y);
        } else if (slot == 1) {
            return new SlotWidget(EmiStack.of(Items.WATER_BUCKET), x, y);
        } else return new SlotWidget(EmiStack.EMPTY, x, y);
    }

    @Override
    public SlotWidget getOutputWidget(int x, int y) {
        return new SlotWidget(EmiStack.of(shield), x, y);
    }

    private EmiStack getShield(Random random) {
        ShieldArt art = ShieldArt.values()[random.nextInt(ShieldArt.values().length)];
        ItemStack stack = new ItemStack(shield);
        if (art != ShieldArt.BLANK) stack.set(MarvelDataComponents.SHIELD_ART, art);
        else stack = DyedItemColor.applyDyes(stack, getDyes(random));
        return EmiStack.of(stack);
    }

    private List<DyeItem> getDyes(Random random) {
        List<DyeItem> dyes = Lists.newArrayList();
        int amount = 1 + random.nextInt(8);
        for (int i = 0; i < amount; i++) {
            dyes.add(DYES.get(random.nextInt(DYES.size())));
        }
        return dyes;
    }
}
