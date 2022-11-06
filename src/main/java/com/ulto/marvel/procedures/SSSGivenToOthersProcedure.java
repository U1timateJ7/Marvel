package com.ulto.marvel.procedures;

import com.ulto.marvel.world.item.MarvelModItems;
import com.ulto.marvel.network.MarvelModVariables;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class SSSGivenToOthersProcedure {
	@SubscribeEvent
	public static void onRightClickEntity(PlayerInteractEvent.EntityInteract event) {
		Player sourceentity = event.getPlayer();
		if (event.getHand() != sourceentity.getUsedItemHand())
			return;
		execute(event, event.getTarget(), sourceentity);
	}

	public static void execute(Entity entity, Entity sourceentity) {
		execute(null, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
				.getItem() == MarvelModItems.SUPER_SOLDIER_SERUM.get()) {
			if (entity instanceof Player) {
				if (!(MarvelModVariables.getPlayerVariables(entity).hasEatenHeartShapedHerb
						&& MarvelModVariables.getPlayerVariables(entity).radioactive)) {
					{
						boolean _setval = true;
						entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.superSoldier = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
			} else {
				if (entity instanceof LivingEntity _entity)
					_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20000000, 0, (false), (false)));
				if (entity instanceof LivingEntity _entity)
					_entity.addEffect(new MobEffectInstance(MobEffects.JUMP, 20000000, 1, (false), (false)));
				if (entity instanceof LivingEntity _entity)
					_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20000000, 0, (false), (false)));
				if (entity instanceof LivingEntity _entity)
					_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20000000, 1, (false), (false)));
				if (entity instanceof LivingEntity _entity)
					_entity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 20000000, 1, (false), (false)));
			}
			if (sourceentity instanceof LivingEntity _entity) {
				ItemStack _setstack = new ItemStack(Items.GLASS_BOTTLE);
				_setstack.setCount(1);
				_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
				if (_entity instanceof ServerPlayer _serverPlayer)
					_serverPlayer.getInventory().setChanged();
			}
		}
		if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == MarvelModItems.ANTI_SERUM.get()) {
			if (entity instanceof Player) {
				{
					boolean _setval = false;
					entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.superSoldier = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					boolean _setval = false;
					entity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.radioactive = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if (entity instanceof LivingEntity _entity)
				_entity.removeAllEffects();
			if (sourceentity instanceof LivingEntity _entity) {
				ItemStack _setstack = new ItemStack(Items.GLASS_BOTTLE);
				_setstack.setCount(1);
				_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
				if (_entity instanceof ServerPlayer _serverPlayer)
					_serverPlayer.getInventory().setChanged();
			}
		}
	}
}
