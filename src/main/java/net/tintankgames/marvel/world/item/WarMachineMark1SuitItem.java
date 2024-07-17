package net.tintankgames.marvel.world.item;

import net.minecraft.network.chat.Component;

import java.util.List;

public class WarMachineMark1SuitItem extends WarMachineSuitItem {
    public WarMachineMark1SuitItem(Type type, Properties properties) {
        super(MarvelArmorMaterials.IRON_MAN_IRON_DIAMOND, type, MarvelItems.Tags.WAR_MACHINE_MARK_1_ARMOR, List.of(), List.of(MarvelItems.REPULSOR.get(), MarvelItems.UNIBEAM.get(), MarvelItems.SHOULDER_TURRET.get()), properties);
    }

    @Override
    public Component mark() {
        return Component.translatable("container.suit_charger.mark_1");
    }

    @Override
    public Component hudMark() {
        return Component.translatable("gui.iron_man.mark_1");
    }
}
