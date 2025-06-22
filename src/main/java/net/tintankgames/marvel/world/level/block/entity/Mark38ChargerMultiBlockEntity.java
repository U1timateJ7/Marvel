package net.tintankgames.marvel.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.tintankgames.marvel.world.level.block.Mark38ChargerBlock;
import net.tintankgames.marvel.world.level.block.Mark38ChargerMultiBlock;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;
import org.jetbrains.annotations.Nullable;

public class Mark38ChargerMultiBlockEntity extends BlockEntity {
    public BlockPos mainBlock = BlockPos.ZERO;
    public boolean shouldBeDestroyed = false;

    public Mark38ChargerMultiBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MarvelBlockEntityTypes.MARK_38_CHARGER_PART.get(), blockPos, blockState);
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.saveAdditional(compoundTag, provider);
        compoundTag.putIntArray("main", new int[] {mainBlock.getX(), mainBlock.getY(), mainBlock.getZ()});
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

    @Override
    public void loadAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.loadAdditional(compoundTag, provider);
        int[] pos = compoundTag.getIntArray("main");
        mainBlock = new BlockPos(pos[0], pos[1], pos[2]);
    }

    private int tickCount = 0;

    public static void tick(Level level, BlockPos blockPos, BlockState state, Mark38ChargerMultiBlockEntity mark38ChargerMultiBlockEntity) {
        if (mark38ChargerMultiBlockEntity.tickCount > 3) {
            boolean bl = false;
            for (BlockPos pos : Mark38ChargerBlock.getExtraPlacementPositions(blockPos, state.getValue(Mark38ChargerBlock.FACING), state.getValue(Mark38ChargerMultiBlock.PART))) {
                if (!level.getBlockState(pos).is(MarvelBlocks.MARK_38_CHARGER_PART) && !level.getBlockState(pos).is(MarvelBlocks.MARK_38_CHARGER)) {
                    bl = true;
                    break;
                }
            }
            if (bl) {
                mark38ChargerMultiBlockEntity.shouldBeDestroyed = true;
            }

            boolean bl2 = false;
            for (BlockPos pos : Mark38ChargerBlock.getExtraPlacementPositions(blockPos, state.getValue(Mark38ChargerBlock.FACING), state.getValue(Mark38ChargerMultiBlock.PART))) {
                boolean bl3 = level.getBlockEntity(pos, MarvelBlockEntityTypes.MARK_38_CHARGER.get()).isPresent();
                boolean bl4 = level.getBlockEntity(pos, MarvelBlockEntityTypes.MARK_38_CHARGER_PART.get()).isPresent();
                if (bl3) {
                    if (level.getBlockEntity(pos, MarvelBlockEntityTypes.MARK_38_CHARGER.get()).get().shouldBeDestroyed) {
                        bl2 = true;
                        break;
                    }
                }
                if (bl4) {
                    if (level.getBlockEntity(pos, MarvelBlockEntityTypes.MARK_38_CHARGER_PART.get()).get().shouldBeDestroyed) {
                        bl2 = true;
                        break;
                    }
                }
            }
            if (bl2 || mark38ChargerMultiBlockEntity.shouldBeDestroyed) {
                level.destroyBlock(blockPos, true);
            }
        }
        mark38ChargerMultiBlockEntity.tickCount++;
    }
}
