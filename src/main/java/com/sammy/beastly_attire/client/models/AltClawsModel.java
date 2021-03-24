package com.sammy.beastly_attire.client.models;// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class AltClawsModel<T extends LivingEntity> extends BipedModel<T>
{
	public final ModelRenderer claws;

	public AltClawsModel() {
		super(1);
		textureWidth = 32;
		textureHeight = 32;
		claws = new ModelRenderer(this);
		bipedLeftArm.addChild(claws);
		
		claws.setRotationPoint(6.0F, 2.0F, 0.0F);
		claws.setTextureOffset(12, 0).addBox(-1.0F, 8.0F, -3.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		claws.setTextureOffset(6, 7).addBox(2.0F, 8.0F, -3.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		claws.setTextureOffset(0, 2).addBox(0.0F, 8.0F, 2.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		claws.setTextureOffset(0, 0).addBox(0.0F, 8.0F, -3.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		claws.setTextureOffset(0, 20).addBox(2.5F, 7.0F, 1.5F, 1.0F, 7.0F, 1.0F, 0.0F, false);
		claws.setTextureOffset(0, 20).addBox(2.5F, 7.0F, -0.5F, 1.0F, 7.0F, 1.0F, 0.0F, false);
		claws.setTextureOffset(0, 20).addBox(2.5F, 7.0F, -2.5F, 1.0F, 7.0F, 1.0F, 0.0F, false);
	}
	
	@Override
	public void setRotationAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		//previously the render function, render code was moved to a method below
	}
	
	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha)
	{
		bipedLeftArm = claws;
		claws.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}
}