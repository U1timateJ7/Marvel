package dustw.libgui.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DustW
 */
@EventBusSubscriber(Dist.CLIENT)
public class ClientTickEvents {
    private static final List<EndTick> ENDS = new ArrayList<>();

    public static void registerEnd(EndTick endTick) {
        ENDS.add(endTick);
    }

    @SubscribeEvent
    public static void onEvent(ClientTickEvent.Post event) {
        ENDS.forEach(e -> e.onEndTick(Minecraft.getInstance()));
    }

    @SubscribeEvent
    public static void render(RenderGuiLayerEvent.Post event) {
        if (event.getName() == VanillaGuiLayers.CROSSHAIR) HudRenderCallback.EVENTS.forEach(e -> e.onHudRender(event.getGuiGraphics(), event.getPartialTick()));
    }

    @FunctionalInterface
    public interface EndTick {
        void onEndTick(Minecraft client);
    }
}
