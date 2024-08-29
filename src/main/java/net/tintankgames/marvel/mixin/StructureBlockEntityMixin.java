package net.tintankgames.marvel.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.StructureBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.StructureMode;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StructureBlockEntity.class)
public abstract class StructureBlockEntityMixin extends BlockEntity {
    @Shadow public abstract void setStructureName(@Nullable String p_59869_);
    @Shadow private String author;
    @Shadow private String metaData;
    @Shadow private BlockPos structurePos;
    @Shadow private Vec3i structureSize;
    @Shadow private Rotation rotation;
    @Shadow private Mirror mirror;
    @Shadow private StructureMode mode;
    @Shadow private boolean ignoreEntities;
    @Shadow private boolean powered;
    @Shadow private boolean showAir;
    @Shadow private boolean showBoundingBox;
    @Shadow private float integrity;
    @Shadow private long seed;
    @Shadow protected abstract void updateBlockState();

    public StructureBlockEntityMixin(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_) {
        super(p_155228_, p_155229_, p_155230_);
    }

    @Inject(at = @At("HEAD"), method = "loadAdditional", cancellable = true)
    private void sizeFix(CompoundTag p_155800_, HolderLookup.Provider p_324076_, CallbackInfo ci) {
        super.loadAdditional(p_155800_, p_324076_);
        this.setStructureName(p_155800_.getString("name"));
        this.author = p_155800_.getString("author");
        this.metaData = p_155800_.getString("metadata");
        int i = p_155800_.getInt("posX");
        int j = p_155800_.getInt("posY");
        int k = p_155800_.getInt("posZ");
        this.structurePos = new BlockPos(i, j, k);
        int l = Mth.clamp(p_155800_.getInt("sizeX"), 0, Integer.MAX_VALUE);
        int i1 = Mth.clamp(p_155800_.getInt("sizeY"), 0, Integer.MAX_VALUE);
        int j1 = Mth.clamp(p_155800_.getInt("sizeZ"), 0, Integer.MAX_VALUE);
        this.structureSize = new Vec3i(l, i1, j1);

        try {
            this.rotation = Rotation.valueOf(p_155800_.getString("rotation"));
        } catch (IllegalArgumentException illegalargumentexception2) {
            this.rotation = Rotation.NONE;
        }

        try {
            this.mirror = Mirror.valueOf(p_155800_.getString("mirror"));
        } catch (IllegalArgumentException illegalargumentexception1) {
            this.mirror = Mirror.NONE;
        }

        try {
            this.mode = StructureMode.valueOf(p_155800_.getString("mode"));
        } catch (IllegalArgumentException illegalargumentexception) {
            this.mode = StructureMode.DATA;
        }

        this.ignoreEntities = p_155800_.getBoolean("ignoreEntities");
        this.powered = p_155800_.getBoolean("powered");
        this.showAir = p_155800_.getBoolean("showair");
        this.showBoundingBox = p_155800_.getBoolean("showboundingbox");
        if (p_155800_.contains("integrity")) {
            this.integrity = p_155800_.getFloat("integrity");
        } else {
            this.integrity = 1.0F;
        }

        this.seed = p_155800_.getLong("seed");
        this.updateBlockState();
        ci.cancel();
    }
}
