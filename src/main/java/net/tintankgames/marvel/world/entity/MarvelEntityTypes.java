package net.tintankgames.marvel.world.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.entity.projectile.*;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MarvelSuperheroes.MOD_ID)
public class MarvelEntityTypes {
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(Registries.ENTITY_TYPE, MarvelSuperheroes.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<HydraAgent>> HYDRA_AGENT = register("hydra_agent", EntityType.Builder.of(HydraAgent::new, MobCategory.MONSTER).canSpawnFarFromPlayer().sized(0.6F, 1.8F).eyeHeight(1.62F).vehicleAttachment(Player.DEFAULT_VEHICLE_ATTACHMENT).clientTrackingRange(8));
    public static final DeferredHolder<EntityType<?>, EntityType<BaronZemo>> BARON_ZEMO = register("baron_zemo", EntityType.Builder.of(BaronZemo::new, MobCategory.MONSTER).canSpawnFarFromPlayer().sized(0.6F, 1.8F).eyeHeight(1.62F).vehicleAttachment(Player.DEFAULT_VEHICLE_ATTACHMENT).clientTrackingRange(8));
    public static final DeferredHolder<EntityType<?>, EntityType<WinterSoldier>> WINTER_SOLDIER = register("winter_soldier", EntityType.Builder.of(WinterSoldier::new, MobCategory.MONSTER).canSpawnFarFromPlayer().sized(0.6F, 1.8F).eyeHeight(1.62F).vehicleAttachment(Player.DEFAULT_VEHICLE_ATTACHMENT).clientTrackingRange(8));
    public static final DeferredHolder<EntityType<?>, EntityType<RedSkull>> RED_SKULL = register("red_skull", EntityType.Builder.of(RedSkull::new, MobCategory.MONSTER).canSpawnFarFromPlayer().sized(0.6F, 1.8F).eyeHeight(1.62F).vehicleAttachment(Player.DEFAULT_VEHICLE_ATTACHMENT).clientTrackingRange(8));

    public static final DeferredHolder<EntityType<?>, EntityType<ThrownVibraniumShield>> VIBRANIUM_SHIELD = register("vibranium_shield", EntityType.Builder.<ThrownVibraniumShield>of(ThrownVibraniumShield::new, MobCategory.MISC).sized(0.75F, 0.125F).clientTrackingRange(4).updateInterval(20));
    public static final DeferredHolder<EntityType<?>, EntityType<WebShot>> WEB_SHOT = register("web_shot", EntityType.Builder.<WebShot>of(WebShot::new, MobCategory.MISC).noSave().noSummon().sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(5));
    public static final DeferredHolder<EntityType<?>, EntityType<WaspSting>> WASP_STING = register("wasp_sting", EntityType.Builder.<WaspSting>of(WaspSting::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(20));
    public static final DeferredHolder<EntityType<?>, EntityType<ThrownMjolnir>> MJOLNIR = register("mjolnir", EntityType.Builder.<ThrownMjolnir>of(ThrownMjolnir::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20));
    public static final DeferredHolder<EntityType<?>, EntityType<ThrownStormbreaker>> STORMBREAKER = register("stormbreaker", EntityType.Builder.<ThrownStormbreaker>of(ThrownStormbreaker::new, MobCategory.MISC).sized(1.0F, 0.25F).clientTrackingRange(4).updateInterval(20));
    public static final DeferredHolder<EntityType<?>, EntityType<Flame>> FLAME = register("flame", EntityType.Builder.<Flame>of(Flame::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(20));
    public static final DeferredHolder<EntityType<?>, EntityType<Repulsor>> REPULSOR = register("repulsor", EntityType.Builder.<Repulsor>of(Repulsor::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(20));
    public static final DeferredHolder<EntityType<?>, EntityType<TesseractCharge>> TESSERACT_CHARGE = register("tesseract_charge", EntityType.Builder.<TesseractCharge>of(TesseractCharge::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(20));

    private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String id, EntityType.Builder<T> entityTypeBuilder) {
        return REGISTER.register(id, () -> entityTypeBuilder.build(id));
    }

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }

    @SubscribeEvent
    public static void attributes(EntityAttributeCreationEvent event) {
        event.put(HYDRA_AGENT.get(), HydraAgent.createAttributes().build());
        event.put(BARON_ZEMO.get(), BaronZemo.createAttributes().build());
        event.put(WINTER_SOLDIER.get(), WinterSoldier.createAttributes().build());
        event.put(RED_SKULL.get(), RedSkull.createAttributes().build());
    }

    @SubscribeEvent
    public static void attributes(RegisterSpawnPlacementsEvent event) {
        event.register(HYDRA_AGENT.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, HydraAgent::checkHydraAgentSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(BARON_ZEMO.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkAnyLightMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(WINTER_SOLDIER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkAnyLightMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(RED_SKULL.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkAnyLightMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
    }

    public static class Tags {
        public static final TagKey<EntityType<?>> BLOCKED_BY_VIBRANIUM_SHIELD = create("blocked_by_vibranium_shield");

        private static TagKey<EntityType<?>> create(String id) {
            return REGISTER.createTagKey(id);
        }
    }
}
