package com.sammy.beastly_attire;

import com.sammy.beastly_attire.data.*;
import com.sammy.beastly_attire.init.BAItems;
import net.minecraft.data.BlockTagsProvider;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.sammy.beastly_attire.init.BAItems.ITEMS;
import static com.sammy.beastly_attire.init.Registries.*;

@Mod("beastly_attire")
public class BeastlyAttireMod
{
    public static final String MODID = "beastly_attire";
    private static final Logger LOGGER = LogManager.getLogger();
    
    public BeastlyAttireMod()
    {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        ENCHANTMENTS.register(modBus);
        BLOCKS.register(modBus);
        SOUNDS.register(modBus);
        CONTAINERS.register(modBus);
        ITEMS.register(modBus);
        TILE_ENTITIES.register(modBus);
        ENTITY_TYPES.register(modBus);
        EFFECTS.register(modBus);
        PARTICLES.register(modBus);
        SOUNDS.register(modBus);
    
        modBus.addListener(this::gatherData);
    }
    public void gatherData(GatherDataEvent evt)
    {
        BlockTagsProvider provider = new ModBlockTagProvider(evt.getGenerator());
        evt.getGenerator().addProvider(new ModBlockStateProvider(evt.getGenerator(), evt.getExistingFileHelper()));
        evt.getGenerator().addProvider(new ModItemModelProvider(evt.getGenerator(), evt.getExistingFileHelper()));
        evt.getGenerator().addProvider(new ModLangProvider(evt.getGenerator()));
        evt.getGenerator().addProvider(provider);
        evt.getGenerator().addProvider(new ModLootTableProvider(evt.getGenerator()));
        evt.getGenerator().addProvider(new ModItemTagProvider(evt.getGenerator(),provider));
        evt.getGenerator().addProvider(new ModRecipeProvider(evt.getGenerator()));
    }
}