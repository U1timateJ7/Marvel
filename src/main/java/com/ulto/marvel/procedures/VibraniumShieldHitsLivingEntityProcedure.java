package com.ulto.marvel.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

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

public class VibraniumShieldHitsLivingEntityProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity imediatesourceentity, Entity sourceentity) {
		if (imediatesourceentity == null || sourceentity == null)
			return;
		if (imediatesourceentity instanceof VibraniumShieldEntity) {
			if (sourceentity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.VIBRANIUM_SHIELD);
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof CapsShieldRedEntity) {
			if (sourceentity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.CAPTAIN_AMERICAS_SHIELD_RED);
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof CapsShieldBlueEntity) {
			if (sourceentity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.CAPTAIN_AMERICAS_SHIELD_BLUE);
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof BloodyVibraniumShieldEntity) {
			if (sourceentity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.BLOODY_VIBRANIUM_SHIELD);
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof BloodyCapsShieldRedEntity) {
			if (sourceentity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.BLOODY_CAPTAIN_AMERICAS_SHIELD_RED);
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof BloodyCapsShieldBlueEntity) {
			if (sourceentity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.BLOODY_CAPTAIN_AMERICAS_SHIELD_BLUE);
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof RedGuardianShieldEntity) {
			if (sourceentity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.RED_GUARDIAN_SHIELD);
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof BloodyRedGuardianShieldEntity) {
			if (sourceentity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.BLOODY_RED_GUARDIAN_SHIELD);
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof TaskmasterShieldEntity) {
			if (sourceentity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.TASKMASTER_SHIELD);
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (imediatesourceentity instanceof BloodyTaskmasterShieldEntity) {
			if (sourceentity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.BLOODY_TASKMASTER_SHIELD);
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
	}
}
