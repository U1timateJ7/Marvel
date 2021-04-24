package com.ulto.marvel.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.Collection;

import com.ulto.marvel.potion.IceingPotion;
import com.ulto.marvel.MarvelModElements;
import com.ulto.marvel.MarvelMod;

@MarvelModElements.ModElement.Tag
public class IceingProblemOverlayDisplayOverlayIngameProcedure extends MarvelModElements.ModElement {
	public IceingProblemOverlayDisplayOverlayIngameProcedure(MarvelModElements instance) {
		super(instance, 202);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MarvelMod.LOGGER.warn("Failed to load dependency entity for procedure IceingProblemOverlayDisplayOverlayIngame!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((new Object() {
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
		}.check(entity))) {
			return (true);
		}
		return (false);
	}
}
