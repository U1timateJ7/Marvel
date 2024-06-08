package net.tintankgames.marvel;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
@EventBusSubscriber(modid = MarvelSuperheroes.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class MarvelConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue GOOFY_OPTIC_BLAST = BUILDER.comment("Determines whether or not the optic blast is goofy").define("goofyOpticBlast", false);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean goofyOpticBlast;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        goofyOpticBlast = GOOFY_OPTIC_BLAST.get();
    }
}
