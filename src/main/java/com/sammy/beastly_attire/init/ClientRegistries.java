package com.sammy.beastly_attire.init;

import com.sammy.beastly_attire.client.gui.PaccGui;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.glfw.GLFW;

import static com.sammy.beastly_attire.BeastlyAttireMod.MODID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = MODID, value = Dist.CLIENT)
public class ClientRegistries {
    public static final KeyBinding BODY_STRAP_KEYBIND = new KeyBinding("key.body_strap", GLFW.GLFW_KEY_P,       "key.categories.misc");

    @SubscribeEvent
    public static void registerScreenFactory(FMLClientSetupEvent event)
    {
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            ScreenManager.registerFactory(Registries.PACC_CONTAINER.get(), PaccGui::new);
        });
    }
}
