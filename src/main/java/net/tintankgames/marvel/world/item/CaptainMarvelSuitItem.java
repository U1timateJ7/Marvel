package net.tintankgames.marvel.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.client.input.MarvelKeyMappings;
import net.tintankgames.marvel.client.model.MarvelModels;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.core.particles.EmissiveDustParticleOptions;

import java.util.List;
import java.util.UUID;

@EventBusSubscriber
public class CaptainMarvelSuitItem extends SuitItem {
    private static final UUID CAPTAIN_MARVEL_MODIFIER_UUID = UUID.fromString("C1974F00-3873-4CD1-AF21-EB38670BF866");
    private static final AttributeModifier creativeFlightModifier = new AttributeModifier(CAPTAIN_MARVEL_MODIFIER_UUID, "Captain Marvel creative flight modifier", 1, AttributeModifier.Operation.ADD_VALUE);
    private static final AttributeModifier attackDamageModifier = new AttributeModifier(CAPTAIN_MARVEL_MODIFIER_UUID, "Captain Marvel attack damage modifier", 3, AttributeModifier.Operation.ADD_VALUE);
    private static final AttributeModifier attackKnockbackModifier = new AttributeModifier(CAPTAIN_MARVEL_MODIFIER_UUID, "Captain Marvel attack knockback modifier", 3, AttributeModifier.Operation.ADD_VALUE);

    public CaptainMarvelSuitItem(Type type, Properties properties) {
        super(MarvelArmorMaterials.CAPTAIN_MARVEL, type, MarvelItems.Tags.CAPTAIN_MARVEL_ARMOR, type == Type.CHESTPLATE ? List.of(effect(MobEffects.JUMP, 1), effect(MobEffects.MOVEMENT_SPEED, 0), effect(MobEffects.DAMAGE_BOOST, 0)) : List.of(), properties.component(MarvelDataComponents.POWER_ITEMS, List.of(MarvelItems.PHOTON_BLAST.get())));
    }

    @Override
    public ModelLayerLocation modelFactory(Type type, ItemStack itemStack) {
        return MarvelModels.captainMarvelSuit(type);
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_339594_, p_41423_, p_41424_);
        if (type == Type.HELMET) p_41423_.add(Component.translatable(getDescriptionId(p_41421_).replace("_helmet", "") + ".key.h", Component.keybind(MarvelKeyMappings.TOGGLE_HELMET.getName()).withStyle(ChatFormatting.BOLD)).withStyle(ChatFormatting.GRAY));
    }

    @SubscribeEvent
    public static void playerTick(PlayerTickEvent.Post event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            if (serverPlayer.getItemBySlot(EquipmentSlot.HEAD).is(MarvelItems.Tags.CAPTAIN_MARVEL_ARMOR) && serverPlayer.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.CAPTAIN_MARVEL_ARMOR) && serverPlayer.getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.CAPTAIN_MARVEL_ARMOR) && serverPlayer.getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.CAPTAIN_MARVEL_ARMOR)) {
                serverPlayer.getAttribute(NeoForgeMod.CREATIVE_FLIGHT).addOrUpdateTransientModifier(creativeFlightModifier);
                serverPlayer.getAttribute(Attributes.ATTACK_DAMAGE).addOrUpdateTransientModifier(attackDamageModifier);
                serverPlayer.getAttribute(Attributes.ATTACK_KNOCKBACK).addOrUpdateTransientModifier(attackKnockbackModifier);
                if (serverPlayer.getAbilities().flying) {
                    serverPlayer.getItemBySlot(EquipmentSlot.CHEST).set(MarvelDataComponents.FLYING, serverPlayer.getAbilities().flying);
                    serverPlayer.getItemBySlot(EquipmentSlot.CHEST).set(MarvelDataComponents.DELTA_MOVEMENT, serverPlayer.getData(MarvelAttachmentTypes.DELTA_MOVEMENT));
                    Vec3 flamePlacement = serverPlayer.position().add(serverPlayer.getData(MarvelAttachmentTypes.DELTA_MOVEMENT).multiply(-1.5, -1, -1.5)).add(0, serverPlayer.getData(MarvelAttachmentTypes.DELTA_MOVEMENT).horizontalDistance() * 1.4, 0);
                    serverPlayer.serverLevel().sendParticles(new EmissiveDustParticleOptions(SimpleWeightedRandomList.<Integer>builder().add(0xFFBD42, 6).add(0xFFE242, 3).add(0x80B9E5, 1).build(), 0.75f, 0.1F), flamePlacement.x(), flamePlacement.y(), flamePlacement.z(), 4, 0.1, 0, 0.1, 0);
                    flamePlacement = serverPlayer.position().add(0, 0.9, 0).add(serverPlayer.getData(MarvelAttachmentTypes.DELTA_MOVEMENT).multiply(-0.75, -0.5, -0.75)).add(0, serverPlayer.getData(MarvelAttachmentTypes.DELTA_MOVEMENT).horizontalDistance() * 0.7, 0);
                    serverPlayer.serverLevel().sendParticles(new EmissiveDustParticleOptions(SimpleWeightedRandomList.<Integer>builder().add(0xFFBD42, 6).add(0xFFE242, 3).add(0x80B9E5, 1).build(), 0.75f, 0.1F), flamePlacement.x(), flamePlacement.y(), flamePlacement.z(), 6, 0.2, 0.3, 0.2, 0);
                } else {
                    serverPlayer.getItemBySlot(EquipmentSlot.CHEST).remove(MarvelDataComponents.FLYING);
                    serverPlayer.getItemBySlot(EquipmentSlot.CHEST).remove(MarvelDataComponents.DELTA_MOVEMENT);
                }
            } else {
                serverPlayer.getAttribute(NeoForgeMod.CREATIVE_FLIGHT).removeModifier(creativeFlightModifier.id());
                serverPlayer.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(attackDamageModifier.id());
                serverPlayer.getAttribute(Attributes.ATTACK_KNOCKBACK).removeModifier(attackKnockbackModifier.id());
            }
        }
    }

    @SubscribeEvent
    public static void arrowImmunity(LivingDamageEvent event) {
        if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).is(MarvelItems.Tags.CAPTAIN_MARVEL_ARMOR) && event.getEntity().getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.CAPTAIN_MARVEL_ARMOR) && event.getEntity().getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.CAPTAIN_MARVEL_ARMOR) && event.getEntity().getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.CAPTAIN_MARVEL_ARMOR) && event.getSource().getDirectEntity() != null && event.getSource().getDirectEntity().getType().is(EntityTypeTags.ARROWS) && event.getEntity().getRandom().nextInt(5) < 3) {
            event.setCanceled(true);
        }
    }
}
