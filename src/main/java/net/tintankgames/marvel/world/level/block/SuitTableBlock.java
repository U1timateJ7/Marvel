package net.tintankgames.marvel.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.tintankgames.marvel.stats.MarvelStats;
import net.tintankgames.marvel.world.level.block.entity.SuitTableBlockEntity;
import org.jetbrains.annotations.Nullable;

public class SuitTableBlock extends BaseEntityBlock {
    public static final MapCodec<SuitTableBlock> CODEC = simpleCodec(SuitTableBlock::new);
    public static final Component UPGRADING_TITLE = Component.translatable("container.suit_upgrading");
    public static final Component VARIANTS_TITLE = Component.translatable("container.suit_variants");

    @Override
    public MapCodec<SuitTableBlock> codec() {
        return CODEC;
    }

    public SuitTableBlock(BlockBehaviour.Properties p_52225_) {
        super(p_52225_);
    }

    @Override
    protected RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState p_52233_, Level p_52234_, BlockPos p_52235_, Player p_52236_, BlockHitResult p_52238_) {
        if (p_52234_.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            MenuProvider provider = p_52233_.getMenuProvider(p_52234_, p_52235_);
            if (provider != null) {
                p_52236_.openMenu(provider);
                p_52236_.awardStat(MarvelStats.INTERACT_WITH_SUIT_TABLE.get());
            }
            return InteractionResult.CONSUME;
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new SuitTableBlockEntity(p_153215_, p_153216_);
    }
}
