package net.tintankgames.marvel.mixin;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Streams;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import net.tintankgames.marvel.attachment.EntitySuit;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.entity.IronManSuitPart;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.SummonableIronManSuitItem;
import net.tintankgames.marvel.world.item.component.Size;
import net.tintankgames.marvel.world.item.component.SuitParts;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {
    @Shadow public abstract void awardStat(Stat<?> p_36247_);
    @Shadow @Final private Abilities abilities;
    @Shadow protected abstract void destroyVanishingCursedItems();
    @Shadow @Final private Inventory inventory;
    @Shadow @Nullable public abstract ItemEntity drop(ItemStack p_36179_, boolean p_36180_, boolean p_36181_);

    protected PlayerMixin(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
    }

    @Inject(at = @At("TAIL"), method = "aiStep")
    private void grapple(CallbackInfo ci) {
        if (getData(MarvelAttachmentTypes.GRAPPLING.get()).entity != null && getData(MarvelAttachmentTypes.GRAPPLING.get()).entity.inBlock()) {
            this.resetFallDistance();
            if (this.isControlledByLocalInstance()) {
                Vec3 vec3 = getData(MarvelAttachmentTypes.GRAPPLING.get()).entity.position().subtract(this.getEyePosition());
                float g = getData(MarvelAttachmentTypes.GRAPPLING.get()).entity.length();
                double d = vec3.length();
                if (d > (double)g) {
                    double e = d / (double)g * 0.1;
                    this.addDeltaMovement(vec3.scale(1.0 / d).multiply(e, e * 1.1, e));
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "hurtCurrentlyUsedShield", cancellable = true)
    private void shieldDamage(float p_36383_, CallbackInfo ci) {
        if (getMainHandItem().is(MarvelItems.VIBRANIUM_SHIELD) || getOffhandItem().is(MarvelItems.VIBRANIUM_SHIELD)) {
            InteractionHand hand = getMainHandItem().is(MarvelItems.VIBRANIUM_SHIELD) ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
            ItemStack stack = getItemInHand(hand);
            if (!this.level().isClientSide) {
                awardStat(Stats.ITEM_USED.get(stack.getItem()));
            }
            if (p_36383_ >= 3.0F) {
                if (!level().isClientSide && !hasInfiniteMaterials()) {
                    stack.hurtAndBreak(1, getRandom(), this, () -> {
                        broadcastBreakEvent(getSlotForHand(hand));
                        EventHooks.onPlayerDestroyItem((Player)(Object)this, stack, hand);
                    });
                }
                if (stack.isEmpty()) {
                    if (hand == InteractionHand.MAIN_HAND) {
                        setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                    } else {
                        setItemSlot(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
                    }
                }
            }
            ci.cancel();
        }
    }

    @Inject(at = @At("RETURN"), method = "tick")
    private void removeWalkingAnimation(CallbackInfo ci) {
        if (level() instanceof ServerLevel serverLevel && Streams.stream(serverLevel.getAllEntities()).noneMatch(entity -> entity instanceof IronManSuitPart)) {
            setData(MarvelAttachmentTypes.SUMMONED_SUIT, false);
        }
        if (abilities.flying && ((marvel$hasArmor(MarvelItems.Tags.FLYING_ARMOR, true) || marvel$hasFlyingParts(false)) && (!getItemBySlot(EquipmentSlot.CHEST).has(MarvelDataComponents.SIZE) || getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.SIZE, Size.NORMAL) == Size.SMALL) || getMainHandItem().is(MarvelItems.MJOLNIR) || getMainHandItem().is(MarvelItems.STORMBREAKER) || getOffhandItem().is(MarvelItems.MJOLNIR) || getOffhandItem().is(MarvelItems.STORMBREAKER))) walkAnimation.update(0, 1.0F);
    }

    @Inject(at = @At("RETURN"), method = "getFlyingSpeed", cancellable = true)
    private void fastFlying(CallbackInfoReturnable<Float> cir) {
        if (marvel$hasArmor(MarvelItems.Tags.IRON_MAN_MARK_19_ARMOR, true) && abilities.flying) cir.setReturnValue(cir.getReturnValueF() * 2);
        if (marvel$hasFlyingParts(false) && !marvel$hasFlyingParts(true) && abilities.flying) cir.setReturnValue(cir.getReturnValueF() / 2);
    }

    @Inject(at = @At("HEAD"), method = "getDefaultDimensions", cancellable = true)
    private void suitDimensions(Pose pose, CallbackInfoReturnable<EntityDimensions> cir) {
        if (getData(MarvelAttachmentTypes.ENTITY_SUIT) != EntitySuit.NONE) {
            cir.setReturnValue(getData(MarvelAttachmentTypes.ENTITY_SUIT).dimensions().getOrDefault(pose, getData(MarvelAttachmentTypes.ENTITY_SUIT).dimensions().get(Pose.STANDING)));
        }
    }

    @Inject(at = @At("HEAD"), method = "dropEquipment", cancellable = true)
    private void ownDroppedItems(CallbackInfo ci) {
        super.dropEquipment();
        if (!this.level().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY)) {
            this.destroyVanishingCursedItems();
            for (List<ItemStack> list : ImmutableList.of(inventory.items, inventory.armor, inventory.offhand)) {
                for (int i = 0; i < list.size(); i++) {
                    ItemStack itemstack = list.get(i);
                    if (!itemstack.isEmpty()) {
                        ItemEntity entity = this.drop(itemstack, true, itemstack.getItem() instanceof SummonableIronManSuitItem);
                        if (entity != null && entity.getItem().getItem() instanceof SummonableIronManSuitItem) {
                            entity.setPickUpDelay(0);
                        }
                        list.set(i, ItemStack.EMPTY);
                    }
                }
            }
        }
        ci.cancel();
    }

    @Unique
    private boolean marvel$hasArmor(TagKey<Item> tagKey, boolean needsHead) {
        boolean head = getItemBySlot(EquipmentSlot.HEAD).is(tagKey) || !needsHead;
        boolean chest = getItemBySlot(EquipmentSlot.CHEST).is(tagKey);
        boolean legs = getItemBySlot(EquipmentSlot.LEGS).is(tagKey);
        boolean feet = getItemBySlot(EquipmentSlot.FEET).is(tagKey);
        boolean hasAllParts = Streams.stream(getArmorSlots()).allMatch(stack -> stack.getItem() instanceof ArmorItem armorItem && stack.getOrDefault(MarvelDataComponents.SUIT_PARTS, SuitParts.defaultParts(armorItem.getType(), true)).hasAllParts());
        return head && chest && legs && feet && hasAllParts;
    }

    @Unique
    private boolean marvel$hasFlyingParts(boolean bothSides) {
        SuitParts chest = getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.SUIT_PARTS, SuitParts.defaultParts(ArmorItem.Type.CHESTPLATE, false));
        SuitParts feet = getItemBySlot(EquipmentSlot.FEET).getOrDefault(MarvelDataComponents.SUIT_PARTS, SuitParts.defaultParts(ArmorItem.Type.BOOTS, false));
        if (bothSides) return chest.parts().get(3) && chest.parts().get(5) && feet.parts().get(0) && feet.parts().get(1);
        else return (chest.parts().get(3) || chest.parts().get(5)) && (feet.parts().get(0) || feet.parts().get(1));
    }
}
