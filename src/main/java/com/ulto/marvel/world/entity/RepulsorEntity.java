
package com.ulto.marvel.world.entity;

import com.ulto.marvel.sounds.MarvelModSounds;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.Packet;

import java.util.Random;

import com.ulto.marvel.world.item.MarvelModItems;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class RepulsorEntity extends AbstractArrow implements ItemSupplier {
	public RepulsorEntity(PlayMessages.SpawnEntity packet, Level world) {
		super(MarvelModEntityTypes.REPULSOR.get(), world);
	}

	public RepulsorEntity(EntityType<? extends RepulsorEntity> type, Level world) {
		super(type, world);
	}

	public RepulsorEntity(EntityType<? extends RepulsorEntity> type, double x, double y, double z, Level world) {
		super(type, x, y, z, world);
	}

	public RepulsorEntity(EntityType<? extends RepulsorEntity> type, LivingEntity entity, Level world) {
		super(type, entity, world);
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public ItemStack getItem() {
		return new ItemStack(MarvelModItems.REPULSOR_BULLET.get());
	}

	@Override
	protected ItemStack getPickupItem() {
		return null;
	}

	@Override
	protected void doPostHurtEffects(LivingEntity entity) {
		super.doPostHurtEffects(entity);
		entity.setArrowCount(entity.getArrowCount() - 1);
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
			this.discard();
		}
	}

	public static RepulsorEntity shoot(Level world, LivingEntity entity, Random random, float power, double damage, int knockback) {
		RepulsorEntity entityarrow = new RepulsorEntity(MarvelModEntityTypes.REPULSOR.get(), entity, world);
		entityarrow.shoot(entity.getLookAngle().x, entity.getLookAngle().y, entity.getLookAngle().z, power * 2, 0);
		entityarrow.setSilent(true);
		entityarrow.setCritArrow(false);
		entityarrow.setBaseDamage(damage);
		entityarrow.setKnockback(knockback);
		world.addFreshEntity(entityarrow);
		world.playSound((Player) null, entity.getX(), entity.getY(), entity.getZ(),
				MarvelModSounds.get(new ResourceLocation("marvel:item.repulsor.shoot")), SoundSource.PLAYERS, 1,
				1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
		return entityarrow;
	}

	public static RepulsorEntity shoot(LivingEntity entity, LivingEntity target) {
		RepulsorEntity entityarrow = new RepulsorEntity(MarvelModEntityTypes.REPULSOR.get(), entity, entity.level);
		double d0 = target.getY() + (double) target.getEyeHeight() - 1.1;
		double d1 = target.getX() - entity.getX();
		double d3 = target.getZ() - entity.getZ();
		entityarrow.shoot(d1, d0 - entityarrow.getY() + Math.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 2f * 2, 12.0F);
		entityarrow.setSilent(true);
		entityarrow.setBaseDamage(2);
		entityarrow.setKnockback(2);
		entityarrow.setCritArrow(false);
		entity.level.addFreshEntity(entityarrow);
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		entity.level.playSound((Player) null, (double) x, (double) y, (double) z,
				MarvelModSounds.get(new ResourceLocation("marvel:item.repulsor.shoot")), SoundSource.PLAYERS, 1,
				1f / (new Random().nextFloat() * 0.5f + 1));
		return entityarrow;
	}
}
