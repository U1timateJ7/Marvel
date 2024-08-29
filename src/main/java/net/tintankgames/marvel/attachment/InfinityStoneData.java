package net.tintankgames.marvel.attachment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.Keyable;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.tintankgames.marvel.world.item.MarvelItems;

import java.util.*;

@EventBusSubscriber
public class InfinityStoneData {
    public static final Codec<InfinityStoneData> CODEC = RecordCodecBuilder.create(instance -> instance.group(InfinityStone.CODEC.listOf().fieldOf("found_stones").forGetter(data -> data.foundStones), Codec.simpleMap(InfinityStone.CODEC, Codec.BOOL, Keyable.forStrings(() -> Arrays.stream(InfinityStone.values()).map(InfinityStone::getName))).fieldOf("exclusive_stones").forGetter(data -> data.exclusiveStones)).apply(instance, InfinityStoneData::new));

    private final List<InfinityStone> foundStones;
    private final Map<InfinityStone, Boolean> exclusiveStones;

    public InfinityStoneData(List<InfinityStone> foundStones, Map<InfinityStone, Boolean> exclusiveStones) {
        this.foundStones = new ArrayList<>(foundStones);
        this.exclusiveStones = new HashMap<>(exclusiveStones);
    }

    public void setFoundStone(InfinityStone infinityStone, boolean found) {
        if (!found) {
            foundStones.remove(infinityStone);
        } else if (!foundStones.contains(infinityStone)) {
            foundStones.add(infinityStone);
        }
    }

    public void setExclusive(InfinityStone stone, boolean exclusive) {
        exclusiveStones.put(stone, exclusive);
    }

    public boolean hasFoundStone(InfinityStone stone) {
        return foundStones.contains(stone);
    }

    public boolean isExclusive(InfinityStone stone) {
        return exclusiveStones.get(stone);
    }

    @SubscribeEvent
    public static void tick(PlayerTickEvent.Post event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            if (player.getInventory().contains(stack -> stack.is(MarvelItems.SPACE_STONE) || stack.is(MarvelItems.TESSERACT)) && !player.server.overworld().getData(MarvelAttachmentTypes.INFINITY_STONES).foundStones.contains(InfinityStone.SPACE)) {
                player.server.overworld().getData(MarvelAttachmentTypes.INFINITY_STONES).foundStones.add(InfinityStone.SPACE);
            }
        }
    }

    @SubscribeEvent
    public static void tick(ServerTickEvent.Post event) {
        InfinityStoneData data = event.getServer().overworld().getData(MarvelAttachmentTypes.INFINITY_STONES);
        for (InfinityStone stone : InfinityStone.values()) {
            if (!data.exclusiveStones.computeIfAbsent(stone, infinityStone -> true)) {
                data.foundStones.remove(stone);
            }
        }
    }
}
