package com.ulto.marvel.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.Difficulty;

import javax.annotation.Nullable;

import java.util.Random;

import com.ulto.marvel.network.MarvelModVariables;

@Mod.EventBusSubscriber
public class SpiderBiteProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			Entity entity = event.getEntity();
			execute(event, entity.level, entity, event.getSource().getEntity());
		}
	}

	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
		execute(null, world, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (entity instanceof Player && sourceentity instanceof Spider) {
			if (world.getDifficulty() == Difficulty.EASY) {
				if (new Random().nextInt(299 + 1) == 69) {
					if (!((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new MarvelModVariables.PlayerVariables())).superSoldier
							&& (entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new MarvelModVariables.PlayerVariables())).hasEatenHeartShapedHerb)) {
						{
							boolean _setval = true;
							entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.radioactive = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
					}
				}
			} else if (world.getDifficulty() == Difficulty.NORMAL) {
				if (new Random().nextInt(199 + 1) == 69) {
					if (!((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new MarvelModVariables.PlayerVariables())).superSoldier
							&& (entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new MarvelModVariables.PlayerVariables())).hasEatenHeartShapedHerb)) {
						{
							boolean _setval = true;
							entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.radioactive = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
					}
				}
			} else if (world.getDifficulty() == Difficulty.HARD) {
				if (new Random().nextInt(99 + 1) == 69) {
					if (!((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new MarvelModVariables.PlayerVariables())).superSoldier
							&& (entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new MarvelModVariables.PlayerVariables())).hasEatenHeartShapedHerb)) {
						{
							boolean _setval = true;
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
}
