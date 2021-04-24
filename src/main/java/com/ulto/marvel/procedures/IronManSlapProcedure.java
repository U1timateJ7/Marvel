package com.ulto.marvel.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.util.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import java.util.Map;
import java.util.HashMap;

import com.ulto.marvel.item.WarMachineMark2Item;
import com.ulto.marvel.item.WarMachineItem;
import com.ulto.marvel.item.Mark30StealthItem;
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
import com.ulto.marvel.item.IronManMark2Item;
import com.ulto.marvel.item.IronManMark25Item;
import com.ulto.marvel.item.IronManMark23Item;
import com.ulto.marvel.item.IronManMark22Item;
import com.ulto.marvel.item.IronManMark21Item;
import com.ulto.marvel.item.IronManMark1Item;
import com.ulto.marvel.MarvelModElements;
import com.ulto.marvel.MarvelMod;

@MarvelModElements.ModElement.Tag
public class IronManSlapProcedure extends MarvelModElements.ModElement {
	public IronManSlapProcedure(MarvelModElements instance) {
		super(instance, 200);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MarvelMod.LOGGER.warn("Failed to load dependency entity for procedure IronManSlap!");
			return;
		}
		if (dependencies.get("imediatesourceentity") == null) {
			if (!dependencies.containsKey("imediatesourceentity"))
				MarvelMod.LOGGER.warn("Failed to load dependency imediatesourceentity for procedure IronManSlap!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity imediatesourceentity = (Entity) dependencies.get("imediatesourceentity");
		if ((((imediatesourceentity instanceof LivingEntity) ? ((LivingEntity) imediatesourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(Blocks.AIR, (int) (1)).getItem())) {
			if (((((imediatesourceentity instanceof LivingEntity)
					? ((LivingEntity) imediatesourceentity)
							.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
					: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark1Item.body, (int) (1)).getItem())
					|| ((((imediatesourceentity instanceof LivingEntity)
							? ((LivingEntity) imediatesourceentity)
									.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
							: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark2Item.body, (int) (1)).getItem())
							|| ((((imediatesourceentity instanceof LivingEntity)
									? ((LivingEntity) imediatesourceentity)
											.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
									: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark3Item.body, (int) (1)).getItem())
									|| ((((imediatesourceentity instanceof LivingEntity)
											? ((LivingEntity) imediatesourceentity).getItemStackFromSlot(
													EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
											: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark5Item.body, (int) (1)).getItem())
											|| ((((imediatesourceentity instanceof LivingEntity)
													? ((LivingEntity) imediatesourceentity).getItemStackFromSlot(
															EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
													: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark6Item.body, (int) (1)).getItem())
													|| ((((imediatesourceentity instanceof LivingEntity)
															? ((LivingEntity) imediatesourceentity).getItemStackFromSlot(
																	EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
															: ItemStack.EMPTY)
																	.getItem() == new ItemStack(IronManMark21Item.body, (int) (1)).getItem())
															|| ((((imediatesourceentity instanceof LivingEntity)
																	? ((LivingEntity) imediatesourceentity).getItemStackFromSlot(EquipmentSlotType
																			.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
																	: ItemStack.EMPTY)
																			.getItem() == new ItemStack(WarMachineItem.body, (int) (1)).getItem())
																	|| ((((imediatesourceentity instanceof LivingEntity)
																			? ((LivingEntity) imediatesourceentity)
																					.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(
																							EquipmentSlotType.Group.ARMOR, (int) 2))
																			: ItemStack.EMPTY)
																					.getItem() == new ItemStack(IronManMark22Item.body, (int) (1))
																							.getItem())
																			|| ((((imediatesourceentity instanceof LivingEntity)
																					? ((LivingEntity) imediatesourceentity).getItemStackFromSlot(
																							EquipmentSlotType.fromSlotTypeAndIndex(
																									EquipmentSlotType.Group.ARMOR, (int) 2))
																					: ItemStack.EMPTY)
																							.getItem() == new ItemStack(IronManMark23Item.body,
																									(int) (1)).getItem())
																					|| ((((imediatesourceentity instanceof LivingEntity)
																							? ((LivingEntity) imediatesourceentity)
																									.getItemStackFromSlot(
																											EquipmentSlotType.fromSlotTypeAndIndex(
																													EquipmentSlotType.Group.ARMOR,
																													(int) 2))
																							: ItemStack.EMPTY)
																									.getItem() == new ItemStack(IronPatriotItem.body,
																											(int) (1)).getItem())
																							|| ((((imediatesourceentity instanceof LivingEntity)
																									? ((LivingEntity) imediatesourceentity)
																											.getItemStackFromSlot(EquipmentSlotType
																													.fromSlotTypeAndIndex(
																															EquipmentSlotType.Group.ARMOR,
																															(int) 2))
																									: ItemStack.EMPTY)
																											.getItem() == new ItemStack(
																													IronManMark25Item.body, (int) (1))
																															.getItem())
																									|| ((((imediatesourceentity instanceof LivingEntity)
																											? ((LivingEntity) imediatesourceentity)
																													.getItemStackFromSlot(
																															EquipmentSlotType
																																	.fromSlotTypeAndIndex(
																																			EquipmentSlotType.Group.ARMOR,
																																			(int) 2))
																											: ItemStack.EMPTY)
																													.getItem() == new ItemStack(
																															IronManMark30Item.body,
																															(int) (1)).getItem())
																											|| ((((imediatesourceentity instanceof LivingEntity)
																													? ((LivingEntity) imediatesourceentity)
																															.getItemStackFromSlot(
																																	EquipmentSlotType
																																			.fromSlotTypeAndIndex(
																																					EquipmentSlotType.Group.ARMOR,
																																					(int) 2))
																													: ItemStack.EMPTY)
																															.getItem() == new ItemStack(
																																	Mark30StealthItem.body,
																																	(int) (1))
																																			.getItem())
																													|| ((((imediatesourceentity instanceof LivingEntity)
																															? ((LivingEntity) imediatesourceentity)
																																	.getItemStackFromSlot(
																																			EquipmentSlotType
																																					.fromSlotTypeAndIndex(
																																							EquipmentSlotType.Group.ARMOR,
																																							(int) 2))
																															: ItemStack.EMPTY)
																																	.getItem() == new ItemStack(
																																			IronManMark33Item.body,
																																			(int) (1))
																																					.getItem())
																															|| ((((imediatesourceentity instanceof LivingEntity)
																																	? ((LivingEntity) imediatesourceentity)
																																			.getItemStackFromSlot(
																																					EquipmentSlotType
																																							.fromSlotTypeAndIndex(
																																									EquipmentSlotType.Group.ARMOR,
																																									(int) 2))
																																	: ItemStack.EMPTY)
																																			.getItem() == new ItemStack(
																																					IronManMark42Item.body,
																																					(int) (1))
																																							.getItem())
																																	|| ((((imediatesourceentity instanceof LivingEntity)
																																			? ((LivingEntity) imediatesourceentity)
																																					.getItemStackFromSlot(
																																							EquipmentSlotType
																																									.fromSlotTypeAndIndex(
																																											EquipmentSlotType.Group.ARMOR,
																																											(int) 2))
																																			: ItemStack.EMPTY)
																																					.getItem() == new ItemStack(
																																							IronManMark43Item.body,
																																							(int) (1))
																																									.getItem())
																																			|| ((((imediatesourceentity instanceof LivingEntity)
																																					? ((LivingEntity) imediatesourceentity)
																																							.getItemStackFromSlot(
																																									EquipmentSlotType
																																											.fromSlotTypeAndIndex(
																																													EquipmentSlotType.Group.ARMOR,
																																													(int) 2))
																																					: ItemStack.EMPTY)
																																							.getItem() == new ItemStack(
																																									IronManMark46Item.body,
																																									(int) (1))
																																											.getItem())
																																					|| ((((imediatesourceentity instanceof LivingEntity)
																																							? ((LivingEntity) imediatesourceentity)
																																									.getItemStackFromSlot(
																																											EquipmentSlotType
																																													.fromSlotTypeAndIndex(
																																															EquipmentSlotType.Group.ARMOR,
																																															(int) 2))
																																							: ItemStack.EMPTY)
																																									.getItem() == new ItemStack(
																																											IronManMark47Item.body,
																																											(int) (1))
																																													.getItem())
																																							|| (((imediatesourceentity instanceof LivingEntity)
																																									? ((LivingEntity) imediatesourceentity)
																																											.getItemStackFromSlot(
																																													EquipmentSlotType
																																															.fromSlotTypeAndIndex(
																																																	EquipmentSlotType.Group.ARMOR,
																																																	(int) 2))
																																									: ItemStack.EMPTY)
																																											.getItem() == new ItemStack(
																																													WarMachineMark2Item.body,
																																													(int) (1))
																																															.getItem())))))))))))))))))))) {
				entity.attackEntityFrom(DamageSource.GENERIC, (float) 6);
			}
		}
	}

	@SubscribeEvent
	public void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			Entity entity = event.getEntity();
			Entity sourceentity = event.getSource().getTrueSource();
			Entity imediatesourceentity = event.getSource().getImmediateSource();
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			double amount = event.getAmount();
			World world = entity.world;
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("amount", amount);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("sourceentity", sourceentity);
			dependencies.put("imediatesourceentity", imediatesourceentity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}
}
