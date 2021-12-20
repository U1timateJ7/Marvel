
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.ulto.marvel.init;

import com.ulto.marvel.entity.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MarvelModEntities {
	private static final List<EntityType<?>> REGISTRY = new ArrayList<>();
	public static final EntityType<MjolnirEntity> MJOLNIR = register("entitybulletmjolnir",
			EntityType.Builder.<MjolnirEntity>of(MjolnirEntity::new, MobCategory.MISC).setCustomClientFactory(MjolnirEntity::new)
					.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final EntityType<CapsShieldRedEntity> CAPTAIN_AMERICAS_SHIELD_RED = register("entitybulletcaptain_americas_shield_red",
			EntityType.Builder.<CapsShieldRedEntity>of(CapsShieldRedEntity::new, MobCategory.MISC).setCustomClientFactory(CapsShieldRedEntity::new)
					.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final EntityType<StormbreakerEntity> STORMBREAKER = register("entitybulletstormbreaker",
			EntityType.Builder.<StormbreakerEntity>of(StormbreakerEntity::new, MobCategory.MISC).setCustomClientFactory(StormbreakerEntity::new)
					.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final EntityType<WebShooterSwingEntity> WEB_SHOOTER_SWING = register("entitybulletweb_shooter_swing",
			EntityType.Builder.<WebShooterSwingEntity>of(WebShooterSwingEntity::new, MobCategory.MISC)
					.setCustomClientFactory(WebShooterSwingEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final EntityType<VibraniumShieldEntity> VIBRANIUM_SHIELD = register("entitybulletvibranium_shield",
			EntityType.Builder.<VibraniumShieldEntity>of(VibraniumShieldEntity::new, MobCategory.MISC)
					.setCustomClientFactory(VibraniumShieldEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final EntityType<CapsShieldBlueEntity> CAPTAIN_AMERICAS_SHIELD_BLUE = register("entitybulletcaptain_americas_shield_blue",
			EntityType.Builder.<CapsShieldBlueEntity>of(CapsShieldBlueEntity::new, MobCategory.MISC).setCustomClientFactory(CapsShieldBlueEntity::new)
					.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final EntityType<RedGuardianShieldEntity> RED_GUARDIAN_SHIELD = register("entitybulletred_guardian_shield",
			EntityType.Builder.<RedGuardianShieldEntity>of(RedGuardianShieldEntity::new, MobCategory.MISC)
					.setCustomClientFactory(RedGuardianShieldEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final EntityType<TaskmasterShieldEntity> TASKMASTER_SHIELD = register("entitybullettaskmaster_shield",
			EntityType.Builder.<TaskmasterShieldEntity>of(TaskmasterShieldEntity::new, MobCategory.MISC)
					.setCustomClientFactory(TaskmasterShieldEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final EntityType<BloodyCapsShieldRedEntity> BLOODY_CAPTAIN_AMERICAS_SHIELD_RED = register(
			"entitybulletbloody_captain_americas_shield_red",
			EntityType.Builder.<BloodyCapsShieldRedEntity>of(BloodyCapsShieldRedEntity::new, MobCategory.MISC)
					.setCustomClientFactory(BloodyCapsShieldRedEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final EntityType<BloodyVibraniumShieldEntity> BLOODY_VIBRANIUM_SHIELD = register("entitybulletbloody_vibranium_shield",
			EntityType.Builder.<BloodyVibraniumShieldEntity>of(BloodyVibraniumShieldEntity::new, MobCategory.MISC)
					.setCustomClientFactory(BloodyVibraniumShieldEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final EntityType<BloodyCapsShieldBlueEntity> BLOODY_CAPTAIN_AMERICAS_SHIELD_BLUE = register(
			"entitybulletbloody_captain_americas_shield_blue",
			EntityType.Builder.<BloodyCapsShieldBlueEntity>of(BloodyCapsShieldBlueEntity::new, MobCategory.MISC)
					.setCustomClientFactory(BloodyCapsShieldBlueEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final EntityType<BloodyRedGuardianShieldEntity> BLOODY_RED_GUARDIAN_SHIELD = register("entitybulletbloody_red_guardian_shield",
			EntityType.Builder.<BloodyRedGuardianShieldEntity>of(BloodyRedGuardianShieldEntity::new, MobCategory.MISC)
					.setCustomClientFactory(BloodyRedGuardianShieldEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final EntityType<BloodyTaskmasterShieldEntity> BLOODY_TASKMASTER_SHIELD = register("entitybulletbloody_taskmaster_shield",
			EntityType.Builder.<BloodyTaskmasterShieldEntity>of(BloodyTaskmasterShieldEntity::new, MobCategory.MISC)
					.setCustomClientFactory(BloodyTaskmasterShieldEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final EntityType<ShrinkingDiskEntity> SHRINKING_DISK = register("entitybulletshrinking_disk",
			EntityType.Builder.<ShrinkingDiskEntity>of(ShrinkingDiskEntity::new, MobCategory.MISC).setCustomClientFactory(ShrinkingDiskEntity::new)
					.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final EntityType<GrowingDiskEntity> GROWING_DISK = register("entitybulletgrowing_disk",
			EntityType.Builder.<GrowingDiskEntity>of(GrowingDiskEntity::new, MobCategory.MISC).setCustomClientFactory(GrowingDiskEntity::new)
					.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final EntityType<SentryModeEntity> SENTRY_MODE = register("sentry_mode",
			EntityType.Builder.<SentryModeEntity>of(SentryModeEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(SentryModeEntity::new).sized(0.6f, 1.8f));
	public static final EntityType<IronManFlamethrowerEntity> FLAMETHROWER = register("entitybulletflamethrower",
			EntityType.Builder.<IronManFlamethrowerEntity>of(IronManFlamethrowerEntity::new, MobCategory.MISC)
					.setCustomClientFactory(IronManFlamethrowerEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final EntityType<RepulsorEntity> REPULSOR = register("entitybulletrepulsor",
			EntityType.Builder.<RepulsorEntity>of(RepulsorEntity::new, MobCategory.MISC).setCustomClientFactory(RepulsorEntity::new)
					.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final EntityType<UnibeamEntity> UNIBEAM = register("entitybulletunibeam",
			EntityType.Builder.<UnibeamEntity>of(UnibeamEntity::new, MobCategory.MISC).setCustomClientFactory(UnibeamEntity::new)
					.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final EntityType<WarMachineGunEntity> WAR_MACHINE_GUN = register("entitybulletwar_machine_gun",
			EntityType.Builder.<WarMachineGunEntity>of(WarMachineGunEntity::new, MobCategory.MISC).setCustomClientFactory(WarMachineGunEntity::new)
					.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final EntityType<WebShooterTrapEntity> WEB_SHOOTER_TRAP = register("entitybulletweb_shooter_trap",
			EntityType.Builder.<WebShooterTrapEntity>of(WebShooterTrapEntity::new, MobCategory.MISC).setCustomClientFactory(WebShooterTrapEntity::new)
					.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final EntityType<Mark17UnibeamEntity> MARK_17_UNIBEAM = register("entitybulletmark_17_unibeam",
			EntityType.Builder.<Mark17UnibeamEntity>of(Mark17UnibeamEntity::new, MobCategory.MISC).setCustomClientFactory(Mark17UnibeamEntity::new)
					.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));

	private static <T extends Entity> EntityType<T> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		EntityType<T> entityType = (EntityType<T>) entityTypeBuilder.build(registryname).setRegistryName(registryname);
		REGISTRY.add(entityType);
		return entityType;
	}

	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
		event.getRegistry().registerAll(REGISTRY.toArray(new EntityType[0]));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			SentryModeEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(SENTRY_MODE, SentryModeEntity.createAttributes().build());
	}
}
