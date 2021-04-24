package com.ulto.marvel.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Entity;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.block.Blocks;

import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Random;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Comparator;

import com.ulto.marvel.item.Mark42OpenItem;
import com.ulto.marvel.item.IronManMark42Item;
import com.ulto.marvel.entity.SentryModeEntity;
import com.ulto.marvel.MarvelModElements;
import com.ulto.marvel.MarvelMod;

@MarvelModElements.ModElement.Tag
public class Mark42UIConfirmProcedure extends MarvelModElements.ModElement {
	public Mark42UIConfirmProcedure(MarvelModElements instance) {
		super(instance, 298);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MarvelMod.LOGGER.warn("Failed to load dependency entity for procedure Mark42UIConfirm!");
			return;
		}
		if (dependencies.get("guistate") == null) {
			if (!dependencies.containsKey("guistate"))
				MarvelMod.LOGGER.warn("Failed to load dependency guistate for procedure Mark42UIConfirm!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MarvelMod.LOGGER.warn("Failed to load dependency x for procedure Mark42UIConfirm!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MarvelMod.LOGGER.warn("Failed to load dependency y for procedure Mark42UIConfirm!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MarvelMod.LOGGER.warn("Failed to load dependency z for procedure Mark42UIConfirm!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MarvelMod.LOGGER.warn("Failed to load dependency world for procedure Mark42UIConfirm!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		HashMap guistate = (HashMap) dependencies.get("guistate");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		boolean bl = false;
		double x2 = 0;
		double z2 = 0;
		double y2 = 0;
		bl = (boolean) (false);
		if ((((((new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:Destination");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())).equals("Sentry Mode")) || (((new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:Destination");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())).equals("sentry mode"))) || ((((new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:Destination");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())).equals("Sentry")) || (((new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:Destination");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())).equals("sentry"))))) {
			if (((bl) == (false))) {
				{
					List<Entity> _entfound = world.getEntitiesWithinAABB(Entity.class,
							new AxisAlignedBB(x - (400 / 2d), y - (400 / 2d), z - (400 / 2d), x + (400 / 2d), y + (400 / 2d), z + (400 / 2d)), null)
							.stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
								}
							}.compareDistOf(x, y, z)).collect(Collectors.toList());
					for (Entity entityiterator : _entfound) {
						if (((((((entityiterator instanceof LivingEntity)
								? ((LivingEntity) entityiterator)
										.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 3))
								: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark42Item.helmet, (int) (1)).getItem())
								|| (((entityiterator instanceof LivingEntity)
										? ((LivingEntity) entityiterator)
												.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 3))
										: ItemStack.EMPTY).getItem() == new ItemStack(Mark42OpenItem.helmet, (int) (1)).getItem()))
								&& (((entityiterator instanceof LivingEntity)
										? ((LivingEntity) entityiterator)
												.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
										: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark42Item.body, (int) (1)).getItem()))
								&& ((((entityiterator instanceof LivingEntity)
										? ((LivingEntity) entityiterator)
												.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 1))
										: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark42Item.legs, (int) (1)).getItem())
										&& (((entityiterator instanceof LivingEntity)
												? ((LivingEntity) entityiterator).getItemStackFromSlot(
														EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0))
												: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark42Item.boots, (int) (1)).getItem())))) {
							x2 = (double) (entityiterator.getPosX());
							y2 = (double) (entityiterator.getPosY());
							z2 = (double) (entityiterator.getPosZ());
							if ((!(entityiterator instanceof SentryModeEntity.CustomEntity))) {
								if (entityiterator instanceof LivingEntity) {
									if (entityiterator instanceof PlayerEntity)
										((PlayerEntity) entityiterator).inventory.armorInventory.set((int) 3, new ItemStack(Blocks.AIR, (int) (1)));
									else
										((LivingEntity) entityiterator).setItemStackToSlot(
												EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 3),
												new ItemStack(Blocks.AIR, (int) (1)));
									if (entityiterator instanceof ServerPlayerEntity)
										((ServerPlayerEntity) entityiterator).inventory.markDirty();
								}
								if (entityiterator instanceof LivingEntity) {
									if (entityiterator instanceof PlayerEntity)
										((PlayerEntity) entityiterator).inventory.armorInventory.set((int) 2, new ItemStack(Blocks.AIR, (int) (1)));
									else
										((LivingEntity) entityiterator).setItemStackToSlot(
												EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2),
												new ItemStack(Blocks.AIR, (int) (1)));
									if (entityiterator instanceof ServerPlayerEntity)
										((ServerPlayerEntity) entityiterator).inventory.markDirty();
								}
								if (entityiterator instanceof LivingEntity) {
									if (entityiterator instanceof PlayerEntity)
										((PlayerEntity) entityiterator).inventory.armorInventory.set((int) 1, new ItemStack(Blocks.AIR, (int) (1)));
									else
										((LivingEntity) entityiterator).setItemStackToSlot(
												EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 1),
												new ItemStack(Blocks.AIR, (int) (1)));
									if (entityiterator instanceof ServerPlayerEntity)
										((ServerPlayerEntity) entityiterator).inventory.markDirty();
								}
								if (entityiterator instanceof LivingEntity) {
									if (entityiterator instanceof PlayerEntity)
										((PlayerEntity) entityiterator).inventory.armorInventory.set((int) 0, new ItemStack(Blocks.AIR, (int) (1)));
									else
										((LivingEntity) entityiterator).setItemStackToSlot(
												EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0),
												new ItemStack(Blocks.AIR, (int) (1)));
									if (entityiterator instanceof ServerPlayerEntity)
										((ServerPlayerEntity) entityiterator).inventory.markDirty();
								}
								if (world instanceof ServerWorld) {
									Entity entityToSpawn = new SentryModeEntity.CustomEntity(SentryModeEntity.entity, (World) world);
									entityToSpawn.setLocationAndAngles((x2), (y2), (z2), world.getRandom().nextFloat() * 360F, 0);
									if (entityToSpawn instanceof MobEntity)
										((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world,
												world.getDifficultyForLocation(entityToSpawn.getPosition()), SpawnReason.MOB_SUMMONED,
												(ILivingEntityData) null, (CompoundNBT) null);
									world.addEntity(entityToSpawn);
								}
								if ((((Entity) world
										.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class, new AxisAlignedBB((x2) - (1 / 2d),
												(y2) - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), (y2) + (1 / 2d), (z2) + (1 / 2d)), null)
										.stream().sorted(new Object() {
											Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
												return Comparator
														.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
											}
										}.compareDistOf((x2), (y2), (z2))).findFirst().orElse(null)) instanceof TameableEntity)
										&& (entity instanceof PlayerEntity)) {
									((TameableEntity) ((Entity) world
											.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class, new AxisAlignedBB((x2) - (1 / 2d),
													(y2) - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), (y2) + (1 / 2d), (z2) + (1 / 2d)), null)
											.stream().sorted(new Object() {
												Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
													return Comparator
															.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
												}
											}.compareDistOf((x2), (y2), (z2))).findFirst().orElse(null))).setTamed(true);
									((TameableEntity) ((Entity) world
											.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class, new AxisAlignedBB((x2) - (1 / 2d),
													(y2) - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), (y2) + (1 / 2d), (z2) + (1 / 2d)), null)
											.stream().sorted(new Object() {
												Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
													return Comparator
															.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
												}
											}.compareDistOf((x2), (y2), (z2))).findFirst().orElse(null))).setTamedBy((PlayerEntity) entity);
								}
								if (((Entity) world
										.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class, new AxisAlignedBB((x2) - (1 / 2d),
												(y2) - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), (y2) + (1 / 2d), (z2) + (1 / 2d)), null)
										.stream().sorted(new Object() {
											Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
												return Comparator
														.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
											}
										}.compareDistOf((x2), (y2), (z2))).findFirst().orElse(null)) instanceof LivingEntity) {
									if (((Entity) world
											.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class, new AxisAlignedBB((x2) - (1 / 2d),
													(y2) - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), (y2) + (1 / 2d), (z2) + (1 / 2d)), null)
											.stream().sorted(new Object() {
												Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
													return Comparator
															.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
												}
											}.compareDistOf((x2), (y2), (z2))).findFirst().orElse(null)) instanceof PlayerEntity)
										((PlayerEntity) ((Entity) world
												.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class, new AxisAlignedBB((x2) - (1 / 2d),
														(y2) - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), (y2) + (1 / 2d), (z2) + (1 / 2d)), null)
												.stream().sorted(new Object() {
													Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
														return Comparator
																.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
													}
												}.compareDistOf((x2), (y2), (z2))).findFirst().orElse(null))).inventory.armorInventory.set((int) 3,
														new ItemStack(IronManMark42Item.helmet, (int) (1)));
									else
										((LivingEntity) ((Entity) world
												.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class, new AxisAlignedBB((x2) - (1 / 2d),
														(y2) - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), (y2) + (1 / 2d), (z2) + (1 / 2d)), null)
												.stream().sorted(new Object() {
													Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
														return Comparator
																.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
													}
												}.compareDistOf((x2), (y2), (z2))).findFirst().orElse(null))).setItemStackToSlot(
														EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 3),
														new ItemStack(IronManMark42Item.helmet, (int) (1)));
									if (((Entity) world
											.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class, new AxisAlignedBB((x2) - (1 / 2d),
													(y2) - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), (y2) + (1 / 2d), (z2) + (1 / 2d)), null)
											.stream().sorted(new Object() {
												Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
													return Comparator
															.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
												}
											}.compareDistOf((x2), (y2), (z2))).findFirst().orElse(null)) instanceof ServerPlayerEntity)
										((ServerPlayerEntity) ((Entity) world
												.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class, new AxisAlignedBB((x2) - (1 / 2d),
														(y2) - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), (y2) + (1 / 2d), (z2) + (1 / 2d)), null)
												.stream().sorted(new Object() {
													Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
														return Comparator
																.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
													}
												}.compareDistOf((x2), (y2), (z2))).findFirst().orElse(null))).inventory.markDirty();
								}
								if (((Entity) world
										.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class, new AxisAlignedBB((x2) - (1 / 2d),
												(y2) - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), (y2) + (1 / 2d), (z2) + (1 / 2d)), null)
										.stream().sorted(new Object() {
											Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
												return Comparator
														.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
											}
										}.compareDistOf((x2), (y2), (z2))).findFirst().orElse(null)) instanceof LivingEntity) {
									if (((Entity) world
											.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class, new AxisAlignedBB((x2) - (1 / 2d),
													(y2) - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), (y2) + (1 / 2d), (z2) + (1 / 2d)), null)
											.stream().sorted(new Object() {
												Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
													return Comparator
															.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
												}
											}.compareDistOf((x2), (y2), (z2))).findFirst().orElse(null)) instanceof PlayerEntity)
										((PlayerEntity) ((Entity) world
												.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class, new AxisAlignedBB((x2) - (1 / 2d),
														(y2) - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), (y2) + (1 / 2d), (z2) + (1 / 2d)), null)
												.stream().sorted(new Object() {
													Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
														return Comparator
																.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
													}
												}.compareDistOf((x2), (y2), (z2))).findFirst().orElse(null))).inventory.armorInventory.set((int) 2,
														new ItemStack(IronManMark42Item.body, (int) (1)));
									else
										((LivingEntity) ((Entity) world
												.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class, new AxisAlignedBB((x2) - (1 / 2d),
														(y2) - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), (y2) + (1 / 2d), (z2) + (1 / 2d)), null)
												.stream().sorted(new Object() {
													Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
														return Comparator
																.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
													}
												}.compareDistOf((x2), (y2), (z2))).findFirst().orElse(null))).setItemStackToSlot(
														EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2),
														new ItemStack(IronManMark42Item.body, (int) (1)));
									if (((Entity) world
											.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class, new AxisAlignedBB((x2) - (1 / 2d),
													(y2) - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), (y2) + (1 / 2d), (z2) + (1 / 2d)), null)
											.stream().sorted(new Object() {
												Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
													return Comparator
															.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
												}
											}.compareDistOf((x2), (y2), (z2))).findFirst().orElse(null)) instanceof ServerPlayerEntity)
										((ServerPlayerEntity) ((Entity) world
												.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class, new AxisAlignedBB((x2) - (1 / 2d),
														(y2) - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), (y2) + (1 / 2d), (z2) + (1 / 2d)), null)
												.stream().sorted(new Object() {
													Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
														return Comparator
																.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
													}
												}.compareDistOf((x2), (y2), (z2))).findFirst().orElse(null))).inventory.markDirty();
								}
								if (((Entity) world
										.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class, new AxisAlignedBB((x2) - (1 / 2d),
												(y2) - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), (y2) + (1 / 2d), (z2) + (1 / 2d)), null)
										.stream().sorted(new Object() {
											Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
												return Comparator
														.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
											}
										}.compareDistOf((x2), (y2), (z2))).findFirst().orElse(null)) instanceof LivingEntity) {
									if (((Entity) world
											.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class, new AxisAlignedBB((x2) - (1 / 2d),
													(y2) - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), (y2) + (1 / 2d), (z2) + (1 / 2d)), null)
											.stream().sorted(new Object() {
												Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
													return Comparator
															.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
												}
											}.compareDistOf((x2), (y2), (z2))).findFirst().orElse(null)) instanceof PlayerEntity)
										((PlayerEntity) ((Entity) world
												.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class, new AxisAlignedBB((x2) - (1 / 2d),
														(y2) - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), (y2) + (1 / 2d), (z2) + (1 / 2d)), null)
												.stream().sorted(new Object() {
													Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
														return Comparator
																.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
													}
												}.compareDistOf((x2), (y2), (z2))).findFirst().orElse(null))).inventory.armorInventory.set((int) 1,
														new ItemStack(IronManMark42Item.legs, (int) (1)));
									else
										((LivingEntity) ((Entity) world
												.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class, new AxisAlignedBB((x2) - (1 / 2d),
														(y2) - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), (y2) + (1 / 2d), (z2) + (1 / 2d)), null)
												.stream().sorted(new Object() {
													Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
														return Comparator
																.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
													}
												}.compareDistOf((x2), (y2), (z2))).findFirst().orElse(null))).setItemStackToSlot(
														EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 1),
														new ItemStack(IronManMark42Item.legs, (int) (1)));
									if (((Entity) world
											.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class, new AxisAlignedBB((x2) - (1 / 2d),
													(y2) - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), (y2) + (1 / 2d), (z2) + (1 / 2d)), null)
											.stream().sorted(new Object() {
												Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
													return Comparator
															.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
												}
											}.compareDistOf((x2), (y2), (z2))).findFirst().orElse(null)) instanceof ServerPlayerEntity)
										((ServerPlayerEntity) ((Entity) world
												.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class, new AxisAlignedBB((x2) - (1 / 2d),
														(y2) - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), (y2) + (1 / 2d), (z2) + (1 / 2d)), null)
												.stream().sorted(new Object() {
													Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
														return Comparator
																.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
													}
												}.compareDistOf((x2), (y2), (z2))).findFirst().orElse(null))).inventory.markDirty();
								}
								if (((Entity) world
										.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class, new AxisAlignedBB((x2) - (1 / 2d),
												(y2) - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), (y2) + (1 / 2d), (z2) + (1 / 2d)), null)
										.stream().sorted(new Object() {
											Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
												return Comparator
														.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
											}
										}.compareDistOf((x2), (y2), (z2))).findFirst().orElse(null)) instanceof LivingEntity) {
									if (((Entity) world
											.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class, new AxisAlignedBB((x2) - (1 / 2d),
													(y2) - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), (y2) + (1 / 2d), (z2) + (1 / 2d)), null)
											.stream().sorted(new Object() {
												Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
													return Comparator
															.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
												}
											}.compareDistOf((x2), (y2), (z2))).findFirst().orElse(null)) instanceof PlayerEntity)
										((PlayerEntity) ((Entity) world
												.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class, new AxisAlignedBB((x2) - (1 / 2d),
														(y2) - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), (y2) + (1 / 2d), (z2) + (1 / 2d)), null)
												.stream().sorted(new Object() {
													Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
														return Comparator
																.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
													}
												}.compareDistOf((x2), (y2), (z2))).findFirst().orElse(null))).inventory.armorInventory.set((int) 0,
														new ItemStack(IronManMark42Item.boots, (int) (1)));
									else
										((LivingEntity) ((Entity) world
												.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class, new AxisAlignedBB((x2) - (1 / 2d),
														(y2) - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), (y2) + (1 / 2d), (z2) + (1 / 2d)), null)
												.stream().sorted(new Object() {
													Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
														return Comparator
																.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
													}
												}.compareDistOf((x2), (y2), (z2))).findFirst().orElse(null))).setItemStackToSlot(
														EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0),
														new ItemStack(IronManMark42Item.boots, (int) (1)));
									if (((Entity) world
											.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class, new AxisAlignedBB((x2) - (1 / 2d),
													(y2) - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), (y2) + (1 / 2d), (z2) + (1 / 2d)), null)
											.stream().sorted(new Object() {
												Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
													return Comparator
															.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
												}
											}.compareDistOf((x2), (y2), (z2))).findFirst().orElse(null)) instanceof ServerPlayerEntity)
										((ServerPlayerEntity) ((Entity) world
												.getEntitiesWithinAABB(SentryModeEntity.CustomEntity.class, new AxisAlignedBB((x2) - (1 / 2d),
														(y2) - (1 / 2d), (z2) - (1 / 2d), (x2) + (1 / 2d), (y2) + (1 / 2d), (z2) + (1 / 2d)), null)
												.stream().sorted(new Object() {
													Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
														return Comparator
																.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
													}
												}.compareDistOf((x2), (y2), (z2))).findFirst().orElse(null))).inventory.markDirty();
								}
								if (world instanceof World && !world.isRemote()) {
									((World) world).playSound(null, new BlockPos((int) (x2), (int) (y2), (int) (z2)),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("marvel:item.iron_man_helmet.open")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1);
								} else {
									((World) world).playSound((x2), (y2), (z2),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("marvel:item.iron_man_helmet.open")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
								}
								bl = (boolean) (true);
							}
						}
					}
				}
			}
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).closeScreen();
		} else {
			{
				List<Entity> _entfound2 = world.getEntitiesWithinAABB(Entity.class,
						new AxisAlignedBB(x - (400 / 2d), y - (400 / 2d), z - (400 / 2d), x + (400 / 2d), y + (400 / 2d), z + (400 / 2d)), null)
						.stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf(x, y, z)).collect(Collectors.toList());
				for (Entity entityiteratortwo : _entfound2) {
					if (((bl) == (false))) {
						if (((((((/* @Entity */(entityiteratortwo) instanceof LivingEntity)
								? ((LivingEntity) /* @Entity */(entityiteratortwo))
										.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 3))
								: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark42Item.helmet, (int) (1)).getItem())
								|| (((/* @Entity */(entityiteratortwo) instanceof LivingEntity)
										? ((LivingEntity) /* @Entity */(entityiteratortwo))
												.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 3))
										: ItemStack.EMPTY).getItem() == new ItemStack(Mark42OpenItem.helmet, (int) (1)).getItem()))
								&& (((/* @Entity */(entityiteratortwo) instanceof LivingEntity)
										? ((LivingEntity) /* @Entity */(entityiteratortwo))
												.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
										: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark42Item.body, (int) (1)).getItem()))
								&& ((((/* @Entity */(entityiteratortwo) instanceof LivingEntity)
										? ((LivingEntity) /* @Entity */(entityiteratortwo))
												.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 1))
										: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark42Item.legs, (int) (1)).getItem())
										&& (((/* @Entity */(entityiteratortwo) instanceof LivingEntity)
												? ((LivingEntity) /* @Entity */(entityiteratortwo)).getItemStackFromSlot(
														EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0))
												: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark42Item.boots, (int) (1)).getItem())))) {
							{
								List<Entity> _entfound = world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(x - (400 / 2d), y - (400 / 2d),
										z - (400 / 2d), x + (400 / 2d), y + (400 / 2d), z + (400 / 2d)), null).stream().sorted(new Object() {
											Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
												return Comparator
														.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
											}
										}.compareDistOf(x, y, z)).collect(Collectors.toList());
								for (Entity entityiterator : _entfound) {
									if ((((entityiterator.getDisplayName().getString())).equals((new Object() {
										public String getText() {
											TextFieldWidget textField = (TextFieldWidget) guistate.get("text:Destination");
											if (textField != null) {
												return textField.getText();
											}
											return "";
										}
									}.getText())))) {
										if ((entityiterator instanceof PlayerEntity)) {
											if ((!((((((entityiterator instanceof LivingEntity)
													? ((LivingEntity) entityiterator).getItemStackFromSlot(
															EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 3))
													: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark42Item.helmet, (int) (1)).getItem())
													|| (((entityiterator instanceof LivingEntity)
															? ((LivingEntity) entityiterator).getItemStackFromSlot(
																	EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 3))
															: ItemStack.EMPTY)
																	.getItem() == new ItemStack(Mark42OpenItem.helmet, (int) (1)).getItem()))
													&& (((entityiterator instanceof LivingEntity)
															? ((LivingEntity) entityiterator).getItemStackFromSlot(
																	EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
															: ItemStack.EMPTY)
																	.getItem() == new ItemStack(IronManMark42Item.body, (int) (1)).getItem()))
													&& ((((entityiterator instanceof LivingEntity)
															? ((LivingEntity) entityiterator).getItemStackFromSlot(
																	EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 1))
															: ItemStack.EMPTY)
																	.getItem() == new ItemStack(IronManMark42Item.legs, (int) (1)).getItem())
															&& (((entityiterator instanceof LivingEntity)
																	? ((LivingEntity) entityiterator).getItemStackFromSlot(EquipmentSlotType
																			.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0))
																	: ItemStack.EMPTY).getItem() == new ItemStack(IronManMark42Item.boots, (int) (1))
																			.getItem()))))) {
												if (/* @Entity */(entityiteratortwo) instanceof LivingEntity) {
													if (/* @Entity */(entityiteratortwo) instanceof PlayerEntity)
														((PlayerEntity) /* @Entity */(entityiteratortwo)).inventory.armorInventory.set((int) 3,
																new ItemStack(Blocks.AIR, (int) (1)));
													else
														((LivingEntity) /* @Entity */(entityiteratortwo)).setItemStackToSlot(
																EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 3),
																new ItemStack(Blocks.AIR, (int) (1)));
													if (/* @Entity */(entityiteratortwo) instanceof ServerPlayerEntity)
														((ServerPlayerEntity) /* @Entity */(entityiteratortwo)).inventory.markDirty();
												}
												if (/* @Entity */(entityiteratortwo) instanceof LivingEntity) {
													if (/* @Entity */(entityiteratortwo) instanceof PlayerEntity)
														((PlayerEntity) /* @Entity */(entityiteratortwo)).inventory.armorInventory.set((int) 2,
																new ItemStack(Blocks.AIR, (int) (1)));
													else
														((LivingEntity) /* @Entity */(entityiteratortwo)).setItemStackToSlot(
																EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2),
																new ItemStack(Blocks.AIR, (int) (1)));
													if (/* @Entity */(entityiteratortwo) instanceof ServerPlayerEntity)
														((ServerPlayerEntity) /* @Entity */(entityiteratortwo)).inventory.markDirty();
												}
												if (/* @Entity */(entityiteratortwo) instanceof LivingEntity) {
													if (/* @Entity */(entityiteratortwo) instanceof PlayerEntity)
														((PlayerEntity) /* @Entity */(entityiteratortwo)).inventory.armorInventory.set((int) 1,
																new ItemStack(Blocks.AIR, (int) (1)));
													else
														((LivingEntity) /* @Entity */(entityiteratortwo)).setItemStackToSlot(
																EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 1),
																new ItemStack(Blocks.AIR, (int) (1)));
													if (/* @Entity */(entityiteratortwo) instanceof ServerPlayerEntity)
														((ServerPlayerEntity) /* @Entity */(entityiteratortwo)).inventory.markDirty();
												}
												if (/* @Entity */(entityiteratortwo) instanceof LivingEntity) {
													if (/* @Entity */(entityiteratortwo) instanceof PlayerEntity)
														((PlayerEntity) /* @Entity */(entityiteratortwo)).inventory.armorInventory.set((int) 0,
																new ItemStack(Blocks.AIR, (int) (1)));
													else
														((LivingEntity) /* @Entity */(entityiteratortwo)).setItemStackToSlot(
																EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0),
																new ItemStack(Blocks.AIR, (int) (1)));
													if (/* @Entity */(entityiteratortwo) instanceof ServerPlayerEntity)
														((ServerPlayerEntity) /* @Entity */(entityiteratortwo)).inventory.markDirty();
												}
												if (world instanceof World && !world.isRemote()) {
													((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
															(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
																	.getValue(new ResourceLocation("marvel:iron_man.mark42.depart")),
															SoundCategory.NEUTRAL, (float) 1, (float) 1);
												} else {
													((World) world).playSound(x, y, z,
															(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
																	.getValue(new ResourceLocation("marvel:iron_man.mark42.depart")),
															SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
												}
												if ((/* @Entity */(entityiteratortwo) instanceof SentryModeEntity.CustomEntity)) {
													if (!/* @Entity */(entityiteratortwo).world.isRemote())
														/* @Entity */(entityiteratortwo).remove();
												}
												new Object() {
													private int ticks = 0;
													private float waitTicks;
													private IWorld world;
													public void start(IWorld world, int waitTicks) {
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
														if (entityiterator instanceof PlayerEntity) {
															ItemStack _setstack = ((entityiterator instanceof LivingEntity)
																	? ((LivingEntity) entityiterator).getItemStackFromSlot(EquipmentSlotType
																			.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 3))
																	: ItemStack.EMPTY);
															_setstack.setCount((int) 1);
															ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entityiterator), _setstack);
														}
														if (entityiterator instanceof PlayerEntity) {
															ItemStack _setstack = ((entityiterator instanceof LivingEntity)
																	? ((LivingEntity) entityiterator).getItemStackFromSlot(EquipmentSlotType
																			.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
																	: ItemStack.EMPTY);
															_setstack.setCount((int) 1);
															ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entityiterator), _setstack);
														}
														if (entityiterator instanceof PlayerEntity) {
															ItemStack _setstack = ((entityiterator instanceof LivingEntity)
																	? ((LivingEntity) entityiterator).getItemStackFromSlot(EquipmentSlotType
																			.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 1))
																	: ItemStack.EMPTY);
															_setstack.setCount((int) 1);
															ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entityiterator), _setstack);
														}
														if (entityiterator instanceof PlayerEntity) {
															ItemStack _setstack = ((entityiterator instanceof LivingEntity)
																	? ((LivingEntity) entityiterator).getItemStackFromSlot(EquipmentSlotType
																			.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0))
																	: ItemStack.EMPTY);
															_setstack.setCount((int) 1);
															ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entityiterator), _setstack);
														}
														if (entityiterator instanceof LivingEntity) {
															if (entityiterator instanceof PlayerEntity)
																((PlayerEntity) entityiterator).inventory.armorInventory.set((int) 3,
																		new ItemStack(IronManMark42Item.helmet, (int) (1)));
															else
																((LivingEntity) entityiterator).setItemStackToSlot(EquipmentSlotType
																		.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 3),
																		new ItemStack(IronManMark42Item.helmet, (int) (1)));
															if (entityiterator instanceof ServerPlayerEntity)
																((ServerPlayerEntity) entityiterator).inventory.markDirty();
														}
														if (entityiterator instanceof LivingEntity) {
															if (entityiterator instanceof PlayerEntity)
																((PlayerEntity) entityiterator).inventory.armorInventory.set((int) 2,
																		new ItemStack(IronManMark42Item.body, (int) (1)));
															else
																((LivingEntity) entityiterator).setItemStackToSlot(EquipmentSlotType
																		.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2),
																		new ItemStack(IronManMark42Item.body, (int) (1)));
															if (entityiterator instanceof ServerPlayerEntity)
																((ServerPlayerEntity) entityiterator).inventory.markDirty();
														}
														if (entityiterator instanceof LivingEntity) {
															if (entityiterator instanceof PlayerEntity)
																((PlayerEntity) entityiterator).inventory.armorInventory.set((int) 1,
																		new ItemStack(IronManMark42Item.legs, (int) (1)));
															else
																((LivingEntity) entityiterator).setItemStackToSlot(EquipmentSlotType
																		.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 1),
																		new ItemStack(IronManMark42Item.legs, (int) (1)));
															if (entityiterator instanceof ServerPlayerEntity)
																((ServerPlayerEntity) entityiterator).inventory.markDirty();
														}
														if (entityiterator instanceof LivingEntity) {
															if (entityiterator instanceof PlayerEntity)
																((PlayerEntity) entityiterator).inventory.armorInventory.set((int) 0,
																		new ItemStack(IronManMark42Item.boots, (int) (1)));
															else
																((LivingEntity) entityiterator).setItemStackToSlot(EquipmentSlotType
																		.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0),
																		new ItemStack(IronManMark42Item.boots, (int) (1)));
															if (entityiterator instanceof ServerPlayerEntity)
																((ServerPlayerEntity) entityiterator).inventory.markDirty();
														}
														if (world instanceof World && !world.isRemote()) {
															((World) world).playSound(null,
																	new BlockPos((int) (entityiterator.getPosX()), (int) (entityiterator.getPosY()),
																			(int) (entityiterator.getPosZ())),
																	(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
																			.getValue(new ResourceLocation("marvel:iron_man.mark42.arrive")),
																	SoundCategory.NEUTRAL, (float) 1, (float) 1);
														} else {
															((World) world).playSound((entityiterator.getPosX()), (entityiterator.getPosY()),
																	(entityiterator.getPosZ()),
																	(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
																			.getValue(new ResourceLocation("marvel:iron_man.mark42.arrive")),
																	SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
														}
														MinecraftForge.EVENT_BUS.unregister(this);
													}
												}.start(world, (int) (((new Random()).nextInt((int) 100 + 1)) + 100));
												bl = (boolean) (true);
											}
										}
									}
								}
							}
						}
						if (entity instanceof PlayerEntity)
							((PlayerEntity) entity).closeScreen();
					}
				}
			}
		}
	}
}
