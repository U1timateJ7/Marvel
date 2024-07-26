package net.tintankgames.marvel.mixin;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.levelgen.Beardifier;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.JigsawJunction;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.neoforged.neoforge.common.world.PieceBeardifierModifier;
import net.tintankgames.marvel.world.level.levelgen.structure.pools.MarvelStructurePoolElementTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Beardifier.class)
public class BeardifierMixin {
    @Inject(at = @At("HEAD"), method = "forStructuresInChunk", cancellable = true)
    private static void fixTheHydraBeard(StructureManager p_223938_, ChunkPos p_223939_, CallbackInfoReturnable<Beardifier> cir) {
        int i = p_223939_.getMinBlockX();
        int j = p_223939_.getMinBlockZ();
        ObjectList<Beardifier.Rigid> objectlist = new ObjectArrayList<>(10);
        ObjectList<JigsawJunction> objectlist1 = new ObjectArrayList<>(32);
        p_223938_.startsForStructure(p_223939_, p_223941_ -> p_223941_.terrainAdaptation() != TerrainAdjustment.NONE)
                .forEach(
                        p_223936_ -> {
                            TerrainAdjustment terrainadjustment = p_223936_.getStructure().terrainAdaptation();

                            for (StructurePiece structurepiece : p_223936_.getPieces()) {
                                if (structurepiece.isCloseToChunk(p_223939_, 12)) {
                                    if (structurepiece instanceof PieceBeardifierModifier pieceBeardifierModifier) {
                                        if (pieceBeardifierModifier.getTerrainAdjustment() != TerrainAdjustment.NONE) {
                                            objectlist.add(new Beardifier.Rigid(pieceBeardifierModifier.getBeardifierBox(), pieceBeardifierModifier.getTerrainAdjustment(), pieceBeardifierModifier.getGroundLevelDelta()));
                                        }
                                    } else if (structurepiece instanceof PoolElementStructurePiece poolelementstructurepiece) {
                                        if (poolelementstructurepiece.getElement().getType() != MarvelStructurePoolElementTypes.UNDERGROUND.get()) {
                                            StructureTemplatePool.Projection structuretemplatepool$projection = poolelementstructurepiece.getElement().getProjection();
                                            if (structuretemplatepool$projection == StructureTemplatePool.Projection.RIGID) {
                                                objectlist.add(
                                                        new Beardifier.Rigid(
                                                                poolelementstructurepiece.getBoundingBox(), terrainadjustment, poolelementstructurepiece.getGroundLevelDelta()
                                                        )
                                                );
                                            }

                                            for (JigsawJunction jigsawjunction : poolelementstructurepiece.getJunctions()) {
                                                int k = jigsawjunction.getSourceX();
                                                int l = jigsawjunction.getSourceZ();
                                                if (k > i - 12 && l > j - 12 && k < i + 15 + 12 && l < j + 15 + 12) {
                                                    objectlist1.add(jigsawjunction);
                                                }
                                            }
                                        }
                                    } else {
                                        objectlist.add(new Beardifier.Rigid(structurepiece.getBoundingBox(), terrainadjustment, 0));
                                    }
                                }
                            }
                        }
                );
        cir.setReturnValue(new Beardifier(objectlist.iterator(), objectlist1.iterator()));
    }
}
