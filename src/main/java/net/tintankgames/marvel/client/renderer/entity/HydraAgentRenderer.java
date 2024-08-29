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
import net.tintankgames.marvel.world.entity.HydraAgent;
import net.tintankgames.marvel.world.entity.HydraAgentSkin;

public class HydraAgentRenderer extends MobRenderer<HydraAgent, PlayerModel<HydraAgent>> {
    protected final PlayerModel<HydraAgent> wideModel;
    protected final PlayerModel<HydraAgent> slimModel;

    public HydraAgentRenderer(EntityRendererProvider.Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), false), 0.5F);
        this.wideModel = new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), false);
        this.slimModel = new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER_SLIM), true);
        this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidArmorModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidArmorModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
        this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
        this.addLayer(new ArrowLayer<>(context, this));
        this.addLayer(new CustomHeadLayer<>(this, context.getModelSet(), context.getItemInHandRenderer()));
        this.addLayer(new ElytraLayer<>(this, context.getModelSet()));
    }

    public void render(HydraAgent hydraAgent, float p_117789_, float p_117790_, PoseStack p_117791_, MultiBufferSource p_117792_, int p_117793_) {
        if (hydraAgent.getSkin().value().model() == HydraAgentSkin.Model.SLIM) {
            this.model = this.slimModel;
        } else {
            this.model = this.wideModel;
        }
        this.setModelProperties(hydraAgent);
        super.render(hydraAgent, p_117789_, p_117790_, p_117791_, p_117792_, p_117793_);
    }

    public Vec3 getRenderOffset(HydraAgent hydraAgent, float p_117786_) {
        return hydraAgent.isCrouching() ? new Vec3(0.0, (double)(hydraAgent.getScale() * -2.0F) / 16.0, 0.0) : super.getRenderOffset(hydraAgent, p_117786_);
    }

    private void setModelProperties(HydraAgent hydraAgent) {
        PlayerModel<HydraAgent> playermodel = this.getModel();
        playermodel.setAllVisible(true);
        playermodel.crouching = hydraAgent.isCrouching();
        HumanoidModel.ArmPose mainHandPose = getArmPose(hydraAgent, InteractionHand.MAIN_HAND);
        HumanoidModel.ArmPose offHandPose = getArmPose(hydraAgent, InteractionHand.OFF_HAND);
        if (mainHandPose.isTwoHanded()) {
            offHandPose = hydraAgent.getOffhandItem().isEmpty() ? HumanoidModel.ArmPose.EMPTY : HumanoidModel.ArmPose.ITEM;
        }

        if (hydraAgent.getMainArm() == HumanoidArm.RIGHT) {
            playermodel.rightArmPose = mainHandPose;
            playermodel.leftArmPose = offHandPose;
        } else {
            playermodel.rightArmPose = offHandPose;
            playermodel.leftArmPose = mainHandPose;
        }
    }

    private static HumanoidModel.ArmPose getArmPose(HydraAgent hydraAgent, InteractionHand hand) {
        return switch (hydraAgent.getArmPose()) {
            case CROSSBOW_HOLD -> HumanoidModel.ArmPose.CROSSBOW_HOLD;
            case CROSSBOW_CHARGE -> HumanoidModel.ArmPose.CROSSBOW_CHARGE;
            default -> hydraAgent.getItemInHand(hand).isEmpty() ? HumanoidModel.ArmPose.EMPTY : HumanoidModel.ArmPose.ITEM;
        };
    }

    public ResourceLocation getTextureLocation(HydraAgent hydraAgent) {
        return hydraAgent.getVariant().value().texture(hydraAgent.getSkin().value().name());
    }

    protected void scale(HydraAgent hydraAgent, PoseStack poseStack, float p_117800_) {
        poseStack.scale(0.9375F, 0.9375F, 0.9375F);
    }
}
