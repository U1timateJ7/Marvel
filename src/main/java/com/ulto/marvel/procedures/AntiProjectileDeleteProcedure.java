package com.ulto.marvel.procedures;

import com.ulto.marvel.world.entity.*;
import com.ulto.marvel.world.item.MarvelModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.vehicle.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class AntiProjectileDeleteProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			Entity entity = event.getEntity();
			execute(event, entity, event.getSource().getDirectEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(Entity entity, Entity imediatesourceentity, Entity sourceentity) {
		execute(null, entity, imediatesourceentity, sourceentity);
	}

	private static void execute(@Nullable Event event, Entity entity, Entity imediatesourceentity, Entity sourceentity) {
		if (entity == null || imediatesourceentity == null || sourceentity == null)
			return;
		if (entity instanceof EnderDragon || entity instanceof EndCrystal || entity instanceof Minecart || entity instanceof Boat || entity instanceof ItemFrame || entity instanceof Painting || entity instanceof MinecartCommandBlock || entity instanceof AbstractMinecartContainer || entity instanceof MinecartFurnace || entity instanceof MinecartSpawner || entity instanceof MinecartTNT || entity instanceof LargeFireball) {
			if (imediatesourceentity instanceof MjolnirEntity) {
				if (sourceentity instanceof Player _player) {
					ItemStack _setstack = new ItemStack(MarvelModItems.MJOLNIR.get());
					_setstack.setCount(1);
					ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
				}
			} else if (imediatesourceentity instanceof StormbreakerEntity) {
				if (sourceentity instanceof Player _player) {
					ItemStack _setstack = new ItemStack(MarvelModItems.STORMBREAKER.get());
					_setstack.setCount(1);
					ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
				}
			}
		}
	}
}
