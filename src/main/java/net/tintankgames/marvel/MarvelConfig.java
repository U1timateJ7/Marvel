package net.tintankgames.marvel;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = MarvelSuperheroes.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class MarvelConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue GOOFY_OPTIC_BLAST = BUILDER.comment("Determines whether or not the optic blast is goofy").define("goofyOpticBlast", false);
    private static final ModConfigSpec.DoubleValue SPIDER_SENSE_RANGE = BUILDER.comment("Number of blocks the Spider Sense can detect mobs from").defineInRange("spiderSenseRange", 20.0D, 0.0D, 1024.0D);
    private static final ModConfigSpec.DoubleValue MJOLNIR_CALL_RANGE = BUILDER.comment("Number of blocks you can call Mjölnir from").defineInRange("mjolnirCallRange", 128.0D, 0.0D, 1024.0D);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean goofyOpticBlast;
    public static double spiderSenseRange;
    public static double mjolnirCallRange;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        goofyOpticBlast = GOOFY_OPTIC_BLAST.get();
        spiderSenseRange = SPIDER_SENSE_RANGE.get();
        mjolnirCallRange = MJOLNIR_CALL_RANGE.get();
    }
}
