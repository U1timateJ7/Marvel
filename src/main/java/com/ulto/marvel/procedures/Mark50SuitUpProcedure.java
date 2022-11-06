package com.ulto.marvel.procedures;

import com.ulto.marvel.sounds.MarvelModSounds;
import com.ulto.marvel.world.item.IronManSuitItem;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.items.ItemHandlerHelper;

public class Mark50SuitUpProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player player) {
			ItemStack arcReactor = player.getInventory().armor.get(2);
			ItemStack helmet = ItemStack.of(arcReactor.getOrCreateTag().getList("ArmorItems", 10).getCompound(3));
			ItemStack chestplate = ItemStack.of(arcReactor.getOrCreateTag().getList("ArmorItems", 10).getCompound(2));
			ItemStack leggings = ItemStack.of(arcReactor.getOrCreateTag().getList("ArmorItems", 10).getCompound(1));
			ItemStack boots = ItemStack.of(arcReactor.getOrCreateTag().getList("ArmorItems", 10).getCompound(0));
			if (IronManSuitItem.getBattery(helmet) > 0 && IronManSuitItem.getBattery(chestplate) > 0 && IronManSuitItem.getBattery(leggings) > 0 && IronManSuitItem.getBattery(boots) > 0) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, new BlockPos((int) x, (int) y, (int) z),
								MarvelModSounds.get(new ResourceLocation("marvel:iron_man.mark50.suit_up")), SoundSource.NEUTRAL, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, MarvelModSounds.get(new ResourceLocation("marvel:iron_man.mark50.suit_up")),
								SoundSource.NEUTRAL, 1, 1, false);
					}
				}
				LivingEntity _entity = (LivingEntity) entity;
				player.getInventory().armor.set(2, chestplate);
				if (_entity instanceof ServerPlayer _serverPlayer)
					_serverPlayer.getInventory().setChanged();
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
								_player.getInventory().armor.set(1, leggings);
							else
								_entity.setItemSlot(EquipmentSlot.LEGS, leggings);
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
										_player.getInventory().armor.set(0, boots);
									else
										_entity.setItemSlot(EquipmentSlot.FEET, boots);
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
												_player.getInventory().armor.set(3, helmet);
											else
												_entity.setItemSlot(EquipmentSlot.HEAD, helmet);
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
			} else {
				if (player.level.isClientSide()) player.displayClientMessage(new TranslatableComponent("item.marvel.mark_50_arc_reactor.out_of_power"), false);
			}
		}
	}
}
