package net.tintankgames.marvel.world.item;

import net.minecraft.network.chat.Component;

import java.util.List;

public class IronManMark2SuitItem extends IronManSuitItem {
    public IronManMark2SuitItem(Type type, Properties properties) {
        super(MarvelArmorMaterials.IRON_MAN_IRON, type, MarvelItems.Tags.IRON_MAN_MARK_2_ARMOR, List.of(), List.of(MarvelItems.REPULSOR.get()), properties);
    }

    @Override
    protected double getFlightMax() {
        return 128.0F;
    }

    @Override
    public Component mark() {
        return Component.translatable("container.suit_charger.mark_2");
    }

    @Override
    public Component hudMark() {
        return Component.translatable("gui.iron_man.mark_2");
    }
}
