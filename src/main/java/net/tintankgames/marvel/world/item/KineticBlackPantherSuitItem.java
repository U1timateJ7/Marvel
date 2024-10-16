package net.tintankgames.marvel.world.item;

import com.google.common.base.Suppliers;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.tintankgames.marvel.client.input.MarvelKeyMappings;
import net.tintankgames.marvel.client.model.MarvelModels;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import org.jetbrains.annotations.Nullable;
import org.joml.Math;

import java.util.EnumMap;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

@EventBusSubscriber
public class KineticBlackPantherSuitItem extends SuitItem {
    private static final EnumMap<Type, UUID> ARMOR_MODIFIER_UUID_PER_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), p_323375_ -> {
        p_323375_.put(ArmorItem.Type.BOOTS, UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"));
        p_323375_.put(ArmorItem.Type.LEGGINGS, UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"));
        p_323375_.put(ArmorItem.Type.CHESTPLATE, UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"));
        p_323375_.put(ArmorItem.Type.HELMET, UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"));
        p_323375_.put(ArmorItem.Type.BODY, UUID.fromString("C1C72771-8B8E-BA4A-ACE0-81A93C8928B2"));
    });
    private final Supplier<ItemAttributeModifiers> defaultModifiers;
    private final int color;

    public KineticBlackPantherSuitItem(int color, Type type, Properties properties) {
        super(MarvelArmorMaterials.VIBRANIUM_NANITE, type, MarvelItems.Tags.KINETIC_BLACK_PANTHER_ARMOR, type == Type.CHESTPLATE ? List.of(effect(MobEffects.JUMP, 2), effect(MobEffects.MOVEMENT_SPEED, 0)) : List.of(), properties.component(MarvelDataComponents.ABSORBED_DAMAGE, 0.0F));
        this.color = color;
        this.defaultModifiers = Suppliers.memoize(
                () -> {
                    int i = MarvelArmorMaterials.VIBRANIUM_NANITE.value().getDefense(type);
                    float f = MarvelArmorMaterials.VIBRANIUM_NANITE.value().toughness();
                    ItemAttributeModifiers.Builder itemattributemodifiers$builder = ItemAttributeModifiers.builder();
                    EquipmentSlotGroup equipmentslotgroup = EquipmentSlotGroup.bySlot(type.getSlot());
                    ResourceLocation resourcelocation = ResourceLocation.withDefaultNamespace("armor." + type.getName());
                    itemattributemodifiers$builder.add(Attributes.ARMOR, new AttributeModifier(resourcelocation, i, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup);
                    itemattributemodifiers$builder.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(resourcelocation, f, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup);
                    float f1 = MarvelArmorMaterials.VIBRANIUM_NANITE.value().knockbackResistance();
                    if (f1 > 0.0F) {
                        itemattributemodifiers$builder.add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(resourcelocation, f1, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup);
                    }
                    if (type == Type.CHESTPLATE) {
                        itemattributemodifiers$builder.add(Attributes.ATTACK_DAMAGE, new AttributeModifier(resourcelocation, 3, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup);
                    }

                    return itemattributemodifiers$builder.build();
                }
        );
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return stack.getOrDefault(MarvelDataComponents.ABSORBED_DAMAGE, 0.0F) > 0.0F;
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return color;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.round(stack.getOrDefault(MarvelDataComponents.ABSORBED_DAMAGE, 0.0F) * 13.0F / 25.0F);
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, @Nullable T entity, Consumer<Item> onBroken) {
        return 0;
    }

    @SubscribeEvent
    public static void playerHurt(LivingDamageEvent.Post event) {
        if (event.getEntity() instanceof ServerPlayer player && event.getSource().getEntity() != null) {
            float absorbed = event.getNewDamage() / 2.0F;
            player.getInventory().armor.forEach(stack -> {
                if (stack.has(MarvelDataComponents.ABSORBED_DAMAGE)) {
                    stack.update(MarvelDataComponents.ABSORBED_DAMAGE, 0.0F, damage -> Math.min(damage + absorbed, 25.0F));
                }
            });
        }
    }

    @SubscribeEvent
    public static void arrowImmunity(LivingIncomingDamageEvent event) {
        if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).is(MarvelItems.Tags.KINETIC_BLACK_PANTHER_ARMOR) && event.getEntity().getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.KINETIC_BLACK_PANTHER_ARMOR) && event.getEntity().getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.KINETIC_BLACK_PANTHER_ARMOR) && event.getEntity().getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.KINETIC_BLACK_PANTHER_ARMOR) && event.getSource().getDirectEntity() != null && event.getSource().getDirectEntity().getType().is(EntityTypeTags.ARROWS) && event.getEntity().getRandom().nextInt(5) < 3) {
            event.setCanceled(true);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ModelLayerLocation modelFactory(Type type, ItemStack itemStack) {
        return MarvelModels.pantherSuit(type);
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers() {
        return defaultModifiers.get();
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_339594_, p_41423_, p_41424_);
        if (type == Type.HELMET) {
            p_41423_.add(Component.translatable(getDescriptionId(p_41421_).replace("_helmet", "") + ".key.v", Component.keybind(MarvelKeyMappings.PRIMARY_SUIT_ABILITY.getName()).withStyle(ChatFormatting.BOLD)).withStyle(ChatFormatting.GRAY));
            p_41423_.add(Component.translatable(getDescriptionId(p_41421_).replace("_helmet", "") + ".key.c", Component.keybind(MarvelKeyMappings.SECONDARY_SUIT_ABILITY.getName()).withStyle(ChatFormatting.BOLD)).withStyle(ChatFormatting.GRAY));
            p_41423_.add(Component.translatable(getDescriptionId(p_41421_).replace("_helmet", "") + ".key.h", Component.keybind(MarvelKeyMappings.TOGGLE_HELMET.getName()).withStyle(ChatFormatting.BOLD)).withStyle(ChatFormatting.GRAY));
        }
    }
}
