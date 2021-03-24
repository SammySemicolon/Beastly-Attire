package com.sammy.beastly_attire.network;

import com.sammy.beastly_attire.BeastlyAttireHelper;
import com.sammy.beastly_attire.BeastlyAttireMod;
import com.sammy.beastly_attire.network.packets.BodyStrapPacket;
import com.sammy.beastly_attire.network.packets.CPacketScrollCopy;
import com.sammy.beastly_attire.network.packets.SPacketScrollCopy;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = BeastlyAttireMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NetworkManager
{
    public static final String PROTOCOL_VERSION = "1";
    public static SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(BeastlyAttireHelper.prefix("main"), () -> NetworkManager.PROTOCOL_VERSION, NetworkManager.PROTOCOL_VERSION::equals, NetworkManager.PROTOCOL_VERSION::equals);
    
    @SuppressWarnings("UnusedAssignment")
    @SubscribeEvent
    public static void registerNetworkStuff(FMLCommonSetupEvent event)
    {
        int index = 0;
        INSTANCE.registerMessage(index++, BodyStrapPacket.class, BodyStrapPacket::encode, BodyStrapPacket::decode, BodyStrapPacket::whenThisPacketIsReceived);
        INSTANCE.registerMessage(index++, CPacketScrollCopy.class, CPacketScrollCopy::encode, CPacketScrollCopy::decode, CPacketScrollCopy::whenThisPacketIsReceived);
        INSTANCE.registerMessage(index++, SPacketScrollCopy.class, SPacketScrollCopy::encode, SPacketScrollCopy::decode, SPacketScrollCopy::whenThisPacketIsReceived);
        
    }
}