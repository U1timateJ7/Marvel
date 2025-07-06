package net.tintankgames.marvel.world.item;

import com.google.common.collect.Streams;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.item.component.SuitParts;

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
        if (getType() == Type.CHESTPLATE) {
            float helmetEnergy = getEnergy(player.getItemBySlot(EquipmentSlot.HEAD));
            float chestplateEnergy = getEnergy(player.getItemBySlot(EquipmentSlot.CHEST));
            float leggingsEnergy = getEnergy(player.getItemBySlot(EquipmentSlot.LEGS));
            float bootsEnergy = getEnergy(player.getItemBySlot(EquipmentSlot.FEET));
            float energy = (helmetEnergy + chestplateEnergy + leggingsEnergy + bootsEnergy) / 4;
            player.getArmorSlots().forEach(armor -> setEnergy(armor, energy));
        }
        if (!player.isCreative() && !player.isSpectator() && getEnergy(stack) > 0.0F) removeEnergy(stack, (2.0F / 60.0F / 2.0F / 20.0F) * (player.getAbilities().flying ? hasArmor(player, MarvelItems.Tags.IRON_MAN_MARK_19_ARMOR) ? 3.0F : 2.0F : 1.0F));
    }

    private static boolean hasArmor(LivingEntity living, TagKey<Item> tagKey) {
        boolean head = living.getItemBySlot(EquipmentSlot.HEAD).is(tagKey);
        boolean chest = living.getItemBySlot(EquipmentSlot.CHEST).is(tagKey);
        boolean legs = living.getItemBySlot(EquipmentSlot.LEGS).is(tagKey);
        boolean feet = living.getItemBySlot(EquipmentSlot.FEET).is(tagKey);
        boolean wearingSameArmor = true;
        if (living.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof SentryIronManSuitItem sentrySuitItem) {
            for (ItemStack armor : living.getArmorSlots()) {
                if (!(armor.getItem() instanceof SentryIronManSuitItem) || !sentrySuitItem.isSuitPiece(armor)) {
                    wearingSameArmor = false;
                    break;
                }
            }
        }
        boolean hasAllParts = Streams.stream(living.getArmorSlots()).allMatch(stack -> stack.getItem() instanceof ArmorItem armorItem && stack.getOrDefault(MarvelDataComponents.SUIT_PARTS, SuitParts.defaultParts(armorItem.getType(), true)).hasAllParts());
        return head && chest && legs && feet && wearingSameArmor && hasAllParts;
    }

    public static float getEnergy(ItemStack stack) {
        return stack.getOrDefault(MarvelDataComponents.ENERGY, 0.0F);
    }

    public static void setEnergy(ItemStack stack, float power) {
        stack.set(MarvelDataComponents.ENERGY, Mth.clamp(power, 0.0F, 100.0F));
    }

    public static void addEnergy(ItemStack stack, float power) {
        setEnergy(stack, getEnergy(stack) + (power / (stack.is(MarvelItems.Tags.IRON_MAN_MARK_20_ARMOR) ? 2 : stack.is(MarvelItems.Tags.IRON_MAN_MARK_30_ARMOR) || stack.is(MarvelItems.Tags.IRON_MAN_MARK_33_ARMOR) ? 1.5F : 1)));
    }

    public static void removeEnergy(ItemStack stack, float power) {
        setEnergy(stack, getEnergy(stack) - (power / (stack.is(MarvelItems.Tags.IRON_MAN_MARK_20_ARMOR) ? 2 : stack.is(MarvelItems.Tags.IRON_MAN_MARK_30_ARMOR) || stack.is(MarvelItems.Tags.IRON_MAN_MARK_33_ARMOR) ? 1.5F : 1)));
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_339594_, p_41423_, p_41424_);
        p_41423_.add(Component.translatable("item.suit.energy", String.format("%.1f", p_41421_.getOrDefault(MarvelDataComponents.ENERGY, 0.0F)), "%").withStyle(ChatFormatting.GRAY));
    }
}
