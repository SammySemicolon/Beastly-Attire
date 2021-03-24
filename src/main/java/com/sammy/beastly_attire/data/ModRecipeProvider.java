package com.sammy.beastly_attire.data;

import com.sammy.beastly_attire.init.BAItems;
import net.minecraft.advancements.criterion.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static net.minecraft.data.CookingRecipeBuilder.smeltingRecipe;
import static net.minecraft.data.ShapedRecipeBuilder.shapedRecipe;
import static net.minecraft.data.ShapelessRecipeBuilder.shapelessRecipe;

public class ModRecipeProvider extends RecipeProvider
{
    public ModRecipeProvider(DataGenerator generatorIn)
    {
        super(generatorIn);
    }
    
    @Override
    public String getName()
    {
        return "Recipe Provider";
    }
    
    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer)
    {
        shapedRecipe(BAItems.PACC.get()).key('#', Tags.Items.ENDER_PEARLS).key('X', Tags.Items.GEMS_DIAMOND).key('Y', Tags.Items.LEATHER).key('Z', Items.GOLD_INGOT).key('W', Items.PURPLE_DYE).patternLine("WZW").patternLine("Y#Y").patternLine("WXW").addCriterion("has_ender_pearl", hasItem(Tags.Items.ENDER_PEARLS)).build(consumer);
        shapedRecipe(BAItems.MOUSTACHE.get()).key('#', Tags.Items.DYES_BROWN).patternLine(" # ").patternLine("# #").addCriterion("has_brown_dye", hasItem(Tags.Items.DYES_BROWN)).build(consumer);
        shapedRecipe(BAItems.ALIEN_HEADBAND.get()).key('#', Tags.Items.SLIMEBALLS).key('X', Tags.Items.RODS_WOODEN).key('Y', Tags.Items.STRING).patternLine("# #").patternLine("X X").patternLine(" Y ").addCriterion("has_slime_ball", hasItem(Tags.Items.SLIMEBALLS)).build(consumer);
        shapedRecipe(BAItems.BLAZE_BELT.get()).key('#', Tags.Items.INGOTS_NETHERITE).key('X', Items.BASALT).key('Y', Tags.Items.RODS_BLAZE).patternLine("#Y#").patternLine("XXX").addCriterion("has_netherite", hasItem(Tags.Items.INGOTS_NETHERITE)).build(consumer);
        shapedRecipe(BAItems.WIZARD_HAT.get()).key('#', Tags.Items.DYES_RED).key('X', Tags.Items.INGOTS_GOLD).key('Y', Tags.Items.GEMS_LAPIS).key('Z', Items.GOLDEN_HELMET).patternLine(" # ").patternLine("#X#").patternLine("YZY").addCriterion("has_gold", hasItem(Tags.Items.INGOTS_GOLD)).build(consumer);
        shapedRecipe(BAItems.WIZARD_ROBES.get()).key('#', Tags.Items.DYES_RED).key('X', Tags.Items.INGOTS_GOLD).key('Y', Tags.Items.GEMS_LAPIS).key('Z', Items.GOLDEN_CHESTPLATE).patternLine("# #").patternLine("XZX").patternLine("Y Y").addCriterion("has_gold", hasItem(Tags.Items.INGOTS_GOLD)).build(consumer);
        shapedRecipe(BAItems.CAT_EARS.get()).key('#', Tags.Items.STRING).key('X', Tags.Items.LEATHER).key('Y', ItemTags.FISHES).patternLine("#Y#").patternLine("X#X").addCriterion("has_fish", hasItem(ItemTags.FISHES)).build(consumer);
        shapedRecipe(BAItems.ELF_EARS.get()).key('#', Tags.Items.STRING).key('X', Tags.Items.LEATHER).patternLine(" X").patternLine("# ").addCriterion("has_leather", hasItem(Tags.Items.LEATHER)).build(consumer);
        shapedRecipe(BAItems.CLAWS.get()).key('#', Tags.Items.LEATHER).key('X', Tags.Items.INGOTS_IRON).key('Y', Tags.Items.NUGGETS_IRON).patternLine(" # ").patternLine("XXX").patternLine("YYY").addCriterion("has_iron_ingot", hasItem(Tags.Items.INGOTS_IRON)).build(consumer);
        shapedRecipe(BAItems.SKULL_SHOULDER.get()).key('#', Tags.Items.INGOTS_IRON).key('X', Items.SKELETON_SKULL).patternLine("X#X").addCriterion("has_iron_ingot", hasItem(Tags.Items.INGOTS_IRON)).build(consumer);
        shapedRecipe(BAItems.BODY_STRAP.get()).key('#', Tags.Items.LEATHER).key('X', Tags.Items.CHESTS_WOODEN).key('Y', Items.RABBIT_HIDE).patternLine(" YX").patternLine("Y#Y").patternLine("XY ").addCriterion("has_rabbit_hide", hasItem(Items.RABBIT_HIDE)).build(consumer);
        shapedRecipe(BAItems.BUNNY_EARS.get()).key('#', Items.RABBIT_HIDE).key('X', Items.WHITE_WOOL).patternLine("X X").patternLine("# #").addCriterion("has_rabbit_hide", hasItem(Items.RABBIT_HIDE)).build(consumer);
        shapedRecipe(BAItems.ROCKET_BOOTS.get()).key('#', Tags.Items.LEATHER).key('X', Items.LEATHER_BOOTS).key('Y', Tags.Items.NUGGETS_GOLD).key('Z', Items.FIREWORK_ROCKET).patternLine("#Y#").patternLine("ZXZ").addCriterion("has_fireworks", hasItem(Items.FIREWORK_ROCKET)).build(consumer);
        shapedRecipe(BAItems.GOLD_CHAIN.get()).key('#', Items.CHAIN).key('X', Tags.Items.NUGGETS_GOLD).patternLine(" X ").patternLine("X#X").patternLine(" X ").addCriterion("has_gold", hasItem(Tags.Items.NUGGETS_GOLD)).build(consumer);
        shapedRecipe(BAItems.MASK.get()).key('#', Items.PAPER).key('X', Tags.Items.STRING).key('Y', Items.MILK_BUCKET).patternLine(" X ").patternLine("XYX").patternLine("###").addCriterion("has_milk_bucket", hasItem(Items.MILK_BUCKET)).build(consumer);
        shapedRecipe(BAItems.HEADPHONES.get()).key('#', Items.JUKEBOX).key('X', Tags.Items.DUSTS_REDSTONE).key('Y', Tags.Items.INGOTS_IRON).patternLine("Y#Y").patternLine("X X").patternLine("Y Y").addCriterion("has_jukebox", hasItem(Items.JUKEBOX)).build(consumer);
        shapedRecipe(BAItems.HEAD_FLOWER.get()).key('#', ItemTags.FLOWERS).key('X', Items.LEATHER_HELMET).patternLine("#").patternLine("X").addCriterion("has_leather_helmet", hasItem(Items.LEATHER_HELMET)).build(consumer);
        shapedRecipe(BAItems.FLOWER_CROWN.get()).key('#', ItemTags.FLOWERS).key('X', ItemTags.LEAVES).patternLine(" # ").patternLine("#X#").patternLine(" # ").addCriterion("has_leather_helmet", hasItem(Items.LEATHER_HELMET)).build(consumer);

    }
    private static void smithingReinforce(Consumer<IFinishedRecipe> recipeConsumer, Item itemToReinforce, Item output)
    {
        SmithingRecipeBuilder.smithingRecipe(Ingredient.fromItems(itemToReinforce), Ingredient.fromItems(Items.NETHERITE_INGOT), output).addCriterion("has_netherite_ingot", hasItem(Items.NETHERITE_INGOT)).build(recipeConsumer, Registry.ITEM.getKey(output.asItem()).getPath() + "_smithing");
    }
    private static void shapelessPlanks(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider planks, ITag<Item> input)
    {
        shapelessRecipe(planks, 4).addIngredient(input).setGroup("planks").addCriterion("has_logs", hasItem(input)).build(recipeConsumer);
    }
    private static void shapelessWood(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider stripped, IItemProvider input)
    {
        shapedRecipe(stripped, 3).key('#', input).patternLine("##").patternLine("##").setGroup("bark").addCriterion("has_log", hasItem(input)).build(recipeConsumer);
    }
    private static void shapelessButton(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider button, IItemProvider input)
    {
        shapelessRecipe(button).addIngredient(input).addCriterion("has_input", hasItem(input)).build(recipeConsumer);
    }
    private static void shapedDoor(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider door, IItemProvider input)
    {
        shapedRecipe(door, 3).key('#', input).patternLine("##").patternLine("##").patternLine("##").addCriterion("has_input", hasItem(input)).build(recipeConsumer);
    }
    private static void shapedFence(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider fence, IItemProvider input)
    {
        shapedRecipe(fence, 3).key('#', Items.STICK).key('W', input).patternLine("W#W").patternLine("W#W").addCriterion("has_input", hasItem(input)).build(recipeConsumer);
    }
    private static void shapedFenceGate(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider fenceGate, IItemProvider input)
    {
        shapedRecipe(fenceGate).key('#', Items.STICK).key('W', input).patternLine("#W#").patternLine("#W#").addCriterion("has_input", hasItem(input)).build(recipeConsumer);
    }
    private static void shapedPressurePlate(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider pressurePlate, IItemProvider input)
    {
        shapedRecipe(pressurePlate).key('#', input).patternLine("##").addCriterion("has_input", hasItem(input)).build(recipeConsumer);
    }
    private static void shapedSlab(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider slab, IItemProvider input)
    {
        shapedRecipe(slab, 6).key('#', input).patternLine("###").addCriterion("has_input", hasItem(input)).build(recipeConsumer);
    }
    private static void shapedStairs(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider stairs, IItemProvider input)
    {
        shapedRecipe(stairs, 4).key('#', input).patternLine("#  ").patternLine("## ").patternLine("###").addCriterion("has_input", hasItem(input)).build(recipeConsumer);
    }
    private static void shapelessSolidTrapdoor(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider button, IItemProvider input)
    {
        shapelessRecipe(button).addIngredient(input).addCriterion("has_input", hasItem(input)).build(recipeConsumer);
    }
    private static void shapedTrapdoor(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider trapdoor, IItemProvider input)
    {
        shapedRecipe(trapdoor, 2).key('#', input).patternLine("###").patternLine("###").addCriterion("has_input", hasItem(input)).build(recipeConsumer);
    }
    private static void shapedSign(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider sign, IItemProvider input)
    {
        String s = Registry.ITEM.getKey(input.asItem()).getPath();
        shapedRecipe(sign, 3).setGroup("sign").key('#', input).key('X', Items.STICK).patternLine("###").patternLine("###").patternLine(" X ").addCriterion("has_" + s, hasItem(input)).build(recipeConsumer);
    }
    private static void shapelessColoredWool(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider coloredWool, IItemProvider dye)
    {
        shapelessRecipe(coloredWool).addIngredient(dye).addIngredient(Blocks.WHITE_WOOL).setGroup("wool").addCriterion("has_white_wool", hasItem(Blocks.WHITE_WOOL)).build(recipeConsumer);
    }
    private static void shapedCarpet(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider carpet, IItemProvider input)
    {
        String s = Registry.ITEM.getKey(input.asItem()).getPath();
        shapedRecipe(carpet, 3).key('#', input).patternLine("##").setGroup("carpet").addCriterion("has_" + s, hasItem(input)).build(recipeConsumer);
    }
    private static void shapelessColoredCarpet(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider coloredCarpet, IItemProvider dye)
    {
        String s = Registry.ITEM.getKey(coloredCarpet.asItem()).getPath();
        String s1 = Registry.ITEM.getKey(dye.asItem()).getPath();
        shapedRecipe(coloredCarpet, 8).key('#', Blocks.WHITE_CARPET).key('$', dye).patternLine("###").patternLine("#$#").patternLine("###").setGroup("carpet").addCriterion("has_white_carpet", hasItem(Blocks.WHITE_CARPET)).addCriterion("has_" + s1, hasItem(dye)).build(recipeConsumer, s + "_from_white_carpet");
    }
    private static void shapedBed(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider bed, IItemProvider wool)
    {
        String s = Registry.ITEM.getKey(wool.asItem()).getPath();
        shapedRecipe(bed).key('#', wool).key('X', ItemTags.PLANKS).patternLine("###").patternLine("XXX").setGroup("bed").addCriterion("has_" + s, hasItem(wool)).build(recipeConsumer);
    }
    private static void shapedColoredBed(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider coloredBed, IItemProvider dye)
    {
        String s = Registry.ITEM.getKey(coloredBed.asItem()).getPath();
        shapelessRecipe(coloredBed).addIngredient(Items.WHITE_BED).addIngredient(dye).setGroup("dyed_bed").addCriterion("has_bed", hasItem(Items.WHITE_BED)).build(recipeConsumer, s + "_from_white_bed");
    }
    private static void shapedBanner(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider banner, IItemProvider input)
    {
        String s = Registry.ITEM.getKey(input.asItem()).getPath();
        shapedRecipe(banner).key('#', input).key('|', Items.STICK).patternLine("###").patternLine("###").patternLine(" | ").setGroup("banner").addCriterion("has_" + s, hasItem(input)).build(recipeConsumer);
    }
    private static void shapedColoredGlass(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider coloredGlass, IItemProvider dye)
    {
        shapedRecipe(coloredGlass, 8).key('#', Blocks.GLASS).key('X', dye).patternLine("###").patternLine("#X#").patternLine("###").setGroup("stained_glass").addCriterion("has_glass", hasItem(Blocks.GLASS)).build(recipeConsumer);
    }
    private static void shapedGlassPane(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider pane, IItemProvider glass)
    {
        shapedRecipe(pane, 16).key('#', glass).patternLine("###").patternLine("###").setGroup("stained_glass_pane").addCriterion("has_glass", hasItem(glass)).build(recipeConsumer);
    }
    private static void shapedColoredPane(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider coloredPane, IItemProvider dye)
    {
        String s = Registry.ITEM.getKey(coloredPane.asItem()).getPath();
        String s1 = Registry.ITEM.getKey(dye.asItem()).getPath();
        shapedRecipe(coloredPane, 8).key('#', Blocks.GLASS_PANE).key('$', dye).patternLine("###").patternLine("#$#").patternLine("###").setGroup("stained_glass_pane").addCriterion("has_glass_pane", hasItem(Blocks.GLASS_PANE)).addCriterion("has_" + s1, hasItem(dye)).build(recipeConsumer, s + "_from_glass_pane");
    }
    private static void shapedColoredTerracotta(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider coloredTerracotta, IItemProvider dye)
    {
        shapedRecipe(coloredTerracotta, 8).key('#', Blocks.TERRACOTTA).key('X', dye).patternLine("###").patternLine("#X#").patternLine("###").setGroup("stained_terracotta").addCriterion("has_terracotta", hasItem(Blocks.TERRACOTTA)).build(recipeConsumer);
    }
    private static void shapedColorConcretePowder(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider coloredConcretePowder, IItemProvider dye)
    {
        shapelessRecipe(coloredConcretePowder, 8).addIngredient(dye).addIngredient(Blocks.SAND, 4).addIngredient(Blocks.GRAVEL, 4).setGroup("concrete_powder").addCriterion("has_sand", hasItem(Blocks.SAND)).addCriterion("has_gravel", hasItem(Blocks.GRAVEL)).build(recipeConsumer);
    }
    protected static EnterBlockTrigger.Instance enteredBlock(Block block)
    {
        return new EnterBlockTrigger.Instance(EntityPredicate.AndPredicate.ANY_AND, block, StatePropertiesPredicate.EMPTY);
    }
    protected static InventoryChangeTrigger.Instance hasItem(IItemProvider item)
    {
        return hasItem(ItemPredicate.Builder.create().item(item).build());
    }
    protected static InventoryChangeTrigger.Instance hasItem(ITag<Item> tag)
    {
        return hasItem(ItemPredicate.Builder.create().tag(tag).build());
    }
    protected static InventoryChangeTrigger.Instance hasItem(ItemPredicate... predicate)
    {
        return new InventoryChangeTrigger.Instance(EntityPredicate.AndPredicate.ANY_AND, MinMaxBounds.IntBound.UNBOUNDED, MinMaxBounds.IntBound.UNBOUNDED, MinMaxBounds.IntBound.UNBOUNDED, predicate);
    }
}