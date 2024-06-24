package net.tintankgames.marvel.world.item;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;
import net.tintankgames.marvel.sounds.MarvelSoundEvents;
import net.tintankgames.marvel.world.entity.projectile.WaspSting;

public class WaspStingItem extends SuitPowerItem implements ProjectileItem {
    public WaspStingItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide) {
            WaspSting waspSting = new WaspSting(level, player);
            waspSting.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);

            level.addFreshEntity(waspSting);
            level.playSound(null, waspSting.getX(), waspSting.getY(), waspSting.getZ(), MarvelSoundEvents.WASP_STING.get(), SoundSource.PLAYERS);
            if (!player.isCreative()) {
                player.getCooldowns().addCooldown(this, 15);
            }
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }

    @Override
    public Projectile asProjectile(Level p_338867_, Position p_338379_, ItemStack p_338543_, Direction p_338380_) {
        return new WaspSting(p_338867_, p_338379_.x(), p_338379_.y(), p_338379_.z());
    }
}
