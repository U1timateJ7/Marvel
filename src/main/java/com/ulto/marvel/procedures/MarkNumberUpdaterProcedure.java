package com.ulto.marvel.procedures;

import com.ulto.marvel.world.item.MarvelModItems;
import com.ulto.marvel.network.MarvelModVariables;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class MarkNumberUpdaterProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Entity entity = event.player;
			execute(event, entity);
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_1_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_1_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_1_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_1_BOOTS.get()) {
			{
				double _setval = 1;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_2_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_2_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_2_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_2_BOOTS.get()) {
			{
				double _setval = 2;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_3_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_3_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_3_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_3_BOOTS.get()) {
			{
				double _setval = 3;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_5_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_5_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_5_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_5_BOOTS.get()) {
			{
				double _setval = 5;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_6_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_6_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_6_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_6_BOOTS.get()) {
			{
				double _setval = 6;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_21_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_21_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_21_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_21_BOOTS.get()) {
			{
				double _setval = 21;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_22_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_22_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_22_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_22_BOOTS.get()) {
			{
				double _setval = 22;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.WAR_MACHINE_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.WAR_MACHINE_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.WAR_MACHINE_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.WAR_MACHINE_BOOTS.get()) {
			{
				double _setval = -1;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_PATRIOT_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_PATRIOT_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_PATRIOT_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_PATRIOT_BOOTS.get()) {
			{
				double _setval = -2;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_25_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_25_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_25_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_25_BOOTS.get()) {
			{
				double _setval = 25;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_30_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_30_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_30_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_30_BOOTS.get()) {
			{
				double _setval = 30;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_33_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_33_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_33_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_33_BOOTS.get()) {
			{
				double _setval = 33;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_42_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_42_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_42_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_42_BOOTS.get()) {
			{
				double _setval = 42;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_43_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_43_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_43_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_43_BOOTS.get()) {
			{
				double _setval = 43;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_46_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_46_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_46_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_46_BOOTS.get()) {
			{
				double _setval = 46;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_47_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_47_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_47_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_47_BOOTS.get()) {
			{
				double _setval = 47;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.WAR_MACHINE_MARK_2_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.WAR_MACHINE_MARK_2_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.WAR_MACHINE_MARK_2_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.WAR_MACHINE_MARK_2_BOOTS.get()) {
			{
				double _setval = -3;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_49_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_49_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_49_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_49_BOOTS.get()) {
			{
				double _setval = 49;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_50_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_50_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_50_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_50_BOOTS.get()) {
			{
				double _setval = 50;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.MARK_50_ARC_REACTOR.get()) {
			{
				double _setval = -50;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_85_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_85_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_85_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_85_BOOTS.get()) {
			{
				double _setval = 85;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.MARK_85_ARC_REACTOR.get()) {
			{
				double _setval = -85;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_SPIDER_SUIT_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_SPIDER_SUIT_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_SPIDER_SUIT_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_SPIDER_SUIT_BOOTS.get()) {
			{
				double _setval = 100;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_SPIDER_STORAGE.get()) {
			{
				double _setval = -100;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_7_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_7_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_7_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_7_BOOTS.get()) {
			{
				double _setval = 7;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_16_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_16_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_16_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_16_BOOTS.get()) {
			{
				LivingEntity _entGetArmor = (LivingEntity) entity;
				double _setval = _entGetArmor.getItemBySlot(EquipmentSlot.CHEST).getOrCreateTag().getBoolean("Invisible") ? -16 : 16;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_17_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_17_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_17_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_17_BOOTS.get()) {
			{
				double _setval = 17;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_19_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_19_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_19_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_19_BOOTS.get()) {
			{
				double _setval = 19;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_20_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_20_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_20_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_20_BOOTS.get()) {
			{
				double _setval = 20;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_37_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_37_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_37_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_37_BOOTS.get()) {
			{
				double _setval = 37;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_39_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_39_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_39_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.IRON_MAN_MARK_39_BOOTS.get()) {
			{
				double _setval = 39;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else {
			{
				double _setval = 0;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironManMkNum = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
