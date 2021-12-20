package com.ulto.marvel.network;

import net.minecraftforge.fmllegacy.network.PacketDistributor;
import net.minecraftforge.fmllegacy.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.client.Minecraft;

import java.util.function.Supplier;

import com.ulto.marvel.MarvelMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MarvelModVariables {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		MarvelMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new,
				PlayerVariablesSyncMessage::handler);
	}

	@SubscribeEvent
	public static void init(RegisterCapabilitiesEvent event) {
		event.register(PlayerVariables.class);
	}

	@Mod.EventBusSubscriber
	public static class EventBusVariableHandlers {
		@SubscribeEvent
		public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getPlayer().level.isClientSide())
				((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
						.syncPlayerVariables(event.getPlayer());
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (!event.getPlayer().level.isClientSide())
				((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
						.syncPlayerVariables(event.getPlayer());
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getPlayer().level.isClientSide())
				((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
						.syncPlayerVariables(event.getPlayer());
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			event.getOriginal().revive();
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
			clone.adamantiumClaws = original.adamantiumClaws;
			clone.isMutantWovlerine = original.isMutantWovlerine;
			clone.isMutantQuicksilver = original.isMutantQuicksilver;
			clone.isMutant = original.isMutant;
			clone.inventory0 = original.inventory0;
			clone.inventory1 = original.inventory1;
			clone.inventory2 = original.inventory2;
			clone.inventory3 = original.inventory3;
			clone.inventory4 = original.inventory4;
			clone.inventory5 = original.inventory5;
			clone.inventory6 = original.inventory6;
			clone.inventory8 = original.inventory8;
			clone.inventory9 = original.inventory9;
			clone.inventory10 = original.inventory10;
			clone.inventory11 = original.inventory11;
			clone.inventory12 = original.inventory12;
			clone.inventory13 = original.inventory13;
			clone.inventory14 = original.inventory14;
			clone.inventory15 = original.inventory15;
			clone.inventory16 = original.inventory16;
			clone.inventory17 = original.inventory17;
			clone.inventory18 = original.inventory18;
			clone.inventory19 = original.inventory19;
			clone.inventory20 = original.inventory20;
			clone.inventory21 = original.inventory21;
			clone.inventory22 = original.inventory22;
			clone.inventory23 = original.inventory23;
			clone.inventory24 = original.inventory24;
			clone.inventory25 = original.inventory25;
			clone.inventory26 = original.inventory26;
			clone.hotbar0 = original.hotbar0;
			clone.hotbar1 = original.hotbar1;
			clone.hotbar2 = original.hotbar2;
			clone.hotbar3 = original.hotbar3;
			clone.hotbar4 = original.hotbar4;
			clone.hotbar5 = original.hotbar5;
			clone.hotbar6 = original.hotbar6;
			clone.hotbar7 = original.hotbar7;
			clone.hotbar8 = original.hotbar8;
			clone.offhand0 = original.offhand0;
			clone.armor0 = original.armor0;
			clone.armor1 = original.armor1;
			clone.armor2 = original.armor2;
			clone.armor3 = original.armor3;
			clone.health = original.health;
			clone.posX = original.posX;
			clone.posY = original.posY;
			clone.posZ = original.posZ;
			clone.yaw = original.yaw;
			clone.pitch = original.pitch;
			clone.controllingMark47 = original.controllingMark47;
			clone.inventory7 = original.inventory7;
			clone.hunger = original.hunger;
			clone.saturation = original.saturation;
			clone.xpLevels = original.xpLevels;
			clone.mark16Ready = original.mark16Ready;
			clone.mark17Ready = original.mark17Ready;
			clone.mark19Ready = original.mark19Ready;
			clone.mark20Ready = original.mark20Ready;
			clone.mark37Ready = original.mark37Ready;
			clone.mark39Ready = original.mark39Ready;
			if (!event.isWasDeath()) {
				clone.isSmall = original.isSmall;
				clone.isBig = original.isBig;
			}
		}
	}

	public static final Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerVariables>() {
	});

	@Mod.EventBusSubscriber
	private static class PlayerVariablesProvider implements ICapabilitySerializable<Tag> {
		@SubscribeEvent
		public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
				event.addCapability(new ResourceLocation("marvel", "player_variables"), new PlayerVariablesProvider());
		}

		private final PlayerVariables playerVariables = new PlayerVariables();
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> playerVariables);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public Tag serializeNBT() {
			return playerVariables.writeNBT();
		}

		@Override
		public void deserializeNBT(Tag nbt) {
			playerVariables.readNBT(nbt);
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
		public boolean isSmall = false;
		public boolean isBig = false;
		public boolean adamantiumClaws = false;
		public boolean isMutantWovlerine = false;
		public boolean isMutantQuicksilver = false;
		public boolean isMutant = false;
		public ItemStack inventory0 = ItemStack.EMPTY;
		public ItemStack inventory1 = ItemStack.EMPTY;
		public ItemStack inventory2 = ItemStack.EMPTY;
		public ItemStack inventory3 = ItemStack.EMPTY;
		public ItemStack inventory4 = ItemStack.EMPTY;
		public ItemStack inventory5 = ItemStack.EMPTY;
		public ItemStack inventory6 = ItemStack.EMPTY;
		public ItemStack inventory8 = ItemStack.EMPTY;
		public ItemStack inventory9 = ItemStack.EMPTY;
		public ItemStack inventory10 = ItemStack.EMPTY;
		public ItemStack inventory11 = ItemStack.EMPTY;
		public ItemStack inventory12 = ItemStack.EMPTY;
		public ItemStack inventory13 = ItemStack.EMPTY;
		public ItemStack inventory14 = ItemStack.EMPTY;
		public ItemStack inventory15 = ItemStack.EMPTY;
		public ItemStack inventory16 = ItemStack.EMPTY;
		public ItemStack inventory17 = ItemStack.EMPTY;
		public ItemStack inventory18 = ItemStack.EMPTY;
		public ItemStack inventory19 = ItemStack.EMPTY;
		public ItemStack inventory20 = ItemStack.EMPTY;
		public ItemStack inventory21 = ItemStack.EMPTY;
		public ItemStack inventory22 = ItemStack.EMPTY;
		public ItemStack inventory23 = ItemStack.EMPTY;
		public ItemStack inventory24 = ItemStack.EMPTY;
		public ItemStack inventory25 = ItemStack.EMPTY;
		public ItemStack inventory26 = ItemStack.EMPTY;
		public ItemStack hotbar0 = ItemStack.EMPTY;
		public ItemStack hotbar1 = ItemStack.EMPTY;
		public ItemStack hotbar2 = ItemStack.EMPTY;
		public ItemStack hotbar3 = ItemStack.EMPTY;
		public ItemStack hotbar4 = ItemStack.EMPTY;
		public ItemStack hotbar5 = ItemStack.EMPTY;
		public ItemStack hotbar6 = ItemStack.EMPTY;
		public ItemStack hotbar7 = ItemStack.EMPTY;
		public ItemStack hotbar8 = ItemStack.EMPTY;
		public ItemStack offhand0 = ItemStack.EMPTY;
		public ItemStack armor0 = ItemStack.EMPTY;
		public ItemStack armor1 = ItemStack.EMPTY;
		public ItemStack armor2 = ItemStack.EMPTY;
		public ItemStack armor3 = ItemStack.EMPTY;
		public double health = 0;
		public double posX = 0;
		public double posY = 0;
		public double posZ = 0;
		public double yaw = 0;
		public double pitch = 0;
		public boolean controllingMark47 = false;
		public ItemStack inventory7 = ItemStack.EMPTY;
		public double hunger = 0;
		public double saturation = 0;
		public double xpLevels = 0;
		public boolean mark16Ready = false;
		public boolean mark17Ready = false;
		public boolean mark19Ready = false;
		public boolean mark20Ready = false;
		public boolean mark37Ready = false;
		public boolean mark39Ready = false;

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer)
				MarvelMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlayerVariablesSyncMessage(this));
		}

		public Tag writeNBT() {
			CompoundTag nbt = new CompoundTag();
			nbt.putBoolean("superSoldier", superSoldier);
			nbt.putBoolean("hasEatenHeartShapedHerb", hasEatenHeartShapedHerb);
			nbt.putDouble("ironManMkNum", ironManMkNum);
			nbt.putBoolean("radioactive", radioactive);
			nbt.putBoolean("mark21Ready", mark21Ready);
			nbt.putBoolean("mark22Ready", mark22Ready);
			nbt.putBoolean("mark23Ready", mark23Ready);
			nbt.putBoolean("mark25Ready", mark25Ready);
			nbt.putBoolean("mark30Ready", mark30Ready);
			nbt.putBoolean("mark33Ready", mark33Ready);
			nbt.putBoolean("mark42Ready", mark42Ready);
			nbt.putBoolean("mark43Ready", mark43Ready);
			nbt.putBoolean("mark46Ready", mark46Ready);
			nbt.putBoolean("mark47Ready", mark47Ready);
			nbt.putBoolean("warMachineMark2Ready", warMachineMark2Ready);
			nbt.putBoolean("ironPatriotReady", ironPatriotReady);
			nbt.putBoolean("isSmall", isSmall);
			nbt.putBoolean("isBig", isBig);
			nbt.putBoolean("adamantiumClaws", adamantiumClaws);
			nbt.putBoolean("isMutantWovlerine", isMutantWovlerine);
			nbt.putBoolean("isMutantQuicksilver", isMutantQuicksilver);
			nbt.putBoolean("isMutant", isMutant);
			nbt.put("inventory0", inventory0.save(new CompoundTag()));
			nbt.put("inventory1", inventory1.save(new CompoundTag()));
			nbt.put("inventory2", inventory2.save(new CompoundTag()));
			nbt.put("inventory3", inventory3.save(new CompoundTag()));
			nbt.put("inventory4", inventory4.save(new CompoundTag()));
			nbt.put("inventory5", inventory5.save(new CompoundTag()));
			nbt.put("inventory6", inventory6.save(new CompoundTag()));
			nbt.put("inventory8", inventory8.save(new CompoundTag()));
			nbt.put("inventory9", inventory9.save(new CompoundTag()));
			nbt.put("inventory10", inventory10.save(new CompoundTag()));
			nbt.put("inventory11", inventory11.save(new CompoundTag()));
			nbt.put("inventory12", inventory12.save(new CompoundTag()));
			nbt.put("inventory13", inventory13.save(new CompoundTag()));
			nbt.put("inventory14", inventory14.save(new CompoundTag()));
			nbt.put("inventory15", inventory15.save(new CompoundTag()));
			nbt.put("inventory16", inventory16.save(new CompoundTag()));
			nbt.put("inventory17", inventory17.save(new CompoundTag()));
			nbt.put("inventory18", inventory18.save(new CompoundTag()));
			nbt.put("inventory19", inventory19.save(new CompoundTag()));
			nbt.put("inventory20", inventory20.save(new CompoundTag()));
			nbt.put("inventory21", inventory21.save(new CompoundTag()));
			nbt.put("inventory22", inventory22.save(new CompoundTag()));
			nbt.put("inventory23", inventory23.save(new CompoundTag()));
			nbt.put("inventory24", inventory24.save(new CompoundTag()));
			nbt.put("inventory25", inventory25.save(new CompoundTag()));
			nbt.put("inventory26", inventory26.save(new CompoundTag()));
			nbt.put("hotbar0", hotbar0.save(new CompoundTag()));
			nbt.put("hotbar1", hotbar1.save(new CompoundTag()));
			nbt.put("hotbar2", hotbar2.save(new CompoundTag()));
			nbt.put("hotbar3", hotbar3.save(new CompoundTag()));
			nbt.put("hotbar4", hotbar4.save(new CompoundTag()));
			nbt.put("hotbar5", hotbar5.save(new CompoundTag()));
			nbt.put("hotbar6", hotbar6.save(new CompoundTag()));
			nbt.put("hotbar7", hotbar7.save(new CompoundTag()));
			nbt.put("hotbar8", hotbar8.save(new CompoundTag()));
			nbt.put("offhand0", offhand0.save(new CompoundTag()));
			nbt.put("armor0", armor0.save(new CompoundTag()));
			nbt.put("armor1", armor1.save(new CompoundTag()));
			nbt.put("armor2", armor2.save(new CompoundTag()));
			nbt.put("armor3", armor3.save(new CompoundTag()));
			nbt.putDouble("health", health);
			nbt.putDouble("posX", posX);
			nbt.putDouble("posY", posY);
			nbt.putDouble("posZ", posZ);
			nbt.putDouble("yaw", yaw);
			nbt.putDouble("pitch", pitch);
			nbt.putBoolean("controllingMark47", controllingMark47);
			nbt.put("inventory7", inventory7.save(new CompoundTag()));
			nbt.putDouble("hunger", hunger);
			nbt.putDouble("saturation", saturation);
			nbt.putDouble("xpLevels", xpLevels);
			nbt.putBoolean("mark16Ready", mark16Ready);
			nbt.putBoolean("mark17Ready", mark17Ready);
			nbt.putBoolean("mark19Ready", mark19Ready);
			nbt.putBoolean("mark20Ready", mark20Ready);
			nbt.putBoolean("mark37Ready", mark37Ready);
			nbt.putBoolean("mark39Ready", mark39Ready);
			return nbt;
		}

		public void readNBT(Tag Tag) {
			CompoundTag nbt = (CompoundTag) Tag;
			superSoldier = nbt.getBoolean("superSoldier");
			hasEatenHeartShapedHerb = nbt.getBoolean("hasEatenHeartShapedHerb");
			ironManMkNum = nbt.getDouble("ironManMkNum");
			radioactive = nbt.getBoolean("radioactive");
			mark21Ready = nbt.getBoolean("mark21Ready");
			mark22Ready = nbt.getBoolean("mark22Ready");
			mark23Ready = nbt.getBoolean("mark23Ready");
			mark25Ready = nbt.getBoolean("mark25Ready");
			mark30Ready = nbt.getBoolean("mark30Ready");
			mark33Ready = nbt.getBoolean("mark33Ready");
			mark42Ready = nbt.getBoolean("mark42Ready");
			mark43Ready = nbt.getBoolean("mark43Ready");
			mark46Ready = nbt.getBoolean("mark46Ready");
			mark47Ready = nbt.getBoolean("mark47Ready");
			warMachineMark2Ready = nbt.getBoolean("warMachineMark2Ready");
			ironPatriotReady = nbt.getBoolean("ironPatriotReady");
			isSmall = nbt.getBoolean("isSmall");
			isBig = nbt.getBoolean("isBig");
			adamantiumClaws = nbt.getBoolean("adamantiumClaws");
			isMutantWovlerine = nbt.getBoolean("isMutantWovlerine");
			isMutantQuicksilver = nbt.getBoolean("isMutantQuicksilver");
			isMutant = nbt.getBoolean("isMutant");
			inventory0 = ItemStack.of(nbt.getCompound("inventory0"));
			inventory1 = ItemStack.of(nbt.getCompound("inventory1"));
			inventory2 = ItemStack.of(nbt.getCompound("inventory2"));
			inventory3 = ItemStack.of(nbt.getCompound("inventory3"));
			inventory4 = ItemStack.of(nbt.getCompound("inventory4"));
			inventory5 = ItemStack.of(nbt.getCompound("inventory5"));
			inventory6 = ItemStack.of(nbt.getCompound("inventory6"));
			inventory8 = ItemStack.of(nbt.getCompound("inventory8"));
			inventory9 = ItemStack.of(nbt.getCompound("inventory9"));
			inventory10 = ItemStack.of(nbt.getCompound("inventory10"));
			inventory11 = ItemStack.of(nbt.getCompound("inventory11"));
			inventory12 = ItemStack.of(nbt.getCompound("inventory12"));
			inventory13 = ItemStack.of(nbt.getCompound("inventory13"));
			inventory14 = ItemStack.of(nbt.getCompound("inventory14"));
			inventory15 = ItemStack.of(nbt.getCompound("inventory15"));
			inventory16 = ItemStack.of(nbt.getCompound("inventory16"));
			inventory17 = ItemStack.of(nbt.getCompound("inventory17"));
			inventory18 = ItemStack.of(nbt.getCompound("inventory18"));
			inventory19 = ItemStack.of(nbt.getCompound("inventory19"));
			inventory20 = ItemStack.of(nbt.getCompound("inventory20"));
			inventory21 = ItemStack.of(nbt.getCompound("inventory21"));
			inventory22 = ItemStack.of(nbt.getCompound("inventory22"));
			inventory23 = ItemStack.of(nbt.getCompound("inventory23"));
			inventory24 = ItemStack.of(nbt.getCompound("inventory24"));
			inventory25 = ItemStack.of(nbt.getCompound("inventory25"));
			inventory26 = ItemStack.of(nbt.getCompound("inventory26"));
			hotbar0 = ItemStack.of(nbt.getCompound("hotbar0"));
			hotbar1 = ItemStack.of(nbt.getCompound("hotbar1"));
			hotbar2 = ItemStack.of(nbt.getCompound("hotbar2"));
			hotbar3 = ItemStack.of(nbt.getCompound("hotbar3"));
			hotbar4 = ItemStack.of(nbt.getCompound("hotbar4"));
			hotbar5 = ItemStack.of(nbt.getCompound("hotbar5"));
			hotbar6 = ItemStack.of(nbt.getCompound("hotbar6"));
			hotbar7 = ItemStack.of(nbt.getCompound("hotbar7"));
			hotbar8 = ItemStack.of(nbt.getCompound("hotbar8"));
			offhand0 = ItemStack.of(nbt.getCompound("offhand0"));
			armor0 = ItemStack.of(nbt.getCompound("armor0"));
			armor1 = ItemStack.of(nbt.getCompound("armor1"));
			armor2 = ItemStack.of(nbt.getCompound("armor2"));
			armor3 = ItemStack.of(nbt.getCompound("armor3"));
			health = nbt.getDouble("health");
			posX = nbt.getDouble("posX");
			posY = nbt.getDouble("posY");
			posZ = nbt.getDouble("posZ");
			yaw = nbt.getDouble("yaw");
			pitch = nbt.getDouble("pitch");
			controllingMark47 = nbt.getBoolean("controllingMark47");
			inventory7 = ItemStack.of(nbt.getCompound("inventory7"));
			hunger = nbt.getDouble("hunger");
			saturation = nbt.getDouble("saturation");
			xpLevels = nbt.getDouble("xpLevels");
			mark16Ready = nbt.getBoolean("mark16Ready");
			mark17Ready = nbt.getBoolean("mark17Ready");
			mark19Ready = nbt.getBoolean("mark19Ready");
			mark20Ready = nbt.getBoolean("mark20Ready");
			mark37Ready = nbt.getBoolean("mark37Ready");
			mark39Ready = nbt.getBoolean("mark39Ready");
		}
	}

	public static class PlayerVariablesSyncMessage {
		public PlayerVariables data;

		public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
			this.data = new PlayerVariables();
			this.data.readNBT(buffer.readNbt());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data) {
			this.data = data;
		}

		public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeNbt((CompoundTag) message.data.writeNBT());
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
					variables.isSmall = message.data.isSmall;
					variables.isBig = message.data.isBig;
					variables.adamantiumClaws = message.data.adamantiumClaws;
					variables.isMutantWovlerine = message.data.isMutantWovlerine;
					variables.isMutantQuicksilver = message.data.isMutantQuicksilver;
					variables.isMutant = message.data.isMutant;
					variables.inventory0 = message.data.inventory0;
					variables.inventory1 = message.data.inventory1;
					variables.inventory2 = message.data.inventory2;
					variables.inventory3 = message.data.inventory3;
					variables.inventory4 = message.data.inventory4;
					variables.inventory5 = message.data.inventory5;
					variables.inventory6 = message.data.inventory6;
					variables.inventory8 = message.data.inventory8;
					variables.inventory9 = message.data.inventory9;
					variables.inventory10 = message.data.inventory10;
					variables.inventory11 = message.data.inventory11;
					variables.inventory12 = message.data.inventory12;
					variables.inventory13 = message.data.inventory13;
					variables.inventory14 = message.data.inventory14;
					variables.inventory15 = message.data.inventory15;
					variables.inventory16 = message.data.inventory16;
					variables.inventory17 = message.data.inventory17;
					variables.inventory18 = message.data.inventory18;
					variables.inventory19 = message.data.inventory19;
					variables.inventory20 = message.data.inventory20;
					variables.inventory21 = message.data.inventory21;
					variables.inventory22 = message.data.inventory22;
					variables.inventory23 = message.data.inventory23;
					variables.inventory24 = message.data.inventory24;
					variables.inventory25 = message.data.inventory25;
					variables.inventory26 = message.data.inventory26;
					variables.hotbar0 = message.data.hotbar0;
					variables.hotbar1 = message.data.hotbar1;
					variables.hotbar2 = message.data.hotbar2;
					variables.hotbar3 = message.data.hotbar3;
					variables.hotbar4 = message.data.hotbar4;
					variables.hotbar5 = message.data.hotbar5;
					variables.hotbar6 = message.data.hotbar6;
					variables.hotbar7 = message.data.hotbar7;
					variables.hotbar8 = message.data.hotbar8;
					variables.offhand0 = message.data.offhand0;
					variables.armor0 = message.data.armor0;
					variables.armor1 = message.data.armor1;
					variables.armor2 = message.data.armor2;
					variables.armor3 = message.data.armor3;
					variables.health = message.data.health;
					variables.posX = message.data.posX;
					variables.posY = message.data.posY;
					variables.posZ = message.data.posZ;
					variables.yaw = message.data.yaw;
					variables.pitch = message.data.pitch;
					variables.controllingMark47 = message.data.controllingMark47;
					variables.inventory7 = message.data.inventory7;
					variables.hunger = message.data.hunger;
					variables.saturation = message.data.saturation;
					variables.xpLevels = message.data.xpLevels;
					variables.mark16Ready = message.data.mark16Ready;
					variables.mark17Ready = message.data.mark17Ready;
					variables.mark19Ready = message.data.mark19Ready;
					variables.mark20Ready = message.data.mark20Ready;
					variables.mark37Ready = message.data.mark37Ready;
					variables.mark39Ready = message.data.mark39Ready;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
