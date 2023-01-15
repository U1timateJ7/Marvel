package com.ulto.marvel.procedures;

import com.ulto.marvel.network.MarvelModVariables;
import com.ulto.marvel.world.entity.MarvelModEntityTypes;
import com.ulto.marvel.world.entity.SentryMode;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class SuitSummonProcedure {
	public static void execute(LevelAccessor world, double x, double z, Entity entity, String name) {
		execute(world, x, z, entity, name, null);
	}
	public static void execute(LevelAccessor world, double x, double z, Entity entity, String name, @Nullable Item handItem) {
		execute(world, x, z, entity, name, handItem, handItem);
	}

	public static void execute(LevelAccessor world, double x, double z, Entity entity, String name, @Nullable Item mainHand, @Nullable Item offHand) {
		if (entity == null)
			return;
		double x2 = x + new Random().nextInt(9 + 1) - 4;
		double z2 = z + new Random().nextInt(9 + 1) - 4;
		if (world instanceof ServerLevel serverLevel) {
			SentryMode sentry = MarvelModEntityTypes.SENTRY_MODE.get().spawn(serverLevel, null, null, null, entity.blockPosition(), MobSpawnType.TRIGGERED, false, true);
			if (sentry != null) {
				sentry.moveTo(x2, 255, z2, world.getRandom().nextFloat() * 360F, 0);

				sentry.setSuit(name);

				sentry.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(mainHand));
				sentry.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(offHand));
			}
		}
		{
			entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.setVeronicaVariable(name, false);
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
