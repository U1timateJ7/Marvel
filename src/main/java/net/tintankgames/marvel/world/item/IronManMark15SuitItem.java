package net.tintankgames.marvel.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tintankgames.marvel.client.input.MarvelKeyMappings;

import java.util.List;

public class IronManMark15SuitItem extends SentryIronManSuitItem {
    public IronManMark15SuitItem(Type type, Properties properties) {
        super(MarvelArmorMaterials.IRON_MAN_IRON_DIAMOND, type, MarvelItems.Tags.IRON_MAN_MARK_15_ARMOR, List.of(), List.of(MarvelItems.REPULSOR.get(), MarvelItems.UNIBEAM.get()), properties);
    }

    @Override
    protected void addAbilityMessage(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flags) {
        list.add(Component.translatable(getDescriptionId(stack).replace("_helmet", "_mark_15") + ".key.v", Component.keybind(MarvelKeyMappings.PRIMARY_SUIT_ABILITY.getName()).withStyle(ChatFormatting.BOLD)).withStyle(ChatFormatting.GRAY));
        super.addAbilityMessage(stack, context, list, flags);
    }

    @Override
    public Component mark() {
        return Component.translatable("container.suit_charger.mark_15");
    }

    @Override
    public Component hudMark() {
        return Component.translatable("gui.iron_man.mark_15");
    }

    @Override
    public Component sentryName() {
        return Component.translatable("entity.marvel.iron_man_sentry.mark_15");
    }

    @Override
    public int markNumber() {
        return 15;
    }

    @Override
    public Component veronicaName() {
        return Component.translatable("gui.veronica.iron_man_mark_15");
    }
}
