package net.tintankgames.marvel.network;

import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tintankgames.marvel.client.gui.screens.SpaceStoneScreen;

public record OpenSpaceStoneMessage(Component name, Holder<SoundEvent> soundEvent) implements CustomPacketPayload {
    public static final StreamCodec<RegistryFriendlyByteBuf, OpenSpaceStoneMessage> CODEC = StreamCodec.composite(ComponentSerialization.STREAM_CODEC, OpenSpaceStoneMessage::name, ByteBufCodecs.holderRegistry(Registries.SOUND_EVENT), OpenSpaceStoneMessage::soundEvent, OpenSpaceStoneMessage::new);

    public static void handle(OpenSpaceStoneMessage message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.flow().isClientbound()) {
                Minecraft.getInstance().setScreen(new SpaceStoneScreen(message.name(), message.soundEvent()));
            }
        });
    }

    @Override
    public Type<OpenSpaceStoneMessage> type() {
        return MarvelNetworking.OPEN_SPACE_STONE;
    }
}
