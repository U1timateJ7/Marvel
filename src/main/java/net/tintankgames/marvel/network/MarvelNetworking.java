package net.tintankgames.marvel.network;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.attachment.TargetedEntity;
import net.tintankgames.marvel.attachment.VeronicaData;

@EventBusSubscriber(modid = MarvelSuperheroes.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class MarvelNetworking {
    public static final CustomPacketPayload.Type<PrimarySuitAbilityMessage> PRIMARY_SUIT_ABILITY = new CustomPacketPayload.Type<>(MarvelSuperheroes.id("primary_suit_ability"));
    public static final CustomPacketPayload.Type<SecondarySuitAbilityMessage> SECONDARY_SUIT_ABILITY = new CustomPacketPayload.Type<>(MarvelSuperheroes.id("secondary_suit_ability"));
    public static final CustomPacketPayload.Type<ToggleHelmetMessage> TOGGLE_HELMET = new CustomPacketPayload.Type<>(MarvelSuperheroes.id("toggle_helmet"));
    public static final CustomPacketPayload.Type<SwitchSuitTabMessage> SWITCH_SUIT_TAB = new CustomPacketPayload.Type<>(MarvelSuperheroes.id("switch_suit_tab"));
    public static final CustomPacketPayload.Type<SyncSprintMessage> SYNC_SPRINT = new CustomPacketPayload.Type<>(MarvelSuperheroes.id("sync_sprint"));
    public static final CustomPacketPayload.Type<OpenSpaceStoneMessage> OPEN_SPACE_STONE = new CustomPacketPayload.Type<>(MarvelSuperheroes.id("open_space_stone"));
    public static final CustomPacketPayload.Type<SpawnPointMessage> SPAWN_POINT = new CustomPacketPayload.Type<>(MarvelSuperheroes.id("spawn_point"));
    public static final CustomPacketPayload.Type<TpToPlayerMessage> TP_TO_PLAYER = new CustomPacketPayload.Type<>(MarvelSuperheroes.id("tp_to_player"));
    public static final CustomPacketPayload.Type<CallMjolnirMessage> CALL_MJOLNIR = new CustomPacketPayload.Type<>(MarvelSuperheroes.id("call_mjolnir"));
    public static final CustomPacketPayload.Type<OpenVeronicaMessage> OPEN_VERONICA = new CustomPacketPayload.Type<>(MarvelSuperheroes.id("open_veronica"));
    public static final CustomPacketPayload.Type<SendSuitMessage> SEND_SUIT = new CustomPacketPayload.Type<>(MarvelSuperheroes.id("send_suit"));

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(MarvelSuperheroes.MOD_ID).versioned(MarvelSuperheroes.MOD_VERSION);
        registrar.playToServer(PRIMARY_SUIT_ABILITY, PrimarySuitAbilityMessage.CODEC, PrimarySuitAbilityMessage::handle);
        registrar.playToServer(SECONDARY_SUIT_ABILITY, SecondarySuitAbilityMessage.CODEC, SecondarySuitAbilityMessage::handle);
        registrar.playToServer(TOGGLE_HELMET, ToggleHelmetMessage.CODEC, ToggleHelmetMessage::handle);
        registrar.playToServer(SWITCH_SUIT_TAB, SwitchSuitTabMessage.CODEC, SwitchSuitTabMessage::handle);
        registrar.playToServer(SYNC_SPRINT, SyncSprintMessage.CODEC, SyncSprintMessage::handle);
        registrar.playToClient(OPEN_SPACE_STONE, OpenSpaceStoneMessage.CODEC, OpenSpaceStoneMessage::handle);
        registrar.playToServer(SPAWN_POINT, SpawnPointMessage.CODEC, SpawnPointMessage::handle);
        registrar.playToServer(TP_TO_PLAYER, TpToPlayerMessage.CODEC, TpToPlayerMessage::handle);
        registrar.playToServer(CALL_MJOLNIR, CallMjolnirMessage.CODEC, CallMjolnirMessage::handle);
        registrar.playToClient(OPEN_VERONICA, OpenVeronicaMessage.CODEC, OpenVeronicaMessage::handle);
        registrar.playToServer(SEND_SUIT, SendSuitMessage.CODEC, SendSuitMessage::handle);
        registrar.playToClient(TargetedEntity.SyncMessage.TYPE, TargetedEntity.SyncMessage.CODEC, TargetedEntity.SyncMessage::handle);
        registrar.playToClient(VeronicaData.SyncMessage.TYPE, VeronicaData.SyncMessage.CODEC, VeronicaData.SyncMessage::handle);
    }
}
