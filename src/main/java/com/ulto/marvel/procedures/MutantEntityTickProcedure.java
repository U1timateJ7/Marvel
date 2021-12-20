package com.ulto.marvel.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import javax.annotation.Nullable;

import com.ulto.marvel.network.MarvelModVariables;

@Mod.EventBusSubscriber
public class MutantEntityTickProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Entity entity = event.player;
			execute(event, entity.level, entity);
		}
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MarvelModVariables.PlayerVariables())).isMutantWovlerine) {
			if (entity instanceof LivingEntity _entity)
				_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 10, 1, (false), (false)));
			if (entity instanceof LivingEntity _entity)
				_entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 10, 3, (false), (false)));
			if (entity instanceof LivingEntity _entity)
				_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 10, 0, (false), (false)));
		}
		if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MarvelModVariables.PlayerVariables())).isMutantQuicksilver
				&& !(entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).isBig
				&& !(entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).isSmall
				&& entity.isSprinting()) {
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performCommand(new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "",
						new TextComponent(""), _level.getServer(), null).withSuppressedOutput(),
						"scale set pehkui:motion 7.5 " + entity.getStringUUID());
		} else if ((entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MarvelModVariables.PlayerVariables())).isMutantQuicksilver
				&& !(entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).isBig
				&& !(entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MarvelModVariables.PlayerVariables())).isSmall
				&& !entity.isSprinting()) {
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performCommand(new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "",
						new TextComponent(""), _level.getServer(), null).withSuppressedOutput(),
						"scale set pehkui:motion 1 " + entity.getStringUUID());
		}
	}
}
