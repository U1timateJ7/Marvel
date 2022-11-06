package com.ulto.marvel.procedures;

import com.ulto.marvel.world.item.IronManSuitItem;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class IronManLightningProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			if (event.getEntity() instanceof Player player && event.getSource() == DamageSource.LIGHTNING_BOLT) {
				event.setCanceled(true);
				player.hurt(event.getSource(), 3);
				if (player.getInventory().armor.get(3).getItem() instanceof IronManSuitItem) IronManSuitItem.addBattery(player.getInventory().armor.get(3), 45f);
				if (player.getInventory().armor.get(2).getItem() instanceof IronManSuitItem) IronManSuitItem.addBattery(player.getInventory().armor.get(2), 45f);
				if (player.getInventory().armor.get(1).getItem() instanceof IronManSuitItem) IronManSuitItem.addBattery(player.getInventory().armor.get(1), 45f);
				if (player.getInventory().armor.get(0).getItem() instanceof IronManSuitItem) IronManSuitItem.addBattery(player.getInventory().armor.get(0), 45f);
			}
		}
	}
}
