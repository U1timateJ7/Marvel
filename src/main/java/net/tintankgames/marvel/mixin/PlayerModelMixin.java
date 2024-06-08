package net.tintankgames.marvel.mixin;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.item.MarvelItems;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerModel.class)
public abstract class PlayerModelMixin<T extends LivingEntity> extends HumanoidModel<T> {
    @Shadow @Final public ModelPart jacket;
    @Shadow @Final public ModelPart leftPants;
    @Shadow @Final public ModelPart rightPants;
    @Shadow @Final public ModelPart leftSleeve;
    @Shadow @Final public ModelPart rightSleeve;

    public PlayerModelMixin(ModelPart p_170677_) {
        super(p_170677_);
    }

    @Inject(at = @At("RETURN"), method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V")
    private void hideOuterLayer(T entity, float p_103396_, float p_103397_, float p_103398_, float p_103399_, float p_103400_, CallbackInfo ci) {
        if (!entity.getItemBySlot(EquipmentSlot.HEAD).getOrDefault(MarvelDataComponents.HELMET_OPEN, false) && entity.getItemBySlot(EquipmentSlot.HEAD).is(MarvelItems.Tags.HIDES_OUTER_LAYER)) {
            this.hat.visible = false;
        }
        if (entity.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.HIDES_OUTER_LAYER)) {
            this.leftSleeve.visible = false;
            this.rightSleeve.visible = false;
            this.jacket.visible = false;
        }
        if (entity.getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.HIDES_OUTER_LAYER) || entity.getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.HIDES_OUTER_LAYER)) {
            this.leftPants.visible = false;
            this.rightPants.visible = false;
        }
    }
}
