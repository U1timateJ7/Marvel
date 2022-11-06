
package com.ulto.marvel.world.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.server.level.ServerPlayer;

import com.ulto.marvel.world.entity.WarMachineGunEntity;

public class WarMachineGunItem extends Item {
	public WarMachineGunItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		entity.startUsingItem(hand);
		return new InteractionResultHolder(InteractionResult.SUCCESS, entity.getItemInHand(hand));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.NONE;
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 72000;
	}

	@Override
	public void onUsingTick(ItemStack itemstack, LivingEntity entityLiving, int count) {
		Level world = entityLiving.level;
		if (!world.isClientSide() && entityLiving instanceof ServerPlayer entity) {
			double x = entity.getX();
			double y = entity.getY();
			double z = entity.getZ();
			ItemStack stack = ProjectileWeaponItem.getHeldProjectile(entity, e -> e.getItem() == MarvelModItems.BULLET.get());
			if (stack == ItemStack.EMPTY) {
				for (int i = 0; i < entity.getInventory().items.size(); i++) {
					ItemStack teststack = entity.getInventory().items.get(i);
					if (teststack.getItem() == MarvelModItems.BULLET.get()) {
						stack = teststack;
						break;
					}
				}
			}
			if (entity.getAbilities().instabuild || stack != ItemStack.EMPTY) {
				WarMachineGunEntity entityarrow = WarMachineGunEntity.shoot(world, entity, world.getRandom(), 2f, 0.5, 1);
				itemstack.hurtAndBreak(1, entity, e -> e.broadcastBreakEvent(entity.getUsedItemHand()));
				if (entity.getAbilities().instabuild) {
					entityarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
				} else {
					if (new ItemStack(MarvelModItems.BULLET.get()).isDamageableItem()) {
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
			entity.releaseUsingItem();
		}
	}
}
