package net.tintankgames.marvel.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.tintankgames.marvel.attachment.EntityHolder;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.core.particles.EmissiveDustParticleOptions;
import net.tintankgames.marvel.world.entity.projectile.WebShot;
import org.joml.Vector3f;

public class WebShooterItem extends SuitPowerItem {
    public WebShooterItem(Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        WebShot webShot = player.getData(MarvelAttachmentTypes.GRAPPLING).entity;
        if (webShot != null) {
            retrieve(level, player, webShot);
        } else {
            if (player.getInventory().contains(stack -> stack.is(MarvelItems.WEB_FLUID)) || player.isCreative()) {
                this.shoot(level, player);
            } else if (level instanceof ServerLevel serverLevel) {
                Vec3 look = player.getLookAngle().scale(0.5);
                serverLevel.sendParticles(new EmissiveDustParticleOptions(new Vector3f(1, 1, 1), 0.2f), player.getX() + look.x(), player.getY() + look.y() + 1.3, player.getZ() + look.z(), 10, 0.1, 0.1, 0.1, 0);
                player.displayClientMessage(Component.translatable("item.marvel.web_shooter.out_of_fluid").withStyle(ChatFormatting.RED), true);
            }
        }

        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide);
    }

    private void shoot(Level level, Player player) {
        if (!level.isClientSide) {
            level.addFreshEntity(new WebShot(level, player));
            if (player.getRandom().nextInt(3) == 0 && !player.isCreative()) player.getInventory().removeItem(SuitItem.findSlotMatchingItem(player.getInventory().items, MarvelItems.WEB_FLUID.get()), 1);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        player.gameEvent(GameEvent.ITEM_INTERACT_START);
    }

    private static void retrieve(Level level, Player player, WebShot lashingPotatoHook) {
        if (!level.isClientSide()) {
            lashingPotatoHook.discard();
            player.setData(MarvelAttachmentTypes.GRAPPLING, new EntityHolder<>(null));
        }

        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_RETRIEVE, SoundSource.NEUTRAL, 1.0F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        player.gameEvent(GameEvent.ITEM_INTERACT_FINISH);
    }
}
