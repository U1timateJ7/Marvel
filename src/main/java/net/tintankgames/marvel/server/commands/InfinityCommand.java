package net.tintankgames.marvel.server.commands;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.tintankgames.marvel.attachment.InfinityStone;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;

import static net.minecraft.commands.Commands.literal;

@EventBusSubscriber
public class InfinityCommand {
    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(literal("infinity")
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
        }
        String name = stones[0].getName();
        source.sendSuccess(() -> stones.length == 1 ? Component.translatable("commands.infinity.reset.success.singular", Component.translatable("item.marvel." + name + "_stone")) : Component.translatable("commands.infinity.reset.success.all"), true);
        return value;
    }
}
