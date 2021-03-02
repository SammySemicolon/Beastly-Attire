package com.sammy.beastly_attire.common.effects;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class BlazeBeltSpeedEffect extends Effect
{
    public BlazeBeltSpeedEffect()
    {
        super(EffectType.BENEFICIAL, 8171462);
        this.addAttributesModifier(Attributes.MOVEMENT_SPEED, "7a299480-4fe8-4454-9b0a-1a241174d3ea", 0.1F, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
