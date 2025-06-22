package net.tintankgames.marvel.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tintankgames.marvel.core.components.MarvelDataComponents;

import java.util.List;

public class IronManMark38ComponentItem extends Item {
    public IronManMark38ComponentItem(Properties properties) {
        super(properties.component(MarvelDataComponents.ENERGY, 100.0F));
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> list, TooltipFlag flags) {
        super.appendHoverText(stack, context, list, flags);
        list.add(Component.translatable("item.marvel.iron_man.mark_38").withStyle(ChatFormatting.GRAY));
    }
}
