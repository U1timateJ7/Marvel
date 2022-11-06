package com.ulto.marvel.procedures;

import com.ulto.marvel.world.item.MarvelModItems;
import com.ulto.marvel.sounds.MarvelModSounds;
import com.ulto.marvel.network.MarvelModVariables;
import net.minecraft.commands.CommandFunction;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class AbilityKeyV {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (MarvelModVariables.getPlayerVariables(entity).ironManMkNum == 5) {
			if (world instanceof ServerLevel _level && _level.getServer() != null) {
				Optional<CommandFunction> _fopt = _level.getServer().getFunctions().get(new ResourceLocation("marvel:iron_man_ability"));
				_fopt.ifPresent(commandFunction -> _level.getServer().getFunctions().execute(commandFunction, new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO,
						_level, 4, "", new TextComponent(""), _level.getServer(), null)));
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							MarvelModSounds.get(new ResourceLocation("marvel:item.iron_man_helmet.close")), SoundSource.NEUTRAL, 1,
							1);
				} else {
					_level.playLocalSound(x, y, z, MarvelModSounds.get(new ResourceLocation("marvel:item.iron_man_helmet.close")),
							SoundSource.NEUTRAL, 1, 1, false);
				}
			}
		}
		if (MarvelModVariables.getPlayerVariables(entity).ironManMkNum == 16) {
			if (world instanceof ServerLevel _level && _level.getServer() != null) {
				Optional<CommandFunction> _fopt = _level.getServer().getFunctions().get(new ResourceLocation("marvel:mark_30_ability"));
				_fopt.ifPresent(commandFunction -> _level.getServer().getFunctions().execute(commandFunction, new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO,
						_level, 4, "", new TextComponent(""), _level.getServer(), null)));
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							MarvelModSounds.get(new ResourceLocation("marvel:iron_man.mark30.invisible")), SoundSource.NEUTRAL, 1,
							1);
				} else {
					_level.playLocalSound(x, y, z, MarvelModSounds.get(new ResourceLocation("marvel:iron_man.mark30.invisible")),
							SoundSource.NEUTRAL, 1, 1, false);
				}
			}
		} else if (MarvelModVariables.getPlayerVariables(entity).ironManMkNum == -16) {
			if (world instanceof ServerLevel _level && _level.getServer() != null) {
				Optional<CommandFunction> _fopt = _level.getServer().getFunctions().get(new ResourceLocation("marvel:mark_30_ability_2"));
				if (_fopt.isPresent())
					_level.getServer().getFunctions().execute(_fopt.get(), new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO,
							_level, 4, "", new TextComponent(""), _level.getServer(), null));
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							MarvelModSounds.get(new ResourceLocation("marvel:iron_man.mark30.visible")), SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, MarvelModSounds.get(new ResourceLocation("marvel:iron_man.mark30.visible")),
							SoundSource.NEUTRAL, 1, 1, false);
				}
			}
		}
		if (MarvelModVariables.getPlayerVariables(entity).ironManMkNum == 47) {
			Mark47DisconnectProcedure.execute(world, x, y, z, entity);
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.MARK_47_GLASSES.get()) {
			Mark47ConnectProcedure.execute(world, x, y, z, entity);
		}
		if (MarvelModVariables.getPlayerVariables(entity).ironManMkNum == 50) {
			Mark50SuitDownProcedure.execute(world, x, y, z, entity);
		} else if (MarvelModVariables.getPlayerVariables(entity).ironManMkNum == -50) {
			Mark50SuitUpProcedure.execute(world, x, y, z, entity);
		}
		if (MarvelModVariables.getPlayerVariables(entity).ironManMkNum == 85) {
			Mark85SuitDownProcedure.execute(world, x, y, z, entity);
		} else if (MarvelModVariables.getPlayerVariables(entity).ironManMkNum == -85) {
			Mark85SuitUpProcedure.execute(world, x, y, z, entity);
		}
		if (MarvelModVariables.getPlayerVariables(entity).ironManMkNum == 100) {
			IronSpiderSuitDownProcedure.execute(world, x, y, z, entity);
		} else if (MarvelModVariables.getPlayerVariables(entity).ironManMkNum == -100) {
			IronSpiderSuitUpProcedure.execute(world, x, y, z, entity);
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.THOR_ARMOR_CHESTPLATE.get()) {
			ThorDeactivateArmorProcedure.execute(world, x, y, z, entity);
		}
	}
}
