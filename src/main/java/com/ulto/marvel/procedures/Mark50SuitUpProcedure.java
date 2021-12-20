package com.ulto.marvel.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import com.ulto.marvel.init.MarvelModItems;

public class Mark50SuitUpProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("marvel:iron_man.mark50.suit_up")), SoundSource.NEUTRAL, 1, 1);
			} else {
				_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("marvel:iron_man.mark50.suit_up")),
						SoundSource.NEUTRAL, 1, 1, false);
			}
		}
		if (entity instanceof LivingEntity _entity) {
			if (_entity instanceof Player _player)
				_player.getInventory().armor.set(2, new ItemStack(MarvelModItems.IRON_MAN_MARK_50_CHESTPLATE));
			else
				_entity.setItemSlot(EquipmentSlot.CHEST, new ItemStack(MarvelModItems.IRON_MAN_MARK_50_CHESTPLATE));
			if (_entity instanceof ServerPlayer _serverPlayer)
				_serverPlayer.getInventory().setChanged();
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
				if (entity instanceof Player _player) {
					ItemStack _setstack = (entity instanceof LivingEntity _entGetArmor
							? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS)
							: ItemStack.EMPTY);
					_setstack.setCount(1);
					ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
				}
				if (entity instanceof LivingEntity _entity) {
					if (_entity instanceof Player _player)
						_player.getInventory().armor.set(1, new ItemStack(MarvelModItems.IRON_MAN_MARK_50_LEGGINGS));
					else
						_entity.setItemSlot(EquipmentSlot.LEGS, new ItemStack(MarvelModItems.IRON_MAN_MARK_50_LEGGINGS));
					if (_entity instanceof ServerPlayer _serverPlayer)
						_serverPlayer.getInventory().setChanged();
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
						if (entity instanceof Player _player) {
							ItemStack _setstack = (entity instanceof LivingEntity _entGetArmor
									? _entGetArmor.getItemBySlot(EquipmentSlot.FEET)
									: ItemStack.EMPTY);
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
						}
						if (entity instanceof LivingEntity _entity) {
							if (_entity instanceof Player _player)
								_player.getInventory().armor.set(0, new ItemStack(MarvelModItems.IRON_MAN_MARK_50_BOOTS));
							else
								_entity.setItemSlot(EquipmentSlot.FEET, new ItemStack(MarvelModItems.IRON_MAN_MARK_50_BOOTS));
							if (_entity instanceof ServerPlayer _serverPlayer)
								_serverPlayer.getInventory().setChanged();
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
								if (entity instanceof Player _player) {
									ItemStack _setstack = (entity instanceof LivingEntity _entGetArmor
											? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD)
											: ItemStack.EMPTY);
									_setstack.setCount(1);
									ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
								}
								if (entity instanceof LivingEntity _entity) {
									if (_entity instanceof Player _player)
										_player.getInventory().armor.set(3, new ItemStack(MarvelModItems.IRON_MAN_MARK_50_HELMET));
									else
										_entity.setItemSlot(EquipmentSlot.HEAD, new ItemStack(MarvelModItems.IRON_MAN_MARK_50_HELMET));
									if (_entity instanceof ServerPlayer _serverPlayer)
										_serverPlayer.getInventory().setChanged();
								}
								MinecraftForge.EVENT_BUS.unregister(this);
							}
						}.start(world, 10);
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, 5);
				MinecraftForge.EVENT_BUS.unregister(this);
			}
		}.start(world, 10);
	}
}
