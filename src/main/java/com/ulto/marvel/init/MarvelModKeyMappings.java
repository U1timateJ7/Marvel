
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.ulto.marvel.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fmlclient.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import com.ulto.marvel.network.ToggleHelmetMessage;
import com.ulto.marvel.network.SentryModeKeyMessage;
import com.ulto.marvel.network.IronManAbilityMessage;
import com.ulto.marvel.network.AbilityKeyMessage;
import com.ulto.marvel.MarvelMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class MarvelModKeyMappings {
	public static final KeyMapping TOGGLE_HELMET = new KeyMapping("key.marvel.toggle_helmet", GLFW.GLFW_KEY_H, "key.categories.misc");
	public static final KeyMapping IRON_MAN_ABILITY = new KeyMapping("key.marvel.iron_man_ability", GLFW.GLFW_KEY_V, "key.categories.misc");
	public static final KeyMapping ABILITY_KEY = new KeyMapping("key.marvel.ability_key", GLFW.GLFW_KEY_C, "key.categories.misc");
	public static final KeyMapping SENTRY_MODE_KEY = new KeyMapping("key.marvel.sentry_mode_key", GLFW.GLFW_KEY_X, "key.categories.misc");
	private static long ABILITY_KEY_LASTPRESS = 0;

	@SubscribeEvent
	public static void registerKeyBindings(FMLClientSetupEvent event) {
		ClientRegistry.registerKeyBinding(TOGGLE_HELMET);
		ClientRegistry.registerKeyBinding(IRON_MAN_ABILITY);
		ClientRegistry.registerKeyBinding(ABILITY_KEY);
		ClientRegistry.registerKeyBinding(SENTRY_MODE_KEY);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onKeyInput(InputEvent.KeyInputEvent event) {
			if (Minecraft.getInstance().screen == null) {
				if (event.getKey() == TOGGLE_HELMET.getKey().getValue()) {
					if (event.getAction() == GLFW.GLFW_PRESS) {
						MarvelMod.PACKET_HANDLER.sendToServer(new ToggleHelmetMessage(0, 0));
						ToggleHelmetMessage.pressAction(Minecraft.getInstance().player, 0, 0);
					}
				}
				if (event.getKey() == IRON_MAN_ABILITY.getKey().getValue()) {
					if (event.getAction() == GLFW.GLFW_PRESS) {
						MarvelMod.PACKET_HANDLER.sendToServer(new IronManAbilityMessage(0, 0));
						IronManAbilityMessage.pressAction(Minecraft.getInstance().player, 0, 0);
					}
				}
				if (event.getKey() == ABILITY_KEY.getKey().getValue()) {
					if (event.getAction() == GLFW.GLFW_PRESS) {
						MarvelMod.PACKET_HANDLER.sendToServer(new AbilityKeyMessage(0, 0));
						AbilityKeyMessage.pressAction(Minecraft.getInstance().player, 0, 0);
						ABILITY_KEY_LASTPRESS = System.currentTimeMillis();
					} else if (event.getAction() == GLFW.GLFW_RELEASE) {
						int dt = (int) (System.currentTimeMillis() - ABILITY_KEY_LASTPRESS);
						MarvelMod.PACKET_HANDLER.sendToServer(new AbilityKeyMessage(1, dt));
						AbilityKeyMessage.pressAction(Minecraft.getInstance().player, 1, dt);
					}
				}
				if (event.getKey() == SENTRY_MODE_KEY.getKey().getValue()) {
					if (event.getAction() == GLFW.GLFW_PRESS) {
						MarvelMod.PACKET_HANDLER.sendToServer(new SentryModeKeyMessage(0, 0));
						SentryModeKeyMessage.pressAction(Minecraft.getInstance().player, 0, 0);
					}
				}
			}
		}
	}
}
