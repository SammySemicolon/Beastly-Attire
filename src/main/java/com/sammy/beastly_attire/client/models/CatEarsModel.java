package com.sammy.beastly_attire.client.models;// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class CatEarsModel<T extends LivingEntity> extends BipedModel<T>
{
	public final ModelRenderer bothEars;
	
	public CatEarsModel() {
		super(1);
		textureWidth = 32;
		textureHeight = 32;

		bothEars = new ModelRenderer(this);
		bothEars.setRotationPoint(0.0F, 0.0F, 0.0F);
		
		ModelRenderer leftEar = new ModelRenderer(this);
		leftEar.setRotationPoint(0.0F, 0.0F, 0.0F);
		bothEars.addChild(leftEar);
		setRotationAngle(leftEar, -0.1309F, 0.0F, -0.0873F);
		leftEar.setTextureOffset(0, 0).addBox(4.3439F, -11.0465F, -2.8619F, 1.0F, 5.0F, 4.0F, -0.1F, true);
		leftEar.setTextureOffset(0, 9).addBox(4.3439F, -11.0465F, -2.8619F, 1.0F, 5.0F, 4.0F, 0.05F, true);
		
		ModelRenderer rightEar = new ModelRenderer(this);
		rightEar.setRotationPoint(0.0F, 0.0F, 0.0F);
		bothEars.addChild(rightEar);
		setRotationAngle(rightEar, -0.1309F, 0.0F, 0.0873F);
		rightEar.setTextureOffset(0, 0).addBox(-5.3439F, -11.0465F, -2.8619F, 1.0F, 5.0F, 4.0F, -0.1F, false);
		rightEar.setTextureOffset(0, 9).addBox(-5.3439F, -11.0465F, -2.8619F, 1.0F, 5.0F, 4.0F, 0.05F, false);
	}
	
	@Override
	public void setRotationAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		//previously the render function, render code was moved to a method below
	}
	
	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha)
	{
		bothEars.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}