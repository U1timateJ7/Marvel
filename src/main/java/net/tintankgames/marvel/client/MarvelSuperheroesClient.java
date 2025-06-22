package net.tintankgames.marvel.client;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.SheetedDecalTextureGenerator;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.FastColor;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RenderHighlightEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerHeartTypeEvent;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.client.gui.screens.SpaceStoneScreen;
import net.tintankgames.marvel.client.gui.screens.VeronicaScreen;
import net.tintankgames.marvel.client.model.SuitModel;
import net.tintankgames.marvel.client.renderer.item.NecklaceRenderer;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.mixin.LevelRendererAccessor;
import net.tintankgames.marvel.network.ClientUtils;
import net.tintankgames.marvel.network.MarvelNetworking;
import net.tintankgames.marvel.world.item.*;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = MarvelSuperheroes.MOD_ID, value = Dist.CLIENT)
public class MarvelSuperheroesClient {
    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        MarvelNetworking.clientUtils = new ClientUtils() {
            @Override
            public void openSpaceStone(Component name, Holder<SoundEvent> soundEvent) {
                Minecraft.getInstance().setScreen(new SpaceStoneScreen(name, soundEvent));
            }

            @Override
            public void openVeronica() {
                Minecraft.getInstance().setScreen(new VeronicaScreen());
            }
        };

        CuriosRendererRegistry.register(MarvelItems.KINETIC_BLACK_PANTHER_NECKLACE.get(), NecklaceRenderer::new);
        CuriosRendererRegistry.register(MarvelItems.KILLMONGER_NECKLACE.get(), NecklaceRenderer::new);
        CuriosRendererRegistry.register(MarvelItems.BLACK_PANTHER_SHURI_NECKLACE.get(), NecklaceRenderer::new);

        ItemBlockRenderTypes.setRenderLayer(MarvelBlocks.SPIDER_WEB.get(), RenderType.cutoutMipped());

        ItemProperties.register(
                MarvelItems.TESSERACT_CROSSBOW.get(),
                ResourceLocation.withDefaultNamespace("pull"),
                (p_351682_, p_351683_, p_351684_, p_351685_) -> {
                    if (p_351684_ == null) {
                        return 0.0F;
                    } else {
                        return CrossbowItem.isCharged(p_351682_) ? 0.0F : (float)(p_351682_.getUseDuration(p_351684_) - p_351684_.getUseItemRemainingTicks()) / (float)CrossbowItem.getChargeDuration(p_351682_, p_351684_);
                    }
                }
        );
        ItemProperties.register(MarvelItems.TESSERACT_CROSSBOW.get(), ResourceLocation.withDefaultNamespace("pulling"), (p_174605_, p_174606_, p_174607_, p_174608_) -> p_174607_ != null && p_174607_.isUsingItem() && p_174607_.getUseItem() == p_174605_ && !CrossbowItem.isCharged(p_174605_) ? 1.0F : 0.0F);
        ItemProperties.register(MarvelItems.TESSERACT_CROSSBOW.get(), ResourceLocation.withDefaultNamespace("charged"), (p_275891_, p_275892_, p_275893_, p_275894_) -> CrossbowItem.isCharged(p_275891_) ? 1.0F : 0.0F);
        ItemProperties.register(MarvelItems.TESSERACT_CROSSBOW.get(), ResourceLocation.withDefaultNamespace("firework"), (p_329796_, p_329797_, p_329798_, p_329799_) -> {
            ChargedProjectiles chargedprojectiles = p_329796_.get(DataComponents.CHARGED_PROJECTILES);
            return chargedprojectiles != null && chargedprojectiles.contains(Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
        });
        ItemProperties.register(MarvelItems.REPULSOR.get(), MarvelSuperheroes.id("war_machine"), (stack, level, living, i) -> living != null && living.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.WAR_MACHINE_ARMOR) ? 1 : 0);
        ItemProperties.register(MarvelItems.UNIBEAM.get(), MarvelSuperheroes.id("war_machine"), (stack, level, living, i) -> living != null && living.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.WAR_MACHINE_ARMOR) ? 1 : 0);
        ItemProperties.register(MarvelItems.CENTURION_BLADE.get(), MarvelSuperheroes.id("iron_man_mark_30"), (stack, level, living, i) -> living != null && living.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.IRON_MAN_MARK_30_ARMOR) ? 1 : 0);
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
        IClientItemExtensions clawsExtensions = new IClientItemExtensions() {
            @Nullable
            @Override
            public HumanoidModel.ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack stack) {
                return entityLiving.getItemInHand(hand) == stack && entityLiving.getData(MarvelAttachmentTypes.HELD_ENTITY).entity != null ? MarvelClientEnumExtensions.CLAWS_HOLD_POSE.getValue() : IClientItemExtensions.super.getArmPose(entityLiving, hand, stack);
            }
        };
        for (Item item : BuiltInRegistries.ITEM.stream().filter(item -> item instanceof SuitItem).toList()) {
            event.registerItem(suitExtensions, item);
        }
        for (Item item : BuiltInRegistries.ITEM.stream().filter(item -> item instanceof VibraniumShieldItem).toList()) {
            event.registerItem(shieldExtensions, item);
        }
        for (Item item : BuiltInRegistries.ITEM.stream().filter(item -> item instanceof DisasterRescueClawsItem).toList()) {
            event.registerItem(clawsExtensions, item);
        }
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
                    event.setType(MarvelClientEnumExtensions.ADAMANTIUM_HEART_TYPE.getValue());
                }
                if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).is(MarvelItems.Tags.DEADPOOL_ARMOR) && event.getEntity().getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.DEADPOOL_ARMOR) && event.getEntity().getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.DEADPOOL_ARMOR) && event.getEntity().getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.DEADPOOL_ARMOR)) {
                    event.setType(MarvelClientEnumExtensions.DEADPOOL_HEART_TYPE.getValue());
                }
            }
        }

        @SubscribeEvent
        public static void renderAdditionalBlockBounds(RenderHighlightEvent.Block event) {
            if (event.getTarget().getType() == HitResult.Type.BLOCK && event.getCamera().getEntity() instanceof LivingEntity living) {
                BlockHitResult hitResult = event.getTarget();
                Level level = living.level();
                BlockState state = level.getBlockState(hitResult.getBlockPos());
                ItemStack stack = living.getItemInHand(InteractionHand.MAIN_HAND);

                if (stack.getItem() instanceof MiningDrillItem drillItem && (state.is(BlockTags.MINEABLE_WITH_PICKAXE) || state.is(BlockTags.MINEABLE_WITH_SHOVEL) || state.is(BlockTags.MINEABLE_WITH_HOE)) && !state.is(BlockTags.INCORRECT_FOR_IRON_TOOL)) {
                    if (living instanceof Player player && !living.isShiftKeyDown() && !player.getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.SINGLE_BLOCK, false)) {
                        ImmutableList<BlockPos> potentialBlocks = drillItem.getExtraBlocksDug(level, player, event.getTarget());
                        List<BlockPos> breakingBlocks = new ArrayList<>();
                        for (BlockPos candidate : potentialBlocks) {
                            BlockState targetState = level.getBlockState(candidate);
                            if (drillItem.canBreakExtraBlock(level, candidate, targetState, player)) breakingBlocks.add(candidate);
                        }
                        drawAdditionalBlockBreak(event, player, breakingBlocks);
                    }
                }
            }
        }

        private static void drawAdditionalBlockBreak(RenderHighlightEvent.Block event, Player player, Collection<BlockPos> blocks) {
            Vec3 camera = event.getCamera().getPosition();
            for (BlockPos pos : blocks) {
                ((LevelRendererAccessor) event.getLevelRenderer()).invokeRenderHitOutline(event.getPoseStack(), event.getMultiBufferSource().getBuffer(RenderType.lines()), player, camera.x, camera.y, camera.z, pos, Minecraft.getInstance().level.getBlockState(pos));
            }
            PoseStack poseStack = event.getPoseStack();
            poseStack.pushPose();
            poseStack.translate(-camera.x, -camera.y, -camera.z);
            MultiPlayerGameMode gameMode = Minecraft.getInstance().gameMode;
            if (gameMode.isDestroying()) {
                int progress = gameMode.getDestroyStage();
                if (progress >= 0 && progress < ModelBakery.DESTROY_TYPES.size()) {
                    for (BlockPos blockpos : blocks) {
                        poseStack.pushPose();
                        poseStack.translate(blockpos.getX(), blockpos.getY(), blockpos.getZ());
                        VertexConsumer worldRendererIn = event.getMultiBufferSource().getBuffer(ModelBakery.DESTROY_TYPES.get(progress));
                        worldRendererIn = new SheetedDecalTextureGenerator(worldRendererIn, poseStack.last(), 1);
                        Block block = player.level().getBlockState(blockpos).getBlock();
                        boolean hasBreak = block instanceof ChestBlock || block instanceof EnderChestBlock || block instanceof SignBlock || block instanceof SkullBlock;
                        if (!hasBreak) {
                            BlockState iblockstate = player.level().getBlockState(blockpos);
                            if (!iblockstate.isAir()) Minecraft.getInstance().getBlockRenderer().renderBreakingTexture(iblockstate, blockpos, player.level(), poseStack, worldRendererIn);
                        }
                        poseStack.popPose();
                    }
                }
            }
            poseStack.popPose();
        }
    }
}
