package net.tintankgames.marvel.world.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.tintankgames.marvel.core.components.MarvelDataComponents;

import java.util.List;

public class SuitPowerItem extends Item {
    public SuitPowerItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public int getEntityLifespan(ItemStack itemStack, Level level) {
        return 1;
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        if (!(p_41406_ instanceof Player living) || !allArmorHasThisPower(living)) p_41404_.shrink(1);
    }

    private boolean allArmorHasThisPower(LivingEntity living) {
        boolean feet = living.getItemBySlot(EquipmentSlot.FEET).getOrDefault(MarvelDataComponents.POWER_ITEMS, List.of()).contains(this);
        boolean legs = living.getItemBySlot(EquipmentSlot.LEGS).getOrDefault(MarvelDataComponents.POWER_ITEMS, List.of()).contains(this);
        boolean chest = living.getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.POWER_ITEMS, List.of()).contains(this);
        boolean head = living.getItemBySlot(EquipmentSlot.HEAD).getOrDefault(MarvelDataComponents.POWER_ITEMS, List.of()).contains(this);
        return feet && legs && chest && head;
    }

    @Override
    public boolean doesSneakBypassUse(ItemStack stack, LevelReader level, BlockPos pos, Player player) {
        return true;
    }
}
