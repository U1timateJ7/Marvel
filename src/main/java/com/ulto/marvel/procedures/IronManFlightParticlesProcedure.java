package com.ulto.marvel.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.GameType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.Minecraft;

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
import com.ulto.marvel.MarvelModElements;
import com.ulto.marvel.MarvelMod;

@MarvelModElements.ModElement.Tag
public class IronManFlightParticlesProcedure extends MarvelModElements.ModElement {
	public IronManFlightParticlesProcedure(MarvelModElements instance) {
		super(instance, 299);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MarvelMod.LOGGER.warn("Failed to load dependency entity for procedure IronManFlightParticles!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MarvelMod.LOGGER.warn("Failed to load dependency x for procedure IronManFlightParticles!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MarvelMod.LOGGER.warn("Failed to load dependency y for procedure IronManFlightParticles!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MarvelMod.LOGGER.warn("Failed to load dependency z for procedure IronManFlightParticles!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MarvelMod.LOGGER.warn("Failed to load dependency world for procedure IronManFlightParticles!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((((PlayerEntity) entity).abilities.isFlying) && (!(new Object() {
			public boolean checkGamemode(Entity _ent) {
				if (_ent instanceof ServerPlayerEntity) {
					return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.SPECTATOR;
				} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
					NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
							.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
					return _npi != null && _npi.getGameType() == GameType.SPECTATOR;
				}
				return false;
			}
		}.checkGamemode(entity))))) {
			if (((((entity instanceof LivingEntity)
					? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0))
					: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark2Item.boots, (int) (1)).getItem())
					|| ((((entity instanceof LivingEntity)
							? ((LivingEntity) entity)
									.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0))
							: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark3Item.boots, (int) (1)).getItem())
							|| ((((entity instanceof LivingEntity)
									? ((LivingEntity) entity)
											.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0))
									: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark5Item.boots, (int) (1)).getItem())
									|| ((((entity instanceof LivingEntity)
											? ((LivingEntity) entity).getItemStackFromSlot(
													EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0))
											: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark6Item.boots, (int) (1)).getItem())
											|| ((((entity instanceof LivingEntity)
													? ((LivingEntity) entity).getItemStackFromSlot(
															EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0))
													: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark21Item.boots, (int) (1)).getItem())
													|| ((((entity instanceof LivingEntity)
															? ((LivingEntity) entity).getItemStackFromSlot(
																	EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0))
															: ItemStack.EMPTY).getItem() == new ItemStack(WarMachineItem.boots, (int) (1)).getItem())
															|| ((((entity instanceof LivingEntity)
																	? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType
																			.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0))
																	: ItemStack.EMPTY)
																			.getItem() == new ItemStack(IronManMark22Item.boots, (int) (1)).getItem())
																	|| ((((entity instanceof LivingEntity)
																			? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType
																					.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0))
																			: ItemStack.EMPTY)
																					.getItem() == new ItemStack(IronManMark23Item.boots, (int) (1))
																							.getItem())
																			|| ((((entity instanceof LivingEntity)
																					? ((LivingEntity) entity).getItemStackFromSlot(
																							EquipmentSlotType.fromSlotTypeAndIndex(
																									EquipmentSlotType.Group.ARMOR, (int) 0))
																					: ItemStack.EMPTY)
																							.getItem() == new ItemStack(IronPatriotItem.boots,
																									(int) (1)).getItem())
																					|| ((((entity instanceof LivingEntity)
																							? ((LivingEntity) entity).getItemStackFromSlot(
																									EquipmentSlotType.fromSlotTypeAndIndex(
																											EquipmentSlotType.Group.ARMOR, (int) 0))
																							: ItemStack.EMPTY)
																									.getItem() == new ItemStack(
																											IronManMark25Item.boots, (int) (1))
																													.getItem())
																							|| ((((entity instanceof LivingEntity)
																									? ((LivingEntity) entity).getItemStackFromSlot(
																											EquipmentSlotType.fromSlotTypeAndIndex(
																													EquipmentSlotType.Group.ARMOR,
																													(int) 0))
																									: ItemStack.EMPTY).getItem() == new ItemStack(
																											IronManMark30Item.boots, (int) (1))
																													.getItem())
																									|| ((((entity instanceof LivingEntity)
																											? ((LivingEntity) entity)
																													.getItemStackFromSlot(
																															EquipmentSlotType
																																	.fromSlotTypeAndIndex(
																																			EquipmentSlotType.Group.ARMOR,
																																			(int) 0))
																											: ItemStack.EMPTY)
																													.getItem() == new ItemStack(
																															Mark30StealthItem.boots,
																															(int) (1)).getItem())
																											|| ((((entity instanceof LivingEntity)
																													? ((LivingEntity) entity)
																															.getItemStackFromSlot(
																																	EquipmentSlotType
																																			.fromSlotTypeAndIndex(
																																					EquipmentSlotType.Group.ARMOR,
																																					(int) 0))
																													: ItemStack.EMPTY)
																															.getItem() == new ItemStack(
																																	IronManMark33Item.boots,
																																	(int) (1))
																																			.getItem())
																													|| ((((entity instanceof LivingEntity)
																															? ((LivingEntity) entity)
																																	.getItemStackFromSlot(
																																			EquipmentSlotType
																																					.fromSlotTypeAndIndex(
																																							EquipmentSlotType.Group.ARMOR,
																																							(int) 0))
																															: ItemStack.EMPTY)
																																	.getItem() == new ItemStack(
																																			IronManMark42Item.boots,
																																			(int) (1))
																																					.getItem())
																															|| ((((entity instanceof LivingEntity)
																																	? ((LivingEntity) entity)
																																			.getItemStackFromSlot(
																																					EquipmentSlotType
																																							.fromSlotTypeAndIndex(
																																									EquipmentSlotType.Group.ARMOR,
																																									(int) 0))
																																	: ItemStack.EMPTY)
																																			.getItem() == new ItemStack(
																																					IronManMark43Item.boots,
																																					(int) (1))
																																							.getItem())
																																	|| ((((entity instanceof LivingEntity)
																																			? ((LivingEntity) entity)
																																					.getItemStackFromSlot(
																																							EquipmentSlotType
																																									.fromSlotTypeAndIndex(
																																											EquipmentSlotType.Group.ARMOR,
																																											(int) 0))
																																			: ItemStack.EMPTY)
																																					.getItem() == new ItemStack(
																																							IronManMark46Item.boots,
																																							(int) (1))
																																									.getItem())
																																			|| ((((entity instanceof LivingEntity)
																																					? ((LivingEntity) entity)
																																							.getItemStackFromSlot(
																																									EquipmentSlotType
																																											.fromSlotTypeAndIndex(
																																													EquipmentSlotType.Group.ARMOR,
																																													(int) 0))
																																					: ItemStack.EMPTY)
																																							.getItem() == new ItemStack(
																																									IronManMark47Item.boots,
																																									(int) (1))
																																											.getItem())
																																					|| (((entity instanceof LivingEntity)
																																							? ((LivingEntity) entity)
																																									.getItemStackFromSlot(
																																											EquipmentSlotType
																																													.fromSlotTypeAndIndex(
																																															EquipmentSlotType.Group.ARMOR,
																																															(int) 0))
																																							: ItemStack.EMPTY)
																																									.getItem() == new ItemStack(
																																											WarMachineMark2Item.boots,
																																											(int) (1))
																																													.getItem()))))))))))))))))))) {
				world.addParticle(ParticleTypes.FLAME, x, y, z, 0, (-0.5), 0);
				world.addParticle(ParticleTypes.FLAME, (x + 0.1), y, z, 0, (-0.5), 0);
				world.addParticle(ParticleTypes.FLAME, (x - 0.1), y, z, 0, (-0.5), 0);
				world.addParticle(ParticleTypes.FLAME, x, y, (z + 0.1), 0, (-0.5), 0);
				world.addParticle(ParticleTypes.FLAME, x, y, (z - 0.1), 0, (-0.5), 0);
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
