package com.sammy.beastly_attire.common.items.equipment.curios;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.sammy.beastly_attire.BeastlyAttireHelper;
import com.sammy.beastly_attire.client.models.BlazeBeltModel;
import com.sammy.beastly_attire.client.models.ClawsModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class ClawsCurioItem extends Item implements ICurio
{
    public ClawsCurioItem(Properties builder)
    {
        super(builder);
    }
    
    public final ResourceLocation texture = BeastlyAttireHelper.prefix("textures/armor/claws.png");
    public ClawsModel<LivingEntity> model;
    
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused)
    {
        return CurioProvider.createProvider(new ICurio()
        {
            @Override
            public void playRightClickEquipSound(LivingEntity livingEntity)
            {
                livingEntity.world.playSound(null, livingEntity.getPosition(), SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.NEUTRAL, 1.0f, 1.0f);
            }
    
            @Override
            public boolean canRender(String identifier, int index, LivingEntity livingEntity)
            {
                return true;
            }
    
            @Override
            public void render(String identifier, int index, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int light, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
            {
                matrixStack.push();
                if (model == null)
                {
                    model = new ClawsModel<>();
                }
                model.setLivingAnimations(livingEntity, limbSwing, limbSwingAmount, partialTicks);
                model.setRotationAngles(livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                ICurio.RenderHelper.followBodyRotations(livingEntity, model);
                IVertexBuilder bucketBuilder = ItemRenderer.getBuffer(renderTypeBuffer, model.getRenderType(texture), false, stack.hasEffect());
                model.render(matrixStack, bucketBuilder, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
                matrixStack.pop();
            }
    
            @Override
            public boolean showAttributesTooltip(String identifier)
            {
                return false;
            }
    
            @Override
            public boolean canRightClickEquip()
            {
                return true;
            }
        });
    }
}