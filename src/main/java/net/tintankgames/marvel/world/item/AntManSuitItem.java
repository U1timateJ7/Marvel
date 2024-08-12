package net.tintankgames.marvel.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.client.input.MarvelKeyMappings;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.damagesources.MarvelDamageTypes;
import net.tintankgames.marvel.world.item.component.Size;

import java.util.List;

@EventBusSubscriber
public class AntManSuitItem extends LeatherSuitItem {
    private static final ResourceLocation ANT_MAN_MODIFIER_ID = MarvelSuperheroes.id("ant_man");
    private static final AttributeModifier smallScaleModifier = new AttributeModifier(ANT_MAN_MODIFIER_ID, -0.9375, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    private static final AttributeModifier smallEntityInteractionRangeModifier = new AttributeModifier(ANT_MAN_MODIFIER_ID, -1.0, AttributeModifier.Operation.ADD_VALUE);
    private static final AttributeModifier smallBlockInteractionRangeModifier = new AttributeModifier(ANT_MAN_MODIFIER_ID, -2.5, AttributeModifier.Operation.ADD_VALUE);
    private static final AttributeModifier smallMovementSpeedModifier = new AttributeModifier(ANT_MAN_MODIFIER_ID, -0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    private static final AttributeModifier bigScaleModifier = new AttributeModifier(ANT_MAN_MODIFIER_ID, 9.0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    private static final AttributeModifier bigEntityInteractionRangeModifier = new AttributeModifier(ANT_MAN_MODIFIER_ID, 33.0, AttributeModifier.Operation.ADD_VALUE);
    private static final AttributeModifier bigBlockInteractionRangeModifier = new AttributeModifier(ANT_MAN_MODIFIER_ID, 21.5, AttributeModifier.Operation.ADD_VALUE);
    private static final AttributeModifier bigMovementSpeedModifier = new AttributeModifier(ANT_MAN_MODIFIER_ID, 1.0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    private static final AttributeModifier bigStepHeightModifier = new AttributeModifier(ANT_MAN_MODIFIER_ID, 9.0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    private static final AttributeModifier bigJumpStrengthModifier = new AttributeModifier(ANT_MAN_MODIFIER_ID, 2.0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    private static final AttributeModifier bigSafeFallDistanceModifier = new AttributeModifier(ANT_MAN_MODIFIER_ID, 27, AttributeModifier.Operation.ADD_VALUE);
    private static final AttributeModifier bigFallDamageMultiplierModifier = new AttributeModifier(ANT_MAN_MODIFIER_ID, -0.9, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    private static final AttributeModifier attackDamageModifier = new AttributeModifier(ANT_MAN_MODIFIER_ID, 3.0, AttributeModifier.Operation.ADD_VALUE);
    private static final AttributeModifier creativeFlightModifier = new AttributeModifier(ANT_MAN_MODIFIER_ID, 1.0, AttributeModifier.Operation.ADD_VALUE);

    public AntManSuitItem(Type type, Properties properties) {
        super(type, MarvelItems.Tags.ANT_MAN_ARMOR, type == Type.CHESTPLATE ? List.of(effect(MobEffects.JUMP, 1), effect(MobEffects.MOVEMENT_SPEED, 0)) : List.of(), type == Type.CHESTPLATE ? properties.component(MarvelDataComponents.POWER_ITEMS, List.of(MarvelItems.SHRINK.get())).component(MarvelDataComponents.SIZE, Size.NORMAL) : properties.component(MarvelDataComponents.POWER_ITEMS, List.of(MarvelItems.SHRINK.get())));
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        stack.set(MarvelDataComponents.SIZE, Size.NORMAL);
        return super.onEntityItemUpdate(stack, entity);
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_339594_, p_41423_, p_41424_);
        if (type == Type.HELMET) p_41423_.add(Component.translatable(getDescriptionId(p_41421_).replace("_helmet", "") + ".key.h", Component.keybind(MarvelKeyMappings.TOGGLE_HELMET.getName()).withStyle(ChatFormatting.BOLD)).withStyle(ChatFormatting.GRAY));
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
                    player.getItemBySlot(EquipmentSlot.CHEST).set(MarvelDataComponents.DELTA_MOVEMENT, player.getKnownMovement());
                }
            } else {
                if (event.getEntity() instanceof ServerPlayer player) {
                    player.getItemBySlot(EquipmentSlot.CHEST).remove(MarvelDataComponents.FLYING);
                    player.getItemBySlot(EquipmentSlot.CHEST).remove(MarvelDataComponents.DELTA_MOVEMENT);
                }
            }
        }
    }

    @SubscribeEvent
    public static void sizeChange(ItemAttributeModifierEvent event) {
        if (event.getItemStack().has(MarvelDataComponents.SIZE)) {
            switch (event.getItemStack().getOrDefault(MarvelDataComponents.SIZE, Size.NORMAL)) {
                case NORMAL -> {
                    event.removeModifier(Attributes.SCALE, ANT_MAN_MODIFIER_ID);
                    event.removeModifier(Attributes.ENTITY_INTERACTION_RANGE, ANT_MAN_MODIFIER_ID);
                    event.removeModifier(Attributes.BLOCK_INTERACTION_RANGE, ANT_MAN_MODIFIER_ID);
                    event.removeModifier(Attributes.MOVEMENT_SPEED, ANT_MAN_MODIFIER_ID);
                    event.removeModifier(Attributes.STEP_HEIGHT, ANT_MAN_MODIFIER_ID);
                    event.removeModifier(Attributes.JUMP_STRENGTH, ANT_MAN_MODIFIER_ID);
                    event.removeModifier(Attributes.SAFE_FALL_DISTANCE, ANT_MAN_MODIFIER_ID);
                    event.removeModifier(Attributes.FALL_DAMAGE_MULTIPLIER, ANT_MAN_MODIFIER_ID);
                    event.removeModifier(Attributes.ATTACK_DAMAGE, ANT_MAN_MODIFIER_ID);
                    event.removeModifier(NeoForgeMod.CREATIVE_FLIGHT, ANT_MAN_MODIFIER_ID);
                }
                case SMALL -> {
                    event.addModifier(Attributes.SCALE, smallScaleModifier, EquipmentSlotGroup.CHEST);
                    event.addModifier(Attributes.ENTITY_INTERACTION_RANGE, smallEntityInteractionRangeModifier, EquipmentSlotGroup.CHEST);
                    event.addModifier(Attributes.BLOCK_INTERACTION_RANGE, smallBlockInteractionRangeModifier, EquipmentSlotGroup.CHEST);
                    event.addModifier(Attributes.MOVEMENT_SPEED, smallMovementSpeedModifier, EquipmentSlotGroup.CHEST);
                    event.removeModifier(Attributes.STEP_HEIGHT, ANT_MAN_MODIFIER_ID);
                    event.removeModifier(Attributes.JUMP_STRENGTH, ANT_MAN_MODIFIER_ID);
                    event.removeModifier(Attributes.SAFE_FALL_DISTANCE, ANT_MAN_MODIFIER_ID);
                    event.removeModifier(Attributes.FALL_DAMAGE_MULTIPLIER, ANT_MAN_MODIFIER_ID);
                    event.addModifier(Attributes.ATTACK_DAMAGE, attackDamageModifier, EquipmentSlotGroup.CHEST);
                    if (event.getItemStack().is(MarvelItems.Tags.WASP_CHESTPLATE)) {
                        event.addModifier(NeoForgeMod.CREATIVE_FLIGHT, creativeFlightModifier, EquipmentSlotGroup.CHEST);
                    } else {
                        event.removeModifier(NeoForgeMod.CREATIVE_FLIGHT, ANT_MAN_MODIFIER_ID);
                    }
                }
                case BIG -> {
                    event.addModifier(Attributes.SCALE, bigScaleModifier, EquipmentSlotGroup.CHEST);
                    event.addModifier(Attributes.ENTITY_INTERACTION_RANGE, bigEntityInteractionRangeModifier, EquipmentSlotGroup.CHEST);
                    event.addModifier(Attributes.BLOCK_INTERACTION_RANGE, bigBlockInteractionRangeModifier, EquipmentSlotGroup.CHEST);
                    event.addModifier(Attributes.MOVEMENT_SPEED, bigMovementSpeedModifier, EquipmentSlotGroup.CHEST);
                    event.addModifier(Attributes.STEP_HEIGHT, bigStepHeightModifier, EquipmentSlotGroup.CHEST);
                    event.addModifier(Attributes.JUMP_STRENGTH, bigJumpStrengthModifier, EquipmentSlotGroup.CHEST);
                    event.addModifier(Attributes.SAFE_FALL_DISTANCE, bigSafeFallDistanceModifier, EquipmentSlotGroup.CHEST);
                    event.addModifier(Attributes.FALL_DAMAGE_MULTIPLIER, bigFallDamageMultiplierModifier, EquipmentSlotGroup.CHEST);
                    event.addModifier(Attributes.ATTACK_DAMAGE, attackDamageModifier, EquipmentSlotGroup.CHEST);
                    event.removeModifier(NeoForgeMod.CREATIVE_FLIGHT, ANT_MAN_MODIFIER_ID);
                }
            }
        }
    }
}
