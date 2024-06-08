package net.tintankgames.marvel.world.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.neoforged.neoforge.registries.DeferredItem;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class KineticBlackPantherNecklaceItem extends Item implements ICurioItem {
    private final int color;

    public KineticBlackPantherNecklaceItem(int color, List<DeferredItem<Item>> suitItems, Properties p_41383_) {
        super(p_41383_.stacksTo(1).component(DataComponents.CONTAINER, ItemContainerContents.fromItems(suitItems.stream().map(DeferredItem::toStack).toList())));
        this.color = color;
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        boolean bl = false;
        for (ItemStack stack1 : stack.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).nonEmptyItemsCopy()) {
            bl = bl || stack1.isBarVisible();
        }
        return bl;
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return color;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        int width = 0;
        for (ItemStack stack1 : stack.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).nonEmptyItemsCopy()) {
            width += stack1.getBarWidth();
        }
        return width / 4;
    }
}
