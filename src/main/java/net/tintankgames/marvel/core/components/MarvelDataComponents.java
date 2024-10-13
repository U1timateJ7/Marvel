package net.tintankgames.marvel.core.components;

import com.mojang.serialization.Codec;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.Unit;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.item.component.ItemStackHolder;
import net.tintankgames.marvel.world.item.component.OpticBlastMode;
import net.tintankgames.marvel.world.item.component.ShieldArt;
import net.tintankgames.marvel.world.item.component.Size;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

public class MarvelDataComponents {
    private static final DeferredRegister<DataComponentType<?>> REGISTER = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, MarvelSuperheroes.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Float>> ABSORBED_DAMAGE = register("absorbed_damage", () -> DataComponentType.<Float>builder().persistent(Codec.floatRange(0, 25)).networkSynchronized(ByteBufCodecs.FLOAT).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> HELMET_OPEN = register("helmet_open", () -> DataComponentType.<Boolean>builder().persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ShieldArt>> SHIELD_ART = register("shield_art", () -> DataComponentType.<ShieldArt>builder().persistent(ShieldArt.CODEC).networkSynchronized(ShieldArt.STREAM_CODEC).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> CLAWS_OUT = register("claws_out", () -> DataComponentType.<Unit>builder().persistent(Codec.unit(Unit.INSTANCE)).networkSynchronized(StreamCodec.unit(Unit.INSTANCE)).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<MobEffectInstance>>> SUIT_EFFECTS = register("suit_effects", () -> DataComponentType.<List<MobEffectInstance>>builder().persistent(Codec.withAlternative(MobEffectInstance.CODEC.listOf(), MobEffectInstance.CODEC, List::of)).networkSynchronized(MobEffectInstance.STREAM_CODEC.apply(ByteBufCodecs.list())).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<Item>>> POWER_ITEMS = register("power_items", () -> DataComponentType.<List<Item>>builder().persistent(Codec.withAlternative(BuiltInRegistries.ITEM.byNameCodec().listOf(), BuiltInRegistries.ITEM.byNameCodec(), List::of)).networkSynchronized(ByteBufCodecs.registry(Registries.ITEM).apply(ByteBufCodecs.list())).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<OpticBlastMode>> OPTIC_BLAST_MODE = register("optic_blast_mode", () -> DataComponentType.<OpticBlastMode>builder().persistent(OpticBlastMode.CODEC).networkSynchronized(OpticBlastMode.STREAM_CODEC).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> SPIDER_SENSE = register("spider_sense", () -> DataComponentType.<Integer>builder().persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Size>> SIZE = register("size", () -> DataComponentType.<Size>builder().persistent(Size.CODEC).networkSynchronized(Size.STREAM_CODEC).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> FLYING = register("flying", () -> DataComponentType.<Boolean>builder().persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).cacheEncoding().build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ItemStackHolder>> ITEM_STACK = register("item_stack", () -> DataComponentType.<ItemStackHolder>builder().persistent(ItemStackHolder.CODEC).networkSynchronized(ItemStackHolder.STREAM_CODEC).cacheEncoding().build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<UUID>> OWNER = register("owner", () -> DataComponentType.<UUID>builder().persistent(UUIDUtil.LENIENT_CODEC).networkSynchronized(UUIDUtil.STREAM_CODEC).cacheEncoding().build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> FUEL = register("fuel", () -> DataComponentType.<Integer>builder().persistent(Codec.intRange(0, Integer.MAX_VALUE)).networkSynchronized(ByteBufCodecs.INT).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> FUEL_MAX = register("fuel_max", () -> DataComponentType.<Integer>builder().persistent(Codec.intRange(0, Integer.MAX_VALUE)).networkSynchronized(ByteBufCodecs.INT).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Float>> ENERGY = register("energy", () -> DataComponentType.<Float>builder().persistent(Codec.floatRange(0, 100)).networkSynchronized(ByteBufCodecs.FLOAT).cacheEncoding().build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> SPEEDING = register("speeding", () -> DataComponentType.<Unit>builder().persistent(Codec.unit(Unit.INSTANCE)).networkSynchronized(StreamCodec.unit(Unit.INSTANCE)).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Vec3>> DELTA_MOVEMENT = register("delta_movement", () -> DataComponentType.<Vec3>builder().networkSynchronized(ByteBufCodecs.fromCodec(Vec3.CODEC)).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> FAKE = register("fake", () -> DataComponentType.<Unit>builder().networkSynchronized(StreamCodec.unit(Unit.INSTANCE)).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> IN_HAND = register("in_hand", () -> DataComponentType.<Unit>builder().networkSynchronized(StreamCodec.unit(Unit.INSTANCE)).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> HAS_KATANA_IN_INVENTORY = register("has_katana_in_inventory", () -> DataComponentType.<Boolean>builder().networkSynchronized(ByteBufCodecs.BOOL).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> INVISIBLE = register("invisible", () -> DataComponentType.<Unit>builder().persistent(Codec.unit(Unit.INSTANCE)).networkSynchronized(StreamCodec.unit(Unit.INSTANCE)).cacheEncoding().build());

    private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String id, Supplier<DataComponentType<T>> supplier) {
        return REGISTER.register(id, supplier);
    }

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }
}
