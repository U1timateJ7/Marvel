package net.tintankgames.marvel.client.gui;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.effect.MarvelMobEffects;
import net.tintankgames.marvel.world.item.EnergySuitItem;
import net.tintankgames.marvel.world.item.IronManSuitItem;
import net.tintankgames.marvel.world.item.MarvelItems;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.Optional;
import java.util.UUID;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(Dist.CLIENT)
public class MarvelGui {
    private static final ResourceLocation POWDER_SNOW_OUTLINE_LOCATION = ResourceLocation.withDefaultNamespace("textures/misc/powder_snow_outline.png");

    @SubscribeEvent
    public static void renderOverlays(RenderGuiLayerEvent.Post event) {
        if (event.getName() == VanillaGuiLayers.CAMERA_OVERLAYS && Minecraft.getInstance().options.getCameraType().isFirstPerson()) {
            ItemStack helmet = Minecraft.getInstance().player.getInventory().getArmor(3);
            if (helmet.is(MarvelItems.IRON_MAN_MARK_1_HELMET) || (helmet.is(MarvelItems.Tags.IRON_MAN_ARMOR) && EnergySuitItem.getEnergy(helmet) <= 0.0F && !helmet.getOrDefault(MarvelDataComponents.HELMET_OPEN, false))) {
                renderTextureOverlay(event.getGuiGraphics(), MarvelSuperheroes.id("textures/misc/iron_man_low_power.png"), 1.0F, 0xFFFFFF);
            }
            if (Minecraft.getInstance().player.hasEffect(MarvelMobEffects.ICING)) {
                renderTextureOverlay(event.getGuiGraphics(), POWDER_SNOW_OUTLINE_LOCATION, Math.min(1.0F, (30.0F - (Minecraft.getInstance().player.getEffect(MarvelMobEffects.ICING).getDuration() + event.getPartialTick().getGameTimeDeltaTicks())) / 10.0F), 0xFFFFFF);
            }
            if (helmet.is(MarvelItems.Tags.IRON_MAN_ARMOR) && EnergySuitItem.getEnergy(helmet) > 0.0F && !helmet.getOrDefault(MarvelDataComponents.HELMET_OPEN, false) && !Minecraft.getInstance().player.hasEffect(MarvelMobEffects.ICING)) {
                renderTextureOverlay(event.getGuiGraphics(), MarvelSuperheroes.id("textures/misc/iron_man_hud.png"), 1.0F, helmet.getItem() instanceof IronManSuitItem suitItem ? suitItem.hudColor() : 0x93F6FF);
                for (int i = -2; i < 2; i++) {
                    ItemStack armor = Minecraft.getInstance().player.getInventory().armor.get(1 - i);
                    if (!armor.isEmpty()) {
                        event.getGuiGraphics().renderItem(armor, 12, event.getGuiGraphics().guiHeight() / 2 + i * 18);
                        event.getGuiGraphics().renderItemDecorations(Minecraft.getInstance().font, armor, 12, event.getGuiGraphics().guiHeight() / 2 + i * 18);
                    }
                }
                Component coordinateText = Component.translatable("gui.iron_man.coordinates", String.format("%.1f", Minecraft.getInstance().player.getX()), String.format("%.1f", Minecraft.getInstance().player.getY()), String.format("%.1f", Minecraft.getInstance().player.getZ()));
                event.getGuiGraphics().drawString(Minecraft.getInstance().font, Component.literal(String.format("%.1f", EnergySuitItem.getEnergy(helmet)) + "%"), 7, (int) (event.getGuiGraphics().guiHeight() * 0.302), helmet.getItem() instanceof IronManSuitItem suitItem ? suitItem.hudColor() : 0x93F6FF, false);
                event.getGuiGraphics().drawString(Minecraft.getInstance().font, helmet.getItem() instanceof IronManSuitItem suitItem ? suitItem.hudMark() : Component.empty(), 7, (int) (event.getGuiGraphics().guiHeight() * 0.66666667), helmet.getItem() instanceof IronManSuitItem suitItem ? suitItem.hudColor() : 0x93F6FF, false);
                event.getGuiGraphics().drawString(Minecraft.getInstance().font, coordinateText, event.getGuiGraphics().guiWidth() / 2 - Minecraft.getInstance().font.width(coordinateText) / 2, (int) (event.getGuiGraphics().guiHeight() * 0.03F), helmet.getItem() instanceof IronManSuitItem suitItem ? suitItem.hudColor() : 0x93F6FF, false);
                renderCompass(event.getGuiGraphics(), event.getPartialTick().getGameTimeDeltaTicks(), helmet.getItem() instanceof IronManSuitItem suitItem ? suitItem.hudColor() : 0x93F6FF);
                LivingEntity target = getEntityLookingAtOrTargeting(Minecraft.getInstance().player, 32.0D, 0.0F);
                if (target != null) renderTargetEntity(event.getGuiGraphics(), event.getPartialTick().getGameTimeDeltaTicks(), target, Minecraft.getInstance().player, helmet.getItem() instanceof IronManSuitItem suitItem ? suitItem.hudColor() : 0x93F6FF);
            }
        }
    }

    private static LivingEntity getEntityLookingAtOrTargeting(Player player, double distance, float rotation) {
        Entity entity = null;
        Optional<UUID> uuid = player.getData(MarvelAttachmentTypes.TARGETED_ENTITY).uuid;
        if (uuid.isPresent()) {
            entity = ((ClientLevel) player.level()).getEntities().get(uuid.get());
            if (entity instanceof LivingEntity living && !living.isRemoved()) return living;
        }
        Vec3 vec33 = player.position().add(0, 1.25, 0).add(player.getViewVector(0.0F).yRot(rotation).scale(distance));
        EntityHitResult entityHitResult = ProjectileUtil.getEntityHitResult(player.level(), player, player.getEyePosition(), vec33, player.getBoundingBox().expandTowards(player.getViewVector(0.0F).scale(distance)).inflate(1.0), entity1 -> entity1 instanceof LivingEntity);
        if (entityHitResult != null) {
            entity = entityHitResult.getEntity();
        }
        return entity instanceof LivingEntity living ? living : null;
    }

    private static void renderTargetEntity(GuiGraphics guiGraphics, float partialTick, LivingEntity entity, Player player, int color) {
        float scale = 0.75F;
        int xOffset = guiGraphics.guiWidth() - (int) (125 * scale) - 18;
        int yOffset = guiGraphics.guiHeight() / 2 - 10;
        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        poseStack.translate(xOffset, yOffset - 0.5F, 0);
        poseStack.scale(scale, scale, scale);
        float biggestEntityDimension = Math.max(entity.getBbWidth() * 1.2F + 0.3F, entity.getBbHeight() * 0.9F) * 0.85F;
        float renderScale = 38.0F;
        if ((double) biggestEntityDimension > 0.5D) {
            renderScale /= biggestEntityDimension;
        }
        renderEntityInGui(guiGraphics, 45, 56, renderScale, new Quaternionf().rotationXYZ((float) Math.toRadians(30), (float) Math.toRadians(130), (float) Math.PI), entity, partialTick);
        poseStack.popPose();
        xOffset += 10;
        yOffset -= 10;
        guiGraphics.drawString(Minecraft.getInstance().font, Component.translatable("gui.iron_man.target", entity.getName()), Minecraft.getInstance().font.width(entity.getName()) + xOffset >= guiGraphics.guiWidth() ? Minecraft.getInstance().font.width(entity.getName()) - (guiGraphics.guiWidth() - xOffset) : xOffset, yOffset, color, false);
        xOffset += 50;
        yOffset += 26;
        guiGraphics.drawString(Minecraft.getInstance().font, Component.translatable("gui.iron_man.hp", String.format("%.0f", entity.getHealth())), xOffset, yOffset - 8, color, false);
        guiGraphics.drawString(Minecraft.getInstance().font, Component.translatable("gui.iron_man.x", String.format("%.1f", entity.getX())), xOffset, yOffset, color, false);
        guiGraphics.drawString(Minecraft.getInstance().font, Component.translatable("gui.iron_man.y", String.format("%.1f", entity.getY())), xOffset, yOffset + 8, color, false);
        guiGraphics.drawString(Minecraft.getInstance().font, Component.translatable("gui.iron_man.z", String.format("%.1f", entity.getZ())), xOffset, yOffset + 16, color, false);
        xOffset -= 50;
        yOffset += 34;
        guiGraphics.drawString(Minecraft.getInstance().font, Component.translatable("gui.iron_man.distance", String.format("%.1f", entity.distanceTo(player))), xOffset, yOffset, color, false);
    }

    public static void renderEntityInGui(GuiGraphics guiGraphics, int xPos, int yPos, float scale, Quaternionf rotation, Entity entity, float partialTicks) {
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(xPos, yPos, -60.0D);
        guiGraphics.pose().mulPose(new Matrix4f().scaling(scale, scale, (-scale)));
        guiGraphics.pose().mulPose(rotation);
        Vector3f light0 = new Vector3f(1, -1.0F, -1.0F).normalize();
        Vector3f light1 = new Vector3f(-1, 1.0F, 1.0F).normalize();
        RenderSystem.setShaderLights(light0, light1);
        EntityRenderDispatcher entityrenderdispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        entityrenderdispatcher.setRenderShadow(false);
        float f = entity.yRotO + (entity.getYRot() - entity.yRotO) * partialTicks;
        if (entity instanceof LivingEntity living) {
            float f1 = living.yBodyRotO + (living.yBodyRot - living.yBodyRotO) * partialTicks;
            guiGraphics.pose().mulPose(Axis.YN.rotationDegrees(-f1));
        } else {
            guiGraphics.pose().mulPose(Axis.YN.rotationDegrees(-f));
        }
        CompoundTag nameTagDistance = null;
        if (entity instanceof LivingEntity living && living.getAttribute(NeoForgeMod.NAMETAG_DISTANCE) != null) {
            nameTagDistance = living.getAttribute(NeoForgeMod.NAMETAG_DISTANCE).save();
            living.getAttribute(NeoForgeMod.NAMETAG_DISTANCE).removeModifiers();
            living.getAttribute(NeoForgeMod.NAMETAG_DISTANCE).setBaseValue(0.0D);
        }
        RenderSystem.runAsFancy(() -> entityrenderdispatcher.render(entity, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks, guiGraphics.pose(), guiGraphics.bufferSource(), 15728880));
        if (entity instanceof LivingEntity living && nameTagDistance != null) {
            living.getAttribute(NeoForgeMod.NAMETAG_DISTANCE).load(nameTagDistance);
        }
        guiGraphics.flush();
        entityrenderdispatcher.setRenderShadow(true);
        guiGraphics.pose().popPose();
        Lighting.setupFor3DItems();
    }

    private static void renderCompass(GuiGraphics guiGraphics, float partialTicks, int color) {
        int xPos = guiGraphics.guiWidth() / 2;
        float yaw = Mth.lerp(partialTicks, Minecraft.getInstance().player.yHeadRotO, Minecraft.getInstance().player.yHeadRot) % 360;
        if (yaw < 0) yaw += 360;
        RenderSystem.enableBlend();
        drawDirection(guiGraphics, yaw, 0, xPos, color, "S");
        drawDirection(guiGraphics, yaw, 90, xPos, color, "W");
        drawDirection(guiGraphics, yaw, 180, xPos, color, "N");
        drawDirection(guiGraphics, yaw, 270, xPos, color, "E");
    }

    private static void drawDirection(GuiGraphics guiGraphics, float yaw, float angle, int xPos, int color, String text) {
        float nDist = angleDistance(yaw, angle);
        if (Math.abs(nDist) <= 90) {
            float nPos = xPos + nDist;
            guiGraphics.drawString(Minecraft.getInstance().font, text, (nPos - Minecraft.getInstance().font.width(text) / 2F), guiGraphics.guiHeight() / 10.0F, color, false);
        }
    }

    private static float angleDistance(float yaw, float other) {
        float dist = other - yaw;
        if (dist > 0) {
            return dist > 180 ? (dist - 360) : dist;
        } else {
            return dist < -180 ? (dist + 360) : dist;
        }
    }

    protected static void renderTextureOverlay(GuiGraphics guiGraphics, ResourceLocation texture, float alpha, int color) {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        guiGraphics.setColor(FastColor.ARGB32.red(color) / 255.0F, FastColor.ARGB32.green(color) / 255.0F, FastColor.ARGB32.blue(color) / 255.0F, alpha);
        guiGraphics.blit(texture, 0, 0, -90, 0.0F, 0.0F, guiGraphics.guiWidth(), guiGraphics.guiHeight(), guiGraphics.guiWidth(), guiGraphics.guiHeight());
        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
