package com.ulto.marvel.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;

import javax.annotation.Nullable;

import com.ulto.marvel.world.item.MarvelModItems;

@Mod.EventBusSubscriber
public class BlackPantherAttackProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			Entity entity = event.getEntity();
			execute(event, entity, event.getSource().getDirectEntity());
		}
	}

	public static void execute(Entity entity, Entity imediatesourceentity) {
		execute(null, entity, imediatesourceentity);
	}

	private static void execute(@Nullable Event event, Entity entity, Entity imediatesourceentity) {
		if (entity == null || imediatesourceentity == null)
			return;
		if ((imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.BLACK_PANTHER_SUIT_CHESTPLATE.get()) {
			entity.hurt(DamageSource.mobAttack((LivingEntity) imediatesourceentity), 6);
		}
	}
}
