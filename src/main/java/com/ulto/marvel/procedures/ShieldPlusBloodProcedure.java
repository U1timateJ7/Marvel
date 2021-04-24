package com.ulto.marvel.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.util.Hand;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

import com.ulto.marvel.item.VibraniumShieldItem;
import com.ulto.marvel.item.TaskmasterShieldItem;
import com.ulto.marvel.item.RedGuardianShieldItem;
import com.ulto.marvel.item.CapsShieldRedItem;
import com.ulto.marvel.item.CapsShieldBlueItem;
import com.ulto.marvel.item.BloodyVibraniumShieldItem;
import com.ulto.marvel.item.BloodyTaskmasterShieldItem;
import com.ulto.marvel.item.BloodyRedGuardianShieldItem;
import com.ulto.marvel.item.BloodyCapsShieldRedItem;
import com.ulto.marvel.item.BloodyCapsShieldBlueItem;
import com.ulto.marvel.MarvelModElements;
import com.ulto.marvel.MarvelMod;

@MarvelModElements.ModElement.Tag
public class ShieldPlusBloodProcedure extends MarvelModElements.ModElement {
	public ShieldPlusBloodProcedure(MarvelModElements instance) {
		super(instance, 145);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MarvelMod.LOGGER.warn("Failed to load dependency entity for procedure ShieldPlusBlood!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				MarvelMod.LOGGER.warn("Failed to load dependency sourceentity for procedure ShieldPlusBlood!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		double randomNumber = 0;
		randomNumber = (double) ((new Random()).nextInt((int) 1 + 1));
		if (((randomNumber) == 1)) {
			if ((((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
					.getItem() == new ItemStack(VibraniumShieldItem.block, (int) (1)).getItem())) {
				if (((entity instanceof PlayerEntity) || ((entity instanceof WolfEntity) || (entity instanceof CatEntity)))) {
					if (sourceentity instanceof LivingEntity) {
						ItemStack _setstack = new ItemStack(BloodyVibraniumShieldItem.block, (int) (1));
						_setstack.setCount((int) 1);
						((LivingEntity) sourceentity).setHeldItem(Hand.MAIN_HAND, _setstack);
						if (sourceentity instanceof ServerPlayerEntity)
							((ServerPlayerEntity) sourceentity).inventory.markDirty();
					}
				}
			} else if ((((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
					.getItem() == new ItemStack(CapsShieldRedItem.block, (int) (1)).getItem())) {
				if (((entity instanceof PlayerEntity) || ((entity instanceof WolfEntity) || (entity instanceof CatEntity)))) {
					if (sourceentity instanceof LivingEntity) {
						ItemStack _setstack = new ItemStack(BloodyCapsShieldRedItem.block, (int) (1));
						_setstack.setCount((int) 1);
						((LivingEntity) sourceentity).setHeldItem(Hand.MAIN_HAND, _setstack);
						if (sourceentity instanceof ServerPlayerEntity)
							((ServerPlayerEntity) sourceentity).inventory.markDirty();
					}
				}
			} else if ((((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
					.getItem() == new ItemStack(CapsShieldBlueItem.block, (int) (1)).getItem())) {
				if (((entity instanceof PlayerEntity) || ((entity instanceof WolfEntity) || (entity instanceof CatEntity)))) {
					if (sourceentity instanceof LivingEntity) {
						ItemStack _setstack = new ItemStack(BloodyCapsShieldBlueItem.block, (int) (1));
						_setstack.setCount((int) 1);
						((LivingEntity) sourceentity).setHeldItem(Hand.MAIN_HAND, _setstack);
						if (sourceentity instanceof ServerPlayerEntity)
							((ServerPlayerEntity) sourceentity).inventory.markDirty();
					}
				}
			} else if ((((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
					.getItem() == new ItemStack(RedGuardianShieldItem.block, (int) (1)).getItem())) {
				if (((entity instanceof PlayerEntity) || ((entity instanceof WolfEntity) || (entity instanceof CatEntity)))) {
					if (sourceentity instanceof LivingEntity) {
						ItemStack _setstack = new ItemStack(BloodyRedGuardianShieldItem.block, (int) (1));
						_setstack.setCount((int) 1);
						((LivingEntity) sourceentity).setHeldItem(Hand.MAIN_HAND, _setstack);
						if (sourceentity instanceof ServerPlayerEntity)
							((ServerPlayerEntity) sourceentity).inventory.markDirty();
					}
				}
			} else if ((((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
					.getItem() == new ItemStack(TaskmasterShieldItem.block, (int) (1)).getItem())) {
				if (((entity instanceof PlayerEntity) || ((entity instanceof WolfEntity) || (entity instanceof CatEntity)))) {
					if (sourceentity instanceof LivingEntity) {
						ItemStack _setstack = new ItemStack(BloodyTaskmasterShieldItem.block, (int) (1));
						_setstack.setCount((int) 1);
						((LivingEntity) sourceentity).setHeldItem(Hand.MAIN_HAND, _setstack);
						if (sourceentity instanceof ServerPlayerEntity)
							((ServerPlayerEntity) sourceentity).inventory.markDirty();
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent event) {
		if (event != null && event.getEntity() != null) {
			Entity entity = event.getEntity();
			Entity sourceentity = event.getSource().getTrueSource();
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			World world = entity.world;
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("sourceentity", sourceentity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}
}
