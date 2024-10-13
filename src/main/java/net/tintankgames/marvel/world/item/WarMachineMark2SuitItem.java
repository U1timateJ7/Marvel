package net.tintankgames.marvel.world.item;

import net.minecraft.network.chat.Component;

import java.util.List;

public class WarMachineMark2SuitItem extends WarMachineSuitItem {
    public WarMachineMark2SuitItem(Type type, Properties properties) {
        super(MarvelArmorMaterials.IRON_MAN_DIAMOND, type, MarvelItems.Tags.WAR_MACHINE_MARK_2_ARMOR, List.of(), List.of(MarvelItems.REPULSOR.get(), MarvelItems.UNIBEAM.get(), MarvelItems.SHOULDER_TURRET.get()), properties);
    }

    @Override
    public Component mark() {
        return Component.translatable("container.suit_charger.mark_2");
    }

    @Override
    public Component hudMark() {
        return Component.translatable("gui.iron_man.mark_2");
    }

    @Override
    public Component sentryName() {
        return Component.translatable("entity.marvel.war_machine_sentry.mark_2");
    }

    @Override
    public int markNumber() {
        return 0x10000;
    }

    @Override
    public Component veronicaName() {
        return Component.translatable("gui.veronica.war_machine_mark_2");
    }
}
