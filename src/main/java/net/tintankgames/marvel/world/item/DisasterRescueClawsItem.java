package net.tintankgames.marvel.world.item;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.server.level.ServerPlayer;
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
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.client.MarvelClientEnumExtensions;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

@EventBusSubscriber
public class DisasterRescueClawsItem extends SuitPowerItem {
    public DisasterRescueClawsItem(Properties properties) {
        super(properties.attributes(SwordItem.createAttributes(Tiers.IRON, 2, -2.8F)));
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
    public static void interactLivingEntity(PlayerInteractEvent.EntityInteract event) {
        if (event.getItemStack().getItem() instanceof DisasterRescueClawsItem) {
            if ((event.getTarget() instanceof Mob || event.getTarget() instanceof Player) && event.getTarget().getBoundingBox().getSize() <= 2.5 && event.getEntity().getData(MarvelAttachmentTypes.HELD_ENTITY).entity == null) {
                if (!event.getEntity().level().isClientSide()) event.getEntity().getData(MarvelAttachmentTypes.HELD_ENTITY).entity = event.getTarget();
                event.setCancellationResult(InteractionResult.sidedSuccess(event.getEntity().level().isClientSide()));
                event.setCanceled(true);
            } else if (event.getEntity().getData(MarvelAttachmentTypes.HELD_ENTITY).entity != null) {
                if (!event.getEntity().level().isClientSide()) event.getEntity().getData(MarvelAttachmentTypes.HELD_ENTITY).entity = null;
                event.setCancellationResult(InteractionResult.CONSUME);
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void playerTick(PlayerTickEvent.Post event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            Entity entity = player.getData(MarvelAttachmentTypes.HELD_ENTITY).entity;
            if (entity instanceof LivingEntity living) {
                living.moveTo(player.position().add(0, 1.1 - (living.getBbHeight() / 2), 0).add(player.getViewVector(1.0F)));
                living.setDeltaMovement(0, 0, 0);
                living.resetFallDistance();
                if (living instanceof Mob mob) {
                    mob.setTarget(null);
                    mob.goalSelector.getAvailableGoals().forEach(WrappedGoal::stop);
                }
            }
        }
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_43417_) {
        return UseAnim.CUSTOM;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Nullable
            @Override
            public HumanoidModel.ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack stack) {
                return entityLiving.getItemInHand(hand) == stack && entityLiving.getData(MarvelAttachmentTypes.HELD_ENTITY).entity != null ? MarvelClientEnumExtensions.CLAWS_HOLD_POSE : IClientItemExtensions.super.getArmPose(entityLiving, hand, stack);
            }
        });
    }
}
