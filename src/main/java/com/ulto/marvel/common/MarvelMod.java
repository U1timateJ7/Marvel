package com.ulto.marvel.common;

import com.ulto.marvel.world.level.block.MarvelModBlocks;
import com.ulto.marvel.world.item.MarvelModItems;
import com.ulto.marvel.world.item.MarvelModTabs;
import com.ulto.marvel.world.item.MarvelModTiers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Mod("marvel")
public class MarvelMod {
	public static final Logger LOGGER = LogManager.getLogger("Marvel Superheroes");
	public static final String MOD_ID = "marvel";
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MOD_ID, MOD_ID), () -> PROTOCOL_VERSION,
			PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	private static int messageID = 0;

	public MarvelMod() {
		var wood = new ResourceLocation("wood");
		var stone = new ResourceLocation("stone");
		var iron = new ResourceLocation("iron");
		var diamond = new ResourceLocation("diamond");
		var netherite = new ResourceLocation("netherite");
		TierSortingRegistry.registerTier(MarvelModTiers.BLADE, new ResourceLocation(MOD_ID, "blade"), List.of(wood), List.of(iron));
		TierSortingRegistry.registerTier(MarvelModTiers.DRILL, new ResourceLocation(MOD_ID, "drill"), List.of(stone), List.of(diamond));
		TierSortingRegistry.registerTier(MarvelModTiers.NANO_TOOL, new ResourceLocation(MOD_ID, "nano_tool"), List.of(stone), List.of(diamond));
		TierSortingRegistry.registerTier(MarvelModTiers.CLAWS, new ResourceLocation(MOD_ID, "claws"), List.of(stone), List.of(diamond));
		TierSortingRegistry.registerTier(MarvelModTiers.UPGRADED_NANO_TOOL, new ResourceLocation(MOD_ID, "upgraded_nano_tool"), List.of(iron), List.of(netherite));
		TierSortingRegistry.registerTier(MarvelModTiers.VIBRANIUM, new ResourceLocation(MOD_ID, "vibranium"), List.of(netherite), List.of());

		MarvelModTabs.load();
	}

	public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder,
			BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
		PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
		messageID++;
	}

	@SubscribeEvent
	public static void missingBlocks(RegistryEvent.MissingMappings<Block> event) {
		handleMissingMappings(event, MOD_ID, s -> switch (s) {
			case "heart_shaped_herb_stage_1", "heart_shaped_herb_stage_2", "heart_shaped_herb_stage_3", "heart_shaped_herb_stage_4", "heart_shaped_herb_stage_5", "heart_shaped_herb_stage_6", "heart_shaped_herb_stage_7" -> MarvelModBlocks.HEART_SHAPED_HERB_CROP.get();
			default -> null;
		});
	}

	@SubscribeEvent
	public static void missingItems(RegistryEvent.MissingMappings<Item> event) {
		handleMissingMappings(event, MOD_ID, s -> switch (s) {
			case "adamantium" -> MarvelModItems.ADAMANTIUM_INGOT.get();
			case "mark_2_open_helmet" -> MarvelModItems.IRON_MAN_MARK_2_HELMET.get();
			case "mark_3_open_helmet" -> MarvelModItems.IRON_MAN_MARK_3_HELMET.get();
			case "mark_5_open_helmet" -> MarvelModItems.IRON_MAN_MARK_5_HELMET.get();
			case "mark_6_open_helmet" -> MarvelModItems.IRON_MAN_MARK_6_HELMET.get();
			case "mark_7_open_helmet" -> MarvelModItems.IRON_MAN_MARK_7_HELMET.get();
			case "mark_16_stealth_helmet", "mark_16_open_helmet" -> MarvelModItems.IRON_MAN_MARK_16_HELMET.get();
			case "mark_16_stealth_chestplate" -> MarvelModItems.IRON_MAN_MARK_16_CHESTPLATE.get();
			case "mark_16_stealth_leggings" -> MarvelModItems.IRON_MAN_MARK_16_LEGGINGS.get();
			case "mark_16_stealth_boots" -> MarvelModItems.IRON_MAN_MARK_16_BOOTS.get();
			case "mark_17_open_helmet" -> MarvelModItems.IRON_MAN_MARK_17_HELMET.get();
			case "mark_19_open_helmet" -> MarvelModItems.IRON_MAN_MARK_19_HELMET.get();
			case "mark_20_open_helmet" -> MarvelModItems.IRON_MAN_MARK_20_HELMET.get();
			case "mark_21_open_helmet" -> MarvelModItems.IRON_MAN_MARK_21_HELMET.get();
			case "mark_22_open_helmet" -> MarvelModItems.IRON_MAN_MARK_22_HELMET.get();
			case "mark_23_open_helmet" -> MarvelModItems.IRON_MAN_MARK_23_HELMET.get();
			case "mark_25_open_helmet" -> MarvelModItems.IRON_MAN_MARK_25_HELMET.get();
			case "mark_30_open_helmet" -> MarvelModItems.IRON_MAN_MARK_30_HELMET.get();
			case "mark_33_open_helmet" -> MarvelModItems.IRON_MAN_MARK_33_HELMET.get();
			case "mark_37_open_helmet" -> MarvelModItems.IRON_MAN_MARK_37_HELMET.get();
			case "mark_39_open_helmet" -> MarvelModItems.IRON_MAN_MARK_39_HELMET.get();
			case "mark_42_open_helmet" -> MarvelModItems.IRON_MAN_MARK_42_HELMET.get();
			case "mark_43_open_helmet" -> MarvelModItems.IRON_MAN_MARK_43_HELMET.get();
			case "mark_46_open_helmet" -> MarvelModItems.IRON_MAN_MARK_46_HELMET.get();
			case "mark_47_open_helmet" -> MarvelModItems.IRON_MAN_MARK_47_HELMET.get();
			case "mark_47_glasses_helmet" -> MarvelModItems.MARK_47_GLASSES.get();
			case "mark_49_open_helmet" -> MarvelModItems.IRON_MAN_MARK_49_HELMET.get();
			case "mark_50_arc_reactor_chestplate" -> MarvelModItems.MARK_50_ARC_REACTOR.get();
			case "mark_50_open_helmet" -> MarvelModItems.IRON_MAN_MARK_50_HELMET.get();
			case "mark_85_arc_reactor_chestplate" -> MarvelModItems.MARK_85_ARC_REACTOR.get();
			case "mark_85_open_helmet" -> MarvelModItems.IRON_MAN_MARK_85_HELMET.get();
			case "war_machine_open_helmet" -> MarvelModItems.WAR_MACHINE_HELMET.get();
			case "iron_patriot_open_helmet" -> MarvelModItems.IRON_PATRIOT_HELMET.get();
			case "war_machine_mark_2_open_helmet" -> MarvelModItems.WAR_MACHINE_MARK_2_HELMET.get();
			case "iron_spider_open_helmet" -> MarvelModItems.IRON_SPIDER_SUIT_HELMET.get();
			case "iron_spider_arms_chestplate" -> MarvelModItems.IRON_SPIDER_SUIT_CHESTPLATE.get();
			case "iron_spider_storage_chestplate" -> MarvelModItems.IRON_SPIDER_STORAGE.get();
			case "antman_open_helmet" -> MarvelModItems.ANTMAN_SUIT_HELMET.get();
			case "antman_v_2_suit_helmet", "antman_v_2_suit_open_helmet" -> MarvelModItems.ANTMAN_V2_SUIT_HELMET.get();
			case "antman_v_2_suit_chestplate" -> MarvelModItems.ANTMAN_V2_SUIT_CHESTPLATE.get();
			case "antman_v_2_suit_leggings" -> MarvelModItems.ANTMAN_V2_SUIT_LEGGINGS.get();
			case "antman_v_2_suit_boots" -> MarvelModItems.ANTMAN_V2_SUIT_BOOTS.get();
			case "heart_shaped_herb_item" -> MarvelModItems.HEART_SHAPED_HERB.get();
			case "vibranium_armor_helmet" -> MarvelModItems.VIBRANIUM_HELMET.get();
			case "vibranium_armor_chestplate" -> MarvelModItems.VIBRANIUM_CHESTPLATE.get();
			case "vibranium_armor_leggings" -> MarvelModItems.VIBRANIUM_LEGGINGS.get();
			case "vibranium_armor_boots" -> MarvelModItems.VIBRANIUM_BOOTS.get();
			default -> null;
		});
	}

	public static <T extends IForgeRegistryEntry<T>> void handleMissingMappings(RegistryEvent.MissingMappings<T> event, String modID, Function<String, T> handler) {
		for (RegistryEvent.MissingMappings.Mapping<T> mapping : event.getAllMappings()) {
			if (modID.equals(mapping.key.getNamespace())) {
				@Nullable T value = handler.apply(mapping.key.getPath());
				if (value != null) {
					mapping.remap(value);
				}
			}
		}
	}
}
