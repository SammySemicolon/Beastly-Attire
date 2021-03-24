package com.sammy.beastly_attire.client.models;// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;

public class ModelWizardArmor extends ModelArmor
{
	private final ModelRenderer armorRightArm;
	private final ModelRenderer armorLeftArm;
	private final ModelRenderer armorBody;
	private final ModelRenderer armorHead;
	
	
	public ModelWizardArmor(EquipmentSlotType slot)
	{
		super(slot, 64, 64);
		textureWidth = 64;
		textureHeight = 64;
		
		armorRightArm = new ModelRenderer(this);
		armorRightArm.setRotationPoint(-6.0F, -22.0F, 0.0F);
		armorRightArm.setTextureOffset(0, 47).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.65F, false);
		
		armorLeftArm = new ModelRenderer(this);
		armorLeftArm.setRotationPoint(6.0F, -22.0F, 0.0F);
		armorLeftArm.setTextureOffset(0, 47).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.65F, false);
		
		armorBody = new ModelRenderer(this);
		armorBody.setRotationPoint(0.0F, -24.0F, 0.0F);
		armorBody.setTextureOffset(16, 47).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 13.0F, 4.0F, 0.45F, false);
		
		armorHead = new ModelRenderer(this);
		armorHead.setRotationPoint(0.0F, -24.0F, 0.0F);
		armorHead.setTextureOffset(0, 0).addBox(-7.0F, -9.0F, -7.0F, 14.0F, 1.0F, 14.0F, 0.1F, false);
		
		ModelRenderer dontTouch_r1 = new ModelRenderer(this);
		dontTouch_r1.setRotationPoint(0.0F, -8.5604F, 0.0548F);
		armorHead.addChild(dontTouch_r1);
		setRotationAngle(dontTouch_r1, -0.7418F, 0.0F, 0.0F);
		dontTouch_r1.setTextureOffset(0, 35).addBox(-4.0F, -12.0F, -3.0F, 8.0F, 4.0F, 6.0F, -0.02F, false);
		
		ModelRenderer dontTouch_r2 = new ModelRenderer(this);
		dontTouch_r2.setRotationPoint(0.0F, -8.3054F, -0.0608F);
		armorHead.addChild(dontTouch_r2);
		setRotationAngle(dontTouch_r2, -0.1745F, 0.0F, 0.0F);
		dontTouch_r2.setTextureOffset(0, 15).addBox(-4.0F, -12.0F, -4.0F, 8.0F, 12.0F, 8.0F, -0.01F, false);
	}
	
	@Override
	public void render(MatrixStack ms, IVertexBuilder buffer, int light, int overlay, float r, float g, float b, float a)
	{
		armorHead.showModel = slot == EquipmentSlotType.HEAD;
		
		armorBody.showModel = slot == EquipmentSlotType.CHEST;
		armorRightArm.showModel = slot == EquipmentSlotType.CHEST;
		armorLeftArm.showModel = slot == EquipmentSlotType.CHEST;
		
		bipedHeadwear.showModel = false;
		
		bipedHead = armorHead;
		
		bipedBody = armorBody;
		bipedRightArm = armorRightArm;
		bipedLeftArm = armorLeftArm;
		
		super.render(ms, buffer, light, overlay, r, g, b, a);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}