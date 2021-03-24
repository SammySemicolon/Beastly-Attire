package com.sammy.beastly_attire.common.items.equipment.curios;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.sammy.beastly_attire.BeastlyAttireHelper;
import com.sammy.beastly_attire.client.models.BodyStrapModel;
import com.sammy.beastly_attire.init.ClientRegistries;
import com.sammy.beastly_attire.network.NetworkManager;
import com.sammy.beastly_attire.network.packets.BodyStrapPacket;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.network.PacketDistributor;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class BodyStrapCurioItem extends Item implements ICurio
{
    public BodyStrapCurioItem(Properties builder)
    {
        super(builder);
    }
    
    public final ResourceLocation texture = BeastlyAttireHelper.prefix("textures/armor/body_strap.png");
    public BodyStrapModel<LivingEntity> model;
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
                    model = new BodyStrapModel<>();
                }
                ICurio.RenderHelper.translateIfSneaking(matrixStack, livingEntity);
                ICurio.RenderHelper.rotateIfSneaking(matrixStack, livingEntity);
                IVertexBuilder bucketBuilder = ItemRenderer.getBuffer(renderTypeBuffer, model.getRenderType(texture), false, stack.hasEffect());
                model.render(matrixStack, bucketBuilder, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
                matrixStack.pop();
            }
            @Override
            public void curioTick(String identifier, int index, LivingEntity livingEntity)
            {
                if (BeastlyAttireHelper.areWeOnClient(livingEntity.world)) {
                    if (livingEntity instanceof PlayerEntity) {
                        if (ClientRegistries.BODY_STRAP_KEYBIND.isPressed()) {
                            NetworkManager.INSTANCE.send(PacketDistributor.SERVER.noArg(), new BodyStrapPacket(livingEntity.getUniqueID()));
                        }
                    }
                }
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