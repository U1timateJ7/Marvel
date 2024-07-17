package net.tintankgames.marvel.plugin.emi;

import com.google.common.collect.Lists;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.handler.EmiCraftContext;
import dev.emi.emi.api.recipe.handler.StandardRecipeHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.tintankgames.marvel.world.inventory.SuitVariantMenu;
import net.tintankgames.marvel.world.item.crafting.MarvelRecipeTypes;
import net.tintankgames.marvel.world.item.crafting.SuitVariantRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SuitVariantRecipeHandler implements StandardRecipeHandler<SuitVariantMenu> {
    @Override
    public List<Slot> getInputSources(SuitVariantMenu handler) {
        List<Slot> list = Lists.newArrayList();
        list.add(handler.getSlot(0));
        int invStart = 2;
        for (int i = invStart; i < invStart + 36; i++) {
            list.add(handler.getSlot(i));
        }
        return list;
    }

    @Override
    public List<Slot> getCraftingSlots(SuitVariantMenu handler) {
        return List.of(handler.slots.get(0));
    }

    @Override
    public boolean supportsRecipe(EmiRecipe recipe) {
        return recipe.getCategory() == MarvelSuperheroesEmiPlugin.SUIT_VARIANT;
    }

    @Override
    public @Nullable Slot getOutputSlot(SuitVariantMenu handler) {
        return handler.getSlot(1);
    }

    @Override
    public boolean craft(EmiRecipe recipe, EmiCraftContext<SuitVariantMenu> context) {
        boolean action = StandardRecipeHandler.super.craft(recipe, context);
        Minecraft client = Minecraft.getInstance();
        Level world = client.level;
        List<SuitVariantRecipe> recipes = world.getRecipeManager().getRecipesFor(MarvelRecipeTypes.SUIT_VARIANT.get(), new SimpleContainer(recipe.getInputs().get(0).getEmiStacks().get(0).getItemStack()), world).stream().map(RecipeHolder::value).toList();
        for (int i = 0; i < recipes.size(); i++) {
            if (getId(recipes.get(i)).equals(recipe.getId())) {
                SuitVariantMenu sh = context.getScreenHandler();
                client.gameMode.handleInventoryButtonClick(sh.containerId, i);
                if (context.getDestination() == EmiCraftContext.Destination.CURSOR) {
                    client.gameMode.handleInventoryMouseClick(sh.containerId, 1, 0, ClickType.PICKUP, client.player);
                } else if (context.getDestination() == EmiCraftContext.Destination.INVENTORY) {
                    client.gameMode.handleInventoryMouseClick(sh.containerId, 1, 0, ClickType.QUICK_MOVE, client.player);
                }
                break;
            }
        }
        return action;
    }

    private ResourceLocation getId(SuitVariantRecipe recipe) {
        return Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(MarvelRecipeTypes.SUIT_VARIANT.get()).stream().filter(holder -> holder.value() == recipe).toList().get(0).id();
    }
}
