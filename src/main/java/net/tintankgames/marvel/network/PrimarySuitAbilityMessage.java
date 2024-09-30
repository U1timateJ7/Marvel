package net.tintankgames.marvel.network;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.core.particles.MarvelParticleTypes;
import net.tintankgames.marvel.sounds.MarvelSoundEvents;
import net.tintankgames.marvel.world.damagesources.MarvelDamageTypes;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.component.OpticBlastMode;
import net.tintankgames.marvel.world.level.KineticExplosionDamageCalculator;

public class PrimarySuitAbilityMessage implements CustomPacketPayload {
    public static final PrimarySuitAbilityMessage INSTANCE = new PrimarySuitAbilityMessage();
    public static final StreamCodec<RegistryFriendlyByteBuf, PrimarySuitAbilityMessage> CODEC = StreamCodec.unit(INSTANCE);

    private PrimarySuitAbilityMessage() {

    }

    public static void handle(PrimarySuitAbilityMessage message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.flow().isServerbound() && context.player() instanceof ServerPlayer player) {
                ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
                ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
                ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
                ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
                float absorbed = 0.0F;
                for (ItemStack stack : player.getInventory().armor) {
                    if (stack.has(MarvelDataComponents.ABSORBED_DAMAGE)) {
                        absorbed += stack.getOrDefault(MarvelDataComponents.ABSORBED_DAMAGE, 0.0F);
                        stack.set(MarvelDataComponents.ABSORBED_DAMAGE, 0.0F);
                    }
                }
                if (absorbed > 0.0F) {
                    player.serverLevel().explode(player, player.damageSources().source(MarvelDamageTypes.KINETIC_BLAST, player), new KineticExplosionDamageCalculator(absorbed / 5f, null), player.getX(), player.getY(), player.getZ(), absorbed/20f, false, Level.ExplosionInteraction.NONE, ColorParticleOption.create(MarvelParticleTypes.KINETIC_BLAST_EMITTER.get(), chestplate.getBarColor()), ColorParticleOption.create(MarvelParticleTypes.KINETIC_BLAST_EMITTER.get(), chestplate.getBarColor()), SoundEvents.GENERIC_EXPLODE);
                }
                if (helmet.has(MarvelDataComponents.OPTIC_BLAST_MODE)) {
                    helmet.update(MarvelDataComponents.OPTIC_BLAST_MODE, OpticBlastMode.NARROW, mode -> {
                        if (mode == OpticBlastMode.WIDE) player.sendSystemMessage(Component.translatable("item.marvel.optic_blast.narrow").withStyle(ChatFormatting.RED), true);
                        else if (mode == OpticBlastMode.NARROW) player.sendSystemMessage(Component.translatable("item.marvel.optic_blast.wide").withStyle(ChatFormatting.RED), true);
                        return mode == OpticBlastMode.NARROW ? OpticBlastMode.WIDE : OpticBlastMode.NARROW;
                    });
                }
                if (!chestplate.has(MarvelDataComponents.SPIDER_SENSE) && helmet.is(MarvelItems.Tags.SPIDER_MAN_ARMOR) && chestplate.is(MarvelItems.Tags.SPIDER_MAN_ARMOR) && leggings.is(MarvelItems.Tags.SPIDER_MAN_ARMOR) && boots.is(MarvelItems.Tags.SPIDER_MAN_ARMOR)) {
                    player.connection.send(new ClientboundSoundPacket(MarvelSoundEvents.SPIDER_MAN_SPIDER_SENSE, SoundSource.PLAYERS, player.getX(), player.getY(), player.getZ(), 1.0F, 1.0F, player.getRandom().nextLong()));
                    player.serverLevel().sendParticles(MarvelParticleTypes.SPIDER_SENSE.get(), player.getX(), player.getEyeY() + 0.5F, player.getZ(), 1, 0, 0, 0, 0);
                    chestplate.set(MarvelDataComponents.SPIDER_SENSE, 100);
                }
                if (helmet.isEmpty() && chestplate.is(MarvelItems.Tags.QUICKSILVER_ARMOR) && leggings.is(MarvelItems.Tags.QUICKSILVER_ARMOR) && boots.is(MarvelItems.Tags.QUICKSILVER_ARMOR)) {
                    if (chestplate.has(MarvelDataComponents.SPEEDING)) {
                        chestplate.remove(MarvelDataComponents.SPEEDING);
                    } else {
                        chestplate.set(MarvelDataComponents.SPEEDING, Unit.INSTANCE);
                    }
                }
                if (helmet.is(MarvelItems.Tags.IRON_MAN_MARK_15_ARMOR) && chestplate.is(MarvelItems.Tags.IRON_MAN_MARK_15_ARMOR) && leggings.is(MarvelItems.Tags.IRON_MAN_MARK_15_ARMOR) && boots.is(MarvelItems.Tags.IRON_MAN_MARK_15_ARMOR)) {
                    if (chestplate.has(MarvelDataComponents.INVISIBLE)) {
                        chestplate.remove(MarvelDataComponents.INVISIBLE);
                    } else {
                        chestplate.set(MarvelDataComponents.INVISIBLE, Unit.INSTANCE);
                    }
                }
            }
        });
    }

    @Override
    public Type<PrimarySuitAbilityMessage> type() {
        return MarvelNetworking.PRIMARY_SUIT_ABILITY;
    }
}
