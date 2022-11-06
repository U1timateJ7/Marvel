
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.ulto.marvel.world.level.block.entity;

import com.ulto.marvel.common.MarvelMod;
import com.ulto.marvel.world.level.block.MarvelModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MarvelModBlockEntityTypes {

	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MarvelMod.MOD_ID);

	static {
		REGISTRY.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	public static final RegistryObject<BlockEntityType<IronManSuitChargerBlockEntity>> IRON_MAN_SUIT_CHARGER = REGISTRY.register("iron_man_suit_charger", () -> BlockEntityType.Builder.of(IronManSuitChargerBlockEntity::new, MarvelModBlocks.IRON_MAN_SUIT_CHARGER.get()).build(null));
}
