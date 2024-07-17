package net.tintankgames.marvel.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tintankgames.marvel.MarvelSuperheroes;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class MarvelBlocks {
    public static final DeferredRegister.Blocks REGISTER = DeferredRegister.createBlocks(MarvelSuperheroes.MOD_ID);
    private static final DeferredRegister.Items REGISTER_ITEMS = DeferredRegister.createItems(MarvelSuperheroes.MOD_ID);
    private static final DeferredRegister<MapCodec<? extends Block>> REGISTER_TYPE = DeferredRegister.create(Registries.BLOCK_TYPE, MarvelSuperheroes.MOD_ID);

    public static final DeferredBlock<Block> VIBRANIUM_ORE = register("vibranium_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(6.0F, 1200.0F)), () -> new Item.Properties().fireResistant());
    public static final DeferredBlock<Block> DEEPSLATE_VIBRANIUM_ORE = register("deepslate_vibranium_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofLegacyCopy(MarvelBlocks.VIBRANIUM_ORE.get()).mapColor(MapColor.DEEPSLATE).strength(7.5F, 1200.0F).sound(SoundType.DEEPSLATE)), () -> new Item.Properties().fireResistant());
    public static final DeferredBlock<Block> VIBRANIUM_BLOCK = register("vibranium_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).requiresCorrectToolForDrops().strength(50.0F, 1200.0F).sound(SoundType.METAL)), () -> new Item.Properties().fireResistant());
    public static final DeferredBlock<Block> TITANIUM_ORE = register("titanium_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final DeferredBlock<Block> DEEPSLATE_TITANIUM_ORE = register("deepslate_titanium_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofLegacyCopy(MarvelBlocks.TITANIUM_ORE.get()).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)));
    public static final DeferredBlock<Block> TITANIUM_BLOCK = register("titanium_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> RAW_TITANIUM_BLOCK = register("raw_titanium_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
    public static final DeferredBlock<Block> PALLADIUM_ORE = register("palladium_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final DeferredBlock<Block> DEEPSLATE_PALLADIUM_ORE = register("deepslate_palladium_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofLegacyCopy(MarvelBlocks.PALLADIUM_ORE.get()).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)));
    public static final DeferredBlock<Block> PALLADIUM_BLOCK = register("palladium_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> RAW_PALLADIUM_BLOCK = register("raw_palladium_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
    public static final DeferredBlock<Block> GOLD_TITANIUM_BLOCK = register("gold_titanium_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ADAMANTIUM_BLOCK = register("adamantium_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).requiresCorrectToolForDrops().strength(50.0F, 1200.0F).sound(SoundType.METAL)), () -> new Item.Properties().fireResistant());
    public static final DeferredBlock<Block> PROTO_ADAMANTIUM_BLOCK = register("proto_adamantium_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).requiresCorrectToolForDrops().strength(50.0F, 1200.0F).sound(SoundType.METAL)), () -> new Item.Properties().fireResistant());
    public static final DeferredBlock<Block> URU_BLOCK = register("uru_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).requiresCorrectToolForDrops().strength(50.0F, 1200.0F).sound(SoundType.METAL)), () -> new Item.Properties().fireResistant());
    public static final DeferredBlock<Block> SUIT_TABLE = registerWithType("suit_table", () -> SuitTableBlock.CODEC, () -> new SuitTableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> SUIT_CHARGER = registerWithType("suit_charger", () -> SuitChargerBlock.CODEC, () -> new SuitChargerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> SUIT_CHARGER_UPPER = registerBlockOnlyWithType("suit_charger_upper", () -> SuitChargerUpperBlock.CODEC, () -> new SuitChargerUpperBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).pushReaction(PushReaction.BLOCK).noLootTable()));
    public static final DeferredBlock<Block> GREEN_HYDRA_BRICKS = register("green_hydra_bricks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final DeferredBlock<Block> GREEN_HYDRA_BRICK_SLAB = register("green_hydra_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2.0F, 6.0F)));
    public static final DeferredBlock<Block> GREEN_HYDRA_BRICK_STAIRS = register("green_hydra_brick_stairs", () -> new StairBlock(GREEN_HYDRA_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(GREEN_HYDRA_BRICKS.get())));
    public static final DeferredBlock<Block> GREEN_HYDRA_BRICK_WALL = register("green_hydra_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(GREEN_HYDRA_BRICKS.get()).forceSolidOn()));
    public static final DeferredBlock<Block> YELLOW_HYDRA_BRICKS = register("yellow_hydra_bricks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final DeferredBlock<Block> YELLOW_HYDRA_BRICK_SLAB = register("yellow_hydra_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2.0F, 6.0F)));
    public static final DeferredBlock<Block> YELLOW_HYDRA_BRICK_STAIRS = register("yellow_hydra_brick_stairs", () -> new StairBlock(YELLOW_HYDRA_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(YELLOW_HYDRA_BRICKS.get())));
    public static final DeferredBlock<Block> YELLOW_HYDRA_BRICK_WALL = register("yellow_hydra_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(YELLOW_HYDRA_BRICKS.get()).forceSolidOn()));
    public static final DeferredBlock<Block> SPIDER_WEB = registerBlockOnlyWithType("spider_web", () -> SpiderWebBlock.CODEC, () -> new SpiderWebBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).sound(SoundType.COBWEB).forceSolidOn().noCollission().requiresCorrectToolForDrops().strength(4.0F).pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> MJOLNIR = registerBlockOnlyWithType("mjolnir", () -> MjolnirBlock.CODEC, () -> new MjolnirBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).noLootTable().strength(-1, 360000).sound(SoundType.METAL)));

    private static <T extends Block> DeferredBlock<T> registerWithType(String id, Supplier<MapCodec<? extends Block>> type, Supplier<T> supplier) {
        REGISTER_TYPE.register(id, type);
        return register(id, supplier);
    }

    private static <T extends Block> DeferredBlock<T> registerBlockOnlyWithType(String id, Supplier<MapCodec<? extends Block>> type, Supplier<T> supplier) {
        REGISTER_TYPE.register(id, type);
        return registerBlockOnly(id, supplier);
    }

    private static <T extends Block> DeferredBlock<T> register(String id, Supplier<T> supplier) {
        return register(id, supplier, Item.Properties::new);
    }

    private static <T extends Block> DeferredBlock<T> registerBlockOnly(String id, Supplier<T> supplier) {
        return register(id, supplier, null);
    }

    private static <T extends Block> DeferredBlock<T> register(String id, Supplier<T> supplier, @Nullable Supplier<Item.Properties> propertiesSupplier) {
        DeferredBlock<T> block = REGISTER.register(id, supplier);
        if (propertiesSupplier != null) {
            REGISTER_ITEMS.register(id, () -> block.get() instanceof DoorBlock ? new DoubleHighBlockItem(block.get(), propertiesSupplier.get()) : new BlockItem(block.get(), propertiesSupplier.get()));
        }
        return block;
    }

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
        REGISTER_ITEMS.register(bus);
        REGISTER_TYPE.register(bus);
    }

    public static class Tags {
        public static final TagKey<Block> VIBRANIUM_ORES = create("vibranium_ores");
        public static final TagKey<Block> TITANIUM_ORES = create("titanium_ores");
        public static final TagKey<Block> PALLADIUM_ORES = create("palladium_ores");

        private static TagKey<Block> create(String id) {
            return REGISTER.createTagKey(id);
        }
    }
}
