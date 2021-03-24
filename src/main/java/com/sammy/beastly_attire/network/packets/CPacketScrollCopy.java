package com.sammy.beastly_attire.network.packets;

import com.sammy.beastly_attire.BeastlyAttireHelper;
import com.sammy.beastly_attire.client.gui.PaccContainer;
import com.sammy.beastly_attire.init.BAItems;
import com.sammy.beastly_attire.systems.inventory.ItemInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.common.inventory.container.CuriosContainer;
import top.theillusivec4.curios.common.network.client.CPacketScroll;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

public class CPacketScrollCopy
{
    public int windowId;
    public int index;
    
    public CPacketScrollCopy(int windowId, int index)
    {
        this.windowId = windowId;
        this.index = index;
        
    }
    
    public static CPacketScrollCopy decode(PacketBuffer buf)
    {
        return new CPacketScrollCopy(buf.readInt(), buf.readInt());
    }
    
    public void encode(PacketBuffer buf)
    {
        buf.writeInt(windowId);
        buf.writeInt(index);
    }
    
    
    public void whenThisPacketIsReceived(Supplier<NetworkEvent.Context> context)
    {
        context.get().enqueueWork(() -> {
            ServerPlayerEntity sender = context.get().getSender();
            if (sender != null)
            {
                Container container = sender.openContainer;
                if (container instanceof PaccContainer && container.windowId == windowId)
                {
                    ((PaccContainer) container).scrollToIndex(index);
                }
            }
        });
        context.get().setPacketHandled(true);
    }
}