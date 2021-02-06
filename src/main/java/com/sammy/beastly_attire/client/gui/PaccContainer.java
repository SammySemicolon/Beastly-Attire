package com.sammy.beastly_attire.client.gui;

import com.sammy.beastly_attire.BeastlyAttireHelper;
import com.sammy.beastly_attire.init.Registries;
import com.sammy.beastly_attire.systems.inventory.ContainerInventory;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;
import top.theillusivec4.curios.common.inventory.CosmeticCurioSlot;
import top.theillusivec4.curios.common.inventory.CurioSlot;
import top.theillusivec4.curios.common.inventory.container.CuriosContainer;
import top.theillusivec4.curios.common.network.NetworkHandler;
import top.theillusivec4.curios.common.network.client.CPacketScroll;
import top.theillusivec4.curios.common.network.server.SPacketScroll;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.Map;

public class PaccContainer extends Container
{
    public final LazyOptional<ICuriosItemHandler> curiosHandler;
    private final PlayerEntity player;
    private boolean cosmeticColumn;
    private int lastScrollIndex;
    private final boolean isLocalWorld;
    
    public ItemStack box;
    
    public boolean hasCosmeticColumn()
    {
        return this.cosmeticColumn;
    }
    
    public boolean canScroll()
    {
        return this.curiosHandler.map((curios) -> curios.getVisibleSlots() > 8 ? 1 : 0).orElse(0) == 1;
    }
    
    public PaccContainer(int windowId, PlayerInventory playerInv, ItemStack box)
    {
        super(Registries.PACC_CONTAINER.get(), windowId);
    
        this.box = box;
        ContainerInventory inventory = create(box);
        this.player = playerInv.player;
        this.curiosHandler = CuriosApi.getCuriosHelper().getCuriosHandler(this.player);
        this.isLocalWorld = this.player.world.isRemote;
        
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 6; ++j)
            {
                int index = i * 6 + j;
                addSlot(new Slot(inventory, index, 62 + j * 18, 26 + i * 18)
                {
                    @Override
                    public boolean isItemValid(@Nonnull ItemStack stack)
                    {
                        return BeastlyAttireHelper.isCurio(stack);
                    }
                });
            }
        }
    
        for (int l = 0; l < 3; ++l)
        {
            for (int j1 = 0; j1 < 9; ++j1)
            {
                this.addSlot(new Slot(playerInv, j1 + (l + 1) * 9, 8 + j1 * 18, 84 + l * 18));
            }
        }
    
        for (int i1 = 0; i1 < 9; ++i1)
        {
            if (playerInv.currentItem != i1)
            {
                this.addSlot(new Slot(playerInv, i1, 8 + i1 * 18, 142));
            }
        }
        this.curiosHandler.ifPresent((curios) -> {
            Map<String, ICurioStacksHandler> curioMap = curios.getCurios();
            int slots = 0;
            int yOffset = 12;
            Iterator var5 = curioMap.keySet().iterator();
    
            while (true)
            {
                String identifier;
                ICurioStacksHandler stacksHandler;
                IDynamicStackHandler stackHandler;
                do
                {
                    if (!var5.hasNext())
                    {
                        return;
                    }
    
                    identifier = (String) var5.next();
                    stacksHandler = curioMap.get(identifier);
                    stackHandler = stacksHandler.getStacks();
                } while (!stacksHandler.isVisible());
    
                for (int i = 0; i < stackHandler.getSlots() && slots < 8; ++i)
                {
                    this.addSlot(new CurioSlot(this.player, stackHandler, i, identifier, -18, yOffset, stacksHandler.getRenders()));
                    if (stacksHandler.hasCosmetic())
                    {
                        IDynamicStackHandler cosmeticHandler = stacksHandler.getCosmeticStacks();
                        this.cosmeticColumn = true;
                        this.addSlot(new CosmeticCurioSlot(this.player, cosmeticHandler, i, identifier, -37, yOffset));
                    }
    
                    yOffset += 18;
                    ++slots;
                }
            }
        });
        this.scrollToIndex(0);
    }
    
    public void scrollToIndex(int indexIn)
    {
        this.curiosHandler.ifPresent((curios) -> {
            Map<String, ICurioStacksHandler> curioMap = curios.getCurios();
            int slots = 0;
            int yOffset = 12;
            int index = 0;
            this.inventorySlots.subList(54, this.inventorySlots.size()).clear();
            if (this.inventoryItemStacks != null)
            {
                this.inventoryItemStacks.subList(54, this.inventoryItemStacks.size()).clear();
            }
            
            Iterator var7 = curioMap.keySet().iterator();
            
            while (true)
            {
                String identifier;
                ICurioStacksHandler stacksHandler;
                IDynamicStackHandler stackHandler;
                do
                {
                    if (!var7.hasNext())
                    {
                        if (!this.isLocalWorld)
                        {
                            NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) this.player), new SPacketScroll(this.windowId, indexIn));
                        }
                        
                        this.lastScrollIndex = indexIn;
                        return;
                    }
                    
                    identifier = (String) var7.next();
                    stacksHandler = curioMap.get(identifier);
                    stackHandler = stacksHandler.getStacks();
                } while (!stacksHandler.isVisible());
                
                for (int i = 0; i < stackHandler.getSlots() && slots < 8; ++i)
                {
                    if (index >= indexIn)
                    {
                        this.addSlot(new CurioSlot(this.player, stackHandler, i, identifier, -18, yOffset, stacksHandler.getRenders()));
                        if (stacksHandler.hasCosmetic())
                        {
                            IDynamicStackHandler cosmeticHandler = stacksHandler.getCosmeticStacks();
                            this.cosmeticColumn = true;
                            this.addSlot(new CosmeticCurioSlot(this.player, cosmeticHandler, i, identifier, -37, yOffset));
                        }
                        
                        yOffset += 18;
                        ++slots;
                    }
                    
                    ++index;
                }
            }
        });
    }
    
    public void scrollTo(float pos)
    {
        this.curiosHandler.ifPresent((curios) -> {
            int k = curios.getVisibleSlots() - 8;
            int j = (int) ((double) (pos * (float) k) + 0.5D);
            if (j < 0)
            {
                j = 0;
            }
            if (j != this.lastScrollIndex)
            {
                if (this.isLocalWorld)
                {
                    NetworkHandler.INSTANCE.send(PacketDistributor.SERVER.noArg(), new CPacketScroll(this.windowId, j));
                }
                
            }
        });
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
    public ItemStack transferStackInSlot(PlayerEntity player, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < 18)
            {
                if (!mergeItemStack(itemstack1, 54, inventoryItemStacks.size(), false))
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