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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.client.input.MarvelKeyMappings;
import net.tintankgames.marvel.client.model.MarvelModels;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

@EventBusSubscriber
public class ThorSuitItem extends SuitItem {
    private static final ResourceLocation THOR_ID = MarvelSuperheroes.id("thor");
    private static final AttributeModifier fallDamageMultiplierModifier = new AttributeModifier(THOR_ID, -1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

    public ThorSuitItem(Type type, Properties properties) {
        super(MarvelArmorMaterials.THOR, type, MarvelItems.Tags.THOR_ARMOR, type == Type.CHESTPLATE ? List.of(effect(MobEffects.JUMP, 1), effect(MobEffects.MOVEMENT_SPEED, 0)) : List.of(), properties);
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_339594_, p_41423_, p_41424_);
        if (type == Type.HELMET) p_41423_.add(Component.translatable(getDescriptionId(p_41421_).replace("_helmet", "") + ".key.h", Component.keybind(MarvelKeyMappings.TOGGLE_HELMET.getName()).withStyle(ChatFormatting.BOLD)).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public int getEntityLifespan(ItemStack itemStack, Level level) {
        return 1;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    protected ModelLayerLocation modelFactory(Type type, ItemStack itemStack) {
        return MarvelModels.thorSuit(type);
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, @Nullable T entity, Consumer<Item> onBroken) {
        return 0;
    }

    @SubscribeEvent
    public static void livingTick(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof LivingEntity living) {
            if (living.getItemBySlot(EquipmentSlot.HEAD).is(MarvelItems.Tags.THOR_ARMOR) && living.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.THOR_ARMOR) && living.getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.THOR_ARMOR) && living.getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.THOR_ARMOR)) {
                living.getAttribute(Attributes.FALL_DAMAGE_MULTIPLIER).addOrUpdateTransientModifier(fallDamageMultiplierModifier);
            } else {
                living.getAttribute(Attributes.FALL_DAMAGE_MULTIPLIER).removeModifier(fallDamageMultiplierModifier.id());
            }
        }
    }
}
