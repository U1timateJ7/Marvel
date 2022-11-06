
package com.ulto.marvel.world.entity;

import com.ulto.marvel.world.item.MarvelModItems;
import com.ulto.marvel.sounds.MarvelModSounds;
import com.ulto.marvel.procedures.VibraniumShieldHitsBlockProcedure;
import com.ulto.marvel.procedures.VibraniumShieldHitsLivingEntityProcedure;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

import java.util.Random;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class VibraniumShieldEntity extends AbstractArrow implements ItemSupplier {
	public VibraniumShieldEntity(PlayMessages.SpawnEntity packet, Level world) {
		super(MarvelModEntityTypes.VIBRANIUM_SHIELD.get(), world);
	}

	public VibraniumShieldEntity(EntityType<? extends VibraniumShieldEntity> type, Level world) {
		super(type, world);
	}

	public VibraniumShieldEntity(EntityType<? extends VibraniumShieldEntity> type, double x, double y, double z, Level world) {
		super(type, x, y, z, world);
	}

	public VibraniumShieldEntity(EntityType<? extends VibraniumShieldEntity> type, LivingEntity entity, Level world) {
		super(type, entity, world);
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public ItemStack getItem() {
		return new ItemStack(MarvelModItems.VIBRANIUM_SHIELD.get());
	}

	@Override
	protected ItemStack getPickupItem() {
		return new ItemStack(MarvelModItems.VIBRANIUM_SHIELD.get());
	}

	@Override
	protected void doPostHurtEffects(LivingEntity entity) {
		super.doPostHurtEffects(entity);
		entity.setArrowCount(entity.getArrowCount() - 1);
		Entity sourceentity = this.getOwner();
		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();
		Level world = this.level;
		Entity imediatesourceentity = this;

		VibraniumShieldHitsLivingEntityProcedure.execute(world, x, y, z, imediatesourceentity, sourceentity);
	}

	@Override
	public void tick() {
		super.tick();
		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();
		Level world = this.level;
		Entity entity = this.getOwner();
		Entity imediatesourceentity = this;
		if (this.inGround) {

			VibraniumShieldHitsBlockProcedure.execute(entity, imediatesourceentity);
			this.discard();
		}
	}

	public static VibraniumShieldEntity shoot(Level world, LivingEntity entity, Random random, float power, double damage, int knockback) {
		VibraniumShieldEntity entityarrow = new VibraniumShieldEntity(MarvelModEntityTypes.VIBRANIUM_SHIELD.get(), entity, world);
		entityarrow.shoot(entity.getLookAngle().x, entity.getLookAngle().y, entity.getLookAngle().z, power * 2, 0);
		entityarrow.setSilent(true);
		entityarrow.setCritArrow(false);
		entityarrow.setBaseDamage(damage);
		entityarrow.setKnockback(knockback);
		world.addFreshEntity(entityarrow);
		world.playSound((Player) null, entity.getX(), entity.getY(), entity.getZ(),
				MarvelModSounds.get(new ResourceLocation("marvel:item.vibranium_shield.throw")), SoundSource.PLAYERS, 1,
				1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
		return entityarrow;
	}

	public static VibraniumShieldEntity shoot(LivingEntity entity, LivingEntity target) {
		VibraniumShieldEntity entityarrow = new VibraniumShieldEntity(MarvelModEntityTypes.VIBRANIUM_SHIELD.get(), entity, entity.level);
		double d0 = target.getY() + (double) target.getEyeHeight() - 1.1;
		double d1 = target.getX() - entity.getX();
		double d3 = target.getZ() - entity.getZ();
		entityarrow.shoot(d1, d0 - entityarrow.getY() + Math.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 1f * 2, 12.0F);
		entityarrow.setSilent(true);
		entityarrow.setBaseDamage(6);
		entityarrow.setKnockback(1);
		entityarrow.setCritArrow(false);
		entity.level.addFreshEntity(entityarrow);
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		entity.level.playSound((Player) null, (double) x, (double) y, (double) z,
				MarvelModSounds.get(new ResourceLocation("marvel:item.vibranium_shield.throw")), SoundSource.PLAYERS, 1,
				1f / (new Random().nextFloat() * 0.5f + 1));
		return entityarrow;
	}
}
