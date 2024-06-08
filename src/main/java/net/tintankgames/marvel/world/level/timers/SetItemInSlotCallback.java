package net.tintankgames.marvel.world.level.timers;

import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.timers.TimerCallback;
import net.minecraft.world.level.timers.TimerQueue;
import net.tintankgames.marvel.MarvelSuperheroes;

import java.util.UUID;

public class SetItemInSlotCallback implements TimerCallback<MinecraftServer> {
    final UUID player;
    final EquipmentSlot slot;
    final ItemStack stack;
    final boolean playSound;

    public SetItemInSlotCallback(ServerPlayer player, EquipmentSlot slot, ItemStack stack, boolean playSound) {
        this(player.getUUID(), slot, stack, playSound);
    }

    public SetItemInSlotCallback(UUID player, EquipmentSlot slot, ItemStack stack, boolean playSound) {
        this.player = player;
        this.slot = slot;
        this.stack = stack.copy();
        this.playSound = playSound;
    }

    public void handle(MinecraftServer p_82172_, TimerQueue<MinecraftServer> p_82173_, long p_82174_) {
        ServerPlayer player = p_82172_.getPlayerList().getPlayer(this.player);
        if (this.playSound) {
            player.setItemSlot(this.slot, this.stack);
        } else {
            player.getInventory().armor.set(this.slot.getIndex(), this.stack);
        }
    }

    public static class Serializer extends TimerCallback.Serializer<MinecraftServer, SetItemInSlotCallback> {
        public Serializer() {
            super(MarvelSuperheroes.id("set_item_in_slot"), SetItemInSlotCallback.class);
        }

        public void serialize(CompoundTag p_82182_, SetItemInSlotCallback p_82183_) {
            p_82182_.putUUID("player", p_82183_.player);
            p_82182_.putString("slot", p_82183_.slot.getName());
            p_82182_.put("item", p_82183_.stack.saveOptional(VanillaRegistries.createLookup()));
            p_82182_.putBoolean("play_sound", p_82183_.playSound);
        }

        public SetItemInSlotCallback deserialize(CompoundTag p_82180_) {
            UUID player = p_82180_.getUUID("player");
            EquipmentSlot slot = EquipmentSlot.byName(p_82180_.getString("slot"));
            ItemStack stack = ItemStack.parseOptional(VanillaRegistries.createLookup(), p_82180_.getCompound("item"));
            boolean playSound = p_82180_.getBoolean("play_sound");
            return new SetItemInSlotCallback(player, slot, stack, playSound);
        }
    }
}
