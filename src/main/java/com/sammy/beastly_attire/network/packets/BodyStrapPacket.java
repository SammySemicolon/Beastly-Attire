package com.sammy.beastly_attire.network.packets;

import com.sammy.beastly_attire.BeastlyAttireHelper;
import com.sammy.beastly_attire.init.BAItems;
import com.sammy.beastly_attire.systems.inventory.ItemInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.common.CuriosHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import static net.minecraft.particles.ParticleTypes.EXPLOSION;
import static net.minecraft.particles.ParticleTypes.SMOKE;

public class BodyStrapPacket
{
    UUID uuid;
    
    public BodyStrapPacket(UUID uuid)
    {
        this.uuid = uuid;
    }
    
    public static BodyStrapPacket decode(PacketBuffer buf)
    {
        UUID uuid = buf.readUniqueId();
        return new BodyStrapPacket(uuid);
    }
    
    public void encode(PacketBuffer buf)
    {
        buf.writeUniqueId(uuid);
    }
    public ItemInventory create(ItemStack stack)
    {
        return new ItemInventory(stack,9,64);
    }
    
    public void whenThisPacketIsReceived(Supplier<NetworkEvent.Context> context)
    {
        context.get().enqueueWork(() -> {

            World world = context.get().getSender().world;
            PlayerEntity playerEntity = world.getPlayerByUuid(uuid);
            if (playerEntity != null)
            {
                Optional<ImmutableTriple<String, Integer, ItemStack>> optional = CuriosApi.getCuriosHelper().findEquippedCurio(BAItems.BODY_STRAP.get(), playerEntity);
                if (optional.isPresent())
                {
                    ItemStack stack = optional.get().right;
                    ItemInventory inventory = create(stack);
                    ArrayList<ItemStack> replacing = inventory.stacks();
                    ArrayList<ItemStack> hotbar = BeastlyAttireHelper.toArrayList(playerEntity.inventory.mainInventory.stream());
                    for (int i = 0; i < 9; i++)
                    {
                        playerEntity.inventory.mainInventory.set(i, replacing.get(i));
                        inventory.setStackInSlot(i, hotbar.get(i));
                    }
                }
            }
        });
        context.get().setPacketHandled(true);
    }
}