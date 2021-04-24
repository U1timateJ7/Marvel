package com.ulto.marvel.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.HashMap;

import com.ulto.marvel.MarvelModVariables;
import com.ulto.marvel.MarvelModElements;
import com.ulto.marvel.MarvelMod;

@MarvelModElements.ModElement.Tag
public class HousePartyProtocolProcedure extends MarvelModElements.ModElement {
	public HousePartyProtocolProcedure(MarvelModElements instance) {
		super(instance, 413);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MarvelMod.LOGGER.warn("Failed to load dependency entity for procedure HousePartyProtocol!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MarvelMod.LOGGER.warn("Failed to load dependency x for procedure HousePartyProtocol!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MarvelMod.LOGGER.warn("Failed to load dependency y for procedure HousePartyProtocol!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MarvelMod.LOGGER.warn("Failed to load dependency z for procedure HousePartyProtocol!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MarvelMod.LOGGER.warn("Failed to load dependency world for procedure HousePartyProtocol!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		new Object() {
			private int ticks = 0;
			private float waitTicks;
			private IWorld world;
			public void start(IWorld world, int waitTicks) {
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
				if (world instanceof World && !world.isRemote()) {
					((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
									.getValue(new ResourceLocation("marvel:iron_man.house_party_protocol")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1);
				} else {
					((World) world).playSound(x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
									.getValue(new ResourceLocation("marvel:iron_man.house_party_protocol")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
				}
				new Object() {
					private int ticks = 0;
					private float waitTicks;
					private IWorld world;
					public void start(IWorld world, int waitTicks) {
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
						if (((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).mark21Ready)) {
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("entity", entity);
								$_dependencies.put("x", x);
								$_dependencies.put("y", y);
								$_dependencies.put("z", z);
								$_dependencies.put("world", world);
								Mark21SummonSummonProcedure.executeProcedure($_dependencies);
							}
						}
						if (((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).mark22Ready)) {
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("entity", entity);
								$_dependencies.put("x", x);
								$_dependencies.put("y", y);
								$_dependencies.put("z", z);
								$_dependencies.put("world", world);
								Mark22SummonSummonProcedure.executeProcedure($_dependencies);
							}
						}
						if (((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).mark23Ready)) {
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("entity", entity);
								$_dependencies.put("x", x);
								$_dependencies.put("y", y);
								$_dependencies.put("z", z);
								$_dependencies.put("world", world);
								Mark23SummonSummonProcedure.executeProcedure($_dependencies);
							}
						}
						if (((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).mark25Ready)) {
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("entity", entity);
								$_dependencies.put("x", x);
								$_dependencies.put("y", y);
								$_dependencies.put("z", z);
								$_dependencies.put("world", world);
								Mark25SummonSummonProcedure.executeProcedure($_dependencies);
							}
						}
						if (((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).mark30Ready)) {
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("entity", entity);
								$_dependencies.put("x", x);
								$_dependencies.put("y", y);
								$_dependencies.put("z", z);
								$_dependencies.put("world", world);
								Mark30SummonSummonProcedure.executeProcedure($_dependencies);
							}
						}
						if (((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).mark33Ready)) {
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("entity", entity);
								$_dependencies.put("x", x);
								$_dependencies.put("y", y);
								$_dependencies.put("z", z);
								$_dependencies.put("world", world);
								Mark33SummonSummonProcedure.executeProcedure($_dependencies);
							}
						}
						if (((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).mark42Ready)) {
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("entity", entity);
								$_dependencies.put("x", x);
								$_dependencies.put("y", y);
								$_dependencies.put("z", z);
								$_dependencies.put("world", world);
								Mark42SummonSummonProcedure.executeProcedure($_dependencies);
							}
						}
						if (((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).mark43Ready)) {
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("entity", entity);
								$_dependencies.put("x", x);
								$_dependencies.put("y", y);
								$_dependencies.put("z", z);
								$_dependencies.put("world", world);
								Mark43SummonSummonProcedure.executeProcedure($_dependencies);
							}
						}
						if (((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).mark46Ready)) {
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("entity", entity);
								$_dependencies.put("x", x);
								$_dependencies.put("y", y);
								$_dependencies.put("z", z);
								$_dependencies.put("world", world);
								Mark46SummonSummonProcedure.executeProcedure($_dependencies);
							}
						}
						if (((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).mark47Ready)) {
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("entity", entity);
								$_dependencies.put("x", x);
								$_dependencies.put("y", y);
								$_dependencies.put("z", z);
								$_dependencies.put("world", world);
								Mark47SummonSummonProcedure.executeProcedure($_dependencies);
							}
						}
						if (((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).ironPatriotReady)) {
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("entity", entity);
								$_dependencies.put("x", x);
								$_dependencies.put("y", y);
								$_dependencies.put("z", z);
								$_dependencies.put("world", world);
								IronPatriotSummonSummonProcedure.executeProcedure($_dependencies);
							}
						}
						if (((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new MarvelModVariables.PlayerVariables())).warMachineMark2Ready)) {
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("entity", entity);
								$_dependencies.put("x", x);
								$_dependencies.put("y", y);
								$_dependencies.put("z", z);
								$_dependencies.put("world", world);
								WarMachineSummonSummonProcedure.executeProcedure($_dependencies);
							}
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, (int) 60);
				MinecraftForge.EVENT_BUS.unregister(this);
			}
		}.start(world, (int) 20);
	}
}
