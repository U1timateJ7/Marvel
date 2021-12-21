package com.ulto.marvel.procedures;

import com.ulto.marvel.init.MarvelModItems;
import com.ulto.marvel.network.MarvelModVariables;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

public class ShrinkItemUsedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!(entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MarvelModVariables.PlayerVariables())).isSmall) {
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
					.getItem() == MarvelModItems.ANTMAN_SUIT_HELMET
					&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.ANTMAN_SUIT_CHESTPLATE
					&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.ANTMAN_SUIT_LEGGINGS
					&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.ANTMAN_SUIT_BOOTS
					|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
							.getItem() == MarvelModItems.ANTMAN_V_2_SUIT_HELMET
							&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
									.getItem() == MarvelModItems.ANTMAN_V_2_SUIT_CHESTPLATE
							&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
									.getItem() == MarvelModItems.ANTMAN_V_2_SUIT_LEGGINGS
							&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
									.getItem() == MarvelModItems.ANTMAN_V_2_SUIT_BOOTS) {
				if (entity instanceof Player _playerHasItem && _playerHasItem.getInventory().contains(new ItemStack(MarvelModItems.PYM_PARTICLE))) {
					if (((LivingEntity)entity).getItemBySlot(EquipmentSlot.HEAD).getOrCreateTag().getBoolean("Open")) ToggleHelmetOnKeyProcedure.execute(world, x, y, z, entity);
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands()
								.performCommand(
										new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "", new TextComponent(""),
												_level.getServer(), null).withSuppressedOutput(),
										"scale set pehkui:height 0.1 " + entity.getStringUUID());
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands()
								.performCommand(
										new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "", new TextComponent(""),
												_level.getServer(), null).withSuppressedOutput(),
										"scale set pehkui:width 0.1 " + entity.getStringUUID());
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands()
								.performCommand(
										new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "", new TextComponent(""),
												_level.getServer(), null).withSuppressedOutput(),
										"scale set pehkui:reach 0.5 " + entity.getStringUUID());
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands()
								.performCommand(
										new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "", new TextComponent(""),
												_level.getServer(), null).withSuppressedOutput(),
										"scale set pehkui:drops 0.1 " + entity.getStringUUID());
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performCommand(
								new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "", new TextComponent(""),
										_level.getServer(), null).withSuppressedOutput(),
								"scale set pehkui:projectiles 0.1 " + entity.getStringUUID());
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands()
								.performCommand(
										new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "", new TextComponent(""),
												_level.getServer(), null).withSuppressedOutput(),
										"scale set pehkui:motion 1 " + entity.getStringUUID());
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, new BlockPos((int) x, (int) y, (int) z),
									ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("marvel:antman.shrink")), SoundSource.NEUTRAL, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("marvel:antman.shrink")),
									SoundSource.NEUTRAL, 1, 1, false);
						}
					}
					{
						boolean _setval = true;
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.isSmall = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						boolean _setval = false;
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.isBig = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					if (entity instanceof Player _player) {
						ItemStack _stktoremove = new ItemStack(MarvelModItems.PYM_PARTICLE);
						_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
								_player.inventoryMenu.getCraftSlots());
					}
				}
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.ANTMAN_SUIT_HELMET
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.ANTMAN_SUIT_CHESTPLATE
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.ANTMAN_SUIT_LEGGINGS
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.ANTMAN_SUIT_BOOTS
				|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem() == MarvelModItems.ANTMAN_V_2_SUIT_HELMET
						&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
								.getItem() == MarvelModItems.ANTMAN_V_2_SUIT_CHESTPLATE
						&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
								.getItem() == MarvelModItems.ANTMAN_V_2_SUIT_LEGGINGS
						&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
								.getItem() == MarvelModItems.ANTMAN_V_2_SUIT_BOOTS) {
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performCommand(new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "",
						new TextComponent(""), _level.getServer(), null).withSuppressedOutput(),
						"scale set pehkui:height 1 " + entity.getStringUUID());
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performCommand(new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "",
						new TextComponent(""), _level.getServer(), null).withSuppressedOutput(),
						"scale set pehkui:width 1 " + entity.getStringUUID());
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performCommand(new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "",
						new TextComponent(""), _level.getServer(), null).withSuppressedOutput(),
						"scale set pehkui:reach 1 " + entity.getStringUUID());
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performCommand(new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "",
						new TextComponent(""), _level.getServer(), null).withSuppressedOutput(),
						"scale set pehkui:drops 1 " + entity.getStringUUID());
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performCommand(new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "",
						new TextComponent(""), _level.getServer(), null).withSuppressedOutput(),
						"scale set pehkui:projectiles 1 " + entity.getStringUUID());
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performCommand(new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, _level, 4, "",
						new TextComponent(""), _level.getServer(), null).withSuppressedOutput(),
						"scale set pehkui:motion 1 " + entity.getStringUUID());
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("marvel:antman.grow")), SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("marvel:antman.grow")),
							SoundSource.NEUTRAL, 1, 1, false);
				}
			}
			{
				boolean _setval = false;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.isSmall = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				boolean _setval = false;
				entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.isBig = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
