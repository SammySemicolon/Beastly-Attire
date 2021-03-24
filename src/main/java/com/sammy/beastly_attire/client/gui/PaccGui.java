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
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.PacketDistributor;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.client.KeyRegistry;
import top.theillusivec4.curios.client.gui.RenderButton;
import top.theillusivec4.curios.common.inventory.CosmeticCurioSlot;
import top.theillusivec4.curios.common.inventory.CurioSlot;
import top.theillusivec4.curios.common.network.NetworkHandler;
import top.theillusivec4.curios.common.network.client.CPacketToggleRender;

import javax.annotation.Nonnull;

public class PaccGui extends ContainerScreen<PaccContainer>
{
	private static final ResourceLocation CREATIVE_INVENTORY_TABS = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");
	static final ResourceLocation CURIO_INVENTORY = new ResourceLocation("curios", "textures/gui/inventory.png");
	private static final ResourceLocation PACC = BeastlyAttireHelper.prefix("textures/gui/pacc.png");
	
	private static float currentScroll;
	public boolean hasScrollBar;
	public boolean widthTooNarrow;
	private boolean isScrolling;
	private boolean buttonClicked;
	private boolean isRenderButtonHovered;
	
	public PaccGui(PaccContainer container, PlayerInventory player, ITextComponent title)
	{
		super(container, player, title);
		this.passEvents = true;
	}
	
	@Override
	public void init()
	{
		super.init();
		if (this.minecraft != null)
		{
			if (this.minecraft.player != null)
			{
				this.hasScrollBar = CuriosApi.getCuriosHelper().getCuriosHandler(this.minecraft.player).map((handler) -> handler.getVisibleSlots() > 8).orElse(false);
				if (this.hasScrollBar)
				{
					this.container.scrollTo(currentScroll);
				}
			}
			
			int neededWidth = 431;
			if (this.hasScrollBar)
			{
				neededWidth += 30;
			}
			
			if (this.container.hasCosmeticColumn())
			{
				neededWidth += 40;
			}
			
			this.widthTooNarrow = this.width < neededWidth;
			this.updateScreenPosition();
			
			this.updateRenderButtons();
		}
	}
	
	public void updateRenderButtons()
	{
		this.buttons.removeIf((widget) -> widget instanceof RenderButton);
		this.children.removeIf((widget) -> widget instanceof RenderButton);
		
		for (Slot inventorySlot : this.container.inventorySlots)
		{
			if (inventorySlot instanceof CurioSlot && !(inventorySlot instanceof CosmeticCurioSlot))
			{
				this.addButton(new RenderButton((CurioSlot) inventorySlot, this.guiLeft + inventorySlot.xPos + 11, this.guiTop + inventorySlot.yPos - 3, 8, 8, 75, 0, 8, CURIO_INVENTORY, (button) -> {
					NetworkHandler.INSTANCE.send(PacketDistributor.SERVER.noArg(), new CPacketToggleRender(((CurioSlot) inventorySlot).getIdentifier(), inventorySlot.getSlotIndex()));
				}));
			}
		}
	}
	
	private void updateScreenPosition()
	{
		this.guiLeft = (this.width - this.xSize) / 2;
		this.updateRenderButtons();
	}
	
	private boolean inScrollBar(double mouseX, double mouseY)
	{
		int i = this.guiLeft;
		int j = this.guiTop;
		int k = i - 34;
		int l = j + 12;
		int i1 = k + 14;
		int j1 = l + 139;
		if (this.container.hasCosmeticColumn())
		{
			i1 -= 19;
			k -= 19;
		}
		
		return mouseX >= (double) k && mouseY >= (double) l && mouseX < (double) i1 && mouseY < (double) j1;
	}
	
	@Override
	public void render(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
	{
		this.renderBackground(matrixStack);
		
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		boolean isButtonHovered = false;
		
		for (Widget button : this.buttons)
		{
			if (button instanceof RenderButton)
			{
				((RenderButton) button).renderButtonOverlay(matrixStack, mouseX, mouseY, partialTicks);
				if (button.isHovered())
				{
					isButtonHovered = true;
				}
			}
		}
		
		this.isRenderButtonHovered = isButtonHovered;
		ClientPlayerEntity clientPlayer = Minecraft.getInstance().player;
		if (!this.isRenderButtonHovered && clientPlayer != null && clientPlayer.inventory.getItemStack().isEmpty() && this.getSlotUnderMouse() != null)
		{
			Slot slot = this.getSlotUnderMouse();
			if (slot instanceof CurioSlot && !slot.getHasStack())
			{
				CurioSlot slotCurio = (CurioSlot) slot;
				this.renderTooltip(matrixStack, new StringTextComponent(slotCurio.getSlotName()), mouseX, mouseY);
			}
		}
		this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
	}
	
	@Override
	protected void renderHoveredTooltip(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY)
	{
		Minecraft mc = this.minecraft;
		if (mc != null)
		{
			ClientPlayerEntity clientPlayer = mc.player;
			if (clientPlayer != null && clientPlayer.inventory.getItemStack().isEmpty())
			{
				if (this.isRenderButtonHovered)
				{
					this.renderTooltip(matrixStack, new TranslationTextComponent("gui.curios.toggle"), mouseX, mouseY);
				}
				else if (this.hoveredSlot != null && this.hoveredSlot.getHasStack())
				{
					this.renderTooltip(matrixStack, this.hoveredSlot.getStack(), mouseX, mouseY);
				}
			}
		}
		
	}
	
	@Override
	public boolean keyPressed(int p_keyPressed_1_, int p_keyPressed_2_, int p_keyPressed_3_)
	{
		if (KeyRegistry.openCurios.isActiveAndMatches(InputMappings.getInputByCode(p_keyPressed_1_, p_keyPressed_2_)))
		{
			ClientPlayerEntity playerEntity = this.getMinecraft().player;
			if (playerEntity != null)
			{
				playerEntity.closeScreen();
			}
			
			return true;
		}
		else
		{
			return super.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_);
		}
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY)
	{
		if (this.minecraft != null && this.minecraft.player != null)
		{
			this.font.func_243248_b(matrixStack, this.title, 97.0F, 6.0F, 4210752);
		}
		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(@Nonnull MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY)
	{
		if (this.minecraft != null && this.minecraft.player != null)
		{
			RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.minecraft.getTextureManager().bindTexture(PACC);
			int i = this.guiLeft;
			int j = this.guiTop;
			blit(matrixStack, guiLeft, guiTop, 0, 0, xSize, ySize);
			InventoryScreen.drawEntityOnScreen(guiLeft + 31, guiTop + 75, 30, guiLeft + 31 - mouseX, guiTop + 75 - 50 - mouseY, minecraft.player);
			
			CuriosApi.getCuriosHelper().getCuriosHandler(this.minecraft.player).ifPresent((handler) -> {
				int slotCount = handler.getVisibleSlots();
				if (slotCount > 0)
				{
					int upperHeight = 7 + Math.min(slotCount, 9) * 18;
					RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
					this.getMinecraft().getTextureManager().bindTexture(CURIO_INVENTORY);
					int xTexOffset = 0;
					int width = 27;
					int xOffset = -26;
					if (this.container.hasCosmeticColumn())
					{
						xTexOffset = 92;
						width = 46;
						xOffset -= 19;
					}
					
					this.blit(matrixStack, i + xOffset, j + 4, xTexOffset, 0, width, upperHeight);
					if (slotCount <= 8)
					{
						this.blit(matrixStack, i + xOffset, j + 4 + upperHeight, xTexOffset, 151, width, 7);
					}
					else
					{
						this.blit(matrixStack, i + xOffset - 16, j + 4, 27, 0, 23, 158);
						this.getMinecraft().getTextureManager().bindTexture(CREATIVE_INVENTORY_TABS);
						this.blit(matrixStack, i + xOffset - 8, j + 12 + (int) (127.0F * currentScroll), 232, 0, 12, 15);
					}
					
					for (Slot slot : this.container.inventorySlots)
					{
						if (slot instanceof CosmeticCurioSlot)
						{
							int x = this.guiLeft + slot.xPos - 1;
							int y = this.guiTop + slot.yPos - 1;
							this.getMinecraft().getTextureManager().bindTexture(CURIO_INVENTORY);
							this.blit(matrixStack, x, y, 138, 0, 18, 18);
						}
					}
				}
				
			});
		}
	}
	
	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton)
	{
		if (this.inScrollBar(mouseX, mouseY))
		{
			this.isScrolling = this.needsScrollBars();
			return true;
		}
		return super.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	@Override
	public boolean mouseReleased(double mouseReleased1, double mouseReleased3, int mouseReleased5)
	{
		if (mouseReleased5 == 0)
		{
			this.isScrolling = false;
		}
		
		if (this.buttonClicked)
		{
			this.buttonClicked = false;
			return true;
		}
		else
		{
			return super.mouseReleased(mouseReleased1, mouseReleased3, mouseReleased5);
		}
	}
	
	@Override
	public boolean mouseDragged(double pMouseDragged1, double pMouseDragged3, int pMouseDragged5, double pMouseDragged6, double pMouseDragged8)
	{
		if (this.isScrolling)
		{
			int i = this.guiTop + 8;
			int j = i + 148;
			currentScroll = ((float) pMouseDragged3 - (float) i - 7.5F) / ((float) (j - i) - 15.0F);
			currentScroll = MathHelper.clamp(currentScroll, 0.0F, 1.0F);
			this.container.scrollTo(currentScroll);
			return true;
		}
		else
		{
			return super.mouseDragged(pMouseDragged1, pMouseDragged3, pMouseDragged5, pMouseDragged6, pMouseDragged8);
		}
	}
	
	@Override
	public boolean mouseScrolled(double pMouseScrolled1, double pMouseScrolled3, double pMouseScrolled5)
	{
		if (!this.needsScrollBars())
		{
			return false;
		}
		else
		{
			int i = this.container.curiosHandler.map(ICuriosItemHandler::getVisibleSlots).orElse(1);
			currentScroll = (float) ((double) currentScroll - pMouseScrolled5 / (double) i);
			currentScroll = MathHelper.clamp(currentScroll, 0.0F, 1.0F);
			this.container.scrollTo(currentScroll);
			return true;
		}
	}
	
	private boolean needsScrollBars()
	{
		return this.container.canScroll();
	}
}