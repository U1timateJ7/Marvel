package net.tintankgames.marvel.network;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.tintankgames.marvel.MarvelSuperheroes;

@EventBusSubscriber(modid = MarvelSuperheroes.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class MarvelNetworking {
    public static final CustomPacketPayload.Type<SuitAbilityMessage> SUIT_ABILITY = new CustomPacketPayload.Type<>(MarvelSuperheroes.id("suit_ability"));
    public static final CustomPacketPayload.Type<SuitTransformationMessage> SUIT_TRANSFORMATION = new CustomPacketPayload.Type<>(MarvelSuperheroes.id("suit_transformation"));
    public static final CustomPacketPayload.Type<ToggleHelmetMessage> TOGGLE_HELMET = new CustomPacketPayload.Type<>(MarvelSuperheroes.id("toggle_helmet"));
    public static final CustomPacketPayload.Type<SwitchSuitTabMessage> SWITCH_SUIT_TAB = new CustomPacketPayload.Type<>(MarvelSuperheroes.id("switch_suit_tab"));

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(MarvelSuperheroes.MOD_ID).versioned(MarvelSuperheroes.MOD_VERSION);
        registrar.playToServer(SUIT_ABILITY, SuitAbilityMessage.CODEC, SuitAbilityMessage::handle);
        registrar.playToServer(SUIT_TRANSFORMATION, SuitTransformationMessage.CODEC, SuitTransformationMessage::handle);
        registrar.playToServer(TOGGLE_HELMET, ToggleHelmetMessage.CODEC, ToggleHelmetMessage::handle);
        registrar.playToServer(SWITCH_SUIT_TAB, SwitchSuitTabMessage.CODEC, SwitchSuitTabMessage::handle);
    }
}
