package com.ulto.marvel.procedures;

import com.ulto.marvel.network.MarvelModVariables;
import com.ulto.marvel.sounds.MarvelModSounds;
import com.ulto.marvel.world.item.MarvelModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import virtuoel.pehkui.api.ScaleTypes;

public class GrowItemUsedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!MarvelModVariables.getPlayerVariables(entity).isBig) {
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
					.getItem() == MarvelModItems.ANTMAN_V2_SUIT_HELMET.get()
					&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.ANTMAN_V2_SUIT_CHESTPLATE.get()
					&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.ANTMAN_V2_SUIT_LEGGINGS.get()
					&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.ANTMAN_V2_SUIT_BOOTS.get()) {
				if (entity instanceof Player _playerHasItem && (_playerHasItem.getInventory().contains(new ItemStack(MarvelModItems.PYM_PARTICLE.get())) || _playerHasItem.isCreative())) {
					if (((LivingEntity)entity).getItemBySlot(EquipmentSlot.HEAD).getOrCreateTag().getBoolean("Open")) ToggleHelmetOnKeyProcedure.execute(world, x, y, z, entity);
					ScaleTypes.HEIGHT.getScaleData(entity).setTargetScale(10);
					ScaleTypes.WIDTH.getScaleData(entity).setTargetScale(10);
					ScaleTypes.REACH.getScaleData(entity).setTargetScale(10);
					ScaleTypes.DROPS.getScaleData(entity).setTargetScale(10);
					ScaleTypes.PROJECTILES.getScaleData(entity).setTargetScale(10);
					ScaleTypes.MOTION.getScaleData(entity).setTargetScale(4);
					ScaleTypes.EXPLOSIONS.getScaleData(entity).setTargetScale(10);
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, new BlockPos((int) x, (int) y, (int) z),
									MarvelModSounds.get(new ResourceLocation("marvel:antman.grow")), SoundSource.NEUTRAL, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, MarvelModSounds.get(new ResourceLocation("marvel:antman.grow")),
									SoundSource.NEUTRAL, 1, 1, false);
						}
					}
					{
						boolean _setval = true;
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.isBig = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						boolean _setval = false;
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.isSmall = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					ItemStack _stktoremove = new ItemStack(_playerHasItem.isCreative() ? null : MarvelModItems.PYM_PARTICLE.get());
					_playerHasItem.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
							_playerHasItem.inventoryMenu.getCraftSlots());
				}
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.ANTMAN_V2_SUIT_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.ANTMAN_V2_SUIT_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.ANTMAN_V2_SUIT_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.ANTMAN_V2_SUIT_BOOTS.get()) {
			ScaleTypes.HEIGHT.getScaleData(entity).setTargetScale(1);
			ScaleTypes.WIDTH.getScaleData(entity).setTargetScale(1);
			ScaleTypes.REACH.getScaleData(entity).setTargetScale(1);
			ScaleTypes.DROPS.getScaleData(entity).setTargetScale(1);
			ScaleTypes.PROJECTILES.getScaleData(entity).setTargetScale(1);
			ScaleTypes.MOTION.getScaleData(entity).setTargetScale(1);
			ScaleTypes.EXPLOSIONS.getScaleData(entity).setTargetScale(1);
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							MarvelModSounds.get(new ResourceLocation("marvel:antman.shrink")), SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, MarvelModSounds.get(new ResourceLocation("marvel:antman.shrink")),
							SoundSource.NEUTRAL, 1, 1, false);
				}
			}
			{
				boolean _setval = false;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.isBig = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				boolean _setval = false;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.isSmall = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
