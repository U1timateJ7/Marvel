package net.tintankgames.marvel.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
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

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {
    @Shadow public abstract void awardStat(Stat<?> p_36247_);
    @Shadow @Final private Abilities abilities;
    @Shadow public abstract ItemStack getItemBySlot(EquipmentSlot p_36257_);

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
                if (this.level() instanceof ServerLevel serverlevel && !hasInfiniteMaterials()) {
                    stack.hurtAndBreak(1, serverlevel, this, item -> {
                        this.onEquippedItemBroken(item, getSlotForHand(hand));
                        net.neoforged.neoforge.event.EventHooks.onPlayerDestroyItem((Player)(Object)this, this.useItem, hand);
                        stopUsingItem();
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
        if (abilities.flying && (hasArmor(MarvelItems.Tags.IRON_MAN_ARMOR, true) || (hasArmor(MarvelItems.Tags.WASP_ARMOR, true) && getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.SIZE, Size.NORMAL) == Size.SMALL) || getMainHandItem().is(MarvelItems.MJOLNIR) || getMainHandItem().is(MarvelItems.STORMBREAKER) || getOffhandItem().is(MarvelItems.MJOLNIR) || getOffhandItem().is(MarvelItems.STORMBREAKER))) walkAnimation.update(0, 1.0F);
    }

    @Unique
    private boolean hasArmor(TagKey<Item> tagKey, boolean needsHead) {
        boolean head = getItemBySlot(EquipmentSlot.HEAD).is(tagKey) || !needsHead;
        boolean chest = getItemBySlot(EquipmentSlot.CHEST).is(tagKey);
        boolean legs = getItemBySlot(EquipmentSlot.LEGS).is(tagKey);
        boolean feet = getItemBySlot(EquipmentSlot.FEET).is(tagKey);
        return head && chest && legs && feet;
    }
}
