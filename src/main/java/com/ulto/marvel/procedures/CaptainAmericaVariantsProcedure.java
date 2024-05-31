package com.ulto.marvel.procedures;

import com.ulto.marvel.world.item.MarvelModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CaptainAmericaVariantsProcedure {
	@SubscribeEvent
	public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
		execute(event, event.getPlayer(), event.getCrafting());
	}

	public static void execute(Entity entity, ItemStack itemstack) {
		execute(null, entity, itemstack);
	}

	private static void execute(@Nullable Event event, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (itemstack.getItem() == MarvelModItems.CAPTAIN_AMERICA_SUIT.get()) {
			if (entity instanceof Player _player) {
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.CAPTAIN_AMERICA_SUIT_HELMET.get()));
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.CAPTAIN_AMERICA_SUIT_CHESTPLATE.get()));
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.CAPTAIN_AMERICA_SUIT_LEGGINGS.get()));
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.CAPTAIN_AMERICA_SUIT_BOOTS.get()));
				itemstack.shrink(itemstack.getCount());
			}
		} else if (itemstack.getItem() == MarvelModItems.CAPTAIN_AMERICA_STEALTH_SUIT_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.CAPTAIN_AMERICA_STEALTH_SUIT_HELMET.get()));
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.CAPTAIN_AMERICA_STEALTH_SUIT_LEGGINGS.get()));
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.CAPTAIN_AMERICA_STEALTH_SUIT_BOOTS.get()));
			}
		} else if (itemstack.getItem() == MarvelModItems.CAPTAIN_CARTER_SUIT_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.CAPTAIN_CARTER_SUIT_LEGGINGS.get()));
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.CAPTAIN_CARTER_SUIT_BOOTS.get()));
			}
		} else if (itemstack.getItem() == MarvelModItems.JOHN_WALKER_SUIT_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.JOHN_WALKER_SUIT_HELMET.get()));
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.JOHN_WALKER_SUIT_LEGGINGS.get()));
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.JOHN_WALKER_SUIT_BOOTS.get()));
			}
		} else if (itemstack.getItem() == MarvelModItems.US_AGENT_SUIT_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.US_AGENT_SUIT_HELMET.get()));
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.US_AGENT_SUIT_LEGGINGS.get()));
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.US_AGENT_SUIT_BOOTS.get()));
			}
		} else if (itemstack.getItem() == MarvelModItems.RED_GUARDIAN_SUIT_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.RED_GUARDIAN_SUIT_HELMET.get()));
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.RED_GUARDIAN_SUIT_LEGGINGS.get()));
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.RED_GUARDIAN_SUIT_BOOTS.get()));
			}
		}
	}
}
