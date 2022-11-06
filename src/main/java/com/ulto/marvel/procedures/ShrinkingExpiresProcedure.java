package com.ulto.marvel.procedures;

import com.ulto.marvel.sounds.MarvelModSounds;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class ShrinkingExpiresProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performCommand(
					new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null)
							.withSuppressedOutput(),
					"scale set pehkui:height 1 " + entity.getStringUUID());
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performCommand(
					new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null)
							.withSuppressedOutput(),
					"scale set pehkui:width 1 " + entity.getStringUUID());
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performCommand(
					new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null)
							.withSuppressedOutput(),
					"scale set pehkui:motion 1 " + entity.getStringUUID());
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performCommand(
					new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null)
							.withSuppressedOutput(),
					"scale set pehkui:reach 1 " + entity.getStringUUID());
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performCommand(
					new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null)
							.withSuppressedOutput(),
					"scale set pehkui:drops 1 " + entity.getStringUUID());
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performCommand(
					new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null)
							.withSuppressedOutput(),
					"scale set pehkui:projectiles 1 " + entity.getStringUUID());
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performCommand(
					new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null)
							.withSuppressedOutput(),
					"scale set pehkui:explosions 1 " + entity.getStringUUID());
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						MarvelModSounds.get(new ResourceLocation("marvel:antman.grow")), SoundSource.NEUTRAL, 1, 1);
			} else {
				_level.playLocalSound(x, y, z, MarvelModSounds.get(new ResourceLocation("marvel:antman.grow")), SoundSource.NEUTRAL,
						1, 1, false);
			}
		}
	}
}
