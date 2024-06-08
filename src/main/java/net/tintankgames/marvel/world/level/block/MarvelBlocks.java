package net.tintankgames.marvel.world.level.block;

import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tintankgames.marvel.MarvelSuperheroes;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class MarvelBlocks {
    public static final DeferredRegister.Blocks REGISTER = DeferredRegister.createBlocks(MarvelSuperheroes.MOD_ID);
    private static final DeferredRegister.Items REGISTER_ITEMS = DeferredRegister.createItems(MarvelSuperheroes.MOD_ID);

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
    public static final DeferredBlock<Block> SUIT_TABLE = register("suit_table", () -> new SuitTableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));

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
