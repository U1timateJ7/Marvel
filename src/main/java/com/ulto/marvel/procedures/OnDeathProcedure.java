package com.ulto.marvel.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

import com.ulto.marvel.network.MarvelModVariables;

@Mod.EventBusSubscriber
public class OnDeathProcedure {
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (event != null && event.getEntity() != null) {
			Entity entity = event.getEntity();
			execute(event, entity);
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (MarvelModVariables.getPlayerVariables(entity).hasEatenHeartShapedHerb) {
			{
				boolean _setval = false;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.hasEatenHeartShapedHerb = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
