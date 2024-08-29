package net.tintankgames.marvel.network;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tintankgames.marvel.core.particles.MarvelParticleTypes;
import net.tintankgames.marvel.core.particles.SpaceStoneParticleOptions;

public record SpawnPointMessage(Holder<SoundEvent> soundEvent) implements CustomPacketPayload {
    public static final StreamCodec<RegistryFriendlyByteBuf, SpawnPointMessage> CODEC = StreamCodec.composite(ByteBufCodecs.holderRegistry(Registries.SOUND_EVENT), SpawnPointMessage::soundEvent, SpawnPointMessage::new);

    public static void handle(SpawnPointMessage message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.flow().isServerbound() && context.player() instanceof ServerPlayer player) {
                ServerLevel level = player.server.getLevel(player.getRespawnDimension());
                if (level == null) level = player.serverLevel();
                BlockPos pos = player.getRespawnPosition();
                float angle = player.getRespawnAngle();
                if (pos == null) {
                    pos = player.server.overworld().getSharedSpawnPos();
                    angle = player.server.overworld().getSharedSpawnAngle();
                }
                for (ServerPlayer serverPlayer : player.serverLevel().players()) {
                    player.serverLevel().sendParticles(serverPlayer, new SpaceStoneParticleOptions(MarvelParticleTypes.SPACE_STONE_EMITTER.get(), 1, player.yHeadRot), true, player.getX(), player.getY(0.5), player.getZ(), 0, 0, 0, 0, 1);
                }
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), message.soundEvent().value(), player.getSoundSource(), 1.0F, 1.0F);
                player.teleportTo(level, pos.getX(), pos.getY(), pos.getZ(), angle, player.getXRot());
                for (ServerPlayer serverPlayer : player.serverLevel().players()) {
                    player.serverLevel().sendParticles(serverPlayer, new SpaceStoneParticleOptions(MarvelParticleTypes.REVERSE_SPACE_STONE_EMITTER.get(), 1, player.yHeadRot), true, player.getX(), player.getY(0.5), player.getZ(), 0, 0, 0, 0, 1);
                }
            }
        });
    }

    @Override
    public Type<SpawnPointMessage> type() {
        return MarvelNetworking.SPAWN_POINT;
    }
}
