package net.tintankgames.marvel.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.sounds.MarvelSoundEvents;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.component.Size;

public class ToggleHelmetMessage implements CustomPacketPayload {
    public static final ToggleHelmetMessage INSTANCE = new ToggleHelmetMessage();
    public static final StreamCodec<RegistryFriendlyByteBuf, ToggleHelmetMessage> CODEC = StreamCodec.unit(INSTANCE);

    private ToggleHelmetMessage() {

    }

    public static void handle(ToggleHelmetMessage message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.flow().isServerbound() && context.player() instanceof ServerPlayer player) toggleHelmet(player);
        });
    }

    public static void toggleHelmet(Player player) {
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
        if (!helmet.isEmpty() && helmet.has(MarvelDataComponents.HELMET_OPEN) && chestplate.getOrDefault(MarvelDataComponents.SIZE, Size.NORMAL) == Size.NORMAL) {
            helmet.update(MarvelDataComponents.HELMET_OPEN, false, open -> {
                if (!player.level().isClientSide) {
                    if (helmet.is(MarvelItems.Tags.KINETIC_BLACK_PANTHER_HELMET)) {
                        if (open)
                            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.KINETIC_BLACK_PANTHER_HELMET_DOWN.get(), SoundSource.PLAYERS);
                        else
                            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.KINETIC_BLACK_PANTHER_HELMET_UP.get(), SoundSource.PLAYERS);
                    } else if (helmet.is(MarvelItems.Tags.ANT_MAN_HELMET)) {
                        if (open)
                            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.ANT_MAN_HELMET_CLOSE.get(), SoundSource.PLAYERS);
                        else
                            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.ANT_MAN_HELMET_OPEN.get(), SoundSource.PLAYERS);
                    }
                }
                return !open;
            });
        }
    }

    @Override
    public Type<ToggleHelmetMessage> type() {
        return MarvelNetworking.TOGGLE_HELMET;
    }
}
