package com.ulto.marvel.procedures;

import com.ulto.marvel.sounds.MarvelModSounds;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import com.ulto.marvel.world.item.MarvelModItems;

public class ClawsInProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == MarvelModItems.ADAMANTIUM_CLAWS.get()
				&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
						.getItem() == MarvelModItems.ADAMANTIUM_CLAWS.get()) {
			if (entity instanceof LivingEntity _entity) {
				ItemStack _setstack = new ItemStack(Blocks.AIR);
				_setstack.setCount(1);
				_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
				if (_entity instanceof ServerPlayer _serverPlayer)
					_serverPlayer.getInventory().setChanged();
			}
			if (entity instanceof LivingEntity _entity) {
				ItemStack _setstack = new ItemStack(Blocks.AIR);
				_setstack.setCount(1);
				_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
				if (_entity instanceof ServerPlayer _serverPlayer)
					_serverPlayer.getInventory().setChanged();
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							MarvelModSounds.get(new ResourceLocation("marvel:item.adamantium_claws.retract")), SoundSource.NEUTRAL,
							1, 1);
				} else {
					_level.playLocalSound(x, y, z,
							MarvelModSounds.get(new ResourceLocation("marvel:item.adamantium_claws.retract")), SoundSource.NEUTRAL,
							1, 1, false);
				}
			}
		}
	}
}
