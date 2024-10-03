package dustw.libgui.network;

import io.github.cottonmc.cotton.gui.impl.ScreenNetworkingImpl;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

import java.util.Objects;

/**
 * @author DustW
 */
public record LibGuiPacket(int syncId, ResourceLocation message, RegistryFriendlyByteBuf rest) implements CustomPacketPayload {
    private static final StreamCodec<RegistryFriendlyByteBuf, RegistryFriendlyByteBuf> BYTE_BUF_CODEC = StreamCodec.of(FriendlyByteBuf::writeBytes, buf -> buf);
    public static final StreamCodec<RegistryFriendlyByteBuf, LibGuiPacket> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, LibGuiPacket::syncId,
            ResourceLocation.STREAM_CODEC, LibGuiPacket::message,
            BYTE_BUF_CODEC, libGuiPacket -> libGuiPacket.rest,
            LibGuiPacket::new
    );
    // Packet structure:
    //   syncId: int
    //   message: identifier
    //   rest: buf

    public static void handle(LibGuiPacket message, IPayloadContext context) {
        context.enqueueWork(() -> ScreenNetworkingImpl.handle(ServerLifecycleHooks.getCurrentServer(), Objects.requireNonNull(context.player()), message.rest));
    }

    @Override
    public Type<LibGuiPacket> type() {
        return LibGuiMessages.PACKET_ID;
    }
}
