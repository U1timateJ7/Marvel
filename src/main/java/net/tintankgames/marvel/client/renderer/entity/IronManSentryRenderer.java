package net.tintankgames.marvel.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.phys.Vec3;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.entity.BaronZemo;
import net.tintankgames.marvel.world.entity.IronManSentry;
import org.jetbrains.annotations.Nullable;

public class IronManSentryRenderer extends MobRenderer<IronManSentry, PlayerModel<IronManSentry>> {
    private static final ResourceLocation TEXTURE_LOCATION = MarvelSuperheroes.id("textures/models/suit/empty");

    public IronManSentryRenderer(EntityRendererProvider.Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), false), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidArmorModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidArmorModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
        this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
        this.addLayer(new ArrowLayer<>(context, this));
        this.addLayer(new CustomHeadLayer<>(this, context.getModelSet(), context.getItemInHandRenderer()));
        this.addLayer(new ElytraLayer<>(this, context.getModelSet()));
    }

    public void render(IronManSentry sentry, float p_117789_, float p_117790_, PoseStack p_117791_, MultiBufferSource p_117792_, int p_117793_) {
        this.setModelProperties(sentry);
        super.render(sentry, p_117789_, p_117790_, p_117791_, p_117792_, p_117793_);
    }

    @Nullable
    @Override
    protected RenderType getRenderType(IronManSentry sentry, boolean visible, boolean translucent, boolean glowing) {
        return null;
    }

    public Vec3 getRenderOffset(IronManSentry sentry, float p_117786_) {
        return sentry.isCrouching() ? new Vec3(0.0, (double)(sentry.getScale() * -2.0F) / 16.0, 0.0) : super.getRenderOffset(sentry, p_117786_);
    }

    private void setModelProperties(IronManSentry sentry) {
        PlayerModel<IronManSentry> playermodel = this.getModel();
        playermodel.setAllVisible(true);
        playermodel.crouching = sentry.isCrouching();
        HumanoidModel.ArmPose mainHandPose = getArmPose(sentry, InteractionHand.MAIN_HAND);
        HumanoidModel.ArmPose offHandPose = getArmPose(sentry, InteractionHand.OFF_HAND);
        if (mainHandPose.isTwoHanded()) {
            offHandPose = sentry.getOffhandItem().isEmpty() ? HumanoidModel.ArmPose.EMPTY : HumanoidModel.ArmPose.ITEM;
        }

        if (sentry.getMainArm() == HumanoidArm.RIGHT) {
            playermodel.rightArmPose = mainHandPose;
            playermodel.leftArmPose = offHandPose;
        } else {
            playermodel.rightArmPose = offHandPose;
            playermodel.leftArmPose = mainHandPose;
        }
    }

    private static HumanoidModel.ArmPose getArmPose(IronManSentry sentry, InteractionHand hand) {
        if (sentry.firingRepulsor()) return HumanoidModel.ArmPose.BOW_AND_ARROW;
        return sentry.getItemInHand(hand).isEmpty() ? HumanoidModel.ArmPose.EMPTY : HumanoidModel.ArmPose.ITEM;
    }

    public ResourceLocation getTextureLocation(IronManSentry sentry) {
        return TEXTURE_LOCATION;
    }

    protected void scale(IronManSentry sentry, PoseStack poseStack, float p_117800_) {
        poseStack.scale(0.9375F, 0.9375F, 0.9375F);
    }
}
