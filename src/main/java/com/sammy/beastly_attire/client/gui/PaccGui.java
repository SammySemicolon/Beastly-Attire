/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
package com.sammy.beastly_attire.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.sammy.beastly_attire.BeastlyAttireHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class PaccGui extends ContainerScreen<PaccContainer> {

	private static final ResourceLocation texture = BeastlyAttireHelper.prefix("textures/gui/pacc.png");
	private int mouseX;
	private int mouseY;

	public PaccGui(PaccContainer container, PlayerInventory player, ITextComponent title) {
		super(container, player, title);
	}

	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.renderHoveredTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getInstance().getTextureManager().bindTexture(texture);
		blit(ms, guiLeft, guiTop, 0, 0, xSize, ySize);
		InventoryScreen.drawEntityOnScreen(guiLeft + 31, guiTop + 75, 30, guiLeft + 31 - this.mouseX, guiTop + 75 - 50 - this.mouseY, minecraft.player);
	}

}
