package com.ulto.marvel.procedures;

import net.minecraft.entity.Entity;

import java.util.Map;

import com.ulto.marvel.MarvelModElements;
import com.ulto.marvel.MarvelMod;

@MarvelModElements.ModElement.Tag
public class SentryTeleportProcedure extends MarvelModElements.ModElement {
	public SentryTeleportProcedure(MarvelModElements instance) {
		super(instance, 338);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MarvelMod.LOGGER.warn("Failed to load dependency entity for procedure SentryTeleport!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		return (entity.isOnGround());
	}
}
