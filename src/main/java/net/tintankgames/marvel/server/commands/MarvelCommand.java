package net.tintankgames.marvel.server.commands;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.ResourceArgument;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.tintankgames.marvel.attachment.InfinityStone;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.attachment.VeronicaData;
import net.tintankgames.marvel.attachment.VeronicaSuitPreset;
import net.tintankgames.marvel.core.registries.MarvelBuiltInRegistries;
import net.tintankgames.marvel.core.registries.MarvelRegistries;
import net.tintankgames.marvel.world.item.EnergySuitItem;
import net.tintankgames.marvel.world.item.VeronicaSuit;

import java.util.List;
import java.util.Objects;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

@EventBusSubscriber
public class MarvelCommand {
    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(literal("marvel")
                .then(literal("infinity")
                        .then(literal("exclusive")
                                .then(literal("all").executes(context -> exclusive(context.getSource(), InfinityStone.values())))
                                .then(literal("space").executes(context -> exclusive(context.getSource(), InfinityStone.SPACE)))
                        ).then(literal("multiple")
                                .then(literal("all").executes(context -> multiple(context.getSource(), InfinityStone.values())))
                                .then(literal("space").executes(context -> multiple(context.getSource(), InfinityStone.SPACE)))
                        ).then(literal("reset")
                                .then(literal("all").executes(context -> reset(context.getSource(), InfinityStone.values())))
                                .then(literal("space").executes(context -> reset(context.getSource(), InfinityStone.SPACE)))
                        )
                ).then(literal("veronica").requires(source -> source.hasPermission(2)).then(argument("target", EntityArgument.player())
                        .then(literal("add")
                                .then(argument("suit", ResourceArgument.resource(event.getBuildContext(), MarvelRegistries.VERONICA_SUIT_PRESET)).executes(context -> addSuit(context.getSource(), EntityArgument.getPlayer(context, "target"), ResourceArgument.getResource(context, "suit", MarvelRegistries.VERONICA_SUIT_PRESET).value())))
                                .then(literal("*").executes(context -> addAllSuits(context.getSource(), EntityArgument.getPlayer(context, "target"))))
                        ).then(literal("remove")
                                .then(argument("suit", ResourceLocationArgument.id()).suggests((context, builder) -> SharedSuggestionProvider.suggestResource(EntityArgument.getPlayer(context, "target").getData(MarvelAttachmentTypes.VERONICA).getSuits().stream().map(suit -> ResourceLocation.tryParse(suit.armor().get(2).getItemHolder().getRegisteredName().replace("_chestplate", ""))).filter(Objects::nonNull), builder)).executes(context -> removeSuit(context.getSource(), EntityArgument.getPlayer(context, "target"), ResourceLocationArgument.getId(context, "suit"))))
                                .then(literal("*").executes(context -> removeAllSuits(context.getSource(), EntityArgument.getPlayer(context, "target"))))
                        ).then(literal("repair").executes(context -> repairSuits(context.getSource(), EntityArgument.getPlayer(context, "target"))))
                        .then(literal("charge").executes(context -> chargeSuits(context.getSource(), EntityArgument.getPlayer(context, "target"))))
                        .then(literal("enable").executes(context -> enableVeronica(context.getSource(), EntityArgument.getPlayer(context, "target"))))
                        .then(literal("disable").executes(context -> disableVeronica(context.getSource(), EntityArgument.getPlayer(context, "target"))))
                ))
        );
    }

    private static int exclusive(CommandSourceStack source, InfinityStone... stones) {
        int value = 0;
        for (InfinityStone stone : stones) {
            source.getServer().overworld().getData(MarvelAttachmentTypes.INFINITY_STONES).setExclusive(stone, true);
        }
        String name = stones[0].getName();
        source.sendSuccess(() -> stones.length == 1 ? Component.translatable("commands.infinity.exclusive.success.singular", Component.translatable("item.marvel." + name + "_stone")) : Component.translatable("commands.infinity.exclusive.success.all"), true);
        return value;
    }

    private static int multiple(CommandSourceStack source, InfinityStone... stones) {
        int value = 0;
        for (InfinityStone stone : stones) {
            source.getServer().overworld().getData(MarvelAttachmentTypes.INFINITY_STONES).setExclusive(stone, false);
        }
        String name = stones[0].getName();
        source.sendSuccess(() -> stones.length == 1 ? Component.translatable("commands.infinity.multiple.success.singular", Component.translatable("item.marvel." + name + "_stone"), "s") : Component.translatable("commands.infinity.multiple.success.all"), true);
        return value;
    }

    private static int reset(CommandSourceStack source, InfinityStone... stones) {
        int value = 0;
        for (InfinityStone stone : stones) {
            source.getServer().overworld().getData(MarvelAttachmentTypes.INFINITY_STONES).setFoundStone(stone, false);
            value++;
        }
        String name = stones[0].getName();
        source.sendSuccess(() -> stones.length == 1 ? Component.translatable("commands.infinity.reset.success.singular", Component.translatable("item.marvel." + name + "_stone")) : Component.translatable("commands.infinity.reset.success.all"), true);
        return value;
    }

    private static int addSuit(CommandSourceStack source, Player player, VeronicaSuitPreset suit) {
        player.getData(MarvelAttachmentTypes.VERONICA).addSuit(new VeronicaData.Suit(List.of(suit.boots(), suit.leggings(), suit.chestplate(), suit.helmet()), ((VeronicaSuit) suit.chestplate().getItem()).markNumber(), player.getData(MarvelAttachmentTypes.VERONICA).nextId()));
        source.sendSuccess(() -> Component.translatable("commands.veronica.add.success", Component.translatable("gui.veronica." + MarvelBuiltInRegistries.VERONICA_SUIT_PRESET.getKey(suit).getPath())), true);
        return 1;
    }

    private static int addAllSuits(CommandSourceStack source, Player player) {
        int size = MarvelBuiltInRegistries.VERONICA_SUIT_PRESET.size();
        MarvelBuiltInRegistries.VERONICA_SUIT_PRESET.forEach(suit -> player.getData(MarvelAttachmentTypes.VERONICA).addSuit(new VeronicaData.Suit(List.of(suit.boots(), suit.leggings(), suit.chestplate(), suit.helmet()), ((VeronicaSuit) suit.chestplate().getItem()).markNumber(), player.getData(MarvelAttachmentTypes.VERONICA).nextId())));
        source.sendSuccess(() -> Component.translatable("commands.veronica.add.all.success"), true);
        return size;
    }

    private static int removeSuit(CommandSourceStack source, Player player, ResourceLocation suit) {
        player.getData(MarvelAttachmentTypes.VERONICA).removeSuit(player.getData(MarvelAttachmentTypes.VERONICA).getSuits().stream().filter(veronicaSuit -> veronicaSuit.mark() == ((VeronicaSuit) BuiltInRegistries.ITEM.get(suit.withSuffix("_chestplate"))).markNumber()).findFirst().orElseThrow().id());
        source.sendSuccess(() -> Component.translatable("commands.veronica.remove.success", Component.translatable("gui.veronica." + suit.getPath())), true);
        return 1;
    }

    private static int removeAllSuits(CommandSourceStack source, Player player) {
        int size = player.getData(MarvelAttachmentTypes.VERONICA).getSuits().size();
        player.getData(MarvelAttachmentTypes.VERONICA).clear();
        source.sendSuccess(() -> Component.translatable("commands.veronica.remove.all.success"), true);
        return size;
    }

    private static int repairSuits(CommandSourceStack source, Player player) {
        int size = player.getData(MarvelAttachmentTypes.VERONICA).getSuits().stream().filter(suit -> suit.armor().stream().anyMatch(piece -> piece.getDamageValue() > 0)).toList().size();
        player.getData(MarvelAttachmentTypes.VERONICA).forEach(suit -> suit.armor().forEach(piece -> piece.setDamageValue(0)));
        source.sendSuccess(() -> Component.translatable("commands.veronica.repair.success"), true);
        return size;
    }

    private static int chargeSuits(CommandSourceStack source, Player player) {
        int size = player.getData(MarvelAttachmentTypes.VERONICA).getSuits().stream().filter(suit -> suit.armor().stream().anyMatch(piece -> EnergySuitItem.getEnergy(piece) < 100.0F)).toList().size();
        player.getData(MarvelAttachmentTypes.VERONICA).forEach(suit -> suit.armor().forEach(piece -> EnergySuitItem.setEnergy(piece, 100.0F)));
        source.sendSuccess(() -> Component.translatable("commands.veronica.charge.success"), true);
        return size;
    }

    private static int enableVeronica(CommandSourceStack source, Player player) {
        player.getData(MarvelAttachmentTypes.VERONICA).setEnabled(true);
        source.sendSuccess(() -> Component.translatable("commands.veronica.enable.success"), true);
        return 1;
    }

    private static int disableVeronica(CommandSourceStack source, Player player) {
        player.getData(MarvelAttachmentTypes.VERONICA).setEnabled(false);
        source.sendSuccess(() -> Component.translatable("commands.veronica.disable.success"), true);
        return 1;
    }
}
