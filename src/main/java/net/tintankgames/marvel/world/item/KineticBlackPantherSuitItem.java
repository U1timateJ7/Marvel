package net.tintankgames.marvel.world.item;

import com.google.common.base.Suppliers;
import net.minecraft.Util;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingHurtEvent;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.client.model.MarvelModels;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import org.jetbrains.annotations.Nullable;
import org.joml.Math;

import java.util.EnumMap;
import java.util.List;
import java.util.UUID;
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
        super(MarvelArmorMaterials.VIBRANIUM_NANITE, type, true, MarvelItems.Tags.KINETIC_BLACK_PANTHER_ARMOR, type == Type.CHESTPLATE ? List.of(effect(MobEffects.JUMP, 1), effect(MobEffects.MOVEMENT_SPEED, 0)) : List.of(), properties.component(MarvelDataComponents.ABSORBED_DAMAGE, 0.0F));
        this.color = color;
        this.defaultModifiers = Suppliers.memoize(
                () -> {
                    int i = MarvelArmorMaterials.VIBRANIUM_NANITE.value().getDefense(type);
                    float f = MarvelArmorMaterials.VIBRANIUM_NANITE.value().toughness();
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
                    float f1 = MarvelArmorMaterials.VIBRANIUM_NANITE.value().knockbackResistance();
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
                                new AttributeModifier(uuid, "Armor attack damage", 3, AttributeModifier.Operation.ADD_VALUE),
                                equipmentslotgroup
                        );
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
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, @Nullable T entity, Runnable onBroken) {
        return 0;
    }

    @SubscribeEvent
    public static void playerHurt(LivingHurtEvent event) {
        if (event.getEntity() instanceof ServerPlayer player && event.getSource().getEntity() != null) {
            float absorbed = event.getAmount() / 2.0F;
            player.getInventory().armor.forEach(stack -> {
                if (stack.has(MarvelDataComponents.ABSORBED_DAMAGE)) {
                    stack.update(MarvelDataComponents.ABSORBED_DAMAGE, 0.0F, damage -> Math.min(damage + absorbed, 25.0F));
                }
            });
        }
    }

    @Override
    protected ModelLayerLocation modelFactory(Type type, ItemStack itemStack) {
        return MarvelModels.pantherSuit(type);
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers() {
        return defaultModifiers.get();
    }

    @Override
    public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        return slot == EquipmentSlot.HEAD && stack.getOrDefault(MarvelDataComponents.HELMET_OPEN, false) ? MarvelSuperheroes.id("textures/models/suit/empty.png") : super.getArmorTexture(stack, entity, slot, layer, innerModel);
    }
}
