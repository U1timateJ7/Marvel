package net.tintankgames.marvel.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tintankgames.marvel.client.input.MarvelKeyMappings;

import java.util.List;

public abstract class SentryIronManSuitItem extends IronManSuitItem {
    public SentryIronManSuitItem(Holder<ArmorMaterial> armorMaterial, Type type, TagKey<Item> tagKey, List<MobEffectInstance> list, List<Item> powerItems, Properties properties) {
        super(armorMaterial, type, tagKey, list, powerItems, properties);
    }

    @Override
    protected void addAbilityMessage(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flags) {
        list.add(Component.translatable(BuiltInRegistries.ITEM.getKey(this).withPath("iron_man.key.c").toLanguageKey("item"), Component.keybind(MarvelKeyMappings.SECONDARY_SUIT_ABILITY.getName()).withStyle(ChatFormatting.BOLD)).withStyle(ChatFormatting.GRAY));
    }

    public Component sentryName() {
        return Component.empty();
    }
}
