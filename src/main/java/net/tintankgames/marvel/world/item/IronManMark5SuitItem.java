package net.tintankgames.marvel.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tintankgames.marvel.client.input.MarvelKeyMappings;

import java.util.List;

public class IronManMark5SuitItem extends IronManSuitItem {
    public IronManMark5SuitItem(Type type, Properties properties) {
        super(MarvelArmorMaterials.IRON_MAN_IRON, type, MarvelItems.Tags.IRON_MAN_MARK_5_ARMOR, List.of(), List.of(MarvelItems.REPULSOR.get(), MarvelItems.UNIBEAM.get()), properties);
    }

    @Override
    protected void addAbilityMessage(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flags) {
        list.add(Component.translatable(getDescriptionId(stack).replace("_helmet", "_mark_5") + ".key.c", Component.keybind(MarvelKeyMappings.SECONDARY_SUIT_ABILITY.getName()).withStyle(ChatFormatting.BOLD)).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public Component mark() {
        return Component.translatable("container.suit_charger.mark_5");
    }

    @Override
    public Component hudMark() {
        return Component.translatable("gui.iron_man.mark_5");
    }
}
