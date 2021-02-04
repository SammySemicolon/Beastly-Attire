package com.sammy.beastly_attire.init;

import com.sammy.beastly_attire.common.items.equipment.curios.SimpleCurioItem;
import com.sammy.beastly_attire.init.creativetabs.ContentsTab;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

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
}
