package net.tintankgames.marvel.world.item.crafting;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.tintankgames.marvel.world.item.MarvelItems;

import java.util.List;

public class IronManMark5SuitcaseRecipe extends CustomRecipe {
    public IronManMark5SuitcaseRecipe(CraftingBookCategory p_250392_) {
        super(p_250392_);
    }

    public boolean matches(CraftingContainer p_44499_, Level p_44500_) {
        boolean hasHelmet = false;
        boolean hasChestplate = false;
        boolean hasLeggings = false;
        boolean hasBoots = false;
        for (int i = 0; i < p_44499_.getContainerSize(); i++) {
            ItemStack itemstack = p_44499_.getItem(i);
            if (!itemstack.isEmpty()) {
                if (itemstack.is(MarvelItems.IRON_MAN_MARK_5_HELMET) && !hasHelmet) {
                    hasHelmet = true;
                } else if (itemstack.is(MarvelItems.IRON_MAN_MARK_5_CHESTPLATE) && !hasChestplate) {
                    hasChestplate = true;
                } else if (itemstack.is(MarvelItems.IRON_MAN_MARK_5_LEGGINGS) && !hasLeggings) {
                    hasLeggings = true;
                } else if (itemstack.is(MarvelItems.IRON_MAN_MARK_5_BOOTS) && !hasBoots) {
                    hasBoots = true;
                }
            }
        }
        return hasHelmet && hasChestplate && hasLeggings && hasBoots;
    }

    public ItemStack assemble(CraftingContainer p_44497_, HolderLookup.Provider p_336034_) {
        ItemStack stack = MarvelItems.IRON_MAN_MARK_5_SUITCASE.toStack();
        ItemStack helmet = ItemStack.EMPTY;
        ItemStack chestplate = ItemStack.EMPTY;
        ItemStack leggings = ItemStack.EMPTY;
        ItemStack boots = ItemStack.EMPTY;
        for (int i = 0; i < p_44497_.getContainerSize(); i++) {
            ItemStack stack1 = p_44497_.getItem(i);
            if (!stack1.isEmpty()) {
                if (stack1.is(MarvelItems.IRON_MAN_MARK_5_HELMET) && helmet.isEmpty()) {
                    helmet = stack1.copy();
                } else if (stack1.is(MarvelItems.IRON_MAN_MARK_5_CHESTPLATE) && chestplate.isEmpty()) {
                    chestplate = stack1.copy();
                } else if (stack1.is(MarvelItems.IRON_MAN_MARK_5_LEGGINGS) && leggings.isEmpty()) {
                    leggings = stack1.copy();
                } else if (stack1.is(MarvelItems.IRON_MAN_MARK_5_BOOTS) && boots.isEmpty()) {
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
        return MarvelRecipeSerializers.IRON_MAN_MARK_5_SUITCASE.get();
    }
}
