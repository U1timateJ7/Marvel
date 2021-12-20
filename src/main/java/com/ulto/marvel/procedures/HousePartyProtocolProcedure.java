package com.ulto.marvel.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import com.ulto.marvel.network.MarvelModVariables;

public class HousePartyProtocolProcedure {
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
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, new BlockPos((int) x, (int) y, (int) z),
								ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("marvel:iron_man.house_party_protocol")),
								SoundSource.NEUTRAL, 1, 1);
					} else {
						_level.playLocalSound(x, y, z,
								ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("marvel:iron_man.house_party_protocol")),
								SoundSource.NEUTRAL, 1, 1, false);
					}
				}
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
								.orElse(new MarvelModVariables.PlayerVariables())).mark21Ready) {
							Mark21SummonSummonProcedure.execute(world, x, y, z, entity);
						}
						if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).mark22Ready) {
							Mark22SummonSummonProcedure.execute(world, x, y, z, entity);
						}
						if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).mark23Ready) {
							Mark23SummonSummonProcedure.execute(world, x, y, z, entity);
						}
						if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).mark25Ready) {
							Mark25SummonSummonProcedure.execute(world, x, y, z, entity);
						}
						if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).mark30Ready) {
							Mark30SummonSummonProcedure.execute(world, x, y, z, entity);
						}
						if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).mark33Ready) {
							Mark33SummonSummonProcedure.execute(world, x, y, z, entity);
						}
						if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).mark42Ready) {
							Mark42SummonSummonProcedure.execute(world, x, y, z, entity);
						}
						if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).mark43Ready) {
							Mark43SummonSummonProcedure.execute(world, x, y, z, entity);
						}
						if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).mark46Ready) {
							Mark46SummonSummonProcedure.execute(world, x, y, z, entity);
						}
						if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).mark47Ready) {
							Mark47SummonSummonProcedure.execute(world, x, y, z, entity);
						}
						if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).ironPatriotReady) {
							IronPatriotSummonSummonProcedure.execute(world, x, y, z, entity);
						}
						if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).warMachineMark2Ready) {
							WarMachineSummonSummonProcedure.execute(world, x, y, z, entity);
						}
						if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).mark16Ready) {
							Mark16SummonSummonProcedure.execute(world, x, y, z, entity);
						}
						if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).mark17Ready) {
							Mark17SummonSummonProcedure.execute(world, x, y, z, entity);
						}
						if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).mark19Ready) {
							Mark19SummonSummonProcedure.execute(world, x, y, z, entity);
						}
						if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).mark20Ready) {
							Mark20SummonSummonProcedure.execute(world, x, y, z, entity);
						}
						if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).mark37Ready) {
							Mark37SummonSummonProcedure.execute(world, x, y, z, entity);
						}
						if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).mark39Ready) {
							Mark39SummonSummonProcedure.execute(world, x, y, z, entity);
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, 60);
				MinecraftForge.EVENT_BUS.unregister(this);
			}
		}.start(world, 20);
	}
}
