package com.ulto.marvel.procedures;

import com.ulto.marvel.sounds.MarvelModSounds;
import com.ulto.marvel.world.entity.SentryMode;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import com.ulto.marvel.network.MarvelModVariables;
import com.ulto.marvel.common.MarvelMod;

public class VeronicaSendProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (!canSendToVeronica(entity, sourceentity)) return;
		MarvelMod.LOGGER.info(sourceentity.getDisplayName().getString() + " just sent " + entity.getDisplayName().getString() + " to space");
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						MarvelModSounds.get(new ResourceLocation("marvel:iron_man.fly")), SoundSource.NEUTRAL, 1, 1);
			} else {
				_level.playLocalSound(x, y, z, MarvelModSounds.get(new ResourceLocation("marvel:iron_man.fly")),
						SoundSource.NEUTRAL, 1, 1, false);
			}
		}
		if (entity instanceof LivingEntity _entity)
			_entity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 100, 19, (false), (false)));
		sourceentity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
			capability.setVeronicaVariable((((SentryMode)entity).getSuit()), true);
			capability.syncPlayerVariables(sourceentity);
		});
		new Object() {
			private int ticks = 0;
			private float waitTicks;

			public void start(int waitTicks) {
				this.waitTicks = waitTicks;
				MinecraftForge.EVENT_BUS.register(this);
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
				if (!entity.level.isClientSide())
					entity.discard();
				MinecraftForge.EVENT_BUS.unregister(this);
			}
		}.start(100);
	}

	private static boolean canSendToVeronica(Entity suit, Entity user) {
		return !MarvelModVariables.getPlayerVariables(user).getVeronicaVariable(((SentryMode)suit).getSuit());
	}
}
