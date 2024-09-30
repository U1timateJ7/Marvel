package net.tintankgames.marvel.network;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.timers.TimerQueue;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.sounds.MarvelSoundEvents;
import net.tintankgames.marvel.world.entity.IronManSentry;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;
import net.tintankgames.marvel.world.item.*;
import net.tintankgames.marvel.world.item.component.ItemStackHolder;
import net.tintankgames.marvel.world.level.timers.SetItemInCurioSlotCallback;
import net.tintankgames.marvel.world.level.timers.SetItemInSlotCallback;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.Objects;

public class SecondarySuitAbilityMessage implements CustomPacketPayload {
    public static final SecondarySuitAbilityMessage INSTANCE = new SecondarySuitAbilityMessage();
    public static final StreamCodec<RegistryFriendlyByteBuf, SecondarySuitAbilityMessage> CODEC = StreamCodec.unit(INSTANCE);

    private SecondarySuitAbilityMessage() {

    }

    public static void handle(SecondarySuitAbilityMessage message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.flow().isServerbound() && context.player() instanceof ServerPlayer player) {
                boolean noKineticBlackPantherArmor = true, noThorArmor = true, hasFullSentryArmor = true;
                for (ItemStack armor : player.getInventory().armor) {
                    if (armor.is(MarvelItems.Tags.KINETIC_BLACK_PANTHER_ARMOR)) {
                        noKineticBlackPantherArmor = false;
                        break;
                    }
                }
                for (ItemStack armor : player.getInventory().armor) {
                    if (armor.is(MarvelItems.Tags.THOR_ARMOR)) {
                        noThorArmor = false;
                        break;
                    }
                }
                for (ItemStack armor : player.getInventory().armor) {
                    if (!(armor.getItem() instanceof SentryIronManSuitItem)) {
                        hasFullSentryArmor = false;
                        break;
                    }
                }
                ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
                ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
                ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
                ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
                ItemStack mainHand = player.getMainHandItem();
                if (hasFullSentryArmor && !chestplate.has(MarvelDataComponents.INVISIBLE) && EnergySuitItem.getEnergy(chestplate) > 0.0F) {
                    IronManSentry sentry = MarvelEntityTypes.IRON_MAN_SENTRY.get().create(player.serverLevel(), null, player.blockPosition(), MobSpawnType.TRIGGERED, false, false);
                    if (sentry != null) {
                        sentry.moveTo(player.position(), player.getYRot(), player.getXRot());
                        sentry.setTame(true, false);
                        sentry.setOwnerUUID(player.getUUID());
                        for (EquipmentSlot slot : new EquipmentSlot[] {EquipmentSlot.FEET, EquipmentSlot.LEGS, EquipmentSlot.CHEST, EquipmentSlot.HEAD}) {
                            sentry.setItemSlot(slot, player.getItemBySlot(slot).copy());
                            player.setItemSlot(slot, ItemStack.EMPTY);
                        }
                        player.serverLevel().tryAddFreshEntityWithPassengers(sentry);
                        player.serverLevel().playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.IRON_MAN_HELMET_OPEN.get(), SoundSource.PLAYERS);
                    }
                } else if ((mainHand.is(MarvelItems.MJOLNIR) && Objects.equals(mainHand.get(MarvelDataComponents.OWNER).toString(), player.getUUID().toString())) || mainHand.is(MarvelItems.STORMBREAKER)) {
                    if (noThorArmor) {
                        ItemStack thorHelmet = MarvelItems.THOR_HELMET.toStack();
                        ItemStack thorChestplate = MarvelItems.THOR_CHESTPLATE.toStack();
                        ItemStack thorLeggings = MarvelItems.THOR_LEGGINGS.toStack();
                        ItemStack thorBoots = MarvelItems.THOR_BOOTS.toStack();
                        thorHelmet.set(MarvelDataComponents.ITEM_STACK, new ItemStackHolder(helmet));
                        thorChestplate.set(MarvelDataComponents.ITEM_STACK, new ItemStackHolder(chestplate));
                        thorLeggings.set(MarvelDataComponents.ITEM_STACK, new ItemStackHolder(leggings));
                        thorBoots.set(MarvelDataComponents.ITEM_STACK, new ItemStackHolder(boots));
                        player.setItemSlot(EquipmentSlot.HEAD, thorHelmet);
                        player.setItemSlot(EquipmentSlot.CHEST, thorChestplate);
                        player.setItemSlot(EquipmentSlot.LEGS, thorLeggings);
                        player.setItemSlot(EquipmentSlot.FEET, thorBoots);
                        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                            ItemStack item = player.getInventory().getItem(i);
                            if (item.getItem() instanceof SuitPowerItem) player.getInventory().removeItem(i, 1);
                        }
                        LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(player.serverLevel());
                        if (lightningBolt != null) {
                            lightningBolt.moveTo(Vec3.atBottomCenterOf(player.blockPosition()));
                            lightningBolt.setVisualOnly(true);
                            player.serverLevel().addFreshEntity(lightningBolt);
                        }
                    } else if (helmet.is(MarvelItems.Tags.THOR_ARMOR) && chestplate.is(MarvelItems.Tags.THOR_ARMOR) && leggings.is(MarvelItems.Tags.THOR_ARMOR) && boots.is(MarvelItems.Tags.THOR_ARMOR)) {
                        ItemStack thorHelmet = helmet.getOrDefault(MarvelDataComponents.ITEM_STACK, new ItemStackHolder(ItemStack.EMPTY)).stack();
                        ItemStack thorChestplate = chestplate.getOrDefault(MarvelDataComponents.ITEM_STACK, new ItemStackHolder(ItemStack.EMPTY)).stack();
                        ItemStack thorLeggings = leggings.getOrDefault(MarvelDataComponents.ITEM_STACK, new ItemStackHolder(ItemStack.EMPTY)).stack();
                        ItemStack thorBoots = boots.getOrDefault(MarvelDataComponents.ITEM_STACK, new ItemStackHolder(ItemStack.EMPTY)).stack();
                        player.setItemSlot(EquipmentSlot.HEAD, thorHelmet);
                        player.setItemSlot(EquipmentSlot.CHEST, thorChestplate);
                        player.setItemSlot(EquipmentSlot.LEGS, thorLeggings);
                        player.setItemSlot(EquipmentSlot.FEET, thorBoots);
                        LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(player.serverLevel());
                        if (lightningBolt != null) {
                            lightningBolt.moveTo(Vec3.atBottomCenterOf(player.blockPosition()));
                            lightningBolt.setVisualOnly(true);
                            player.serverLevel().addFreshEntity(lightningBolt);
                        }
                    }
                } else {
                    if (CuriosApi.getCuriosInventory(player).get().isEquipped(MarvelItems.KINETIC_BLACK_PANTHER_NECKLACE.get()) && noKineticBlackPantherArmor && noThorArmor) {
                        SlotResult slotResult = CuriosApi.getCuriosInventory(player).get().findFirstCurio(MarvelItems.KINETIC_BLACK_PANTHER_NECKLACE.get()).get();
                        ItemContainerContents necklaceContents = slotResult.stack().get(DataComponents.CONTAINER);
                        TimerQueue<MinecraftServer> timerqueue = player.getServer().getWorldData().overworldData().getScheduledEvents();
                        long i = player.serverLevel().getGameTime();
                        player.serverLevel().playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.KINETIC_BLACK_PANTHER_SUIT_UP.get(), SoundSource.PLAYERS);
                        timerqueue.schedule(player.getStringUUID() + "_kinetic_black_panther_chestplate_equip", i + 1, new SetItemInSlotCallback(player, EquipmentSlot.CHEST, necklaceContents.getStackInSlot(2), false, false));
                        timerqueue.schedule(player.getStringUUID() + "_kinetic_black_panther_leggings_equip", i + 10, new SetItemInSlotCallback(player, EquipmentSlot.LEGS, necklaceContents.getStackInSlot(1), false, false));
                        timerqueue.schedule(player.getStringUUID() + "_kinetic_black_panther_boots_equip", i + 20, new SetItemInSlotCallback(player, EquipmentSlot.FEET, necklaceContents.getStackInSlot(0), false, false));
                        timerqueue.schedule(player.getStringUUID() + "_kinetic_black_panther_helmet_equip", i + 30, new SetItemInSlotCallback(player, EquipmentSlot.HEAD, necklaceContents.getStackInSlot(3), false, false));
                        CuriosApi.getCuriosInventory(player).get().setEquippedCurio(slotResult.slotContext().identifier(), slotResult.slotContext().index(), ItemStack.EMPTY);
                    } else if (CuriosApi.getCuriosInventory(player).get().findCurio("necklace", 0).isEmpty() && boots.is(MarvelItems.KINETIC_BLACK_PANTHER_BOOTS) && leggings.is(MarvelItems.KINETIC_BLACK_PANTHER_LEGGINGS) && chestplate.is(MarvelItems.KINETIC_BLACK_PANTHER_CHESTPLATE) && helmet.is(MarvelItems.KINETIC_BLACK_PANTHER_HELMET)) {
                        ItemStack necklace = MarvelItems.KINETIC_BLACK_PANTHER_NECKLACE.toStack();
                        necklace.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(player.getInventory().armor));
                        TimerQueue<MinecraftServer> timerqueue = player.getServer().getWorldData().overworldData().getScheduledEvents();
                        long i = player.serverLevel().getGameTime();
                        player.serverLevel().playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.KINETIC_BLACK_PANTHER_SUIT_DOWN.get(), SoundSource.PLAYERS);
                        player.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
                        timerqueue.schedule(player.getStringUUID() + "_kinetic_black_panther_boots_unequip", i + 10, new SetItemInSlotCallback(player, EquipmentSlot.FEET, ItemStack.EMPTY, false, true));
                        timerqueue.schedule(player.getStringUUID() + "_kinetic_black_panther_leggings_unequip", i + 20, new SetItemInSlotCallback(player, EquipmentSlot.LEGS, ItemStack.EMPTY, false, true));
                        timerqueue.schedule(player.getStringUUID() + "_kinetic_black_panther_chestplate_unequip", i + 30, new SetItemInSlotCallback(player, EquipmentSlot.CHEST, ItemStack.EMPTY, false, true));
                        timerqueue.schedule(player.getStringUUID() + "_kinetic_black_panther_necklace_equip", i + 30, new SetItemInCurioSlotCallback(player, "necklace", 0, necklace));
                    }
                    if (CuriosApi.getCuriosInventory(player).get().isEquipped(MarvelItems.KILLMONGER_NECKLACE.get()) && noKineticBlackPantherArmor && noThorArmor) {
                        SlotResult slotResult = CuriosApi.getCuriosInventory(player).get().findFirstCurio(MarvelItems.KILLMONGER_NECKLACE.get()).get();
                        ItemContainerContents necklaceContents = slotResult.stack().get(DataComponents.CONTAINER);
                        TimerQueue<MinecraftServer> timerqueue = player.getServer().getWorldData().overworldData().getScheduledEvents();
                        long i = player.serverLevel().getGameTime();
                        player.serverLevel().playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.KINETIC_BLACK_PANTHER_SUIT_UP.get(), SoundSource.PLAYERS);
                        timerqueue.schedule(player.getStringUUID() + "_killmonger_chestplate_equip", i + 1, new SetItemInSlotCallback(player, EquipmentSlot.CHEST, necklaceContents.getStackInSlot(2), false, false));
                        timerqueue.schedule(player.getStringUUID() + "_killmonger_leggings_equip", i + 10, new SetItemInSlotCallback(player, EquipmentSlot.LEGS, necklaceContents.getStackInSlot(1), false, false));
                        timerqueue.schedule(player.getStringUUID() + "_killmonger_boots_equip", i + 20, new SetItemInSlotCallback(player, EquipmentSlot.FEET, necklaceContents.getStackInSlot(0), false, false));
                        timerqueue.schedule(player.getStringUUID() + "_killmonger_helmet_equip", i + 30, new SetItemInSlotCallback(player, EquipmentSlot.HEAD, necklaceContents.getStackInSlot(3), false, false));
                        CuriosApi.getCuriosInventory(player).get().setEquippedCurio(slotResult.slotContext().identifier(), slotResult.slotContext().index(), ItemStack.EMPTY);
                    } else if (CuriosApi.getCuriosInventory(player).get().findCurio("necklace", 0).isEmpty() && boots.is(MarvelItems.KILLMONGER_BOOTS) && leggings.is(MarvelItems.KILLMONGER_LEGGINGS) && chestplate.is(MarvelItems.KILLMONGER_CHESTPLATE) && helmet.is(MarvelItems.KILLMONGER_HELMET)) {
                        ItemStack necklace = MarvelItems.KILLMONGER_NECKLACE.toStack();
                        necklace.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(player.getInventory().armor));
                        TimerQueue<MinecraftServer> timerqueue = player.getServer().getWorldData().overworldData().getScheduledEvents();
                        long i = player.serverLevel().getGameTime();
                        player.serverLevel().playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.KINETIC_BLACK_PANTHER_SUIT_DOWN.get(), SoundSource.PLAYERS);
                        player.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
                        timerqueue.schedule(player.getStringUUID() + "_killmonger_boots_unequip", i + 10, new SetItemInSlotCallback(player, EquipmentSlot.FEET, ItemStack.EMPTY, false, true));
                        timerqueue.schedule(player.getStringUUID() + "_killmonger_leggings_unequip", i + 20, new SetItemInSlotCallback(player, EquipmentSlot.LEGS, ItemStack.EMPTY, false, true));
                        timerqueue.schedule(player.getStringUUID() + "_killmonger_chestplate_unequip", i + 30, new SetItemInSlotCallback(player, EquipmentSlot.CHEST, ItemStack.EMPTY, false, true));
                        timerqueue.schedule(player.getStringUUID() + "_killmonger_necklace_equip", i + 30, new SetItemInCurioSlotCallback(player, "necklace", 0, necklace));
                    }
                    if (CuriosApi.getCuriosInventory(player).get().isEquipped(MarvelItems.BLACK_PANTHER_SHURI_NECKLACE.get()) && noKineticBlackPantherArmor && noThorArmor) {
                        SlotResult slotResult = CuriosApi.getCuriosInventory(player).get().findFirstCurio(MarvelItems.BLACK_PANTHER_SHURI_NECKLACE.get()).get();
                        ItemContainerContents necklaceContents = slotResult.stack().get(DataComponents.CONTAINER);
                        TimerQueue<MinecraftServer> timerqueue = player.getServer().getWorldData().overworldData().getScheduledEvents();
                        long i = player.serverLevel().getGameTime();
                        player.serverLevel().playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.KINETIC_BLACK_PANTHER_SUIT_UP.get(), SoundSource.PLAYERS);
                        timerqueue.schedule(player.getStringUUID() + "_black_panther_shuri_chestplate_equip", i + 1, new SetItemInSlotCallback(player, EquipmentSlot.CHEST, necklaceContents.getStackInSlot(2), false, false));
                        timerqueue.schedule(player.getStringUUID() + "_black_panther_shuri_leggings_equip", i + 10, new SetItemInSlotCallback(player, EquipmentSlot.LEGS, necklaceContents.getStackInSlot(1), false, false));
                        timerqueue.schedule(player.getStringUUID() + "_black_panther_shuri_boots_equip", i + 20, new SetItemInSlotCallback(player, EquipmentSlot.FEET, necklaceContents.getStackInSlot(0), false, false));
                        timerqueue.schedule(player.getStringUUID() + "_black_panther_shuri_helmet_equip", i + 30, new SetItemInSlotCallback(player, EquipmentSlot.HEAD, necklaceContents.getStackInSlot(3), false, false));
                        CuriosApi.getCuriosInventory(player).get().setEquippedCurio(slotResult.slotContext().identifier(), slotResult.slotContext().index(), ItemStack.EMPTY);
                    } else if (CuriosApi.getCuriosInventory(player).get().findCurio("necklace", 0).isEmpty() && boots.is(MarvelItems.BLACK_PANTHER_SHURI_BOOTS) && leggings.is(MarvelItems.BLACK_PANTHER_SHURI_LEGGINGS) && chestplate.is(MarvelItems.BLACK_PANTHER_SHURI_CHESTPLATE) && helmet.is(MarvelItems.BLACK_PANTHER_SHURI_HELMET)) {
                        ItemStack necklace = MarvelItems.BLACK_PANTHER_SHURI_NECKLACE.toStack();
                        necklace.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(player.getInventory().armor));
                        TimerQueue<MinecraftServer> timerqueue = player.getServer().getWorldData().overworldData().getScheduledEvents();
                        long i = player.serverLevel().getGameTime();
                        player.serverLevel().playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.KINETIC_BLACK_PANTHER_SUIT_DOWN.get(), SoundSource.PLAYERS);
                        player.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
                        timerqueue.schedule(player.getStringUUID() + "_black_panther_shuri_boots_unequip", i + 10, new SetItemInSlotCallback(player, EquipmentSlot.FEET, ItemStack.EMPTY, false, true));
                        timerqueue.schedule(player.getStringUUID() + "_black_panther_shuri_leggings_unequip", i + 20, new SetItemInSlotCallback(player, EquipmentSlot.LEGS, ItemStack.EMPTY, false, true));
                        timerqueue.schedule(player.getStringUUID() + "_black_panther_shuri_chestplate_unequip", i + 30, new SetItemInSlotCallback(player, EquipmentSlot.CHEST, ItemStack.EMPTY, false, true));
                        timerqueue.schedule(player.getStringUUID() + "_black_panther_shuri_necklace_equip", i + 30, new SetItemInCurioSlotCallback(player, "necklace", 0, necklace));
                    }
                    if (boots.is(MarvelItems.IRON_MAN_MARK_5_BOOTS) && leggings.is(MarvelItems.IRON_MAN_MARK_5_LEGGINGS) && chestplate.is(MarvelItems.IRON_MAN_MARK_5_CHESTPLATE) && helmet.is(MarvelItems.IRON_MAN_MARK_5_HELMET)) {
                        ItemStack suitcase = MarvelItems.IRON_MAN_MARK_5_SUITCASE.toStack();
                        suitcase.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(player.getInventory().armor));
                        player.serverLevel().playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.IRON_MAN_HELMET_CLOSE.get(), SoundSource.PLAYERS);
                        player.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
                        player.setItemSlot(EquipmentSlot.CHEST, ItemStack.EMPTY);
                        player.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);
                        player.setItemSlot(EquipmentSlot.FEET, ItemStack.EMPTY);
                        if (!player.addItem(suitcase)) {
                            player.drop(suitcase, true);
                        }
                    }
                }
            }
        });
    }

    @Override
    public Type<SecondarySuitAbilityMessage> type() {
        return MarvelNetworking.SECONDARY_SUIT_ABILITY;
    }
}
