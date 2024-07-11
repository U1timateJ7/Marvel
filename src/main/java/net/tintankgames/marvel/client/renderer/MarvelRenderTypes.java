package net.tintankgames.marvel.client.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;
import net.tintankgames.marvel.MarvelSuperheroes;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.function.BiFunction;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MarvelSuperheroes.MOD_ID, value = Dist.CLIENT)
public class MarvelRenderTypes {
    @Nullable
    private static ShaderInstance rendertypeEntityEmissiveShader;

    private static final BiFunction<ResourceLocation, Boolean, RenderType> ENTITY_EMISSIVE = Util.memoize(
            (p_286163_, p_286164_) -> {
                RenderType.CompositeState rendertype$compositestate = RenderType.CompositeState.builder()
                        .setShaderState(new RenderStateShard.ShaderStateShard(MarvelRenderTypes::getRendertypeEntityEmissiveShader))
                        .setTextureState(new RenderStateShard.TextureStateShard(p_286163_, false, false))
                        .setTransparencyState(new RenderStateShard.TransparencyStateShard("translucent_transparency", () -> {RenderSystem.enableBlend();RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);}, () -> {RenderSystem.disableBlend();RenderSystem.defaultBlendFunc();}))
                        .setCullState(new RenderStateShard.CullStateShard(false))
                        .setWriteMaskState(new RenderStateShard.WriteMaskStateShard(true, false))
                        .setOverlayState(new RenderStateShard.OverlayStateShard(true))
                        .createCompositeState(p_286164_);
                return RenderType.create("entity_emissive", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 1536, true, true, rendertype$compositestate);
            }
    );
    private static final BiFunction<ResourceLocation, Boolean, RenderType> ENTITY_SOLID_EMISSIVE = Util.memoize(
            (p_286163_, p_286164_) -> {
                RenderType.CompositeState rendertype$compositestate = RenderType.CompositeState.builder()
                        .setShaderState(new RenderStateShard.ShaderStateShard(MarvelRenderTypes::getRendertypeEntityEmissiveShader))
                        .setTextureState(new RenderStateShard.TextureStateShard(p_286163_, false, false))
                        .setTransparencyState(new RenderStateShard.TransparencyStateShard("no_transparency", RenderSystem::disableBlend, () -> {}))
                        .setCullState(new RenderStateShard.CullStateShard(false))
                        .setOverlayState(new RenderStateShard.OverlayStateShard(true))
                        .createCompositeState(p_286164_);
                return RenderType.create("entity_solid_emissive", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 1536, true, true, rendertype$compositestate);
            }
    );

    @Nullable
    public static ShaderInstance getRendertypeEntityEmissiveShader() {
        return rendertypeEntityEmissiveShader;
    }

    public static RenderType entityEmissive(ResourceLocation p_234336_, boolean p_234337_) {
        return ENTITY_EMISSIVE.apply(p_234336_, p_234337_);
    }

    public static RenderType entityEmissive(ResourceLocation p_234339_) {
        return entityEmissive(p_234339_, true);
    }

    public static RenderType entitySolidEmissive(ResourceLocation p_234336_, boolean p_234337_) {
        return ENTITY_SOLID_EMISSIVE.apply(p_234336_, p_234337_);
    }

    public static RenderType entitySolidEmissive(ResourceLocation p_234339_) {
        return entitySolidEmissive(p_234339_, true);
    }

    @SubscribeEvent
    public static void registerShaders(RegisterShadersEvent event) throws IOException {
        event.registerShader(new ShaderInstance(event.getResourceProvider(), MarvelSuperheroes.id("rendertype_entity_emissive"), DefaultVertexFormat.NEW_ENTITY), shaderInstance -> rendertypeEntityEmissiveShader = shaderInstance);
    }
}
