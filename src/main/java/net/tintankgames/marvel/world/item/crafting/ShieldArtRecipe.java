package net.tintankgames.marvel.world.item.crafting;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.item.VibraniumShieldItem;
import net.tintankgames.marvel.world.item.component.ShieldArt;

import java.util.ArrayList;
import java.util.List;

public class ShieldArtRecipe extends CustomRecipe {
    public ShieldArtRecipe(CraftingBookCategory p_250392_) {
        super(p_250392_);
    }

    public boolean matches(CraftingInput p_44499_, Level p_44500_) {
        boolean hasShield = false;
        boolean hasPaper = false;
        List<DyeColor> dyeColors = new ArrayList<>();
        for (int i = 0; i < p_44499_.size(); i++) {
            ItemStack itemstack = p_44499_.getItem(i);
            if (!itemstack.isEmpty()) {
                if (itemstack.getItem() instanceof VibraniumShieldItem && !hasShield) {
                    hasShield = true;
                } else if (itemstack.is(Items.PAPER) && !hasPaper) {
                    hasPaper = true;
                } else if (itemstack.getItem() instanceof DyeItem dyeItem) {
                    dyeColors.add(dyeItem.getDyeColor());
                }
            }
        }
        ShieldArt art = ShieldArt.getFromColors(dyeColors);
        return hasPaper && hasShield && art != null;
    }

    public ItemStack assemble(CraftingInput p_44497_, HolderLookup.Provider p_336034_) {
        ItemStack stack = ItemStack.EMPTY;
        List<DyeColor> dyeColors = new ArrayList<>();
        for (int i = 0; i < p_44497_.size(); i++) {
            ItemStack stack1 = p_44497_.getItem(i);
            if (!stack1.isEmpty()) {
                if (stack1.getItem() instanceof VibraniumShieldItem && !(stack.getItem() instanceof VibraniumShieldItem)) {
                    stack = stack1.copy();
                } else if (stack1.getItem() instanceof DyeItem dyeItem) {
                    dyeColors.add(dyeItem.getDyeColor());
                }
            }
        }
        ShieldArt art = ShieldArt.getFromColors(dyeColors);
        if (!stack.isEmpty()) stack.set(MarvelDataComponents.SHIELD_ART, art);
        return stack;
    }

    @Override
    public boolean canCraftInDimensions(int p_44489_, int p_44490_) {
        return p_44489_ >= 2 && p_44490_ >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MarvelRecipeSerializers.SHIELD_ART.get();
    }
}
