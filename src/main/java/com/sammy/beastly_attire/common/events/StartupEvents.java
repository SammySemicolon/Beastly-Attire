package com.sammy.beastly_attire.common.events;

import com.sammy.beastly_attire.init.Registries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import top.theillusivec4.curios.api.SlotTypeMessage;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class StartupEvents
{
    
    @SubscribeEvent
    public static void registerKeybind(FMLClientSetupEvent event)
    {
        ClientRegistry.registerKeyBinding(Registries.bodyStrapKeybind);
        
    }
    @SubscribeEvent
    public static void registerCurios(InterModEnqueueEvent event)
    {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("head").size(3).cosmetic().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("back").size(1).cosmetic().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("charm").size(1).cosmetic().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("hands").size(1).cosmetic().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("belt").size(1).cosmetic().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("necklace").size(1).cosmetic().build());
    }
}
