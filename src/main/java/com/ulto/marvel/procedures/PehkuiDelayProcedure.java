package com.ulto.marvel.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import virtuoel.pehkui.api.ScaleTypes;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class PehkuiDelayProcedure {
	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		Entity entity = event.getPlayer();
		execute(event, entity, entity.level, entity.getX(), entity.getY(), entity.getZ());
	}

	public static void execute(Entity entity, LevelAccessor world, double x, double y, double z) {
		execute(null, entity, world, x, y, z);
	}

	private static void execute(@Nullable Event event, Entity entity, LevelAccessor world, double x, double y, double z) {
		ScaleTypes.BASE.getScaleData(entity).setScaleTickDelay(4);
	}
}
