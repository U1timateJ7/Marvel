package net.tintankgames.marvel.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tintankgames.marvel.core.components.MarvelDataComponents;

import java.util.List;
import java.util.function.Predicate;

public abstract class EnergySuitItem extends SuitItem implements SuitChargerItem {
    public EnergySuitItem(Holder<ArmorMaterial> material, Type type, TagKey<Item> suitPieces, List<MobEffectInstance> effects, Properties properties) {
        super(material, type, suitPieces, effects, properties.component(MarvelDataComponents.ENERGY, 100.0F));
    }

    public EnergySuitItem(Holder<ArmorMaterial> material, Type type, Predicate<Holder<Item>> suitPieces, List<MobEffectInstance> effects, Properties properties) {
        super(material, type, suitPieces, effects, properties.component(MarvelDataComponents.ENERGY, 100.0F));
    }

    @Override
    protected void fullSuitTick(ItemStack stack, Level level, Player player) {
        super.fullSuitTick(stack, level, player);
        if (!player.isCreative() && !player.isSpectator() && getEnergy(stack) > 0.0F) removeEnergy(stack, 2.0F / 60.0F / (player.getAbilities().flying ? 1.0F : 2.0F) / 20.0F);
    }

    public static float getEnergy(ItemStack stack) {
        return stack.getOrDefault(MarvelDataComponents.ENERGY, 0.0F);
    }

    public static void setEnergy(ItemStack stack, float power) {
        stack.set(MarvelDataComponents.ENERGY, Mth.clamp(power, 0.0F, 100.0F));
    }

    public static void addEnergy(ItemStack stack, float power) {
        setEnergy(stack, getEnergy(stack) + (power / (stack.is(MarvelItems.Tags.IRON_MAN_MARK_20_ARMOR) ? 2 : 1)));
    }

    public static void removeEnergy(ItemStack stack, float power) {
        setEnergy(stack, getEnergy(stack) - (power / (stack.is(MarvelItems.Tags.IRON_MAN_MARK_20_ARMOR) ? 2 : 1)));
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_339594_, p_41423_, p_41424_);
        p_41423_.add(Component.translatable("item.suit.energy", String.format("%.1f", p_41421_.getOrDefault(MarvelDataComponents.ENERGY, 0.0F)), "%").withStyle(ChatFormatting.GRAY));
    }
}
