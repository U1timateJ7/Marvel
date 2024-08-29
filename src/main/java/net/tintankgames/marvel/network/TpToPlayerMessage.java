package net.tintankgames.marvel.network;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.selector.EntitySelector;
import net.minecraft.commands.arguments.selector.EntitySelectorParser;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tintankgames.marvel.core.particles.MarvelParticleTypes;
import net.tintankgames.marvel.core.particles.SpaceStoneParticleOptions;

public record TpToPlayerMessage(String player, Holder<SoundEvent> soundEvent) implements CustomPacketPayload {
    public static final StreamCodec<RegistryFriendlyByteBuf, TpToPlayerMessage> CODEC = StreamCodec.composite(ByteBufCodecs.STRING_UTF8, TpToPlayerMessage::player, ByteBufCodecs.holderRegistry(Registries.SOUND_EVENT), TpToPlayerMessage::soundEvent, TpToPlayerMessage::new);

    public static void handle(TpToPlayerMessage message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.flow().isServerbound() && context.player() instanceof ServerPlayer player) {
                Entity other;
                EntitySelectorParser entityselectorparser = new EntitySelectorParser(new StringReader(message.player()), true);
                try {
                    EntitySelector entityselector = entityselectorparser.parse();
                    other = entityselector.findSingleEntity(new CommandSourceStack(player, player.position(), player.getRotationVector(), player.serverLevel(), 2, player.getName().getString(), player.getDisplayName(), player.server, player));
                } catch (CommandSyntaxException e) {
                    other = player;
                }
                Vec3 pos = other.position();
                if (other != player) {
                    for (ServerPlayer serverPlayer : player.serverLevel().players()) {
                        player.serverLevel().sendParticles(serverPlayer, new SpaceStoneParticleOptions(MarvelParticleTypes.SPACE_STONE_EMITTER.get(), 1, player.yHeadRot), true, player.getX(), player.getY(0.5), player.getZ(), 0, 0, 0, 0, 1);
                    }
                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(), message.soundEvent().value(), player.getSoundSource(), 1.0F, 1.0F);
                    player.teleportTo((ServerLevel) other.level(), pos.x(), pos.y(), pos.z(), other.getYRot(), player.getXRot());
                    for (ServerPlayer serverPlayer : player.serverLevel().players()) {
                        player.serverLevel().sendParticles(serverPlayer, new SpaceStoneParticleOptions(MarvelParticleTypes.REVERSE_SPACE_STONE_EMITTER.get(), 1, player.yHeadRot), true, player.getX(), player.getY(0.5), player.getZ(), 0, 0, 0, 0, 1);
                    }
                }
            }
        });
    }

    @Override
    public Type<TpToPlayerMessage> type() {
        return MarvelNetworking.TP_TO_PLAYER;
    }
}
