package com.ulto.marvel.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import java.util.Random;

import com.ulto.marvel.network.MarvelModVariables;

public class Mark19SummonSummonProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		new Object() {
			private int ticks = 0;
			private float waitTicks;
			private LevelAccessor world;

			public void start(LevelAccessor world, int waitTicks) {
				this.waitTicks = waitTicks;
				MinecraftForge.EVENT_BUS.register(this);
				this.world = world;
			}

			@SubscribeEvent
			public void tick(TickEvent.ServerTickEvent event) {
				if (event.phase == TickEvent.Phase.END) {
					this.ticks += 1;
					if (this.ticks >= this.waitTicks)
						run();
				}
			}

			private void run() {
				if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).mark19Ready) {
					Mark19SummonProcedure.execute(world, x, z, entity);
				}
				MinecraftForge.EVENT_BUS.unregister(this);
			}
		}.start(world, (int) (new Random().nextInt(60 + 1) + 30));
	}
}
