package com.ulto.marvel.procedures;

import com.ulto.marvel.world.level.block.MarvelModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class TemporaryWebUpdateTickProcedure {
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
		if (!(entity instanceof Player) && !(entity instanceof Spider)) {
			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == MarvelModBlocks.TEMPORARY_WEB.get()) {
				entity.makeStuckInBlock(Blocks.AIR.defaultBlockState(), new Vec3(0.25, 0.05, 0.25));
			}
		}
	}
}
