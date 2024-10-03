package dustw.libgui.network;

import dustw.libgui.LibGuiCommon;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

/**
 * @author DustW
 */
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class LibGuiMessages {
    public static final CustomPacketPayload.Type<LibGuiPacket> PACKET_ID = new CustomPacketPayload.Type<>(new ResourceLocation(LibGuiCommon.MOD_ID, "message"));

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(LibGuiCommon.MOD_ID).versioned("1");
        registrar.playBidirectional(PACKET_ID, LibGuiPacket.CODEC, LibGuiPacket::handle);
    }

    public static <MSG extends CustomPacketPayload> void sendToServer(MSG message) {
        Minecraft.getInstance().player.connection.send(message);
    }

    public static <MSG extends CustomPacketPayload> void sendToPlayer(MSG message, ServerPlayer player) {
        PacketDistributor.sendToPlayer(player, message);
    }
}
