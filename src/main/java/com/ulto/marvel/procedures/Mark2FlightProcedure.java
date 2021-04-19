package com.ulto.marvel.procedures;

@MarvelModElements.ModElement.Tag
public class Mark2FlightProcedure extends MarvelModElements.ModElement {

	public Mark2FlightProcedure(MarvelModElements instance) {
		super(instance, 148);

	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MarvelMod.LOGGER.warn("Failed to load dependency entity for procedure Mark2Flight!");
			return;
		}

		Entity entity = (Entity) dependencies.get("entity");

		if (entity instanceof PlayerEntity) {
			((PlayerEntity) entity).abilities.allowFlying = ((((entity instanceof LivingEntity)
					? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0))
					: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark2Item.boots, (int) (1)).getItem()) && ((entity.getPosY()) < 180));
			((PlayerEntity) entity).sendPlayerAbilities();
		}

	}

}
