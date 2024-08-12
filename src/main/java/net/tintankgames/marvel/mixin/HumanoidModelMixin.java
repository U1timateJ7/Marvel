package net.tintankgames.marvel.mixin;

import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.component.Size;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidModel.class)
public abstract class HumanoidModelMixin<T extends LivingEntity> extends AgeableListModel<T> {
    @Shadow @Final public ModelPart leftArm;
    @Shadow @Final public ModelPart rightArm;

    @Inject(at = @At("RETURN"), method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V")
    private void fixHead(T living, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, CallbackInfo ci) {
        if ((living.getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.FLYING, false) || living.getItemBySlot(EquipmentSlot.MAINHAND).getOrDefault(MarvelDataComponents.FLYING, false)) && (hasArmor(living, MarvelItems.Tags.FLYING_ARMOR, true) && (!living.getItemBySlot(EquipmentSlot.CHEST).has(MarvelDataComponents.SIZE) || living.getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.SIZE, Size.NORMAL) == Size.SMALL) || living.getMainHandItem().is(MarvelItems.MJOLNIR) || living.getMainHandItem().is(MarvelItems.STORMBREAKER) || living.getOffhandItem().is(MarvelItems.MJOLNIR) || living.getOffhandItem().is(MarvelItems.STORMBREAKER))) {
            ModelPart mainArm = living.getMainArm() == HumanoidArm.LEFT ? leftArm : rightArm;
            ModelPart offArm = living.getMainArm() == HumanoidArm.LEFT ? leftArm : rightArm;
            if (living.getMainHandItem().is(MarvelItems.MJOLNIR) || living.getMainHandItem().is(MarvelItems.STORMBREAKER)) {
                mainArm.xRot -= Mth.PI - (Mth.PI / 5);
            } else if (living.getOffhandItem().is(MarvelItems.MJOLNIR) || living.getOffhandItem().is(MarvelItems.STORMBREAKER)) {
                offArm.xRot -= Mth.PI - (Mth.PI / 5);
            }
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
