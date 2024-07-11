package net.tintankgames.marvel.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.inventory.SuitChargerMenu;
import net.tintankgames.marvel.world.item.EnergySuitItem;
import net.tintankgames.marvel.world.level.block.SuitChargerBlock;
import org.jetbrains.annotations.Nullable;

public class SuitChargerBlockEntity extends BaseContainerBlockEntity {
    private NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);

    public SuitChargerBlockEntity(BlockPos pos, BlockState state) {
        super(MarvelBlockEntityTypes.SUIT_CHARGER.get(), pos, state);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items, provider);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);
        ContainerHelper.saveAllItems(tag, this.items, provider);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider p_323910_) {
        return this.saveCustomOnly(p_323910_);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    protected Component getDefaultName() {
        return SuitChargerBlock.UPGRADING_TITLE;
    }

    public ItemStack getItem(EquipmentSlot slot) {
        return slot.isArmor() ? items.get(slot.getIndex()) : ItemStack.EMPTY;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> list) {
        items = list;
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new SuitChargerMenu(id, inventory, this, ContainerLevelAccess.create(getLevel(), getBlockPos()));
    }

    @Override
    public int getContainerSize() {
        return 4;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, SuitChargerBlockEntity blockEntity) {
        if (!level.isClientSide()) {
            if (state.getValue(SuitChargerBlock.POWERED) && blockEntity.getItem(EquipmentSlot.FEET).has(MarvelDataComponents.ENERGY) && blockEntity.getItem(EquipmentSlot.LEGS).has(MarvelDataComponents.ENERGY) && blockEntity.getItem(EquipmentSlot.CHEST).has(MarvelDataComponents.ENERGY) && blockEntity.getItem(EquipmentSlot.HEAD).has(MarvelDataComponents.ENERGY)) {
                blockEntity.getItems().forEach(stack -> EnergySuitItem.addEnergy(stack, 0.01F));
            }
            level.sendBlockUpdated(pos, state, state, 2);
        }
    }
}
