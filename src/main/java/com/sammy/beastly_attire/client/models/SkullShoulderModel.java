package com.sammy.beastly_attire.client.models;// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class SkullShoulderModel<T extends LivingEntity> extends BipedModel<T>
{
	public final ModelRenderer skulls;
	public final ModelRenderer rightSkull;
	public final ModelRenderer leftSkull;
	
	public SkullShoulderModel() {
		super(1);
		textureWidth = 32;
		textureHeight = 32;
		
		skulls = new ModelRenderer(this);
		skulls.setRotationPoint(0.0F, 0.0F, 0.0F);
		
		
		rightSkull = new ModelRenderer(this);
		rightSkull.setRotationPoint(-8.0F, 1.0F, 0.0F);
		bipedRightArm.addChild(rightSkull);
		skulls.addChild(rightSkull);
		setRotationAngle(rightSkull, 0.0F, 0.0F, 0.2618F);
		rightSkull.setTextureOffset(0, 12).addBox(-2.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
		
		leftSkull = new ModelRenderer(this);
		leftSkull.setRotationPoint(8.0F, 1.0F, 0.0F);
		bipedLeftArm.addChild(leftSkull);
		skulls.addChild(leftSkull);
		setRotationAngle(leftSkull, 0.0F, 0.0F, -0.2618F);
		leftSkull.setTextureOffset(0, 0).addBox(-4.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
	}
	
	@Override
	public void setRotationAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		//previously the render function, render code was moved to a method below
	}
	
	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha)
	{
		bipedRightArm = rightSkull;
		bipedLeftArm = leftSkull;
		skulls.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}