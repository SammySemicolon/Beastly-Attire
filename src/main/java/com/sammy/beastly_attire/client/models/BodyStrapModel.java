package com.sammy.beastly_attire.client.models;// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class BodyStrapModel<T extends LivingEntity> extends BipedModel<T>
{
	public final ModelRenderer bodyStrap;
	
	public BodyStrapModel()
	{
		super(1);
		textureWidth = 32;
		textureHeight = 32;
		
		
		bodyStrap = new ModelRenderer(this);
		bodyStrap.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bodyStrap, 0.0F, 0.0F, -0.1309F);
		bodyStrap.setTextureOffset(10, 7).addBox(0.0086F, 1.8695F, -4.0F, 3.0F, 5.0F, 1.0F, 0.0F, false);
		bodyStrap.setTextureOffset(10, 0).addBox(-3.9914F, 6.8695F, -4.0F, 3.0F, 5.0F, 1.0F, 0.0F, false);
		
		ModelRenderer front = new ModelRenderer(this);
		front.setRotationPoint(0.0F, 6.0F, -3.0F);
		bodyStrap.addChild(front);
		setRotationAngle(front, 0.0F, 0.0F, 0.7854F);
		front.setTextureOffset(0, 14).addBox(-1.0F, -6.0F, 0.0F, 2.0F, 13.0F, 1.0F, 0.0F, false);
		
		ModelRenderer back = new ModelRenderer(this);
		back.setRotationPoint(0.0F, 6.0F, 2.0F);
		bodyStrap.addChild(back);
		setRotationAngle(back, 0.0F, 0.0F, 0.7854F);
		back.setTextureOffset(6, 14).addBox(-1.0F, -6.0F, 0.0F, 2.0F, 13.0F, 1.0F, 0.0F, false);
		
		ModelRenderer right = new ModelRenderer(this);
		right.setRotationPoint(0.0F, 6.0F, -3.0F);
		bodyStrap.addChild(right);
		setRotationAngle(right, 0.0F, 0.0F, 0.7854F);
		right.setTextureOffset(0, 7).addBox(-1.0F, -7.0F, 0.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);
		
		ModelRenderer left = new ModelRenderer(this);
		left.setRotationPoint(-10.0F, 16.0F, -3.0F);
		bodyStrap.addChild(left);
		setRotationAngle(left, 0.0F, 0.0F, 0.7854F);
		left.setTextureOffset(0, 0).addBox(-1.0F, -7.2F, 0.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	@Override
	public void setRotationAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		//previously the render function, render code was moved to a method below
	}
	
	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha)
	{
		bodyStrap.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}
}