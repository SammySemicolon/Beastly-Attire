package com.sammy.beastly_attire.data;

import com.sammy.beastly_attire.BeastlyAttireHelper;
import com.sammy.beastly_attire.init.BAItems;
import net.minecraft.block.Block;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.RegistryObject;
import java.util.HashSet;
import java.util.Set;

import static com.sammy.beastly_attire.BeastlyAttireMod.MODID;
import static com.sammy.beastly_attire.init.Registries.*;


public class ModLangProvider extends LanguageProvider
{
    public ModLangProvider(DataGenerator gen)
    {
        super(gen, MODID, "en_us");
    }
    
    @Override
    protected void addTranslations()
    {
        Set<RegistryObject<Block>> blocks = new HashSet<>(BLOCKS.getEntries());
        Set<RegistryObject<Item>> items = new HashSet<>(BAItems.ITEMS.getEntries());
        Set<RegistryObject<Enchantment>> enchantments = new HashSet<>(ENCHANTMENTS.getEntries());
        Set<RegistryObject<Effect>> effects = new HashSet<>(EFFECTS.getEntries());
        BeastlyAttireHelper.takeAll(items, i -> i.get() instanceof BlockItem);
        BeastlyAttireHelper.takeAll(blocks, i -> i.get() instanceof WallTorchBlock);
        blocks.forEach(b -> {
            String name = b.get().getTranslationKey().replaceFirst("block.beastly_attire.", "");
            name = BeastlyAttireHelper.toTitleCase(specialBlockNameChanges(name), "_");
            add(b.get().getTranslationKey(), name);
        });
        
        items.forEach(i -> {
            if (!(i.get() instanceof BlockItem))
            {
                String name = i.get().getTranslationKey().replaceFirst("item.beastly_attire.", "");
                name = BeastlyAttireHelper.toTitleCase(specialBlockNameChanges(name), "_");
                add(i.get().getTranslationKey(), name);
            }
        });
        
        enchantments.forEach(e -> {
            String name = BeastlyAttireHelper.toTitleCase(e.getId().getPath(), "_");
            add(e.get().getName(), name);
        });
        
        effects.forEach(e -> {
            String name = BeastlyAttireHelper.toTitleCase(e.getId().getPath(), "_");
            add("effect.beastly_attire." + e.get().getRegistryName().getPath(), name);
        });
        
        add("itemGroup.beastly_attire", "Beastly Attire");
    }
    
    @Override
    public String getName()
    {
        return "Lang Entries";
    }
    
    public void addTooltip(String identifier, String tooltip)
    {
        add("beastly_attire.tooltip." + identifier, tooltip);
    }
    public String specialBlockNameChanges(String name)
    {
        if ((!name.endsWith("_bricks")))
        {
            if (name.contains("bricks"))
            {
                name = name.replaceFirst("bricks", "brick");
            }
        }
        if (name.contains("_fence") || name.contains("_button"))
        {
            if (name.contains("planks"))
            {
                name = name.replaceFirst("_planks", "");
            }
        }
        return name;
    }
}