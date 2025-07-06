package net.tintankgames.marvel.attachment;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.entity.IronManSuitPart;
import net.tintankgames.marvel.world.item.EnergySuitItem;
import net.tintankgames.marvel.world.item.SentryIronManSuitItem;
import net.tintankgames.marvel.world.item.SummonableIronManSuitItem;
import net.tintankgames.marvel.world.item.component.SuitParts;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

@EventBusSubscriber
public class VeronicaData {
    public static final Codec<VeronicaData> CODEC = RecordCodecBuilder.create(instance -> instance.group(Codec.BOOL.fieldOf("enabled").forGetter(data -> data.enabled), Suit.CODEC.listOf().fieldOf("suits").forGetter(data -> data.suits), Codec.INT.fieldOf("next_id").forGetter(data -> data.nextId)).apply(instance, VeronicaData::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, VeronicaData> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.BOOL, data -> data.enabled, Suit.STREAM_CODEC.apply(ByteBufCodecs.list()), data -> data.suits, ByteBufCodecs.INT, data -> data.nextId, VeronicaData::new);

    private boolean enabled;
    private final List<Suit> suits;
    private int nextId;

    public VeronicaData(boolean enabled, List<Suit> suits, int nextId) {
        this.enabled = enabled;
        this.suits = new ArrayList<>(suits);
        this.suits.forEach(suit -> suit.armor.forEach(piece -> {
            piece.remove(MarvelDataComponents.FLYING);
            piece.remove(MarvelDataComponents.DELTA_MOVEMENT);
        }));
        this.nextId = nextId;
        if (this.suits.size() > 1) this.suits.sort(Comparator.comparingInt(Suit::mark));
    }

    public boolean enabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Suit> getSuits() {
        return ImmutableList.copyOf(suits);
    }

    public void forEach(Consumer<Suit> action) {
        Objects.requireNonNull(action);
        for (Suit suit : suits) {
            action.accept(suit);
        }
    }

    public void addSuit(Suit suit) {
        suit.armor.forEach(piece -> {
            piece.remove(MarvelDataComponents.FLYING);
            piece.remove(MarvelDataComponents.DELTA_MOVEMENT);
        });
        suits.add(suit);
        suits.sort(Comparator.comparingInt(Suit::mark));
    }

    @Nullable
    public Suit getSuit(int id) {
        for (Suit suit1 : suits) {
            if (suit1.id == id) {
                return suit1;
            }
        }
        return null;
    }

    public void removeSuit(int id) {
        for (Suit suit1 : suits) {
            if (suit1.id == id) {
                suits.remove(suit1);
                if (suits.isEmpty()) nextId = 0;
                return;
            }
        }
    }

    public void clear() {
        suits.clear();
        nextId = 0;
    }

    public int nextId() {
        nextId++;
        return nextId - 1;
    }

    public void addSuitPart(IronManSuitPart suitPart) {
        SummonableIronManSuitItem item = (SummonableIronManSuitItem) suitPart.getPiece().getItem();
        for (Suit suit : suits) {
            if (suit.armor.stream().anyMatch(item::isSuitPiece)) {
                if (suit.armor.get(3 - item.getType().ordinal()).isEmpty()) {
                    suit.armor.set(3 - item.getType().ordinal(), suitPart.getPiece().copy());
                    return;
                }
                SuitParts parts = suit.armor.get(3 - item.getType().ordinal()).getOrDefault(MarvelDataComponents.SUIT_PARTS, new SuitParts(item.getType() == ArmorItem.Type.CHESTPLATE ? List.of(true, true, true, true, true, true) : List.of(true, true)));
                SuitParts suitParts = suitPart.getPiece().getOrDefault(MarvelDataComponents.SUIT_PARTS, new SuitParts(item.getType() == ArmorItem.Type.CHESTPLATE ? List.of(true, true, true, true, true, true) : List.of(true, true)));
                boolean hasPartOpen = false;
                for (int i = 0; i < suitParts.parts().size(); i++) {
                    hasPartOpen = !parts.parts().get(i) && suitParts.parts().get(i);
                    if (hasPartOpen) {
                        parts.parts().set(i, true);
                        break;
                    }
                }
                if (hasPartOpen) {
                    suit.armor.get(3 - item.getType().ordinal()).set(MarvelDataComponents.SUIT_PARTS, parts);
                    return;
                }
            }
        }
        List<ItemStack> armor = Lists.newArrayList(ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY);
        armor.set(3 - item.getType().ordinal(), suitPart.getPiece().copy());
        addSuit(new Suit(armor, item.markNumber(), nextId()));
    }

    public Suit getSuit(Predicate<Suit> predicate, @Nullable Comparator<Suit> sorter) {
        Suit suit = null;
        List<Suit> list = new ArrayList<>(suits);
        if (sorter != null) list.sort(sorter);
        for (Suit suit1 : list) {
            if (predicate.test(suit1)) {
                suit = suit1;
                break;
            }
        }
        return suit;
    }

    public record Suit(List<ItemStack> armor, int mark, int id) implements Comparable<Suit> {
        public static final Codec<Suit> CODEC = RecordCodecBuilder.create(instance -> instance.group(ItemStack.OPTIONAL_CODEC.listOf(4, 4).fieldOf("armor").forGetter(Suit::armor), Codec.intRange(0, Integer.MAX_VALUE).fieldOf("mark").forGetter(Suit::mark), Codec.intRange(0, Integer.MAX_VALUE).fieldOf("id").forGetter(Suit::id)).apply(instance, Suit::new));
        public static final StreamCodec<RegistryFriendlyByteBuf, Suit> STREAM_CODEC = StreamCodec.composite(ItemStack.OPTIONAL_LIST_STREAM_CODEC, Suit::armor, ByteBufCodecs.INT, Suit::mark, ByteBufCodecs.INT, Suit::id, Suit::new);

        @Override
        public int compareTo(@NotNull VeronicaData.Suit o) {
            return Integer.compare(mark(), o.mark());
        }

        public SentryIronManSuitItem helmet() {
            ItemStack armorStack = armor.stream().filter(stack -> stack.getItem() instanceof SentryIronManSuitItem).toList().getFirst();
            return (SentryIronManSuitItem) BuiltInRegistries.ITEM.get(BuiltInRegistries.ITEM.getKey(armorStack.getItem()).withPath(id -> id.replace("_chestplate", "_helmet").replace("_leggings", "_helmet").replace("_boots", "_helmet")));
        }

        public SentryIronManSuitItem chestplate() {
            ItemStack armorStack = armor.stream().filter(stack -> stack.getItem() instanceof SentryIronManSuitItem).toList().getFirst();
            return (SentryIronManSuitItem) BuiltInRegistries.ITEM.get(BuiltInRegistries.ITEM.getKey(armorStack.getItem()).withPath(id -> id.replace("_helmet", "_chestplate").replace("_leggings", "_chestplate").replace("_boots", "_chestplate")));
        }

        public SentryIronManSuitItem leggings() {
            ItemStack armorStack = armor.stream().filter(stack -> stack.getItem() instanceof SentryIronManSuitItem).toList().getFirst();
            return (SentryIronManSuitItem) BuiltInRegistries.ITEM.get(BuiltInRegistries.ITEM.getKey(armorStack.getItem()).withPath(id -> id.replace("_helmet", "_leggings").replace("_chestplate", "_leggings").replace("_boots", "_leggings")));
        }

        public SentryIronManSuitItem boots() {
            ItemStack armorStack = armor.stream().filter(stack -> stack.getItem() instanceof SentryIronManSuitItem).toList().getFirst();
            return (SentryIronManSuitItem) BuiltInRegistries.ITEM.get(BuiltInRegistries.ITEM.getKey(armorStack.getItem()).withPath(id -> id.replace("_helmet", "_boots").replace("_chestplate", "_boots").replace("_leggings", "_boots")));
        }
    }

    @SubscribeEvent
    public static void tick(PlayerTickEvent.Post event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            VeronicaData veronica = player.getData(MarvelAttachmentTypes.VERONICA);
            for (Suit suit : veronica.suits) {
                suit.armor.forEach(piece -> EnergySuitItem.addEnergy(piece, 0.005F));
            }
            PacketDistributor.sendToPlayer(player, new SyncMessage(veronica));
        }
    }

    @SubscribeEvent
    public static void clone(PlayerEvent.Clone event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            player.setData(MarvelAttachmentTypes.VERONICA, event.getOriginal().getData(MarvelAttachmentTypes.VERONICA));
        }
    }

    public record SyncMessage(VeronicaData veronicaData) implements CustomPacketPayload {
        public static final Type<SyncMessage> TYPE = new Type<>(MarvelSuperheroes.id("veronica_sync"));
        public static final StreamCodec<RegistryFriendlyByteBuf, SyncMessage> CODEC = StreamCodec.composite(VeronicaData.STREAM_CODEC, SyncMessage::veronicaData, SyncMessage::new);

        public static void handle(SyncMessage message, IPayloadContext context) {
            context.enqueueWork(() -> {
                if (context.flow().isClientbound() && context.player() instanceof LocalPlayer player) {
                    player.setData(MarvelAttachmentTypes.VERONICA, message.veronicaData());
                }
            });
        }

        @Override
        public Type<SyncMessage> type() {
            return TYPE;
        }
    }
}
