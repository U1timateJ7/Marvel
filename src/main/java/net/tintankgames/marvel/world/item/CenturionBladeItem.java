package net.tintankgames.marvel.world.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class CenturionBladeItem extends SuitPowerItem {
    public CenturionBladeItem(Properties properties) {
        super(properties.component(DataComponents.TOOL, new Tool(List.of(), 8.0F, 0)).attributes(SwordItem.createAttributes(MarvelTiers.CENTURION_BLADE, 2, -2.2F)));
    }

    @Override
    public boolean canAttackBlock(BlockState p_43291_, Level p_43292_, BlockPos p_43293_, Player p_43294_) {
        return !p_43294_.isCreative();
    }
}
