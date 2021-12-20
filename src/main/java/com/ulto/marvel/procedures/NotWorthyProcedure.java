package com.ulto.marvel.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

import java.util.concurrent.atomic.AtomicReference;

import com.ulto.marvel.init.MarvelModItems;
import com.ulto.marvel.init.MarvelModBlocks;

@Mod.EventBusSubscriber
public class NotWorthyProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Entity entity = event.player;
			execute(event, entity.level, entity.getX(), entity.getY(), entity.getZ(), entity);
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		{
			AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _iitemhandlerref.set(capability));
			if (_iitemhandlerref.get() != null) {
				for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
					ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx);
					if (itemstackiterator.getItem() == MarvelModItems.MJOLNIR) {
						if (!(itemstackiterator.getOrCreateTag().getString("OwnerId")).equals(entity.getStringUUID())) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, new BlockPos((int) x, (int) y, (int) z),
											ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("marvel:item.mjolnir.throw")),
											SoundSource.NEUTRAL, 1, 1);
								} else {
									_level.playLocalSound(x, y, z,
											ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("marvel:item.mjolnir.throw")),
											SoundSource.NEUTRAL, 1, 1, false);
								}
							}
							world.setBlock(new BlockPos((int) x, (int) y, (int) z), MarvelModBlocks.MJOLNIR_BLOCK.defaultBlockState(), 3);
							if (!world.isClientSide()) {
								BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getTileData().putString("OwnerId", (itemstackiterator.getOrCreateTag().getString("OwnerId")));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
							if (entity instanceof Player _player) {
								ItemStack _stktoremove = new ItemStack(MarvelModItems.MJOLNIR);
								_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
										_player.inventoryMenu.getCraftSlots());
							}
						}
					}
				}
			}
		}
	}
}
