package net.tintankgames.marvel.network;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.attachment.TargetedEntity;

@EventBusSubscriber(modid = MarvelSuperheroes.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class MarvelNetworking {
    public static final CustomPacketPayload.Type<PrimarySuitAbilityMessage> PRIMARY_SUIT_ABILITY = new CustomPacketPayload.Type<>(MarvelSuperheroes.id("primary_suit_ability"));
    public static final CustomPacketPayload.Type<SecondarySuitAbilityMessage> SECONDARY_SUIT_ABILITY = new CustomPacketPayload.Type<>(MarvelSuperheroes.id("secondary_suit_ability"));
    public static final CustomPacketPayload.Type<ToggleHelmetMessage> TOGGLE_HELMET = new CustomPacketPayload.Type<>(MarvelSuperheroes.id("toggle_helmet"));
    public static final CustomPacketPayload.Type<SwitchSuitTabMessage> SWITCH_SUIT_TAB = new CustomPacketPayload.Type<>(MarvelSuperheroes.id("switch_suit_tab"));

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(MarvelSuperheroes.MOD_ID).versioned(MarvelSuperheroes.MOD_VERSION);
        registrar.playToServer(PRIMARY_SUIT_ABILITY, PrimarySuitAbilityMessage.CODEC, PrimarySuitAbilityMessage::handle);
        registrar.playToServer(SECONDARY_SUIT_ABILITY, SecondarySuitAbilityMessage.CODEC, SecondarySuitAbilityMessage::handle);
        registrar.playToServer(TOGGLE_HELMET, ToggleHelmetMessage.CODEC, ToggleHelmetMessage::handle);
        registrar.playToServer(SWITCH_SUIT_TAB, SwitchSuitTabMessage.CODEC, SwitchSuitTabMessage::handle);
        registrar.playToClient(TargetedEntity.SyncMessage.TYPE, TargetedEntity.SyncMessage.CODEC, TargetedEntity.SyncMessage::handle);
    }
}
