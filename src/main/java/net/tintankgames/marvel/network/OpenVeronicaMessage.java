package net.tintankgames.marvel.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class OpenVeronicaMessage implements CustomPacketPayload {
    public static final OpenVeronicaMessage INSTANCE = new OpenVeronicaMessage();
    public static final StreamCodec<RegistryFriendlyByteBuf, OpenVeronicaMessage> CODEC = StreamCodec.unit(INSTANCE);

    private OpenVeronicaMessage() {
    }

    public static void handle(OpenVeronicaMessage message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.flow().isClientbound()) {
                MarvelNetworking.clientUtils.openVeronica();
            }
        });
    }

    @Override
    public Type<OpenVeronicaMessage> type() {
        return MarvelNetworking.OPEN_VERONICA;
    }
}
