package net.tintankgames.marvel.plugin.emi;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.inventory.MarvelMenuTypes;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.component.ShieldArt;
import net.tintankgames.marvel.world.item.crafting.MarvelRecipeTypes;
import net.tintankgames.marvel.world.item.crafting.SuitUpgradingRecipe;
import net.tintankgames.marvel.world.item.crafting.SuitVariantRecipe;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;

@EmiEntrypoint
public class MarvelSuperheroesEmiPlugin implements EmiPlugin {
    public static final ResourceLocation SUIT_UPGRADING_SPRITE = MarvelSuperheroes.id("textures/gui/sprites/emi/suit_upgrading.png");
    public static final ResourceLocation SUIT_VARIANT_SPRITE = MarvelSuperheroes.id("textures/gui/sprites/emi/suit_variant.png");
    public static final EmiStack SUIT_TABLE = EmiStack.of(MarvelBlocks.SUIT_TABLE);
    public static final EmiRecipeCategory SUIT_UPGRADING = new EmiRecipeCategory(MarvelSuperheroes.id("suit_upgrading"), SUIT_TABLE, new EmiTexture(SUIT_UPGRADING_SPRITE, 0, 0, 16, 16, 16, 16, 16, 16));
    public static final EmiRecipeCategory SUIT_VARIANT = new EmiRecipeCategory(MarvelSuperheroes.id("suit_variant"), SUIT_TABLE, new EmiTexture(SUIT_VARIANT_SPRITE, 0, 0, 16, 16, 16, 16, 16, 16));

    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(SUIT_UPGRADING);
        registry.addCategory(SUIT_VARIANT);

        registry.addWorkstation(SUIT_UPGRADING, SUIT_TABLE);
        registry.addWorkstation(SUIT_VARIANT, SUIT_TABLE);

        registry.addRecipeHandler(MarvelMenuTypes.SUIT_UPGRADING.get(), new SuitUpgradingRecipeHandler());
        registry.addRecipeHandler(MarvelMenuTypes.SUIT_VARIANT.get(), new SuitVariantRecipeHandler());

        RecipeManager manager = registry.getRecipeManager();

        for (RecipeHolder<SuitUpgradingRecipe> recipe : manager.getAllRecipesFor(MarvelRecipeTypes.SUIT_UPGRADING.get())) {
            registry.addRecipe(new EmiSuitUpgradingRecipe(recipe));
        }
        for (RecipeHolder<SuitVariantRecipe> recipe : manager.getAllRecipesFor(MarvelRecipeTypes.SUIT_VARIANT.get())) {
            registry.addRecipe(new EmiSuitVariantRecipe(recipe));
        }

        for (ShieldArt art : ShieldArt.values()) {
            if (!art.colors().isEmpty()) {
                registry.addRecipe(new EmiShieldArtRecipe(MarvelItems.VIBRANIUM_SHIELD.get(), art, MarvelSuperheroes.id("vibranium_shield_art/" + art.getName())));
                registry.addRecipe(new EmiShieldArtRecipe(MarvelItems.PROTO_ADAMANTIUM_SHIELD.get(), art, MarvelSuperheroes.id("proto_adamantium_shield_art/" + art.getName())));
            }
        }
        registry.addRecipe(new EmiShieldCleanRecipe(MarvelItems.VIBRANIUM_SHIELD.get(), MarvelSuperheroes.id("vibranium_shield_clean")));
        registry.addRecipe(new EmiShieldCleanRecipe(MarvelItems.PROTO_ADAMANTIUM_SHIELD.get(), MarvelSuperheroes.id("proto_adamantium_shield_clean")));
        registry.addRecipe(new EmiNecklaceRecipe(MarvelItems.KINETIC_BLACK_PANTHER_NECKLACE.get(), MarvelSuperheroes.id("kinetic_black_panther_necklace")));
        registry.addRecipe(new EmiNecklaceRecipe(MarvelItems.KILLMONGER_NECKLACE.get(), MarvelSuperheroes.id("killmonger_necklace")));
    }
}
