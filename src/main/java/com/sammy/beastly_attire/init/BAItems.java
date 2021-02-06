package com.sammy.beastly_attire.init;

import com.sammy.beastly_attire.client.gui.PaccContainer;
import com.sammy.beastly_attire.client.gui.PaccGui;
import com.sammy.beastly_attire.common.items.equipment.PACCItem;
import com.sammy.beastly_attire.common.items.equipment.curios.*;
import com.sammy.beastly_attire.init.creativetabs.ContentsTab;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import static com.sammy.beastly_attire.BeastlyAttireMod.MODID;


public class BAItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    
    public static Item.Properties DEFAULT_PROPERTIES()
    {
        return new Item.Properties().group(ContentsTab.INSTANCE);
    }
    public static Item.Properties GEAR_PROPERTIES()
    {
        return new Item.Properties().group(ContentsTab.INSTANCE).maxStackSize(1);
    }
    
    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item", () -> new SimpleCurioItem(GEAR_PROPERTIES()));
    public static final RegistryObject<Item> CAT_EARS = ITEMS.register("cat_ears", () -> new CatEarsCurioItem(GEAR_PROPERTIES()));
    public static final RegistryObject<Item> ELF_EARS = ITEMS.register("elf_ears", () -> new ElfEarsCurioItem(GEAR_PROPERTIES()));
    public static final RegistryObject<Item> CLAWS = ITEMS.register("claws", () -> new ClawsCurioItem(GEAR_PROPERTIES()));
    public static final RegistryObject<Item> WIZARD_HAT = ITEMS.register("wizard_hat", () -> new WizardHatCurioItem(GEAR_PROPERTIES()));
    public static final RegistryObject<Item> SKULL_SHOULDER = ITEMS.register("skull_shoulder", () -> new SkullShoulderCurioItem(GEAR_PROPERTIES()));
    public static final RegistryObject<Item> ALIEN_HEADBAND = ITEMS.register("alien_headband", () -> new AlienHeadbandCurioItem(GEAR_PROPERTIES()));
    public static final RegistryObject<Item> FLOWER_CROWN = ITEMS.register("flower_crown", () -> new FlowerCrownCurioItem(GEAR_PROPERTIES()));
    public static final RegistryObject<Item> BLAZE_BELT = ITEMS.register("blaze_belt", () -> new BlazeBeltCurioItem(GEAR_PROPERTIES()));
    public static final RegistryObject<Item> BODY_STRAP = ITEMS.register("body_strap", () -> new BodyStrapCurioItem(GEAR_PROPERTIES()));
    public static final RegistryObject<Item> GOLD_CHAIN = ITEMS.register("gold_chain", () -> new GoldChainCurioItem(GEAR_PROPERTIES()));
    public static final RegistryObject<Item> MOUSTACHE = ITEMS.register("moustache", () -> new MoustacheCurioItem(GEAR_PROPERTIES()));
    public static final RegistryObject<Item> BUNNY_EARS = ITEMS.register("bunny_ears", () -> new BunnyEarsCurioItem(GEAR_PROPERTIES()));
    public static final RegistryObject<Item> MASH = ITEMS.register("mash", () -> new MashCurioItem(GEAR_PROPERTIES()));
    public static final RegistryObject<Item> HAIR_FLOWER = ITEMS.register("hair_flower", () -> new HairFlowerCurioItem(GEAR_PROPERTIES()));
    public static final RegistryObject<Item> HEAD_FLOWER = ITEMS.register("head_flower", () -> new HeadFlowerCurioItem(GEAR_PROPERTIES()));
    public static final RegistryObject<Item> HEADPHONES = ITEMS.register("headphones", () -> new HeadphonesCurioItem(GEAR_PROPERTIES()));
    
    public static final RegistryObject<Item> ROCKET_BOOTS = ITEMS.register("rocket_boots", () -> new ArmorItem(ArmorMaterial.IRON, EquipmentSlotType.FEET, GEAR_PROPERTIES()));
    public static final RegistryObject<Item> PACC = ITEMS.register("pacc", () -> new PACCItem(GEAR_PROPERTIES()));
    
}
