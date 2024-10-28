package net.tintankgames.marvel.network;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;

public interface ClientUtils {
    void openSpaceStone(Component name, Holder<SoundEvent> soundEvent);

    void openVeronica();
}
