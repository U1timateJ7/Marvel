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

import java.util.Map;
import java.util.HashMap;

import com.ulto.marvel.item.BlackPantherSuitItem;
import com.ulto.marvel.MarvelModElements;
import com.ulto.marvel.MarvelMod;
import net.minecraft.util.EntityDamageSource;

@MarvelModElements.ModElement.Tag
public class BlackPantherAttackProcedure extends MarvelModElements.ModElement {
	public BlackPantherAttackProcedure(MarvelModElements instance) {
		super(instance, 99);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MarvelMod.LOGGER.warn("Failed to load dependency entity for procedure BlackPantherAttack!");
			return;
		}
		if (dependencies.get("imediatesourceentity") == null) {
			if (!dependencies.containsKey("imediatesourceentity"))
				MarvelMod.LOGGER.warn("Failed to load dependency imediatesourceentity for procedure BlackPantherAttack!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity imediatesourceentity = (Entity) dependencies.get("imediatesourceentity");
		if ((((imediatesourceentity instanceof LivingEntity)
				? ((LivingEntity) imediatesourceentity)
						.getItemStackFromSlot(EquipmentSlotType.CHEST)
				: ItemStack.EMPTY).getItem() == new ItemStack(BlackPantherSuitItem.body, (int) (1)).getItem())) {
			entity.attackEntityFrom(DamageSource.GENERIC, (float) 7);
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
