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
public class IronMan3CraftingProcedure {
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
		if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_16_HELMET.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_7_HELMET.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_16_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_7_CHESTPLATE.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_16_LEGGINGS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_7_LEGGINGS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_16_BOOTS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_7_BOOTS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_17_HELMET.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_16_HELMET.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_17_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_16_CHESTPLATE.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_17_LEGGINGS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_16_LEGGINGS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_17_BOOTS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_16_BOOTS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_19_HELMET.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_17_HELMET.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_19_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_17_CHESTPLATE.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_19_LEGGINGS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_17_LEGGINGS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_19_BOOTS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_17_BOOTS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_20_HELMET.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_19_HELMET.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_20_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_19_CHESTPLATE.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_20_LEGGINGS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_19_LEGGINGS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_20_BOOTS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_19_BOOTS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_21_HELMET.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_20_HELMET.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_21_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_20_CHESTPLATE.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_21_LEGGINGS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_20_LEGGINGS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_21_BOOTS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_20_BOOTS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_22_HELMET.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_21_HELMET.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_22_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_21_CHESTPLATE.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_22_LEGGINGS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_21_LEGGINGS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_22_BOOTS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_21_BOOTS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_23_HELMET.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_22_HELMET.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_23_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_22_CHESTPLATE.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_23_LEGGINGS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_22_LEGGINGS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_23_BOOTS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_22_BOOTS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_25_HELMET.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_23_HELMET.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_25_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_23_CHESTPLATE.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_25_LEGGINGS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_23_LEGGINGS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_25_BOOTS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_23_BOOTS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_30_HELMET.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_25_HELMET.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_30_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_25_CHESTPLATE.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_30_LEGGINGS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_25_LEGGINGS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_30_BOOTS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_25_BOOTS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_33_HELMET.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_30_HELMET.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_33_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_30_CHESTPLATE.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_33_LEGGINGS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_30_LEGGINGS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_33_BOOTS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_30_BOOTS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_37_HELMET.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_33_HELMET.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_37_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_33_CHESTPLATE.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_37_LEGGINGS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_33_LEGGINGS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_37_BOOTS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_33_BOOTS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_39_HELMET.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_37_HELMET.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_39_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_37_CHESTPLATE.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_39_LEGGINGS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_37_LEGGINGS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_39_BOOTS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_37_BOOTS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_42_HELMET.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_39_HELMET.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_42_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_39_CHESTPLATE.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_42_LEGGINGS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_39_LEGGINGS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.IRON_MAN_MARK_42_BOOTS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.IRON_MAN_MARK_39_BOOTS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (itemstack.getItem() == MarvelModItems.ANTMAN_V2_SUIT_HELMET.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.ANTMAN_SUIT_HELMET.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.ANTMAN_V2_SUIT_CHESTPLATE.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.ANTMAN_SUIT_CHESTPLATE.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.ANTMAN_V2_SUIT_LEGGINGS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.ANTMAN_SUIT_LEGGINGS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else if (itemstack.getItem() == MarvelModItems.ANTMAN_V2_SUIT_BOOTS.get()) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(MarvelModItems.ANTMAN_SUIT_BOOTS.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
	}
}
