package com.ulto.marvel.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.HashMap;

import com.ulto.marvel.item.WarMachineMark2Item;
import com.ulto.marvel.item.WarMachineItem;
import com.ulto.marvel.item.WarMachineGunItem;
import com.ulto.marvel.item.UnibeamItem;
import com.ulto.marvel.item.RepulsorItem;
import com.ulto.marvel.item.Mark30StealthItem;
import com.ulto.marvel.item.Mark25DrillItem;
import com.ulto.marvel.item.IronPatriotItem;
import com.ulto.marvel.item.IronManMark6Item;
import com.ulto.marvel.item.IronManMark5Item;
import com.ulto.marvel.item.IronManMark47Item;
import com.ulto.marvel.item.IronManMark46Item;
import com.ulto.marvel.item.IronManMark43Item;
import com.ulto.marvel.item.IronManMark42Item;
import com.ulto.marvel.item.IronManMark3Item;
import com.ulto.marvel.item.IronManMark33Item;
import com.ulto.marvel.item.IronManMark30Item;
import com.ulto.marvel.item.IronManMark25Item;
import com.ulto.marvel.item.IronManMark23Item;
import com.ulto.marvel.item.IronManMark22Item;
import com.ulto.marvel.item.IronManMark21Item;
import com.ulto.marvel.item.IronManMark1Item;
import com.ulto.marvel.item.IronManFlamethrowerItem;
import com.ulto.marvel.item.CenturionBladeItem;
import com.ulto.marvel.MarvelModElements;
import com.ulto.marvel.MarvelMod;

@MarvelModElements.ModElement.Tag
public class RemoveIronManWeaponsProcedure extends MarvelModElements.ModElement {
	public RemoveIronManWeaponsProcedure(MarvelModElements instance) {
		super(instance, 177);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MarvelMod.LOGGER.warn("Failed to load dependency entity for procedure RemoveIronManWeapons!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((!(((entity instanceof LivingEntity)
				? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
				: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark1Item.body, (int) (1)).getItem()))) {
			if (entity instanceof PlayerEntity) {
				ItemStack _stktoremove = new ItemStack(IronManFlamethrowerItem.block, (int) (1));
				((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
						((PlayerEntity) entity).container.func_234641_j_());
			}
		}
		if (((!(((entity instanceof LivingEntity)
				? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
				: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark3Item.body, (int) (1)).getItem()))
				&& ((!(((entity instanceof LivingEntity)
						? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
						: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark5Item.body, (int) (1)).getItem()))
						&& ((!(((entity instanceof LivingEntity)
								? ((LivingEntity) entity)
										.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
								: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark6Item.body, (int) (1)).getItem()))
								&& ((!(((entity instanceof LivingEntity)
										? ((LivingEntity) entity)
												.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
										: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark21Item.body, (int) (1)).getItem()))
										&& ((!(((entity instanceof LivingEntity)
												? ((LivingEntity) entity).getItemStackFromSlot(
														EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
												: ItemStack.EMPTY).getItem() == new ItemStack(WarMachineItem.body, (int) (1)).getItem()))
												&& ((!(((entity instanceof LivingEntity)
														? ((LivingEntity) entity).getItemStackFromSlot(
																EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
														: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark22Item.body, (int) (1)).getItem()))
														&& ((!(((entity instanceof LivingEntity)
																? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType
																		.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
																: ItemStack.EMPTY)
																		.getItem() == new ItemStack(IronManMark23Item.body, (int) (1)).getItem()))
																&& ((!(((entity instanceof LivingEntity)
																		? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType
																				.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
																		: ItemStack.EMPTY).getItem() == new ItemStack(IronPatriotItem.body, (int) (1))
																				.getItem()))
																		&& ((!(((entity instanceof LivingEntity)
																				? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType
																						.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
																				: ItemStack.EMPTY)
																						.getItem() == new ItemStack(IronManMark25Item.body, (int) (1))
																								.getItem()))
																				&& ((!(((entity instanceof LivingEntity)
																						? ((LivingEntity) entity).getItemStackFromSlot(
																								EquipmentSlotType.fromSlotTypeAndIndex(
																										EquipmentSlotType.Group.ARMOR, (int) 2))
																						: ItemStack.EMPTY)
																								.getItem() == new ItemStack(IronManMark30Item.body,
																										(int) (1)).getItem()))
																						&& ((!(((entity instanceof LivingEntity)
																								? ((LivingEntity) entity).getItemStackFromSlot(
																										EquipmentSlotType.fromSlotTypeAndIndex(
																												EquipmentSlotType.Group.ARMOR,
																												(int) 2))
																								: ItemStack.EMPTY)
																										.getItem() == new ItemStack(
																												Mark30StealthItem.body, (int) (1))
																														.getItem()))
																								&& ((!(((entity instanceof LivingEntity)
																										? ((LivingEntity) entity)
																												.getItemStackFromSlot(
																														EquipmentSlotType
																																.fromSlotTypeAndIndex(
																																		EquipmentSlotType.Group.ARMOR,
																																		(int) 2))
																										: ItemStack.EMPTY).getItem() == new ItemStack(
																												IronManMark33Item.body, (int) (1))
																														.getItem()))
																										&& ((!(((entity instanceof LivingEntity)
																												? ((LivingEntity) entity)
																														.getItemStackFromSlot(
																																EquipmentSlotType
																																		.fromSlotTypeAndIndex(
																																				EquipmentSlotType.Group.ARMOR,
																																				(int) 2))
																												: ItemStack.EMPTY)
																														.getItem() == new ItemStack(
																																IronManMark42Item.body,
																																(int) (1)).getItem()))
																												&& ((!(((entity instanceof LivingEntity)
																														? ((LivingEntity) entity)
																																.getItemStackFromSlot(
																																		EquipmentSlotType
																																				.fromSlotTypeAndIndex(
																																						EquipmentSlotType.Group.ARMOR,
																																						(int) 2))
																														: ItemStack.EMPTY)
																																.getItem() == new ItemStack(
																																		IronManMark43Item.body,
																																		(int) (1))
																																				.getItem()))
																														&& ((!(((entity instanceof LivingEntity)
																																? ((LivingEntity) entity)
																																		.getItemStackFromSlot(
																																				EquipmentSlotType
																																						.fromSlotTypeAndIndex(
																																								EquipmentSlotType.Group.ARMOR,
																																								(int) 2))
																																: ItemStack.EMPTY)
																																		.getItem() == new ItemStack(
																																				IronManMark46Item.body,
																																				(int) (1))
																																						.getItem()))
																																&& ((!(((entity instanceof LivingEntity)
																																		? ((LivingEntity) entity)
																																				.getItemStackFromSlot(
																																						EquipmentSlotType
																																								.fromSlotTypeAndIndex(
																																										EquipmentSlotType.Group.ARMOR,
																																										(int) 2))
																																		: ItemStack.EMPTY)
																																				.getItem() == new ItemStack(
																																						IronManMark47Item.body,
																																						(int) (1))
																																								.getItem()))
																																		&& (!(((entity instanceof LivingEntity)
																																				? ((LivingEntity) entity)
																																						.getItemStackFromSlot(
																																								EquipmentSlotType
																																										.fromSlotTypeAndIndex(
																																												EquipmentSlotType.Group.ARMOR,
																																												(int) 2))
																																				: ItemStack.EMPTY)
																																						.getItem() == new ItemStack(
																																								WarMachineMark2Item.body,
																																								(int) (1))
																																										.getItem()))))))))))))))))))) {
			if (entity instanceof PlayerEntity) {
				ItemStack _stktoremove = new ItemStack(RepulsorItem.block, (int) (1));
				((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
						((PlayerEntity) entity).container.func_234641_j_());
			}
			if (entity instanceof PlayerEntity) {
				ItemStack _stktoremove = new ItemStack(UnibeamItem.block, (int) (1));
				((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
						((PlayerEntity) entity).container.func_234641_j_());
			}
		}
		if (((!(((entity instanceof LivingEntity)
				? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
				: ItemStack.EMPTY).getItem() == new ItemStack(WarMachineItem.body, (int) (1)).getItem()))
				&& ((!(((entity instanceof LivingEntity)
						? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
						: ItemStack.EMPTY).getItem() == new ItemStack(IronPatriotItem.body, (int) (1)).getItem()))
						&& (!(((entity instanceof LivingEntity)
								? ((LivingEntity) entity)
										.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
								: ItemStack.EMPTY).getItem() == new ItemStack(WarMachineMark2Item.body, (int) (1)).getItem()))))) {
			if (entity instanceof PlayerEntity) {
				ItemStack _stktoremove = new ItemStack(WarMachineGunItem.block, (int) (1));
				((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
						((PlayerEntity) entity).container.func_234641_j_());
			}
		}
		if ((!(((entity instanceof LivingEntity)
				? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
				: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark25Item.body, (int) (1)).getItem()))) {
			if (entity instanceof PlayerEntity) {
				ItemStack _stktoremove = new ItemStack(Mark25DrillItem.block, (int) (1));
				((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
						((PlayerEntity) entity).container.func_234641_j_());
			}
		}
		if ((!(((entity instanceof LivingEntity)
				? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
				: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark33Item.body, (int) (1)).getItem()))) {
			if (entity instanceof PlayerEntity) {
				ItemStack _stktoremove = new ItemStack(CenturionBladeItem.block, (int) (1));
				((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
						((PlayerEntity) entity).container.func_234641_j_());
			}
		}
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Entity entity = event.player;
			World world = entity.world;
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}
}
