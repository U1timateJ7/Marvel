package net.tintankgames.marvel.attachment;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.*;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;
import org.joml.Vector3f;

import java.util.Map;
import java.util.function.IntFunction;
import java.util.function.Supplier;

@EventBusSubscriber
public enum EntitySuit implements StringRepresentable {
    NONE("none", MarvelSuperheroes.id("models/suit/empty"), () -> null, new Vector3f(0.0F, 0.0F, 0.0F), null),
    IRON_MAN_MARK_38("iron_man_mark_38", MarvelSuperheroes.id("entity/igor/iron_man_mark_38"), MarvelEntityTypes.IRON_MAN_MARK_38::get, new Vector3f(0.0F, -5.75F, -6.0F), ImmutableMap.<Pose, EntityDimensions>builder().put(Pose.STANDING, EntityDimensions.scalable(0.8F, 2.25F).withEyeHeight(2.07F).withAttachments(EntityAttachments.builder().attach(EntityAttachment.VEHICLE, new Vec3(0.0, 1.65, 0.0)))).put(Pose.SLEEPING, EntityDimensions.fixed(0.2F, 0.2F).withEyeHeight(0.2F)).put(Pose.FALL_FLYING, EntityDimensions.scalable(0.8F, 0.8F).withEyeHeight(0.6F)).put(Pose.SWIMMING, EntityDimensions.scalable(0.8F, 0.8F).withEyeHeight(0.6F)).put(Pose.SPIN_ATTACK, EntityDimensions.scalable(0.8F, 0.8F).withEyeHeight(0.6F)).put(Pose.CROUCHING, EntityDimensions.scalable(0.8F, 1.95F).withEyeHeight(1.72F).withAttachments(EntityAttachments.builder().attach(EntityAttachment.VEHICLE, new Vec3(0.0, 0.8, 0.0)))).put(Pose.DYING, EntityDimensions.fixed(0.2F, 0.2F).withEyeHeight(2.07F)).build());

    private static final IntFunction<EntitySuit> BY_ID = ByIdMap.continuous(EntitySuit::ordinal, EntitySuit.values(), ByIdMap.OutOfBoundsStrategy.ZERO);
    public static final Codec<EntitySuit> CODEC = StringRepresentable.fromEnum(EntitySuit::values);
    public static final StreamCodec<ByteBuf, EntitySuit> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, EntitySuit::ordinal);
    private final String name;
    private final ResourceLocation texture;
    private final Supplier<EntityType<? extends TamableAnimal>> type;
    private final Vector3f headOffset;
    private final Map<Pose, EntityDimensions> dimensions;

    EntitySuit(String name, ResourceLocation texture, Supplier<EntityType<? extends TamableAnimal>> type, Vector3f headOffset, Map<Pose, EntityDimensions> dimensions) {
        this.name = name;
        this.texture = texture;
        this.type = type;
        this.headOffset = headOffset;
        this.dimensions = dimensions;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    public ResourceLocation texture() {
        return texture.withPath(path -> "textures/" + path + ".png");
    }

    public ResourceLocation openTexture() {
        return this == NONE ? texture() : texture.withPath(path -> "textures/" + path + "_open.png");
    }

    public EntityType<? extends TamableAnimal> type() {
        return type.get();
    }

    public Vector3f headOffset() {
        return headOffset;
    }

    public Map<Pose, EntityDimensions> dimensions() {
        return dimensions;
    }

    @SubscribeEvent
    public static void tick(PlayerTickEvent.Post event) {
        event.getEntity().refreshDimensions();
    }
}
