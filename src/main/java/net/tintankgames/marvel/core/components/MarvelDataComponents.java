package net.tintankgames.marvel.core.components;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.Unit;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.item.component.OpticBlastMode;
import net.tintankgames.marvel.world.item.component.ShieldArt;

import java.util.List;
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

    private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String id, Supplier<DataComponentType<T>> supplier) {
        return REGISTER.register(id, supplier);
    }

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }
}
