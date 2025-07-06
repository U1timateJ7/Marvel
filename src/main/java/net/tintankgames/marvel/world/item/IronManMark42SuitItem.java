package net.tintankgames.marvel.world.item;

import net.minecraft.network.chat.Component;
import net.tintankgames.marvel.world.item.component.SuitPartItem;

import java.util.List;

public class IronManMark42SuitItem extends SummonableIronManSuitItem {
    public IronManMark42SuitItem(Type type, Properties properties) {
        super(MarvelArmorMaterials.IRON_MAN_IRON_DIAMOND, type, MarvelItems.Tags.IRON_MAN_MARK_42_ARMOR, List.of(), List.of(new SuitPartItem(Type.CHESTPLATE, 3, MarvelItems.REPULSOR.toStack()), new SuitPartItem(Type.CHESTPLATE, 5, MarvelItems.REPULSOR.toStack()), new SuitPartItem(Type.CHESTPLATE, 0, MarvelItems.UNIBEAM.toStack())), properties);
    }

    @Override
    public Component mark() {
        return Component.translatable("container.suit_charger.mark_42");
    }

    @Override
    public Component hudMark() {
        return Component.translatable("gui.iron_man.mark_42");
    }

    @Override
    public Component sentryName() {
        return Component.translatable("entity.marvel.iron_man_sentry.mark_42");
    }

    @Override
    public int markNumber() {
        return 42;
    }

    @Override
    public Component veronicaName() {
        return Component.translatable("gui.veronica.iron_man_mark_42");
    }
}
