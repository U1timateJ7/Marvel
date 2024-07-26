package net.tintankgames.marvel.world.level.levelgen.structure.pools;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class UndergroundPoolElement extends SinglePoolElement {
    private static final Holder<StructureProcessorList> EMPTY = Holder.direct(new StructureProcessorList(List.of()));
    public static final MapCodec<UndergroundPoolElement> CODEC = RecordCodecBuilder.mapCodec(p_352015_ -> p_352015_.group(templateCodec(), processorsCodec(), projectionCodec(), overrideLiquidSettingsCodec()).apply(p_352015_, UndergroundPoolElement::new));

    protected UndergroundPoolElement(Either<ResourceLocation, StructureTemplate> template, Holder<StructureProcessorList> processors, StructureTemplatePool.Projection projection, Optional<LiquidSettings> overrideLiquidSettings) {
        super(template, processors, projection, overrideLiquidSettings);
    }

    public static Function<StructureTemplatePool.Projection, UndergroundPoolElement> create(String template) {
        return projection -> new UndergroundPoolElement(Either.left(ResourceLocation.parse(template)), EMPTY, projection, Optional.empty());
    }

    public static Function<StructureTemplatePool.Projection, UndergroundPoolElement> create(String template, Holder<StructureProcessorList> processors) {
        return projection -> new UndergroundPoolElement(Either.left(ResourceLocation.parse(template)), processors, projection, Optional.empty());
    }

    public static Function<StructureTemplatePool.Projection, UndergroundPoolElement> create(String template, LiquidSettings overrideLiquidSettings) {
        return projection -> new UndergroundPoolElement(Either.left(ResourceLocation.parse(template)), EMPTY, projection, Optional.of(overrideLiquidSettings));
    }

    public static Function<StructureTemplatePool.Projection, UndergroundPoolElement> create(String template, Holder<StructureProcessorList> processors, LiquidSettings overrideLiquidSettings) {
        return projection -> new UndergroundPoolElement(Either.left(ResourceLocation.parse(template)), processors, projection, Optional.of(overrideLiquidSettings));
    }

    @Override
    public StructurePoolElementType<?> getType() {
        return MarvelStructurePoolElementTypes.UNDERGROUND.get();
    }

    @Override
    public String toString() {
        return "Underground[" + this.template + "]";
    }
}
