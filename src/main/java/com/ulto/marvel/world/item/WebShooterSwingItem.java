
package com.ulto.marvel.world.item;

import com.ulto.marvel.world.entity.WebShooterSwingEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class WebShooterSwingItem extends Item {
	public WebShooterSwingItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		entity.startUsingItem(hand);
		return new InteractionResultHolder<>(InteractionResult.SUCCESS, entity.getItemInHand(hand));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(new TextComponent("Mode: Swing"));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.CROSSBOW;
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 72000;
	}

	@Override
	public void releaseUsing(ItemStack itemstack, Level world, LivingEntity entityLiving, int timeLeft) {
		if (!world.isClientSide() && entityLiving instanceof ServerPlayer entity) {
			ItemStack stack = ProjectileWeaponItem.getHeldProjectile(entity, e -> e.getItem() == MarvelModItems.WEB_FLUID.get());
			if (stack == ItemStack.EMPTY) {
				for (int i = 0; i < entity.getInventory().items.size(); i++) {
					ItemStack teststack = entity.getInventory().items.get(i);
					if (teststack.getItem() == MarvelModItems.WEB_FLUID.get()) {
						stack = teststack;
						break;
					}
				}
			}
			if (entity.getAbilities().instabuild || stack != ItemStack.EMPTY) {
				WebShooterSwingEntity entityarrow = WebShooterSwingEntity.shoot(world, entity, world.getRandom(), 2f, 0, 0);
				itemstack.hurtAndBreak(1, entity, e -> e.broadcastBreakEvent(entity.getUsedItemHand()));
				if (entity.getAbilities().instabuild) {
					entityarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
				} else {
					if (new ItemStack(MarvelModItems.WEB_FLUID.get()).isDamageableItem()) {
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
			}
		}
	}
}
