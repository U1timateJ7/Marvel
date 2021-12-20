package com.ulto.marvel.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.server.level.ServerPlayer;

import com.ulto.marvel.init.MarvelModItems;

public class WebShooterSwichProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == MarvelModItems.WEB_SHOOTER_SWING) {
			if (entity instanceof LivingEntity _entity) {
				ItemStack _setstack = new ItemStack(MarvelModItems.WEB_SHOOTER_TRAP);
				_setstack.setCount(1);
				_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
				if (_entity instanceof ServerPlayer _serverPlayer)
					_serverPlayer.getInventory().setChanged();
			}
		} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
				.getItem() == MarvelModItems.WEB_SHOOTER_TRAP) {
			if (entity instanceof LivingEntity _entity) {
				ItemStack _setstack = new ItemStack(MarvelModItems.WEB_SHOOTER_SWING);
				_setstack.setCount(1);
				_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
				if (_entity instanceof ServerPlayer _serverPlayer)
					_serverPlayer.getInventory().setChanged();
			}
		}
	}
}
