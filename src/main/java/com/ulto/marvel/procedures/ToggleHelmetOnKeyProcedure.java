package com.ulto.marvel.procedures;

import com.ulto.marvel.world.item.MarvelModItems;
import com.ulto.marvel.sounds.MarvelModSounds;
import com.ulto.marvel.network.MarvelModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class ToggleHelmetOnKeyProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_46_HELMET.get()) {
			if (entity instanceof LivingEntity _entity) {
				switchHelmet(_entity);
				playSound(world, x, y, z, _entity, MarvelModSounds.get("marvel:item.iron_man_helmet.open"), MarvelModSounds.get("marvel:item.iron_man_helmet.close_46"));
			}
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.ANTMAN_SUIT_HELMET.get()
				&& !MarvelModVariables.getPlayerVariables(entity).isSmall) {
			if (entity instanceof LivingEntity _entity) {
				switchHelmet(_entity);
				playSound(world, x, y, z, _entity, MarvelModSounds.get("marvel:item.antman.helmet_open"), MarvelModSounds.get("marvel:item.antman.helmet_close"));
			}
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.ANTMAN_V2_SUIT_HELMET.get()
				&& !MarvelModVariables.getPlayerVariables(entity).isSmall
				&& !MarvelModVariables.getPlayerVariables(entity).isBig) {
			if (entity instanceof LivingEntity _entity) {
				switchHelmet(_entity);
				playSound(world, x, y, z, _entity, MarvelModSounds.get("marvel:item.antman.helmet_open"), MarvelModSounds.get("marvel:item.antman.helmet_close"));
			}
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
				.is(MarvelModItems.Tags.IRON_MAN_HELMETS)) {
			if (entity instanceof LivingEntity _entity) {
				switchHelmet(_entity);
				playSound(world, x, y, z, _entity, MarvelModSounds.get("marvel:item.iron_man_helmet.open"), MarvelModSounds.get("marvel:item.iron_man_helmet.close"));
			}
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
				.is(MarvelModItems.Tags.NANOTECH_HELMETS)) {
			if (entity instanceof LivingEntity _entity) {
				switchHelmet(_entity);
				playSound(world, x, y, z, _entity, MarvelModSounds.get("marvel:item.iron_man_helmet.nanotech_open"), MarvelModSounds.get("marvel:item.iron_man_helmet.nanotech_close"));
			}
		}
	}

	public static void playSound(LevelAccessor world, double x, double y, double z, LivingEntity entity, SoundEvent openingSound, SoundEvent closingSound) {
		if (world instanceof Level _level) {
			_level.playSound(null, new BlockPos((int) x, (int) y, (int) z), entity.getItemBySlot(EquipmentSlot.HEAD).getOrCreateTag().getBoolean("Open") ? openingSound : closingSound, SoundSource.PLAYERS, 1, 1);
		}
	}

	public static void switchHelmet(LivingEntity entity) {
		if (entity instanceof Player _player)
			_player.getInventory().armor.get(3).getOrCreateTag().putBoolean("Open", !_player.getInventory().armor.get(3).getOrCreateTag().getBoolean("Open"));
		else
			entity.getItemBySlot(EquipmentSlot.HEAD).getOrCreateTag().putBoolean("Open", !entity.getItemBySlot(EquipmentSlot.HEAD).getOrCreateTag().getBoolean("Open"));
		if (entity instanceof ServerPlayer _serverPlayer)
			_serverPlayer.getInventory().setChanged();
	}
}
