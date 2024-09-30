package net.tintankgames.marvel.world.level.timers;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.timers.TimerCallback;
import net.minecraft.world.level.timers.TimerQueue;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;

import java.util.UUID;

public class ResetCallMjolnirCallback implements TimerCallback<MinecraftServer> {
    final UUID player;

    public ResetCallMjolnirCallback(UUID player) {
        this.player = player;
    }

    public void handle(MinecraftServer server, TimerQueue<MinecraftServer> p_82173_, long p_82174_) {
        Player player = server.getPlayerList().getPlayer(this.player);
        player.removeData(MarvelAttachmentTypes.CALLING_MJOLNIR);
    }

    public static class Serializer extends TimerCallback.Serializer<MinecraftServer, ResetCallMjolnirCallback> {
        public Serializer() {
            super(MarvelSuperheroes.id("reset_call_mjolnir"), ResetCallMjolnirCallback.class);
        }

        public void serialize(CompoundTag tag, ResetCallMjolnirCallback callback) {
            tag.putUUID("player", callback.player);
        }

        public ResetCallMjolnirCallback deserialize(CompoundTag tag) {
            UUID player = tag.getUUID("player");
            return new ResetCallMjolnirCallback(player);
        }
    }
}
