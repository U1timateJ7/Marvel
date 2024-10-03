package io.github.cottonmc.cotton.gui.client;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import io.github.cottonmc.cotton.gui.impl.client.LibGuiShaders;
import net.minecraft.client.renderer.ShaderInstance;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;
import net.tintankgames.marvel.MarvelSuperheroes;

import java.io.IOException;

/**
 * This class provides access to LibGui configuration and other global data.
 *
 * @since 4.0.0
 */
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MarvelSuperheroes.MOD_ID)
public final class LibGui {
    public static final String MOD_ID = "libgui";

    private LibGui() {
    }

    @SubscribeEvent
    public static void registerShaders(RegisterShadersEvent event) throws IOException {
        event.registerShader(new ShaderInstance(event.getResourceProvider(), MarvelSuperheroes.id("tiled_rectangle"), DefaultVertexFormat.POSITION), (p_172743_) -> {
            LibGuiShaders.tiledRectangle = p_172743_;
        });
    }

    public static boolean isDarkMode() {
        return false;
    }
}
