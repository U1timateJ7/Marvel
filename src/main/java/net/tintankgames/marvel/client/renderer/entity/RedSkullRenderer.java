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
import net.tintankgames.marvel.world.entity.RedSkull;

@OnlyIn(Dist.CLIENT)
public class RedSkullRenderer extends MobRenderer<RedSkull, PlayerModel<RedSkull>> {
    private static final ResourceLocation RED_SKULL_LOCATION = MarvelSuperheroes.id("textures/entity/red_skull.png");

    public RedSkullRenderer(EntityRendererProvider.Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), false), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidArmorModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidArmorModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
        this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
        this.addLayer(new ArrowLayer<>(context, this));
        this.addLayer(new CustomHeadLayer<>(this, context.getModelSet(), context.getItemInHandRenderer()));
        this.addLayer(new ElytraLayer<>(this, context.getModelSet()));
    }

    public void render(RedSkull redSkull, float p_117789_, float p_117790_, PoseStack p_117791_, MultiBufferSource p_117792_, int p_117793_) {
        this.setModelProperties(redSkull);
        super.render(redSkull, p_117789_, p_117790_, p_117791_, p_117792_, p_117793_);
    }

    public Vec3 getRenderOffset(RedSkull redSkull, float p_117786_) {
        return redSkull.isCrouching() ? new Vec3(0.0, (double)(redSkull.getScale() * -2.0F) / 16.0, 0.0) : super.getRenderOffset(redSkull, p_117786_);
    }

    private void setModelProperties(RedSkull redSkull) {
        PlayerModel<RedSkull> playermodel = this.getModel();
        playermodel.setAllVisible(true);
        playermodel.crouching = redSkull.isCrouching();
        HumanoidModel.ArmPose mainHandPose = getArmPose(redSkull, InteractionHand.MAIN_HAND);
        HumanoidModel.ArmPose offHandPose = getArmPose(redSkull, InteractionHand.OFF_HAND);
        if (mainHandPose.isTwoHanded()) {
            offHandPose = redSkull.getOffhandItem().isEmpty() ? HumanoidModel.ArmPose.EMPTY : HumanoidModel.ArmPose.ITEM;
        }

        if (redSkull.getMainArm() == HumanoidArm.RIGHT) {
            playermodel.rightArmPose = mainHandPose;
            playermodel.leftArmPose = offHandPose;
        } else {
            playermodel.rightArmPose = offHandPose;
            playermodel.leftArmPose = mainHandPose;
        }
    }

    private static HumanoidModel.ArmPose getArmPose(RedSkull redSkull, InteractionHand hand) {
        if (!redSkull.getItemInHand(hand).isEmpty()) return HumanoidModel.ArmPose.ITEM;
        return HumanoidModel.ArmPose.EMPTY;
    }

    public ResourceLocation getTextureLocation(RedSkull redSkull) {
        return RED_SKULL_LOCATION;
    }

    protected void scale(RedSkull redSkull, PoseStack poseStack, float p_117800_) {
        poseStack.scale(0.9375F, 0.9375F, 0.9375F);
    }
}
