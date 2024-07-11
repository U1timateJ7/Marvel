package net.tintankgames.marvel.world.level.timers;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.timers.TimerCallback;
import net.minecraft.world.level.timers.TimerQueue;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.network.ToggleHelmetMessage;

import java.util.UUID;

public class ToggleHelmetCallback implements TimerCallback<MinecraftServer> {
    final UUID player;

    public ToggleHelmetCallback(ServerPlayer player) {
        this(player.getUUID());
    }

    public ToggleHelmetCallback(UUID player) {
        this.player = player;
    }

    public void handle(MinecraftServer p_82172_, TimerQueue<MinecraftServer> p_82173_, long p_82174_) {
        ServerPlayer player = p_82172_.getPlayerList().getPlayer(this.player);
        if (player != null) {
            ToggleHelmetMessage.toggleHelmet(player);
        }
    }

    public static class Serializer extends TimerCallback.Serializer<MinecraftServer, ToggleHelmetCallback> {
        public Serializer() {
            super(MarvelSuperheroes.id("toggle_helmet"), ToggleHelmetCallback.class);
        }

        public void serialize(CompoundTag p_82182_, ToggleHelmetCallback p_82183_) {
            p_82182_.putUUID("player", p_82183_.player);
        }

        public ToggleHelmetCallback deserialize(CompoundTag p_82180_) {
            UUID player = p_82180_.getUUID("player");
            return new ToggleHelmetCallback(player);
        }
    }
}
