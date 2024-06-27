package net.tintankgames.marvel.core.cauldron;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.stats.MarvelStats;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.VibraniumShieldItem;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MarvelSuperheroes.MOD_ID)
public class MarvelCauldronInteractions {
    public static final CauldronInteraction SHIELD = (state, level, pos, player, hand, stack) -> {
        if (!(stack.getItem() instanceof VibraniumShieldItem)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        } else if (!stack.has(DataComponents.DYED_COLOR) && !stack.has(MarvelDataComponents.SHIELD_ART)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        } else {
            if (!level.isClientSide) {
                if (stack.has(DataComponents.DYED_COLOR)) stack.remove(DataComponents.DYED_COLOR);
                if (stack.has(MarvelDataComponents.SHIELD_ART)) stack.remove(MarvelDataComponents.SHIELD_ART);
                player.awardStat(MarvelStats.CLEAN_SHIELD.get());
                LayeredCauldronBlock.lowerFillLevel(state, level, pos);
            }

            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
    };

    @SubscribeEvent
    public static void registerInteractions(FMLCommonSetupEvent event) {
        CauldronInteraction.WATER.map().put(MarvelItems.VIBRANIUM_SHIELD.get(), SHIELD);
        CauldronInteraction.WATER.map().put(MarvelItems.PROTO_ADAMANTIUM_SHIELD.get(), SHIELD);
    }
}
