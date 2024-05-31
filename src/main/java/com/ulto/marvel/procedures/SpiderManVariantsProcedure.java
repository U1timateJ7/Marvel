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
public class SpiderManVariantsProcedure {
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
		if (itemstack.getItem() == MarvelModItems.SPIDER_MAN_SUIT.get()) {
			if (entity instanceof Player _player) {
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.SPIDER_MAN_SUIT_HELMET.get()));
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.SPIDER_MAN_SUIT_CHESTPLATE.get()));
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.SPIDER_MAN_SUIT_LEGGINGS.get()));
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.SPIDER_MAN_SUIT_BOOTS.get()));
				itemstack.shrink(itemstack.getCount());
			}
		} else if (itemstack.getItem() == MarvelModItems.SPIDER_MAN_ADVANCED_SUIT_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.SPIDER_MAN_ADVANCED_SUIT_HELMET.get()));
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.SPIDER_MAN_ADVANCED_SUIT_LEGGINGS.get()));
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.SPIDER_MAN_ADVANCED_SUIT_BOOTS.get()));
			}
		} else if (itemstack.getItem() == MarvelModItems.SPIDER_MAN_INTEGRATED_SUIT_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.SPIDER_MAN_INTEGRATED_SUIT_HELMET.get()));
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.SPIDER_MAN_INTEGRATED_SUIT_LEGGINGS.get()));
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.SPIDER_MAN_INTEGRATED_SUIT_BOOTS.get()));
			}
		} else if (itemstack.getItem() == MarvelModItems.SPIDER_MAN_ANDREW_SUIT_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.SPIDER_MAN_ANDREW_SUIT_HELMET.get()));
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.SPIDER_MAN_ANDREW_SUIT_LEGGINGS.get()));
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.SPIDER_MAN_ANDREW_SUIT_BOOTS.get()));
			}
		} else if (itemstack.getItem() == MarvelModItems.SPIDER_MAN_MILES_SUIT_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.SPIDER_MAN_MILES_SUIT_HELMET.get()));
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.SPIDER_MAN_MILES_SUIT_LEGGINGS.get()));
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.SPIDER_MAN_MILES_SUIT_BOOTS.get()));
			}
		} else if (itemstack.getItem() == MarvelModItems.SPIDER_MAN_TOBEY_SUIT_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.SPIDER_MAN_TOBEY_SUIT_HELMET.get()));
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.SPIDER_MAN_TOBEY_SUIT_LEGGINGS.get()));
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(MarvelModItems.SPIDER_MAN_TOBEY_SUIT_BOOTS.get()));
			}
		}
	}
}
