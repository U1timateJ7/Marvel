package io.github.cottonmc.cotton.gui.impl.client;

import net.minecraft.client.renderer.ShaderInstance;
import org.jetbrains.annotations.Nullable;

public final class LibGuiShaders {
    public static @Nullable ShaderInstance tiledRectangle;

    public static void register() {
    }

    private static ShaderInstance assertPresent(ShaderInstance program, String name) {
        if (program == null) {
            throw new NullPointerException("Shader multiverse:" + name + " not initialised!");
        }

        return program;
    }

    public static ShaderInstance getTiledRectangle() {
        return assertPresent(tiledRectangle, "tiled_rectangle");
    }
}