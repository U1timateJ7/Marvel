package net.tintankgames.marvel.attachment;

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

    private static <T> DeferredHolder<AttachmentType<?>, AttachmentType<T>> register(String id, Supplier<AttachmentType<T>> supplier) {
        return REGISTER.register(id, supplier);
    }

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }
}
