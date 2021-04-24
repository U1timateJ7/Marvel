package com.ulto.marvel;

import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Direction;
import net.minecraft.network.PacketBuffer;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.Minecraft;

import java.util.function.Supplier;

public class MarvelModVariables {
	public MarvelModVariables(MarvelModElements elements) {
		elements.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new,
				PlayerVariablesSyncMessage::handler);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
	}

	private void init(FMLCommonSetupEvent event) {
		CapabilityManager.INSTANCE.register(PlayerVariables.class, new PlayerVariablesStorage(), PlayerVariables::new);
	}
	@CapabilityInject(PlayerVariables.class)
	public static Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = null;
	@SubscribeEvent
	public void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof PlayerEntity && !(event.getObject() instanceof FakePlayer))
			event.addCapability(new ResourceLocation("marvel", "player_variables"), new PlayerVariablesProvider());
	}
	private static class PlayerVariablesProvider implements ICapabilitySerializable<INBT> {
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(PLAYER_VARIABLES_CAPABILITY::getDefaultInstance);
		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public INBT serializeNBT() {
			return PLAYER_VARIABLES_CAPABILITY.getStorage().writeNBT(PLAYER_VARIABLES_CAPABILITY, this.instance.orElseThrow(RuntimeException::new),
					null);
		}

		@Override
		public void deserializeNBT(INBT nbt) {
			PLAYER_VARIABLES_CAPABILITY.getStorage().readNBT(PLAYER_VARIABLES_CAPABILITY, this.instance.orElseThrow(RuntimeException::new), null,
					nbt);
		}
	}

	private static class PlayerVariablesStorage implements Capability.IStorage<PlayerVariables> {
		@Override
		public INBT writeNBT(Capability<PlayerVariables> capability, PlayerVariables instance, Direction side) {
			CompoundNBT nbt = new CompoundNBT();
			nbt.putBoolean("superSoldier", instance.superSoldier);
			nbt.putBoolean("hasEatenHeartShapedHerb", instance.hasEatenHeartShapedHerb);
			nbt.putDouble("ironManMkNum", instance.ironManMkNum);
			nbt.putBoolean("radioactive", instance.radioactive);
			nbt.putBoolean("mark21Ready", instance.mark21Ready);
			nbt.putBoolean("mark22Ready", instance.mark22Ready);
			nbt.putBoolean("mark23Ready", instance.mark23Ready);
			nbt.putBoolean("mark25Ready", instance.mark25Ready);
			nbt.putBoolean("mark30Ready", instance.mark30Ready);
			nbt.putBoolean("mark33Ready", instance.mark33Ready);
			nbt.putBoolean("mark42Ready", instance.mark42Ready);
			nbt.putBoolean("mark43Ready", instance.mark43Ready);
			nbt.putBoolean("mark46Ready", instance.mark46Ready);
			nbt.putBoolean("mark47Ready", instance.mark47Ready);
			nbt.putBoolean("warMachineMark2Ready", instance.warMachineMark2Ready);
			nbt.putBoolean("ironPatriotReady", instance.ironPatriotReady);
			return nbt;
		}

		@Override
		public void readNBT(Capability<PlayerVariables> capability, PlayerVariables instance, Direction side, INBT inbt) {
			CompoundNBT nbt = (CompoundNBT) inbt;
			instance.superSoldier = nbt.getBoolean("superSoldier");
			instance.hasEatenHeartShapedHerb = nbt.getBoolean("hasEatenHeartShapedHerb");
			instance.ironManMkNum = nbt.getDouble("ironManMkNum");
			instance.radioactive = nbt.getBoolean("radioactive");
			instance.mark21Ready = nbt.getBoolean("mark21Ready");
			instance.mark22Ready = nbt.getBoolean("mark22Ready");
			instance.mark23Ready = nbt.getBoolean("mark23Ready");
			instance.mark25Ready = nbt.getBoolean("mark25Ready");
			instance.mark30Ready = nbt.getBoolean("mark30Ready");
			instance.mark33Ready = nbt.getBoolean("mark33Ready");
			instance.mark42Ready = nbt.getBoolean("mark42Ready");
			instance.mark43Ready = nbt.getBoolean("mark43Ready");
			instance.mark46Ready = nbt.getBoolean("mark46Ready");
			instance.mark47Ready = nbt.getBoolean("mark47Ready");
			instance.warMachineMark2Ready = nbt.getBoolean("warMachineMark2Ready");
			instance.ironPatriotReady = nbt.getBoolean("ironPatriotReady");
		}
	}

	public static class PlayerVariables {
		public boolean superSoldier = false;
		public boolean hasEatenHeartShapedHerb = false;
		public double ironManMkNum = 0;
		public boolean radioactive = false;
		public boolean mark21Ready = false;
		public boolean mark22Ready = false;
		public boolean mark23Ready = false;
		public boolean mark25Ready = false;
		public boolean mark30Ready = false;
		public boolean mark33Ready = false;
		public boolean mark42Ready = false;
		public boolean mark43Ready = false;
		public boolean mark46Ready = false;
		public boolean mark47Ready = false;
		public boolean warMachineMark2Ready = false;
		public boolean ironPatriotReady = false;
		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayerEntity)
				MarvelMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) entity), new PlayerVariablesSyncMessage(this));
		}
	}
	@SubscribeEvent
	public void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
		if (!event.getPlayer().world.isRemote())
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
		if (!event.getPlayer().world.isRemote())
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (!event.getPlayer().world.isRemote())
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void clonePlayer(PlayerEvent.Clone event) {
		if (event.isWasDeath()) {
			PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new PlayerVariables()));
			PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new PlayerVariables()));
			clone.superSoldier = original.superSoldier;
			clone.hasEatenHeartShapedHerb = original.hasEatenHeartShapedHerb;
			clone.ironManMkNum = original.ironManMkNum;
			clone.radioactive = original.radioactive;
			clone.mark21Ready = original.mark21Ready;
			clone.mark22Ready = original.mark22Ready;
			clone.mark23Ready = original.mark23Ready;
			clone.mark25Ready = original.mark25Ready;
			clone.mark30Ready = original.mark30Ready;
			clone.mark33Ready = original.mark33Ready;
			clone.mark42Ready = original.mark42Ready;
			clone.mark43Ready = original.mark43Ready;
			clone.mark46Ready = original.mark46Ready;
			clone.mark47Ready = original.mark47Ready;
			clone.warMachineMark2Ready = original.warMachineMark2Ready;
			clone.ironPatriotReady = original.ironPatriotReady;
		}
	}
	public static class PlayerVariablesSyncMessage {
		public PlayerVariables data;
		public PlayerVariablesSyncMessage(PacketBuffer buffer) {
			this.data = new PlayerVariables();
			new PlayerVariablesStorage().readNBT(null, this.data, null, buffer.readCompoundTag());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data) {
			this.data = data;
		}

		public static void buffer(PlayerVariablesSyncMessage message, PacketBuffer buffer) {
			buffer.writeCompoundTag((CompoundNBT) new PlayerVariablesStorage().writeNBT(null, message.data, null));
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new PlayerVariables()));
					variables.superSoldier = message.data.superSoldier;
					variables.hasEatenHeartShapedHerb = message.data.hasEatenHeartShapedHerb;
					variables.ironManMkNum = message.data.ironManMkNum;
					variables.radioactive = message.data.radioactive;
					variables.mark21Ready = message.data.mark21Ready;
					variables.mark22Ready = message.data.mark22Ready;
					variables.mark23Ready = message.data.mark23Ready;
					variables.mark25Ready = message.data.mark25Ready;
					variables.mark30Ready = message.data.mark30Ready;
					variables.mark33Ready = message.data.mark33Ready;
					variables.mark42Ready = message.data.mark42Ready;
					variables.mark43Ready = message.data.mark43Ready;
					variables.mark46Ready = message.data.mark46Ready;
					variables.mark47Ready = message.data.mark47Ready;
					variables.warMachineMark2Ready = message.data.warMachineMark2Ready;
					variables.ironPatriotReady = message.data.ironPatriotReady;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
