package com.sammy.beastly_attire.common.items.equipment.curios;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.sammy.beastly_attire.BeastlyAttireHelper;
import com.sammy.beastly_attire.client.models.BlazeBeltModel;
import com.sammy.beastly_attire.client.models.HeadphonesModel;
import com.sammy.beastly_attire.init.Registries;
import com.sammy.beastly_attire.systems.inventory.ContainerInventory;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.ItemHandlerHelper;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.ArrayList;
import java.util.List;

public class BlazeBeltCurioItem extends Item implements ICurio
{
    public BlazeBeltCurioItem(Properties builder)
    {
        super(builder);
    }

    public final ResourceLocation texture = BeastlyAttireHelper.prefix("textures/armor/blaze_belt.png");
    public BlazeBeltModel<LivingEntity> model;
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
                    model = new BlazeBeltModel<>();
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
                World world = livingEntity.world;
                if (BeastlyAttireHelper.areWeOnServer(world))
                {
                    if (livingEntity instanceof PlayerEntity)
                    {
                        PlayerEntity playerEntity = (PlayerEntity) livingEntity;
                        if (!playerEntity.getHeldItemOffhand().isEmpty())
                        {
                            CompoundNBT nbt = stack.getOrCreateTag();
                            int progress = nbt.getInt("progress");
                            if (world.getGameTime() % 20L == 0)
                            {
                                if (playerEntity.isSprinting())
                                {
                                    progress++;
                                    world.playSound(null, playerEntity.getPosX(), playerEntity.getPosY(),playerEntity.getPosZ(), SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.PLAYERS,0.2f,1);
                                    nbt.putInt("progress", progress);
                                }
                            }
                            if (progress >= 10)
                            {
                                ItemStack offHandItem = playerEntity.getHeldItemOffhand();
                                RecipeManager recipeManager = world.getRecipeManager();
                                List<FurnaceRecipe> cookingRecipes = recipeManager.getRecipesForType(IRecipeType.SMELTING);
                                IRecipe<?> iRecipe = cookingRecipes.stream().filter(r -> r.ingredient.test(offHandItem)).findFirst().orElse(null);
                                if (iRecipe != null)
                                {
                                    ItemStack outputItem = iRecipe.getRecipeOutput();
                                    ItemHandlerHelper.giveItemToPlayer(playerEntity, outputItem);
                                    offHandItem.shrink(1);
                                    playerEntity.addPotionEffect(new EffectInstance(Registries.BLAZE_BELT_SPEED.get(), 300, 0));
                                    world.playSound(null, playerEntity.getPosX(), playerEntity.getPosY(),playerEntity.getPosZ(), SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS,0.2f,1);
                                    nbt.putInt("progress", 0);
                                }
                            }
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