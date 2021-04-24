package com.ulto.marvel.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.Collections;

import com.ulto.marvel.MarvelModElements;
import com.ulto.marvel.MarvelMod;

@MarvelModElements.ModElement.Tag
public class WebShooterSwingHitsLivingEntityProcedure extends MarvelModElements.ModElement {
	public WebShooterSwingHitsLivingEntityProcedure(MarvelModElements instance) {
		super(instance, 224);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MarvelMod.LOGGER.warn("Failed to load dependency entity for procedure WebShooterSwingHitsLivingEntity!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				MarvelMod.LOGGER.warn("Failed to load dependency sourceentity for procedure WebShooterSwingHitsLivingEntity!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		{
			Entity _ent = entity;
			_ent.setPositionAndUpdate((sourceentity.getPosX()), (sourceentity.getPosY()), (sourceentity.getPosZ()));
			if (_ent instanceof ServerPlayerEntity) {
				((ServerPlayerEntity) _ent).connection.setPlayerLocation((sourceentity.getPosX()), (sourceentity.getPosY()), (sourceentity.getPosZ()),
						_ent.rotationYaw, _ent.rotationPitch, Collections.emptySet());
			}
		}
		entity.attackEntityFrom(DamageSource.GENERIC, (float) 3);
	}
}
