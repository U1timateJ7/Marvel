package net.tintankgames.marvel.world.level;

import net.minecraft.server.level.TicketType;
import net.minecraft.world.level.ChunkPos;

import java.util.Comparator;

public class MarvelTicketTypes {
    public static final TicketType<ChunkPos> SUIT_PART = TicketType.create("suit_part", Comparator.comparingLong(ChunkPos::toLong), 40);
    public static final TicketType<ChunkPos> SUIT_CHARGER = TicketType.create("suit_charger", Comparator.comparingLong(ChunkPos::toLong), 40);
}
