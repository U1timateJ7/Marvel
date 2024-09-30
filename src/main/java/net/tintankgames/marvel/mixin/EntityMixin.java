package net.tintankgames.marvel.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.item.MarvelItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow public abstract EntityType<?> getType();

    @Inject(at = @At("HEAD"), method = "playStepSound", cancellable = true)
    private void disableStepSounds(BlockPos p_20135_, BlockState p_20136_, CallbackInfo ci) {
        if ((Entity)(Object)this instanceof LivingEntity living && living.getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.SOUND_DAMPENING_BOOTS)) {
            ci.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "playMuffledStepSound", cancellable = true)
    private void disableStepSounds(BlockState p_283110_, BlockPos pos, CallbackInfo ci) {
        if ((Entity)(Object)this instanceof LivingEntity living && living.getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.SOUND_DAMPENING_BOOTS)) {
            ci.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "isInvulnerableTo", cancellable = true)
    private void thorInvulnerableToLightning(DamageSource p_20122_, CallbackInfoReturnable<Boolean> cir) {
        if ((Entity)(Object)this instanceof LivingEntity living && p_20122_.is(DamageTypeTags.IS_LIGHTNING) && marvel$hasArmor(living, MarvelItems.Tags.THOR_ARMOR, true)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(at = @At("HEAD"), method = "isInvisible", cancellable = true)
    private void invisibleOClock(CallbackInfoReturnable<Boolean> cir) {
        if ((Entity)(Object)this instanceof LivingEntity living && living.getItemBySlot(EquipmentSlot.CHEST).has(MarvelDataComponents.INVISIBLE)) {
            cir.setReturnValue(true);
        }
    }

    @Unique
    private boolean marvel$hasArmor(LivingEntity living, TagKey<Item> tagKey, boolean needsHead) {
        boolean head = living.getItemBySlot(EquipmentSlot.HEAD).is(tagKey) || !needsHead;
        boolean chest = living.getItemBySlot(EquipmentSlot.CHEST).is(tagKey);
        boolean legs = living.getItemBySlot(EquipmentSlot.LEGS).is(tagKey);
        boolean feet = living.getItemBySlot(EquipmentSlot.FEET).is(tagKey);
        return head && chest && legs && feet;
    }
}
