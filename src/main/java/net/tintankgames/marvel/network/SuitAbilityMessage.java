package net.tintankgames.marvel.network;

import net.minecraft.ChatFormatting;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.damagesources.MarvelDamageTypes;
import net.tintankgames.marvel.world.item.component.OpticBlastMode;
import net.tintankgames.marvel.world.level.KineticExplosionDamageCalculator;

public class SuitAbilityMessage implements CustomPacketPayload {
    public static final SuitAbilityMessage INSTANCE = new SuitAbilityMessage();
    public static final StreamCodec<RegistryFriendlyByteBuf, SuitAbilityMessage> CODEC = StreamCodec.unit(INSTANCE);

    private SuitAbilityMessage() {

    }

    public static void handle(SuitAbilityMessage message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.flow().isServerbound() && context.player() instanceof ServerPlayer player) {
                ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
                ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
                float absorbed = 0.0F;
                for (ItemStack stack : player.getInventory().armor) {
                    if (stack.has(MarvelDataComponents.ABSORBED_DAMAGE)) {
                        absorbed += stack.getOrDefault(MarvelDataComponents.ABSORBED_DAMAGE, 0.0F);
                        stack.set(MarvelDataComponents.ABSORBED_DAMAGE, 0.0F);
                    }
                }
                if (absorbed > 0.0F) {
                    player.serverLevel().explode(player, player.damageSources().source(MarvelDamageTypes.KINETIC_BLAST, player), new KineticExplosionDamageCalculator(absorbed / 5f), player.position(), absorbed/6.25f, false, Level.ExplosionInteraction.NONE);
                }
                if (helmet.has(MarvelDataComponents.OPTIC_BLAST_MODE)) {
                    helmet.update(MarvelDataComponents.OPTIC_BLAST_MODE, OpticBlastMode.NARROW, mode -> {
                        if (mode == OpticBlastMode.WIDE) player.sendSystemMessage(Component.translatable("item.marvel.optic_blast.narrow").withStyle(ChatFormatting.RED), true);
                        else if (mode == OpticBlastMode.NARROW) player.sendSystemMessage(Component.translatable("item.marvel.optic_blast.wide").withStyle(ChatFormatting.RED), true);
                        return mode == OpticBlastMode.NARROW ? OpticBlastMode.WIDE : OpticBlastMode.NARROW;
                    });
                }
            }
        });
    }

    @Override
    public Type<SuitAbilityMessage> type() {
        return MarvelNetworking.SUIT_ABILITY;
    }
}
