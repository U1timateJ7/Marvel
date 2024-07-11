package net.tintankgames.marvel.world.item.crafting;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.tintankgames.marvel.world.item.MarvelItems;

import java.util.List;

public class KineticBlackPantherNecklaceRecipe extends CustomRecipe {
    public KineticBlackPantherNecklaceRecipe(CraftingBookCategory p_250392_) {
        super(p_250392_);
    }

    public boolean matches(CraftingInput p_44499_, Level p_44500_) {
        boolean hasHelmet = false;
        boolean hasChestplate = false;
        boolean hasLeggings = false;
        boolean hasBoots = false;
        for (int i = 0; i < p_44499_.size(); i++) {
            ItemStack itemstack = p_44499_.getItem(i);
            if (!itemstack.isEmpty()) {
                if (itemstack.is(MarvelItems.KINETIC_BLACK_PANTHER_HELMET) && !hasHelmet) {
                    hasHelmet = true;
                } else if (itemstack.is(MarvelItems.KINETIC_BLACK_PANTHER_CHESTPLATE) && !hasChestplate) {
                    hasChestplate = true;
                } else if (itemstack.is(MarvelItems.KINETIC_BLACK_PANTHER_LEGGINGS) && !hasLeggings) {
                    hasLeggings = true;
                } else if (itemstack.is(MarvelItems.KINETIC_BLACK_PANTHER_BOOTS) && !hasBoots) {
                    hasBoots = true;
                }
            }
        }
        return hasHelmet && hasChestplate && hasLeggings && hasBoots;
    }

    public ItemStack assemble(CraftingInput p_44497_, HolderLookup.Provider p_336034_) {
        ItemStack stack = MarvelItems.KINETIC_BLACK_PANTHER_NECKLACE.toStack();
        ItemStack helmet = ItemStack.EMPTY;
        ItemStack chestplate = ItemStack.EMPTY;
        ItemStack leggings = ItemStack.EMPTY;
        ItemStack boots = ItemStack.EMPTY;
        for (int i = 0; i < p_44497_.size(); i++) {
            ItemStack stack1 = p_44497_.getItem(i);
            if (!stack1.isEmpty()) {
                if (stack1.is(MarvelItems.KINETIC_BLACK_PANTHER_HELMET) && helmet.isEmpty()) {
                    helmet = stack1.copy();
                } else if (stack1.is(MarvelItems.KINETIC_BLACK_PANTHER_CHESTPLATE) && chestplate.isEmpty()) {
                    chestplate = stack1.copy();
                } else if (stack1.is(MarvelItems.KINETIC_BLACK_PANTHER_LEGGINGS) && leggings.isEmpty()) {
                    leggings = stack1.copy();
                } else if (stack1.is(MarvelItems.KINETIC_BLACK_PANTHER_BOOTS) && boots.isEmpty()) {
                    boots = stack1.copy();
                }
            }
        }
        List<ItemStack> list = List.of(boots, leggings, chestplate, helmet);
        stack.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(list));
        return stack;
    }

    @Override
    public boolean canCraftInDimensions(int p_44489_, int p_44490_) {
        return p_44489_ * p_44490_ >= 4;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MarvelRecipeSerializers.KINETIC_BLACK_PANTHER_NECKLACE.get();
    }
}
