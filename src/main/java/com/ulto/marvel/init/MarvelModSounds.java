
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.ulto.marvel.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MarvelModSounds {
	public static Map<ResourceLocation, SoundEvent> REGISTRY = new HashMap<>();
	static {
		REGISTRY.put(new ResourceLocation("marvel", "item.vibranium_shield.throw"),
				new SoundEvent(new ResourceLocation("marvel", "item.vibranium_shield.throw")));
		REGISTRY.put(new ResourceLocation("marvel", "item.vibranium_shield.hit"),
				new SoundEvent(new ResourceLocation("marvel", "item.vibranium_shield.hit")));
		REGISTRY.put(new ResourceLocation("marvel", "music.marvel.star_spangled_man"),
				new SoundEvent(new ResourceLocation("marvel", "music.marvel.star_spangled_man")));
		REGISTRY.put(new ResourceLocation("marvel", "music.marvel.wakanda"), new SoundEvent(new ResourceLocation("marvel", "music.marvel.wakanda")));
		REGISTRY.put(new ResourceLocation("marvel", "item.iron_man_helmet.open"),
				new SoundEvent(new ResourceLocation("marvel", "item.iron_man_helmet.open")));
		REGISTRY.put(new ResourceLocation("marvel", "item.iron_man_helmet.close"),
				new SoundEvent(new ResourceLocation("marvel", "item.iron_man_helmet.close")));
		REGISTRY.put(new ResourceLocation("marvel", "item.repulsor.shoot"), new SoundEvent(new ResourceLocation("marvel", "item.repulsor.shoot")));
		REGISTRY.put(new ResourceLocation("marvel", "item.repulsor.use"), new SoundEvent(new ResourceLocation("marvel", "item.repulsor.use")));
		REGISTRY.put(new ResourceLocation("marvel", "item.unibeam.fire"), new SoundEvent(new ResourceLocation("marvel", "item.unibeam.fire")));
		REGISTRY.put(new ResourceLocation("marvel", "item.unibeam.use"), new SoundEvent(new ResourceLocation("marvel", "item.unibeam.use")));
		REGISTRY.put(new ResourceLocation("marvel", "music.marvel.driving_with_the_top_down"),
				new SoundEvent(new ResourceLocation("marvel", "music.marvel.driving_with_the_top_down")));
		REGISTRY.put(new ResourceLocation("marvel", "item.war_machine_gun.fire"),
				new SoundEvent(new ResourceLocation("marvel", "item.war_machine_gun.fire")));
		REGISTRY.put(new ResourceLocation("marvel", "item.web_shooter.fire"),
				new SoundEvent(new ResourceLocation("marvel", "item.web_shooter.fire")));
		REGISTRY.put(new ResourceLocation("marvel", "iron_man.mark30.invisible"),
				new SoundEvent(new ResourceLocation("marvel", "iron_man.mark30.invisible")));
		REGISTRY.put(new ResourceLocation("marvel", "iron_man.mark30.visible"),
				new SoundEvent(new ResourceLocation("marvel", "iron_man.mark30.visible")));
		REGISTRY.put(new ResourceLocation("marvel", "iron_man.fly"), new SoundEvent(new ResourceLocation("marvel", "iron_man.fly")));
		REGISTRY.put(new ResourceLocation("marvel", "item.iron_man_helmet.close_46"),
				new SoundEvent(new ResourceLocation("marvel", "item.iron_man_helmet.close_46")));
		REGISTRY.put(new ResourceLocation("marvel", "iron_man.mark5.suit_up"),
				new SoundEvent(new ResourceLocation("marvel", "iron_man.mark5.suit_up")));
		REGISTRY.put(new ResourceLocation("marvel", "iron_man.mark42.arrive"),
				new SoundEvent(new ResourceLocation("marvel", "iron_man.mark42.arrive")));
		REGISTRY.put(new ResourceLocation("marvel", "iron_man.mark42.depart"),
				new SoundEvent(new ResourceLocation("marvel", "iron_man.mark42.depart")));
		REGISTRY.put(new ResourceLocation("marvel", "iron_man.house_party_protocol"),
				new SoundEvent(new ResourceLocation("marvel", "iron_man.house_party_protocol")));
		REGISTRY.put(new ResourceLocation("marvel", "item.antman.helmet_open"),
				new SoundEvent(new ResourceLocation("marvel", "item.antman.helmet_open")));
		REGISTRY.put(new ResourceLocation("marvel", "item.antman.helmet_close"),
				new SoundEvent(new ResourceLocation("marvel", "item.antman.helmet_close")));
		REGISTRY.put(new ResourceLocation("marvel", "antman.shrink"), new SoundEvent(new ResourceLocation("marvel", "antman.shrink")));
		REGISTRY.put(new ResourceLocation("marvel", "antman.grow"), new SoundEvent(new ResourceLocation("marvel", "antman.grow")));
		REGISTRY.put(new ResourceLocation("marvel", "item.iron_man_helmet.nanotech_open"),
				new SoundEvent(new ResourceLocation("marvel", "item.iron_man_helmet.nanotech_open")));
		REGISTRY.put(new ResourceLocation("marvel", "item.iron_man_helmet.nanotech_close"),
				new SoundEvent(new ResourceLocation("marvel", "item.iron_man_helmet.nanotech_close")));
		REGISTRY.put(new ResourceLocation("marvel", "iron_man.mark50.suit_up"),
				new SoundEvent(new ResourceLocation("marvel", "iron_man.mark50.suit_up")));
		REGISTRY.put(new ResourceLocation("marvel", "iron_spider.suit_up"), new SoundEvent(new ResourceLocation("marvel", "iron_spider.suit_up")));
		REGISTRY.put(new ResourceLocation("marvel", "iron_man.mark85.suit_up"),
				new SoundEvent(new ResourceLocation("marvel", "iron_man.mark85.suit_up")));
		REGISTRY.put(new ResourceLocation("marvel", "item.iron_spider_arms.deactivate"),
				new SoundEvent(new ResourceLocation("marvel", "item.iron_spider_arms.deactivate")));
		REGISTRY.put(new ResourceLocation("marvel", "item.iron_spider_arms.activate"),
				new SoundEvent(new ResourceLocation("marvel", "item.iron_spider_arms.activate")));
		REGISTRY.put(new ResourceLocation("marvel", "item.iron_spider_arms.attack"),
				new SoundEvent(new ResourceLocation("marvel", "item.iron_spider_arms.attack")));
		REGISTRY.put(new ResourceLocation("marvel", "item.stormbreaker.hit"),
				new SoundEvent(new ResourceLocation("marvel", "item.stormbreaker.hit")));
		REGISTRY.put(new ResourceLocation("marvel", "item.stormbreaker.catch"),
				new SoundEvent(new ResourceLocation("marvel", "item.stormbreaker.catch")));
		REGISTRY.put(new ResourceLocation("marvel", "item.mjolnir.hit_shield"),
				new SoundEvent(new ResourceLocation("marvel", "item.mjolnir.hit_shield")));
		REGISTRY.put(new ResourceLocation("marvel", "item.mjolnir.catch"), new SoundEvent(new ResourceLocation("marvel", "item.mjolnir.catch")));
		REGISTRY.put(new ResourceLocation("marvel", "item.mjolnir.hit"), new SoundEvent(new ResourceLocation("marvel", "item.mjolnir.hit")));
		REGISTRY.put(new ResourceLocation("marvel", "item.mjolnir.throw"), new SoundEvent(new ResourceLocation("marvel", "item.mjolnir.throw")));
		REGISTRY.put(new ResourceLocation("marvel", "item.stormbreaker.thorw"),
				new SoundEvent(new ResourceLocation("marvel", "item.stormbreaker.thorw")));
		REGISTRY.put(new ResourceLocation("marvel", "item.adamantium_claws.retract"),
				new SoundEvent(new ResourceLocation("marvel", "item.adamantium_claws.retract")));
		REGISTRY.put(new ResourceLocation("marvel", "item.adamantium_claws.activate"),
				new SoundEvent(new ResourceLocation("marvel", "item.adamantium_claws.activate")));
	}

	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
		for (Map.Entry<ResourceLocation, SoundEvent> sound : REGISTRY.entrySet())
			event.getRegistry().register(sound.getValue().setRegistryName(sound.getKey()));
	}
}
