package com.sammy.beastly_attire.client.gui;

import com.sammy.beastly_attire.BeastlyAttireHelper;
import com.sammy.beastly_attire.init.Registries;
import com.sammy.beastly_attire.systems.inventory.ContainerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import top.theillusivec4.curios.common.inventory.container.CuriosContainer;

import javax.annotation.Nonnull;

public class PaccContainer extends Container
{
    public ItemStack box;
    @Override
    protected Slot addSlot(Slot slotIn)
    {
        return super.addSlot(slotIn);
    }
    
    public PaccContainer(int windowId, PlayerInventory playerInv, ItemStack box)
    {
        super(Registries.PACC_CONTAINER.get(), windowId);
        
        this.box = box;
        ContainerInventory inventory = create(box);
        
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 6; ++j)
            {
                int index = i * 6 + j;
                addSlot(new Slot(inventory, index, 80 + j * 18, 26 + i * 18)
                {
                    @Override
                    public boolean isItemValid(@Nonnull ItemStack stack)
                    {
                        return BeastlyAttireHelper.isCurio(stack);
                    }
                });
            }
        }
    }
    
    public static ContainerInventory create(ItemStack stack)
    {
        return new ContainerInventory(stack, 18, 1);
    }
    
    public static PaccContainer makeContainer(int windowId, PlayerInventory inv, PacketBuffer buf)
    {
        Hand hand = buf.readBoolean() ? Hand.MAIN_HAND : Hand.OFF_HAND;
        return new PaccContainer(windowId, inv, inv.player.getHeldItem(hand));
    }
    
    @Override
    public boolean canInteractWith(@Nonnull PlayerEntity player)
    {
        ItemStack main = player.getHeldItemMainhand();
        ItemStack off = player.getHeldItemOffhand();
        return !main.isEmpty() && main == box || !off.isEmpty() && off == box;
    }
    
    @Nonnull
    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int slotIndex)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(slotIndex);
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            int start;
            int end;
            if (slotIndex < 18)
            {
                if (!mergeItemStack(itemstack1, 18, 54, true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else
            {
                if (!itemstack1.isEmpty() && BeastlyAttireHelper.isCurio(itemstack1) && !mergeItemStack(itemstack1, 0, 18, false))
                {
                    return ItemStack.EMPTY;
                }
            }
        
            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }
        
            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }
        
            slot.onTake(player, itemstack1);
        }
        return itemstack;
    }
}