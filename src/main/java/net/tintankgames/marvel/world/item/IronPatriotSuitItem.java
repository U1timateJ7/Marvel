package net.tintankgames.marvel.world.item;

import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;

public class IronPatriotSuitItem extends WarMachineMark2SuitItem {
    public IronPatriotSuitItem(Type type, Properties properties) {
        super(type, properties);
    }

    @Override
    protected String getOrCreateDescriptionId() {
        if (this.descriptionId == null) {
            this.descriptionId = Util.makeDescriptionId("item", BuiltInRegistries.ITEM.getKey(this));
        }
        return this.descriptionId;
    }

    @Override
    public Component sentryName() {
        return Component.translatable("entity.marvel.iron_patriot_sentry");
    }

    @Override
    public int markNumber() {
        return 0x10001;
    }

    @Override
    public Component veronicaName() {
        return Component.translatable("gui.veronica.iron_patriot");
    }
}
