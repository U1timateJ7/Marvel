package net.tintankgames.marvel.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tintankgames.marvel.client.input.MarvelKeyMappings;

import java.util.List;

public class CaptainCarterSuitItem extends CaptainAmericaSuitItem {
    public CaptainCarterSuitItem(Type type, Properties properties) {
        super(type, properties);
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_339594_, p_41423_, p_41424_);
        if (type == Type.HELMET) p_41423_.add(Component.translatable(getDescriptionId(p_41421_).replace("_helmet", "") + ".key.h", Component.keybind(MarvelKeyMappings.TOGGLE_HELMET.getName()).withStyle(ChatFormatting.BOLD)).withStyle(ChatFormatting.GRAY));
    }
}
