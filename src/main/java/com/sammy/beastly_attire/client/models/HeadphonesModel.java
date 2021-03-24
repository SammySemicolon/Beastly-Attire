package com.sammy.beastly_attire.client.models;// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class HeadphonesModel<T extends LivingEntity> extends BipedModel<T>
{
	public final ModelRenderer headphones;
	
	public HeadphonesModel()
	{
		super(1);
		textureWidth = 32;
		textureHeight = 32;
		
		headphones = new ModelRenderer(this);
		headphones.setRotationPoint(0.0F, 0.0F, 0.0F);
		headphones.setTextureOffset(0, 0).addBox(-4.5F, -8.5F, -1.0F, 9.0F, 3.0F, 2.0F, 0.0F, false);
		headphones.setTextureOffset(0, 13).addBox(3.5F, -5.5F, -2.0F, 2.0F, 4.0F, 4.0F, 0.0F, false);
		headphones.setTextureOffset(0, 5).addBox(-5.5F, -5.5F, -2.0F, 2.0F, 4.0F, 4.0F, 0.0F, false);
	}
	
	@Override
	public void setRotationAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		//previously the render function, render code was moved to a method below
	}
	
	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha)
	{
		headphones.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}
}