package net.tintankgames.marvel.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.UUIDUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.entity.IronManSuitPart;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;
import net.tintankgames.marvel.world.inventory.SuitChargerMenu;
import net.tintankgames.marvel.world.item.EnergySuitItem;
import net.tintankgames.marvel.world.item.SummonableIronManSuitItem;
import net.tintankgames.marvel.world.item.component.SuitParts;
import net.tintankgames.marvel.world.level.MarvelTicketTypes;
import net.tintankgames.marvel.world.level.block.SuitChargerBlock;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class SuitChargerBlockEntity extends BaseContainerBlockEntity {
    private NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);
    protected final ContainerData dataAccess;
    private UUID lastInteracted = null;
    private long ticketTimer = 0L;

    public SuitChargerBlockEntity(BlockPos pos, BlockState state) {
        super(MarvelBlockEntityTypes.SUIT_CHARGER.get(), pos, state);
        this.dataAccess = new ContainerData() {
            @Override
            public int get(int slot) {
                if (lastInteracted == null) return 0;
                int[] uuid = UUIDUtil.uuidToIntArray(lastInteracted);
                return uuid[slot];
            }

            @Override
            public void set(int slot, int value) {
                if (lastInteracted == null) lastInteracted = new UUID(0, 0);
                int[] uuid = UUIDUtil.uuidToIntArray(lastInteracted);
                uuid[slot] = value;
                lastInteracted = UUIDUtil.uuidFromIntArray(uuid);
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items, provider);
        if (tag.contains("last_interacted", Tag.TAG_INT_ARRAY)) setLastInteracted(tag.getUUID("last_interacted"));
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);
        ContainerHelper.saveAllItems(tag, this.items, provider);
        if (lastInteracted != null) tag.putUUID("last_interacted", getLastInteracted());
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

    public UUID getLastInteracted() {
        return lastInteracted;
    }

    public Player getLastInteracted(ServerLevel level) {
        return level.getServer().getPlayerList().getPlayer(getLastInteracted());
    }

    public void setLastInteracted(UUID lastInteracted) {
        this.lastInteracted = lastInteracted;
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

    public NonNullList<ItemStack> items() {
        return NonNullList.copyOf(items);
    }

    @Override
    protected void setItems(NonNullList<ItemStack> list) {
        items = list;
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new SuitChargerMenu(id, inventory, this, ContainerLevelAccess.create(getLevel(), getBlockPos()), dataAccess);
    }

    @Override
    public int getContainerSize() {
        return 4;
    }

    public static long registerAndUpdateTicket(Level level, ChunkPos chunkPos) {
        if (level instanceof ServerLevel serverlevel) {
            serverlevel.resetEmptyTime();
            return placeTicket(serverlevel, chunkPos) - 1L;
        } else {
            return 0L;
        }
    }

    public static long placeTicket(ServerLevel level, ChunkPos chunkPos) {
        level.getChunkSource().addRegionTicket(MarvelTicketTypes.SUIT_PART, chunkPos, 2, chunkPos);
        return MarvelTicketTypes.SUIT_PART.timeout();
    }

    public static void tick(Level level, BlockPos pos, BlockState state, SuitChargerBlockEntity blockEntity) {
        if (level instanceof ServerLevel serverLevel) {
            if (state.getValue(SuitChargerBlock.POWERED) && blockEntity.getItem(EquipmentSlot.FEET).has(MarvelDataComponents.ENERGY) && blockEntity.getItem(EquipmentSlot.LEGS).has(MarvelDataComponents.ENERGY) && blockEntity.getItem(EquipmentSlot.CHEST).has(MarvelDataComponents.ENERGY) && blockEntity.getItem(EquipmentSlot.HEAD).has(MarvelDataComponents.ENERGY)) {
                blockEntity.getItems().forEach(stack -> EnergySuitItem.addEnergy(stack, 0.01F));
            }
            if (--blockEntity.ticketTimer <= 0L) {
                blockEntity.ticketTimer = registerAndUpdateTicket(level, new ChunkPos(pos));
            }
            if (blockEntity.getLastInteracted(serverLevel) != null && state.getValue(SuitChargerBlock.POWERED)) {
                if (blockEntity.getLastInteracted(serverLevel).getData(MarvelAttachmentTypes.SUMMONING_SUIT) && !blockEntity.getLastInteracted(serverLevel).getData(MarvelAttachmentTypes.SUMMONED_SUIT) && blockEntity.getItems().stream().allMatch(stack -> stack.getItem() instanceof SummonableIronManSuitItem || stack.isEmpty()) && blockEntity.getItems().stream().anyMatch(stack -> stack.getItem() instanceof SummonableIronManSuitItem)) {
                    blockEntity.getLastInteracted(serverLevel).setData(MarvelAttachmentTypes.SUMMONED_SUIT, true);
                    for (ItemStack stack : blockEntity.getItems().stream().filter(stack -> !stack.isEmpty()).toList()) {
                        SummonableIronManSuitItem item = (SummonableIronManSuitItem) stack.getItem();
                        SuitParts parts = stack.getOrDefault(MarvelDataComponents.SUIT_PARTS, new SuitParts(item.getType() == ArmorItem.Type.CHESTPLATE ? List.of(true, true, true, true, true, true) : List.of(true, true)));
                        for (int i = 0; i < parts.parts().size(); i++) {
                            if (parts.parts().get(i)) {
                                IronManSuitPart part = MarvelEntityTypes.IRON_MAN_SUIT_PART.get().create(serverLevel, null, pos, MobSpawnType.TRIGGERED, false, false);
                                if (part != null) {
                                    part.setTame(true, false);
                                    part.setOwnerUUID(blockEntity.getLastInteracted());
                                    ItemStack newStack = stack.copy();
                                    newStack.set(MarvelDataComponents.SUIT_PARTS, SuitParts.onePart(i, parts.parts().size()));
                                    part.setPiece(newStack);
                                    part.setDelay(level.getRandom().nextInt(20, 160));
                                    serverLevel.addFreshEntityWithPassengers(part);
                                    if (part.getArmorType() == ArmorItem.Type.CHESTPLATE) {
                                        part.moveTo(part.getX(), part.getY() + 0.9375, part.getZ(), state.getValue(SuitChargerBlock.FACING).toYRot(), 0);
                                    } else if (part.getArmorType() == ArmorItem.Type.HELMET) {
                                        part.moveTo(part.getX(), part.getY() + 1.6875, part.getZ(), state.getValue(SuitChargerBlock.FACING).toYRot(), 0);
                                    } else {
                                        part.moveTo(part.getX(), part.getY() + 0.1875, part.getZ(), state.getValue(SuitChargerBlock.FACING).toYRot(), 0);
                                    }
                                    part.yRotO = state.getValue(SuitChargerBlock.FACING).toYRot();
                                    part.yBodyRotO = state.getValue(SuitChargerBlock.FACING).toYRot();
                                    part.yHeadRotO = state.getValue(SuitChargerBlock.FACING).toYRot();
                                }
                            }
                        }
                        stack.shrink(stack.getCount());
                        blockEntity.setChanged();
                    }
                }
            }
            level.sendBlockUpdated(pos, state, state, 2);
        }
    }
}
