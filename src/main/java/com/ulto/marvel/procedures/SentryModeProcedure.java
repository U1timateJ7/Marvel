package com.ulto.marvel.procedures;

import com.ulto.marvel.sounds.MarvelModSounds;
import com.ulto.marvel.world.entity.MarvelModEntityTypes;
import com.ulto.marvel.world.entity.SentryMode;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.jetbrains.annotations.Nullable;

public class SentryModeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, String name) {
		execute(world, x, y, z, entity, name, null);
	}
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, String name, @Nullable Item handItem) {
		execute(world, x, y, z, entity, name, handItem, handItem);
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, String name, @Nullable Item mainHand, @Nullable Item offHand) {
		if (entity == null)
			return;
		if (world instanceof ServerLevel serverLevel && entity instanceof Player player) {
			SentryMode sentry = MarvelModEntityTypes.SENTRY_MODE.get().spawn(serverLevel, null, null, null, entity.blockPosition(), MobSpawnType.TRIGGERED, false, true);
			if (sentry != null) {
				sentry.setPos(entity.position());

				sentry.setSuit(name);

				sentry.setItemSlot(EquipmentSlot.HEAD, player.getInventory().armor.get(3).copy());
				sentry.setItemSlot(EquipmentSlot.CHEST, player.getInventory().armor.get(2).copy());
				sentry.setItemSlot(EquipmentSlot.LEGS, player.getInventory().armor.get(1).copy());
				sentry.setItemSlot(EquipmentSlot.FEET, player.getInventory().armor.get(0).copy());

				sentry.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(mainHand));
				sentry.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(offHand));
			}
		}
		if (entity instanceof Player player) {
			player.getInventory().armor.set(3, ItemStack.EMPTY);
			player.getInventory().armor.set(2, ItemStack.EMPTY);
			player.getInventory().armor.set(1, ItemStack.EMPTY);
			player.getInventory().armor.set(0, ItemStack.EMPTY);
			player.getInventory().setChanged();
		}
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						MarvelModSounds.get(new ResourceLocation("marvel:item.iron_man_helmet.open")), SoundSource.NEUTRAL, 1, 1);
			} else {
				_level.playLocalSound(x, y, z, MarvelModSounds.get(new ResourceLocation("marvel:item.iron_man_helmet.open")),
						SoundSource.NEUTRAL, 1, 1, false);
			}
		}
	}
}
