package net.tintankgames.marvel.world.item.component;

import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

public record ItemStackHolder(ItemStack stack) {
    public static final Codec<ItemStackHolder> CODEC = ItemStack.OPTIONAL_CODEC.xmap(ItemStackHolder::new, ItemStackHolder::stack);
    public static final StreamCodec<RegistryFriendlyByteBuf, ItemStackHolder> STREAM_CODEC = ItemStack.OPTIONAL_STREAM_CODEC.map(ItemStackHolder::new, ItemStackHolder::stack);
}
