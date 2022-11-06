package com.ulto.marvel.procedures;

import com.ulto.marvel.world.item.MarvelModItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

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
					.getItem() == MarvelModItems.IRON_MAN_MARK_1_CHESTPLATE.get()
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_2_CHESTPLATE.get()
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_3_CHESTPLATE.get()
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_5_CHESTPLATE.get()
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_6_CHESTPLATE.get()
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_7_CHESTPLATE.get()
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_16_CHESTPLATE.get()
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_17_CHESTPLATE.get()
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_19_CHESTPLATE.get()
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_20_CHESTPLATE.get()
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_21_CHESTPLATE.get()
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.WAR_MACHINE_CHESTPLATE.get()
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_22_CHESTPLATE.get()
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_23_CHESTPLATE.get()
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_PATRIOT_CHESTPLATE.get()
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_25_CHESTPLATE.get()
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_30_CHESTPLATE.get()
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_33_CHESTPLATE.get()
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_37_CHESTPLATE.get()
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_39_CHESTPLATE.get()
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_42_CHESTPLATE.get()
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_43_CHESTPLATE.get()
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_46_CHESTPLATE.get()
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_47_CHESTPLATE.get()
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.WAR_MACHINE_MARK_2_CHESTPLATE.get()
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_49_CHESTPLATE.get()
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_50_CHESTPLATE.get()
					|| (imediatesourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.IRON_MAN_MARK_85_CHESTPLATE.get()) {
				entity.hurt(DamageSource.GENERIC, 6);
			}
		}
	}
}
