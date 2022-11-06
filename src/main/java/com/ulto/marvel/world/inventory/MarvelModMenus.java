package com.ulto.marvel.world.inventory;

import com.ulto.marvel.common.MarvelMod;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.AbstractContainerMenu;

import com.ulto.marvel.world.inventory.VeronicaGuiMenu;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MarvelModMenus {
	private static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.CONTAINERS, MarvelMod.MOD_ID);

	static {
		REGISTRY.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	public static final RegistryObject<MenuType<VeronicaGuiMenu>> VERONICA_GUI = register("veronica_gui",
			VeronicaGuiMenu::new);

	private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> register(String registryname, IContainerFactory<T> containerFactory) {
		return REGISTRY.register(registryname, () -> new MenuType<>(containerFactory));
	}
}
