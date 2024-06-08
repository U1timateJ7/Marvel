package net.tintankgames.marvel.network;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.timers.TimerQueue;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tintankgames.marvel.sounds.MarvelSoundEvents;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.level.timers.SetItemInCurioSlotCallback;
import net.tintankgames.marvel.world.level.timers.SetItemInSlotCallback;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

public class SuitTransformationMessage implements CustomPacketPayload {
    public static final SuitTransformationMessage INSTANCE = new SuitTransformationMessage();
    public static final StreamCodec<RegistryFriendlyByteBuf, SuitTransformationMessage> CODEC = StreamCodec.unit(INSTANCE);

    private SuitTransformationMessage() {

    }

    public static void handle(SuitTransformationMessage message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.flow().isServerbound() && context.player() instanceof ServerPlayer player) {
                boolean noArmor = true;
                for (ItemStack armor : player.getInventory().armor) {
                    if (!armor.isEmpty()) {
                        noArmor = false;
                        break;
                    }
                }
                if (CuriosApi.getCuriosInventory(player).get().isEquipped(MarvelItems.KINETIC_BLACK_PANTHER_NECKLACE.get()) && noArmor) {
                    SlotResult slotResult = CuriosApi.getCuriosInventory(player).get().findFirstCurio(MarvelItems.KINETIC_BLACK_PANTHER_NECKLACE.get()).get();
                    ItemContainerContents necklaceContents = slotResult.stack().get(DataComponents.CONTAINER);
                    TimerQueue<MinecraftServer> timerqueue = player.getServer().getWorldData().overworldData().getScheduledEvents();
                    long i = player.serverLevel().getGameTime();
                    player.serverLevel().playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.KINETIC_BLACK_PANTHER_SUIT_UP.get(), SoundSource.PLAYERS);
                    player.setItemSlot(EquipmentSlot.CHEST, necklaceContents.getStackInSlot(2));
                    timerqueue.schedule(player.getStringUUID() + "_kinetic_black_panther_leggings_equip", i + 10, new SetItemInSlotCallback(player, EquipmentSlot.LEGS, necklaceContents.getStackInSlot(1), false));
                    timerqueue.schedule(player.getStringUUID() + "_kinetic_black_panther_boots_equip", i + 20, new SetItemInSlotCallback(player, EquipmentSlot.FEET, necklaceContents.getStackInSlot(0), false));
                    timerqueue.schedule(player.getStringUUID() + "_kinetic_black_panther_helmet_equip", i + 30, new SetItemInSlotCallback(player, EquipmentSlot.HEAD, necklaceContents.getStackInSlot(3), false));
                    CuriosApi.getCuriosInventory(player).get().setEquippedCurio(slotResult.slotContext().identifier(), slotResult.slotContext().index(), ItemStack.EMPTY);
                } else if (CuriosApi.getCuriosInventory(player).get().findCurio("necklace", 0).isEmpty() && player.getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.KINETIC_BLACK_PANTHER_BOOTS) && player.getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.KINETIC_BLACK_PANTHER_LEGGINGS) && player.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.KINETIC_BLACK_PANTHER_CHESTPLATE) && player.getItemBySlot(EquipmentSlot.HEAD).is(MarvelItems.KINETIC_BLACK_PANTHER_HELMET)) {
                    ItemStack necklace = MarvelItems.KINETIC_BLACK_PANTHER_NECKLACE.toStack();
                    necklace.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(player.getInventory().armor));
                    TimerQueue<MinecraftServer> timerqueue = player.getServer().getWorldData().overworldData().getScheduledEvents();
                    long i = player.serverLevel().getGameTime();
                    player.serverLevel().playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.KINETIC_BLACK_PANTHER_SUIT_DOWN.get(), SoundSource.PLAYERS);
                    player.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
                    timerqueue.schedule(player.getStringUUID() + "_kinetic_black_panther_boots_unequip", i + 10, new SetItemInSlotCallback(player, EquipmentSlot.FEET, ItemStack.EMPTY, false));
                    timerqueue.schedule(player.getStringUUID() + "_kinetic_black_panther_leggings_unequip", i + 20, new SetItemInSlotCallback(player, EquipmentSlot.LEGS, ItemStack.EMPTY, false));
                    timerqueue.schedule(player.getStringUUID() + "_kinetic_black_panther_chestplate_unequip", i + 30, new SetItemInSlotCallback(player, EquipmentSlot.CHEST, ItemStack.EMPTY, false));
                    timerqueue.schedule(player.getStringUUID() + "_kinetic_black_panther_necklace_equip", i + 30, new SetItemInCurioSlotCallback(player, "necklace", 0, necklace));
                }
                if (CuriosApi.getCuriosInventory(player).get().isEquipped(MarvelItems.KILLMONGER_NECKLACE.get()) && noArmor) {
                    SlotResult slotResult = CuriosApi.getCuriosInventory(player).get().findFirstCurio(MarvelItems.KILLMONGER_NECKLACE.get()).get();
                    ItemContainerContents necklaceContents = slotResult.stack().get(DataComponents.CONTAINER);
                    TimerQueue<MinecraftServer> timerqueue = player.getServer().getWorldData().overworldData().getScheduledEvents();
                    long i = player.serverLevel().getGameTime();
                    player.serverLevel().playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.KINETIC_BLACK_PANTHER_SUIT_UP.get(), SoundSource.PLAYERS);
                    player.setItemSlot(EquipmentSlot.CHEST, necklaceContents.getStackInSlot(2));
                    timerqueue.schedule(player.getStringUUID() + "_killmonger_leggings_equip", i + 10, new SetItemInSlotCallback(player, EquipmentSlot.LEGS, necklaceContents.getStackInSlot(1), false));
                    timerqueue.schedule(player.getStringUUID() + "_killmonger_boots_equip", i + 20, new SetItemInSlotCallback(player, EquipmentSlot.FEET, necklaceContents.getStackInSlot(0), false));
                    timerqueue.schedule(player.getStringUUID() + "_killmonger_helmet_equip", i + 30, new SetItemInSlotCallback(player, EquipmentSlot.HEAD, necklaceContents.getStackInSlot(3), false));
                    CuriosApi.getCuriosInventory(player).get().setEquippedCurio(slotResult.slotContext().identifier(), slotResult.slotContext().index(), ItemStack.EMPTY);
                } else if (CuriosApi.getCuriosInventory(player).get().findCurio("necklace", 0).isEmpty() && player.getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.KILLMONGER_BOOTS) && player.getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.KILLMONGER_LEGGINGS) && player.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.KILLMONGER_CHESTPLATE) && player.getItemBySlot(EquipmentSlot.HEAD).is(MarvelItems.KILLMONGER_HELMET)) {
                    ItemStack necklace = MarvelItems.KILLMONGER_NECKLACE.toStack();
                    necklace.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(player.getInventory().armor));
                    TimerQueue<MinecraftServer> timerqueue = player.getServer().getWorldData().overworldData().getScheduledEvents();
                    long i = player.serverLevel().getGameTime();
                    player.serverLevel().playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.KINETIC_BLACK_PANTHER_SUIT_DOWN.get(), SoundSource.PLAYERS);
                    player.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
                    timerqueue.schedule(player.getStringUUID() + "_killmonger_boots_unequip", i + 10, new SetItemInSlotCallback(player, EquipmentSlot.FEET, ItemStack.EMPTY, false));
                    timerqueue.schedule(player.getStringUUID() + "_killmonger_leggings_unequip", i + 20, new SetItemInSlotCallback(player, EquipmentSlot.LEGS, ItemStack.EMPTY, false));
                    timerqueue.schedule(player.getStringUUID() + "_killmonger_chestplate_unequip", i + 30, new SetItemInSlotCallback(player, EquipmentSlot.CHEST, ItemStack.EMPTY, false));
                    timerqueue.schedule(player.getStringUUID() + "_killmonger_necklace_equip", i + 30, new SetItemInCurioSlotCallback(player, "necklace", 0, necklace));
                }
            }
        });
    }

    @Override
    public Type<SuitTransformationMessage> type() {
        return MarvelNetworking.SUIT_TRANSFORMATION;
    }
}
