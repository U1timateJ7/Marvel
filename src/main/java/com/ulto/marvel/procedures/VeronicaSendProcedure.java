package com.ulto.marvel.procedures;

import com.ulto.marvel.sounds.MarvelModSounds;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import com.ulto.marvel.network.MarvelModVariables;
import com.ulto.marvel.world.item.MarvelModItems;
import com.ulto.marvel.common.MarvelMod;

public class VeronicaSendProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		MarvelMod.LOGGER.info(sourceentity.getDisplayName().getString() + " Just sent a suit to space");
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						MarvelModSounds.get(new ResourceLocation("marvel:iron_man.fly")), SoundSource.NEUTRAL, 1, 1);
			} else {
				_level.playLocalSound(x, y, z, MarvelModSounds.get(new ResourceLocation("marvel:iron_man.fly")),
						SoundSource.NEUTRAL, 1, 1, false);
			}
		}
		if (entity instanceof LivingEntity _entity)
			_entity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 100, 19, (false), (false)));
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_21_CHESTPLATE.get()) {
			{
				boolean _setval = true;
				sourceentity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.mark21Ready = _setval;
					capability.syncPlayerVariables(sourceentity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_22_CHESTPLATE.get()) {
			{
				boolean _setval = true;
				sourceentity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.mark22Ready = _setval;
					capability.syncPlayerVariables(sourceentity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_23_CHESTPLATE.get()) {
			{
				boolean _setval = true;
				sourceentity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.mark23Ready = _setval;
					capability.syncPlayerVariables(sourceentity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_25_CHESTPLATE.get()) {
			{
				boolean _setval = true;
				sourceentity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.mark25Ready = _setval;
					capability.syncPlayerVariables(sourceentity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_30_CHESTPLATE.get()) {
			{
				boolean _setval = true;
				sourceentity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.mark30Ready = _setval;
					capability.syncPlayerVariables(sourceentity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_33_CHESTPLATE.get()) {
			{
				boolean _setval = true;
				sourceentity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.mark33Ready = _setval;
					capability.syncPlayerVariables(sourceentity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_42_CHESTPLATE.get()) {
			{
				boolean _setval = true;
				sourceentity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.mark42Ready = _setval;
					capability.syncPlayerVariables(sourceentity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_43_CHESTPLATE.get()) {
			{
				boolean _setval = true;
				sourceentity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.mark43Ready = _setval;
					capability.syncPlayerVariables(sourceentity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_46_CHESTPLATE.get()) {
			{
				boolean _setval = true;
				sourceentity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.mark46Ready = _setval;
					capability.syncPlayerVariables(sourceentity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_47_CHESTPLATE.get()) {
			{
				boolean _setval = true;
				sourceentity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.mark47Ready = _setval;
					capability.syncPlayerVariables(sourceentity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_PATRIOT_CHESTPLATE.get()) {
			{
				boolean _setval = true;
				sourceentity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ironPatriotReady = _setval;
					capability.syncPlayerVariables(sourceentity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.WAR_MACHINE_MARK_2_CHESTPLATE.get()) {
			{
				boolean _setval = true;
				sourceentity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.warMachineMark2Ready = _setval;
					capability.syncPlayerVariables(sourceentity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_16_CHESTPLATE.get()) {
			{
				boolean _setval = true;
				sourceentity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.mark16Ready = _setval;
					capability.syncPlayerVariables(sourceentity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_17_CHESTPLATE.get()) {
			{
				boolean _setval = true;
				sourceentity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.mark17Ready = _setval;
					capability.syncPlayerVariables(sourceentity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_19_CHESTPLATE.get()) {
			{
				boolean _setval = true;
				sourceentity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.mark19Ready = _setval;
					capability.syncPlayerVariables(sourceentity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_20_CHESTPLATE.get()) {
			{
				boolean _setval = true;
				sourceentity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.mark20Ready = _setval;
					capability.syncPlayerVariables(sourceentity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_37_CHESTPLATE.get()) {
			{
				boolean _setval = true;
				sourceentity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.mark37Ready = _setval;
					capability.syncPlayerVariables(sourceentity);
				});
			}
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
				.getItem() == MarvelModItems.IRON_MAN_MARK_39_CHESTPLATE.get()) {
			{
				boolean _setval = true;
				sourceentity.getCapability(MarvelModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.mark39Ready = _setval;
					capability.syncPlayerVariables(sourceentity);
				});
			}
		}
		new Object() {
			private int ticks = 0;
			private float waitTicks;
			private LevelAccessor world;

			public void start(LevelAccessor world, int waitTicks) {
				this.waitTicks = waitTicks;
				MinecraftForge.EVENT_BUS.register(this);
				this.world = world;
			}

			@SubscribeEvent
			public void tick(TickEvent.ServerTickEvent event) {
				if (event.phase == TickEvent.Phase.END) {
					this.ticks += 1;
					if (this.ticks >= this.waitTicks)
						run();
				}
			}

			private void run() {
				if (!entity.level.isClientSide())
					entity.discard();
				MinecraftForge.EVENT_BUS.unregister(this);
			}
		}.start(world, 100);
	}
}
