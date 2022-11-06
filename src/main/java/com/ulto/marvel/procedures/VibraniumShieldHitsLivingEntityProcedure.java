package com.ulto.marvel.procedures;

import com.ulto.marvel.world.entity.*;
import com.ulto.marvel.world.item.MarvelModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.items.ItemHandlerHelper;

public class VibraniumShieldHitsLivingEntityProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity imediatesourceentity, Entity sourceentity) {
		if (imediatesourceentity == null || sourceentity == null)
			return;
		if (imediatesourceentity instanceof VibraniumShieldEntity) {
			if (sourceentity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.VIBRANIUM_SHIELD.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof CapsShieldRedEntity) {
			if (sourceentity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.CAPTAIN_AMERICAS_SHIELD_RED.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof CapsShieldBlueEntity) {
			if (sourceentity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.CAPTAIN_AMERICAS_SHIELD_BLUE.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof CaptainCartersShieldEntity) {
			if (sourceentity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.CAPTAIN_CARTERS_SHIELD.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof USAgentsShieldEntity) {
			if (sourceentity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.US_AGENTS_SHIELD.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof RedGuardianShieldEntity) {
			if (sourceentity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.RED_GUARDIAN_SHIELD.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof TaskmasterShieldEntity) {
			if (sourceentity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.TASKMASTER_SHIELD.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof BloodyVibraniumShieldEntity) {
			if (sourceentity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.BLOODY_VIBRANIUM_SHIELD.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof BloodyCapsShieldRedEntity) {
			if (sourceentity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.BLOODY_CAPTAIN_AMERICAS_SHIELD_RED.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof BloodyCapsShieldBlueEntity) {
			if (sourceentity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.BLOODY_CAPTAIN_AMERICAS_SHIELD_BLUE.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof BloodyCaptainCartersShieldEntity) {
			if (sourceentity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.BLOODY_CAPTAIN_CARTERS_SHIELD.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof BloodyUSAgentsShieldEntity) {
			if (sourceentity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.BLOODY_US_AGENTS_SHIELD.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof BloodyRedGuardianShieldEntity) {
			if (sourceentity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.BLOODY_RED_GUARDIAN_SHIELD.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof BloodyTaskmasterShieldEntity) {
			if (sourceentity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.BLOODY_TASKMASTER_SHIELD.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
	}
}
