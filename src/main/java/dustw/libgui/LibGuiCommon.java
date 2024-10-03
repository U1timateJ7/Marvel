package dustw.libgui;

import io.github.cottonmc.cotton.gui.impl.client.LibGuiShaders;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class LibGuiCommon {
    public static final String MOD_ID = "libgui";

    @SubscribeEvent
    public static void clientInit(FMLClientSetupEvent event) {
        LibGuiShaders.register();
    }
}
