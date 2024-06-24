package net.tintankgames.marvel.world.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.sounds.MarvelSoundEvents;
import net.tintankgames.marvel.world.entity.projectile.ThrownMjolnir;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;
import net.tintankgames.marvel.world.level.block.entity.MarvelBlockEntityTypes;

import java.util.Objects;

public class MjolnirItem extends Item implements ProjectileItem {
    public MjolnirItem(Properties properties) {
        super(properties);
    }

    public static ItemAttributeModifiers attributes() {
        return ItemAttributeModifiers.builder().add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", 11.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", -3.0F, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build();
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);
        if (p_41406_ instanceof Player player) {
            if (!p_41404_.has(MarvelDataComponents.OWNER)) {
                p_41404_.set(MarvelDataComponents.OWNER, player.getUUID());
            } else if (!Objects.equals(p_41404_.get(MarvelDataComponents.OWNER).toString(), player.getUUID().toString())) {
                player.drop(p_41404_.copy(), true);
                p_41404_.shrink(p_41404_.getCount());
            }
        }
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        entity.level().destroyBlock(entity.blockPosition(), true, stack.has(MarvelDataComponents.OWNER) ? entity.level().getPlayerByUUID(stack.get(MarvelDataComponents.OWNER)) : null, 512);
        entity.level().setBlockAndUpdate(entity.blockPosition(), MarvelBlocks.MJOLNIR.get().defaultBlockState());
        entity.level().getBlockEntity(entity.blockPosition(), MarvelBlockEntityTypes.MJOLNIR.get()).ifPresent(blockEntity -> {
            blockEntity.setStack(stack);
            if (stack.has(MarvelDataComponents.OWNER)) blockEntity.setOwner(stack.get(MarvelDataComponents.OWNER));
        });
        entity.discard();
        return true;
    }

    @Override
    public boolean canAttackBlock(BlockState p_43409_, Level p_43410_, BlockPos p_43411_, Player p_43412_) {
        return !p_43412_.isCreative();
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_43417_) {
        return UseAnim.SPEAR;
    }

    @Override
    public int getUseDuration(ItemStack p_43419_) {
        return 72000;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity living, int timeLeft) {
        if (living instanceof Player player) {
            if (!level.isClientSide) {
                if (stack.isDamageableItem()) stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(living.getUsedItemHand()));
                ThrownMjolnir thrownMjolnir = new ThrownMjolnir(level, player, stack);
                thrownMjolnir.setBaseDamage(stack.get(DataComponents.ATTRIBUTE_MODIFIERS).modifiers().stream().filter(modifier -> modifier.attribute().is(Attributes.ATTACK_DAMAGE)).toList().getFirst().modifier().amount() + 1);
                thrownMjolnir.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);
                if (player.hasInfiniteMaterials()) {
                    thrownMjolnir.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                }
                level.addFreshEntity(thrownMjolnir);
                level.playSound(null, thrownMjolnir, MarvelSoundEvents.MJOLNIR_THROW.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
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
        return 0;
    }

    @Override
    public Projectile asProjectile(Level p_338867_, Position p_338379_, ItemStack p_338543_, Direction p_338380_) {
        ThrownMjolnir mjolnir = new ThrownMjolnir(p_338867_, p_338379_.x(), p_338379_.y(), p_338379_.z(), p_338543_.copyWithCount(1));
        mjolnir.setBaseDamage(p_338543_.get(DataComponents.ATTRIBUTE_MODIFIERS).modifiers().stream().filter(modifier -> modifier.attribute().is(Attributes.ATTACK_DAMAGE)).toList().getFirst().modifier().amount() + 1);
        mjolnir.pickup = AbstractArrow.Pickup.ALLOWED;
        return mjolnir;
    }
}
