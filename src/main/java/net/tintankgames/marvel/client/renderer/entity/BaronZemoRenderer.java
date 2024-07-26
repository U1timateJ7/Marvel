package net.tintankgames.marvel.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.phys.Vec3;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.entity.BaronZemo;

public class BaronZemoRenderer extends MobRenderer<BaronZemo, PlayerModel<BaronZemo>> {
    private static final ResourceLocation BARON_ZEMO_LOCATION = MarvelSuperheroes.id("textures/entity/baron_zemo.png");

    public BaronZemoRenderer(EntityRendererProvider.Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), false), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidArmorModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidArmorModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
        this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
        this.addLayer(new ArrowLayer<>(context, this));
        this.addLayer(new CustomHeadLayer<>(this, context.getModelSet(), context.getItemInHandRenderer()));
        this.addLayer(new ElytraLayer<>(this, context.getModelSet()));
    }

    public void render(BaronZemo baronZemo, float p_117789_, float p_117790_, PoseStack p_117791_, MultiBufferSource p_117792_, int p_117793_) {
        this.setModelProperties(baronZemo);
        super.render(baronZemo, p_117789_, p_117790_, p_117791_, p_117792_, p_117793_);
    }

    public Vec3 getRenderOffset(BaronZemo baronZemo, float p_117786_) {
        return baronZemo.isCrouching() ? new Vec3(0.0, (double)(baronZemo.getScale() * -2.0F) / 16.0, 0.0) : super.getRenderOffset(baronZemo, p_117786_);
    }

    private void setModelProperties(BaronZemo baronZemo) {
        PlayerModel<BaronZemo> playermodel = this.getModel();
        playermodel.setAllVisible(true);
        playermodel.crouching = baronZemo.isCrouching();
        HumanoidModel.ArmPose mainHandPose = getArmPose(baronZemo, InteractionHand.MAIN_HAND);
        HumanoidModel.ArmPose offHandPose = getArmPose(baronZemo, InteractionHand.OFF_HAND);
        if (mainHandPose.isTwoHanded()) {
            offHandPose = baronZemo.getOffhandItem().isEmpty() ? HumanoidModel.ArmPose.EMPTY : HumanoidModel.ArmPose.ITEM;
        }

        if (baronZemo.getMainArm() == HumanoidArm.RIGHT) {
            playermodel.rightArmPose = mainHandPose;
            playermodel.leftArmPose = offHandPose;
        } else {
            playermodel.rightArmPose = offHandPose;
            playermodel.leftArmPose = mainHandPose;
        }
    }

    private static HumanoidModel.ArmPose getArmPose(BaronZemo baronZemo, InteractionHand hand) {
        if (!baronZemo.getItemInHand(hand).isEmpty()) return HumanoidModel.ArmPose.ITEM;
        return HumanoidModel.ArmPose.EMPTY;
    }

    public ResourceLocation getTextureLocation(BaronZemo baronZemo) {
        return BARON_ZEMO_LOCATION;
    }

    protected void scale(BaronZemo baronZemo, PoseStack poseStack, float p_117800_) {
        poseStack.scale(0.9375F, 0.9375F, 0.9375F);
    }
}
