package com.sammy.beastly_attire.init;

import com.sammy.beastly_attire.client.gui.PaccContainer;
import com.sammy.beastly_attire.client.gui.PaccGui;
import com.sammy.beastly_attire.common.effects.BlazeBeltSpeedEffect;
import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.Effect;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import org.lwjgl.glfw.GLFW;

import static com.sammy.beastly_attire.BeastlyAttireMod.MODID;
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = MODID)
public class Registries
{
    //temporary class, disregard.
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MODID);
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MODID);
    public static DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MODID);
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, MODID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MODID);
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);
    
    public static final RegistryObject<ContainerType<PaccContainer>> PACC_CONTAINER = CONTAINERS.register("pacc_contanier", () -> IForgeContainerType.create(PaccContainer::makeContainer));
    
    public static final RegistryObject<Effect> BLAZE_BELT_SPEED = EFFECTS.register("blazing", BlazeBeltSpeedEffect::new);
    
    public static final KeyBinding bodyStrapKeybind = new KeyBinding("key.body_strap", GLFW.GLFW_KEY_P,       "key.categories.misc");
    
    @SubscribeEvent
    public static void registerScreenFactory(FMLClientSetupEvent event)
    {
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            ScreenManager.registerFactory(PACC_CONTAINER.get(), PaccGui::new);
        });
    }
}
