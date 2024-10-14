package net.tintankgames.marvel.world.inventory;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.client.gui.screens.SuitChargerScreen;
import net.tintankgames.marvel.client.gui.screens.SuitRepairingScreen;
import net.tintankgames.marvel.client.gui.screens.SuitUpgradingScreen;
import net.tintankgames.marvel.client.gui.screens.SuitVariantScreen;

import java.util.function.Supplier;

@EventBusSubscriber(modid = MarvelSuperheroes.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MarvelMenuTypes {
    public static final DeferredRegister<MenuType<?>> REGISTER = DeferredRegister.create(Registries.MENU, MarvelSuperheroes.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<SuitUpgradingMenu>> SUIT_UPGRADING = register("suit_upgrading", () -> new MenuType<>(SuitUpgradingMenu::new, FeatureFlags.VANILLA_SET));
    public static final DeferredHolder<MenuType<?>, MenuType<SuitVariantMenu>> SUIT_VARIANT = register("suit_variant", () -> new MenuType<>(SuitVariantMenu::new, FeatureFlags.VANILLA_SET));
    public static final DeferredHolder<MenuType<?>, MenuType<SuitRepairingMenu>> SUIT_REPAIRING = register("suit_repairing", () -> new MenuType<>(SuitRepairingMenu::new, FeatureFlags.VANILLA_SET));
    public static final DeferredHolder<MenuType<?>, MenuType<SuitChargerMenu>> SUIT_CHARGER = register("suit_charger", () -> new MenuType<>(SuitChargerMenu::new, FeatureFlags.VANILLA_SET));
    public static double targetMouseX = -1;
    public static double targetMouseY = -1;

    private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> register(String id, Supplier<MenuType<T>> supplier) {
        return REGISTER.register(id, supplier);
    }

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerClient(RegisterMenuScreensEvent event) {
        event.register(SUIT_UPGRADING.get(), SuitUpgradingScreen::new);
        event.register(SUIT_VARIANT.get(), SuitVariantScreen::new);
        event.register(SUIT_REPAIRING.get(), SuitRepairingScreen::new);
        event.register(SUIT_CHARGER.get(), SuitChargerScreen::new);
    }
}
