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
public class IronManCraftingProcedure {
	@SubscribeEvent
	public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
		Entity entity = event.getPlayer();
		execute(event, event.getPlayer(), event.getCrafting());
	}

	public static void execute(Entity entity, ItemStack itemstack) {
		execute(null, entity, itemstack);
	}

	private static void execute(@Nullable Event event, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_2_HELMET.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_1_HELMET.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_2_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_1_CHESTPLATE.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_2_LEGGINGS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_1_LEGGINGS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_2_BOOTS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_1_BOOTS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_3_HELMET.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_2_HELMET.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_3_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_2_CHESTPLATE.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_3_LEGGINGS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_2_LEGGINGS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_3_BOOTS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_2_BOOTS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_5_HELMET.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_3_HELMET.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_5_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_3_CHESTPLATE.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_5_LEGGINGS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_3_LEGGINGS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_5_BOOTS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_3_BOOTS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_6_HELMET.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_5_HELMET.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_6_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_5_CHESTPLATE.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_6_LEGGINGS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_5_LEGGINGS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_6_BOOTS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_5_BOOTS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_7_HELMET.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_6_HELMET.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_7_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_6_CHESTPLATE.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_7_LEGGINGS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_6_LEGGINGS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_7_BOOTS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_6_BOOTS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_43_HELMET.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_42_HELMET.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_43_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_42_CHESTPLATE.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_43_LEGGINGS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_42_LEGGINGS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_43_BOOTS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_42_BOOTS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_46_HELMET.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_43_HELMET.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_46_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_43_CHESTPLATE.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_46_LEGGINGS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_43_LEGGINGS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_46_BOOTS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_43_BOOTS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_47_HELMET.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_46_HELMET.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_47_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_46_CHESTPLATE.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_47_LEGGINGS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_46_LEGGINGS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_47_BOOTS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_46_BOOTS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_49_HELMET.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_47_HELMET.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_49_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_47_CHESTPLATE.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_49_LEGGINGS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_47_LEGGINGS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_49_BOOTS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_47_BOOTS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_50_HELMET.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_49_HELMET.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_50_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_49_CHESTPLATE.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_50_LEGGINGS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_49_LEGGINGS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_50_BOOTS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_49_BOOTS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_85_HELMET.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_50_HELMET.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_85_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_50_CHESTPLATE.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_85_LEGGINGS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_50_LEGGINGS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_85_BOOTS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_50_BOOTS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
	}
}
