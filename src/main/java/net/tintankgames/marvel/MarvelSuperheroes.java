package net.tintankgames.marvel;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.timers.TimerCallbacks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.core.particles.MarvelParticleTypes;
import net.tintankgames.marvel.sounds.MarvelSoundEvents;
import net.tintankgames.marvel.stats.MarvelStats;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;
import net.tintankgames.marvel.world.inventory.MarvelMenuTypes;
import net.tintankgames.marvel.world.item.MarvelArmorMaterials;
import net.tintankgames.marvel.world.item.MarvelCreativeModeTabs;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.crafting.MarvelRecipeSerializers;
import net.tintankgames.marvel.world.item.crafting.MarvelRecipeTypes;
import net.tintankgames.marvel.world.level.MarvelGameRules;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;
import net.tintankgames.marvel.world.level.block.entity.MarvelBlockEntityTypes;
import net.tintankgames.marvel.world.level.timers.SetItemInCurioSlotCallback;
import net.tintankgames.marvel.world.level.timers.SetItemInSlotCallback;
import org.slf4j.Logger;

@Mod(MarvelSuperheroes.MOD_ID)
public class MarvelSuperheroes {
    public static final String MOD_ID = "marvel";
    public static final String MOD_NAME = "Marvel Superheroes";
    public static final String MOD_VERSION = "2.0.0-rc3";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MarvelSuperheroes(IEventBus bus, ModContainer modContainer) {
        LOGGER.info("Initializing {} version {}...", MOD_NAME, MOD_VERSION);
        bus.addListener(this::setup);

        MarvelSoundEvents.register(bus);
        MarvelStats.register(bus);
        MarvelParticleTypes.register(bus);
        MarvelMenuTypes.register(bus);
        MarvelRecipeTypes.register(bus);
        MarvelRecipeSerializers.register(bus);
        MarvelEntityTypes.register(bus);
        MarvelArmorMaterials.register(bus);
        MarvelDataComponents.register(bus);
        MarvelAttachmentTypes.register(bus);
        MarvelBlockEntityTypes.register(bus);
        MarvelBlocks.register(bus);
        MarvelItems.register(bus);
        MarvelCreativeModeTabs.register(bus);
        MarvelGameRules.register();
        modContainer.registerConfig(ModConfig.Type.COMMON, MarvelConfig.SPEC);
    }

    private void setup(FMLCommonSetupEvent event) {
        TimerCallbacks.SERVER_CALLBACKS.register(new SetItemInSlotCallback.Serializer());
        TimerCallbacks.SERVER_CALLBACKS.register(new SetItemInCurioSlotCallback.Serializer());
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
