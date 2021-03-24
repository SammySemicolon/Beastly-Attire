package com.sammy.beastly_attire.common.events;

import com.sammy.beastly_attire.BeastlyAttireHelper;
import com.sammy.beastly_attire.BeastlyAttireMod;
import com.sammy.beastly_attire.init.BAItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BeastlyAttireMod.MODID)
public class RuntimeEvents
{
    @SubscribeEvent
    public static void jumpHigher(LivingEvent.LivingJumpEvent event)
    {
        if (BeastlyAttireHelper.hasCurioEquipped(event.getEntityLiving(), BAItems.BUNNY_EARS))
        {
            LivingEntity entity = event.getEntityLiving();
            Vector3d motion = entity.getMotion().add(0,0.2f,0);
            event.getEntityLiving().setMotion(motion);
        }
    }
    @SubscribeEvent
    public static void clawsDamage(LivingDamageEvent event)
    {
        if (event.getSource().getTrueSource() instanceof LivingEntity)
        {
            LivingEntity entity = (LivingEntity) event.getSource().getTrueSource();
            if (BeastlyAttireHelper.hasCurioEquipped(entity, BAItems.CLAWS))
            {
                if (entity.getHeldItemMainhand().isEmpty())
                {
                    event.setAmount(event.getAmount() + 5);
                }
            }
        }
    }
    @SubscribeEvent
    public static void fireworkJump(LivingEvent.LivingJumpEvent event)
    {
        if (event.getEntityLiving() instanceof PlayerEntity)
        {
            PlayerEntity entity = (PlayerEntity) event.getEntityLiving();
            if (entity.isSneaking())
            {
                if (entity.inventory.armorInventory.get(0).getItem().equals(BAItems.ROCKET_BOOTS.get()))
                {
                    if (!entity.getCooldownTracker().hasCooldown(BAItems.ROCKET_BOOTS.get()))
                    {
                        entity.addVelocity(0, 0.4f, 0);
                        entity.world.playSound(null, entity.getPosition(), SoundEvents.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, SoundCategory.PLAYERS, 1, 1);
                        entity.world.playSound(null, entity.getPosition(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 1, 1);
                        entity.getCooldownTracker().setCooldown(BAItems.ROCKET_BOOTS.get(), 100);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void fireworkReadySound(LivingEvent.LivingUpdateEvent event)
    {
        if (event.getEntityLiving() instanceof PlayerEntity)
        {
            PlayerEntity entity = (PlayerEntity) event.getEntityLiving();

            if (entity.isSneaking())
            {
                if (entity.inventory.armorInventory.get(0).getItem().equals(BAItems.ROCKET_BOOTS.get()))
                {
                    if (!entity.getCooldownTracker().hasCooldown(BAItems.ROCKET_BOOTS.get()))
                    {
                        if (entity.world.getGameTime() % 20L == 0)
                        {
                            entity.world.playSound(null, entity.getPosition(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.PLAYERS, 1, 2);
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void inputDisk(PlayerInteractEvent event)
    {
        if (event instanceof PlayerInteractEvent.RightClickItem || event instanceof PlayerInteractEvent.RightClickBlock)
        {
            ItemStack stack = event.getItemStack();
            PlayerEntity playerEntity = event.getPlayer();
            if (stack.getItem() instanceof MusicDiscItem)
            {
                if (BeastlyAttireHelper.hasCurioEquipped(playerEntity, BAItems.HEADPHONES))
                {
                    playerEntity.world.playEvent(playerEntity, 1010, playerEntity.getPosition(), Item.getIdFromItem(stack.getItem()));
                    playerEntity.swingArm(event.getHand());
                }
            }
        }
    }
    @SubscribeEvent
    public static void wizardHatDamage(LivingDamageEvent event)
    {
        if (event.getSource().isMagicDamage())
        {
            if (event.getSource().getTrueSource() instanceof LivingEntity)
            {
                LivingEntity entity = (LivingEntity) event.getSource().getTrueSource();
                if (BeastlyAttireHelper.hasCurioEquipped(entity, BAItems.WIZARD_HAT))
                {
                    event.setAmount(event.getAmount() * 1.25f);
                }
            }
        }
    }
    @SubscribeEvent
    public static void noPoisonPlease(PotionEvent.PotionApplicableEvent event)
    {
        if (event.getPotionEffect().getPotion().equals(Effects.POISON))
        {
            if (BeastlyAttireHelper.hasCurioEquipped(event.getEntityLiving(), BAItems.MASK))
            {
                event.setResult(Event.Result.DENY);
            }
        }
    }

//    @SubscribeEvent
//    public void noTargetCate(LivingSetAttackTargetEvent event)
//    {
//        if (event.getEntity() instanceof CreeperEntity)
//        {
//            if (BeastlyAttireHelper.hasCurioEquipped(event.getTarget(), BAItems.CAT_EARS))
//            {
//                event.setCanceled(true);
//            }
//        }
//    }
//
//    @SubscribeEvent
//    public void runFromCats(EntityJoinWorldEvent event)
//    {
//        if (event.getEntity() instanceof CreeperEntity)
//        {
//            CreeperEntity entity = (CreeperEntity) event.getEntity();
//            entity.goalSelector.addGoal(3, new AvoidEntityGoal<>(entity, PlayerEntity.class, (e) -> BeastlyAttireHelper.hasCurioEquipped(e, BAItems.CAT_EARS), 6, 1, 1.3, EntityPredicates.CAN_AI_TARGET::test));
//        }
//    }
}