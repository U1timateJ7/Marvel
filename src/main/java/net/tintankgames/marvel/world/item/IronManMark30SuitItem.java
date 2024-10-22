package net.tintankgames.marvel.world.item;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.tintankgames.marvel.client.model.MarvelModels;

import java.util.List;

public class IronManMark30SuitItem extends SentryIronManSuitItem {
    public IronManMark30SuitItem(Type type, Properties properties) {
        super(MarvelArmorMaterials.IRON_MAN_IRON_DIAMOND, type, MarvelItems.Tags.IRON_MAN_MARK_30_ARMOR, List.of(), List.of(MarvelItems.REPULSOR.get(), MarvelItems.UNIBEAM.get(), MarvelItems.CENTURION_BLADE.get()), properties);
    }

    @Override
    public ModelLayerLocation modelFactory(Type type, ItemStack itemStack) {
        return MarvelModels.ironManCenturionSuit(type);
    }

    @Override
    public Component mark() {
        return Component.translatable("container.suit_charger.mark_30");
    }

    @Override
    public Component hudMark() {
        return Component.translatable("gui.iron_man.mark_30");
    }

    @Override
    public Component sentryName() {
        return Component.translatable("entity.marvel.iron_man_sentry.mark_30");
    }

    @Override
    public int markNumber() {
        return 30;
    }

    @Override
    public Component veronicaName() {
        return Component.translatable("gui.veronica.iron_man_mark_30");
    }
}
