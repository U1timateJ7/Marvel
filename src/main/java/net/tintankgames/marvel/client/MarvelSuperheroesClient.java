package net.tintankgames.marvel.client;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.event.entity.player.PlayerHeartTypeEvent;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.client.renderer.item.NecklaceRenderer;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = MarvelSuperheroes.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MarvelSuperheroesClient {
    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        CuriosRendererRegistry.register(MarvelItems.KINETIC_BLACK_PANTHER_NECKLACE.get(), NecklaceRenderer::new);
        CuriosRendererRegistry.register(MarvelItems.KILLMONGER_NECKLACE.get(), NecklaceRenderer::new);
        CuriosRendererRegistry.register(MarvelItems.BLACK_PANTHER_SHURI_NECKLACE.get(), NecklaceRenderer::new);

        ItemBlockRenderTypes.setRenderLayer(MarvelBlocks.SPIDER_WEB.get(), RenderType.cutoutMipped());

        ItemProperties.register(
                MarvelItems.TESSERACT_CROSSBOW.get(),
                new ResourceLocation("pull"),
                (p_351682_, p_351683_, p_351684_, p_351685_) -> {
                    if (p_351684_ == null) {
                        return 0.0F;
                    } else {
                        return CrossbowItem.isCharged(p_351682_) ? 0.0F : (float)(p_351682_.getUseDuration() - p_351684_.getUseItemRemainingTicks()) / (float)CrossbowItem.getChargeDuration(p_351682_);
                    }
                }
        );
        ItemProperties.register(MarvelItems.TESSERACT_CROSSBOW.get(), new ResourceLocation("pulling"), (p_174605_, p_174606_, p_174607_, p_174608_) -> p_174607_ != null && p_174607_.isUsingItem() && p_174607_.getUseItem() == p_174605_ && !CrossbowItem.isCharged(p_174605_) ? 1.0F : 0.0F);
        ItemProperties.register(MarvelItems.TESSERACT_CROSSBOW.get(), new ResourceLocation("charged"), (p_275891_, p_275892_, p_275893_, p_275894_) -> CrossbowItem.isCharged(p_275891_) ? 1.0F : 0.0F);
        ItemProperties.register(MarvelItems.TESSERACT_CROSSBOW.get(), new ResourceLocation("firework"), (p_329796_, p_329797_, p_329798_, p_329799_) -> {
            ChargedProjectiles chargedprojectiles = p_329796_.get(DataComponents.CHARGED_PROJECTILES);
            return chargedprojectiles != null && chargedprojectiles.contains(Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
        });
        ItemProperties.register(MarvelItems.REPULSOR.get(), MarvelSuperheroes.id("war_machine"), (stack, level, living, i) -> living != null && living.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.WAR_MACHINE_ARMOR) ? 1 : 0);
        ItemProperties.register(MarvelItems.UNIBEAM.get(), MarvelSuperheroes.id("war_machine"), (stack, level, living, i) -> living != null && living.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.WAR_MACHINE_ARMOR) ? 1 : 0);
        ItemProperties.register(MarvelItems.KATANAS.get(), MarvelSuperheroes.id("in_hand"), (stack, level, living, i) -> living != null && (living.getMainHandItem() == stack || living.getOffhandItem() == stack) ? 1 : 0);
        ItemProperties.register(MarvelItems.KATANAS.get(), MarvelSuperheroes.id("on_back"), (stack, level, living, i) -> i == -2016 ? 1 : 0);
        ItemProperties.register(MarvelItems.KATANAS.get(), MarvelSuperheroes.id("on_back_empty"), (stack, level, living, i) -> i == -2018 ? 1 : 0);
    }

    @SubscribeEvent
    public static void itemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, layer) -> layer != 1 ? -1 : FastColor.ARGB32.opaque(DyedItemColor.getOrDefault(stack, 0xFDF68C)), MarvelItems.CAPTAIN_MARVEL_HELMET);
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
                if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).is(MarvelItems.Tags.DEADPOOL_ARMOR) && event.getEntity().getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.DEADPOOL_ARMOR) && event.getEntity().getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.DEADPOOL_ARMOR) && event.getEntity().getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.DEADPOOL_ARMOR)) {
                    event.setType(MarvelClientEnumExtensions.DEADPOOL_HEART_TYPE);
                }
            }
        }
    }
}
