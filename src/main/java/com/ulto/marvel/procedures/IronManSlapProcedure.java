package com.ulto.marvel.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;

import javax.annotation.Nullable;

import com.ulto.marvel.init.MarvelModItems;

@Mod.EventBusSubscriber
public class IronManSlapProcedure {
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
		if ((imediatesourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem()) {
			if ((imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
					.getItem() == MarvelModItems.IRON_MAN_MARK_1_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_2_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_3_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_5_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_6_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_7_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_16_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.MARK_16_STEALTH_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_17_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_19_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_20_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_21_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.WAR_MACHINE_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_22_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_23_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_PATRIOT_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_25_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_30_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_33_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_37_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_39_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_42_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_43_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_46_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_47_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.WAR_MACHINE_MARK_2_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_49_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_50_CHESTPLATE
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_85_CHESTPLATE) {
				entity.hurt(DamageSource.GENERIC, 6);
			}
		}
	}
}
