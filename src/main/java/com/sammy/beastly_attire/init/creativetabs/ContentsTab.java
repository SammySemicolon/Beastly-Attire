package com.sammy.beastly_attire.init.creativetabs;

import com.sammy.beastly_attire.BeastlyAttireMod;
import com.sammy.beastly_attire.init.BAItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ContentsTab extends ItemGroup
{
    public static final ContentsTab INSTANCE = new ContentsTab();
    
    public ContentsTab() {
        super(BeastlyAttireMod.MODID);
    }
    
    @Nonnull
    @Override
    public ItemStack createIcon() {
        return new ItemStack(BAItems.PACC.get());
    }
}