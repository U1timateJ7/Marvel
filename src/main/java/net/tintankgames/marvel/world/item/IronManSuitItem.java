package net.tintankgames.marvel.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.entity.EntityStruckByLightningEvent;
import net.neoforged.neoforge.event.entity.living.LivingAttackEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.client.input.MarvelKeyMappings;
import net.tintankgames.marvel.client.model.MarvelModels;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.core.particles.MarvelParticleTypes;
import net.tintankgames.marvel.world.effect.MarvelMobEffects;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EventBusSubscriber
public abstract class IronManSuitItem extends EnergySuitItem {
    public static final UUID IRON_MAN_UUID = UUID.fromString("FC31016D-4B91-494D-B7D1-B89E02D85503");
    private static final AttributeModifier creativeFlightModifier = new AttributeModifier(IRON_MAN_UUID, "Iron Man creative flight modifier", 1, AttributeModifier.Operation.ADD_VALUE);
    private static final AttributeModifier safeFalLDistanceModifier = new AttributeModifier(IRON_MAN_UUID, "Iron Man safe fall distance modifier", 7, AttributeModifier.Operation.ADD_VALUE);
    private static final AttributeModifier fallDamageMultiplierModifier = new AttributeModifier(IRON_MAN_UUID, "Iron Man fall damage multiplier modifier", -0.6, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

    public IronManSuitItem(Holder<ArmorMaterial> armorMaterial, Type type, TagKey<Item> tagKey, List<MobEffectInstance> list, List<Item> powerItems, Properties properties) {
        super(armorMaterial, type, tagKey, type == Type.CHESTPLATE ? makeList(list, effect(MobEffects.DAMAGE_BOOST, 0)) : List.of(), !powerItems.isEmpty() ? properties.component(MarvelDataComponents.POWER_ITEMS, powerItems) : properties);
    }

    private static List<MobEffectInstance> makeList(List<MobEffectInstance> baseList, MobEffectInstance... mobEffectInstances) {
        List<MobEffectInstance> list = new ArrayList<>(baseList);
        list.addAll(List.of(mobEffectInstances));
        return list;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ModelLayerLocation modelFactory(Type type, ItemStack itemStack) {
        return MarvelModels.ironManSuit(type);
    }

    @SubscribeEvent
    public static void playerTick(PlayerTickEvent.Post event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            if (hasArmor(serverPlayer, MarvelItems.Tags.IRON_MAN_ARMOR)) {
                serverPlayer.getAttribute(Attributes.SAFE_FALL_DISTANCE).addOrUpdateTransientModifier(safeFalLDistanceModifier);
                serverPlayer.getAttribute(Attributes.FALL_DAMAGE_MULTIPLIER).addOrUpdateTransientModifier(fallDamageMultiplierModifier);
                if (serverPlayer.getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.ENERGY, 0.0F) > 2.0F) {
                    serverPlayer.getAttribute(NeoForgeMod.CREATIVE_FLIGHT).addOrUpdateTransientModifier(creativeFlightModifier);
                } else {
                    serverPlayer.getAttribute(NeoForgeMod.CREATIVE_FLIGHT).removeModifier(creativeFlightModifier.id());
                }
                if (serverPlayer.getY() >= ((IronManSuitItem) serverPlayer.getItemBySlot(EquipmentSlot.CHEST).getItem()).getFlightMax() && !serverPlayer.isCreative()) {
                    serverPlayer.addEffect(effect(MarvelMobEffects.ICING, 0, 30));
                }
                if (serverPlayer.getAbilities().flying) {
                    double d = 1 / (serverPlayer.getAbilities().getFlyingSpeed() * (hasArmor(serverPlayer, MarvelItems.Tags.IRON_MAN_MARK_19_ARMOR) ? 2.0F : 1.0F) / 0.05F);
                    Vec3 movement = serverPlayer.getData(MarvelAttachmentTypes.DELTA_MOVEMENT).multiply(d, d, d);
                    movement = new Vec3(Math.clamp(movement.x, -1.0F, 1.0F), Math.clamp(movement.y, -1.0F, 1.0F), Math.clamp(movement.z, -1.0F, 1.0F));
                    serverPlayer.getItemBySlot(EquipmentSlot.CHEST).set(MarvelDataComponents.FLYING, serverPlayer.getAbilities().flying);
                    serverPlayer.getItemBySlot(EquipmentSlot.CHEST).set(MarvelDataComponents.DELTA_MOVEMENT, movement);
                    Vec3 flamePlacement = serverPlayer.position().add(movement.multiply(-1.5, -1, -1.5)).add(0, movement.horizontalDistance() * 1.4, 0);
                    serverPlayer.serverLevel().sendParticles(MarvelParticleTypes.IRON_MAN_FLAME.get(), flamePlacement.x(), flamePlacement.y(), flamePlacement.z(), 4, 0.1, 0, 0.1, 0);
                } else {
                    serverPlayer.getItemBySlot(EquipmentSlot.CHEST).remove(MarvelDataComponents.FLYING);
                    serverPlayer.getItemBySlot(EquipmentSlot.CHEST).remove(MarvelDataComponents.DELTA_MOVEMENT);
                }
            } else {
                serverPlayer.getAttribute(NeoForgeMod.CREATIVE_FLIGHT).removeModifier(creativeFlightModifier.id());
                serverPlayer.getAttribute(Attributes.SAFE_FALL_DISTANCE).removeModifier(safeFalLDistanceModifier.id());
                serverPlayer.getAttribute(Attributes.FALL_DAMAGE_MULTIPLIER).removeModifier(fallDamageMultiplierModifier.id());
            }
        }
    }

    private static boolean hasArmor(LivingEntity living, TagKey<Item> tagKey) {
        boolean head = living.getItemBySlot(EquipmentSlot.HEAD).is(tagKey);
        boolean chest = living.getItemBySlot(EquipmentSlot.CHEST).is(tagKey);
        boolean legs = living.getItemBySlot(EquipmentSlot.LEGS).is(tagKey);
        boolean feet = living.getItemBySlot(EquipmentSlot.FEET).is(tagKey);
        return head && chest && legs && feet;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean p_41408_) {
        super.inventoryTick(stack, level, entity, slot, p_41408_);
        if (entity instanceof ServerPlayer serverPlayer && !(serverPlayer.getItemBySlot(EquipmentSlot.HEAD).is(MarvelItems.Tags.IRON_MAN_ARMOR) && serverPlayer.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.IRON_MAN_ARMOR) && serverPlayer.getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.IRON_MAN_ARMOR) && serverPlayer.getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.IRON_MAN_ARMOR)) && stack.has(MarvelDataComponents.FLYING)) {
            stack.remove(MarvelDataComponents.FLYING);
        }
    }

    @SubscribeEvent
    public static void livingTick(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof LivingEntity living && !living.level().isClientSide()) {
            if (living.isUnderWater()) {
                living.setData(MarvelAttachmentTypes.UNDERWATER_TICKS, living.getData(MarvelAttachmentTypes.UNDERWATER_TICKS) + 1);
            } else {
                living.setData(MarvelAttachmentTypes.UNDERWATER_TICKS, 0);
            }
        }
    }

    @Override
    protected void fullSuitTick(ItemStack stack, Level level, Player player) {
        super.fullSuitTick(stack, level, player);
        if (player.getItemBySlot(EquipmentSlot.HEAD).is(MarvelItems.Tags.IRON_MAN_MARK_6_ARMOR) && player.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.IRON_MAN_MARK_6_ARMOR) && player.getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.IRON_MAN_MARK_6_ARMOR) && player.getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.IRON_MAN_MARK_6_ARMOR) && player.getData(MarvelAttachmentTypes.UNDERWATER_TICKS) < 200) {
            player.addEffect(effect(MobEffects.WATER_BREATHING, 0));
        }
    }

    @SubscribeEvent
    public static void lightning(EntityStruckByLightningEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            if (serverPlayer.getItemBySlot(EquipmentSlot.HEAD).is(MarvelItems.Tags.IRON_MAN_ARMOR) && serverPlayer.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.IRON_MAN_ARMOR) && serverPlayer.getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.IRON_MAN_ARMOR) && serverPlayer.getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.IRON_MAN_ARMOR)) {
                serverPlayer.getInventory().armor.forEach(stack -> addEnergy(stack, 50.0F));
            }
        }
    }

    @SubscribeEvent
    public static void arrowImmunity(LivingAttackEvent event) {
        if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).is(MarvelItems.Tags.IRON_MAN_ARMOR) && event.getEntity().getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.IRON_MAN_ARMOR) && event.getEntity().getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.IRON_MAN_ARMOR) && event.getEntity().getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.IRON_MAN_ARMOR) && event.getSource().getDirectEntity() != null && event.getSource().getDirectEntity().getType().is(EntityTypeTags.ARROWS) && event.getEntity().getRandom().nextInt(5) < 3) {
            event.setCanceled(true);
        }
    }

    protected double getFlightMax() {
        return 256.0F;
    }

    @Override
    protected String getOrCreateDescriptionId() {
        if (this.descriptionId == null) {
            this.descriptionId = Util.makeDescriptionId("item", BuiltInRegistries.ITEM.getKey(this).withPath("iron_man_" + type.getName()));
        }
        return this.descriptionId;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flags) {
        super.appendHoverText(stack, context, list, flags);
        list.add(Component.translatable(BuiltInRegistries.ITEM.getKey(this).withPath("iron_man." + BuiltInRegistries.ITEM.getKey(this).getPath().replace("iron_man_", "").replace("war_machine_", "").replace("_" + type.getName(), "")).toLanguageKey("item")).withStyle(ChatFormatting.GRAY));
        if (type == Type.HELMET) addAbilityMessage(stack, context, list, flags);
        if (stack.has(MarvelDataComponents.HELMET_OPEN)) list.add(Component.translatable(BuiltInRegistries.ITEM.getKey(this).withPath("iron_man.key.h" + (stack.is(MarvelItems.Tags.INVISIBLE_WHEN_OPEN) ? ".invisible" : "")).toLanguageKey("item"), Component.keybind(MarvelKeyMappings.TOGGLE_HELMET.getName()).withStyle(ChatFormatting.BOLD)).withStyle(ChatFormatting.GRAY));
    }

    protected void addAbilityMessage(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flags) {
    }

    public int hudColor() {
        return 0x68E3FF;
    }

    public Component hudMark() {
        return Component.empty();
    }
}
