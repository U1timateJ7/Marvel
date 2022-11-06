
package com.ulto.marvel.world.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.ulto.marvel.world.entity.MjolnirEntity;
import com.ulto.marvel.procedures.MjolnirUsedProcedure;
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
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class MjolnirItem extends Item {
	public MjolnirItem(Properties properties) {
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
					new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Ranged item modifier", 10, AttributeModifier.Operation.ADDITION));
			builder.put(Attributes.ATTACK_SPEED,
					new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Ranged item modifier", -2.4, AttributeModifier.Operation.ADDITION));
			return builder.build();
		}
		return super.getAttributeModifiers(slot, stack);
	}

	@Override
	public void releaseUsing(ItemStack itemstack, Level world, LivingEntity entityLiving, int timeLeft) {
		if (!world.isClientSide() && entityLiving instanceof ServerPlayer entity) {
			MjolnirEntity entityarrow = MjolnirEntity.shoot(world, entity, world.getRandom(), 2f, 3, 2);
			itemstack.hurtAndBreak(1, entity, e -> e.broadcastBreakEvent(entity.getUsedItemHand()));
			entityarrow.pickup = AbstractArrow.Pickup.DISALLOWED;

			MjolnirUsedProcedure.execute(entity);
		}
	}
}
