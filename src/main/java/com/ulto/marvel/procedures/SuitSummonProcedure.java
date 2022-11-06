package com.ulto.marvel.procedures;

import com.ulto.marvel.common.MarvelMod;
import com.ulto.marvel.world.entity.SentryModeEntity;
import com.ulto.marvel.world.entity.MarvelModEntityTypes;
import com.ulto.marvel.network.MarvelModVariables;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.registries.ForgeRegistries;
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
			SentryModeEntity sentry = MarvelModEntityTypes.SENTRY_MODE.get().spawn(serverLevel, null, new TranslatableComponent("suit.marvel." + name), null, entity.blockPosition(), MobSpawnType.TRIGGERED, false, true);
			if (sentry != null) {
				sentry.moveTo(x2, 255, z2, world.getRandom().nextFloat() * 360F, 0);

				sentry.setItemSlot(EquipmentSlot.HEAD, ForgeRegistries.ITEMS.getValue(new ResourceLocation(MarvelMod.MOD_ID, name + "_helmet")).getDefaultInstance());
				sentry.setItemSlot(EquipmentSlot.CHEST, ForgeRegistries.ITEMS.getValue(new ResourceLocation(MarvelMod.MOD_ID, name + "_chestplate")).getDefaultInstance());
				sentry.setItemSlot(EquipmentSlot.LEGS, ForgeRegistries.ITEMS.getValue(new ResourceLocation(MarvelMod.MOD_ID, name + "_leggings")).getDefaultInstance());
				sentry.setItemSlot(EquipmentSlot.FEET, ForgeRegistries.ITEMS.getValue(new ResourceLocation(MarvelMod.MOD_ID, name + "_boots")).getDefaultInstance());

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
