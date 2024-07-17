package net.tintankgames.marvel.client;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.entity.EquipmentSlot;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterRecipeBookCategoriesEvent;
import net.neoforged.neoforge.event.entity.player.PlayerHeartTypeEvent;
import net.tintankgames.marvel.MarvelEnumExtensions;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.client.renderer.item.NecklaceRenderer;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.crafting.MarvelRecipeTypes;
import net.tintankgames.marvel.world.item.crafting.SuitUpgradingBookCategory;
import net.tintankgames.marvel.world.item.crafting.SuitUpgradingRecipe;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

import java.util.List;
import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = MarvelSuperheroes.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MarvelSuperheroesClient {
    public static final Supplier<List<RecipeBookCategories>> SUIT_UPGRADING_CATEGORIES = Suppliers.memoize(() -> ImmutableList.of(
            MarvelClientEnumExtensions.SUIT_UPGRADING_SEARCH_CATEGORY.get(), MarvelClientEnumExtensions.SUIT_UPGRADING_SUITS_CATEGORY.get(), MarvelClientEnumExtensions.SUIT_UPGRADING_IRON_MAN_SUITS_CATEGORY.get()
    ));

    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        CuriosRendererRegistry.register(MarvelItems.KINETIC_BLACK_PANTHER_NECKLACE.get(), NecklaceRenderer::new);
        CuriosRendererRegistry.register(MarvelItems.KILLMONGER_NECKLACE.get(), NecklaceRenderer::new);
        CuriosRendererRegistry.register(MarvelItems.BLACK_PANTHER_SHURI_NECKLACE.get(), NecklaceRenderer::new);

        ItemBlockRenderTypes.setRenderLayer(MarvelBlocks.SPIDER_WEB.get(), RenderType.cutoutMipped());

        ItemProperties.register(MarvelItems.REPULSOR.get(), MarvelSuperheroes.id("war_machine"), (stack, level, living, i) -> living != null && living.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.WAR_MACHINE_ARMOR) ? 1 : 0);
        ItemProperties.register(MarvelItems.UNIBEAM.get(), MarvelSuperheroes.id("war_machine"), (stack, level, living, i) -> living != null && living.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.WAR_MACHINE_ARMOR) ? 1 : 0);
    }

    @SubscribeEvent
    public static void registerCategories(RegisterRecipeBookCategoriesEvent event) {
        event.registerAggregateCategory(MarvelClientEnumExtensions.SUIT_UPGRADING_SEARCH_CATEGORY.get(), List.of(MarvelClientEnumExtensions.SUIT_UPGRADING_SUITS_CATEGORY.get(), MarvelClientEnumExtensions.SUIT_UPGRADING_IRON_MAN_SUITS_CATEGORY.get()));
        event.registerBookCategories(MarvelEnumExtensions.SUIT_UPGRADING_TYPE.get(), SUIT_UPGRADING_CATEGORIES.get());
        event.registerRecipeCategoryFinder(MarvelRecipeTypes.SUIT_UPGRADING.get(), holder -> holder.value() instanceof SuitUpgradingRecipe suitUpgradingRecipe ? getCategoriesFromCategory(suitUpgradingRecipe.category()) : MarvelClientEnumExtensions.SUIT_UPGRADING_SUITS_CATEGORY.get());
    }

    private static RecipeBookCategories getCategoriesFromCategory(SuitUpgradingBookCategory category) {
        return switch (category) {
            case SUITS -> MarvelClientEnumExtensions.SUIT_UPGRADING_SUITS_CATEGORY.get();
            case IRON_MAN_SUITS -> MarvelClientEnumExtensions.SUIT_UPGRADING_IRON_MAN_SUITS_CATEGORY.get();
        };
    }

    @OnlyIn(Dist.CLIENT)
    @EventBusSubscriber(Dist.CLIENT)
    public static class EventHandler {
        @SubscribeEvent
        public static void customHearts(PlayerHeartTypeEvent event) {
            if (event.getOriginalType() == Gui.HeartType.NORMAL) {
                if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).is(MarvelItems.Tags.WOLVERINE_ARMOR) && event.getEntity().getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.WOLVERINE_ARMOR) && event.getEntity().getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.WOLVERINE_ARMOR) && event.getEntity().getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.WOLVERINE_ARMOR)) {
                    event.setType(MarvelClientEnumExtensions.ADAMANTIUM_HEART_TYPE);
                }
            }
        }
    }
}
