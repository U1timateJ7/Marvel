
package com.ulto.marvel.network;

import com.ulto.marvel.common.MarvelMod;
import com.ulto.marvel.procedures.AbilityKeyC;
import com.ulto.marvel.procedures.WebShooterSwichProcedure;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AbilityKeyMessage {
	int type, pressedms;

	public AbilityKeyMessage(int type, int pressedms) {
		this.type = type;
		this.pressedms = pressedms;
	}

	public AbilityKeyMessage(FriendlyByteBuf buffer) {
		this.type = buffer.readInt();
		this.pressedms = buffer.readInt();
	}

	public static void buffer(AbilityKeyMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.type);
		buffer.writeInt(message.pressedms);
	}

	public static void handler(AbilityKeyMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			pressAction(context.getSender(), message.type, message.pressedms);
		});
		context.setPacketHandled(true);
	}

	public static void pressAction(Player entity, int type, int pressedms) {
		Level world = entity.level;
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(entity.blockPosition()))
			return;
		if (type == 0) {

			AbilityKeyC.execute(world, x, y, z, entity);
		}
		if (type == 1) {

			WebShooterSwichProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MarvelMod.addNetworkMessage(AbilityKeyMessage.class, AbilityKeyMessage::buffer, AbilityKeyMessage::new, AbilityKeyMessage::handler);
	}
}
