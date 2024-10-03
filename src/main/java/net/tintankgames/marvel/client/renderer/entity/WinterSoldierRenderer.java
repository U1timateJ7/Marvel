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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.entity.WinterSoldier;

@OnlyIn(Dist.CLIENT)
public class WinterSoldierRenderer extends MobRenderer<WinterSoldier, PlayerModel<WinterSoldier>> {
    private static final ResourceLocation WINTER_SOLDIER_LOCATION = MarvelSuperheroes.id("textures/entity/winter_soldier.png");

    public WinterSoldierRenderer(EntityRendererProvider.Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), false), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidArmorModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidArmorModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
        this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
        this.addLayer(new ArrowLayer<>(context, this));
        this.addLayer(new CustomHeadLayer<>(this, context.getModelSet(), context.getItemInHandRenderer()));
        this.addLayer(new ElytraLayer<>(this, context.getModelSet()));
    }

    public void render(WinterSoldier winterSoldier, float p_117789_, float p_117790_, PoseStack p_117791_, MultiBufferSource p_117792_, int p_117793_) {
        this.setModelProperties(winterSoldier);
        super.render(winterSoldier, p_117789_, p_117790_, p_117791_, p_117792_, p_117793_);
    }

    public Vec3 getRenderOffset(WinterSoldier winterSoldier, float p_117786_) {
        return winterSoldier.isCrouching() ? new Vec3(0.0, (double)(winterSoldier.getScale() * -2.0F) / 16.0, 0.0) : super.getRenderOffset(winterSoldier, p_117786_);
    }

    private void setModelProperties(WinterSoldier winterSoldier) {
        PlayerModel<WinterSoldier> playermodel = this.getModel();
        playermodel.setAllVisible(true);
        playermodel.crouching = winterSoldier.isCrouching();
        HumanoidModel.ArmPose mainHandPose = getArmPose(winterSoldier, InteractionHand.MAIN_HAND);
        HumanoidModel.ArmPose offHandPose = getArmPose(winterSoldier, InteractionHand.OFF_HAND);
        if (mainHandPose.isTwoHanded()) {
            offHandPose = winterSoldier.getOffhandItem().isEmpty() ? HumanoidModel.ArmPose.EMPTY : HumanoidModel.ArmPose.ITEM;
        }

        if (winterSoldier.getMainArm() == HumanoidArm.RIGHT) {
            playermodel.rightArmPose = mainHandPose;
            playermodel.leftArmPose = offHandPose;
        } else {
            playermodel.rightArmPose = offHandPose;
            playermodel.leftArmPose = mainHandPose;
        }
    }

    private static HumanoidModel.ArmPose getArmPose(WinterSoldier winterSoldier, InteractionHand hand) {
        return switch (winterSoldier.getArmPose()) {
            case CROSSBOW_HOLD -> HumanoidModel.ArmPose.CROSSBOW_HOLD;
            case CROSSBOW_CHARGE -> HumanoidModel.ArmPose.CROSSBOW_CHARGE;
            default -> winterSoldier.getItemInHand(hand).isEmpty() ? HumanoidModel.ArmPose.EMPTY : HumanoidModel.ArmPose.ITEM;
        };
    }

    public ResourceLocation getTextureLocation(WinterSoldier winterSoldier) {
        return WINTER_SOLDIER_LOCATION;
    }

    protected void scale(WinterSoldier winterSoldier, PoseStack poseStack, float p_117800_) {
        poseStack.scale(0.9375F, 0.9375F, 0.9375F);
    }
}
