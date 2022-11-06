package com.ulto.marvel.procedures;

import com.ulto.marvel.sounds.MarvelModSounds;
import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import com.ulto.marvel.world.item.MarvelModItems;

public class MjolnirBlockRightClickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof Level _level) {
			_level.playSound(null, new BlockPos((int) x, (int) y, (int) z), MarvelModSounds.get(new ResourceLocation("marvel:item.mjolnir.catch")), SoundSource.PLAYERS, 1, 1);
		}
		if (entity instanceof Player _player) {
			ItemStack _setstack = new ItemStack(MarvelModItems.MJOLNIR.get());
			_setstack.setCount(1);
			ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
		}
		world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
	}
}
