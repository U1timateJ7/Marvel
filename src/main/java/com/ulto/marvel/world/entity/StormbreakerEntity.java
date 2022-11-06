
package com.ulto.marvel.world.entity;

import com.ulto.marvel.world.item.MarvelModItems;
import com.ulto.marvel.sounds.MarvelModSounds;
import com.ulto.marvel.procedures.StormbreakerHitsBlockProcedure;
import com.ulto.marvel.procedures.StormbreakerHitsLivingEntityProcedure;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
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
public class StormbreakerEntity extends AbstractArrow implements ItemSupplier {
	public StormbreakerEntity(PlayMessages.SpawnEntity packet, Level world) {
		super(MarvelModEntityTypes.STORMBREAKER.get(), world);
	}

	public StormbreakerEntity(EntityType<? extends StormbreakerEntity> type, Level world) {
		super(type, world);
	}

	public StormbreakerEntity(EntityType<? extends StormbreakerEntity> type, double x, double y, double z, Level world) {
		super(type, x, y, z, world);
	}

	public StormbreakerEntity(EntityType<? extends StormbreakerEntity> type, LivingEntity entity, Level world) {
		super(type, entity, world);
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public ItemStack getItem() {
		return new ItemStack(MarvelModItems.STORMBREAKER.get());
	}

	@Override
	protected ItemStack getPickupItem() {
		return null;
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

		StormbreakerHitsLivingEntityProcedure.execute(world, x, y, z, sourceentity);
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

			StormbreakerHitsBlockProcedure.execute(world, x, y, z, entity);
			this.discard();
		}
	}

	public static StormbreakerEntity shoot(Level world, LivingEntity entity, Random random, float power, double damage, int knockback) {
		StormbreakerEntity entityarrow = new StormbreakerEntity(MarvelModEntityTypes.STORMBREAKER.get(), entity, world);
		entityarrow.shoot(entity.getLookAngle().x, entity.getLookAngle().y, entity.getLookAngle().z, power * 2, 0);
		entityarrow.setSilent(true);
		entityarrow.setCritArrow(false);
		entityarrow.setBaseDamage(damage);
		entityarrow.setKnockback(knockback);
		world.addFreshEntity(entityarrow);
		world.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
				MarvelModSounds.get(new ResourceLocation("marvel:item.stormbreaker.throw")), SoundSource.PLAYERS, 1,
				1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
		return entityarrow;
	}

	public static StormbreakerEntity shoot(LivingEntity entity, LivingEntity target) {
		StormbreakerEntity entityarrow = new StormbreakerEntity(MarvelModEntityTypes.STORMBREAKER.get(), entity, entity.level);
		double d0 = target.getY() + (double) target.getEyeHeight() - 1.1;
		double d1 = target.getX() - entity.getX();
		double d3 = target.getZ() - entity.getZ();
		entityarrow.shoot(d1, d0 - entityarrow.getY() + Math.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 2f * 2, 12.0F);
		entityarrow.setSilent(true);
		entityarrow.setBaseDamage(3);
		entityarrow.setKnockback(2);
		entityarrow.setCritArrow(false);
		entity.level.addFreshEntity(entityarrow);
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		entity.level.playSound(null, x, y, z,
				MarvelModSounds.get(new ResourceLocation("marvel:item.stormbreaker.throw")), SoundSource.PLAYERS, 1,
				1f / (new Random().nextFloat() * 0.5f + 1));
		return entityarrow;
	}
}
