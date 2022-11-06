package com.ulto.marvel.world.item;

import com.ulto.marvel.common.MarvelMod;
import net.minecraft.Util;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class IronManSuitItem extends ArmorItem implements GlowingArmor {
    private final float batteryLossRate;
    private final float flyingBatteryLossRate;

    public IronManSuitItem(ArmorMaterial material, EquipmentSlot slot, Properties properties) {
        this(0.01666666666f, 0.03333333333f, material, slot, properties);
    }

    public IronManSuitItem(float batteryLossRate, float flyingBatteryLossRate, ArmorMaterial material, EquipmentSlot slot, Properties properties) {
        super(material, slot, properties);
        this.batteryLossRate = batteryLossRate;
        this.flyingBatteryLossRate = flyingBatteryLossRate;
    }

    @Override
    protected String getOrCreateDescriptionId() {
        if (this.descriptionId == null) {
            this.descriptionId = Util.makeDescriptionId("item", new ResourceLocation(MarvelMod.MOD_ID, "iron_man_" + getArmorSuffix(getSlot())));
        }

        return this.descriptionId;
    }

    private String getArmorSuffix(EquipmentSlot slot) {
        return switch (slot) {
            case FEET -> "boots";
            case LEGS -> "leggings";
            case CHEST -> "chestplate";
            case HEAD -> "helmet";
            default -> "";
        };
    }

    @Override
    public void fillItemCategory(CreativeModeTab p_41391_, NonNullList<ItemStack> p_41392_) {
        if (this.allowdedIn(p_41391_)) {
            p_41392_.add(getDefaultInstance());
        }
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = new ItemStack(this);
        stack.getOrCreateTag().putFloat("Battery", 100);
        return stack;
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        stack.getOrCreateTag().putFloat("Battery", 100);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (!stack.getOrCreateTag().contains("Battery", 99)) stack.getOrCreateTag().putFloat("Battery", 100);
        if (!player.isCreative() && !player.isSpectator()) removeBattery(stack, player.getAbilities().flying && !(this instanceof IronManMark20Item) ? flyingBatteryLossRate / 20 : batteryLossRate / 20);
        if (getBattery(stack) <= 0) onBatteryRunOut(stack, level, player, getSlot());
    }

    protected void onBatteryRunOut(ItemStack stack, Level level, Entity entity, EquipmentSlot slot) {
    }

    public static float getBattery(ItemStack stack) {
        return stack.getOrCreateTag().getFloat("Battery");
    }

    public static void setBattery(ItemStack stack, float battery) {
        stack.getOrCreateTag().putFloat("Battery", Mth.clamp(battery ,0f, 100f));
    }

    public static void addBattery(ItemStack stack, float battery) {
        setBattery(stack, getBattery(stack) + battery);
    }

    public static void removeBattery(ItemStack stack, float battery) {
        addBattery(stack, -battery);
    }

    public static float getBatteryOfEntity(LivingEntity entity, EquipmentSlot slot) {
        return entity instanceof Player player && player.isCreative() ? 100 : getBattery(entity.getItemBySlot(slot));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        super.appendHoverText(stack, level, components, flag);
        if (getBattery(stack) > 0) components.add(new TranslatableComponent("item.marvel.iron_man.battery_left", String.format("%.1f", getBattery(stack)), '%'));
        else components.add(new TranslatableComponent("item.marvel.iron_man.no_battery"));
    }

    public boolean isNanotech() {
        return false;
    }
}
