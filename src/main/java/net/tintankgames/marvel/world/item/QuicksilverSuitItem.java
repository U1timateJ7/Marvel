package net.tintankgames.marvel.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.timers.TimerQueue;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingHurtEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.client.input.MarvelKeyMappings;
import net.tintankgames.marvel.client.model.MarvelModels;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.level.timers.MultiAttackCallback;

import java.util.List;
import java.util.UUID;

@EventBusSubscriber
public class QuicksilverSuitItem extends SuitItem {
    public static final UUID QUICKSILVER_UUID = UUID.fromString("378C2898-DF09-4BF9-AF9D-DEAE4DCB4655");
    private static final AttributeModifier movementSpeedModifier = new AttributeModifier(QUICKSILVER_UUID, "Quicksilver movement speed modifier", 5.8, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    private static final AttributeModifier stepHeightModifier = new AttributeModifier(QUICKSILVER_UUID, "Quicksilver step height modifier", 3.4, AttributeModifier.Operation.ADD_VALUE);
    private static final AttributeModifier safeFallDistanceModifier = new AttributeModifier(QUICKSILVER_UUID, "Quicksilver safe fall distance modifier", 7, AttributeModifier.Operation.ADD_VALUE);
    private static final AttributeModifier fallDamageMultiplierModifier = new AttributeModifier(QUICKSILVER_UUID, "Quicksilver fall damage modifier", -0.5, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    private static final AttributeModifier attackDamageModifier = new AttributeModifier(QUICKSILVER_UUID, "Quicksilver attack damage modifier", 1, AttributeModifier.Operation.ADD_VALUE);

    public QuicksilverSuitItem(Type type, Properties properties) {
        super(MarvelArmorMaterials.QUICKSILVER, type, MarvelItems.Tags.QUICKSILVER_ARMOR, type == Type.CHESTPLATE ? List.of(effect(MobEffects.MOVEMENT_SPEED, 0), effect(MobEffects.JUMP, 1)) : List.of(), properties.component(MarvelDataComponents.POWER_ITEMS, List.of(MarvelItems.SONIC_BOOM.get())));
    }

    @Override
    public boolean needsHelmet() {
        return false;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ModelLayerLocation modelFactory(Type type, ItemStack itemStack) {
        return MarvelModels.suit(type);
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_339594_, p_41423_, p_41424_);
        if (type == Type.CHESTPLATE) p_41423_.add(Component.translatable(getDescriptionId(p_41421_).replace("_chestplate", "") + ".key.v", Component.keybind(MarvelKeyMappings.PRIMARY_SUIT_ABILITY.getName()).withStyle(ChatFormatting.BOLD)).withStyle(ChatFormatting.GRAY));
    }

    @SubscribeEvent
    public static void livingTick(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof LivingEntity living) {
            if (living.getItemBySlot(EquipmentSlot.HEAD).isEmpty() && living.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.QUICKSILVER_ARMOR) && living.getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.QUICKSILVER_ARMOR) && living.getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.QUICKSILVER_ARMOR) && living.getItemBySlot(EquipmentSlot.CHEST).has(MarvelDataComponents.SPEEDING)) {
                living.getAttribute(Attributes.MOVEMENT_SPEED).addOrUpdateTransientModifier(movementSpeedModifier);
                living.getAttribute(Attributes.STEP_HEIGHT).addOrUpdateTransientModifier(stepHeightModifier);
                living.getAttribute(Attributes.SAFE_FALL_DISTANCE).addOrUpdateTransientModifier(safeFallDistanceModifier);
                living.getAttribute(Attributes.FALL_DAMAGE_MULTIPLIER).addOrUpdateTransientModifier(fallDamageMultiplierModifier);
                if (living.getAttribute(Attributes.ATTACK_DAMAGE) != null) living.getAttribute(Attributes.ATTACK_DAMAGE).addOrUpdateTransientModifier(attackDamageModifier);
                if (living instanceof ServerPlayer serverPlayer && serverPlayer.getData(MarvelAttachmentTypes.MOVING.get())) {
                    Vec3 particlePlacement = serverPlayer.getPosition(0.0F).subtract(serverPlayer.getViewVector(0.0F));
                    serverPlayer.serverLevel().sendParticles(ParticleTypes.OMINOUS_SPAWNING, particlePlacement.x(), particlePlacement.y() + 1, particlePlacement.z(), 75, 0, 0, 0, 0.3);
                }
            } else {
                living.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(movementSpeedModifier.id());
                living.getAttribute(Attributes.STEP_HEIGHT).removeModifier(stepHeightModifier.id());
                living.getAttribute(Attributes.SAFE_FALL_DISTANCE).removeModifier(safeFallDistanceModifier.id());
                living.getAttribute(Attributes.FALL_DAMAGE_MULTIPLIER).removeModifier(fallDamageMultiplierModifier.id());
                if (living.getAttribute(Attributes.ATTACK_DAMAGE) != null) living.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(attackDamageModifier.id());
            }
        }
    }

    @SubscribeEvent
    public static void multiAttack(LivingHurtEvent event) {
        if (event.getSource().is(DamageTypes.PLAYER_ATTACK) && event.getSource().getDirectEntity() instanceof ServerPlayer player && player.getItemBySlot(EquipmentSlot.HEAD).isEmpty() && player.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.QUICKSILVER_ARMOR) && player.getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.QUICKSILVER_ARMOR) && player.getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.QUICKSILVER_ARMOR) && player.getItemBySlot(EquipmentSlot.CHEST).has(MarvelDataComponents.SPEEDING)) {
            TimerQueue<MinecraftServer> timerQueue = player.getServer().getWorldData().overworldData().getScheduledEvents();
            long i = player.serverLevel().getGameTime();
            timerQueue.schedule(player.getStringUUID() + "_quicksilver_attack", i + 2, new MultiAttackCallback(player.getUUID(), event.getEntity().getUUID(), player.level().dimension(), false, true));
            timerQueue.schedule(player.getStringUUID() + "_quicksilver_attack", i + 4, new MultiAttackCallback(player.getUUID(), event.getEntity().getUUID(), player.level().dimension(), false, true));
            timerQueue.schedule(player.getStringUUID() + "_quicksilver_attack", i + 6, new MultiAttackCallback(player.getUUID(), event.getEntity().getUUID(), player.level().dimension(), false, true));
        }
    }
}
