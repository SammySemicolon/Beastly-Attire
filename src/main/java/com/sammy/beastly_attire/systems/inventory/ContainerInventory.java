package com.sammy.beastly_attire.systems.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;

public class ContainerInventory extends ItemInventory implements IInventory
{
    public ContainerInventory(ItemStack stack, int slotCount, int slotSize)
    {
        super(stack, slotCount, slotSize);
    }
    
    @Override
    public int getSizeInventory()
    {
        return slotCount;
    }
    
    @Override
    public boolean isEmpty()
    {
        return nonEmptyItems() == 0;
    }
    
    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        ItemStack itemstack = ItemStackHelper.getAndSplit(stacks, index, count);
        if (!itemstack.isEmpty())
        {
            this.markDirty();
        }
    
        return itemstack;
    }
    
    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        setStackInSlot(index, ItemStack.EMPTY);
        return ItemStack.EMPTY;
    }
    
    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        setStackInSlot(index, stack);
    }
    
    @Override
    public void markDirty()
    {
    }
    
    @Override
    public boolean isUsableByPlayer(PlayerEntity player)
    {
        return true;
    }
    
    @Override
    public void clear()
    {
        clearItems();
    }
}
