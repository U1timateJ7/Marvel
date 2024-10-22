package net.tintankgames.marvel.world.item;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.tintankgames.marvel.client.model.MarvelModels;
import net.tintankgames.marvel.core.components.MarvelDataComponents;

import java.util.List;

public class IronManMark25SuitItem extends SentryIronManSuitItem {
    public IronManMark25SuitItem(Type type, Properties properties) {
        super(MarvelArmorMaterials.IRON_MAN_IRON_DIAMOND, type, MarvelItems.Tags.IRON_MAN_MARK_25_ARMOR, List.of(), List.of(MarvelItems.REPULSOR.get(), MarvelItems.UNIBEAM.get(), MarvelItems.MINING_DRILL.get()), type == Type.CHESTPLATE ? properties.component(MarvelDataComponents.SINGLE_BLOCK, false) : properties);
    }

    @Override
    public ModelLayerLocation modelFactory(Type type, ItemStack itemStack) {
        return MarvelModels.ironManMark25Suit(type);
    }

    @Override
    public Component mark() {
        return Component.translatable("container.suit_charger.mark_25");
    }

    @Override
    public Component hudMark() {
        return Component.translatable("gui.iron_man.mark_25");
    }

    @Override
    public Component sentryName() {
        return Component.translatable("entity.marvel.iron_man_sentry.mark_25");
    }

    @Override
    public int markNumber() {
        return 25;
    }

    @Override
    public Component veronicaName() {
        return Component.translatable("gui.veronica.iron_man_mark_25");
    }
}
