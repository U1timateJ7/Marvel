package net.tintankgames.marvel.world.item;

import com.google.common.base.Suppliers;
import net.minecraft.Util;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.tintankgames.marvel.client.model.MarvelModels;

import java.util.EnumMap;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

public class BlackPantherSuitItem extends SuitItem {
    private static final EnumMap<Type, UUID> ARMOR_MODIFIER_UUID_PER_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), p_323375_ -> {
        p_323375_.put(ArmorItem.Type.BOOTS, UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"));
        p_323375_.put(ArmorItem.Type.LEGGINGS, UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"));
        p_323375_.put(ArmorItem.Type.CHESTPLATE, UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"));
        p_323375_.put(ArmorItem.Type.HELMET, UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"));
        p_323375_.put(ArmorItem.Type.BODY, UUID.fromString("C1C72771-8B8E-BA4A-ACE0-81A93C8928B2"));
    });
    private final Supplier<ItemAttributeModifiers> defaultModifiers;

    public BlackPantherSuitItem(Type type, Properties properties) {
        super(MarvelArmorMaterials.VIBRANIUM_WEAVE, type, true, MarvelItems.Tags.BLACK_PANTHER_ARMOR, type == Type.CHESTPLATE ? List.of(effect(MobEffects.JUMP, 2), effect(MobEffects.MOVEMENT_SPEED, 0)) : List.of(), properties);
        this.defaultModifiers = Suppliers.memoize(
                () -> {
                    int i = MarvelArmorMaterials.VIBRANIUM_WEAVE.value().getDefense(type);
                    float f = MarvelArmorMaterials.VIBRANIUM_WEAVE.value().toughness();
                    ItemAttributeModifiers.Builder itemattributemodifiers$builder = ItemAttributeModifiers.builder();
                    EquipmentSlotGroup equipmentslotgroup = EquipmentSlotGroup.bySlot(type.getSlot());
                    UUID uuid = ARMOR_MODIFIER_UUID_PER_TYPE.get(type);
                    itemattributemodifiers$builder.add(
                            Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", i, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup
                    );
                    itemattributemodifiers$builder.add(
                            Attributes.ARMOR_TOUGHNESS,
                            new AttributeModifier(uuid, "Armor toughness", f, AttributeModifier.Operation.ADD_VALUE),
                            equipmentslotgroup
                    );
                    float f1 = MarvelArmorMaterials.VIBRANIUM_WEAVE.value().knockbackResistance();
                    if (f1 > 0.0F) {
                        itemattributemodifiers$builder.add(
                                Attributes.KNOCKBACK_RESISTANCE,
                                new AttributeModifier(uuid, "Armor knockback resistance", f1, AttributeModifier.Operation.ADD_VALUE),
                                equipmentslotgroup
                        );
                    }
                    if (type == Type.CHESTPLATE) {
                        itemattributemodifiers$builder.add(
                                Attributes.ATTACK_DAMAGE,
                                new AttributeModifier(uuid, "Armor attack damage", 2, AttributeModifier.Operation.ADD_VALUE),
                                equipmentslotgroup
                        );
                    }

                    return itemattributemodifiers$builder.build();
                }
        );
    }

    @Override
    protected ModelLayerLocation modelFactory(Type type, ItemStack itemStack) {
        return MarvelModels.pantherSuit(type);
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers() {
        return defaultModifiers.get();
    }
}
