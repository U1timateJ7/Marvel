package com.ulto.marvel.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.BlockTags;
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

@MarvelModElements.ModElement.Tag
public class VibraniumShieldHitsBlockProcedure extends MarvelModElements.ModElement {
	public VibraniumShieldHitsBlockProcedure(MarvelModElements instance) {
		super(instance, 38);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MarvelMod.LOGGER.warn("Failed to load dependency entity for procedure VibraniumShieldHitsBlock!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MarvelMod.LOGGER.warn("Failed to load dependency x for procedure VibraniumShieldHitsBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MarvelMod.LOGGER.warn("Failed to load dependency y for procedure VibraniumShieldHitsBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MarvelMod.LOGGER.warn("Failed to load dependency z for procedure VibraniumShieldHitsBlock!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MarvelMod.LOGGER.warn("Failed to load dependency world for procedure VibraniumShieldHitsBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity shield = (Entity) dependencies.get("shield");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (shield instanceof VibraniumShieldItem.ArrowCustomEntity) {
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(VibraniumShieldItem.block, (int) (1));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
		} else if (shield instanceof CapsShieldRedItem.ArrowCustomEntity) {
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(CapsShieldRedItem.block, (int) (1));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
		} else if (shield instanceof CapsShieldBlueItem.ArrowCustomEntity) {
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(CapsShieldBlueItem.block, (int) (1));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
		} else if (shield instanceof BloodyVibraniumShieldItem.ArrowCustomEntity) {
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(BloodyVibraniumShieldItem.block, (int) (1));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
		} else if (shield instanceof BloodyCapsShieldRedItem.ArrowCustomEntity) {
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(BloodyCapsShieldRedItem.block, (int) (1));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
		} else if (shield instanceof BloodyCapsShieldBlueItem.ArrowCustomEntity) {
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(BloodyCapsShieldBlueItem.block, (int) (1));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
		}
		if ((BlockTags.getCollection().getTagByID(new ResourceLocation(("forge:glass_panes").toLowerCase(java.util.Locale.ENGLISH)))
				.contains((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))) {
			world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
		}
	}
}
