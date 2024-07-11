package net.tintankgames.marvel.client;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterRecipeBookCategoriesEvent;
import net.neoforged.neoforge.event.entity.player.PlayerHeartTypeEvent;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.client.renderer.item.NecklaceRenderer;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.crafting.MarvelRecipeTypes;
import net.tintankgames.marvel.world.item.crafting.SuitUpgradingBookCategory;
import net.tintankgames.marvel.world.item.crafting.SuitUpgradingRecipe;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;
import org.joml.Vector3f;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

import java.util.List;
import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = MarvelSuperheroes.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MarvelSuperheroesClient {
    public static final Gui.HeartType ADAMANTIUM = Gui.HeartType.create("ADAMANTIUM",
            MarvelSuperheroes.id("hud/heart/adamantium_full"),
            MarvelSuperheroes.id("hud/heart/adamantium_full_blinking"),
            MarvelSuperheroes.id("hud/heart/adamantium_half"),
            MarvelSuperheroes.id("hud/heart/adamantium_half_blinking"),
            MarvelSuperheroes.id("hud/heart/adamantium_hardcore_full"),
            MarvelSuperheroes.id("hud/heart/adamantium_hardcore_full_blinking"),
            MarvelSuperheroes.id("hud/heart/adamantium_hardcore_half"),
            MarvelSuperheroes.id("hud/heart/adamantium_hardcore_half_blinking"));
    public static final HumanoidModel.ArmPose VIBRANIUM_SHIELD_POSE = HumanoidModel.ArmPose.create("VIBRANIUM_SHIELD", false, (model, entity, arm) -> {
        ModelPart armModel = arm == HumanoidArm.RIGHT ? model.rightArm : model.leftArm;
        armModel.offsetPos(new Vector3f(arm == HumanoidArm.RIGHT ? -1.0F : 1.0F, 0.0F, 0.0F));
        armModel.setRotation(-1.309F, 2.3562F * (arm == HumanoidArm.LEFT ? -1.0F : 1.0F), 0.3927F * (arm == HumanoidArm.RIGHT ? -1.0F : 1.0F));
    });
    public static final Supplier<RecipeBookCategories> SUIT_UPGRADING_SEARCH_CATEGORY = Suppliers.memoize(() -> RecipeBookCategories.create("SUIT_UPGRADING_SEARCH", new ItemStack(Items.COMPASS)));
    public static final Supplier<RecipeBookCategories> SUIT_UPGRADING_SUITS_CATEGORY = Suppliers.memoize(() -> RecipeBookCategories.create("SUIT_UPGRADING_SUITS", MarvelItems.ANT_MAN_UPGRADED_CHESTPLATE.toStack(), MarvelItems.WASP_HELMET.toStack()));
    public static final Supplier<RecipeBookCategories> SUIT_UPGRADING_IRON_MAN_SUITS_CATEGORY = Suppliers.memoize(() -> RecipeBookCategories.create("SUIT_UPGRADING_IRON_MAN_SUITS", MarvelItems.IRON_MAN_MARK_2_CHESTPLATE.toStack(), MarvelItems.IRON_MAN_MARK_3_HELMET.toStack()));
    public static final Supplier<List<RecipeBookCategories>> SUIT_UPGRADING_CATEGORIES = Suppliers.memoize(() -> ImmutableList.of(
            SUIT_UPGRADING_SEARCH_CATEGORY.get(), SUIT_UPGRADING_SUITS_CATEGORY.get(), SUIT_UPGRADING_IRON_MAN_SUITS_CATEGORY.get()
    ));

    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        CuriosRendererRegistry.register(MarvelItems.KINETIC_BLACK_PANTHER_NECKLACE.get(), NecklaceRenderer::new);
        CuriosRendererRegistry.register(MarvelItems.KILLMONGER_NECKLACE.get(), NecklaceRenderer::new);
        CuriosRendererRegistry.register(MarvelItems.BLACK_PANTHER_SHURI_NECKLACE.get(), NecklaceRenderer::new);

        ItemBlockRenderTypes.setRenderLayer(MarvelBlocks.SPIDER_WEB.get(), RenderType.cutoutMipped());
    }

    @SubscribeEvent
    public static void registerCategories(RegisterRecipeBookCategoriesEvent event) {
        event.registerAggregateCategory(SUIT_UPGRADING_SEARCH_CATEGORY.get(), List.of(SUIT_UPGRADING_SUITS_CATEGORY.get(), SUIT_UPGRADING_IRON_MAN_SUITS_CATEGORY.get()));
        event.registerBookCategories(SuitUpgradingRecipe.RECIPE_BOOK_TYPE.get(), SUIT_UPGRADING_CATEGORIES.get());
        event.registerRecipeCategoryFinder(MarvelRecipeTypes.SUIT_UPGRADING.get(), holder -> holder.value() instanceof SuitUpgradingRecipe suitUpgradingRecipe ? getCategoriesFromCategory(suitUpgradingRecipe.category()) : SUIT_UPGRADING_SUITS_CATEGORY.get());
    }

    private static RecipeBookCategories getCategoriesFromCategory(SuitUpgradingBookCategory category) {
        return switch (category) {
            case SUITS -> SUIT_UPGRADING_SUITS_CATEGORY.get();
            case IRON_MAN_SUITS -> SUIT_UPGRADING_IRON_MAN_SUITS_CATEGORY.get();
        };
    }

    @OnlyIn(Dist.CLIENT)
    @EventBusSubscriber(Dist.CLIENT)
    public static class EventHandler {
        @SubscribeEvent
        public static void customHearts(PlayerHeartTypeEvent event) {
            if (event.getOriginalType() == Gui.HeartType.NORMAL) {
                if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).is(MarvelItems.Tags.WOLVERINE_ARMOR) && event.getEntity().getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.WOLVERINE_ARMOR) && event.getEntity().getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.WOLVERINE_ARMOR) && event.getEntity().getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.WOLVERINE_ARMOR)) {
                    event.setType(ADAMANTIUM);
                }
            }
        }
    }
}
