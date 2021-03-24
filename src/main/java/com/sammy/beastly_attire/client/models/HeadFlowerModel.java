package com.sammy.beastly_attire.client.models;// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class HeadFlowerModel<T extends LivingEntity> extends BipedModel<T>
{
	public final ModelRenderer headFlower;

	public HeadFlowerModel() {
		super(1);
		textureWidth = 16;
		textureHeight = 16;

		headFlower = new ModelRenderer(this);
		headFlower.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(headFlower, 0.0F, 0.7854F, 0.0F);
		headFlower.setTextureOffset(0, 0).addBox(0.001F, -13.0F, -1.5858F, 0.0F, 5.0F, 3.0F, 0.0F, false);
		headFlower.setTextureOffset(0, 0).addBox(-1.4132F, -13.0F, 1.4142F, 3.0F, 3.0F, 0.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha)
	{
		headFlower.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}