package net.tintankgames.marvel.attachment;

import com.mojang.serialization.Codec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.entity.projectile.WebShot;

import java.util.function.Supplier;

public class MarvelAttachmentTypes {
    private static final DeferredRegister<AttachmentType<?>> REGISTER = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MarvelSuperheroes.MOD_ID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<EntityHolder<WebShot>>> GRAPPLING = register("grappling", () -> AttachmentType.builder(() -> new EntityHolder<WebShot>(null)).build());
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Float>> HEALING_FACTOR_TRACKER = register("healing_factor_tracker", () -> AttachmentType.builder(() -> 20.0F).serialize(Codec.floatRange(0, Float.MAX_VALUE)).build());
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Integer>> UNDERWATER_TICKS = register("underwater_ticks", () -> AttachmentType.builder(() -> 0).serialize(Codec.intRange(0, Integer.MAX_VALUE)).build());
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<TargetedEntity>> TARGETED_ENTITY = register("targeted_entity", () -> AttachmentType.serializable(() -> new TargetedEntity()).build());

    private static <T> DeferredHolder<AttachmentType<?>, AttachmentType<T>> register(String id, Supplier<AttachmentType<T>> supplier) {
        return REGISTER.register(id, supplier);
    }

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }
}
