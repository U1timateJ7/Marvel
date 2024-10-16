package net.tintankgames.marvel.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.client.input.MarvelKeyMappings;
import net.tintankgames.marvel.client.model.MarvelModels;
import net.tintankgames.marvel.core.components.MarvelDataComponents;

import java.util.List;

@EventBusSubscriber
public class SpiderManSuitItem extends LeatherSuitItem {
    private static final ResourceLocation SPIDER_MAN_ID = MarvelSuperheroes.id("spider_man");
    private static final AttributeModifier safeFallDistanceModifier = new AttributeModifier(SPIDER_MAN_ID, 3, AttributeModifier.Operation.ADD_VALUE);
    private static final AttributeModifier fallDamageMultiplierModifier = new AttributeModifier(SPIDER_MAN_ID, -0.5, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);

    public SpiderManSuitItem(Type type, Properties properties) {
        super(type, MarvelItems.Tags.SPIDER_MAN_ARMOR, type == Type.CHESTPLATE ? List.of(effect(MobEffects.JUMP, 2), effect(MobEffects.MOVEMENT_SPEED, 0), effect(MobEffects.DAMAGE_BOOST, 0)) : List.of(), properties.component(MarvelDataComponents.POWER_ITEMS, List.of(MarvelItems.WEB_SHOOTER.get())));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ModelLayerLocation modelFactory(Type type, ItemStack itemStack) {
        return MarvelModels.spiderManSuit(type);
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_339594_, p_41423_, p_41424_);
        if (type == Type.HELMET) p_41423_.add(Component.translatable(getDescriptionId(p_41421_).replace("_helmet", "") + ".key.v", Component.keybind(MarvelKeyMappings.PRIMARY_SUIT_ABILITY.getName()).withStyle(ChatFormatting.BOLD)).withStyle(ChatFormatting.GRAY));
    }

    @SubscribeEvent
    public static void livingTick(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof LivingEntity living) {
            if (living.getItemBySlot(EquipmentSlot.CHEST).has(MarvelDataComponents.SPIDER_SENSE)) {
                living.getItemBySlot(EquipmentSlot.CHEST).update(MarvelDataComponents.SPIDER_SENSE, 1, timer -> timer - 1);
                if (living.getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.SPIDER_SENSE, 0) <= 0) {
                    living.getItemBySlot(EquipmentSlot.CHEST).remove(MarvelDataComponents.SPIDER_SENSE);
                }
            }
            if (living.getItemBySlot(EquipmentSlot.HEAD).is(MarvelItems.Tags.SPIDER_MAN_ARMOR) && living.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.SPIDER_MAN_ARMOR) && living.getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.SPIDER_MAN_ARMOR) && living.getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.SPIDER_MAN_ARMOR)) {
                living.getAttribute(Attributes.SAFE_FALL_DISTANCE).addOrUpdateTransientModifier(safeFallDistanceModifier);
                living.getAttribute(Attributes.FALL_DAMAGE_MULTIPLIER).addOrUpdateTransientModifier(fallDamageMultiplierModifier);
            } else {
                living.getAttribute(Attributes.SAFE_FALL_DISTANCE).removeModifier(safeFallDistanceModifier.id());
                living.getAttribute(Attributes.FALL_DAMAGE_MULTIPLIER).removeModifier(fallDamageMultiplierModifier.id());
            }
        }
    }
}
