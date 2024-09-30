package net.tintankgames.marvel.attachment;

import com.mojang.serialization.Codec;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.entity.projectile.WebShot;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class MarvelAttachmentTypes {
    private static final DeferredRegister<AttachmentType<?>> REGISTER = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MarvelSuperheroes.MOD_ID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<EntityHolder<WebShot>>> GRAPPLING = register("grappling", () -> AttachmentType.builder(() -> new EntityHolder<WebShot>(null)).build());
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Float>> HEALING_FACTOR_TRACKER = register("healing_factor_tracker", () -> AttachmentType.builder(() -> 20.0F).serialize(Codec.floatRange(0, Float.MAX_VALUE)).build());
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Integer>> UNDERWATER_TICKS = register("underwater_ticks", () -> AttachmentType.builder(() -> 0).serialize(Codec.intRange(0, Integer.MAX_VALUE)).build());
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<TargetedEntity>> TARGETED_ENTITY = register("targeted_entity", () -> AttachmentType.serializable(() -> new TargetedEntity()).build());
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Boolean>> MOVING = register("moving", () -> AttachmentType.builder(() -> false).build());
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Vec3>> DELTA_MOVEMENT = register("delta_movement", () -> AttachmentType.builder(() -> Vec3.ZERO).build());
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Boolean>> TESSERACT_CHARGED = register("tesseract_charged", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build());
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<InfinityStoneData>> INFINITY_STONES = register("infinity_stones", () -> AttachmentType.builder(() -> new InfinityStoneData(List.of(), Map.of())).serialize(InfinityStoneData.CODEC).build());
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Boolean>> CALLING_MJOLNIR = register("calling_mjolnir", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build());
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<AnimationState>> TURRET_EQUIP_ANIMATION_STATE = register("turret_equip_animation_state", () -> AttachmentType.builder(AnimationState::new).build());
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<AnimationState>> TURRET_UNEQUIP_ANIMATION_STATE = register("turret_unequip_animation_state", () -> AttachmentType.builder(AnimationState::new).build());

    private static <T> DeferredHolder<AttachmentType<?>, AttachmentType<T>> register(String id, Supplier<AttachmentType<T>> supplier) {
        return REGISTER.register(id, supplier);
    }

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }
}
