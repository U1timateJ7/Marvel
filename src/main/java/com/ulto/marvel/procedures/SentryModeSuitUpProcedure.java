package com.ulto.marvel.procedures;

import com.ulto.marvel.world.entity.SentryModeEntity;
import com.ulto.marvel.world.item.MarvelModItems;
import com.ulto.marvel.sounds.MarvelModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.items.ItemHandlerHelper;

public class SentryModeSuitUpProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
				.getItem() == MarvelModItems.VERONICA_REMOTE.get()) {
			VeronicaSendProcedure.execute(world, x, y, z, entity, sourceentity);
		} else {
			if (sourceentity instanceof Player player && entity instanceof SentryModeEntity sentry) {
				ItemHandlerHelper.giveItemToPlayer(player, player.getInventory().armor.get(3).copy());
				ItemHandlerHelper.giveItemToPlayer(player, player.getInventory().armor.get(2).copy());
				ItemHandlerHelper.giveItemToPlayer(player, player.getInventory().armor.get(1).copy());
				ItemHandlerHelper.giveItemToPlayer(player, player.getInventory().armor.get(0).copy());

				player.getInventory().armor.set(3, sentry.getItemBySlot(EquipmentSlot.HEAD));
				player.getInventory().armor.set(2, sentry.getItemBySlot(EquipmentSlot.CHEST));
				player.getInventory().armor.set(1, sentry.getItemBySlot(EquipmentSlot.LEGS));
				player.getInventory().armor.set(0, sentry.getItemBySlot(EquipmentSlot.FEET));
			}
			if (!entity.level.isClientSide())
				entity.discard();
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							MarvelModSounds.get(new ResourceLocation("marvel:item.iron_man_helmet.close")), SoundSource.NEUTRAL, 1,
							1);
				} else {
					_level.playLocalSound(x, y, z, MarvelModSounds.get(new ResourceLocation("marvel:item.iron_man_helmet.close")),
							SoundSource.NEUTRAL, 1, 1, false);
				}
			}
		}
	}
}
