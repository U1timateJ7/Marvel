package net.tintankgames.marvel.world.item;

import net.minecraft.Util;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.tintankgames.marvel.client.model.MarvelModels;

import java.util.List;

public abstract class WarMachineSuitItem extends SentryIronManSuitItem {
    public WarMachineSuitItem(Holder<ArmorMaterial> armorMaterial, Type type, TagKey<Item> tagKey, List<MobEffectInstance> list, List<Item> powerItems, Properties properties) {
        super(armorMaterial, type, tagKey, list, powerItems, properties);
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
}
