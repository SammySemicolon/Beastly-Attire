package com.sammy.beastly_attire.network.packets;

import com.sammy.beastly_attire.client.gui.PaccContainer;
import com.sammy.beastly_attire.client.gui.PaccGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import top.theillusivec4.curios.client.gui.CuriosScreen;
import top.theillusivec4.curios.common.inventory.container.CuriosContainer;

import java.util.function.Supplier;

public class SPacketScrollCopy
{
    public int windowId;
    public int index;
    
    public SPacketScrollCopy(int windowId, int index)
    {
        this.windowId = windowId;
        this.index = index;
        
    }
    
    public static SPacketScrollCopy decode(PacketBuffer buf)
    {
        return new SPacketScrollCopy(buf.readInt(), buf.readInt());
    }
    
    public void encode(PacketBuffer buf)
    {
        buf.writeInt(windowId);
        buf.writeInt(index);
    }
    
    
    public void whenThisPacketIsReceived(Supplier<NetworkEvent.Context> context)
    {
        context.get().enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();
            ClientPlayerEntity clientPlayer = mc.player;
            Screen screen = mc.currentScreen;
            if (clientPlayer != null)
            {
                Container container = clientPlayer.openContainer;
                if (container instanceof PaccContainer && container.windowId == windowId)
                {
                    ((PaccContainer) container).scrollToIndex(index);
                }
            }
    
            if (screen instanceof PaccGui)
            {
                ((PaccGui) screen).updateRenderButtons();
            }
        });
        context.get().setPacketHandled(true);
    }
}