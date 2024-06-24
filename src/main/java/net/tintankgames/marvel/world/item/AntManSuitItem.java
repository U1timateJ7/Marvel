package net.tintankgames.marvel.world.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.damagesources.MarvelDamageTypes;
import net.tintankgames.marvel.world.item.component.Size;

import java.util.List;
import java.util.UUID;

@EventBusSubscriber
public class AntManSuitItem extends LeatherSuitItem {
    public static final UUID ANT_MAN_UUID = UUID.fromString("710B5334-5538-4E7E-A658-B13B18859A29");
    private static final AttributeModifier smallScaleModifier = new AttributeModifier(ANT_MAN_UUID, "Ant-Man scale modifier", -0.9375, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    private static final AttributeModifier smallEntityInteractionRangeModifier = new AttributeModifier(ANT_MAN_UUID, "Ant-Man entity interaction range modifier", -1.0, AttributeModifier.Operation.ADD_VALUE);
    private static final AttributeModifier smallBlockInteractionRangeModifier = new AttributeModifier(ANT_MAN_UUID, "Ant-Man block interaction range modifier", -2.5, AttributeModifier.Operation.ADD_VALUE);
    private static final AttributeModifier smallMovementSpeedModifier = new AttributeModifier(ANT_MAN_UUID, "Ant-Man movement speed modifier", -0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    private static final AttributeModifier bigScaleModifier = new AttributeModifier(ANT_MAN_UUID, "Ant-Man scale modifier", 9.0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    private static final AttributeModifier bigEntityInteractionRangeModifier = new AttributeModifier(ANT_MAN_UUID, "Ant-Man entity interaction range modifier", 33.0, AttributeModifier.Operation.ADD_VALUE);
    private static final AttributeModifier bigBlockInteractionRangeModifier = new AttributeModifier(ANT_MAN_UUID, "Ant-Man block interaction range modifier", 21.5, AttributeModifier.Operation.ADD_VALUE);
    private static final AttributeModifier bigMovementSpeedModifier = new AttributeModifier(ANT_MAN_UUID, "Ant-Man movement speed modifier", 1.0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    private static final AttributeModifier bigStepHeightModifier = new AttributeModifier(ANT_MAN_UUID, "Ant-Man step height modifier", 9.0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    private static final AttributeModifier bigJumpStrengthModifier = new AttributeModifier(ANT_MAN_UUID, "Ant-Man jump strength modifier", 2.0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    private static final AttributeModifier bigSafeFallDistanceModifier = new AttributeModifier(ANT_MAN_UUID, "Ant-Man safe fall distance modifier", 27, AttributeModifier.Operation.ADD_VALUE);
    private static final AttributeModifier bigFallDamageMultiplierModifier = new AttributeModifier(ANT_MAN_UUID, "Ant-Man fall damage multiplier modifier", -0.9, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    private static final AttributeModifier attackDamageModifier = new AttributeModifier(ANT_MAN_UUID, "Ant-Man attack damage modifier", 3.0, AttributeModifier.Operation.ADD_VALUE);
    private static final AttributeModifier creativeFlightModifier = new AttributeModifier(ANT_MAN_UUID, "Wasp creative flight modifier", 1.0, AttributeModifier.Operation.ADD_VALUE);

    public AntManSuitItem(Type type, Properties properties) {
        super(type, MarvelItems.Tags.ANT_MAN_ARMOR, type == Type.CHESTPLATE ? List.of(effect(MobEffects.JUMP, 1), effect(MobEffects.MOVEMENT_SPEED, 0)) : List.of(), properties.component(MarvelDataComponents.POWER_ITEMS, List.of(MarvelItems.SHRINK.get())));
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        stack.set(MarvelDataComponents.SIZE, Size.NORMAL);
        return super.onEntityItemUpdate(stack, entity);
    }

    @SubscribeEvent
    public static void collide(PlayerTickEvent.Post event) {
        Size size = event.getEntity().getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.SIZE, Size.NORMAL);
        if (size != Size.NORMAL) {
            event.getEntity().addEffect(effect(MobEffects.DAMAGE_RESISTANCE, 1));
            if (size == Size.BIG) {
                if (event.getEntity().isSprinting()) {
                    for (LivingEntity living : event.getEntity().level().getEntitiesOfClass(LivingEntity.class, event.getEntity().getBoundingBox().inflate(1.0), entity -> entity != event.getEntity())) {
                        living.hurt(living.damageSources().source(MarvelDamageTypes.GIANT_MAN, event.getEntity()), 2.0F);
                    }
                }
            } else if (size == Size.SMALL) {
                if (event.getEntity() instanceof ServerPlayer player && player.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.WASP_CHESTPLATE)) {
                    player.getItemBySlot(EquipmentSlot.CHEST).set(MarvelDataComponents.FLYING, player.getAbilities().flying);
                }
            } else {
                if (event.getEntity() instanceof ServerPlayer player) player.getItemBySlot(EquipmentSlot.CHEST).remove(MarvelDataComponents.FLYING);
            }
        }
    }

    @SubscribeEvent
    public static void sizeChange(ItemAttributeModifierEvent event) {
        if (event.getSlotType() == EquipmentSlot.CHEST && event.getItemStack().has(MarvelDataComponents.SIZE)) {
            switch (event.getItemStack().getOrDefault(MarvelDataComponents.SIZE, Size.NORMAL)) {
                case NORMAL -> {
                    event.removeModifier(Attributes.SCALE, smallScaleModifier);
                    event.removeModifier(Attributes.ENTITY_INTERACTION_RANGE, smallEntityInteractionRangeModifier);
                    event.removeModifier(Attributes.BLOCK_INTERACTION_RANGE, smallBlockInteractionRangeModifier);
                    event.removeModifier(Attributes.MOVEMENT_SPEED, smallMovementSpeedModifier);
                    event.removeModifier(Attributes.SCALE, bigScaleModifier);
                    event.removeModifier(Attributes.ENTITY_INTERACTION_RANGE, bigEntityInteractionRangeModifier);
                    event.removeModifier(Attributes.BLOCK_INTERACTION_RANGE, bigBlockInteractionRangeModifier);
                    event.removeModifier(Attributes.MOVEMENT_SPEED, bigMovementSpeedModifier);
                    event.removeModifier(Attributes.STEP_HEIGHT, bigStepHeightModifier);
                    event.removeModifier(Attributes.JUMP_STRENGTH, bigJumpStrengthModifier);
                    event.removeModifier(Attributes.SAFE_FALL_DISTANCE, bigSafeFallDistanceModifier);
                    event.removeModifier(Attributes.FALL_DAMAGE_MULTIPLIER, bigFallDamageMultiplierModifier);
                    event.removeModifier(Attributes.ATTACK_DAMAGE, attackDamageModifier);
                    event.removeModifier(NeoForgeMod.CREATIVE_FLIGHT, creativeFlightModifier);
                }
                case SMALL -> {
                    event.addModifier(Attributes.SCALE, smallScaleModifier);
                    event.addModifier(Attributes.ENTITY_INTERACTION_RANGE, smallEntityInteractionRangeModifier);
                    event.addModifier(Attributes.BLOCK_INTERACTION_RANGE, smallBlockInteractionRangeModifier);
                    event.addModifier(Attributes.MOVEMENT_SPEED, smallMovementSpeedModifier);
                    event.removeModifier(Attributes.SCALE, bigScaleModifier);
                    event.removeModifier(Attributes.ENTITY_INTERACTION_RANGE, bigEntityInteractionRangeModifier);
                    event.removeModifier(Attributes.BLOCK_INTERACTION_RANGE, bigBlockInteractionRangeModifier);
                    event.removeModifier(Attributes.MOVEMENT_SPEED, bigMovementSpeedModifier);
                    event.removeModifier(Attributes.STEP_HEIGHT, bigStepHeightModifier);
                    event.removeModifier(Attributes.JUMP_STRENGTH, bigJumpStrengthModifier);
                    event.removeModifier(Attributes.SAFE_FALL_DISTANCE, bigSafeFallDistanceModifier);
                    event.removeModifier(Attributes.FALL_DAMAGE_MULTIPLIER, bigFallDamageMultiplierModifier);
                    event.addModifier(Attributes.ATTACK_DAMAGE, attackDamageModifier);
                    if (event.getItemStack().is(MarvelItems.Tags.WASP_CHESTPLATE)) {
                        event.addModifier(NeoForgeMod.CREATIVE_FLIGHT, creativeFlightModifier);
                    } else {
                        event.removeModifier(NeoForgeMod.CREATIVE_FLIGHT, creativeFlightModifier);
                    }
                }
                case BIG -> {
                    event.removeModifier(Attributes.SCALE, smallScaleModifier);
                    event.removeModifier(Attributes.ENTITY_INTERACTION_RANGE, smallEntityInteractionRangeModifier);
                    event.removeModifier(Attributes.BLOCK_INTERACTION_RANGE, smallBlockInteractionRangeModifier);
                    event.removeModifier(Attributes.MOVEMENT_SPEED, smallMovementSpeedModifier);
                    event.addModifier(Attributes.SCALE, bigScaleModifier);
                    event.addModifier(Attributes.ENTITY_INTERACTION_RANGE, bigEntityInteractionRangeModifier);
                    event.addModifier(Attributes.BLOCK_INTERACTION_RANGE, bigBlockInteractionRangeModifier);
                    event.addModifier(Attributes.MOVEMENT_SPEED, bigMovementSpeedModifier);
                    event.addModifier(Attributes.STEP_HEIGHT, bigStepHeightModifier);
                    event.addModifier(Attributes.JUMP_STRENGTH, bigJumpStrengthModifier);
                    event.addModifier(Attributes.SAFE_FALL_DISTANCE, bigSafeFallDistanceModifier);
                    event.addModifier(Attributes.FALL_DAMAGE_MULTIPLIER, bigFallDamageMultiplierModifier);
                    event.addModifier(Attributes.ATTACK_DAMAGE, attackDamageModifier);
                    event.removeModifier(NeoForgeMod.CREATIVE_FLIGHT, creativeFlightModifier);
                }
            }
        }
    }
}
