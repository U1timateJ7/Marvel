package com.ulto.marvel.procedures;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.vehicle.MinecartTNT;
import net.minecraft.world.entity.vehicle.MinecartSpawner;
import net.minecraft.world.entity.vehicle.MinecartHopper;
import net.minecraft.world.entity.vehicle.MinecartFurnace;
import net.minecraft.world.entity.vehicle.MinecartCommandBlock;
import net.minecraft.world.entity.vehicle.MinecartChest;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.AbstractMinecartContainer;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

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
import com.ulto.marvel.entity.MjolnirEntity;
import com.ulto.marvel.entity.StormbreakerEntity;

@Mod.EventBusSubscriber
public class AntiProjectileDeleteProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			Entity entity = event.getEntity();
			execute(event, entity, event.getSource().getDirectEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(Entity entity, Entity imediatesourceentity, Entity sourceentity) {
		execute(null, entity, imediatesourceentity, sourceentity);
	}

	private static void execute(@Nullable Event event, Entity entity, Entity imediatesourceentity, Entity sourceentity) {
		if (entity == null || imediatesourceentity == null || sourceentity == null)
			return;
		if (entity instanceof EnderDragon || entity instanceof EndCrystal || entity instanceof Minecart || entity instanceof Boat
				|| entity instanceof ItemFrame || entity instanceof Painting || entity instanceof MinecartChest
				|| entity instanceof MinecartCommandBlock || entity instanceof AbstractMinecartContainer || entity instanceof MinecartFurnace
				|| entity instanceof MinecartHopper || entity instanceof MinecartSpawner || entity instanceof MinecartTNT
				|| entity instanceof LargeFireball) {
			if (imediatesourceentity instanceof VibraniumShieldEntity) {
				if (sourceentity instanceof Player _player) {
					ItemStack _setstack = new ItemStack(MarvelModItems.VIBRANIUM_SHIELD);
					_setstack.setCount(1);
					ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
				}
			} else if (imediatesourceentity instanceof BloodyVibraniumShieldEntity) {
				if (sourceentity instanceof Player _player) {
					ItemStack _setstack = new ItemStack(MarvelModItems.BLOODY_VIBRANIUM_SHIELD);
					_setstack.setCount(1);
					ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
				}
			} else if (imediatesourceentity instanceof CapsShieldRedEntity) {
				if (sourceentity instanceof Player _player) {
					ItemStack _setstack = new ItemStack(MarvelModItems.CAPTAIN_AMERICAS_SHIELD_RED);
					_setstack.setCount(1);
					ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
				}
			} else if (imediatesourceentity instanceof BloodyCapsShieldRedEntity) {
				if (sourceentity instanceof Player _player) {
					ItemStack _setstack = new ItemStack(MarvelModItems.BLOODY_CAPTAIN_AMERICAS_SHIELD_RED);
					_setstack.setCount(1);
					ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
				}
			} else if (imediatesourceentity instanceof CapsShieldBlueEntity) {
				if (sourceentity instanceof Player _player) {
					ItemStack _setstack = new ItemStack(MarvelModItems.CAPTAIN_AMERICAS_SHIELD_BLUE);
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
			} else if (imediatesourceentity instanceof MjolnirEntity) {
				if (sourceentity instanceof Player _player) {
					ItemStack _setstack = new ItemStack(MarvelModItems.MJOLNIR);
					_setstack.setCount(1);
					ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
				}
			} else if (imediatesourceentity instanceof StormbreakerEntity) {
				if (sourceentity instanceof Player _player) {
					ItemStack _setstack = new ItemStack(MarvelModItems.STORMBREAKER);
					_setstack.setCount(1);
					ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
				}
			}
		}
	}
}
