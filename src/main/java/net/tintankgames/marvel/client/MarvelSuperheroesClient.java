package net.tintankgames.marvel.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerHeartTypeEvent;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.client.model.SuitModel;
import net.tintankgames.marvel.client.renderer.item.NecklaceRenderer;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.SuitItem;
import net.tintankgames.marvel.world.item.VibraniumShieldItem;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;
import org.jetbrains.annotations.Nullable;
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

        ItemProperties.register(MarvelItems.REPULSOR.get(), MarvelSuperheroes.id("war_machine"), (stack, level, living, i) -> living != null && living.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.WAR_MACHINE_ARMOR) ? 1 : 0);
        ItemProperties.register(MarvelItems.UNIBEAM.get(), MarvelSuperheroes.id("war_machine"), (stack, level, living, i) -> living != null && living.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.WAR_MACHINE_ARMOR) ? 1 : 0);
        ItemProperties.register(MarvelItems.KATANAS.get(), MarvelSuperheroes.id("in_hand"), (stack, level, living, i) -> living != null && (living.getMainHandItem() == stack || living.getOffhandItem() == stack) ? 1 : 0);
        ItemProperties.register(MarvelItems.KATANAS.get(), MarvelSuperheroes.id("on_back"), (stack, level, living, i) -> i == -2016 ? 1 : 0);
        ItemProperties.register(MarvelItems.KATANAS.get(), MarvelSuperheroes.id("on_back_empty"), (stack, level, living, i) -> i == -2018 ? 1 : 0);
    }

    @SubscribeEvent
    public static void initializeClient(RegisterClientExtensionsEvent event) {
        IClientItemExtensions suitExtensions = new IClientItemExtensions() {
            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                return itemStack.getItem() instanceof SuitItem suitItem ? new SuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(suitItem.modelFactory(suitItem.getType(), itemStack))) : IClientItemExtensions.super.getHumanoidArmorModel(livingEntity, itemStack, equipmentSlot, original);
            }
        };
        IClientItemExtensions shieldExtensions = new IClientItemExtensions() {
            @Nullable
            @Override
            public HumanoidModel.ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack stack) {
                return entityLiving.getUseItem() == stack ? MarvelClientEnumExtensions.VIBRANIUM_SHIELD_POSE.getValue() : IClientItemExtensions.super.getArmPose(entityLiving, hand, stack);
            }
        };
        for (Item item : BuiltInRegistries.ITEM.stream().filter(item -> item instanceof SuitItem).toList()) {
            event.registerItem(suitExtensions, item);
        }
        for (Item item : BuiltInRegistries.ITEM.stream().filter(item -> item instanceof VibraniumShieldItem).toList()) {
            event.registerItem(shieldExtensions, item);
        }
    }

    @OnlyIn(Dist.CLIENT)
    @EventBusSubscriber(Dist.CLIENT)
    public static class EventHandler {
        @SubscribeEvent
        public static void customHearts(PlayerHeartTypeEvent event) {
            if (event.getOriginalType() == Gui.HeartType.NORMAL) {
                if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).is(MarvelItems.Tags.WOLVERINE_ARMOR) && event.getEntity().getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.WOLVERINE_ARMOR) && event.getEntity().getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.WOLVERINE_ARMOR) && event.getEntity().getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.WOLVERINE_ARMOR)) {
                    event.setType(MarvelClientEnumExtensions.ADAMANTIUM_HEART_TYPE.getValue());
                }
                if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).is(MarvelItems.Tags.DEADPOOL_ARMOR) && event.getEntity().getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.DEADPOOL_ARMOR) && event.getEntity().getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.DEADPOOL_ARMOR) && event.getEntity().getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.DEADPOOL_ARMOR)) {
                    event.setType(MarvelClientEnumExtensions.DEADPOOL_HEART_TYPE.getValue());
                }
            }
        }
    }
}
