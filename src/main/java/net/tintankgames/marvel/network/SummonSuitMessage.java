package net.tintankgames.marvel.network;

import com.google.common.collect.Streams;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.attachment.VeronicaData;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.entity.IronManSuitPart;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.SummonableIronManSuitItem;
import net.tintankgames.marvel.world.item.component.SuitParts;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public record SummonSuitMessage(boolean summoning) implements CustomPacketPayload {
    public static final StreamCodec<RegistryFriendlyByteBuf, SummonSuitMessage> CODEC = StreamCodec.composite(ByteBufCodecs.BOOL, SummonSuitMessage::summoning, SummonSuitMessage::new);

    public static void handle(SummonSuitMessage message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.flow().isServerbound() && context.player() instanceof ServerPlayer player) {
                if (Streams.stream(player.getArmorSlots()).noneMatch(stack -> stack.is(MarvelItems.Tags.IRON_MAN_ARMOR))) {
                    VeronicaData.Suit suit = player.getData(MarvelAttachmentTypes.VERONICA).getSuit(suit1 -> suit1.armor().stream().allMatch(stack -> stack.getItem() instanceof SummonableIronManSuitItem || stack.isEmpty()) && suit1.armor().stream().anyMatch(stack -> stack.getItem() instanceof SummonableIronManSuitItem), new Comparator<VeronicaData.Suit>() {
                        @Override
                        public int compare(VeronicaData.Suit o1, VeronicaData.Suit o2) {
                            int damage1 = o1.armor().stream().collect(AtomicInteger::new, (atom, stack) -> atom.addAndGet(stack.getDamageValue()), (atom1, atom2) -> atom1.addAndGet(atom2.get())).get();
                            int damage2 = o2.armor().stream().collect(AtomicInteger::new, (atom, stack) -> atom.addAndGet(stack.getDamageValue()), (atom1, atom2) -> atom1.addAndGet(atom2.get())).get();
                            int result = Integer.compare(damage1, damage2);
                            if (result == 0) {
                                result = -Integer.compare(o1.mark(), o2.mark());
                            }
                            return result;
                        }
                    });
                    if (message.summoning && !player.getData(MarvelAttachmentTypes.SUMMONED_SUIT) && suit != null) {
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
                    }
                    player.setData(MarvelAttachmentTypes.SUMMONING_SUIT, message.summoning);
                }
            }
        });
    }

    @Override
    public Type<SummonSuitMessage> type() {
        return MarvelNetworking.SUMMON_SUIT;
    }
}
