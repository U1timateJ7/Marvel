package com.ulto.marvel.sounds;

import com.ulto.marvel.common.MarvelMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MarvelModSounds {
	private static final Map<ResourceLocation, SoundEvent> SOUNDS = new HashMap<>();
	static {
		SOUNDS.put(new ResourceLocation("marvel", "item.vibranium_shield.throw"), new SoundEvent(new ResourceLocation("marvel", "item.vibranium_shield.throw")));
		SOUNDS.put(new ResourceLocation("marvel", "item.vibranium_shield.hit"), new SoundEvent(new ResourceLocation("marvel", "item.vibranium_shield.hit")));
		SOUNDS.put(new ResourceLocation("marvel", "music.marvel.star_spangled_man"), new SoundEvent(new ResourceLocation("marvel", "music.marvel.star_spangled_man")));
		SOUNDS.put(new ResourceLocation("marvel", "music.marvel.wakanda"), new SoundEvent(new ResourceLocation("marvel", "music.marvel.wakanda")));
		SOUNDS.put(new ResourceLocation("marvel", "item.iron_man_helmet.open"), new SoundEvent(new ResourceLocation("marvel", "item.iron_man_helmet.open")));
		SOUNDS.put(new ResourceLocation("marvel", "item.iron_man_helmet.close"), new SoundEvent(new ResourceLocation("marvel", "item.iron_man_helmet.close")));
		SOUNDS.put(new ResourceLocation("marvel", "item.repulsor.shoot"), new SoundEvent(new ResourceLocation("marvel", "item.repulsor.shoot")));
		SOUNDS.put(new ResourceLocation("marvel", "item.repulsor.use"), new SoundEvent(new ResourceLocation("marvel", "item.repulsor.use")));
		SOUNDS.put(new ResourceLocation("marvel", "item.unibeam.fire"), new SoundEvent(new ResourceLocation("marvel", "item.unibeam.fire")));
		SOUNDS.put(new ResourceLocation("marvel", "item.unibeam.use"), new SoundEvent(new ResourceLocation("marvel", "item.unibeam.use")));
		SOUNDS.put(new ResourceLocation("marvel", "music.marvel.driving_with_the_top_down"), new SoundEvent(new ResourceLocation("marvel", "music.marvel.driving_with_the_top_down")));
		SOUNDS.put(new ResourceLocation("marvel", "item.war_machine_gun.fire"), new SoundEvent(new ResourceLocation("marvel", "item.war_machine_gun.fire")));
		SOUNDS.put(new ResourceLocation("marvel", "item.web_shooter.fire"), new SoundEvent(new ResourceLocation("marvel", "item.web_shooter.fire")));
		SOUNDS.put(new ResourceLocation("marvel", "iron_man.mark30.invisible"), new SoundEvent(new ResourceLocation("marvel", "iron_man.mark30.invisible")));
		SOUNDS.put(new ResourceLocation("marvel", "iron_man.mark30.visible"), new SoundEvent(new ResourceLocation("marvel", "iron_man.mark30.visible")));
		SOUNDS.put(new ResourceLocation("marvel", "iron_man.fly"), new SoundEvent(new ResourceLocation("marvel", "iron_man.fly")));
		SOUNDS.put(new ResourceLocation("marvel", "item.iron_man_helmet.close_46"), new SoundEvent(new ResourceLocation("marvel", "item.iron_man_helmet.close_46")));
		SOUNDS.put(new ResourceLocation("marvel", "iron_man.mark5.suit_up"), new SoundEvent(new ResourceLocation("marvel", "iron_man.mark5.suit_up")));
		SOUNDS.put(new ResourceLocation("marvel", "iron_man.mark42.arrive"), new SoundEvent(new ResourceLocation("marvel", "iron_man.mark42.arrive")));
		SOUNDS.put(new ResourceLocation("marvel", "iron_man.mark42.depart"), new SoundEvent(new ResourceLocation("marvel", "iron_man.mark42.depart")));
		SOUNDS.put(new ResourceLocation("marvel", "iron_man.house_party_protocol"), new SoundEvent(new ResourceLocation("marvel", "iron_man.house_party_protocol")));
		SOUNDS.put(new ResourceLocation("marvel", "item.antman.helmet_open"), new SoundEvent(new ResourceLocation("marvel", "item.antman.helmet_open")));
		SOUNDS.put(new ResourceLocation("marvel", "item.antman.helmet_close"), new SoundEvent(new ResourceLocation("marvel", "item.antman.helmet_close")));
		SOUNDS.put(new ResourceLocation("marvel", "antman.shrink"), new SoundEvent(new ResourceLocation("marvel", "antman.shrink")));
		SOUNDS.put(new ResourceLocation("marvel", "antman.grow"), new SoundEvent(new ResourceLocation("marvel", "antman.grow")));
		SOUNDS.put(new ResourceLocation("marvel", "item.iron_man_helmet.nanotech_open"), new SoundEvent(new ResourceLocation("marvel", "item.iron_man_helmet.nanotech_open")));
		SOUNDS.put(new ResourceLocation("marvel", "item.iron_man_helmet.nanotech_close"), new SoundEvent(new ResourceLocation("marvel", "item.iron_man_helmet.nanotech_close")));
		SOUNDS.put(new ResourceLocation("marvel", "iron_man.mark50.suit_up"), new SoundEvent(new ResourceLocation("marvel", "iron_man.mark50.suit_up")));
		SOUNDS.put(new ResourceLocation("marvel", "iron_spider.suit_up"), new SoundEvent(new ResourceLocation("marvel", "iron_spider.suit_up")));
		SOUNDS.put(new ResourceLocation("marvel", "iron_man.mark85.suit_up"), new SoundEvent(new ResourceLocation("marvel", "iron_man.mark85.suit_up")));
		SOUNDS.put(new ResourceLocation("marvel", "item.iron_spider_arms.deactivate"), new SoundEvent(new ResourceLocation("marvel", "item.iron_spider_arms.deactivate")));
		SOUNDS.put(new ResourceLocation("marvel", "item.iron_spider_arms.activate"), new SoundEvent(new ResourceLocation("marvel", "item.iron_spider_arms.activate")));
		SOUNDS.put(new ResourceLocation("marvel", "item.iron_spider_arms.attack"), new SoundEvent(new ResourceLocation("marvel", "item.iron_spider_arms.attack")));
		SOUNDS.put(new ResourceLocation("marvel", "item.stormbreaker.hit"), new SoundEvent(new ResourceLocation("marvel", "item.stormbreaker.hit")));
		SOUNDS.put(new ResourceLocation("marvel", "item.stormbreaker.catch"), new SoundEvent(new ResourceLocation("marvel", "item.stormbreaker.catch")));
		SOUNDS.put(new ResourceLocation("marvel", "item.mjolnir.hit_shield"), new SoundEvent(new ResourceLocation("marvel", "item.mjolnir.hit_shield")));
		SOUNDS.put(new ResourceLocation("marvel", "item.mjolnir.catch"), new SoundEvent(new ResourceLocation("marvel", "item.mjolnir.catch")));
		SOUNDS.put(new ResourceLocation("marvel", "item.mjolnir.hit"), new SoundEvent(new ResourceLocation("marvel", "item.mjolnir.hit")));
		SOUNDS.put(new ResourceLocation("marvel", "item.mjolnir.throw"), new SoundEvent(new ResourceLocation("marvel", "item.mjolnir.throw")));
		SOUNDS.put(new ResourceLocation("marvel", "item.stormbreaker.throw"), new SoundEvent(new ResourceLocation("marvel", "item.stormbreaker.throw")));
		SOUNDS.put(new ResourceLocation("marvel", "item.adamantium_claws.retract"), new SoundEvent(new ResourceLocation("marvel", "item.adamantium_claws.retract")));
		SOUNDS.put(new ResourceLocation("marvel", "item.adamantium_claws.activate"), new SoundEvent(new ResourceLocation("marvel", "item.adamantium_claws.activate")));
	}

	public static SoundEvent get(String id) {
		return SOUNDS.getOrDefault(id.contains(":") ? new ResourceLocation(id) : new ResourceLocation(MarvelMod.MOD_ID, id), SoundEvents.ANVIL_DESTROY);
	}

	public static SoundEvent get(ResourceLocation id) {
		return SOUNDS.getOrDefault(id, SoundEvents.ANVIL_DESTROY);
	}

	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
		for (Map.Entry<ResourceLocation, SoundEvent> sound : SOUNDS.entrySet())
			event.getRegistry().register(sound.getValue().setRegistryName(sound.getKey()));
	}
}
