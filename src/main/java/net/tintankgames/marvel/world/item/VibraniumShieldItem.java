package net.tintankgames.marvel.world.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.sounds.MarvelSoundEvents;
import net.tintankgames.marvel.world.entity.projectile.ThrownVibraniumShield;
import net.tintankgames.marvel.world.item.component.ShieldArt;

import java.util.function.Supplier;

public class VibraniumShieldItem extends Item implements ProjectileItem {
    public static final ResourceLocation BASE_KNOCKBACK_RESISTANCE_ID = MarvelSuperheroes.id("base_knockback_resistance");
    private final Supplier<Item> repairItem;

    public VibraniumShieldItem(Supplier<Item> repairItem, Properties properties) {
        super(properties.component(MarvelDataComponents.SHIELD_ART, ShieldArt.BLANK));
        this.repairItem = repairItem;
        DispenserBlock.registerProjectileBehavior(this);
    }

    @Override
    public String getDescriptionId(ItemStack stack) {
        return stack.getOrDefault(MarvelDataComponents.SHIELD_ART, ShieldArt.BLANK) == ShieldArt.BLANK ? super.getDescriptionId(stack) : BuiltInRegistries.ITEM.getKey(this).toLanguageKey("item", stack.getOrDefault(MarvelDataComponents.SHIELD_ART, ShieldArt.BLANK).getName());
    }

    public static ItemAttributeModifiers vibraniumAttributes() {
        return ItemAttributeModifiers.builder().add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 7.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, -3.0F, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(BASE_KNOCKBACK_RESISTANCE_ID, 0.5F, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.HAND).build();
    }

    public static ItemAttributeModifiers protoAdamantiumAttributes() {
        return ItemAttributeModifiers.builder().add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 13.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, -3.0F, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(BASE_KNOCKBACK_RESISTANCE_ID, 0.5F, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.HAND).build();
    }

    @Override
    public boolean canAttackBlock(BlockState p_43409_, Level p_43410_, BlockPos p_43411_, Player p_43412_) {
        return !p_43412_.isCreative();
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_43417_) {
        return UseAnim.CUSTOM;
    }

    @Override
    public int getUseDuration(ItemStack p_43419_, LivingEntity living) {
        return 72000;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity living, int timeLeft) {
        if (living instanceof Player player) {
            if (!level.isClientSide) {
                if (stack.isDamageableItem()) stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(living.getUsedItemHand()));
                ThrownVibraniumShield thrownVibraniumShield = new ThrownVibraniumShield(level, player, stack);
                thrownVibraniumShield.setBaseDamage(stack.get(DataComponents.ATTRIBUTE_MODIFIERS).modifiers().stream().filter(modifier -> modifier.attribute().is(Attributes.ATTACK_DAMAGE)).toList().getFirst().modifier().amount() + 1);
                thrownVibraniumShield.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);
                if (player.hasInfiniteMaterials()) {
                    thrownVibraniumShield.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                }

                level.addFreshEntity(thrownVibraniumShield);
                level.playSound(null, thrownVibraniumShield, MarvelSoundEvents.VIBRANIUM_SHIELD_THROW.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                if (!player.hasInfiniteMaterials()) {
                    player.getInventory().removeItem(stack);
                }
            }

            player.awardStat(Stats.ITEM_USED.get(this));
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_43405_, Player p_43406_, InteractionHand p_43407_) {
        ItemStack itemstack = p_43406_.getItemInHand(p_43407_);
        if (itemstack.isDamageableItem() && itemstack.getDamageValue() >= itemstack.getMaxDamage() - 1) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            p_43406_.startUsingItem(p_43407_);
            return InteractionResultHolder.consume(itemstack);
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack p_43390_, LivingEntity p_43391_, LivingEntity p_43392_) {
        if (p_43390_.isDamageableItem()) p_43390_.hurtAndBreak(1, p_43392_, EquipmentSlot.MAINHAND);
        return true;
    }

    @Override
    public int getEnchantmentValue() {
        return 1;
    }

    @Override
    public boolean isValidRepairItem(ItemStack p_41402_, ItemStack p_41403_) {
        return p_41403_.is(repairItem.get()) || super.isValidRepairItem(p_41402_, p_41403_);
    }

    @Override
    public Projectile asProjectile(Level p_338867_, Position p_338379_, ItemStack p_338543_, Direction p_338380_) {
        ThrownVibraniumShield vibraniumShield = new ThrownVibraniumShield(p_338867_, p_338379_.x(), p_338379_.y(), p_338379_.z(), p_338543_.copyWithCount(1));
        vibraniumShield.setBaseDamage(p_338543_.get(DataComponents.ATTRIBUTE_MODIFIERS).modifiers().stream().filter(modifier -> modifier.attribute().is(Attributes.ATTACK_DAMAGE)).toList().getFirst().modifier().amount() + 1);
        vibraniumShield.pickup = AbstractArrow.Pickup.ALLOWED;
        return vibraniumShield;
    }
}
