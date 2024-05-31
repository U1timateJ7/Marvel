package com.ulto.marvel.procedures;

import com.ulto.marvel.sounds.MarvelModSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.level.block.Blocks;
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

import com.ulto.marvel.world.item.MarvelModItems;

public class Mark50SuitDownProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player player && player.getInventory().armor.get(0).is(MarvelModItems.IRON_MAN_MARK_50_BOOTS.get()) && player.getInventory().armor.get(1).is(MarvelModItems.IRON_MAN_MARK_50_LEGGINGS.get()) && player.getInventory().armor.get(2).is(MarvelModItems.IRON_MAN_MARK_50_CHESTPLATE.get()) && player.getInventory().armor.get(3).is(MarvelModItems.IRON_MAN_MARK_50_HELMET.get())) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							MarvelModSounds.get(new ResourceLocation("marvel:iron_man.mark50.suit_up")), SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, MarvelModSounds.get(new ResourceLocation("marvel:iron_man.mark50.suit_up")),
							SoundSource.NEUTRAL, 1, 1, false);
				}
			}
			ItemStack arcReactor = new ItemStack(MarvelModItems.MARK_50_ARC_REACTOR.get());
			ListTag armorList = new ListTag();
			armorList.addTag(0, player.getInventory().armor.get(0).save(new CompoundTag()));
			armorList.addTag(1, player.getInventory().armor.get(1).save(new CompoundTag()));
			armorList.addTag(2, player.getInventory().armor.get(2).save(new CompoundTag()));
			armorList.addTag(3, player.getInventory().armor.get(3).save(new CompoundTag()));
			arcReactor.getOrCreateTag().put("ArmorItems", armorList);
			player.getInventory().armor.set(3, new ItemStack(Blocks.AIR));
			if (player instanceof ServerPlayer _serverPlayer)
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
					player.getInventory().armor.set(0, new ItemStack(Blocks.AIR));
					if (player instanceof ServerPlayer _serverPlayer)
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
							player.getInventory().armor.set(1, new ItemStack(Blocks.AIR));
							if (player instanceof ServerPlayer _serverPlayer)
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
									player.getInventory().armor.set(2, arcReactor);
									if (player instanceof ServerPlayer _serverPlayer)
										_serverPlayer.getInventory().setChanged();
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
}
