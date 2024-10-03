package net.tintankgames.marvel.world.item;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.sounds.MarvelSoundEvents;
import net.tintankgames.marvel.world.level.KineticExplosionDamageCalculator;
import org.joml.Vector3f;

public class SonicBoomItem extends SuitPowerItem {
    public SonicBoomItem(Properties properties) {
        super(properties);
    }

    protected boolean needsHead() {
        return false;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (player.getData(MarvelAttachmentTypes.MOVING) && player.getItemBySlot(EquipmentSlot.CHEST).has(MarvelDataComponents.SPEEDING)) {
            if (level instanceof ServerLevel serverLevel) {
                level.explode(player, player.damageSources().explosion(player, player), new KineticExplosionDamageCalculator(-1, player), player.getX(), player.getY(), player.getZ(), 6, false, Level.ExplosionInteraction.TRIGGER, new DustParticleOptions(new Vector3f(0, 0, 0), 0), new DustParticleOptions(new Vector3f(0, 0, 0), 0), MarvelSoundEvents.QUICKSILVER_SONIC_BOOM);
                serverLevel.sendParticles(ParticleTypes.FLASH, player.getX(), player.getY(), player.getZ(), 4, 0, 0, 0, 0);
                player.addEffect(SuitItem.effect(MobEffects.MOVEMENT_SPEED, 15, 40));
            }
            if (!player.isCreative()) player.getCooldowns().addCooldown(this, 1200);
            return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
        }
        return super.use(level, player, hand);
    }
}
