package com.sammy.beastly_attire.common.items.equipment;

import com.sammy.beastly_attire.client.gui.PaccContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;

public class PACCItem extends Item
{
    public PACCItem(Properties properties)
    {
        super(properties);
    }
    
    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, @Nonnull Hand hand)
    {
        if (!world.isRemote)
        {
            ItemStack stack = player.getHeldItem(hand);
            INamedContainerProvider container = new SimpleNamedContainerProvider((w, p, pl) -> new PaccContainer(w, p, stack), stack.getDisplayName());
            NetworkHooks.openGui((ServerPlayerEntity) player, container, b -> b.writeBoolean(hand == Hand.MAIN_HAND));
        }
        return ActionResult.resultSuccess(player.getHeldItem(hand));
    }
}
