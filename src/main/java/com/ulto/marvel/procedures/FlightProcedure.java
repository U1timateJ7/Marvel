package com.ulto.marvel.procedures;

import com.ulto.marvel.world.item.MarvelModItems;
import com.ulto.marvel.world.effect.MarvelModMobEffects;
import com.ulto.marvel.world.item.IronManSuitItem;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class FlightProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Entity entity = event.player;
			execute(event, entity);
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		boolean canFly = false;
		if (entity instanceof Player _player && !_player.isCreative() && !_player.isSpectator()) {
			LivingEntity _livEnt = (LivingEntity) entity;
			if (!_livEnt.hasEffect(MarvelModMobEffects.ICEING.get())) {
				LivingEntity _entGetArmor = (LivingEntity) entity;
				if (ForgeRegistries.ITEMS.tags().getTag(MarvelModItems.Tags.FLYING_CHESTPLATES).contains(
						_entGetArmor.getItemBySlot(EquipmentSlot.CHEST).getItem())
						&& ForgeRegistries.ITEMS.tags().getTag(MarvelModItems.Tags.FLYING_BOOTS).contains(
						_entGetArmor.getItemBySlot(EquipmentSlot.FEET).getItem())
						|| ForgeRegistries.ITEMS.tags().getTag(MarvelModItems.Tags.FLYING_HANDHELD)
								.contains(_livEnt.getMainHandItem().getItem())
						|| ForgeRegistries.ITEMS.tags().getTag(MarvelModItems.Tags.FLYING_HANDHELD)
								.contains(_livEnt.getOffhandItem().getItem())) {
					canFly = !(_entGetArmor.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof IronManSuitItem)
							|| !(_entGetArmor.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof IronManSuitItem) || IronManSuitItem.getBatteryOfEntity(_entGetArmor, EquipmentSlot.CHEST) >= 5f && IronManSuitItem.getBatteryOfEntity(_entGetArmor, EquipmentSlot.FEET) >= 5f;
					if (ForgeRegistries.ITEMS.tags().getTag(MarvelModItems.Tags.FLYING_HEIGHT_180).contains(
							_entGetArmor.getItemBySlot(EquipmentSlot.CHEST)
									.getItem())
							|| ForgeRegistries.ITEMS.tags().getTag(MarvelModItems.Tags.FLYING_HEIGHT_180).contains(
							_entGetArmor.getItemBySlot(EquipmentSlot.FEET)
									.getItem())
							|| ForgeRegistries.ITEMS.tags().getTag(MarvelModItems.Tags.FLYING_HEIGHT_180)
									.contains(_livEnt.getMainHandItem().getItem())
							|| ForgeRegistries.ITEMS.tags().getTag(MarvelModItems.Tags.FLYING_HEIGHT_180)
									.contains(_livEnt.getOffhandItem().getItem())) {
						if (entity.getY() >= 180) {
							LivingEntity _entity = (LivingEntity) entity;
							_entity.addEffect(new MobEffectInstance(MarvelModMobEffects.ICEING.get(), 100, 0, (false), (false)));
						}
					} else {
						if (ForgeRegistries.ITEMS.tags().getTag(MarvelModItems.Tags.FLYING_HEIGHT_200).contains(
								_entGetArmor.getItemBySlot(EquipmentSlot.CHEST)
										.getItem())
								|| ForgeRegistries.ITEMS.tags().getTag(MarvelModItems.Tags.FLYING_HEIGHT_200).contains(
								_entGetArmor.getItemBySlot(EquipmentSlot.FEET)
												.getItem())
								|| ForgeRegistries.ITEMS.tags().getTag(MarvelModItems.Tags.FLYING_HEIGHT_200)
										.contains(_livEnt.getMainHandItem().getItem())
								|| ForgeRegistries.ITEMS.tags().getTag(MarvelModItems.Tags.FLYING_HEIGHT_200)
										.contains(_livEnt.getOffhandItem().getItem())) {
							if (entity.getY() >= 200) {
								LivingEntity _entity = (LivingEntity) entity;
								_entity.addEffect(new MobEffectInstance(MarvelModMobEffects.ICEING.get(), 100, 0, (false), (false)));
							}
						} else {
							if (ForgeRegistries.ITEMS.tags().getTag(MarvelModItems.Tags.FLYING_HEIGHT_256).contains(
									_entGetArmor.getItemBySlot(EquipmentSlot.CHEST)
											.getItem())
									|| ForgeRegistries.ITEMS.tags().getTag(MarvelModItems.Tags.FLYING_HEIGHT_256).contains(
									_entGetArmor.getItemBySlot(EquipmentSlot.FEET)
											.getItem())
									|| ForgeRegistries.ITEMS.tags().getTag(MarvelModItems.Tags.FLYING_HEIGHT_256)
									.contains(_livEnt.getMainHandItem().getItem())
									|| ForgeRegistries.ITEMS.tags().getTag(MarvelModItems.Tags.FLYING_HEIGHT_256)
									.contains(_livEnt.getOffhandItem().getItem())) {
								if (entity.getY() >= 256) {
									LivingEntity _entity = (LivingEntity) entity;
									_entity.addEffect(new MobEffectInstance(MarvelModMobEffects.ICEING.get(), 100, 0, (false), (false)));
								}
							} else {
								if (ForgeRegistries.ITEMS.tags().getTag(MarvelModItems.Tags.FLYING_HEIGHT_384).contains(
										_entGetArmor.getItemBySlot(EquipmentSlot.CHEST)
												.getItem())
										|| ForgeRegistries.ITEMS.tags().getTag(MarvelModItems.Tags.FLYING_HEIGHT_384).contains(
										_entGetArmor.getItemBySlot(EquipmentSlot.FEET)
												.getItem())
										|| ForgeRegistries.ITEMS.tags().getTag(MarvelModItems.Tags.FLYING_HEIGHT_384)
										.contains(_livEnt.getMainHandItem().getItem())
										|| ForgeRegistries.ITEMS.tags().getTag(MarvelModItems.Tags.FLYING_HEIGHT_384)
										.contains(_livEnt.getOffhandItem().getItem())) {
									if (entity.getY() >= 384) {
										LivingEntity _entity = (LivingEntity) entity;
										_entity.addEffect(new MobEffectInstance(MarvelModMobEffects.ICEING.get(), 100, 0, (false), (false)));
									}
								}
							}
						}
					}
				}
			}
		} else {
			canFly = true;
		}
		if (entity instanceof ServerPlayer _serverPlayer) {
			_serverPlayer.getAbilities().mayfly = canFly;
			if (_serverPlayer.getAbilities().flying && !canFly) _serverPlayer.getAbilities().flying = false;
			_serverPlayer.onUpdateAbilities();
		}
	}
}
