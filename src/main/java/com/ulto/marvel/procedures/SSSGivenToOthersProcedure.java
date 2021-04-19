package com.ulto.marvel.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.IWorld;
import net.minecraft.util.Hand;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.HashMap;

import com.ulto.marvel.item.SuperSoldierSerumItem;
import com.ulto.marvel.item.AntiSerumItem;
import com.ulto.marvel.MarvelModVariables;
import com.ulto.marvel.MarvelModElements;
import com.ulto.marvel.MarvelMod;

@MarvelModElements.ModElement.Tag
public class SSSGivenToOthersProcedure extends MarvelModElements.ModElement {
	public SSSGivenToOthersProcedure(MarvelModElements instance) {
		super(instance, 105);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MarvelMod.LOGGER.warn("Failed to load dependency entity for procedure SSSGivenToOthers!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				MarvelMod.LOGGER.warn("Failed to load dependency sourceentity for procedure SSSGivenToOthers!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if ((((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(SuperSoldierSerumItem.block, (int) (1)).getItem())) {
			if ((entity instanceof PlayerEntity)) {
				{
					boolean _setval = (boolean) (true);
					entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.superSoldier = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else {
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SPEED, (int) 20000000, (int) 0, (false), (false)));
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, (int) 20000000, (int) 1, (false), (false)));
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.STRENGTH, (int) 20000000, (int) 0, (false), (false)));
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.RESISTANCE, (int) 20000000, (int) 1, (false), (false)));
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.HASTE, (int) 20000000, (int) 1, (false), (false)));
			}
			if (sourceentity instanceof LivingEntity) {
				ItemStack _setstack = new ItemStack(Items.GLASS_BOTTLE, (int) (1));
				_setstack.setCount((int) 1);
				((LivingEntity) sourceentity).setHeldItem(Hand.MAIN_HAND, _setstack);
				if (sourceentity instanceof ServerPlayerEntity)
					((ServerPlayerEntity) sourceentity).inventory.markDirty();
			}
		}
		if ((((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(AntiSerumItem.block, (int) (1)).getItem())) {
			if ((entity instanceof PlayerEntity)) {
				{
					boolean _setval = (boolean) (false);
					entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.superSoldier = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).clearActivePotions();
			if (sourceentity instanceof LivingEntity) {
				ItemStack _setstack = new ItemStack(Items.GLASS_BOTTLE, (int) (1));
				_setstack.setCount((int) 1);
				((LivingEntity) sourceentity).setHeldItem(Hand.MAIN_HAND, _setstack);
				if (sourceentity instanceof ServerPlayerEntity)
					((ServerPlayerEntity) sourceentity).inventory.markDirty();
			}
		}
	}

	@SubscribeEvent
	public void onRightClickEntity(PlayerInteractEvent.EntityInteract event) {
		Entity entity = event.getTarget();
		PlayerEntity sourceentity = event.getPlayer();
		if (event.getHand() != sourceentity.getActiveHand()) {
			return;
		}
		double i = event.getPos().getX();
		double j = event.getPos().getY();
		double k = event.getPos().getZ();
		IWorld world = event.getWorld();
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
