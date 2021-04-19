package com.ulto.marvel.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.GameType;
import net.minecraft.potion.EffectInstance;
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
import java.util.Collection;

import com.ulto.marvel.potion.IceingPotion;
import com.ulto.marvel.item.IronManMark5Item;
import com.ulto.marvel.item.IronManMark3Item;
import com.ulto.marvel.item.IronManMark2Item;
import com.ulto.marvel.MarvelModElements;
import com.ulto.marvel.MarvelMod;

@MarvelModElements.ModElement.Tag
public class IronManFlightProcedure extends MarvelModElements.ModElement {
	public IronManFlightProcedure(MarvelModElements instance) {
		super(instance, 192);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MarvelMod.LOGGER.warn("Failed to load dependency entity for procedure IronManFlight!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((new Object() {
			public boolean checkGamemode(Entity _ent) {
				if (_ent instanceof ServerPlayerEntity) {
					return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.SURVIVAL;
				} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
					NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
							.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
					return _npi != null && _npi.getGameType() == GameType.SURVIVAL;
				}
				return false;
			}
		}.checkGamemode(entity)) || (new Object() {
			public boolean checkGamemode(Entity _ent) {
				if (_ent instanceof ServerPlayerEntity) {
					return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.ADVENTURE;
				} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
					NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
							.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
					return _npi != null && _npi.getGameType() == GameType.ADVENTURE;
				}
				return false;
			}
		}.checkGamemode(entity)))) {
			if ((!(new Object() {
				boolean check(Entity _entity) {
					if (_entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == IceingPotion.potion)
								return true;
						}
					}
					return false;
				}
			}.check(entity)))) {
				if (((((entity instanceof LivingEntity)
						? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
						: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark2Item.body, (int) (1)).getItem())
						&& (((entity instanceof LivingEntity)
								? ((LivingEntity) entity)
										.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0))
								: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark2Item.boots, (int) (1)).getItem()))) {
					if (((entity.getPosY()) < 180)) {
						if (entity instanceof PlayerEntity) {
							((PlayerEntity) entity).abilities.allowFlying = (true);
							((PlayerEntity) entity).sendPlayerAbilities();
						}
					} else if (((entity.getPosY()) >= 180)) {
						if (entity instanceof PlayerEntity) {
							((PlayerEntity) entity).abilities.allowFlying = (false);
							((PlayerEntity) entity).sendPlayerAbilities();
						}
						if (entity instanceof LivingEntity)
							((LivingEntity) entity).addPotionEffect(new EffectInstance(IceingPotion.potion, (int) 100, (int) 0, (false), (false)));
					}
				} else if (((((entity instanceof LivingEntity)
						? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
						: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark3Item.body, (int) (1)).getItem())
						&& (((entity instanceof LivingEntity)
								? ((LivingEntity) entity)
										.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0))
								: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark3Item.boots, (int) (1)).getItem()))) {
					if (((entity.getPosY()) < 256)) {
						if (entity instanceof PlayerEntity) {
							((PlayerEntity) entity).abilities.allowFlying = (true);
							((PlayerEntity) entity).sendPlayerAbilities();
						}
					} else if (((entity.getPosY()) >= 256)) {
						if (entity instanceof PlayerEntity) {
							((PlayerEntity) entity).abilities.allowFlying = (false);
							((PlayerEntity) entity).sendPlayerAbilities();
						}
						if (entity instanceof LivingEntity)
							((LivingEntity) entity).addPotionEffect(new EffectInstance(IceingPotion.potion, (int) 100, (int) 0, (false), (false)));
					}
				} else if (((((entity instanceof LivingEntity)
						? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
						: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark5Item.body, (int) (1)).getItem())
						&& (((entity instanceof LivingEntity)
								? ((LivingEntity) entity)
										.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0))
								: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark5Item.boots, (int) (1)).getItem()))) {
					if (((entity.getPosY()) < 256)) {
						if (entity instanceof PlayerEntity) {
							((PlayerEntity) entity).abilities.allowFlying = (true);
							((PlayerEntity) entity).sendPlayerAbilities();
						}
					} else if (((entity.getPosY()) >= 256)) {
						if (entity instanceof PlayerEntity) {
							((PlayerEntity) entity).abilities.allowFlying = (false);
							((PlayerEntity) entity).sendPlayerAbilities();
						}
						if (entity instanceof LivingEntity)
							((LivingEntity) entity).addPotionEffect(new EffectInstance(IceingPotion.potion, (int) 100, (int) 0, (false), (false)));
					}
				} else {
					if (entity instanceof PlayerEntity) {
						((PlayerEntity) entity).abilities.allowFlying = (false);
						((PlayerEntity) entity).sendPlayerAbilities();
					}
				}
			} else {
				if (entity instanceof PlayerEntity) {
					((PlayerEntity) entity).abilities.allowFlying = (false);
					((PlayerEntity) entity).sendPlayerAbilities();
				}
			}
		} else {
			if (entity instanceof PlayerEntity) {
				((PlayerEntity) entity).abilities.allowFlying = (true);
				((PlayerEntity) entity).sendPlayerAbilities();
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
