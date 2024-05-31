package com.ulto.marvel.world.entity;

import com.ulto.marvel.common.MarvelMod;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MarvelModEntityTypes {
	private static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITIES, MarvelMod.MOD_ID);

	static {
		REGISTRY.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	public static final RegistryObject<EntityType<MjolnirEntity>> MJOLNIR = register("mjolnir",
			EntityType.Builder.<MjolnirEntity>of(MjolnirEntity::new, MobCategory.MISC)
					.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<StormbreakerEntity>> STORMBREAKER = register("stormbreaker",
			EntityType.Builder.<StormbreakerEntity>of(StormbreakerEntity::new, MobCategory.MISC)
					.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<WebShooterSwingEntity>> WEB_SHOOTER_SWING = register("web_shooter_swing",
			EntityType.Builder.<WebShooterSwingEntity>of(WebShooterSwingEntity::new, MobCategory.MISC)
					.setCustomClientFactory(WebShooterSwingEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<VibraniumShieldEntity>> VIBRANIUM_SHIELD = register("vibranium_shield",
			EntityType.Builder.<VibraniumShieldEntity>of(VibraniumShieldEntity::new, MobCategory.MISC)
					.setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<ShrinkingDiskEntity>> SHRINKING_DISK = register("shrinking_disk",
			EntityType.Builder.<ShrinkingDiskEntity>of(ShrinkingDiskEntity::new, MobCategory.MISC).setCustomClientFactory(ShrinkingDiskEntity::new)
					.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<GrowingDiskEntity>> GROWING_DISK = register("growing_disk",
			EntityType.Builder.<GrowingDiskEntity>of(GrowingDiskEntity::new, MobCategory.MISC).setCustomClientFactory(GrowingDiskEntity::new)
					.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<SentryMode>> SENTRY_MODE = register("sentry_mode",
			EntityType.Builder.<SentryMode>of(SentryMode::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(SentryMode::new).sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<IronManFlamethrowerEntity>> FLAMETHROWER = register("flamethrower",
			EntityType.Builder.<IronManFlamethrowerEntity>of(IronManFlamethrowerEntity::new, MobCategory.MISC)
					.setCustomClientFactory(IronManFlamethrowerEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<RepulsorEntity>> REPULSOR = register("repulsor",
			EntityType.Builder.<RepulsorEntity>of(RepulsorEntity::new, MobCategory.MISC).setCustomClientFactory(RepulsorEntity::new)
					.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<UnibeamEntity>> UNIBEAM = register("unibeam",
			EntityType.Builder.<UnibeamEntity>of(UnibeamEntity::new, MobCategory.MISC).setCustomClientFactory(UnibeamEntity::new)
					.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<WarMachineGunEntity>> WAR_MACHINE_GUN = register("war_machine_gun",
			EntityType.Builder.<WarMachineGunEntity>of(WarMachineGunEntity::new, MobCategory.MISC).setCustomClientFactory(WarMachineGunEntity::new)
					.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<WebShooterTrapEntity>> WEB_SHOOTER_TRAP = register("web_shooter_trap",
			EntityType.Builder.<WebShooterTrapEntity>of(WebShooterTrapEntity::new, MobCategory.MISC).setCustomClientFactory(WebShooterTrapEntity::new)
					.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<Mark17UnibeamEntity>> MARK_17_UNIBEAM = register("mark_17_unibeam",
			EntityType.Builder.<Mark17UnibeamEntity>of(Mark17UnibeamEntity::new, MobCategory.MISC).setCustomClientFactory(Mark17UnibeamEntity::new)
					.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));


	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(SENTRY_MODE.get(), SentryMode.createAttributes().build());
	}
}
