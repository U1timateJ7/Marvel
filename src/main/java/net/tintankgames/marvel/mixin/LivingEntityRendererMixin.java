package net.tintankgames.marvel.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.component.Size;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> {
    @Shadow protected M model;

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/EntityModel;setupAnim(Lnet/minecraft/world/entity/Entity;FFFFF)V", shift = At.Shift.AFTER), method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V")
    private void flyingGood(T living, float yaw, float tickDelta, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, CallbackInfo ci) {
        if ((living.getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.FLYING, false) || living.getItemBySlot(EquipmentSlot.MAINHAND).getOrDefault(MarvelDataComponents.FLYING, false) || living.getItemBySlot(EquipmentSlot.OFFHAND).getOrDefault(MarvelDataComponents.FLYING, false)) && (hasArmor(living, MarvelItems.Tags.FLYING_ARMOR, true) && (!living.getItemBySlot(EquipmentSlot.CHEST).has(MarvelDataComponents.SIZE) || living.getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.SIZE, Size.NORMAL) == Size.SMALL) || living.getMainHandItem().is(MarvelItems.MJOLNIR) || living.getMainHandItem().is(MarvelItems.STORMBREAKER) || living.getOffhandItem().is(MarvelItems.MJOLNIR) || living.getOffhandItem().is(MarvelItems.STORMBREAKER))) {
            double d = 1 / (living instanceof Player player ? player.getAbilities().getFlyingSpeed() / 0.05 : 1);
            Vec3 deltaMovement = living instanceof LocalPlayer player ? player.getDeltaMovementLerped(tickDelta) : (living.getItemBySlot(EquipmentSlot.CHEST).has(MarvelDataComponents.DELTA_MOVEMENT) ? living.getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.DELTA_MOVEMENT, Vec3.ZERO) : living.getItemBySlot(EquipmentSlot.MAINHAND).has(MarvelDataComponents.DELTA_MOVEMENT) ? living.getItemBySlot(EquipmentSlot.MAINHAND).getOrDefault(MarvelDataComponents.DELTA_MOVEMENT, Vec3.ZERO) : living.getItemBySlot(EquipmentSlot.OFFHAND).getOrDefault(MarvelDataComponents.DELTA_MOVEMENT, Vec3.ZERO));
            Vec3 movement = deltaMovement.yRot((Mth.lerp(tickDelta, living.yRotO, living.getYRot()) + 90) * Mth.DEG_TO_RAD).multiply(d, d, d);
            if (model instanceof HumanoidModel<?> humanoidModel) {
                humanoidModel.head.xRot -= (float) Math.clamp(movement.x, -1.0, 1.0);
                humanoidModel.hat.xRot -= (float) Math.clamp(movement.x, -1.0, 1.0);
            }
            poseStack.mulPose(Axis.XP.rotationDegrees((float) (Math.clamp(movement.x, -1.0, 1.0) * 90.0F) - (float) (movement.y * Math.clamp(movement.x, 0, 1) * 90.0F)));
            poseStack.mulPose(Axis.ZN.rotationDegrees((float) (Math.clamp(movement.z, -1.0, 1.0) * 90.0F) / 3));
        }
    }

    @Unique
    private boolean hasArmor(LivingEntity living, TagKey<Item> tagKey, boolean needsHead) {
        boolean head = living.getItemBySlot(EquipmentSlot.HEAD).is(tagKey) || !needsHead;
        boolean chest = living.getItemBySlot(EquipmentSlot.CHEST).is(tagKey);
        boolean legs = living.getItemBySlot(EquipmentSlot.LEGS).is(tagKey);
        boolean feet = living.getItemBySlot(EquipmentSlot.FEET).is(tagKey);
        return head && chest && legs && feet;
    }
}
