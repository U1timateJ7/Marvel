package net.tintankgames.marvel.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.client.model.IgorModel;
import net.tintankgames.marvel.client.model.MarvelModels;
import net.tintankgames.marvel.client.renderer.entity.layers.GlowLayer;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.entity.IronManMark38;
import net.tintankgames.marvel.world.item.EnergySuitItem;

@OnlyIn(Dist.CLIENT)
public class IgorRenderer extends MobRenderer<IronManMark38, IgorModel<IronManMark38>> {
    public static final ResourceLocation IRON_MAN_MARK_38_LOCATION = MarvelSuperheroes.id("textures/entity/igor/iron_man_mark_38.png");
    public static final ResourceLocation IRON_MAN_MARK_38_OPEN_LOCATION = MarvelSuperheroes.id("textures/entity/igor/iron_man_mark_38_open.png");
    public static final ResourceLocation IRON_MAN_MARK_38_GLOW_LOCATION = MarvelSuperheroes.id("textures/entity/igor/iron_man_mark_38_glow.png");
    public static final ResourceLocation IRON_MAN_MARK_38_OPEN_GLOW_LOCATION = MarvelSuperheroes.id("textures/entity/igor/iron_man_mark_38_open_glow.png");

    public IgorRenderer(EntityRendererProvider.Context context) {
        super(context, new IgorModel<>(context.bakeLayer(MarvelModels.IRON_MAN_MARK_38)), 1.0F);
        this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
        this.addLayer(new GlowLayer<>(this, IRON_MAN_MARK_38_GLOW_LOCATION, igor -> EnergySuitItem.getEnergy(igor.getItemBySlot(EquipmentSlot.CHEST)) > 0.0F && !igor.getItemBySlot(EquipmentSlot.HEAD).getOrDefault(MarvelDataComponents.HELMET_OPEN, false)));
        this.addLayer(new GlowLayer<>(this, IRON_MAN_MARK_38_OPEN_GLOW_LOCATION, igor -> EnergySuitItem.getEnergy(igor.getItemBySlot(EquipmentSlot.CHEST)) > 0.0F && igor.getItemBySlot(EquipmentSlot.HEAD).getOrDefault(MarvelDataComponents.HELMET_OPEN, false)));
    }

    public void render(IronManMark38 ironManMark38, float p_117789_, float p_117790_, PoseStack p_117791_, MultiBufferSource p_117792_, int p_117793_) {
        this.setModelProperties(ironManMark38);
        super.render(ironManMark38, p_117789_, p_117790_, p_117791_, p_117792_, p_117793_);
    }

    public Vec3 getRenderOffset(IronManMark38 ironManMark38, float p_117786_) {
        return ironManMark38.isCrouching() ? new Vec3(0.0, (double)(ironManMark38.getScale() * -2.0F) / 16.0, 0.0) : super.getRenderOffset(ironManMark38, p_117786_);
    }

    private void setModelProperties(IronManMark38 ironManMark38) {
        IgorModel igorModel = this.getModel();
        igorModel.setAllVisible(true);
        igorModel.crouching = ironManMark38.isCrouching();
        HumanoidModel.ArmPose mainHandPose = getArmPose(ironManMark38, InteractionHand.MAIN_HAND);
        HumanoidModel.ArmPose offHandPose = getArmPose(ironManMark38, InteractionHand.OFF_HAND);
        if (mainHandPose.isTwoHanded()) {
            offHandPose = ironManMark38.getOffhandItem().isEmpty() ? HumanoidModel.ArmPose.EMPTY : HumanoidModel.ArmPose.ITEM;
        }

        if (ironManMark38.getMainArm() == HumanoidArm.RIGHT) {
            igorModel.rightArmPose = mainHandPose;
            igorModel.leftArmPose = offHandPose;
        } else {
            igorModel.rightArmPose = offHandPose;
            igorModel.leftArmPose = mainHandPose;
        }
    }

    private static HumanoidModel.ArmPose getArmPose(IronManMark38 ironManMark38, InteractionHand hand) {
        if (ironManMark38.firingRepulsor()) return HumanoidModel.ArmPose.BOW_AND_ARROW;
        return ironManMark38.getItemInHand(hand).isEmpty() ? HumanoidModel.ArmPose.EMPTY : HumanoidModel.ArmPose.ITEM;
    }

    public ResourceLocation getTextureLocation(IronManMark38 ironManMark38) {
        return ironManMark38.getItemBySlot(EquipmentSlot.HEAD).getOrDefault(MarvelDataComponents.HELMET_OPEN, false) ? IRON_MAN_MARK_38_OPEN_LOCATION : IRON_MAN_MARK_38_LOCATION;
    }

    protected void scale(IronManMark38 ironManMark38, PoseStack poseStack, float p_117800_) {
        poseStack.scale(0.9375F, 0.9375F, 0.9375F);
    }
}
