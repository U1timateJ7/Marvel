package net.tintankgames.marvel.world.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.item.component.SuitPartItem;
import net.tintankgames.marvel.world.item.component.SuitParts;

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
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int p_41407_, boolean p_41408_) {
        if (!(entity instanceof Player player) || (!allArmorHasThisPower(player, needsHead()) && !anyArmorHasThisPartPower(player, stack))) stack.shrink(1);
    }

    protected boolean needsHead() {
        return true;
    }

    private boolean allArmorHasThisPower(Player player, boolean needsHead) {
        boolean feet = player.getItemBySlot(EquipmentSlot.FEET).getOrDefault(MarvelDataComponents.POWER_ITEMS, List.of()).contains(this);
        boolean legs = player.getItemBySlot(EquipmentSlot.LEGS).getOrDefault(MarvelDataComponents.POWER_ITEMS, List.of()).contains(this);
        boolean chest = player.getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.POWER_ITEMS, List.of()).contains(this);
        boolean head = player.getItemBySlot(EquipmentSlot.HEAD).getOrDefault(MarvelDataComponents.POWER_ITEMS, List.of()).contains(this) || !needsHead;
        return feet && legs && chest && head;
    }

    private boolean anyArmorHasThisPartPower(Player player, ItemStack power) {
        boolean usingPower = false;
        for (ItemStack stack : player.getArmorSlots()) {
            if (stack.getItem() instanceof SummonableIronManSuitItem item) {
                for (SuitPartItem suitPartItem : item.partPowerItems.stream().filter(partItem -> ItemStack.isSameItem(partItem.stack(), power) && item.getType() == partItem.piece()).toList()) {
                    if (stack.getOrDefault(MarvelDataComponents.SUIT_PARTS, SuitParts.defaultParts(item.getType(), true)).parts().get(suitPartItem.part())) {
                        usingPower = true;
                        break;
                    }
                }
            }
        }
        return usingPower;
    }

    @Override
    public boolean doesSneakBypassUse(ItemStack stack, LevelReader level, BlockPos pos, Player player) {
        return true;
    }
}
