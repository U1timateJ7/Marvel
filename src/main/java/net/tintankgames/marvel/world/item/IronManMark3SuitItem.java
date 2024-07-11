package net.tintankgames.marvel.world.item;

import net.minecraft.network.chat.Component;

import java.util.List;

public class IronManMark3SuitItem extends IronManSuitItem {
    public IronManMark3SuitItem(Type type, Properties properties) {
        super(MarvelArmorMaterials.IRON_MAN_IRON, type, MarvelItems.Tags.IRON_MAN_MARK_3_ARMOR, List.of(), List.of(MarvelItems.REPULSOR.get(), MarvelItems.UNIBEAM.get()), properties);
    }

    @Override
    public Component mark() {
        return Component.translatable("container.suit_charger.mark_3");
    }
}
