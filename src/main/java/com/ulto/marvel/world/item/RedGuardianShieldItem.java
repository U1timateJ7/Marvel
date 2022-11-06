
package com.ulto.marvel.world.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.ulto.marvel.world.entity.RedGuardianShieldEntity;
import com.ulto.marvel.procedures.RedGuardianShieldRangedItemUsedProcedure;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class RedGuardianShieldItem extends Item {
	public RedGuardianShieldItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		entity.startUsingItem(hand);
		return new InteractionResultHolder<>(InteractionResult.SUCCESS, entity.getItemInHand(hand));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.SPEAR;
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 72000;
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		if (slot == EquipmentSlot.MAINHAND) {
			ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
			builder.putAll(super.getAttributeModifiers(slot, stack));
			builder.put(Attributes.ATTACK_DAMAGE,
					new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Ranged item modifier", 8, AttributeModifier.Operation.ADDITION));
			builder.put(Attributes.ATTACK_SPEED,
					new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Ranged item modifier", -2.4, AttributeModifier.Operation.ADDITION));
			return builder.build();
		}
		return super.getAttributeModifiers(slot, stack);
	}

	@Override
	public void releaseUsing(ItemStack itemstack, Level world, LivingEntity entityLiving, int timeLeft) {
		if (!world.isClientSide() && entityLiving instanceof ServerPlayer entity) {
			ItemStack stack = ProjectileWeaponItem.getHeldProjectile(entity, e -> e.getItem() == this);
			if (stack == ItemStack.EMPTY) {
				for (int i = 0; i < entity.getInventory().items.size(); i++) {
					ItemStack teststack = entity.getInventory().items.get(i);
					if (teststack.getItem() == this) {
						stack = teststack;
						break;
					}
				}
			}
			if (entity.getAbilities().instabuild || stack != ItemStack.EMPTY) {
				RedGuardianShieldEntity entityarrow = RedGuardianShieldEntity.shoot(world, entity, world.getRandom(), 1f, 6, 1);
				itemstack.hurtAndBreak(1, entity, e -> e.broadcastBreakEvent(entity.getUsedItemHand()));
				if (entity.getAbilities().instabuild) {
					entityarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
				} else {
					if (new ItemStack(this).isDamageableItem()) {
						if (stack.hurt(1, world.getRandom(), entity)) {
							stack.shrink(1);
							stack.setDamageValue(0);
							if (stack.isEmpty())
								entity.getInventory().removeItem(stack);
						}
					} else {
						stack.shrink(1);
						if (stack.isEmpty())
							entity.getInventory().removeItem(stack);
					}
				}

				RedGuardianShieldRangedItemUsedProcedure.execute(entity);
			}
		}
	}
}
