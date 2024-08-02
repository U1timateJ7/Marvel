package net.tintankgames.marvel.world.item;

import com.google.common.base.Suppliers;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tintankgames.marvel.client.model.MarvelModels;

import java.util.List;
import java.util.function.Supplier;

public class BlackPantherSuitItem extends SuitItem {
    private final Supplier<ItemAttributeModifiers> defaultModifiers;

    public BlackPantherSuitItem(Type type, Properties properties) {
        super(MarvelArmorMaterials.VIBRANIUM_WEAVE, type, MarvelItems.Tags.BLACK_PANTHER_ARMOR, type == Type.CHESTPLATE ? List.of(effect(MobEffects.JUMP, 2), effect(MobEffects.MOVEMENT_SPEED, 0)) : List.of(), properties);
        this.defaultModifiers = Suppliers.memoize(
                () -> {
                    int i = MarvelArmorMaterials.VIBRANIUM_WEAVE.value().getDefense(type);
                    float f = MarvelArmorMaterials.VIBRANIUM_WEAVE.value().toughness();
                    ItemAttributeModifiers.Builder itemattributemodifiers$builder = ItemAttributeModifiers.builder();
                    EquipmentSlotGroup equipmentslotgroup = EquipmentSlotGroup.bySlot(type.getSlot());
                    ResourceLocation resourcelocation = ResourceLocation.withDefaultNamespace("armor." + type.getName());
                    itemattributemodifiers$builder.add(Attributes.ARMOR, new AttributeModifier(resourcelocation, i, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup);
                    itemattributemodifiers$builder.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(resourcelocation, f, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup);
                    float f1 = MarvelArmorMaterials.VIBRANIUM_WEAVE.value().knockbackResistance();
                    if (f1 > 0.0F) {
                        itemattributemodifiers$builder.add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(resourcelocation, f1, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup);
                    }
                    if (type == Type.CHESTPLATE) {
                        itemattributemodifiers$builder.add(Attributes.ATTACK_DAMAGE, new AttributeModifier(resourcelocation, 2, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup);
                    }

                    return itemattributemodifiers$builder.build();
                }
        );
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
}
