
package com.ulto.marvel.potion;

@MarvelModElements.ModElement.Tag
public class IceingPotion extends MarvelModElements.ModElement {

	@ObjectHolder("marvel:iceing")
	public static final Effect potion = null;

	public IceingPotion(MarvelModElements instance) {
		super(instance, 190);

		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void registerEffect(RegistryEvent.Register<Effect> event) {
		event.getRegistry().register(new EffectCustom());
	}

	public static class EffectCustom extends Effect {

		private final ResourceLocation potionIcon;

		public EffectCustom() {
			super(EffectType.HARMFUL, -6697729);
			setRegistryName("iceing");
			potionIcon = new ResourceLocation("marvel:textures/iceing.png");
		}

		@Override
		public String getName() {
			return "effect.iceing";
		}

		@Override
		public boolean isBeneficial() {
			return false;
		}

		@Override
		public boolean isInstant() {
			return false;
		}

		@Override
		public boolean shouldRenderInvText(EffectInstance effect) {
			return true;
		}

		@Override
		public boolean shouldRender(EffectInstance effect) {
			return true;
		}

		@Override
		public boolean shouldRenderHUD(EffectInstance effect) {
			return true;
		}

		@Override
		public boolean isReady(int duration, int amplifier) {
			return true;
		}

	}

}
