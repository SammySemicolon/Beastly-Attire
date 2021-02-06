package com.sammy.beastly_attire.common.events;

import com.sammy.beastly_attire.BeastlyAttireHelper;
import com.sammy.beastly_attire.BeastlyAttireMod;
import com.sammy.beastly_attire.init.BAItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

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
            if (BeastlyAttireHelper.hasCurioEquipped(event.getEntityLiving(), BAItems.MASH))
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