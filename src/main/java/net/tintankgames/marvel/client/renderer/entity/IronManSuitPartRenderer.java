package net.tintankgames.marvel.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.client.model.MarvelModels;
import net.tintankgames.marvel.client.model.SuitPartModel;
import net.tintankgames.marvel.world.entity.IronManSuitPart;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class IronManSuitPartRenderer extends MobRenderer<IronManSuitPart, SuitPartModel<IronManSuitPart>> {
    private static final ResourceLocation TEXTURE_LOCATION = MarvelSuperheroes.id("textures/models/suit/empty.png");

    public IronManSuitPartRenderer(EntityRendererProvider.Context context) {
        super(context, new SuitPartModel<>(context.bakeLayer(MarvelModels.SUIT_PART)), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidArmorModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidArmorModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
    }

    public void render(IronManSuitPart suitPart, float p_117789_, float p_117790_, PoseStack p_117791_, MultiBufferSource p_117792_, int p_117793_) {
        this.setModelProperties(suitPart);
        super.render(suitPart, p_117789_, p_117790_, p_117791_, p_117792_, p_117793_);
    }

    @Nullable
    @Override
    protected RenderType getRenderType(IronManSuitPart suitPart, boolean visible, boolean translucent, boolean glowing) {
        return null;
    }

    public Vec3 getRenderOffset(IronManSuitPart suitPart, float p_117786_) {
        return suitPart.isCrouching() ? new Vec3(0.0, (double)(suitPart.getScale() * -2.0F) / 16.0, 0.0) : super.getRenderOffset(suitPart, p_117786_);
    }

    private void setModelProperties(IronManSuitPart suitPart) {
        HumanoidModel<IronManSuitPart> humanoidModel = this.getModel();
        humanoidModel.setAllVisible(true);
        humanoidModel.crouching = suitPart.isCrouching();
        HumanoidModel.ArmPose mainHandPose = getArmPose(suitPart, InteractionHand.MAIN_HAND);
        HumanoidModel.ArmPose offHandPose = getArmPose(suitPart, InteractionHand.OFF_HAND);
        if (mainHandPose.isTwoHanded()) {
            offHandPose = suitPart.getOffhandItem().isEmpty() ? HumanoidModel.ArmPose.EMPTY : HumanoidModel.ArmPose.ITEM;
        }

        if (suitPart.getMainArm() == HumanoidArm.RIGHT) {
            humanoidModel.rightArmPose = mainHandPose;
            humanoidModel.leftArmPose = offHandPose;
        } else {
            humanoidModel.rightArmPose = offHandPose;
            humanoidModel.leftArmPose = mainHandPose;
        }
    }

    private static HumanoidModel.ArmPose getArmPose(IronManSuitPart suitPart, InteractionHand hand) {
        return suitPart.getItemInHand(hand).isEmpty() ? HumanoidModel.ArmPose.EMPTY : HumanoidModel.ArmPose.ITEM;
    }

    public ResourceLocation getTextureLocation(IronManSuitPart suitPart) {
        return TEXTURE_LOCATION;
    }

    protected void scale(IronManSuitPart suitPart, PoseStack poseStack, float p_117800_) {
        poseStack.scale(0.9375F, 0.9375F, 0.9375F);
    }
}
