package net.tintankgames.marvel.world.item;

import net.minecraft.Util;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.tintankgames.marvel.client.model.MarvelModels;

import java.util.List;

public class WarMachineMark1SuitItem extends IronManSuitItem {
    public WarMachineMark1SuitItem(Type type, Properties properties) {
        super(MarvelArmorMaterials.IRON_MAN_IRON_DIAMOND, type, MarvelItems.Tags.WAR_MACHINE_MARK_1_ARMOR, List.of(), List.of(MarvelItems.REPULSOR.get(), MarvelItems.UNIBEAM.get(), MarvelItems.SHOULDER_TURRET.get()), properties);
    }

    @Override
    public ModelLayerLocation modelFactory(Type type, ItemStack itemStack) {
        return MarvelModels.warMachineSuit(type);
    }

    @Override
    public int hudColor(ItemStack stack, Player player) {
        return 0xE54F44;
    }

    @Override
    protected String getOrCreateDescriptionId() {
        if (this.descriptionId == null) {
            this.descriptionId = Util.makeDescriptionId("item", BuiltInRegistries.ITEM.getKey(this).withPath("war_machine_" + type.getName()));
        }
        return this.descriptionId;
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
