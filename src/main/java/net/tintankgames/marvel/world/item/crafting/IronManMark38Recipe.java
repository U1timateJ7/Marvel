package net.tintankgames.marvel.world.item.crafting;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;
import net.tintankgames.marvel.world.item.MarvelItems;

public class IronManMark38Recipe extends CustomRecipe {
    public IronManMark38Recipe(CraftingBookCategory p_250392_) {
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
                if (itemstack.is(MarvelItems.IRON_MAN_MARK_38_HELMET_COMPONENT) && !hasHelmet) {
                    hasHelmet = true;
                } else if (itemstack.is(MarvelItems.IRON_MAN_MARK_38_CHESTPLATE_COMPONENT) && !hasChestplate) {
                    hasChestplate = true;
                } else if (itemstack.is(MarvelItems.IRON_MAN_MARK_38_LEGGINGS_COMPONENT) && !hasLeggings) {
                    hasLeggings = true;
                } else if (itemstack.is(MarvelItems.IRON_MAN_MARK_38_BOOTS_COMPONENT) && !hasBoots) {
                    hasBoots = true;
                }
            }
        }
        return hasHelmet && hasChestplate && hasLeggings && hasBoots;
    }

    public ItemStack assemble(CraftingInput p_44497_, HolderLookup.Provider p_336034_) {
        ItemStack stack = MarvelItems.IRON_MAN_MARK_38.toStack();
        ItemStack helmet = ItemStack.EMPTY;
        ItemStack chestplate = ItemStack.EMPTY;
        ItemStack leggings = ItemStack.EMPTY;
        ItemStack boots = ItemStack.EMPTY;
        for (int i = 0; i < p_44497_.size(); i++) {
            ItemStack stack1 = p_44497_.getItem(i);
            if (!stack1.isEmpty()) {
                if (stack1.is(MarvelItems.IRON_MAN_MARK_38_HELMET_COMPONENT) && helmet.isEmpty()) {
                    helmet = stack1.transmuteCopy(BuiltInRegistries.ITEM.get(ResourceLocation.parse(stack1.getItemHolder().getRegisteredName().replace("_component", ""))));
                } else if (stack1.is(MarvelItems.IRON_MAN_MARK_38_CHESTPLATE_COMPONENT) && chestplate.isEmpty()) {
                    chestplate = stack1.transmuteCopy(BuiltInRegistries.ITEM.get(ResourceLocation.parse(stack1.getItemHolder().getRegisteredName().replace("_component", ""))));
                } else if (stack1.is(MarvelItems.IRON_MAN_MARK_38_LEGGINGS_COMPONENT) && leggings.isEmpty()) {
                    leggings = stack1.transmuteCopy(BuiltInRegistries.ITEM.get(ResourceLocation.parse(stack1.getItemHolder().getRegisteredName().replace("_component", ""))));
                } else if (stack1.is(MarvelItems.IRON_MAN_MARK_38_BOOTS_COMPONENT) && boots.isEmpty()) {
                    boots = stack1.transmuteCopy(BuiltInRegistries.ITEM.get(ResourceLocation.parse(stack1.getItemHolder().getRegisteredName().replace("_component", ""))));
                }
            }
        }
        CompoundTag tag = new CompoundTag();
        tag.putString("id", MarvelEntityTypes.IRON_MAN_MARK_38.getRegisteredName());
        ListTag armorItems = new ListTag();
        armorItems.add(boots.save(p_336034_));
        armorItems.add(leggings.save(p_336034_));
        armorItems.add(chestplate.save(p_336034_));
        armorItems.add(helmet.save(p_336034_));
        tag.put("ArmorItems", armorItems);
        stack.set(DataComponents.ENTITY_DATA, CustomData.of(tag));
        return stack;
    }

    @Override
    public boolean canCraftInDimensions(int p_44489_, int p_44490_) {
        return p_44489_ * p_44490_ >= 4;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MarvelRecipeSerializers.IRON_MAN_MARK_38.get();
    }
}
