
package com.ulto.marvel.server.command;

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
				capability.mark16Ready = true;
				capability.mark17Ready = true;
				capability.mark19Ready = true;
				capability.mark20Ready = true;
				capability.mark21Ready = true;
				capability.mark22Ready = true;
				capability.mark23Ready = true;
				capability.mark25Ready = true;
				capability.mark30Ready = true;
				capability.mark33Ready = true;
				capability.mark37Ready = true;
				capability.mark39Ready = true;
				capability.mark42Ready = true;
				capability.mark43Ready = true;
				capability.mark46Ready = true;
				capability.mark47Ready = true;
				capability.ironPatriotReady = true;
				capability.warMachineMark2Ready = true;
				capability.syncPlayerVariables(finalEntity);
			});
		}
		return 0;
	}
}
