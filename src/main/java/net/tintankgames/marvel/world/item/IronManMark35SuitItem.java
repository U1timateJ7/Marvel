package net.tintankgames.marvel.world.item;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tintankgames.marvel.client.model.MarvelModels;

import java.util.List;

public class IronManMark35SuitItem extends SentryIronManSuitItem {
    public IronManMark35SuitItem(Type type, Properties properties) {
        super(MarvelArmorMaterials.IRON_MAN_IRON_DIAMOND, type, MarvelItems.Tags.IRON_MAN_MARK_35_ARMOR, List.of(), List.of(MarvelItems.REPULSOR.get(), MarvelItems.UNIBEAM.get(), MarvelItems.DISASTER_RESCUE_CLAWS.get()), properties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ModelLayerLocation modelFactory(Type type, ItemStack itemStack) {
        return MarvelModels.ironManMark35Suit(type);
    }

    @Override
    public Component mark() {
        return Component.translatable("container.suit_charger.mark_35");
    }

    @Override
    public Component hudMark() {
        return Component.translatable("gui.iron_man.mark_35");
    }

    @Override
    public Component sentryName() {
        return Component.translatable("entity.marvel.iron_man_sentry.mark_35");
    }

    @Override
    public int markNumber() {
        return 35;
    }

    @Override
    public Component veronicaName() {
        return Component.translatable("gui.veronica.iron_man_mark_35");
    }
}
