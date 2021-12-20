
package com.ulto.marvel.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.Minecraft;

import com.ulto.marvel.world.inventory.VeronicaGuiMenu;
import com.ulto.marvel.procedures.WarMachineUnavailableProcedure;
import com.ulto.marvel.procedures.WarMachineAvailableProcedure;
import com.ulto.marvel.procedures.Mark47UnavailableProcedure;
import com.ulto.marvel.procedures.Mark47AvailableProcedure;
import com.ulto.marvel.procedures.Mark46UnavailableProcedure;
import com.ulto.marvel.procedures.Mark46AvailableProcedure;
import com.ulto.marvel.procedures.Mark43UnavailableProcedure;
import com.ulto.marvel.procedures.Mark43AvailableProcedure;
import com.ulto.marvel.procedures.Mark42UnavailableProcedure;
import com.ulto.marvel.procedures.Mark42AvailableProcedure;
import com.ulto.marvel.procedures.Mark39UnavailableProcedure;
import com.ulto.marvel.procedures.Mark39AvailableProcedure;
import com.ulto.marvel.procedures.Mark37UnavailableProcedure;
import com.ulto.marvel.procedures.Mark37AvailableProcedure;
import com.ulto.marvel.procedures.Mark33UnavailableProcedure;
import com.ulto.marvel.procedures.Mark33AvailableProcedure;
import com.ulto.marvel.procedures.Mark30UnavailableProcedure;
import com.ulto.marvel.procedures.Mark30AvailableProcedure;
import com.ulto.marvel.procedures.Mark25UnavailableProcedure;
import com.ulto.marvel.procedures.Mark25AvailableProcedure;
import com.ulto.marvel.procedures.Mark23UnavailableProcedure;
import com.ulto.marvel.procedures.Mark23AvailableProcedure;
import com.ulto.marvel.procedures.Mark22UnavailableProcedure;
import com.ulto.marvel.procedures.Mark22AvailableProcedure;
import com.ulto.marvel.procedures.Mark21UnavailableProcedure;
import com.ulto.marvel.procedures.Mark21AvailableProcedure;
import com.ulto.marvel.procedures.Mark20UnavailableProcedure;
import com.ulto.marvel.procedures.Mark20AvailableProcedure;
import com.ulto.marvel.procedures.Mark19UnavailableProcedure;
import com.ulto.marvel.procedures.Mark19AvailableProcedure;
import com.ulto.marvel.procedures.Mark17UnavailableProcedure;
import com.ulto.marvel.procedures.Mark17AvailableProcedure;
import com.ulto.marvel.procedures.Mark16UnavailableProcedure;
import com.ulto.marvel.procedures.Mark16AvailableProcedure;
import com.ulto.marvel.procedures.IronPatriotUnavailableProcedure;
import com.ulto.marvel.procedures.IronPatriotAvailableProcedure;
import com.ulto.marvel.network.VeronicaGuiButtonMessage;
import com.ulto.marvel.MarvelMod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class VeronicaGuiScreen extends AbstractContainerScreen<VeronicaGuiMenu> {
	private final Level world;
	private final int x, y, z;
	private final Player entity;

	public VeronicaGuiScreen(VeronicaGuiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 424;
		this.imageHeight = 200;
	}

	private static final ResourceLocation texture = new ResourceLocation("marvel:textures/veronica_gui.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, texture);
		this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		RenderSystem.setShaderTexture(0, new ResourceLocation("marvel:textures/veronica_mark21.png"));
		this.blit(ms, this.leftPos + 148, this.topPos + 60, 0, 0, 16, 16, 16, 16);

		RenderSystem.setShaderTexture(0, new ResourceLocation("marvel:textures/veronica_mark23.png"));
		this.blit(ms, this.leftPos + 4, this.topPos + 87, 0, 0, 16, 16, 16, 16);

		RenderSystem.setShaderTexture(0, new ResourceLocation("marvel:textures/veronica_mark30.png"));
		this.blit(ms, this.leftPos + 292, this.topPos + 87, 0, 0, 16, 16, 16, 16);

		RenderSystem.setShaderTexture(0, new ResourceLocation("marvel:textures/veronica_mark42.png"));
		this.blit(ms, this.leftPos + 4, this.topPos + 141, 0, 0, 16, 16, 16, 16);

		RenderSystem.setShaderTexture(0, new ResourceLocation("marvel:textures/veronica_mark46.png"));
		this.blit(ms, this.leftPos + 292, this.topPos + 141, 0, 0, 16, 16, 16, 16);

		RenderSystem.setShaderTexture(0, new ResourceLocation("marvel:textures/veronica_iron_patriot.png"));
		this.blit(ms, this.leftPos + 148, this.topPos + 168, 0, 0, 16, 16, 16, 16);

		RenderSystem.setShaderTexture(0, new ResourceLocation("marvel:textures/veronica_mark22.png"));
		this.blit(ms, this.leftPos + 292, this.topPos + 60, 0, 0, 16, 16, 16, 16);

		RenderSystem.setShaderTexture(0, new ResourceLocation("marvel:textures/veronica_mark25.png"));
		this.blit(ms, this.leftPos + 148, this.topPos + 87, 0, 0, 16, 16, 16, 16);

		RenderSystem.setShaderTexture(0, new ResourceLocation("marvel:textures/veronica_mark33.png"));
		this.blit(ms, this.leftPos + 4, this.topPos + 114, 0, 0, 16, 16, 16, 16);

		RenderSystem.setShaderTexture(0, new ResourceLocation("marvel:textures/veronica_mark43.png"));
		this.blit(ms, this.leftPos + 148, this.topPos + 141, 0, 0, 16, 16, 16, 16);

		RenderSystem.setShaderTexture(0, new ResourceLocation("marvel:textures/veronica_mark47.png"));
		this.blit(ms, this.leftPos + 4, this.topPos + 168, 0, 0, 16, 16, 16, 16);

		RenderSystem.setShaderTexture(0, new ResourceLocation("marvel:textures/veronica_war_machine_v2.png"));
		this.blit(ms, this.leftPos + 292, this.topPos + 168, 0, 0, 16, 16, 16, 16);

		RenderSystem.setShaderTexture(0, new ResourceLocation("marvel:textures/veronica_mark16.png"));
		this.blit(ms, this.leftPos + 4, this.topPos + 33, 0, 0, 16, 16, 16, 16);

		RenderSystem.setShaderTexture(0, new ResourceLocation("marvel:textures/veronica_mark17.png"));
		this.blit(ms, this.leftPos + 148, this.topPos + 33, 0, 0, 16, 16, 16, 16);

		RenderSystem.setShaderTexture(0, new ResourceLocation("marvel:textures/veronica_mark19.png"));
		this.blit(ms, this.leftPos + 292, this.topPos + 33, 0, 0, 16, 16, 16, 16);

		RenderSystem.setShaderTexture(0, new ResourceLocation("marvel:textures/veronica_mark20.png"));
		this.blit(ms, this.leftPos + 4, this.topPos + 60, 0, 0, 16, 16, 16, 16);

		RenderSystem.setShaderTexture(0, new ResourceLocation("marvel:textures/veronica_mark37.png"));
		this.blit(ms, this.leftPos + 148, this.topPos + 114, 0, 0, 16, 16, 16, 16);

		RenderSystem.setShaderTexture(0, new ResourceLocation("marvel:textures/veronica_mark39.png"));
		this.blit(ms, this.leftPos + 292, this.topPos + 114, 0, 0, 16, 16, 16, 16);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		drawString(poseStack, this.font, "Veronica", 186, 7, -12829636);
		if (Mark21AvailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Available", 166, 69, -16738048);
		if (Mark21UnavailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Unavailable", 166, 69, -3407872);
		drawString(poseStack, this.font, "Mark 21", 166, 60, -12829636);
		if (Mark23AvailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Available", 22, 96, -16738048);
		if (Mark23UnavailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Unavailable", 22, 96, -3407872);
		drawString(poseStack, this.font, "Mark 23", 22, 87, -12829636);
		if (Mark30AvailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Available", 310, 96, -16738048);
		if (Mark30UnavailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Unavailable", 310, 96, -3407872);
		drawString(poseStack, this.font, "Mark 30", 310, 87, -12829636);
		if (Mark42AvailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Available", 22, 150, -16738048);
		if (Mark42UnavailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Unavailable", 22, 150, -3407872);
		drawString(poseStack, this.font, "Mark 42", 22, 141, -12829636);
		if (Mark46AvailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Available", 310, 150, -16738048);
		if (Mark46UnavailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Unavailable", 310, 150, -3407872);
		drawString(poseStack, this.font, "Mark 46", 310, 141, -12829636);
		if (IronPatriotAvailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Available", 166, 177, -16738048);
		if (IronPatriotUnavailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Unavailable", 166, 177, -3407872);
		drawString(poseStack, this.font, "Iron Patriot", 166, 168, -12829636);
		if (Mark22AvailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Available", 310, 69, -16738048);
		if (Mark22UnavailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Unavailable", 310, 69, -3407872);
		drawString(poseStack, this.font, "Mark 22", 310, 60, -12829636);
		if (Mark25AvailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Available", 166, 96, -16738048);
		if (Mark25UnavailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Unavailable", 166, 96, -3407872);
		drawString(poseStack, this.font, "Mark 25", 166, 87, -12829636);
		if (Mark33AvailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Available", 22, 123, -16738048);
		if (Mark33UnavailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Unavailable", 22, 123, -3407872);
		drawString(poseStack, this.font, "Mark 33", 22, 114, -12829636);
		if (Mark43AvailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Available", 166, 150, -16738048);
		if (Mark43UnavailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Unavailable", 166, 150, -3407872);
		drawString(poseStack, this.font, "Mark 43", 166, 141, -12829636);
		if (Mark47AvailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Available", 22, 177, -16738048);
		if (Mark47UnavailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Unavailable", 22, 177, -3407872);
		drawString(poseStack, this.font, "Mark 47", 22, 168, -12829636);
		if (WarMachineAvailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Available", 310, 177, -16738048);
		if (WarMachineUnavailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Unavailable", 310, 177, -3407872);
		drawString(poseStack, this.font, "War Machine", 310, 168, -12829636);
		if (Mark16AvailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Available", 22, 42, -16738048);
		if (Mark16UnavailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Unavailable", 22, 42, -3407872);
		drawString(poseStack, this.font, "Mark 16", 22, 33, -12829636);
		if (Mark17AvailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Available", 166, 42, -16738048);
		if (Mark17UnavailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Unavailable", 166, 42, -3407872);
		drawString(poseStack, this.font, "Mark 17", 166, 33, -12829636);
		if (Mark19AvailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Available", 310, 42, -16738048);
		if (Mark19UnavailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Unavailable", 310, 42, -3407872);
		drawString(poseStack, this.font, "Mark 19", 310, 33, -12829636);
		if (Mark20AvailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Available", 22, 69, -16738048);
		if (Mark20UnavailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Unavailable", 22, 69, -3407872);
		drawString(poseStack, this.font, "Mark 20", 22, 60, -12829636);
		if (Mark37AvailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Available", 166, 123, -16738048);
		if (Mark37UnavailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Unavailable", 166, 123, -3407872);
		drawString(poseStack, this.font, "Mark 37", 166, 114, -12829636);
		if (Mark39AvailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Available", 310, 123, -16738048);
		if (Mark39UnavailableProcedure.execute(entity))
			drawString(poseStack, this.font, "Unavailable", 310, 123, -3407872);
		drawString(poseStack, this.font, "Mark 39", 310, 114, -12829636);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
	}

	@Override
	public void init() {
		super.init();
		this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
		this.addRenderableWidget(new Button(this.leftPos + 229, this.topPos + 60, 45, 20, new TextComponent("Summon"), e -> {
			if (true) {
				MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiButtonMessage(0, x, y, z));
				VeronicaGuiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 85, this.topPos + 87, 45, 20, new TextComponent("Summon"), e -> {
			if (true) {
				MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiButtonMessage(1, x, y, z));
				VeronicaGuiButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 373, this.topPos + 87, 45, 20, new TextComponent("Summon"), e -> {
			if (true) {
				MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiButtonMessage(2, x, y, z));
				VeronicaGuiButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 85, this.topPos + 141, 45, 20, new TextComponent("Summon"), e -> {
			if (true) {
				MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiButtonMessage(3, x, y, z));
				VeronicaGuiButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 373, this.topPos + 141, 45, 20, new TextComponent("Summon"), e -> {
			if (true) {
				MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiButtonMessage(4, x, y, z));
				VeronicaGuiButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 229, this.topPos + 168, 45, 20, new TextComponent("Summon"), e -> {
			if (true) {
				MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiButtonMessage(5, x, y, z));
				VeronicaGuiButtonMessage.handleButtonAction(entity, 5, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 373, this.topPos + 60, 45, 20, new TextComponent("Summon"), e -> {
			if (true) {
				MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiButtonMessage(6, x, y, z));
				VeronicaGuiButtonMessage.handleButtonAction(entity, 6, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 229, this.topPos + 87, 45, 20, new TextComponent("Summon"), e -> {
			if (true) {
				MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiButtonMessage(7, x, y, z));
				VeronicaGuiButtonMessage.handleButtonAction(entity, 7, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 85, this.topPos + 114, 45, 20, new TextComponent("Summon"), e -> {
			if (true) {
				MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiButtonMessage(8, x, y, z));
				VeronicaGuiButtonMessage.handleButtonAction(entity, 8, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 229, this.topPos + 141, 45, 20, new TextComponent("Summon"), e -> {
			if (true) {
				MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiButtonMessage(9, x, y, z));
				VeronicaGuiButtonMessage.handleButtonAction(entity, 9, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 85, this.topPos + 168, 45, 20, new TextComponent("Summon"), e -> {
			if (true) {
				MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiButtonMessage(10, x, y, z));
				VeronicaGuiButtonMessage.handleButtonAction(entity, 10, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 373, this.topPos + 168, 45, 20, new TextComponent("Summon"), e -> {
			if (true) {
				MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiButtonMessage(11, x, y, z));
				VeronicaGuiButtonMessage.handleButtonAction(entity, 11, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 85, this.topPos + 33, 45, 20, new TextComponent("Summon"), e -> {
			if (true) {
				MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiButtonMessage(12, x, y, z));
				VeronicaGuiButtonMessage.handleButtonAction(entity, 12, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 229, this.topPos + 33, 45, 20, new TextComponent("Summon"), e -> {
			if (true) {
				MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiButtonMessage(13, x, y, z));
				VeronicaGuiButtonMessage.handleButtonAction(entity, 13, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 373, this.topPos + 33, 45, 20, new TextComponent("Summon"), e -> {
			if (true) {
				MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiButtonMessage(14, x, y, z));
				VeronicaGuiButtonMessage.handleButtonAction(entity, 14, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 85, this.topPos + 60, 45, 20, new TextComponent("Summon"), e -> {
			if (true) {
				MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiButtonMessage(15, x, y, z));
				VeronicaGuiButtonMessage.handleButtonAction(entity, 15, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 229, this.topPos + 114, 45, 20, new TextComponent("Summon"), e -> {
			if (true) {
				MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiButtonMessage(16, x, y, z));
				VeronicaGuiButtonMessage.handleButtonAction(entity, 16, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 373, this.topPos + 114, 45, 20, new TextComponent("Summon"), e -> {
			if (true) {
				MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiButtonMessage(17, x, y, z));
				VeronicaGuiButtonMessage.handleButtonAction(entity, 17, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 4, this.topPos + 6, 125, 20, new TextComponent("House Party Protocol"), e -> {
			if (true) {
				MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiButtonMessage(18, x, y, z));
				VeronicaGuiButtonMessage.handleButtonAction(entity, 18, x, y, z);
			}
		}));
	}
}
