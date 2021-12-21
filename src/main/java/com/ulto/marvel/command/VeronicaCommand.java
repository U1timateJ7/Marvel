
package com.ulto.marvel.command;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.context.CommandContext;
import com.ulto.marvel.network.MarvelModVariables;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber
public class VeronicaCommand {
	@SubscribeEvent
	public static void registerCommands(RegisterCommandsEvent event) {
		event.getDispatcher().register(Commands.literal("veronica").executes((VeronicaCommand::execute)));
	}

	private static int execute(CommandContext<CommandSourceStack> ctx) {
		Entity entity = ctx.getSource().getEntity();
		if (entity == null) {
			UUID uuid = UUID.randomUUID();
			entity = FakePlayerFactory.get(ctx.getSource().getLevel(), new GameProfile(uuid, uuid.toString()));
		}
		{
			Entity finalEntity = entity;
			entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.isBig = true;
				capability.syncPlayerVariables(finalEntity);
			});
		}
		return 0;
	}
}
