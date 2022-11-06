package com.ulto.marvel.procedures;

import com.ulto.marvel.world.item.MarvelModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class MjolnirLightingProofProcedure {
	@SubscribeEvent
	public static void onEntityStruckByLightning(EntityStruckByLightningEvent event) {
		Entity entity = event.getEntity();
		execute(event, entity);
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(MarvelModItems.MJOLNIR.get())) : false)
				|| (entity instanceof Player _playerHasItem
						? _playerHasItem.getInventory().contains(new ItemStack(MarvelModItems.STORMBREAKER.get()))
						: false)) {
			if (event != null && event.isCancelable()) {
				event.setCanceled(true);
			}
		}
	}
}
