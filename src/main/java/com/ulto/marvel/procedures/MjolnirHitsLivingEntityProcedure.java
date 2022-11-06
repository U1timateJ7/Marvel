package com.ulto.marvel.procedures;

import com.ulto.marvel.sounds.MarvelModSounds;
import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import com.ulto.marvel.world.item.MarvelModItems;

public class MjolnirHitsLivingEntityProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity sourceentity) {
		if (sourceentity == null)
			return;
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						MarvelModSounds.get(new ResourceLocation("marvel:item.mjolnir.hit")), SoundSource.NEUTRAL, 1, 1);
			} else {
				_level.playLocalSound(x, y, z, MarvelModSounds.get(new ResourceLocation("marvel:item.mjolnir.hit")),
						SoundSource.NEUTRAL, 1, 1, false);
			}
		}
		if (world instanceof ServerLevel _level) {
			LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
			entityToSpawn.moveTo(Vec3.atBottomCenterOf(new BlockPos((int) x, (int) y, (int) z)));
			entityToSpawn.setVisualOnly(false);
			_level.addFreshEntity(entityToSpawn);
		}
		if (sourceentity instanceof Player _player) {
			ItemStack _setstack = new ItemStack(MarvelModItems.MJOLNIR.get());
			_setstack.setCount(1);
			ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
		}
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, new BlockPos((int) (sourceentity.getX()), (int) (sourceentity.getY()), (int) (sourceentity.getZ())),
						MarvelModSounds.get(new ResourceLocation("marvel:item.mjolnir.catch")), SoundSource.NEUTRAL, 1, 1);
			} else {
				_level.playLocalSound((sourceentity.getX()), (sourceentity.getY()), (sourceentity.getZ()),
						MarvelModSounds.get(new ResourceLocation("marvel:item.mjolnir.catch")), SoundSource.NEUTRAL, 1, 1, false);
			}
		}
	}
}
