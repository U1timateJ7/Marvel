package com.ulto.marvel.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.GameType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.Minecraft;

import javax.annotation.Nullable;

import com.ulto.marvel.init.MarvelModMobEffects;

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
		if (new Object() {
			public boolean checkGamemode(Entity _ent) {
				if (_ent instanceof ServerPlayer _serverPlayer) {
					return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SURVIVAL;
				} else if (_ent.level.isClientSide() && _ent instanceof Player _player) {
					return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null && Minecraft.getInstance()
							.getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.SURVIVAL;
				}
				return false;
			}
		}.checkGamemode(entity) || new Object() {
			public boolean checkGamemode(Entity _ent) {
				if (_ent instanceof ServerPlayer _serverPlayer) {
					return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.ADVENTURE;
				} else if (_ent.level.isClientSide() && _ent instanceof Player _player) {
					return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null && Minecraft.getInstance()
							.getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.ADVENTURE;
				}
				return false;
			}
		}.checkGamemode(entity)) {
			if (!(entity instanceof LivingEntity _livEnt ? _livEnt.hasEffect(MarvelModMobEffects.ICEING) : false)) {
				if (ItemTags.getAllTags().getTagOrEmpty(new ResourceLocation("forge:flying/chestplates")).contains(
						(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem())
						&& ItemTags.getAllTags().getTagOrEmpty(new ResourceLocation("forge:flying/boots")).contains(
								(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
										.getItem())
						|| ItemTags.getAllTags().getTagOrEmpty(new ResourceLocation("forge:flying/handheld"))
								.contains((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem())
						|| ItemTags.getAllTags().getTagOrEmpty(new ResourceLocation("forge:flying/handheld"))
								.contains((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem())) {
					canFly = true;
					if (ItemTags.getAllTags().getTagOrEmpty(new ResourceLocation("forge:flying/height/180")).contains(
							(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
									.getItem())
							|| ItemTags.getAllTags().getTagOrEmpty(new ResourceLocation("forge:flying/height/180")).contains(
									(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
											.getItem())
							|| ItemTags.getAllTags().getTagOrEmpty(new ResourceLocation("forge:flying/height/180"))
									.contains((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem())
							|| ItemTags.getAllTags().getTagOrEmpty(new ResourceLocation("forge:flying/height/180"))
									.contains((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem())) {
						if (entity.getY() >= 180) {
							if (entity instanceof LivingEntity _entity)
								_entity.addEffect(new MobEffectInstance(MarvelModMobEffects.ICEING, 100, 0, (false), (false)));
						}
					} else if (ItemTags.getAllTags().getTagOrEmpty(new ResourceLocation("forge:flying/height/200")).contains(
							(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
									.getItem())
							|| ItemTags.getAllTags().getTagOrEmpty(new ResourceLocation("forge:flying/height/200")).contains(
									(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
											.getItem())
							|| ItemTags.getAllTags().getTagOrEmpty(new ResourceLocation("forge:flying/height/200"))
									.contains((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem())
							|| ItemTags.getAllTags().getTagOrEmpty(new ResourceLocation("forge:flying/height/200"))
									.contains((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem())) {
						if (entity.getY() >= 200) {
							if (entity instanceof LivingEntity _entity)
								_entity.addEffect(new MobEffectInstance(MarvelModMobEffects.ICEING, 100, 0, (false), (false)));
						}
					} else if (ItemTags.getAllTags().getTagOrEmpty(new ResourceLocation("forge:flying/height/256")).contains(
							(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
									.getItem())
							|| ItemTags.getAllTags().getTagOrEmpty(new ResourceLocation("forge:flying/height/256")).contains(
									(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
											.getItem())
							|| ItemTags.getAllTags().getTagOrEmpty(new ResourceLocation("forge:flying/height/256"))
									.contains((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem())
							|| ItemTags.getAllTags().getTagOrEmpty(new ResourceLocation("forge:flying/height/256"))
									.contains((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem())) {
						if (entity.getY() >= 256) {
							if (entity instanceof LivingEntity _entity)
								_entity.addEffect(new MobEffectInstance(MarvelModMobEffects.ICEING, 100, 0, (false), (false)));
						}
					} else if (ItemTags.getAllTags().getTagOrEmpty(new ResourceLocation("forge:flying/height/384")).contains(
							(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
									.getItem())
							|| ItemTags.getAllTags().getTagOrEmpty(new ResourceLocation("forge:flying/height/384")).contains(
									(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
											.getItem())
							|| ItemTags.getAllTags().getTagOrEmpty(new ResourceLocation("forge:flying/height/384"))
									.contains((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem())
							|| ItemTags.getAllTags().getTagOrEmpty(new ResourceLocation("forge:flying/height/384"))
									.contains((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem())) {
						if (entity.getY() >= 384) {
							if (entity instanceof LivingEntity _entity)
								_entity.addEffect(new MobEffectInstance(MarvelModMobEffects.ICEING, 100, 0, (false), (false)));
						}
					}
				}
			} else {
				canFly = false;
			}
		} else {
			canFly = true;
		}
		if (entity instanceof ServerPlayer _serverPlayer) {
			_serverPlayer.getAbilities().mayfly = canFly;
			_serverPlayer.onUpdateAbilities();
		}
	}
}
