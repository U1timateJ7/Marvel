package net.tintankgames.marvel.sounds;

import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tintankgames.marvel.MarvelSuperheroes;

public class MarvelSoundEvents {
    public static final DeferredRegister<SoundEvent> REGISTER = DeferredRegister.create(Registries.SOUND_EVENT, MarvelSuperheroes.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> EMPTY = register("empty");
    public static final DeferredHolder<SoundEvent, SoundEvent> VIBRANIUM_SHIELD_THROW = register("item.vibranium_shield.throw");
    public static final DeferredHolder<SoundEvent, SoundEvent> VIBRANIUM_SHIELD_HIT = register("item.vibranium_shield.hit");
    public static final DeferredHolder<SoundEvent, SoundEvent> KINETIC_BLACK_PANTHER_SUIT_UP = register("suit.kinetic_black_panther.suit_up");
    public static final DeferredHolder<SoundEvent, SoundEvent> KINETIC_BLACK_PANTHER_SUIT_DOWN = register("suit.kinetic_black_panther.suit_down");
    public static final DeferredHolder<SoundEvent, SoundEvent> KINETIC_BLACK_PANTHER_HELMET_UP = register("suit.kinetic_black_panther.helmet_up");
    public static final DeferredHolder<SoundEvent, SoundEvent> KINETIC_BLACK_PANTHER_HELMET_DOWN = register("suit.kinetic_black_panther.helmet_down");
    public static final DeferredHolder<SoundEvent, SoundEvent> WOLVERINE_CLAWS_OUT = register("suit.wolverine.claws_out");
    public static final DeferredHolder<SoundEvent, SoundEvent> WOLVERINE_CLAWS_IN = register("suit.wolverine.claws_in");
    public static final DeferredHolder<SoundEvent, SoundEvent> CYCLOPS_OPTIC_BLAST = register("suit.cyclops.optic_blast");
    public static final DeferredHolder<SoundEvent, SoundEvent> CYCLOPS_OPTIC_BLAST_BIG = register("suit.cyclops.optic_blast_big");
    public static final DeferredHolder<SoundEvent, SoundEvent> CYCLOPS_OPTIC_BLAST_EXPLOSION = register("suit.cyclops.optic_blast_explosion");
    public static final DeferredHolder<SoundEvent, SoundEvent> SPIDER_MAN_WEB_SHOOT = register("suit.spider_man.web_shoot");
    public static final DeferredHolder<SoundEvent, SoundEvent> SPIDER_MAN_SPIDER_SENSE = register("suit.spider_man.spider_sense");
    public static final DeferredHolder<SoundEvent, SoundEvent> ANT_MAN_HELMET_OPEN = register("suit.ant_man.helmet_open");
    public static final DeferredHolder<SoundEvent, SoundEvent> ANT_MAN_HELMET_CLOSE = register("suit.ant_man.helmet_close");
    public static final DeferredHolder<SoundEvent, SoundEvent> ANT_MAN_SHRINK = register("suit.ant_man.shrink");
    public static final DeferredHolder<SoundEvent, SoundEvent> ANT_MAN_GROW = register("suit.ant_man.grow");
    public static final DeferredHolder<SoundEvent, SoundEvent> WASP_STING = register("suit.wasp.sting");
    public static final DeferredHolder<SoundEvent, SoundEvent> MJOLNIR_THROW = register("item.mjolnir.throw");
    public static final DeferredHolder<SoundEvent, SoundEvent> MJOLNIR_HIT = register("item.mjolnir.hit");
    public static final DeferredHolder<SoundEvent, SoundEvent> MJOLNIR_HIT_SHIELD = register("item.mjolnir.hit_shield");
    public static final DeferredHolder<SoundEvent, SoundEvent> MJOLNIR_CALL = register("item.mjolnir.call");

    private static DeferredHolder<SoundEvent, SoundEvent> register(String id) {
        return REGISTER.register(id, () -> SoundEvent.createVariableRangeEvent(MarvelSuperheroes.id(id)));
    }

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }
}
