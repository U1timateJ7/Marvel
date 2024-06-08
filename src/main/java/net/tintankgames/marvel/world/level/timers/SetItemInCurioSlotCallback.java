package net.tintankgames.marvel.world.level.timers;

import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.timers.TimerCallback;
import net.minecraft.world.level.timers.TimerQueue;
import net.tintankgames.marvel.MarvelSuperheroes;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.UUID;

public class SetItemInCurioSlotCallback implements TimerCallback<MinecraftServer> {
    final UUID player;
    final String identifier;
    final int index;
    final ItemStack stack;

    public SetItemInCurioSlotCallback(ServerPlayer player, String identifier, int index, ItemStack stack) {
        this(player.getUUID(), identifier, index, stack);
    }

    public SetItemInCurioSlotCallback(UUID player, String identifier, int index, ItemStack stack) {
        this.player = player;
        this.identifier = identifier;
        this.index = index;
        this.stack = stack.copy();
    }

    public void handle(MinecraftServer p_82172_, TimerQueue<MinecraftServer> p_82173_, long p_82174_) {
        ServerPlayer player = p_82172_.getPlayerList().getPlayer(this.player);
        CuriosApi.getCuriosInventory(player).get().setEquippedCurio(this.identifier, this.index, this.stack);
    }

    public static class Serializer extends TimerCallback.Serializer<MinecraftServer, SetItemInCurioSlotCallback> {
        public Serializer() {
            super(MarvelSuperheroes.id("set_item_in_curio_slot"), SetItemInCurioSlotCallback.class);
        }

        public void serialize(CompoundTag p_82182_, SetItemInCurioSlotCallback p_82183_) {
            p_82182_.putUUID("player", p_82183_.player);
            p_82182_.putString("identifier", p_82183_.identifier);
            p_82182_.putInt("index", p_82183_.index);
            p_82182_.put("item", p_82183_.stack.saveOptional(VanillaRegistries.createLookup()));
        }

        public SetItemInCurioSlotCallback deserialize(CompoundTag p_82180_) {
            UUID player = p_82180_.getUUID("player");
            String identifier = p_82180_.getString("identifier");
            int index = p_82180_.getInt("index");
            ItemStack stack = ItemStack.parseOptional(VanillaRegistries.createLookup(), p_82180_.getCompound("item"));
            return new SetItemInCurioSlotCallback(player, identifier, index, stack);
        }
    }
}
