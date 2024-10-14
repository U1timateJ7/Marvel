package net.tintankgames.marvel.plugin.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.client.gui.screens.SuitRepairingScreen;
import net.tintankgames.marvel.client.gui.screens.SuitUpgradingScreen;
import net.tintankgames.marvel.world.inventory.MarvelMenuTypes;
import net.tintankgames.marvel.world.inventory.SuitRepairingMenu;
import net.tintankgames.marvel.world.inventory.SuitUpgradingMenu;
import net.tintankgames.marvel.world.inventory.SuitVariantMenu;
import net.tintankgames.marvel.world.item.crafting.MarvelRecipeTypes;
import net.tintankgames.marvel.world.item.crafting.SuitRepairingRecipe;
import net.tintankgames.marvel.world.item.crafting.SuitUpgradingRecipe;
import net.tintankgames.marvel.world.item.crafting.SuitVariantRecipe;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class MarvelSuperheroesJeiPlugin implements IModPlugin {
    @Nullable
    private IRecipeCategory<RecipeHolder<SuitUpgradingRecipe>> suitUpgradingCategory;
    @Nullable
    private IRecipeCategory<RecipeHolder<SuitVariantRecipe>> suitVariantCategory;
    @Nullable
    private IRecipeCategory<RecipeHolder<SuitRepairingRecipe>> suitRepairingCategory;

    public static ItemStack getResultItem(Recipe<? extends RecipeInput> recipe) {
        Minecraft minecraft = Minecraft.getInstance();
        ClientLevel level = minecraft.level;
        if (level == null) {
            throw new NullPointerException("level must not be null.");
        }
        RegistryAccess registryAccess = level.registryAccess();
        return recipe.getResultItem(registryAccess);
    }

    @Override
    public ResourceLocation getPluginUid() {
        return MarvelSuperheroes.id("marvel");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IJeiHelpers jeiHelpers = registration.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
        registration.addRecipeCategories(
                suitUpgradingCategory = new SuitUpgradingRecipeCategory(guiHelper),
                suitVariantCategory = new SuitVariantRecipeCategory(guiHelper),
                suitRepairingCategory = new SuitRepairingRecipeCategory(guiHelper)
        );
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        Objects.requireNonNull(suitUpgradingCategory, "suitUpgradingCategory can't be null");
        Objects.requireNonNull(suitVariantCategory, "suitVariantCategory can't be null");
        Objects.requireNonNull(suitRepairingCategory, "suitRepairingCategory can't be null");

        registration.addRecipes(MarvelJeiRecipeTypes.SUIT_UPGRADING, getValidHandledRecipes(Minecraft.getInstance().level.getRecipeManager(), MarvelRecipeTypes.SUIT_UPGRADING.get()));
        registration.addRecipes(MarvelJeiRecipeTypes.SUIT_VARIANT, getValidHandledRecipes(Minecraft.getInstance().level.getRecipeManager(), MarvelRecipeTypes.SUIT_VARIANT.get()));
        registration.addRecipes(MarvelJeiRecipeTypes.SUIT_REPAIRING, getValidHandledRecipes(Minecraft.getInstance().level.getRecipeManager(), MarvelRecipeTypes.SUIT_REPAIRING.get()));
    }

    private static <C extends RecipeInput, T extends Recipe<C>> List<RecipeHolder<T>> getValidHandledRecipes(RecipeManager recipeManager, RecipeType<T> recipeType) {
        return recipeManager.getAllRecipesFor(recipeType).stream().toList();
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(SuitUpgradingScreen.class, 114, 35, 22, 15, MarvelJeiRecipeTypes.SUIT_UPGRADING);
        registration.addRecipeClickArea(SuitRepairingScreen.class, 110, 35, 22, 15, MarvelJeiRecipeTypes.SUIT_REPAIRING);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(SuitUpgradingMenu.class, MarvelMenuTypes.SUIT_UPGRADING.get(), MarvelJeiRecipeTypes.SUIT_UPGRADING, 1, 10, 11, 36);
        registration.addRecipeTransferHandler(SuitVariantMenu.class, MarvelMenuTypes.SUIT_VARIANT.get(), MarvelJeiRecipeTypes.SUIT_VARIANT, 0, 1, 2, 36);
        registration.addRecipeTransferHandler(SuitRepairingMenu.class, MarvelMenuTypes.SUIT_REPAIRING.get(), MarvelJeiRecipeTypes.SUIT_REPAIRING, 1, 5, 6, 36);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(MarvelBlocks.SUIT_TABLE), MarvelJeiRecipeTypes.SUIT_UPGRADING, MarvelJeiRecipeTypes.SUIT_VARIANT, MarvelJeiRecipeTypes.SUIT_REPAIRING);
    }
}
