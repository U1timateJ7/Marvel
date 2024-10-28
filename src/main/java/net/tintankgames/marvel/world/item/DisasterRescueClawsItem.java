package net.tintankgames.marvel.world.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;

@EventBusSubscriber
public class DisasterRescueClawsItem extends SuitPowerItem {
    public DisasterRescueClawsItem(Properties properties) {
        super(properties.attributes(SwordItem.createAttributes(Tiers.IRON, 2, -2.8F)));
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity living, InteractionHand hand) {
        if ((living instanceof Mob || living instanceof Player) && living.getBoundingBox().getSize() <= 2.5 && player.getData(MarvelAttachmentTypes.HELD_ENTITY).entity == null) {
            if (!player.level().isClientSide()) player.getData(MarvelAttachmentTypes.HELD_ENTITY).entity = living;
            return InteractionResult.sidedSuccess(player.level().isClientSide());
        } else if (player.getData(MarvelAttachmentTypes.HELD_ENTITY).entity != null) {
            if (!player.level().isClientSide()) player.getData(MarvelAttachmentTypes.HELD_ENTITY).entity = null;
            return InteractionResult.CONSUME;
        } else {
            return super.interactLivingEntity(stack, player, living, hand);
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (player.getData(MarvelAttachmentTypes.HELD_ENTITY).entity != null) {
            if (!level.isClientSide()) player.getData(MarvelAttachmentTypes.HELD_ENTITY).entity = null;
            return InteractionResultHolder.consume(player.getItemInHand(hand));
        }
        return super.use(level, player, hand);
    }

    @SubscribeEvent
    public static void playerTick(PlayerTickEvent.Post event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            Entity entity = player.getData(MarvelAttachmentTypes.HELD_ENTITY).entity;
            if (entity instanceof LivingEntity living) {
                entity.moveTo(new Vec3(0, 0, 1).yRot(player.getViewYRot(1.0F) * -Mth.DEG_TO_RAD).xRot(player.getViewXRot(1.0F) * -Mth.DEG_TO_RAD).subtract(0, 0.5, 0).add(player.position()));
                entity.setDeltaMovement(0, 0, 0);
                entity.resetFallDistance();
                if (living instanceof Mob mob) {
                    mob.setTarget(null);
                    mob.goalSelector.getAvailableGoals().forEach(WrappedGoal::stop);
                }
            }
        }
    }
}
