package net.tintankgames.marvel.world.item.crafting;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.item.VibraniumShieldItem;

public class ShieldCleanRecipe extends CustomRecipe {
    public ShieldCleanRecipe(CraftingBookCategory p_250392_) {
        super(p_250392_);
    }

    public boolean matches(CraftingInput p_44499_, Level p_44500_) {
        boolean hasShield = false;
        boolean hasWater = false;
        for (int i = 0; i < p_44499_.size(); i++) {
            ItemStack itemstack = p_44499_.getItem(i);
            if (!itemstack.isEmpty()) {
                if (itemstack.getItem() instanceof VibraniumShieldItem && (itemstack.has(DataComponents.DYED_COLOR) || itemstack.has(MarvelDataComponents.SHIELD_ART)) && !hasShield) {
                    hasShield = true;
                } else if (itemstack.is(Items.WATER_BUCKET) && !hasWater) {
                    hasWater = true;
                }
            }
        }
        return hasWater && hasShield;
    }

    public ItemStack assemble(CraftingInput p_44497_, HolderLookup.Provider p_336034_) {
        ItemStack stack = ItemStack.EMPTY;
        for (int i = 0; i < p_44497_.size(); i++) {
            ItemStack stack1 = p_44497_.getItem(i);
            if (!stack1.isEmpty()) {
                if (stack1.getItem() instanceof VibraniumShieldItem && !(stack.getItem() instanceof VibraniumShieldItem)) {
                    stack = stack1.copy();
                }
            }
        }
        if (!stack.isEmpty() && stack.has(DataComponents.DYED_COLOR)) stack.remove(DataComponents.DYED_COLOR);
        if (!stack.isEmpty() && stack.has(MarvelDataComponents.SHIELD_ART)) stack.remove(MarvelDataComponents.SHIELD_ART);
        return stack;
    }

    @Override
    public boolean canCraftInDimensions(int p_44489_, int p_44490_) {
        return p_44489_ * p_44490_ >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MarvelRecipeSerializers.SHIELD_CLEAN.get();
    }
}
