package net.tintankgames.marvel.world.item;

import io.github.cottonmc.cotton.gui.widget.data.Texture;
import io.github.cottonmc.cotton.gui.widget.icon.Icon;
import io.github.cottonmc.cotton.gui.widget.icon.LayeredTextureIcon;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tintankgames.marvel.client.input.MarvelKeyMappings;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;
import net.tintankgames.marvel.world.entity.VeronicaSentry;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class SentryIronManSuitItem extends IronManSuitItem implements VeronicaSuit {
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

    public EntityType<? extends VeronicaSentry> type() {
        return MarvelEntityTypes.IRON_MAN_SENTRY.get();
    }

    @Nullable
    public Icon createIcon(ItemStack stack, Player player) {
        ResourceLocation icon = getArmorTexture(stack, player, EquipmentSlot.HEAD, getMaterial().value().layers().getFirst(), false);
        return icon != null ? new LayeredTextureIcon(new Texture(icon, 0.125F, 0.125F, 0.25F, 0.25F), new Texture(icon.withPath(id -> id.replace(".png", "_glow.png")), 0.125F, 0.125F, 0.25F, 0.25F)) : null;
    }
}
