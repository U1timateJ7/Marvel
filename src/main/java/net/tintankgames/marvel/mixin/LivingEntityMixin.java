package net.tintankgames.marvel.mixin;

import com.google.common.collect.Streams;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.CommonHooks;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.sounds.MarvelSoundEvents;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;
import net.tintankgames.marvel.world.entity.projectile.TesseractCharge;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.VibraniumShieldItem;
import net.tintankgames.marvel.world.item.component.Size;
import net.tintankgames.marvel.world.item.component.SuitParts;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Optional;

@SuppressWarnings("UnreachableCode")
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow public abstract ItemStack getMainHandItem();
    @Shadow public abstract ItemStack getOffhandItem();
    @Shadow public abstract void calculateEntityAnimation(boolean p_268129_);
    @Shadow public abstract ItemStack getItemBySlot(EquipmentSlot p_21127_);
    @Shadow private Optional<BlockPos> lastClimbablePos;
    @Shadow public abstract AttributeMap getAttributes();
    @Shadow public abstract void knockback(double p_147241_, double p_147242_, double p_147243_);
    @Shadow public abstract double getAttributeValue(Holder<Attribute> p_251296_);
    @Shadow public abstract Iterable<ItemStack> getArmorSlots();

    public LivingEntityMixin(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    @Inject(at = @At("RETURN"), method = "hurt")
    private void mark24Knockback(DamageSource source, float p_21017_, CallbackInfoReturnable<Boolean> cir) {
        if (!source.is(DamageTypeTags.NO_KNOCKBACK) && cir.getReturnValueZ() && source.getDirectEntity() instanceof LivingEntity attacker && (hasArmor(attacker, MarvelItems.Tags.IRON_MAN_MARK_24_ARMOR) || hasArmor(attacker, MarvelItems.Tags.IRON_MAN_MARK_38_ARMOR))) {
            knockback(1.0, attacker.getX() - getX(), attacker.getZ() - getZ());
            if (attacker.getMainHandItem().isEmpty() || hasArmor(attacker, MarvelItems.Tags.IRON_MAN_MARK_38_ARMOR)) {
                double d0 = getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
                double d1 = Math.max(0.0, 1.0 - d0);
                addDeltaMovement(new Vec3(0.0, 0.4F * d1, 0.0));
            }
        }
    }

    @Inject(method = "hasEffect", at = @At("HEAD"), cancellable = true)
    private void nightsYourVision(Holder<MobEffect> p_316430_, CallbackInfoReturnable<Boolean> cir) {
        if (hasArmor(MarvelItems.Tags.IRON_MAN_MARK_25_ARMOR, getItemBySlot(EquipmentSlot.HEAD)) && p_316430_ == MobEffects.NIGHT_VISION) {
            cir.setReturnValue(true);
        }
    }

    @Inject(at = @At("HEAD"), method = "isDamageSourceBlocked", cancellable = true)
    private void shieldBlock(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if (source.is(DamageTypeTags.IS_PROJECTILE)) {
            if (marvel$processHand(getMainHandItem(), source.getDirectEntity()) || marvel$processHand(getOffhandItem(), source.getDirectEntity())) {
                Vec3 vec32 = source.getSourcePosition();
                if (vec32 != null) {
                    Vec3 vec3 = this.calculateViewVector(0.0F, this.getYHeadRot());
                    Vec3 vec31 = vec32.vectorTo(this.position());
                    vec31 = new Vec3(vec31.x, 0.0, vec31.z).normalize();
                    cir.setReturnValue(vec31.dot(vec3) < 0.0);
                }
            }
        } else if (source.getEntity() instanceof LivingEntity living && living.getMainHandItem().is(MarvelItems.MJOLNIR) && !source.isIndirect()) {
            if (getMainHandItem().is(MarvelItems.PROTO_ADAMANTIUM_SHIELD) || getOffhandItem().is(MarvelItems.PROTO_ADAMANTIUM_SHIELD)) {
                Vec3 vec32 = source.getSourcePosition();
                if (vec32 != null) {
                    Vec3 vec3 = this.calculateViewVector(0.0F, this.getYHeadRot());
                    Vec3 vec31 = vec32.vectorTo(this.position());
                    vec31 = new Vec3(vec31.x, 0.0, vec31.z).normalize();
                    cir.setReturnValue(vec31.dot(vec3) < 0.0);
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "handleEntityEvent", cancellable = true)
    private void shieldBlockSound(byte p_20975_, CallbackInfo ci) {
        if (p_20975_ == 29 && (marvel$isShield(getMainHandItem()) || marvel$isShield(getOffhandItem()))) {
            this.playSound(MarvelSoundEvents.VIBRANIUM_SHIELD_HIT.get(), 1.0F, 0.8F + this.level().random.nextFloat() * 0.4F);
            ci.cancel();
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;shouldDiscardFriction()Z", shift = At.Shift.BEFORE), method = "travel", locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private void grapple(Vec3 p_21280_, CallbackInfo ci, double d0, boolean flag, FluidState fluidstate, BlockPos blockpos, float f2, float f3, Vec3 vec35, double d2) {
        if (getData(MarvelAttachmentTypes.GRAPPLING).entity != null && getData(MarvelAttachmentTypes.GRAPPLING).entity.inBlock() && !this.onGround()) {
            this.setDeltaMovement(vec35.x * 0.9900000095367432, d2 * 0.9950000047683716, vec35.z * 0.9900000095367432);
            this.calculateEntityAnimation(this instanceof FlyingAnimal);
            ci.cancel();
        }
    }

    @Unique
    private static boolean marvel$processHand(ItemStack stack, Entity source) {
        if (marvel$isShield(stack)) {
            if (!stack.isDamageableItem()) {
                return !(source instanceof TesseractCharge) || source.level().getRandom().nextBoolean();
            } else {
                return source.getType().is(MarvelEntityTypes.Tags.BLOCKED_BY_VIBRANIUM_SHIELD) && !source.getData(MarvelAttachmentTypes.TESSERACT_CHARGED);
            }
        }
        return false;
    }

    @Unique
    private static boolean marvel$isShield(ItemStack stack) {
        return stack.getItem() instanceof VibraniumShieldItem;
    }

    @Inject(at = @At("HEAD"), method = "onClimbable", cancellable = true)
    private void spiderManClimbable(CallbackInfoReturnable<Boolean> cir) {
        if (hasArmor(MarvelItems.Tags.SPIDER_MAN_ARMOR, true)) {
            if (this.isSpectator()) {
                cir.setReturnValue(false);
            } else {
                BlockPos pos = this.blockPosition();
                BlockState feet = this.getInBlockState();
                if (isCurrentBlockClimbable(feet, pos) && (!hasData(MarvelAttachmentTypes.GRAPPLING.get()) || getData(MarvelAttachmentTypes.GRAPPLING.get()).entity == null)) {
                    this.lastClimbablePos = Optional.of(pos);
                    cir.setReturnValue(true);
                }
            }
        }
    }

    @Unique
    private boolean hasArmor(TagKey<Item> tagKey, boolean needsHead) {
        boolean head = getItemBySlot(EquipmentSlot.HEAD).is(tagKey) || !needsHead;
        boolean chest = getItemBySlot(EquipmentSlot.CHEST).is(tagKey);
        boolean legs = getItemBySlot(EquipmentSlot.LEGS).is(tagKey);
        boolean feet = getItemBySlot(EquipmentSlot.FEET).is(tagKey);
        boolean hasAllParts = Streams.stream(getArmorSlots()).allMatch(stack -> stack.getItem() instanceof ArmorItem armorItem && stack.getOrDefault(MarvelDataComponents.SUIT_PARTS, SuitParts.defaultParts(armorItem.getType(), true)).hasAllParts());
        return head && chest && legs && feet && (hasAllParts || !needsHead);
    }

    @Unique
    private boolean hasArmor(TagKey<Item> tagKey, ItemStack helmet) {
        boolean head = getItemBySlot(EquipmentSlot.HEAD).is(tagKey) && !helmet.getOrDefault(MarvelDataComponents.HELMET_OPEN, false);
        boolean chest = getItemBySlot(EquipmentSlot.CHEST).is(tagKey);
        boolean legs = getItemBySlot(EquipmentSlot.LEGS).is(tagKey);
        boolean feet = getItemBySlot(EquipmentSlot.FEET).is(tagKey);
        boolean hasAllParts = Streams.stream(getArmorSlots()).allMatch(stack -> stack.getItem() instanceof ArmorItem armorItem && stack.getOrDefault(MarvelDataComponents.SUIT_PARTS, SuitParts.defaultParts(armorItem.getType(), true)).hasAllParts());
        return head && chest && legs && feet && hasAllParts;
    }

    @Unique
    private boolean hasArmor(LivingEntity living, TagKey<Item> tagKey) {
        boolean head = living.getItemBySlot(EquipmentSlot.HEAD).is(tagKey);
        boolean chest = living.getItemBySlot(EquipmentSlot.CHEST).is(tagKey);
        boolean legs = living.getItemBySlot(EquipmentSlot.LEGS).is(tagKey);
        boolean feet = living.getItemBySlot(EquipmentSlot.FEET).is(tagKey);
        boolean hasAllParts = Streams.stream(living.getArmorSlots()).allMatch(stack -> stack.getItem() instanceof ArmorItem armorItem && stack.getOrDefault(MarvelDataComponents.SUIT_PARTS, SuitParts.defaultParts(armorItem.getType(), true)).hasAllParts());
        return head && chest && legs && feet && hasAllParts;
    }

    @Unique
    private boolean marvel$hasFlyingParts(boolean bothSides) {
        SuitParts chest = getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.SUIT_PARTS, SuitParts.defaultParts(ArmorItem.Type.CHESTPLATE, false));
        SuitParts feet = getItemBySlot(EquipmentSlot.FEET).getOrDefault(MarvelDataComponents.SUIT_PARTS, SuitParts.defaultParts(ArmorItem.Type.BOOTS, false));
        if (bothSides) return chest.parts().get(3) && chest.parts().get(5) && feet.parts().get(0) && feet.parts().get(1);
        else return (chest.parts().get(3) || chest.parts().get(5)) && (feet.parts().get(0) || feet.parts().get(1));
    }

    @Unique
    private boolean isCurrentBlockClimbable(BlockState state, BlockPos pos) {
        if (CommonHooks.isLivingOnLadder(state, level(), pos, (LivingEntity)(Object)this).isPresent()) return true;
        else {
            if (state.getCollisionShape(level(), pos).isEmpty()) {
                if (!level().getBlockState(pos.below()).getCollisionShape(level(), pos.below()).isEmpty()) {
                    boolean north = !level().getBlockState(pos.north()).getCollisionShape(level(), pos.north()).isEmpty() && !level().getBlockState(pos.north().above()).getCollisionShape(level(), pos.north().above()).isEmpty();
                    boolean east = !level().getBlockState(pos.east()).getCollisionShape(level(), pos.east()).isEmpty() && !level().getBlockState(pos.east().above()).getCollisionShape(level(), pos.east().above()).isEmpty();
                    boolean south = !level().getBlockState(pos.south()).getCollisionShape(level(), pos.south()).isEmpty() && !level().getBlockState(pos.south().above()).getCollisionShape(level(), pos.south().above()).isEmpty();
                    boolean west = !level().getBlockState(pos.west()).getCollisionShape(level(), pos.west()).isEmpty() && !level().getBlockState(pos.west().above()).getCollisionShape(level(), pos.west().above()).isEmpty();
                    return north || east || south || west;
                } else {
                    boolean north = !level().getBlockState(pos.north()).getCollisionShape(level(), pos.north()).isEmpty();
                    boolean east = !level().getBlockState(pos.east()).getCollisionShape(level(), pos.east()).isEmpty();
                    boolean south = !level().getBlockState(pos.south()).getCollisionShape(level(), pos.south()).isEmpty();
                    boolean west = !level().getBlockState(pos.west()).getCollisionShape(level(), pos.west()).isEmpty();
                    boolean north1 = !level().getBlockState(pos.north().above()).getCollisionShape(level(), pos.north().above()).isEmpty();
                    boolean east1 = !level().getBlockState(pos.east().above()).getCollisionShape(level(), pos.east().above()).isEmpty();
                    boolean south1 = !level().getBlockState(pos.south().above()).getCollisionShape(level(), pos.south().above()).isEmpty();
                    boolean west1 = !level().getBlockState(pos.west().above()).getCollisionShape(level(), pos.west().above()).isEmpty();
                    boolean north2 = !level().getBlockState(pos.north().above(2)).getCollisionShape(level(), pos.north().above(2)).isEmpty();
                    boolean east2 = !level().getBlockState(pos.east().above(2)).getCollisionShape(level(), pos.east().above(2)).isEmpty();
                    boolean south2 = !level().getBlockState(pos.south().above(2)).getCollisionShape(level(), pos.south().above(2)).isEmpty();
                    boolean west2 = !level().getBlockState(pos.west().above(2)).getCollisionShape(level(), pos.west().above(2)).isEmpty();
                    boolean above = !level().getBlockState(pos.above(2)).getCollisionShape(level(), pos.above(2)).isEmpty();
                    return north || east || south || west || north1 || east1 || south1 || west1 || north2 || east2 || south2 || west2 || above;
                }
            } else {
                if (!level().getBlockState(pos.below()).getCollisionShape(level(), pos.below()).isEmpty()) {
                    boolean north = !level().getBlockState(pos.north().above()).getCollisionShape(level(), pos.north().above()).isEmpty() && !level().getBlockState(pos.north().above(2)).getCollisionShape(level(), pos.north().above(2)).isEmpty();
                    boolean east = !level().getBlockState(pos.east().above()).getCollisionShape(level(), pos.east().above()).isEmpty() && !level().getBlockState(pos.east().above(2)).getCollisionShape(level(), pos.east().above(2)).isEmpty();
                    boolean south = !level().getBlockState(pos.south().above()).getCollisionShape(level(), pos.south().above()).isEmpty() && !level().getBlockState(pos.south().above(2)).getCollisionShape(level(), pos.south().above(2)).isEmpty();
                    boolean west = !level().getBlockState(pos.west().above()).getCollisionShape(level(), pos.west().above()).isEmpty() && !level().getBlockState(pos.west().above(2)).getCollisionShape(level(), pos.west().above(2)).isEmpty();
                    return north || east || south || west;
                } else {
                    boolean north = !level().getBlockState(pos.north().above()).getCollisionShape(level(), pos.north().above()).isEmpty();
                    boolean east = !level().getBlockState(pos.east().above()).getCollisionShape(level(), pos.east().above()).isEmpty();
                    boolean south = !level().getBlockState(pos.south().above()).getCollisionShape(level(), pos.south().above()).isEmpty();
                    boolean west = !level().getBlockState(pos.west().above()).getCollisionShape(level(), pos.west().above()).isEmpty();
                    boolean north1 = !level().getBlockState(pos.north().above(2)).getCollisionShape(level(), pos.north().above(2)).isEmpty();
                    boolean east1 = !level().getBlockState(pos.east().above(2)).getCollisionShape(level(), pos.east().above(2)).isEmpty();
                    boolean south1 = !level().getBlockState(pos.south().above(2)).getCollisionShape(level(), pos.south().above(2)).isEmpty();
                    boolean west1 = !level().getBlockState(pos.west().above(2)).getCollisionShape(level(), pos.west().above(2)).isEmpty();
                    boolean north2 = !level().getBlockState(pos.north().above(3)).getCollisionShape(level(), pos.north().above(3)).isEmpty();
                    boolean east2 = !level().getBlockState(pos.east().above(3)).getCollisionShape(level(), pos.east().above(3)).isEmpty();
                    boolean south2 = !level().getBlockState(pos.south().above(3)).getCollisionShape(level(), pos.south().above(3)).isEmpty();
                    boolean west2 = !level().getBlockState(pos.west().above(3)).getCollisionShape(level(), pos.west().above(3)).isEmpty();
                    boolean above = !level().getBlockState(pos.above(3)).getCollisionShape(level(), pos.above(3)).isEmpty();
                    return north || east || south || west || north1 || east1 || south1 || west1 || north2 || east2 || south2 || west2 || above;
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "canStandOnFluid", cancellable = true)
    private void jesusMoment(FluidState fluidState, CallbackInfoReturnable<Boolean> cir) {
        if (hasArmor(MarvelItems.Tags.QUICKSILVER_ARMOR, false) && getItemBySlot(EquipmentSlot.CHEST).has(MarvelDataComponents.SPEEDING) && getData(MarvelAttachmentTypes.MOVING)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(at = @At("HEAD"), method = "updateWalkAnimation", cancellable = true)
    private void flyingBetter(float p_268283_, CallbackInfo ci) {
        if ((Object)this instanceof Player player && (player.getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.FLYING, false) || player.getItemBySlot(EquipmentSlot.MAINHAND).getOrDefault(MarvelDataComponents.FLYING, false) || player.getItemBySlot(EquipmentSlot.OFFHAND).getOrDefault(MarvelDataComponents.FLYING, false)) && ((hasArmor(MarvelItems.Tags.FLYING_ARMOR, true) || marvel$hasFlyingParts(false)) && (!getItemBySlot(EquipmentSlot.CHEST).has(MarvelDataComponents.SIZE) || getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.SIZE, Size.NORMAL) == Size.SMALL) || player.getMainHandItem().is(MarvelItems.MJOLNIR) || player.getMainHandItem().is(MarvelItems.STORMBREAKER) || player.getOffhandItem().is(MarvelItems.MJOLNIR) || player.getOffhandItem().is(MarvelItems.STORMBREAKER))) {
            ci.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "isPushable", cancellable = true)
    private void noPushingAround(CallbackInfoReturnable<Boolean> cir) {
        if (level().players().stream().anyMatch(player -> player.getData(MarvelAttachmentTypes.HELD_ENTITY).entity == this)) {
            cir.setReturnValue(false);
        }
    }
}
