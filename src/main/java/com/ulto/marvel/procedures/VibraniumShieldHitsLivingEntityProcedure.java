package com.ulto.marvel.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import com.ulto.marvel.item.VibraniumShieldItem;
import com.ulto.marvel.item.CapsShieldRedItem;
import com.ulto.marvel.item.CapsShieldBlueItem;
import com.ulto.marvel.item.BloodyVibraniumShieldItem;
import com.ulto.marvel.item.BloodyCapsShieldRedItem;
import com.ulto.marvel.item.BloodyCapsShieldBlueItem;
import com.ulto.marvel.MarvelModElements;
import com.ulto.marvel.MarvelMod;
import java.util.Random;

@MarvelModElements.ModElement.Tag
public class VibraniumShieldHitsLivingEntityProcedure extends MarvelModElements.ModElement {
	public VibraniumShieldHitsLivingEntityProcedure(MarvelModElements instance) {
		super(instance, 39);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				MarvelMod.LOGGER.warn("Failed to load dependency sourceentity for procedure VibraniumShieldHitsLivingEntity!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MarvelMod.LOGGER.warn("Failed to load dependency x for procedure VibraniumShieldHitsLivingEntity!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MarvelMod.LOGGER.warn("Failed to load dependency y for procedure VibraniumShieldHitsLivingEntity!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MarvelMod.LOGGER.warn("Failed to load dependency z for procedure VibraniumShieldHitsLivingEntity!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MarvelMod.LOGGER.warn("Failed to load dependency world for procedure VibraniumShieldHitsLivingEntity!");
			return;
		}
		Random random = new Random();
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		Entity shield = (Entity) dependencies.get("shield");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (shield instanceof VibraniumShieldItem.ArrowCustomEntity) {
			if (sourceentity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(VibraniumShieldItem.block, (int) (1));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) sourceentity), _setstack);
			}
		} else if (shield instanceof CapsShieldRedItem.ArrowCustomEntity) {
			if (sourceentity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(CapsShieldRedItem.block, (int) (1));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) sourceentity), _setstack);
			}
		} else if (shield instanceof CapsShieldBlueItem.ArrowCustomEntity) {
			if (sourceentity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(CapsShieldBlueItem.block, (int) (1));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) sourceentity), _setstack);
			}
		} else if (shield instanceof BloodyVibraniumShieldItem.ArrowCustomEntity) {
			if (sourceentity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(BloodyVibraniumShieldItem.block, (int) (1));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) sourceentity), _setstack);
			}
		} else if (shield instanceof BloodyCapsShieldRedItem.ArrowCustomEntity) {
			if (sourceentity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(BloodyCapsShieldRedItem.block, (int) (1));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) sourceentity), _setstack);
			}
		} else if (shield instanceof BloodyCapsShieldBlueItem.ArrowCustomEntity) {
			if (sourceentity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(BloodyCapsShieldBlueItem.block, (int) (1));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) sourceentity), _setstack);
			}
		}
		if (world instanceof World && !world.isRemote()) {
			((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("marvel:item.vibranium_shield.hit")),
					SoundCategory.NEUTRAL, (float) 1, (float) 1);
		} else {
			((World) world).playSound(x, y, z,
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("marvel:item.vibranium_shield.hit")),
					SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
		}
	}
}
