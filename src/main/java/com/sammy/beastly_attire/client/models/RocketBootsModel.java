package com.sammy.beastly_attire.client.models;// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;

public class RocketBootsModel extends ModelArmor
{
	private final ModelRenderer leftBoot;
	private final ModelRenderer rightBoot;
	
	
	public RocketBootsModel(EquipmentSlotType slot)
	{
		super(slot, 32, 32);
		textureWidth = 32;
		textureHeight = 32;
		
		leftBoot = new ModelRenderer(this);
		leftBoot.setRotationPoint(2.0F, 12.0F, 0.0F);
		leftBoot.setTextureOffset(0, 0).addBox(-2.0F, 5.3F, -3.0F, 5.0F, 7.0F, 6.0F, 0.0F, false);
		leftBoot.setTextureOffset(16, 0).addBox(-2.0F, 10.3F, -5.0F, 5.0F, 2.0F, 2.0F, 0.0F, false);
		leftBoot.setTextureOffset(22, 22).addBox(3.0F, 7.3F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		
		rightBoot = new ModelRenderer(this);
		rightBoot.setRotationPoint(-2.0F, 12.0F, 0.0F);
		rightBoot.setTextureOffset(0, 13).addBox(-3.0F, 5.3F, -3.0F, 5.0F, 7.0F, 6.0F, 0.0F, false);
		rightBoot.setTextureOffset(16, 13).addBox(-3.0F, 10.3F, -5.0F, 5.0F, 2.0F, 2.0F, 0.0F, false);
		rightBoot.setTextureOffset(22, 4).addBox(-5.0F, 7.3F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
	}
	
	@Override
	public void render(MatrixStack ms, IVertexBuilder buffer, int light, int overlay, float r, float g, float b, float a)
	{
		leftBoot.showModel = slot == EquipmentSlotType.FEET;
		rightBoot.showModel = slot == EquipmentSlotType.FEET;
		
		bipedLeftLeg = leftBoot;
		bipedRightLeg = rightBoot;
		
		super.render(ms, buffer, light, overlay, r, g, b, a);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}