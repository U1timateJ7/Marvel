package com.ulto.marvel.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import com.ulto.marvel.init.MarvelModItems;
import com.ulto.marvel.entity.VibraniumShieldEntity;
import com.ulto.marvel.entity.CapsShieldRedEntity;
import com.ulto.marvel.entity.CapsShieldBlueEntity;
import com.ulto.marvel.entity.RedGuardianShieldEntity;
import com.ulto.marvel.entity.TaskmasterShieldEntity;
import com.ulto.marvel.entity.BloodyVibraniumShieldEntity;
import com.ulto.marvel.entity.BloodyCapsShieldRedEntity;
import com.ulto.marvel.entity.BloodyCapsShieldBlueEntity;
import com.ulto.marvel.entity.BloodyRedGuardianShieldEntity;
import com.ulto.marvel.entity.BloodyTaskmasterShieldEntity;

public class VibraniumShieldHitsBlockProcedure {
	public static void execute(Entity entity, Entity imediatesourceentity) {
		if (entity == null || imediatesourceentity == null)
			return;
		if (imediatesourceentity instanceof VibraniumShieldEntity) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.VIBRANIUM_SHIELD);
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof CapsShieldRedEntity) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.CAPTAIN_AMERICAS_SHIELD_RED);
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof CapsShieldBlueEntity) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.CAPTAIN_AMERICAS_SHIELD_BLUE);
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof BloodyVibraniumShieldEntity) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.BLOODY_VIBRANIUM_SHIELD);
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof BloodyCapsShieldRedEntity) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.BLOODY_CAPTAIN_AMERICAS_SHIELD_RED);
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof BloodyCapsShieldBlueEntity) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.BLOODY_CAPTAIN_AMERICAS_SHIELD_BLUE);
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof RedGuardianShieldEntity) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.RED_GUARDIAN_SHIELD);
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof BloodyRedGuardianShieldEntity) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.BLOODY_RED_GUARDIAN_SHIELD);
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof TaskmasterShieldEntity) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.TASKMASTER_SHIELD);
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof BloodyTaskmasterShieldEntity) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.BLOODY_TASKMASTER_SHIELD);
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
	}
}
