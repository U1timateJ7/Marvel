package net.tintankgames.marvel.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.entity.EntityStruckByLightningEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.tintankgames.marvel.client.input.MarvelKeyMappings;
import net.tintankgames.marvel.client.model.MarvelModels;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.core.particles.MarvelParticleTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EventBusSubscriber
public abstract class IronManSuitItem extends EnergySuitItem {
    public static final UUID IRON_MAN_UUID = UUID.fromString("FC31016D-4B91-494D-B7D1-B89E02D85503");
    private static final AttributeModifier creativeFlightModifier = new AttributeModifier(IRON_MAN_UUID, "Iron Man creative flight modifier", 1, AttributeModifier.Operation.ADD_VALUE);

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
    protected ModelLayerLocation modelFactory(Type type, ItemStack itemStack) {
        return MarvelModels.ironManSuit(type);
    }

    @SubscribeEvent
    public static void livingTick(PlayerTickEvent.Post event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            if (serverPlayer.getItemBySlot(EquipmentSlot.HEAD).is(MarvelItems.Tags.IRON_MAN_ARMOR) && serverPlayer.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.IRON_MAN_ARMOR) && serverPlayer.getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.IRON_MAN_ARMOR) && serverPlayer.getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.IRON_MAN_ARMOR) && serverPlayer.getY() < ((IronManSuitItem)serverPlayer.getItemBySlot(EquipmentSlot.CHEST).getItem()).getFlightMax() && serverPlayer.getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.ENERGY, 0.0F) > 2.0F) {
                serverPlayer.getAttribute(NeoForgeMod.CREATIVE_FLIGHT).addOrUpdateTransientModifier(creativeFlightModifier);
            } else {
                serverPlayer.getAttribute(NeoForgeMod.CREATIVE_FLIGHT).removeModifier(creativeFlightModifier.id());
            }
            if (serverPlayer.getItemBySlot(EquipmentSlot.HEAD).is(MarvelItems.Tags.IRON_MAN_ARMOR) && serverPlayer.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.IRON_MAN_ARMOR) && serverPlayer.getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.IRON_MAN_ARMOR) && serverPlayer.getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.IRON_MAN_ARMOR) && serverPlayer.getAbilities().flying) {
                serverPlayer.serverLevel().sendParticles(MarvelParticleTypes.IRON_MAN_FLAME.get(), serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), 4, 0.1, 0, 0.1, 0);
            }
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
    public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_339594_, p_41423_, p_41424_);
        p_41423_.add(Component.translatable(BuiltInRegistries.ITEM.getKey(this).withPath("iron_man." + BuiltInRegistries.ITEM.getKey(this).getPath().replace("iron_man_", "").replace("_" + type.getName(), "")).toLanguageKey("item")).withStyle(ChatFormatting.GRAY));
        if (p_41421_.has(MarvelDataComponents.HELMET_OPEN)) p_41423_.add(Component.translatable(BuiltInRegistries.ITEM.getKey(this).withPath("iron_man.key.h" + (p_41421_.is(MarvelItems.Tags.INVISIBLE_WHEN_OPEN) ? ".invisible" : "")).toLanguageKey("item"), Component.keybind(MarvelKeyMappings.TOGGLE_HELMET.getName()).withStyle(ChatFormatting.BOLD)).withStyle(ChatFormatting.GRAY));
    }

    public int hudColor() {
        return 0x93F6FF;
    }
}
