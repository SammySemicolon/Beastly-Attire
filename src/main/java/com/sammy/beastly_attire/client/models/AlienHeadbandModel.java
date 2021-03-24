package com.sammy.beastly_attire.client.models;// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class AlienHeadbandModel<T extends LivingEntity> extends BipedModel<T>
{
    public final ModelRenderer alienBase;
    
    public AlienHeadbandModel()
    {
        super(1);
        textureWidth = 32;
        textureHeight = 32;
    
        alienBase = new ModelRenderer(this);
        alienBase.setRotationPoint(0.0F, 0.0F, 0.0F);
    
    
        ModelRenderer leftAntenna = new ModelRenderer(this);
        leftAntenna.setRotationPoint(2.0F, -8.0F, -1.0F);
        alienBase.addChild(leftAntenna);
        setRotationAngle(leftAntenna, 0.3927F, -0.2182F, 0.0F);
        leftAntenna.setTextureOffset(16, 0).addBox(-1.0F, -5.5F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
    
        ModelRenderer leftAntenna2 = new ModelRenderer(this);
        leftAntenna2.setRotationPoint(0.0F, -5.5F, 1.0F);
        leftAntenna.addChild(leftAntenna2);
        setRotationAngle(leftAntenna2, 0.3927F, 0.0F, 0.0F);
        leftAntenna2.setTextureOffset(16, 8).addBox(-0.975F, -6.0F, -2.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
    
        ModelRenderer leftAntennaBobble = new ModelRenderer(this);
        leftAntennaBobble.setRotationPoint(0.0F, -6.0F, 0.0F);
        leftAntenna2.addChild(leftAntennaBobble);
        setRotationAngle(leftAntennaBobble, 0.3927F, 0.0F, 0.0F);
        leftAntennaBobble.setTextureOffset(16, 16).addBox(-1.475F, -3.0F, -2.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
    
        ModelRenderer rightAntenna = new ModelRenderer(this);
        rightAntenna.setRotationPoint(-2.0F, -8.0F, -1.0F);
        alienBase.addChild(rightAntenna);
        setRotationAngle(rightAntenna, 0.3927F, 0.2182F, 0.0F);
        rightAntenna.setTextureOffset(0, 0).addBox(-1.0F, -5.5F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
    
        ModelRenderer rightAntenna2 = new ModelRenderer(this);
        rightAntenna2.setRotationPoint(0.0F, -5.5F, 1.0F);
        rightAntenna.addChild(rightAntenna2);
        setRotationAngle(rightAntenna2, 0.3927F, 0.0F, 0.0F);
        rightAntenna2.setTextureOffset(0, 8).addBox(-1.025F, -6.0F, -2.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
    
        ModelRenderer rightAntennaBobble = new ModelRenderer(this);
        rightAntennaBobble.setRotationPoint(0.0F, -6.0F, 0.0F);
        rightAntenna2.addChild(rightAntennaBobble);
        setRotationAngle(rightAntennaBobble, 0.3927F, 0.0F, 0.0F);
        rightAntennaBobble.setTextureOffset(0, 16).addBox(-1.525F, -3.0F, -2.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
    }
    
    @Override
    public void setRotationAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        //previously the render function, render code was moved to a method below
    }
    
    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha)
    {
        alienBase.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
    
    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}