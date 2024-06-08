package net.tintankgames.marvel.world.level;

import net.minecraft.world.level.GameRules;

public class MarvelGameRules {
    public static final GameRules.Key<GameRules.BooleanValue> RULE_SUPERPOWERGRIEFING = GameRules.register("superpowerGriefing", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true));

    public static void register() {
    }
}
