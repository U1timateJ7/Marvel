package net.tintankgames.marvel.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.entity.projectile.WebShot;
import net.tintankgames.marvel.world.item.MarvelItems;

@OnlyIn(Dist.CLIENT)
public class WebShotRenderer extends EntityRenderer<WebShot> {
    public static final ResourceLocation WEB_LOCATION = MarvelSuperheroes.id("textures/entity/web_shot.png");

    public WebShotRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    public void render(WebShot lashingPotatoHook, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        Player player = lashingPotatoHook.getPlayerOwner();
        if (player != null) {
            poseStack.pushPose();
            Vec3 vec3 = getPlayerHandPos(player, g, MarvelItems.WEB_SHOOTER.get(), this.entityRenderDispatcher);
            Vec3 vec32 = new Vec3(Mth.lerp(g, lashingPotatoHook.xo, lashingPotatoHook.getX()), Mth.lerp(g, lashingPotatoHook.yo, lashingPotatoHook.getY()) + (double)lashingPotatoHook.getEyeHeight(), Mth.lerp(g, lashingPotatoHook.zo, lashingPotatoHook.getZ()));
            float h = 0;
            float j = h * 0.15F % 1.0F;
            Vec3 vec33 = vec3.subtract(vec32);
            float k = (float)(vec33.length() + 0.1);
            vec33 = vec33.normalize();
            float l = (float)Math.acos(vec33.y);
            float m = (float)Math.atan2(vec33.z, vec33.x);
            poseStack.mulPose(Axis.YP.rotationDegrees((1.5707964F - m) * 57.295776F));
            poseStack.mulPose(Axis.XP.rotationDegrees(l * 57.295776F));
            float n = h * 0.05F * -1.5F;
            float p = Mth.cos(n + 3.1415927F) * 0.2F;
            float q = Mth.sin(n + 3.1415927F) * 0.2F;
            float r = Mth.cos(n + 0.0F) * 0.2F;
            float s = Mth.sin(n + 0.0F) * 0.2F;
            float t = Mth.cos(n + 1.5707964F) * 0.2F;
            float u = Mth.sin(n + 1.5707964F) * 0.2F;
            float v = Mth.cos(n + 4.712389F) * 0.2F;
            float w = Mth.sin(n + 4.712389F) * 0.2F;
            float aa = -1.0F + j;
            float ab = k * 2.5F + aa;
            VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(WEB_LOCATION));
            PoseStack.Pose pose = poseStack.last();
            vertex(vertexConsumer, pose, p, k, q, 0.4999F, ab);
            vertex(vertexConsumer, pose, p, 0.0F, q, 0.4999F, aa);
            vertex(vertexConsumer, pose, r, 0.0F, s, 0.0F, aa);
            vertex(vertexConsumer, pose, r, k, s, 0.0F, ab);
            vertex(vertexConsumer, pose, t, k, u, 0.4999F, ab);
            vertex(vertexConsumer, pose, t, 0.0F, u, 0.4999F, aa);
            vertex(vertexConsumer, pose, v, 0.0F, w, 0.0F, aa);
            vertex(vertexConsumer, pose, v, k, w, 0.0F, ab);
            poseStack.popPose();
            super.render(lashingPotatoHook, f, g, poseStack, multiBufferSource, i);
        }
    }

    private static void vertex(VertexConsumer vertexConsumer, PoseStack.Pose pose, float f, float g, float h, float i, float j) {
        vertexConsumer.vertex(pose, f, g, h).color(255, 255, 255, 255).uv(i, j).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(0.0F, 1.0F, 0.0F).endVertex();
    }

    public ResourceLocation getTextureLocation(WebShot lashingPotatoHook) {
        return WEB_LOCATION;
    }

    public static Vec3 getPlayerHandPos(Player player, float f, Item item, EntityRenderDispatcher entityRenderDispatcher) {
        int i = player.getMainArm() == HumanoidArm.RIGHT ? 1 : -1;
        ItemStack itemStack = player.getMainHandItem();
        if (!itemStack.is(item)) {
            i = -i;
        }

        float g = player.getAttackAnim(f);
        float h = Mth.sin(Mth.sqrt(g) * 3.1415927F);
        float j = Mth.lerp(f, player.yBodyRotO, player.yBodyRot) * 0.017453292F;
        double d = Mth.sin(j);
        double e = Mth.cos(j);
        double k = (double)i * 0.35;
        double l = 0.8;
        if (entityRenderDispatcher.options.getCameraType().isFirstPerson() && player == Minecraft.getInstance().player) {
            double n = 960.0 / (double) entityRenderDispatcher.options.fov().get();
            Vec3 vec3 = entityRenderDispatcher.camera.getNearPlane().getPointOnPlane((float)i * 0.525F, -0.1F);
            vec3 = vec3.scale(n);
            vec3 = vec3.yRot(h * 0.5F);
            vec3 = vec3.xRot(-h * 0.7F);
            return new Vec3(Mth.lerp(f, player.xo, player.getX()) + vec3.x, Mth.lerp(f, player.yo, player.getY()) + vec3.y + (double)player.getEyeHeight(), Mth.lerp((double)f, player.zo, player.getZ()) + vec3.z);
        } else {
            float m = player.isCrouching() ? -0.1875F : 0.0F;
            return new Vec3(Mth.lerp(f, player.xo, player.getX()) - e * k - d * 0.8, player.yo + (double)player.getEyeHeight() + (player.getY() - player.yo) * (double)f - 0.45 + (double)m, Mth.lerp((double)f, player.zo, player.getZ()) - d * k + e * 0.8);
        }
    }
}
