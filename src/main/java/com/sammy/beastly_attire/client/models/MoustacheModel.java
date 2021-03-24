package com.sammy.beastly_attire.client.models;// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class MoustacheModel<T extends LivingEntity> extends BipedModel<T>
{
	public final ModelRenderer moustache;
	
	public MoustacheModel()
	{
		super(1);
		textureWidth = 16;
		textureHeight = 16;
		
		
		moustache = new ModelRenderer(this);
		moustache.setRotationPoint(0.0F, 1.0F, 0.0F);
		moustache.setTextureOffset(0, 10).addBox(-1.0F, -3.75F, -5.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		
		ModelRenderer left = new ModelRenderer(this);
		left.setRotationPoint(0.0F, -1.0F, -4.0F);
		moustache.addChild(left);
		setRotationAngle(left, 0.0F, 0.0F, 0.3491F);
		left.setTextureOffset(8, 9).addBox(3.0F, -4.0F, -1.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
		left.setTextureOffset(0, 0).addBox(0.0F, -3.0F, -1.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);
		
		ModelRenderer right = new ModelRenderer(this);
		right.setRotationPoint(0.0F, -1.0F, -4.0F);
		moustache.addChild(right);
		setRotationAngle(right, 0.0F, 0.0F, -0.3491F);
		right.setTextureOffset(0, 5).addBox(-3.0F, -3.0F, -1.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);
		right.setTextureOffset(9, 0).addBox(-5.0F, -4.0F, -1.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
	}
	
	@Override
	public void setRotationAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		//previously the render function, render code was moved to a method below
	}
	
	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha)
	{
		moustache.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}