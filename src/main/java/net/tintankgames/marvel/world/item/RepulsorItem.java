package net.tintankgames.marvel.world.item;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;
import net.tintankgames.marvel.sounds.MarvelSoundEvents;
import net.tintankgames.marvel.world.entity.projectile.Repulsor;

public class RepulsorItem extends SuitPowerItem implements ProjectileItem {
    public RepulsorItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (EnergySuitItem.getEnergy(player.getItemBySlot(EquipmentSlot.CHEST)) >= 0.5F || player.isCreative()) {
            player.startUsingItem(hand);
            level.playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.IRON_MAN_REPULSOR_CHARGE.get(), SoundSource.PLAYERS);
            return InteractionResultHolder.consume(stack);
        }
        return InteractionResultHolder.pass(stack);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity living, int ticksLeft) {
        super.releaseUsing(stack, level, living, ticksLeft);
        if (living instanceof Player player) {
            if (!level.isClientSide && (EnergySuitItem.getEnergy(player.getItemBySlot(EquipmentSlot.CHEST)) >= 0.5F || player.isCreative())) {
                Repulsor repulsor = new Repulsor(level, player);
                repulsor.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);

                level.addFreshEntity(repulsor);
                level.playSound(null, repulsor.getX(), repulsor.getY(), repulsor.getZ(), MarvelSoundEvents.IRON_MAN_REPULSOR_SHOOT.get(), SoundSource.PLAYERS);
                if (!player.isCreative()) {
                    player.getCooldowns().addCooldown(this, 15);
                    player.getInventory().armor.forEach(stack1 -> EnergySuitItem.removeEnergy(stack1, 0.5F));
                }
            }
            player.awardStat(Stats.ITEM_USED.get(this));
        }
    }

    @Override
    public int getUseDuration(ItemStack p_43419_, LivingEntity living) {
        return 72000;
    }

    @Override
    public Projectile asProjectile(Level p_338867_, Position p_338379_, ItemStack p_338543_, Direction p_338380_) {
        return new Repulsor(p_338867_, p_338379_.x(), p_338379_.y(), p_338379_.z());
    }
}
