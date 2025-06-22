package net.tintankgames.marvel.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.tintankgames.marvel.attachment.EntitySuit;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.client.model.IgorModel;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.item.EnergySuitItem;

public class EntitySuitLayer<T extends LivingEntity, M extends HumanoidModel<T>> extends RenderLayer<T, M> {
    private final IgorModel<T> igorModel;

    public EntitySuitLayer(RenderLayerParent<T, M> parent, IgorModel<T> igorModel) {
        super(parent);
        this.igorModel = igorModel;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int light, T living, float f1, float f2, float f3, float f4, float f5, float f6) {
        HumanoidModel<T> model = living.getData(MarvelAttachmentTypes.ENTITY_SUIT) == EntitySuit.IRON_MAN_MARK_38 ? this.igorModel : null;
        if (model != null) {
            model.leftArmPose = getParentModel().leftArmPose;
            model.rightArmPose = getParentModel().rightArmPose;
            model.crouching = getParentModel().crouching;
            model.young = getParentModel().young;
            model.swimAmount = getParentModel().swimAmount;
            model.attackTime = getParentModel().attackTime;
            model.riding = getParentModel().riding;
            model.prepareMobModel(living, f1, f2, f3);
            model.setupAnim(living, f1, f2, f4, f5, f6);
            copyFrom(getParentModel().head, model.head);
            copyFrom(getParentModel().hat, model.hat);
            copyFrom(getParentModel().body, model.body);
            copyFrom(getParentModel().rightArm, model.rightArm);
            copyFrom(getParentModel().leftArm, model.leftArm);
            copyFrom(getParentModel().rightLeg, model.rightLeg);
            copyFrom(getParentModel().leftLeg, model.leftLeg);
            renderColoredCutoutModel(model, living.getItemBySlot(EquipmentSlot.HEAD).getOrDefault(MarvelDataComponents.HELMET_OPEN, false) ? living.getData(MarvelAttachmentTypes.ENTITY_SUIT).openTexture() : living.getData(MarvelAttachmentTypes.ENTITY_SUIT).texture(), poseStack, multiBufferSource, light, living, -1);
            if (EnergySuitItem.getEnergy(living.getItemBySlot(EquipmentSlot.CHEST)) > 0.0F) GlowLayer.renderColoredEmissiveModel(model, living.getItemBySlot(EquipmentSlot.HEAD).getOrDefault(MarvelDataComponents.HELMET_OPEN, false) ? living.getData(MarvelAttachmentTypes.ENTITY_SUIT).openTexture().withPath(path -> path.replace(".png", "_glow.png")) : living.getData(MarvelAttachmentTypes.ENTITY_SUIT).texture().withPath(path -> path.replace(".png", "_glow.png")), poseStack, multiBufferSource, light, living, -1);
        }
    }

    public void copyFrom(ModelPart playerPart, ModelPart igorPart) {
        igorPart.xScale = playerPart.xScale;
        igorPart.yScale = playerPart.yScale;
        igorPart.zScale = playerPart.zScale;
        igorPart.xRot = playerPart.xRot;
        igorPart.yRot = playerPart.yRot;
        igorPart.zRot = playerPart.zRot;
    }
}
