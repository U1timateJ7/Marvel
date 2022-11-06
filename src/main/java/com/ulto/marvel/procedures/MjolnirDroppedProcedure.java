package com.ulto.marvel.procedures;

import com.ulto.marvel.world.level.block.MarvelModBlocks;
import com.ulto.marvel.world.item.MarvelModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class MjolnirDroppedProcedure {
	@SubscribeEvent
	public static void onEntityTick(LivingEvent.LivingUpdateEvent event) {
		Entity entity = event.getEntityLiving();
		execute(event, entity.level, entity.getX(), entity.getY(), entity.getZ(), entity);
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof ItemEntity _itemEntity) {
			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.AIR
					|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.VOID_AIR
					|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.CAVE_AIR
					|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.GRASS
					|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.TALL_GRASS) {
				if (_itemEntity.getItem().getItem() == MarvelModItems.MJOLNIR.get()) {
					world.setBlock(new BlockPos((int) x, (int) y, (int) z), MarvelModBlocks.MJOLNIR_BLOCK.get().defaultBlockState(), 3);
					if (!entity.level.isClientSide())
						entity.discard();
				}
			}
		}
	}
}
