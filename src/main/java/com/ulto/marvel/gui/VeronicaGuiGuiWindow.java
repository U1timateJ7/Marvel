
package com.ulto.marvel.gui;

import org.lwjgl.opengl.GL11;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

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
import com.ulto.marvel.procedures.IronPatriotUnavailableProcedure;
import com.ulto.marvel.procedures.IronPatriotAvailableProcedure;
import com.ulto.marvel.MarvelMod;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.google.common.collect.ImmutableMap;

@OnlyIn(Dist.CLIENT)
public class VeronicaGuiGuiWindow extends ContainerScreen<VeronicaGuiGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	public VeronicaGuiGuiWindow(VeronicaGuiGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 300;
		this.ySize = 200;
	}
	private static final ResourceLocation texture = new ResourceLocation("marvel:textures/veronica_gui.png");
	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float par1, int par2, int par3) {
		GL11.glColor4f(1, 1, 1, 1);
		Minecraft.getInstance().getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.blit(ms, k, l, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("marvel:textures/veronica_mark21.png"));
		this.blit(ms, this.guiLeft + 14, this.guiTop + 33, 0, 0, 16, 16, 16, 16);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("marvel:textures/veronica_mark23.png"));
		this.blit(ms, this.guiLeft + 14, this.guiTop + 60, 0, 0, 16, 16, 16, 16);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("marvel:textures/veronica_mark30.png"));
		this.blit(ms, this.guiLeft + 14, this.guiTop + 87, 0, 0, 16, 16, 16, 16);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("marvel:textures/veronica_mark42.png"));
		this.blit(ms, this.guiLeft + 14, this.guiTop + 114, 0, 0, 16, 16, 16, 16);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("marvel:textures/veronica_mark46.png"));
		this.blit(ms, this.guiLeft + 14, this.guiTop + 141, 0, 0, 16, 16, 16, 16);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("marvel:textures/veronica_iron_patriot.png"));
		this.blit(ms, this.guiLeft + 14, this.guiTop + 168, 0, 0, 16, 16, 16, 16);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("marvel:textures/veronica_mark22.png"));
		this.blit(ms, this.guiLeft + 158, this.guiTop + 33, 0, 0, 16, 16, 16, 16);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("marvel:textures/veronica_mark25.png"));
		this.blit(ms, this.guiLeft + 158, this.guiTop + 60, 0, 0, 16, 16, 16, 16);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("marvel:textures/veronica_mark33.png"));
		this.blit(ms, this.guiLeft + 158, this.guiTop + 87, 0, 0, 16, 16, 16, 16);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("marvel:textures/veronica_mark43.png"));
		this.blit(ms, this.guiLeft + 158, this.guiTop + 114, 0, 0, 16, 16, 16, 16);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("marvel:textures/veronica_mark47.png"));
		this.blit(ms, this.guiLeft + 158, this.guiTop + 141, 0, 0, 16, 16, 16, 16);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("marvel:textures/veronica_war_machine_v2.png"));
		this.blit(ms, this.guiLeft + 158, this.guiTop + 168, 0, 0, 16, 16, 16, 16);
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeScreen();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
		this.font.drawString(ms, "Veronica", 124, 7, -12829636);
		if (Mark21AvailableProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Available", 32, 42, -16738048);
		if (Mark21UnavailableProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Unavailable", 32, 42, -3407872);
		this.font.drawString(ms, "Mark 21", 32, 33, -12829636);
		if (Mark23AvailableProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Available", 32, 69, -16738048);
		if (Mark23UnavailableProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Unavailable", 32, 69, -3407872);
		this.font.drawString(ms, "Mark 23", 32, 60, -12829636);
		if (Mark30AvailableProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Available", 32, 96, -16738048);
		if (Mark30UnavailableProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Unavailable", 32, 96, -3407872);
		this.font.drawString(ms, "Mark 30", 32, 87, -12829636);
		if (Mark42AvailableProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Available", 32, 123, -16738048);
		if (Mark42UnavailableProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Unavailable", 32, 123, -3407872);
		this.font.drawString(ms, "Mark 42", 32, 114, -12829636);
		if (Mark46AvailableProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Available", 32, 150, -16738048);
		if (Mark46UnavailableProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Unavailable", 32, 150, -3407872);
		this.font.drawString(ms, "Mark 46", 32, 141, -12829636);
		if (IronPatriotAvailableProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Available", 32, 177, -16738048);
		if (IronPatriotUnavailableProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Unavailable", 32, 177, -3407872);
		this.font.drawString(ms, "Iron Patriot", 32, 168, -12829636);
		if (Mark22AvailableProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Available", 176, 42, -16738048);
		if (Mark22UnavailableProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Unavailable", 176, 42, -3407872);
		this.font.drawString(ms, "Mark 22", 176, 33, -12829636);
		if (Mark25AvailableProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Available", 176, 69, -16738048);
		if (Mark25UnavailableProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Unavailable", 176, 69, -3407872);
		this.font.drawString(ms, "Mark 25", 176, 60, -12829636);
		if (Mark33AvailableProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Available", 176, 96, -16738048);
		if (Mark33UnavailableProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Unavailable", 176, 96, -3407872);
		this.font.drawString(ms, "Mark 33", 176, 87, -12829636);
		if (Mark43AvailableProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Available", 176, 123, -16738048);
		if (Mark43UnavailableProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Unavailable", 176, 123, -3407872);
		this.font.drawString(ms, "Mark 43", 176, 114, -12829636);
		if (Mark47AvailableProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Available", 176, 150, -16738048);
		if (Mark47UnavailableProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Unavailable", 176, 150, -3407872);
		this.font.drawString(ms, "Mark 47", 176, 141, -12829636);
		if (WarMachineAvailableProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Available", 176, 177, -16738048);
		if (WarMachineUnavailableProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Unavailable", 176, 177, -3407872);
		this.font.drawString(ms, "War Machine", 176, 168, -12829636);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardListener.enableRepeatEvents(false);
	}

	@Override
	public void init(Minecraft minecraft, int width, int height) {
		super.init(minecraft, width, height);
		minecraft.keyboardListener.enableRepeatEvents(true);
		this.addButton(new Button(this.guiLeft + 95, this.guiTop + 33, 45, 20, new StringTextComponent("Summon"), e -> {
			MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiGui.ButtonPressedMessage(0, x, y, z));
			VeronicaGuiGui.handleButtonAction(entity, 0, x, y, z);
		}));
		this.addButton(new Button(this.guiLeft + 95, this.guiTop + 60, 45, 20, new StringTextComponent("Summon"), e -> {
			MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiGui.ButtonPressedMessage(1, x, y, z));
			VeronicaGuiGui.handleButtonAction(entity, 1, x, y, z);
		}));
		this.addButton(new Button(this.guiLeft + 95, this.guiTop + 87, 45, 20, new StringTextComponent("Summon"), e -> {
			MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiGui.ButtonPressedMessage(2, x, y, z));
			VeronicaGuiGui.handleButtonAction(entity, 2, x, y, z);
		}));
		this.addButton(new Button(this.guiLeft + 95, this.guiTop + 114, 45, 20, new StringTextComponent("Summon"), e -> {
			MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiGui.ButtonPressedMessage(3, x, y, z));
			VeronicaGuiGui.handleButtonAction(entity, 3, x, y, z);
		}));
		this.addButton(new Button(this.guiLeft + 95, this.guiTop + 141, 45, 20, new StringTextComponent("Summon"), e -> {
			MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiGui.ButtonPressedMessage(4, x, y, z));
			VeronicaGuiGui.handleButtonAction(entity, 4, x, y, z);
		}));
		this.addButton(new Button(this.guiLeft + 95, this.guiTop + 168, 45, 20, new StringTextComponent("Summon"), e -> {
			MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiGui.ButtonPressedMessage(5, x, y, z));
			VeronicaGuiGui.handleButtonAction(entity, 5, x, y, z);
		}));
		this.addButton(new Button(this.guiLeft + 239, this.guiTop + 33, 45, 20, new StringTextComponent("Summon"), e -> {
			MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiGui.ButtonPressedMessage(6, x, y, z));
			VeronicaGuiGui.handleButtonAction(entity, 6, x, y, z);
		}));
		this.addButton(new Button(this.guiLeft + 239, this.guiTop + 60, 45, 20, new StringTextComponent("Summon"), e -> {
			MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiGui.ButtonPressedMessage(7, x, y, z));
			VeronicaGuiGui.handleButtonAction(entity, 7, x, y, z);
		}));
		this.addButton(new Button(this.guiLeft + 239, this.guiTop + 87, 45, 20, new StringTextComponent("Summon"), e -> {
			MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiGui.ButtonPressedMessage(8, x, y, z));
			VeronicaGuiGui.handleButtonAction(entity, 8, x, y, z);
		}));
		this.addButton(new Button(this.guiLeft + 239, this.guiTop + 114, 45, 20, new StringTextComponent("Summon"), e -> {
			MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiGui.ButtonPressedMessage(9, x, y, z));
			VeronicaGuiGui.handleButtonAction(entity, 9, x, y, z);
		}));
		this.addButton(new Button(this.guiLeft + 239, this.guiTop + 141, 45, 20, new StringTextComponent("Summon"), e -> {
			MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiGui.ButtonPressedMessage(10, x, y, z));
			VeronicaGuiGui.handleButtonAction(entity, 10, x, y, z);
		}));
		this.addButton(new Button(this.guiLeft + 239, this.guiTop + 168, 45, 20, new StringTextComponent("Summon"), e -> {
			MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiGui.ButtonPressedMessage(11, x, y, z));
			VeronicaGuiGui.handleButtonAction(entity, 11, x, y, z);
		}));
		this.addButton(new Button(this.guiLeft + 3, this.guiTop + 4, 110, 20, new StringTextComponent("House Party Protocol"), e -> {
			MarvelMod.PACKET_HANDLER.sendToServer(new VeronicaGuiGui.ButtonPressedMessage(12, x, y, z));
			VeronicaGuiGui.handleButtonAction(entity, 12, x, y, z);
		}));
	}
}
