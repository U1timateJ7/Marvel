package net.tintankgames.marvel.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.tintankgames.marvel.world.entity.VeronicaSentry;
import net.tintankgames.marvel.world.item.EnergySuitItem;
import net.tintankgames.marvel.world.level.block.Mark38ChargerBlock;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;
import net.tintankgames.marvel.world.level.block.SuitChargerBlock;
import net.tintankgames.marvel.world.level.block.state.properties.ChargerPart;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Mark38ChargerBlockEntity extends BlockEntity {
    private Map<ChargerPart, BlockPos> extraBlocks = new HashMap<>();
    public boolean shouldBeDestroyed = false;

    public Mark38ChargerBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MarvelBlockEntityTypes.MARK_38_CHARGER.get(), blockPos, blockState);
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.saveAdditional(compoundTag, provider);
        ListTag list = new ListTag();
        extraBlocks.forEach((part, pos) -> {
            CompoundTag tag = new CompoundTag();
            tag.putString("part", part.getSerializedName());
            tag.putIntArray("pos", new int[] {pos.getX(), pos.getY(), pos.getZ()});
            list.add(tag);
        });
        compoundTag.put("ExtraBlocks", list);
    }

    @Override
    public void loadAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.loadAdditional(compoundTag, provider);
        ListTag list = compoundTag.getList("ExtraBlocks", Tag.TAG_COMPOUND);
        list.forEach(tags -> {
            if (tags instanceof CompoundTag tag) {
                int[] pos = tag.getIntArray("pos");
                extraBlocks = new HashMap<>();
                extraBlocks.put(ChargerPart.valueOf(tag.getString("part").toUpperCase()), new BlockPos(pos[0], pos[1], pos[2]));
                extraBlocks = Map.copyOf(extraBlocks);
            }
        });
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        return this.saveWithoutMetadata(provider);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    private int tickCount = 0;

    public static void tick(Level level, BlockPos blockPos, BlockState state, Mark38ChargerBlockEntity mark38ChargerBlockEntity) {
        for (BlockPos pos : Mark38ChargerBlock.getExtraPlacementPositions(blockPos, state)) {
            if (level.getBlockEntity(pos, MarvelBlockEntityTypes.MARK_38_CHARGER_PART.get()).isPresent()) {
                level.getBlockEntity(pos, MarvelBlockEntityTypes.MARK_38_CHARGER_PART.get()).get().mainBlock = blockPos;
            }
        }
        if (mark38ChargerBlockEntity.tickCount > 3) {
            boolean bl = true;
            for (BlockPos pos : Mark38ChargerBlock.getExtraPlacementPositions(blockPos, state.getValue(Mark38ChargerBlock.FACING))) {
                if (!level.getBlockState(pos).is(MarvelBlocks.MARK_38_CHARGER_PART)) {
                    bl = false;
                    break;
                }
            }
            if (!bl) {
                mark38ChargerBlockEntity.shouldBeDestroyed = true;
            }

            boolean bl2 = false;
            for (BlockPos pos : Mark38ChargerBlock.getExtraPlacementPositions(blockPos, state.getValue(Mark38ChargerBlock.FACING))) {
                if (level.getBlockEntity(pos, MarvelBlockEntityTypes.MARK_38_CHARGER_PART.get()).isPresent()) {
                    if (level.getBlockEntity(pos, MarvelBlockEntityTypes.MARK_38_CHARGER_PART.get()).get().shouldBeDestroyed) {
                        bl2 = true;
                        break;
                    }
                }
            }
            if (bl2 || mark38ChargerBlockEntity.shouldBeDestroyed) {
                level.destroyBlock(blockPos, true);
            }
        }
        mark38ChargerBlockEntity.tickCount++;
        List<VeronicaSentry> list = new ArrayList<>();
        List<BlockPos> posList = new ArrayList<>(Arrays.asList(Mark38ChargerBlock.getExtraPlacementPositions(blockPos, state)));
        posList.add(blockPos);
        for (BlockPos pos : posList) {
            level.getEntitiesOfClass(VeronicaSentry.class, new AABB(pos)).forEach(sentry -> {
                if (!list.contains(sentry)) {
                    list.add(sentry);
                }
            });
        }
        for (VeronicaSentry sentry : list) {
            if (state.getValue(SuitChargerBlock.POWERED)) {
                sentry.setCharging(true);
                sentry.getArmorSlots().forEach(stack -> EnergySuitItem.addEnergy(stack, 0.01f));
                sentry.setYRot(state.getValue(Mark38ChargerBlock.FACING).toYRot());
            } else {
                sentry.setCharging(false);
            }
        }
    }

    public void setExtraBlocks(Map<ChargerPart, BlockPos> extraBlocks) {
        this.extraBlocks = extraBlocks;
    }

    public Map<ChargerPart, BlockPos> getExtraBlocks() {
        return extraBlocks;
    }
}
