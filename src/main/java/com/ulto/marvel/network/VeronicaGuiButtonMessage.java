
package com.ulto.marvel.network;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import java.util.function.Supplier;
import java.util.HashMap;

import com.ulto.marvel.world.inventory.VeronicaGuiMenu;
import com.ulto.marvel.procedures.WarMachineSummonSummonProcedure;
import com.ulto.marvel.procedures.Mark47SummonSummonProcedure;
import com.ulto.marvel.procedures.Mark46SummonSummonProcedure;
import com.ulto.marvel.procedures.Mark43SummonSummonProcedure;
import com.ulto.marvel.procedures.Mark42SummonSummonProcedure;
import com.ulto.marvel.procedures.Mark39SummonSummonProcedure;
import com.ulto.marvel.procedures.Mark37SummonSummonProcedure;
import com.ulto.marvel.procedures.Mark33SummonSummonProcedure;
import com.ulto.marvel.procedures.Mark30SummonSummonProcedure;
import com.ulto.marvel.procedures.Mark25SummonSummonProcedure;
import com.ulto.marvel.procedures.Mark23SummonSummonProcedure;
import com.ulto.marvel.procedures.Mark22SummonSummonProcedure;
import com.ulto.marvel.procedures.Mark21SummonSummonProcedure;
import com.ulto.marvel.procedures.Mark20SummonSummonProcedure;
import com.ulto.marvel.procedures.Mark19SummonSummonProcedure;
import com.ulto.marvel.procedures.Mark17SummonSummonProcedure;
import com.ulto.marvel.procedures.Mark16SummonSummonProcedure;
import com.ulto.marvel.procedures.IronPatriotSummonSummonProcedure;
import com.ulto.marvel.procedures.HousePartyProtocolProcedure;
import com.ulto.marvel.common.MarvelMod;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeronicaGuiButtonMessage {
	private final int buttonID, x, y, z;

	public VeronicaGuiButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public VeronicaGuiButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(VeronicaGuiButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(VeronicaGuiButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int buttonID = message.buttonID;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			handleButtonAction(entity, buttonID, x, y, z);
		});
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level;
		HashMap guistate = VeronicaGuiMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			Mark21SummonSummonProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 1) {

			Mark23SummonSummonProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 2) {

			Mark30SummonSummonProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 3) {

			Mark42SummonSummonProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 4) {

			Mark46SummonSummonProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 5) {

			IronPatriotSummonSummonProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 6) {

			Mark22SummonSummonProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 7) {

			Mark25SummonSummonProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 8) {

			Mark33SummonSummonProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 9) {

			Mark43SummonSummonProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 10) {

			Mark47SummonSummonProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 11) {

			WarMachineSummonSummonProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 12) {

			Mark16SummonSummonProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 13) {

			Mark17SummonSummonProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 14) {

			Mark19SummonSummonProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 15) {

			Mark20SummonSummonProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 16) {

			Mark37SummonSummonProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 17) {

			Mark39SummonSummonProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 18) {

			HousePartyProtocolProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MarvelMod.addNetworkMessage(VeronicaGuiButtonMessage.class, VeronicaGuiButtonMessage::buffer, VeronicaGuiButtonMessage::new,
				VeronicaGuiButtonMessage::handler);
	}
}
