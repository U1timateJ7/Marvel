package net.tintankgames.marvel.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.tintankgames.marvel.world.inventory.SuitUpgradingMenu;
import net.tintankgames.marvel.world.inventory.SuitVariantMenu;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;
import org.jetbrains.annotations.Nullable;

public class SuitTableBlockEntity extends BlockEntity implements MenuProvider, Nameable {
    protected final ContainerData dataAccess;
    @Nullable
    private Component name;
    public boolean variant;

    public SuitTableBlockEntity(BlockPos pos, BlockState state) {
        super(MarvelBlockEntityTypes.SUIT_TABLE.get(), pos, state);
        this.dataAccess = new ContainerData() {
            @Override
            public int get(int slot) {
                return switch (slot) {
                    case 1 -> getBlockPos().getY();
                    case 2 -> getBlockPos().getZ();
                    case 3 -> variant ? 1 : 0;
                    default -> getBlockPos().getX();
                };
            }

            @Override
            public void set(int slot, int value) {
                if (slot == 3) {
                    variant = value <= 0;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    protected void loadAdditional(CompoundTag p_338606_, HolderLookup.Provider p_338309_) {
        super.loadAdditional(p_338606_, p_338309_);
        if (p_338606_.contains("CustomName", 8)) {
            this.name = parseCustomNameSafe(p_338606_.getString("CustomName"), p_338309_);
        }
        this.variant = p_338606_.getBoolean("variant_mode");
    }

    @Override
    protected void saveAdditional(CompoundTag p_187461_, HolderLookup.Provider p_324280_) {
        super.saveAdditional(p_187461_, p_324280_);
        if (this.name != null) {
            p_187461_.putString("CustomName", Component.Serializer.toJson(this.name, p_324280_));
        }
        p_187461_.putBoolean("variant_mode", this.variant);
    }

    @Override
    public Component getName() {
        return this.name != null ? this.name : MarvelBlocks.SUIT_TABLE.get().getName();
    }

    @Override
    public Component getDisplayName() {
        return this.getName();
    }

    @Nullable
    @Override
    public Component getCustomName() {
        return this.name;
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

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return variant ? new SuitVariantMenu(id, inventory, ContainerLevelAccess.create(getLevel(), getBlockPos()), dataAccess) : new SuitUpgradingMenu(id, inventory, ContainerLevelAccess.create(getLevel(), getBlockPos()), dataAccess);
    }
}
