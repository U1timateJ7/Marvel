package net.tintankgames.marvel.world.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.entity.projectile.ThrownVibraniumShield;

public class MarvelEntityTypes {
    private static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(Registries.ENTITY_TYPE, MarvelSuperheroes.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<ThrownVibraniumShield>> VIBRANIUM_SHIELD = register("vibranium_shield", EntityType.Builder.<ThrownVibraniumShield>of(ThrownVibraniumShield::new, MobCategory.MISC).sized(0.75F, 0.125F).clientTrackingRange(4).updateInterval(20));

    private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String id, EntityType.Builder<T> entityTypeBuilder) {
        return REGISTER.register(id, () -> entityTypeBuilder.build(id));
    }

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }

    public static class Tags {
        public static final TagKey<EntityType<?>> BLOCKED_BY_VIBRANIUM_SHIELD = create("blocked_by_vibranium_shield");

        private static TagKey<EntityType<?>> create(String id) {
            return REGISTER.createTagKey(id);
        }
    }
}
