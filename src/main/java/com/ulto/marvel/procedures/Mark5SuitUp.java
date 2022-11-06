package com.ulto.marvel.procedures;

import com.ulto.marvel.world.item.MarvelModItems;
import com.ulto.marvel.sounds.MarvelModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.items.ItemHandlerHelper;

public class Mark5SuitUp {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity) {
			ItemStack _setstack = new ItemStack(Blocks.AIR);
			_setstack.setCount(1);
			_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
			if (_entity instanceof ServerPlayer _serverPlayer)
				_serverPlayer.getInventory().setChanged();
		}
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						MarvelModSounds.get(new ResourceLocation("marvel:iron_man.mark5.suit_up")), SoundSource.NEUTRAL, 1, 1);
			} else {
				_level.playLocalSound(x, y, z, MarvelModSounds.get(new ResourceLocation("marvel:iron_man.mark5.suit_up")),
						SoundSource.NEUTRAL, 1, 1, false);
			}
		}
		if (entity instanceof Player _player) {
			ItemStack _setstack = (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY);
			_setstack.setCount(1);
			ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
		}
		if (entity instanceof LivingEntity _entity) {
			if (_entity instanceof Player _player)
				_player.getInventory().armor.set(2, new ItemStack(MarvelModItems.IRON_MAN_MARK_5_CHESTPLATE.get()));
			else
				_entity.setItemSlot(EquipmentSlot.CHEST, new ItemStack(MarvelModItems.IRON_MAN_MARK_5_CHESTPLATE.get()));
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
						_player.getInventory().armor.set(1, new ItemStack(MarvelModItems.IRON_MAN_MARK_5_LEGGINGS.get()));
					else
						_entity.setItemSlot(EquipmentSlot.LEGS, new ItemStack(MarvelModItems.IRON_MAN_MARK_5_LEGGINGS.get()));
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
								_player.getInventory().armor.set(0, new ItemStack(MarvelModItems.IRON_MAN_MARK_5_BOOTS.get()));
							else
								_entity.setItemSlot(EquipmentSlot.FEET, new ItemStack(MarvelModItems.IRON_MAN_MARK_5_BOOTS.get()));
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
									LivingEntity _entGetArmor = (LivingEntity) entity;
									ItemStack _setstack = _entGetArmor.getItemBySlot(EquipmentSlot.HEAD);
									_setstack.setCount(1);
									ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
								}
								if (entity instanceof LivingEntity _entity) {
									ItemStack helmet = new ItemStack(MarvelModItems.IRON_MAN_MARK_5_HELMET.get());
									helmet.getOrCreateTag().putBoolean("Open", true);
									if (_entity instanceof Player _player)
										_player.getInventory().armor.set(3, helmet);
									else {
										_entity.setItemSlot(EquipmentSlot.HEAD, helmet);
									}
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
										ToggleHelmetOnKeyProcedure.execute(world, x, y, z, entity);
										MinecraftForge.EVENT_BUS.unregister(this);
									}
								}.start(world, 10);
								MinecraftForge.EVENT_BUS.unregister(this);
							}
						}.start(world, 10);
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, 10);
				MinecraftForge.EVENT_BUS.unregister(this);
			}
		}.start(world, 10);
	}
}
