package net.tintankgames.marvel.world.item;

import net.minecraft.network.chat.Component;

import java.util.List;

public class IronManMark19SuitItem extends SentryIronManSuitItem {
    public IronManMark19SuitItem(Type type, Properties properties) {
        super(MarvelArmorMaterials.IRON_MAN_IRON_DIAMOND, type, MarvelItems.Tags.IRON_MAN_MARK_19_ARMOR, List.of(), List.of(MarvelItems.REPULSOR.get(), MarvelItems.UNIBEAM.get()), properties);
    }

    @Override
    public Component mark() {
        return Component.translatable("container.suit_charger.mark_19");
    }

    @Override
    public Component hudMark() {
        return Component.translatable("gui.iron_man.mark_19");
    }

    @Override
    public Component sentryName() {
        return Component.translatable("entity.marvel.iron_man_sentry.mark_19");
    }
}
