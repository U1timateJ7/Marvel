package com.ulto.marvel.world.level.block;

import com.ulto.marvel.common.MarvelMod;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MarvelModBlocks {
	private static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, MarvelMod.MOD_ID);

	static {
		REGISTRY.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	public static final RegistryObject<Block> HEART_SHAPED_HERB_FLOWER = REGISTRY.register("heart_shaped_herb_flower", () -> new FlowerBlock(() -> MobEffects.SATURATION, 0, BlockBehaviour.Properties.of(Material.PLANT).noCollission().sound(SoundType.GRASS).instabreak()));
	public static final RegistryObject<Block> TITANIUM_ORE = REGISTRY.register("titanium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3f, 3f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> DEEPSLATE_TITANIUM_ORE = REGISTRY.register("deepslate_titanium_ore", () -> new Block(BlockBehaviour.Properties.copy(TITANIUM_ORE.get()).sound(SoundType.DEEPSLATE).destroyTime(4.5f)));
	public static final RegistryObject<Block> TITANIUM_BLOCK = REGISTRY.register("titanium_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f, 10f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> RAW_TITANIUM_BLOCK = REGISTRY.register("raw_titanium_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f, 6f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> PALLADIUM_ORE = REGISTRY.register("palladium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3f, 3f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> DEEPSLATE_PALLADIUM_ORE = REGISTRY.register("deepslate_palladium_ore", () -> new Block(BlockBehaviour.Properties.copy(PALLADIUM_ORE.get()).sound(SoundType.DEEPSLATE).destroyTime(4.5f)));
	public static final RegistryObject<Block> PALLADIUM_BLOCK = REGISTRY.register("palladium_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f, 10f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> RAW_PALLADIUM_BLOCK = REGISTRY.register("raw_palladium_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f, 6f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> URU_ORE = REGISTRY.register("uru_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(6f, 1200f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> DEEPSLATE_URU_ORE = REGISTRY.register("deepslate_uru_ore", () -> new Block(BlockBehaviour.Properties.copy(TITANIUM_ORE.get()).sound(SoundType.DEEPSLATE).destroyTime(9f)));
	public static final RegistryObject<Block> URU_BLOCK = REGISTRY.register("uru_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(9f, 1200f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> RAW_URU_BLOCK = REGISTRY.register("raw_uru_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(10f, 1200f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> VIBRANIUM_ORE = REGISTRY.register("vibranium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(6f, 1200f).lightLevel(state -> 7).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> DEEPSLATE_VIBRANIUM_ORE = REGISTRY.register("deepslate_vibranium_ore", () -> new Block(BlockBehaviour.Properties.copy(TITANIUM_ORE.get()).sound(SoundType.DEEPSLATE).destroyTime(7.5f)));
	public static final RegistryObject<Block> VIBRANIUM_BLOCK = REGISTRY.register("vibranium_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(9f, 1200f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> ADAMANTIUM_BLOCK = REGISTRY.register("adamantium_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(10f, 1200f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> GOLD_TITANIUM_BLOCK = REGISTRY.register("gold_titanium_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_RED).sound(SoundType.METAL).strength(5f, 10f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> HEART_SHAPED_HERB_CROP = REGISTRY.register("heart_shaped_herb_crop", () -> new HeartShapedHerbCropBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
	public static final RegistryObject<Block> TEMPORARY_WEB = REGISTRY.register("temporary_web", () -> new TemporaryWebBlock(BlockBehaviour.Properties.copy(Blocks.COBWEB).lootFrom(() -> Blocks.COBWEB)));
	public static final RegistryObject<Block> MJOLNIR_BLOCK = REGISTRY.register("mjolnir_block", () -> new MjolnirBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(-1, 3600000).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> IRON_MAN_SUIT_CHARGER = REGISTRY.register("iron_man_suit_charger", () -> new IronManSuitChargerBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f, 10f).requiresCorrectToolForDrops().noOcclusion().isValidSpawn((s, g, p, e) -> false).isRedstoneConductor((s, g, p) -> false).isSuffocating((s, g, p) -> false).isViewBlocking((s, g, p) -> false)));
	public static final RegistryObject<Block> IRON_MAN_NANOTECH_SUIT_CHARGER = REGISTRY.register("iron_man_nanotech_suit_charger", () -> new IronManNanotechSuitChargerBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f, 10f).requiresCorrectToolForDrops().noOcclusion().isValidSpawn((s, g, p, e) -> false).isRedstoneConductor((s, g, p) -> false).isSuffocating((s, g, p) -> false).isViewBlocking((s, g, p) -> false)));

	@SubscribeEvent
	public static void setup(FMLLoadCompleteEvent event) {
		((FireBlock) Blocks.FIRE).setFlammable(HEART_SHAPED_HERB_FLOWER.get(), 60, 100);
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			ItemBlockRenderTypes.setRenderLayer(MarvelModBlocks.HEART_SHAPED_HERB_FLOWER.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(MarvelModBlocks.HEART_SHAPED_HERB_CROP.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(MarvelModBlocks.TEMPORARY_WEB.get(), RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(MarvelModBlocks.MJOLNIR_BLOCK.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(MarvelModBlocks.IRON_MAN_SUIT_CHARGER.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(MarvelModBlocks.IRON_MAN_NANOTECH_SUIT_CHARGER.get(), RenderType.translucent());
		}
	}

	public static class Tags {
		public static final TagKey<Block> PALLADIUM_ORES = REGISTRY.createTagKey(new ResourceLocation(MarvelMod.MOD_ID, "palladium_ores"));
		public static final TagKey<Block> TITANIUM_ORES = REGISTRY.createTagKey(new ResourceLocation(MarvelMod.MOD_ID, "titanium_ores"));
		public static final TagKey<Block> URU_ORES = REGISTRY.createTagKey(new ResourceLocation(MarvelMod.MOD_ID, "uru_ores"));
		public static final TagKey<Block> VIBRANIUM_ORES = REGISTRY.createTagKey(new ResourceLocation(MarvelMod.MOD_ID, "vibranium_ores"));

		public static final TagKey<Block> NEEDS_VIBRANIUM_TOOL = REGISTRY.createTagKey(new ResourceLocation(MarvelMod.MOD_ID, "needs_vibranium_tool"));
	}
}
