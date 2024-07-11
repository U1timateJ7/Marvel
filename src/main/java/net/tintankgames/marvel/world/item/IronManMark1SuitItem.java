package net.tintankgames.marvel.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tintankgames.marvel.client.model.MarvelModels;
import net.tintankgames.marvel.core.components.MarvelDataComponents;

import java.util.List;

public class IronManMark1SuitItem extends SuitItem implements SuitChargerItem {
    public IronManMark1SuitItem(Type type, Properties properties) {
        super(MarvelArmorMaterials.IRON_MAN_IRON, type, MarvelItems.Tags.IRON_MAN_MARK_1_ARMOR, type == Type.CHESTPLATE ? List.of(effect(MobEffects.MOVEMENT_SLOWDOWN, 0), effect(MobEffects.DAMAGE_BOOST, 0)) : List.of(), properties.component(MarvelDataComponents.POWER_ITEMS, List.of(MarvelItems.FLAMETHROWER.get())));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    protected ModelLayerLocation modelFactory(Type type, ItemStack itemStack) {
        return MarvelModels.ironManMark1Suit(type);
    }

    @Override
    protected String getOrCreateDescriptionId() {
        if (this.descriptionId == null) {
            this.descriptionId = Util.makeDescriptionId("item", BuiltInRegistries.ITEM.getKey(this).withPath("iron_man_" + type.getName()));
        }
        return this.descriptionId;
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_339594_, p_41423_, p_41424_);
        p_41423_.add(Component.translatable("item.marvel.iron_man.mark_1").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public Component mark() {
        return Component.translatable("container.suit_charger.mark_1");
    }
}
