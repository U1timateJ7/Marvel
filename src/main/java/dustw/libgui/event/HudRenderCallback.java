package dustw.libgui.event;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DustW
 */
public interface HudRenderCallback {
    List<HudRenderCallback> EVENTS = new ArrayList<>();

    static void register(HudRenderCallback callback) {
        EVENTS.add(callback);
    }

    /**
     * Called after rendering the whole hud, which is displayed in game, in a world.
     *
     * @param guiGraphics the guiGraphics
     * @param tickDelta Progress for linearly interpolating between the previous and current game state
     */
    void onHudRender(GuiGraphics guiGraphics, float tickDelta);
}
