package net.tintankgames.marvel.world.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
import net.tintankgames.marvel.attachment.InfinityStone;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.core.particles.MarvelParticleTypes;
import net.tintankgames.marvel.core.particles.SpaceStoneParticleOptions;
import net.tintankgames.marvel.network.OpenSpaceStoneMessage;
import net.tintankgames.marvel.sounds.MarvelSoundEvents;

import java.util.function.Predicate;

public class TesseractItem extends BlockItem {
    public TesseractItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (player instanceof ServerPlayer serverPlayer) {
            if (serverPlayer.isSecondaryUseActive()) {
                PacketDistributor.sendToPlayer(serverPlayer, new OpenSpaceStoneMessage(Component.translatable("gui.space_stone.title", getDescription()), MarvelSoundEvents.TESSERACT_TELEPORT));
            } else {
                HitResult hit = getHitResult(player, entity1 -> entity1 instanceof LivingEntity, 100f, 0f);
                BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(hit.getLocation().x(), hit.getLocation().y(), hit.getLocation().z());
                if (hit instanceof BlockHitResult blockHit && blockHit.getType() == HitResult.Type.BLOCK) {
                    pos.move(blockHit.getDirection());
                }
                while (pos.getY() > level.getMinBuildHeight() && !level.getBlockState(pos.below()).blocksMotion()) {
                    pos.move(Direction.DOWN);
                }
                Vec3 vec3 = player.position();
                boolean flag2 = player.randomTeleport(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, false);
                if (flag2) {
                    if (level instanceof ServerLevel serverLevel) {
                        for (ServerPlayer player1 : serverLevel.players()) {
                            serverLevel.sendParticles(player1, new SpaceStoneParticleOptions(MarvelParticleTypes.SPACE_STONE_EMITTER.get(), 1, player.yHeadRot), true, vec3.x(), vec3.y() + player.getBbHeight() * 0.5, vec3.z(), 0, 0, 0, 0, 1);
                        }
                    }
                    player.level().gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(player));
                    if (!player.isSilent()) {
                        player.level().playSound(null, player.xo, player.yo, player.zo, MarvelSoundEvents.TESSERACT_TELEPORT.get(), player.getSoundSource(), 0.6F, 1.0F);
                        player.playSound(MarvelSoundEvents.TESSERACT_TELEPORT.get(), 0.6F, 1.0F);
                    }
                    if (level instanceof ServerLevel serverLevel) {
                        for (ServerPlayer player1 : serverLevel.players()) {
                            serverLevel.sendParticles(player1, new SpaceStoneParticleOptions(MarvelParticleTypes.REVERSE_SPACE_STONE_EMITTER.get(), 1, player.yHeadRot), true, player.getX(), player.getY(0.5), player.getZ(), 0, 0, 0, 0, 1);
                        }
                    }
                    player.getCooldowns().addCooldown(this, 60);
                }
            }
        }
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }

    private static HitResult getHitResult(Entity entity, Predicate<Entity> predicate, double d, float rotation) {
        Vec3 vec33 = entity.position().add(0, 1.25, 0).add(entity.getViewVector(0.0F).yRot(rotation).scale(d));
        HitResult hitResult = entity.level().clip(new ClipContext(entity.getEyePosition(), vec33, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity));
        if (hitResult.getType() != HitResult.Type.MISS) {
            vec33 = hitResult.getLocation();
        }

        HitResult hitResult2 = ProjectileUtil.getEntityHitResult(entity.level(), entity, entity.getEyePosition(), vec33, entity.getBoundingBox().expandTowards(entity.getViewVector(0.0F).scale(d)).inflate(1.0), predicate);
        if (hitResult2 != null) {
            hitResult = hitResult2;
        }

        return hitResult;
    }

    @Override
    public void onDestroyed(ItemEntity itemEntity, DamageSource damageSource) {
        if (itemEntity.level() instanceof ServerLevel level) {
            level.getServer().overworld().getData(MarvelAttachmentTypes.INFINITY_STONES).setFoundStone(InfinityStone.SPACE, false);
        }
    }
}
