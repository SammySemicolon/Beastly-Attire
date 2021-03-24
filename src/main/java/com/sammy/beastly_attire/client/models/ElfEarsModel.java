package com.sammy.beastly_attire.client.models;// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class ElfEarsModel<T extends LivingEntity> extends BipedModel<T>
{
	public final ModelRenderer bothEars;
	
	public ElfEarsModel() {
		super(1);
		textureWidth = 16;
		textureHeight = 16;

		bothEars = new ModelRenderer(this);
		bothEars.setRotationPoint(0.0F, 0.0F, 0.0F);
		
		ModelRenderer Headgear1 = new ModelRenderer(this);
		Headgear1.setRotationPoint(0.1F, 0.0F, 0.5F);
		setRotationAngle(Headgear1, 0.0F, 0.2618F, 0.0F);
		Headgear1.setTextureOffset(0, 2).addBox(4.1553F, -5.0F, -0.4395F, 0.0F, 2.0F, 3.0F, 0.01F, false);
		
		ModelRenderer Headgear2 = new ModelRenderer(this);
		Headgear2.setRotationPoint(0.1F, 0.0F, 0.5F);
		setRotationAngle(Headgear2, 0.0F, -0.2618F, 0.0F);
		Headgear2.setTextureOffset(0, 0).addBox(-4.3475F, -5.0F, -0.3877F, 0.0F, 2.0F, 3.0F, 0.01F, false);
		
		bothEars.addChild(Headgear1);
		bothEars.addChild(Headgear2);
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