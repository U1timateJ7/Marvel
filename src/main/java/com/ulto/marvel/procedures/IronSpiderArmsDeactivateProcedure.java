package com.ulto.marvel.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import com.ulto.marvel.init.MarvelModItems;

public class IronSpiderArmsDeactivateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity) {
			if (_entity instanceof Player _player)
				_player.getInventory().armor.set(2, new ItemStack(MarvelModItems.IRON_SPIDER_SUIT_CHESTPLATE));
			else
				_entity.setItemSlot(EquipmentSlot.CHEST, new ItemStack(MarvelModItems.IRON_SPIDER_SUIT_CHESTPLATE));
			if (_entity instanceof ServerPlayer _serverPlayer)
				_serverPlayer.getInventory().setChanged();
		}
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("marvel:item.iron_spider_arms.deactivate")), SoundSource.NEUTRAL,
						1, 1);
			} else {
				_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("marvel:item.iron_spider_arms.deactivate")),
						SoundSource.NEUTRAL, 1, 1, false);
			}
		}
	}
}
