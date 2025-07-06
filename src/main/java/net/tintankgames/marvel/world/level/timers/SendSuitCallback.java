package net.tintankgames.marvel.world.level.timers;

import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.resources.RegistryOps;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.timers.TimerCallback;
import net.minecraft.world.level.timers.TimerQueue;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.attachment.VeronicaData;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.entity.IronManSuitPart;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;
import net.tintankgames.marvel.world.entity.VeronicaSentry;
import net.tintankgames.marvel.world.item.SummonableIronManSuitItem;
import net.tintankgames.marvel.world.item.component.SuitParts;

import java.util.List;
import java.util.UUID;

public class SendSuitCallback implements TimerCallback<MinecraftServer> {
    final UUID player;
    final VeronicaData.Suit suit;

    public SendSuitCallback(UUID player, VeronicaData.Suit suit) {
        this.player = player;
        this.suit = suit;
    }

    public void handle(MinecraftServer server, TimerQueue<MinecraftServer> p_82173_, long p_82174_) {
        ServerPlayer player = server.getPlayerList().getPlayer(this.player);
        if (player != null) {
            boolean sendParts = suit.armor().stream().anyMatch(stack -> stack.getItem() instanceof SummonableIronManSuitItem suitItem && !stack.getOrDefault(MarvelDataComponents.SUIT_PARTS, SuitParts.defaultParts(suitItem.getType(), true)).hasAllParts());
            if (sendParts) {
                player.setData(MarvelAttachmentTypes.SUMMONED_SUIT, true);
                for (ItemStack stack : suit.armor().stream().filter(stack -> !stack.isEmpty()).toList()) {
                    SummonableIronManSuitItem item = (SummonableIronManSuitItem) stack.getItem();
                    SuitParts parts = stack.getOrDefault(MarvelDataComponents.SUIT_PARTS, new SuitParts(item.getType() == ArmorItem.Type.CHESTPLATE ? List.of(true, true, true, true, true, true) : List.of(true, true)));
                    for (int i = 0; i < parts.parts().size(); i++) {
                        if (parts.parts().get(i)) {
                            IronManSuitPart part = MarvelEntityTypes.IRON_MAN_SUIT_PART.get().spawn(player.serverLevel(), player.blockPosition().above(128), MobSpawnType.TRIGGERED);
                            part.setTame(true, false);
                            part.setOwnerUUID(player.getUUID());
                            ItemStack newStack = stack.copy();
                            newStack.set(MarvelDataComponents.SUIT_PARTS, SuitParts.onePart(i, parts.parts().size()));
                            part.setPiece(newStack);
                            part.setDelay(player.getRandom().nextInt(20, 160));
                        }
                    }
                }
                player.getData(MarvelAttachmentTypes.VERONICA).removeSuit(suit.id());
            } else {
                VeronicaSentry sentry = suit.chestplate().type().spawn(player.serverLevel(), player.blockPosition().offset(player.getRandom().nextInt(-3, 3), 128, player.getRandom().nextInt(-3, 3)), MobSpawnType.TRIGGERED);
                if (sentry != null) {
                    sentry.setItemSlot(EquipmentSlot.FEET, suit.armor().get(0));
                    sentry.setItemSlot(EquipmentSlot.LEGS, suit.armor().get(1));
                    sentry.setItemSlot(EquipmentSlot.CHEST, suit.armor().get(2));
                    sentry.setItemSlot(EquipmentSlot.HEAD, suit.armor().get(3));
                    sentry.setOwnerUUID(player.getUUID());
                    sentry.setFromVeronica(true);
                    player.getData(MarvelAttachmentTypes.VERONICA).removeSuit(suit.id());
                }
            }
        }
    }

    public static class Serializer extends TimerCallback.Serializer<MinecraftServer, SendSuitCallback> {
        public Serializer() {
            super(MarvelSuperheroes.id("send_suit"), SendSuitCallback.class);
        }

        public void serialize(CompoundTag tag, SendSuitCallback callback) {
            tag.putUUID("player", callback.player);
            tag.put("suit", RegistryOps.create(NbtOps.INSTANCE, ServerLifecycleHooks.getCurrentServer() != null ? ServerLifecycleHooks.getCurrentServer().registryAccess() : VanillaRegistries.createLookup()).withEncoder(VeronicaData.Suit.CODEC).apply(callback.suit).getOrThrow());
        }

        public SendSuitCallback deserialize(CompoundTag tag) {
            UUID player = tag.getUUID("player");
            VeronicaData.Suit suit = RegistryOps.create(NbtOps.INSTANCE, ServerLifecycleHooks.getCurrentServer() != null ? ServerLifecycleHooks.getCurrentServer().registryAccess() : VanillaRegistries.createLookup()).withParser(VeronicaData.Suit.CODEC).apply(tag.get("suit")).getOrThrow();
            return new SendSuitCallback(player, suit);
        }
    }
}
