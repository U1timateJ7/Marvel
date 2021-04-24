package com.ulto.marvel.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.Difficulty;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.Entity;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

import com.ulto.marvel.MarvelModVariables;
import com.ulto.marvel.MarvelModElements;
import com.ulto.marvel.MarvelMod;

@MarvelModElements.ModElement.Tag
public class SpiderBiteProcedure extends MarvelModElements.ModElement {
	public SpiderBiteProcedure(MarvelModElements instance) {
		super(instance, 414);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MarvelMod.LOGGER.warn("Failed to load dependency entity for procedure SpiderBite!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				MarvelMod.LOGGER.warn("Failed to load dependency sourceentity for procedure SpiderBite!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MarvelMod.LOGGER.warn("Failed to load dependency world for procedure SpiderBite!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		IWorld world = (IWorld) dependencies.get("world");
		if (((entity instanceof PlayerEntity) && (sourceentity instanceof SpiderEntity))) {
			if ((world.getDifficulty() == Difficulty.EASY)) {
				if ((((new Random()).nextInt((int) 299 + 1)) == 69)) {
					if ((!(((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new MarvelModVariables.PlayerVariables())).superSoldier)
							&& ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new MarvelModVariables.PlayerVariables())).hasEatenHeartShapedHerb)))) {
						{
							boolean _setval = (boolean) (true);
							entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.radioactive = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
					}
				}
			} else if ((world.getDifficulty() == Difficulty.NORMAL)) {
				if ((((new Random()).nextInt((int) 199 + 1)) == 69)) {
					if ((!(((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new MarvelModVariables.PlayerVariables())).superSoldier)
							&& ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new MarvelModVariables.PlayerVariables())).hasEatenHeartShapedHerb)))) {
						{
							boolean _setval = (boolean) (true);
							entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.radioactive = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
					}
				}
			} else if ((world.getDifficulty() == Difficulty.HARD)) {
				if ((((new Random()).nextInt((int) 99 + 1)) == 69)) {
					if ((!(((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new MarvelModVariables.PlayerVariables())).superSoldier)
							&& ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new MarvelModVariables.PlayerVariables())).hasEatenHeartShapedHerb)))) {
						{
							boolean _setval = (boolean) (true);
							entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.radioactive = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			Entity entity = event.getEntity();
			Entity sourceentity = event.getSource().getTrueSource();
			Entity imediatesourceentity = event.getSource().getImmediateSource();
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			double amount = event.getAmount();
			World world = entity.world;
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("amount", amount);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("sourceentity", sourceentity);
			dependencies.put("imediatesourceentity", imediatesourceentity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}
}
