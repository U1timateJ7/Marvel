package com.ulto.marvel.procedures;

import com.ulto.marvel.world.item.MarvelModItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class VibraniumShieldBlockProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			if (event.getEntity() instanceof LivingEntity living && event.getSource().isProjectile()) {
				if (living.getItemInHand(InteractionHand.MAIN_HAND).is(MarvelModItems.Tags.VIBRANIUM_SHIELDS) || living.getItemInHand(InteractionHand.OFF_HAND).is(MarvelModItems.Tags.VIBRANIUM_SHIELDS)) event.setCanceled(true);
			}
		}
	}
}
