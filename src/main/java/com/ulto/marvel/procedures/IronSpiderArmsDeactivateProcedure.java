package com.ulto.marvel.procedures;

import com.ulto.marvel.sounds.MarvelModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class IronSpiderArmsDeactivateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity) {
			_entity.getItemBySlot(EquipmentSlot.CHEST).getOrCreateTag().putBoolean("Extended", false);
			if (_entity instanceof ServerPlayer _serverPlayer)
				_serverPlayer.getInventory().setChanged();
		}
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						MarvelModSounds.get(new ResourceLocation("marvel:item.iron_spider_arms.deactivate")), SoundSource.NEUTRAL,
						1, 1);
			} else {
				_level.playLocalSound(x, y, z, MarvelModSounds.get(new ResourceLocation("marvel:item.iron_spider_arms.deactivate")),
						SoundSource.NEUTRAL, 1, 1, false);
			}
		}
	}
}
