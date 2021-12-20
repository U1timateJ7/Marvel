package com.ulto.marvel.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class GrowingActiveTickProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performCommand(
					new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null)
							.withSuppressedOutput(),
					"scale set pehkui:height 10 " + entity.getStringUUID());
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performCommand(
					new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null)
							.withSuppressedOutput(),
					"scale set pehkui:width 10 " + entity.getStringUUID());
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performCommand(
					new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null)
							.withSuppressedOutput(),
					"scale set pehkui:motion 4 " + entity.getStringUUID());
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performCommand(
					new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null)
							.withSuppressedOutput(),
					"scale set pehkui:reach 10 " + entity.getStringUUID());
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performCommand(
					new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null)
							.withSuppressedOutput(),
					"scale set pehkui:drops 10 " + entity.getStringUUID());
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performCommand(
					new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null)
							.withSuppressedOutput(),
					"scale set pehkui:projectiles 10 " + entity.getStringUUID());
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performCommand(
					new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null)
							.withSuppressedOutput(),
					"scale set pehkui:explosions 10 " + entity.getStringUUID());
	}
}
