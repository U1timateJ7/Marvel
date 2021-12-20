package com.ulto.marvel.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class ShrinkingActiveTickProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performCommand(
					new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null)
							.withSuppressedOutput(),
					"scale set pehkui:height 0.1 " + entity.getStringUUID());
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performCommand(
					new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null)
							.withSuppressedOutput(),
					"scale set pehkui:width 0.1 " + entity.getStringUUID());
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performCommand(
					new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null)
							.withSuppressedOutput(),
					"scale set pehkui:reach 0.5 " + entity.getStringUUID());
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performCommand(
					new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null)
							.withSuppressedOutput(),
					"scale set pehkui:drops 0.1 " + entity.getStringUUID());
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performCommand(
					new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null)
							.withSuppressedOutput(),
					"scale set pehkui:projectiles 0.1 " + entity.getStringUUID());
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performCommand(
					new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null)
							.withSuppressedOutput(),
					"scale set pehkui:explosions 0.1 " + entity.getStringUUID());
	}
}
