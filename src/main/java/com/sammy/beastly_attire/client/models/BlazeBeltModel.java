package com.sammy.beastly_attire.client.models;// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class BlazeBeltModel<T extends LivingEntity> extends BipedModel<T> {
	private final ModelRenderer root;

	public BlazeBeltModel() {
		super(1);
		textureWidth = 32;
		textureHeight = 16;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 0.0F, 0.0F);
		root.setTextureOffset(0, 0).addBox(-4.5F, 9.0F, -2.5F, 9.0F, 2.0F, 5.0F, 0.0F, false);
		root.setTextureOffset(0, 7).addBox(-2.5F, 8.5F, -3.0F, 5.0F, 3.0F, 2.0F, 0.0F, false);
		root.setTextureOffset(16, 7).addBox(1.0F, 8.5F, 1.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		root.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}
}