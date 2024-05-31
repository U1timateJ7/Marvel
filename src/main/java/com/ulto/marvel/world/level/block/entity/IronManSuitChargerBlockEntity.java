package com.ulto.marvel.world.level.block.entity;

import com.ulto.marvel.world.level.block.IronManSuitChargerBlock;
import com.ulto.marvel.world.level.block.MarvelModBlocks;
import com.ulto.marvel.world.item.IronManSuitItem;
import com.ulto.marvel.world.level.block.state.properties.PowerStage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;
import java.util.stream.IntStream;

public class IronManSuitChargerBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
	private NonNullList<ItemStack> stacks = NonNullList.withSize(4, ItemStack.EMPTY);
	public boolean active;
	private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());

	public IronManSuitChargerBlockEntity(BlockPos position, BlockState state) {
		super(MarvelModBlockEntityTypes.IRON_MAN_SUIT_CHARGER.get(), position, state);
	}

	@Override
	public void load(CompoundTag compound) {
		super.load(compound);
		this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(compound, this.stacks);
	}

	@Override
	protected void saveAdditional(CompoundTag compound) {
		super.saveAdditional(compound);
		if (!this.trySaveLootTable(compound)) {
			ContainerHelper.saveAllItems(compound, this.stacks);
		}
	}

	@Override
	public AABB getRenderBoundingBox() {
		VoxelShape occlusionShape = getBlockState().getOcclusionShape(level, getBlockPos());
		return occlusionShape.bounds().move(getBlockPos());
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public CompoundTag getUpdateTag() {
		return this.saveWithoutMetadata();
	}

	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
		this.load(pkt.getTag());
	}

	@Override
	public int getContainerSize() {
		return stacks.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.stacks)
			if (!itemstack.isEmpty())
				return false;
		return true;
	}

	@Override
	public Component getDefaultName() {
		return MarvelModBlocks.IRON_MAN_SUIT_CHARGER.get().getName();
	}

	@Override
	public int getMaxStackSize() {
		return 64;
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inventory) {
		return null;
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.stacks;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> stacks) {
		this.stacks = stacks;
	}

	@Override
	public boolean canPlaceItem(int index, ItemStack stack) {
		return stack.getItem() instanceof IronManSuitItem ironManSuitItem && index == ironManSuitItem.getSlot().getIndex();
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		return IntStream.range(0, this.getContainerSize()).toArray();
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
		return this.canPlaceItem(index, stack);
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
		return true;
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
		if (!this.remove && facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return handlers[facing.ordinal()].cast();
		return super.getCapability(capability, facing);
	}

	@Override
	public void setRemoved() {
		super.setRemoved();
		for (LazyOptional<? extends IItemHandler> handler : handlers)
			handler.invalidate();
	}

	public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T blockEntity) {
		if (blockEntity instanceof IronManSuitChargerBlockEntity suitCharger) {
			suitCharger.active = ((IronManSuitChargerBlock) state.getBlock()).getNeighborSignal(level, pos);
			if (suitCharger.active) {
				for (ItemStack stack : suitCharger.getItems()) {
					if (stack.getItem() instanceof IronManSuitItem) IronManSuitItem.addBattery(stack, 5f / 60f / 20f);
				}
			}
			float minimumBattery = Math.min(Math.min(suitCharger.getItem(0).isEmpty() ? 100 : suitCharger.getItem(0).getOrCreateTag().getFloat("Battery"), suitCharger.getItem(1).isEmpty() ? 100 : suitCharger.getItem(1).getOrCreateTag().getFloat("Battery")), Math.min(suitCharger.getItem(2).isEmpty() ? 100 : suitCharger.getItem(2).getOrCreateTag().getFloat("Battery"), suitCharger.getItem(3).isEmpty() ? 100 : suitCharger.getItem(3).getOrCreateTag().getFloat("Battery")));
			if (suitCharger.getItem(0).isEmpty() && suitCharger.getItem(1).isEmpty() && suitCharger.getItem(2).isEmpty() && suitCharger.getItem(3).isEmpty()) minimumBattery = 0;
			level.setBlock(pos, state.setValue(IronManSuitChargerBlock.ACTIVE, suitCharger.active).setValue(IronManSuitChargerBlock.POWER_STAGE, PowerStage.getStage(minimumBattery)), 2);
		}
	}

	public boolean hasItemInSlot(EquipmentSlot slot) {
		return !getItem(slot.getIndex()).isEmpty();
	}

	public ItemStack getItemBySlot(EquipmentSlot slot) {
		return getItem(slot.getIndex());
	}

	public void setItemSlot(EquipmentSlot slot, ItemStack stack) {
		setItem(slot.getIndex(), stack);
	}
}
